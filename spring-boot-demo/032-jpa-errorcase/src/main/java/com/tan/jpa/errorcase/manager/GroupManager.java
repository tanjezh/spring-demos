package com.tan.jpa.errorcase.manager;

import com.tan.jpa.errorcase.entity.MetaGroupPO;
import com.tan.jpa.errorcase.repository.GroupJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author tanjezh
 * @create 2024-08-24 16:07
 */
@Component
public class GroupManager {

    @Autowired
    private GroupJPARepository groupJPARepository;

    public GroupManager() {
        System.out.println("group manager init!");
    }

    public MetaGroupPO getOnlineGroup(String group, String profile) {
        return groupJPARepository.findByGroupAndProfileAndDeleted(group, profile, 0);
    }

    public Integer addGroup(String group, String profile, String desc) {
        MetaGroupPO jpa = new MetaGroupPO();
        jpa.setGroup(group);
        jpa.setDesc(desc);
        jpa.setProfile(profile);
        jpa.setDeleted(0);
        Timestamp timestamp = Timestamp.from(Instant.now());
        jpa.setCreateTime(timestamp);
        jpa.setUpdateTime(timestamp);
        MetaGroupPO res = groupJPARepository.save(jpa);
        return res.getId();
    }

    @Transactional
    public boolean updateGroup(Integer groupId, String desc) {
        return groupJPARepository.updateDesc(groupId, desc) > 0;
    }

    @Transactional
    public boolean deleteGroup(Integer groupId) {
        return groupJPARepository.logicDeleted(groupId) > 0;
    }

}
