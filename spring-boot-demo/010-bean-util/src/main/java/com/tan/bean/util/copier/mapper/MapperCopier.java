package com.tan.bean.util.copier.mapper;

import com.tan.bean.util.model.Source;
import com.tan.bean.util.model.Target;
import com.tan.bean.util.model.Target2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * MapsCopier 的接口映射关系
 * @author tanjezh
 * @create 2024-05-03 22:02
 */
@Mapper
public interface MapperCopier {

    // 复制并进行驼峰转换
    @Mappings({
            @Mapping(source = "user_name", target = "userName"),
            @Mapping(source = "marketPrice", target = "market_price")
    })
    Target2 copyAndParse(Source source);

    Target copy(Source source);

}
