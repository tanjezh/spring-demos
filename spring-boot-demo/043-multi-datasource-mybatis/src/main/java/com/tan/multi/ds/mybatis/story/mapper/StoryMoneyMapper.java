package com.tan.multi.ds.mybatis.story.mapper;

import com.tan.multi.ds.mybatis.story.entity.StoryMoneyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024/9/14 15:05
 */
@Mapper
public interface StoryMoneyMapper {

    List<StoryMoneyEntity> findByIds(List<Integer> ids);

}
