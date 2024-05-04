package com.tan.bean.util.copier.cglib;

import com.tan.bean.util.copier.util.StrUtil;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.*;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

/**
 * cglib bean copier 扩展，支持驼峰与下划线的转换
 *
 * @author tanjezh
 * @create 2024-05-03 23:26
 */
public abstract class PureCglibBeanCopier {

    private static final BeanCopierKey KEY_FACTORY = (BeanCopierKey) KeyFactory.create(BeanCopierKey.class);
    private static final Type CONVERTER = TypeUtils.parseType("net.sf.cglib.core.Converter");
    private static final Type BEAN_COPIER = TypeUtils.parseType("net.sf.cglib.beans.BeanCopier");
    private static final Signature COPY = new Signature("copy",
            Type.VOID_TYPE, new Type[]{Constants.TYPE_OBJECT, Constants.TYPE_OBJECT, CONVERTER});
    private static final Signature CONVERT = TypeUtils.parseSignature("Object convert(Object, Class, Object)");

    interface BeanCopierKey {
        Object newInstance(String source, String target, boolean useConverter);
    }

    public static BeanCopier create(Class source,Class target,boolean useConverter){
        Generator generator = new Generator();
        generator.setSource(source);
        generator.setTarget(target);
        generator.setUseConverter(useConverter);
        return generator.create();
    }

    abstract public void copy(Object from, Object to, Converter converter);

    public static class Generator extends AbstractClassGenerator{

        private static final Source SOURCE = new Source(BeanCopier.class.getName());
        private Class source;
        private Class target;
        private boolean useConverter;

        public Generator(){
            super(SOURCE);
        }

        public void setSource(Class source) {
            if(!Modifier.isPublic(source.getModifiers())){
                setNamePrefix(source.getName());
            }
            this.source = source;
        }

        public void setTarget(Class target) {
            if(!Modifier.isPublic(target.getModifiers())){
                setNamePrefix(target.getName());
            }
            this.target = target;
        }

        public void setUseConverter(boolean useConverter) {
            this.useConverter = useConverter;
        }

        @Override
        protected ClassLoader getDefaultClassLoader() {
            return source.getClassLoader();
        }

        @Override
        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(source);
        }

        public BeanCopier create(){
            Object o = KEY_FACTORY.newInstance(source.getName(), target.getName(), useConverter);
            return (BeanCopier) super.create(o);
        }

        @Override
        protected Object firstInstance(Class aClass) throws Exception {
            return ReflectUtils.newInstance(aClass);
        }

        @Override
        protected Object nextInstance(Object o) throws Exception {
            return o;
        }

        @Override
        public void generateClass(ClassVisitor classVisitor) throws Exception {
            Type sourceType = Type.getType(source);
            Type targetType = Type.getType(target);
            ClassEmitter ce = new ClassEmitter(classVisitor);
            ce.begin_class(Constants.V1_8,
                    Constants.ACC_PUBLIC,
                    getClassName(),
                    BEAN_COPIER,
                    null,
                    Constants.SOURCE_FILE);

            EmitUtils.null_constructor(ce);
            CodeEmitter codeEmitter = ce.begin_method(Constants.ACC_PUBLIC, COPY, null);
            PropertyDescriptor[] setters = ReflectUtils.getBeanSetters(target);

            Map<String, PropertyDescriptor> names = this.buildGetterNameMapper(source);
            Local sourceLocal = codeEmitter.make_local();
            Local targetLocal = codeEmitter.make_local();

            if(useConverter){
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
                if(null != getter){
                    MethodInfo read = ReflectUtils.getMethodInfo(getter.getReadMethod());
                    MethodInfo write = ReflectUtils.getMethodInfo(setter.getWriteMethod());
                    if(useConverter){
                        Type setterType = write.getSignature().getArgumentTypes()[0];
                        codeEmitter.load_local(targetLocal);
                        codeEmitter.load_arg(2);
                        codeEmitter.load_local(sourceLocal);
                        codeEmitter.invoke(read);
                        codeEmitter.box(read.getSignature().getReturnType());
                        EmitUtils.load_class(codeEmitter,setterType);
                        codeEmitter.push(write.getSignature().getName());
                        codeEmitter.invoke_interface(CONVERTER,CONVERT);
                        codeEmitter.unbox_or_zero(setterType);
                        codeEmitter.invoke(write);
                    }else if(compatible(getter, setter)){
                        codeEmitter.dup2();
                        codeEmitter.invoke(read);
                        codeEmitter.invoke(write);
                    }
                }
            }
            codeEmitter.return_value();
            codeEmitter.end_method();
            ce.end_class();
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
            PropertyDescriptor[] getters = org.springframework.cglib.core.ReflectUtils.getBeanGetters(source);
            Map<String, PropertyDescriptor> map = new HashMap<>(getters.length);
            for (int i = 0; i < getters.length; i++) {
                String name = getters[i].getName();
                String camel = StrUtil.toCamel(name);
                map.put(name, getters[i]);
                if(!name.equalsIgnoreCase(camel)){
                    // 支持下划线转驼峰
                    map.put(camel, getters[i]);
                }
            }
            return map;
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
            return names.getOrDefault(setterName, names.get(StrUtil.toCamel(setterName)));
        }

    }

}
