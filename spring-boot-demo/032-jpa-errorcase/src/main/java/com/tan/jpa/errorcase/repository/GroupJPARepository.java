package com.tan.jpa.errorcase.repository;

import com.tan.jpa.errorcase.entity.MetaGroupPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024-08-24 21:57
 */
public interface GroupJPARepository extends JpaRepository<MetaGroupPO, Integer> {

    List<MetaGroupPO> findByProfile(String profile);

    MetaGroupPO findByGroupAndProfileAndDeleted(String group, String profile, Integer deleted);

    @Modifying
    @Query("update MetaGroupPO m set m.desc=?2 where m.id=?1")
    int updateDesc(int groupId, String desc);

    @Modifying
    @Query("update MetaGroupPO m set m.deleted=1 where m.id=?1")
    int logicDeleted(int groupId);

}
