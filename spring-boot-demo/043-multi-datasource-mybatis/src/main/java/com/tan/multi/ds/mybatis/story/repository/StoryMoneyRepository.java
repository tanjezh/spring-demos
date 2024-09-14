package com.tan.multi.ds.mybatis.story.repository;

import com.tan.multi.ds.mybatis.story.entity.StoryMoneyEntity;
import com.tan.multi.ds.mybatis.story.mapper.StoryMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanjezh
 * @create 2024/9/14 15:14
 */
@Repository
public class StoryMoneyRepository {

    @Autowired
    private StoryMoneyMapper storyMoneyMapper;

    public void query() {
        List<StoryMoneyEntity> list = storyMoneyMapper.findByIds(Arrays.asList(1, 2));
        System.out.println(list);
    }

}
