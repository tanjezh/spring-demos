package com.tan.jpa.errorcase2;

import com.tan.jpa.errorcase2.error.entity.ErrorMetaGroupPo;
import com.tan.jpa.errorcase2.error.repository.ErrorGroupJPARepository;
import com.tan.jpa.errorcase2.right.entity.MetaGroupPO;
import com.tan.jpa.errorcase2.right.repository.GroupJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
 * @author tanjezh
 * @create 2024-08-24 23:23
 */
@Component
public class GroupManager {

    @Autowired
    private ErrorGroupJPARepository errorGroupJPARepository;

    @Autowired
    private GroupJPARepository groupJPARepository;


    public void test() {
        String group = UUID.randomUUID().toString().substring(0, 4);
        String profile = "dev";
        String desc = "测试jpa异常case!";

        try {
            int id2 = addGroup2(group, profile, desc);
            System.out.println("add2: " + groupJPARepository.findById(id2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test2() {
        String group = UUID.randomUUID().toString().substring(0, 4);
        String profile = "dev";
        String desc = "测试jpa异常case!";
        try {
            int id = addGroup1(group, profile, desc);
            System.out.println("add1: " + errorGroupJPARepository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Integer addGroup1(String group, String profile, String desc) {
        ErrorMetaGroupPo jpa = new ErrorMetaGroupPo();
        jpa.setGroup("add1: " + group);
        jpa.setDesc(desc);
        jpa.setProfile(profile);
        jpa.setDeleted(0);
        Timestamp timestamp = Timestamp.from(Instant.now());
        jpa.setCreateTime(timestamp);
        jpa.setUpdateTime(timestamp);
        ErrorMetaGroupPo res = errorGroupJPARepository.save(jpa);
        return res.getId();
    }

    public Integer addGroup2(String group, String profile, String desc) {
        MetaGroupPO jpa = new MetaGroupPO();
        jpa.setGroup("add2: " + group);
        jpa.setDesc(desc);
        jpa.setProfile(profile);
        jpa.setDeleted(0);
        Timestamp timestamp = Timestamp.from(Instant.now());
        jpa.setCreateTime(timestamp);
        jpa.setUpdateTime(timestamp);
        MetaGroupPO res = groupJPARepository.save(jpa);
        return res.getId();
    }

}
