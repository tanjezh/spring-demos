package com.tan.jpatest.repository;

import com.tan.jpatest.entity.MoneyPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author tanjezh
 * @create 2024-08-24 15:43
 */
public interface MoneyUpdateRepository extends JpaRepository<MoneyPO, Integer> {

    /**
     * 根据金钱来修改状态
     * ?1 表示第一个参数， ?2 表示第二个参数
     * @Modifying 注解 表示该方法需要进行数据库的更新或删除操作，从而确保操作的准确性和可靠性。
     *
     * @param money
     * @param state
     */
    @Modifying
    @Query("update MoneyPO m set m.isDeleted=?2 where m.money=?1")
    void updateStateByMoney(Long money, Byte state);

    /**
     * 表达式计算
     *
     * @param id
     * @param money
     */
    @Modifying
    @Query("update MoneyPO m set m.money=m.money + ?2 where m.id=?1")
    void addMoneyById(Integer id, Long money);

}
