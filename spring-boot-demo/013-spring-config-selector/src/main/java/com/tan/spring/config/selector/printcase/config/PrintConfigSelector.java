package com.tan.spring.config.selector.printcase.config;

import com.tan.spring.config.selector.printcase.PrintSelector;
import com.tan.spring.config.selector.printcase.print.ConsolePrint;
import com.tan.spring.config.selector.printcase.print.DbPrint;
import com.tan.spring.config.selector.printcase.print.FilePrint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author tanjezh
 * @create 2024-05-04 21:57
 */
public class PrintConfigSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 获取标了 @PrintSelector 注解的类，并得到里面的属性
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(PrintSelector.class.getName()));
        Class valueClz = annotationAttributes.getClass("value");

        return new String[]{valueClz.getName()};
    }

    public static class ConsoleConfig{
        @Bean
        public ConsolePrint consolePrint(){
            return new ConsolePrint();
        }
    }

    public static class FileConfig{
        @Bean
        public FilePrint filePrint(){
            return new FilePrint();
        }
    }

    public static class DbConfig {
        @Bean
        public DbPrint dbPrint(){
            return new DbPrint();
        }
    }

}
