package com.tan.db.log.mapper;

import com.tan.db.log.entity.MoneyPo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author tanjezh
 * @create 2024-08-17 18:43
 */
@Mapper
public interface MoneyMapper {

    /**
     * 保存数据，并保存主键id (注解方式自定义 sql)
     *
     * @param moneyPo
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "po.id", keyColumn = "id")
    @Insert("insert into money(name, money, is_deleted) values(#{po.name}, #{po.money}, #{po.isDeleted})")
    int save(@Param("po") MoneyPo moneyPo);

    /**
     * 更新
     *
     * @param id
     * @param money
     * @return
     */
    @Update("update money set money = #{money} where id = #{id}")
    int update(@Param("id") int id, @Param("money") long money);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Delete("delete from  money where id = #{id}")
    int delete(@Param("id") int id);

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    @Select("select * from money where id = #{id}")
    // 字段与类属性的映射关系
    @Results(id = "moneyResultMap", value = {
            @Result(property = "id", column = "id", id = true, jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "money", column = "money", jdbcType = JdbcType.INTEGER),
            @Result(property = "isDeleted", column = "is_deleted", jdbcType = JdbcType.TINYINT),
            @Result(property = "createAt", column = "create_at", jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updateAt", column = "update_at", jdbcType = JdbcType.TIMESTAMP)
    })
    MoneyPo getById(@Param("id") int id);

    /**
     * name 字段查询
     *
     * @param name
     * @return
     */
    @Select("select * from money where `name` = #{name}")
    @ResultMap(value = "moneyResultMap")
    MoneyPo getByName(@Param("name") String name);

    /**
     * foreach 查询
     *
     * @param ids
     * @return
     */
    @Select("<script> select * from money where id in" +
            "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<MoneyPo> getByIds(List<Integer> ids);

}
