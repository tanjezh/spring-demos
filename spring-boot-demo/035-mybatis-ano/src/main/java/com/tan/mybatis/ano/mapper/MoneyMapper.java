package com.tan.mybatis.ano.mapper;

import com.tan.mybatis.ano.entity.MoneyPo;
import com.tan.mybatis.ano.handler.Timestamp2LongHandler;
import com.tan.mybatis.ano.service.MoneyService;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 使用注解的方式表示 sql 语句
 *
 * @author tanjezh
 * @create 2024-08-29 23:27
 */
public interface MoneyMapper {

    /**
     * 保存数据，并保存主键id
     *
     * @param po
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "po.id", keyColumn = "id")
    @Insert("insert into money (name, money, is_deleted) values (#{po.name}, #{po.money}, #{po.isDeleted})")
    int save(@Param("po") MoneyPo po);

    /**
     * 更新
     *
     * @param id    id
     * @param money 钱
     * @return int
     */
    @Update("update money set `money`=#{money} where id = #{id}")
    int update(@Param("id") int id, @Param("money") long money);

    /**
     * 删除数据
     *
     * @param id id
     * @return int
     */
    @Delete("delete from money where id = #{id}")
    int delete(@Param("id") int id);

    /**
     * 主键查询
     *
     * @param id id
     * @return {@link MoneyPo}
     */
    @Select("select * from money where id = #{id}")
    @Results(id = "moneyResultMap", value = {
            @Result(property = "id", column = "id", id = true, jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "money", column = "money", jdbcType = JdbcType.INTEGER),
            @Result(property = "isDeleted", column = "is_deleted", jdbcType = JdbcType.TINYINT),
            @Result(property = "createAt", column = "create_at", jdbcType = JdbcType.TIMESTAMP),
//            @Result(property = "updateAt", column = "update_at", jdbcType = JdbcType.TIMESTAMP)})
            @Result(property = "updateAt", column = "update_at", jdbcType = JdbcType.TIMESTAMP, typeHandler = Timestamp2LongHandler.class)})
    MoneyPo getById(@Param("id") int id);

    @Select("select * from money where `name` = #{name}")
    @ResultMap(value = "moneyResultMap")
    MoneyPo getByName(@Param("name") String name);

//    -------------------- 上面属于基础操作 -------------------

    /**
     * 注意 SelectProvider 区别于 @Select 在于它指定一个类来生成sql
     * 执行 MoneyService 的 getByIdSql 方法（返回 sql 语句）
     *
     * @param id
     * @return
     */
    @SelectProvider(type = MoneyService.class, method = "getByIdSql")
    @ResultMap(value = "moneyResultMap")
    MoneyPo getByIdForProvider(@Param("id") int id);

    @SelectProvider(type = MoneyService.class, method = "getByIdSqlV2")
    @ResultMap(value = "moneyResultMap")
    MoneyPo getByIdForProviderV2(@Param("id") int id);

    @SelectProvider(type = MoneyService.class, method = "getByIdSqlV3")
    @ResultMap(value = "moneyResultMap")
    MoneyPo getByIdForProviderV3(@Param("id") int id);


    // ------------- 更多的查询姿势

    /**
     * foreach 查询，通过 interceptor 过滤追踪 sql 执行情况时有问题，参数解析失败
     *
     * @param ids
     * @return
     */
    @Select("<script> select * from money where id in  " +
            "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach></script>")
    @ResultMap(value = "moneyResultMap") // 缺少结果集对象会出现 Timestamp 转 Long 类型异常
    List<MoneyPo> getByIds(@Param("ids") List<Integer> ids);

}
