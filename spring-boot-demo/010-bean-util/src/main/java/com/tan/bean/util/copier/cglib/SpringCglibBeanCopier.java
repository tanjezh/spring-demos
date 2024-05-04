package com.tan.bean.util.copier.cglib;

import com.tan.bean.util.copier.util.StrUtil;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

/**
 * cglib bean copier 扩展，支持驼峰与下划线的转换
 *
 * @author tanjezh
 * @create 2024-05-04 14:50
 */

public class SpringCglibBeanCopier {

    private static final SpringCglibBeanCopier.BeanCopierKey KEY_FACTORY =
            (SpringCglibBeanCopier.BeanCopierKey) KeyFactory.create(SpringCglibBeanCopier.BeanCopierKey.class);

    private static final Type CONVERTER = TypeUtils.parseType("org.springframework.cglib.core.Converter");
    private static final Type BEAN_COPIER = TypeUtils.parseType("org.springframework.cglib.beans.BeanCopier");
    private static final Signature COPY;
    private static final Signature CONVERT;


    public SpringCglibBeanCopier(){
    }

    public static BeanCopier create(Class source,Class target, boolean isConverter){
        Generator generator = new Generator();
        generator.setSource(source);
        generator.setTarget(target);
        generator.setHasConverter(isConverter);
        return generator.create();
    }

    static {
        COPY = new Signature("copy", Type.VOID_TYPE, new Type[]{Constants.TYPE_OBJECT, Constants.TYPE_OBJECT, CONVERTER});
        CONVERT = TypeUtils.parseSignature("Object convert(Object, Class, Object)");
    }

    public static class Generator extends AbstractClassGenerator{
        private static final Source SOURCE = new Source(BeanCopier.class.getName());
        private Class source;
        private Class target;
        private boolean hasConverter;

        public Generator() {
            super(SOURCE);
        }

        public void setSource(Class source) {
            if(!Modifier.isPublic(source.getModifiers())){
                this.setNamePrefix(source.getName());
            }
            this.source = source;
        }

        public void setTarget(Class target) {
            if(!Modifier.isPublic(source.getModifiers())){
                this.setNamePrefix(source.getName());
            }
            this.target = target;
        }

        public void setHasConverter(boolean hasConverter) {
            this.hasConverter = hasConverter;
        }

        public BeanCopier create() {
            Object obj = SpringCglibBeanCopier.KEY_FACTORY.newInstance(this.source.getName(), this.target.getName(), this.hasConverter);
            return (BeanCopier) super.create(obj);
        }

        @Override
        protected ClassLoader getDefaultClassLoader() {
            return this.source.getClassLoader();
        }

        @Override
        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(this.source);
        }

        @Override
        protected Object firstInstance(Class type) throws Exception {
            return ReflectUtils.newInstance(type);
        }

        @Override
        protected Object nextInstance(Object instance) throws Exception {
            return instance;
        }

        @Override
        public void generateClass(ClassVisitor v) throws Exception {
            Type sourceType = Type.getType(this.source);
            Type targetType = Type.getType(this.target);
            ClassEmitter classEmitter = new ClassEmitter(v);
            classEmitter.begin_class(52,1,this.getClassName(),
                    SpringCglibBeanCopier.BEAN_COPIER,(Type[]) null,"<generated>");
            EmitUtils.null_constructor(classEmitter);

            CodeEmitter codeEmitter = classEmitter.begin_method(1, SpringCglibBeanCopier.COPY, (Type[]) null);
            PropertyDescriptor[] setters = ReflectUtils.getBeanSetters(this.target);
            Map<String, PropertyDescriptor> names = this.buildGetterNameMapper(this.source);

            Local sourceLocal = codeEmitter.make_local(sourceType);
            Local targetLocal = codeEmitter.make_local(targetType);
            if(this.hasConverter){
                codeEmitter.load_arg(1);
                codeEmitter.checkcast(targetType);
                codeEmitter.store_local(targetLocal);
                codeEmitter.load_arg(0);
                codeEmitter.checkcast(sourceType);
                codeEmitter.store_local(sourceLocal);
            }else{
                codeEmitter.load_arg(1);
                codeEmitter.checkcast(targetType);
                codeEmitter.load_arg(0);
                codeEmitter.checkcast(sourceType);
            }

            for (int i = 0; i < setters.length; i++) {
                PropertyDescriptor setter = setters[i];
                PropertyDescriptor getter = this.loadSourceGetter(names, setter);
                if(null == getter){
                    continue;
                }
                MethodInfo read = ReflectUtils.getMethodInfo(getter.getReadMethod());
                MethodInfo write = ReflectUtils.getMethodInfo(setter.getWriteMethod());

                if(this.hasConverter){
                    Type setterType = write.getSignature().getArgumentTypes()[0];
                    codeEmitter.load_local(targetLocal);
                    codeEmitter.load_arg(2);
                    codeEmitter.load_local(sourceLocal);
                    codeEmitter.invoke(read);
                    codeEmitter.box(read.getSignature().getReturnType());
                    EmitUtils.load_class(codeEmitter,setterType);
                    codeEmitter.push(write.getSignature().getName());
                    codeEmitter.invoke_interface(SpringCglibBeanCopier.CONVERTER,SpringCglibBeanCopier.CONVERT);
                    codeEmitter.unbox_or_zero(setterType);
                    codeEmitter.invoke(write);
                }else if(compatible(getter, setter)){
                    codeEmitter.dup2();
                    codeEmitter.invoke(read);
                    codeEmitter.invoke(write);
                }

            }
            codeEmitter.return_value();
            codeEmitter.end_method();
            classEmitter.end_class();
        }

        private boolean compatible(PropertyDescriptor getter, PropertyDescriptor setter) {
            return setter.getPropertyType().isAssignableFrom(getter.getPropertyType());
        }

        /**
         * 获取目标的getter方法，支持下划线与驼峰
         *
         * @param source
         * @return
         */
        private Map<String, PropertyDescriptor> buildGetterNameMapper(Class source) {
            PropertyDescriptor[] getters = ReflectUtils.getBeanGetters(source);
            Map<String, PropertyDescriptor> getterMap = new HashMap<>(getters.length);
            for (int i = 0; i < getters.length; i++) {
                String name = getters[i].getName();
                getterMap.put(name, getters[i]);
                String camel = StrUtil.toCamel(name);
                if(!name.equalsIgnoreCase(camel)){
                    getterMap.put(camel, getters[i]);
                }
            }
            return getterMap;
        }

        /**
         * 根据target的setter方法，找到source的getter方法，支持下划线与驼峰的转换
         *
         * @param names
         * @param setter
         * @return
         */
        private PropertyDescriptor loadSourceGetter(Map<String, PropertyDescriptor> names, PropertyDescriptor setter) {
            String setterName = setter.getName();
            return names.getOrDefault(setterName,names.get(StrUtil.toCamel(setterName)));
        }

    }

    interface BeanCopierKey {
        Object newInstance(String var1, String var2, boolean var3);
    }

}
