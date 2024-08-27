package com.tan.mybatis.xml.demo;

import com.tan.mybatis.xml.entity.MoneyPo;
import com.tan.mybatis.xml.mapper.MoneyMapper;
import com.tan.mybatis.xml.mapper.MoneyMapperV2;
import com.tan.mybatis.xml.mapper.MoneyMapperV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author tanjezh
 * @create 2024-08-25 16:22
 */
@Repository
public class MoneyRepository {

    private Random random = new Random();

    @Autowired
    private MoneyMapper moneyMapper;
    @Autowired
    private MoneyMapperV2 moneyMapperV2;
    @Autowired
    private MoneyMapperV3 moneyMapperV3;

    /**
     * 用于测试，mybatis中 #{} 替换的参数类型，是否会转换为String类型
     */
    public void testArgumentReplace() {
        int name = 120;
        List list = moneyMapperV3.queryByName(name);
        System.out.println(list);

        list = moneyMapperV3.queryByNameV2(name);
        System.out.println(list);

        list = moneyMapperV3.queryByNameV3(String.valueOf(name));
        System.out.println(list);

        list = moneyMapperV3.queryByNameV4(name);
        System.out.println(list);
    }

    /**
     * 用于验证某些场景下，只能使用 ${}
     */
    public void groupBy() {
        List<MoneyPo> list = moneyMapperV2.groupBy("name");
        System.out.println(list);
    }

    /**
     * 测试枚举类型的case
     * <p>
     * 知识点：
     * 枚举类型，传入的参数，应该为String类型；如果是int，则表示根据枚举的下标索引来检索
     * - 如枚举字段 bank ('2', '3', '1')
     * - 传参 0 对应的是 '2'， 传参 '1' 对应的则是 '3'
     */
    public void testEnumQuery() {
        // 枚举类型的查询，虽然定义的是字符串，但是传参为数字时，会有问题
        List list = moneyMapperV2.queryByBank(1);
        System.out.println(list);

    }

    /**
     * 根据传递参数动态拼接SQL
     */
    public void testMulParameter(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tan");
        map.put("id", 1);
        moneyMapperV2.queryByCondition(map);
        moneyMapperV2.queryByConditionV2(map);
        moneyMapperV2.queryByConditionV3(map);
    }

    public void testBasic() {
        MoneyPo po = new MoneyPo();
        po.setName("mybatis user");
        po.setMoney((long) random.nextInt(12343));
        po.setIsDeleted(0);

        moneyMapper.savePo(po);
        System.out.println(po);
        MoneyPo out = moneyMapper.findById(po.getId());
        System.out.println("query:" + out);
        moneyMapper.addMoney(po.getId(), 100);
        System.out.println("after update:" + moneyMapper.findById(po.getId()));
        moneyMapper.delPo(po.getId());
        System.out.println("after del:" + moneyMapper.findById(po.getId()));
    }

    public void testMapper() {
        MoneyPo po = new MoneyPo();
        po.setName("mybatis user");
        po.setMoney((long) random.nextInt(12343));
        po.setIsDeleted(0);

        moneyMapper.savePo(po);
        System.out.println("add record: " + po);
        moneyMapper.addMoney(po.getId(), 200);
        System.out.println("after addMoney: " + moneyMapper.findByName(po.getName()));
        moneyMapper.delPo(po.getId());
        System.out.println("after delete: " + moneyMapper.findByName(po.getName()));
        Long id = moneyMapper.findIdById(100);
        System.out.println(id);

        // 批量保存，如果有不满足条件的可以忽略 --> 对于唯一键冲突，不做任何处理； 长度超限，截取
        List<MoneyPo> batchList = Arrays.asList(new MoneyPo("tt", 11L, 0),
                new MoneyPo("mybatis user", 12L, 0),
                new MoneyPo("hello world test this name is too long for sub one", 11L, 0),
                new MoneyPo("haha", 122L, 0));
        int ans = moneyMapper.batchSave(batchList);
        System.out.println(ans);
    }

    /**
     * 测试一级缓存
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void testFirstCache(int id) {
        dupQuery(id);
    }

    /**
     * 多次查询同一条记录，用来测试缓存
     *
     * @param id
     */
    public void dupQuery(int id) {
        MoneyPo po = moneyMapper.findById(id);
        System.out.println("\n>>>>>>>>>>>>" + po + " | " + System.identityHashCode(po) + "\n");

        po = moneyMapper.findById(id);
        System.out.println("\n>>>>>>>>>>>>" + po + " | " + System.identityHashCode(po) + "\n");

        po = moneyMapper.findById(id);
        System.out.println("\n>>>>>>>>>>>>" + po + " | " + System.identityHashCode(po) + "\n");
        System.out.println("\n\n<<<<<<<<< over >>>>>>>>>>>>>\n\n");
    }

    /**
     * 位查询(带特殊字符的查询语句)
     */
    public void testByteQuery() {
        // xml 使用 <![CDATA[  ]]> 内部可以填写原始的表达式，不需要额外的转移符
        // 其他的转移:
        // &lt; == < 小于号
        // &gt; == > 大于号
        // &amp; == & 与符号
        // &apos; == ' 单引号
        // &quot; == " 双引号
        List<Long> ids = moneyMapper.queryBitCondition(1);
        System.out.println(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "tt");
        List<MoneyPo> list = moneyMapper.findByIdOrCondition(1, map);
        System.out.println(list);

        list = moneyMapper.findByIdOrConditionV2(1, map);
        System.out.println(list);
    }

}
