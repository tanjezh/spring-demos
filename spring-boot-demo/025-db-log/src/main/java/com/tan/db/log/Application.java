package com.tan.db.log;

import com.tan.db.log.entity.MoneyPo;
import com.tan.db.log.mapper.MoneyMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@Slf4j
@SpringBootApplication
// 一定要添加 mapper 的注解，否则 mapper 注入会失败
@MapperScan(basePackages = {"com.tan.db.log.mapper"})
public class Application {

    public Application(MoneyMapper mapper, JdbcTemplate jdbcTemplate){
        MoneyPo po = new MoneyPo();
        po.setName("tan");
        po.setMoney(100L);
        po.setIsDeleted(0);
        mapper.save(po);

        MoneyPo poInDb = mapper.getById(po.getId());
        log.info("查询结果： " + poInDb);

        Map<String, Object> map = jdbcTemplate.queryForMap("select * from money where id = ?", po.getId());
        log.info("查询结果： " + map);

//        int delete = mapper.delete(po.getId());
//        log.info("删除完成：{}" , po);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
