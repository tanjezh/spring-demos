package com.tan.multi.ds.mybatis.plus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tan.multi.ds.mybatis.plus.entity.MoneyPo;
import com.tan.multi.ds.mybatis.plus.mapper.MoneyMapper;
import com.tan.multi.ds.mybatis.plus.service.MoneyService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类，通过 @DS 动态切换不同数据库
 *
 * @author tanjezh
 * @create 2024-09-19 0:05
 */
@Service
@DS("story")
public class StoryMoneyServiceImpl extends ServiceImpl<MoneyMapper, MoneyPo> implements MoneyService {
}
