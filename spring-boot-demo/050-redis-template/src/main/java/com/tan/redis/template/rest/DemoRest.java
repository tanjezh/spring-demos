package com.tan.redis.template.rest;

import com.alibaba.fastjson2.JSONObject;
import com.tan.redis.template.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author tanjezh
 * @create 2024-09-22 16:02
 */
@RestController
@RequestMapping(path = "rest")
public class DemoRest {

    @Autowired
    private KVTest kvTest;

    @GetMapping(path = "show")
    public String showKv(String key) {
        Map<String, String> result = new HashMap<>(16);

        // kv test
        String kvKey = "kvKey";
        String kvVal = UUID.randomUUID().toString();
        kvTest.setValue(kvKey, kvVal);
        String kvRes = new String(kvTest.getValue(kvKey));
        result.put("kv get set", kvRes + "==>" + kvVal.equals(kvRes));


        // kv getSet

        // 如果原始数据存在时，返回旧的结果并更新数据
        String kvOldRes = new String(kvTest.setAndGetOldValue(kvKey, kvVal + "==>new"));
        result.put("kv setAndGet", kvOldRes + " # " + new String(kvTest.getValue(kvKey)));

        // 如果原始数据不存在时，返回 null
        byte[] kvOldResNull = kvTest.setAndGetOldValue("not exists", "...");
        result.put("kv setAndGet not exists", kvOldResNull == null ? "null" : new String(kvOldResNull));


        // 自增
        String cntKey = "kvIncrKey";
        long val = 10L;
        long incrRet = kvTest.incr(cntKey, val);
        String incrRes = new String(kvTest.getValue(cntKey));
        result.put("kv incr", incrRet + "#" + incrRes);


        // bitmap 测试
        String bitMapKey = "bitmapKey";
        kvTest.setBit(bitMapKey, 100, true);
        boolean bitRes = kvTest.getBit(bitMapKey, 100);
        boolean bitRes2 = kvTest.getBit(bitMapKey, 101);
        result.put("bitMap", bitRes + ">> true | " + bitRes2 + ">> false");
        return JSONObject.toJSONString(result);
    }

    @Autowired
    private ListTest listTest;

    @GetMapping(path = "list")
    public String showList() {
        Map<String, String> result = new HashMap<>(16);
        String key = "listKey";
        // 删除之前的缓存，避免影响测试数据
        listTest.delete(key);

        // 新增一个数据
        listTest.lpush(key, "hello");

        // 获取列表中的所有值
        List<String> redisVal = listTest.range(key, 0, -1);
        result.put("list", redisVal.toString());


        String indexVal = listTest.index(key, 0);
        result.put("index", indexVal + " == hello");


        listTest.lpush(key, "12");
        listTest.lpush(key, "23");
        listTest.lpush(key, "45");
        listTest.lpush(key, "67");
        listTest.trim(key, 0, 3);
        redisVal = listTest.range(key, 0, -1);
        result.put("trim", redisVal.toString());


        String pop = listTest.lpop(key);
        result.put("pop", pop + " == 67");
        result.put("size", listTest.size(key) + "==3");


        listTest.set(key, 0, "aaa");
        result.put("afterSet", listTest.index(key, 0) + "==aaa");
        return JSONObject.toJSONString(result);
    }

    @Autowired
    private HashTest hashTest;

    @GetMapping("hash")
    public String showHash(){
        Map<String, String> result = new HashMap<>(16);
        hashTest.hdel("food", "fruit");
        hashTest.hset("food", "fruit", "apple");
        hashTest.hset("food", "fruit", "banana");

        hashTest.hset("food", "animal", "chicken");
        hashTest.hset("food", "burger", "hamburger");

        String fruitVal = hashTest.hget("food", "fruit");
        result.put("redisHash data fruit", fruitVal);


        // 获取 hash 中的多个字段
        Map<String, String> fieldMap = hashTest.hmget("food", Arrays.asList("fruit", "animal"));
        System.out.println(fieldMap);

        // 自增
        long initVal = hashTest.hincr("food", "number", 5);
        result.put("num 5 ", String.valueOf(initVal));
        long incrVal = hashTest.hincr("food", "number", 0);
        result.put("num 5 + 0 ", String.valueOf(incrVal));

        // 设置数组
        hashTest.hSetList("food", "vegetable", Arrays.asList("potato","tomato","spinach"));
        List<String> lists = hashTest.hGetList("food", "vegetable", String.class);
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < lists.size(); i++){
            builder.append(lists.get(i));
            builder.append(",");
        }
        String str = builder.toString();
        if(str.endsWith(",")){
            str = str.substring(0,str.length()-1);
        }
        result.put("vegetable list",str);

        System.out.println(result);

        // 获取所有数据
        Map<String, String> foodMap = hashTest.hgetall("food");
        return JSONObject.toJSONString(foodMap);
    }

    @Autowired
    private SetTest setTest;

    @GetMapping("set")
    public String showSet(){
        Map<String, String> result = new HashMap<>(16);
        setTest.add("person","LiBai");
        setTest.add("person","BaiJuYi");

        setTest.add("giant","Kongzi");
        setTest.add("giant","Laozi");
        setTest.add("giant","BaiJuYi");

        boolean containVal = setTest.contains("person", "LiBai");
        result.put("LiBai is exist", String.valueOf(containVal));
        System.out.println(result);

        setTest.remove("person","LiBai");
        Set<String> personSet = setTest.values("person");
        System.out.println(personSet);

        Set<String> unionSet = setTest.union("person", "giant");
        Set<String> intersect = setTest.intersect("person", "giant");
        System.out.println(intersect);
        Set<String> diff = setTest.diff("person", "giant");
        System.out.println(diff);

        return JSONObject.toJSONString(unionSet);
    }

    @Autowired
    private ZSetTest zSetTest;

    @GetMapping("zset")
    public String showZSet(){
        Map<String, String> result = new HashMap<>(16);
        zSetTest.add("subject","Chinese",90);
        zSetTest.add("subject","Math",99);
        zSetTest.add("subject","English",95);

        Double incrScore = zSetTest.incrScore("subject", "Math", 1);
        result.put("Math incr 1",String.valueOf(incrScore));

        Double mathScore = zSetTest.score("subject", "Math");
        result.put("Math score is",String.valueOf(mathScore));

        // 返回排名，从 0 开始
        Long rank = zSetTest.rank("subject", "Chinese");
        result.put("Chinese rank is",String.valueOf(rank));

        Set<String> subjectSeq = zSetTest.range("subject", 0, -1);
        System.out.println(subjectSeq);

        Set<ZSetOperations.TypedTuple<String>> subjectScoreSeq = zSetTest.rangeWithScore("subject", 0, -1);
        System.out.println(subjectScoreSeq);

        // 返回 zset 集合的数据，反向排序
        Set<String> revSet = zSetTest.revRange("subject", 0, -1);
        System.out.println("reverse is: " + revSet);

        // score 在 min 和 max 中的数据
        Set<String> setBetween = zSetTest.sortRange("subject", 96, 100);
        System.out.println("between data is: " + setBetween);

        Long size = zSetTest.size("subject");
        result.put("subject size is: ", String.valueOf(size));
//        zSetTest.remove("subject","Chinese");

        return JSONObject.toJSONString(result);
    }



}
