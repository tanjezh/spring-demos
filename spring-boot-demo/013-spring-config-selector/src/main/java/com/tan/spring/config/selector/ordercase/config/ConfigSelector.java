package com.tan.spring.config.selector.ordercase.config;

import com.tan.spring.config.selector.ordercase.annotation.MySelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author tanjezh
 * @create 2024-05-04 22:19
 */
public class ConfigSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(MySelector.class.getName()));

        String valueStr = annotationAttributes.getString("value");
        if("config1".equalsIgnoreCase(valueStr)){
            return new String[]{Config1Selector.class.getName()};
        }else if("config2".equalsIgnoreCase(valueStr)){
            return new String[]{Config2Selector.class.getName()};
        }else{
            return new String[]{Config1Selector.class.getName(),Config2Selector.class.getName()};
        }
    }
}
