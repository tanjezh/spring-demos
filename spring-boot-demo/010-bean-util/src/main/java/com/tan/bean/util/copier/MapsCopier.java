package com.tan.bean.util.copier;

import com.tan.bean.util.copier.mapper.MapperCopier;
import com.tan.bean.util.model.Source;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * 缺点是需要定义转换接口；优点是性能最高
 * @author tanjezh
 * @create 2024-05-03 21:40
 */
@Component
public class MapsCopier {

    private MapperCopier copier = Mappers.getMapper(MapperCopier.class);

    public <K,T> T copy(K source, Class<T> targetClass){
        return (T) copier.copy((Source) source);
    }

    public <T> T copyAndParse(Source source, Class<T> targetClass){
        return (T) copier.copyAndParse(source);
    }

}
