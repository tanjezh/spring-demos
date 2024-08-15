package com.tan.spel.demo;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author tanjezh
 * @create 2024-08-15 10:55
 */
@Component
public class BasicSpelDemo implements ApplicationContextAware {

    private SpelExpressionParser parser = new SpelExpressionParser();

    public void test() {
        literaExpression();
        list();
        map();
        array();
        expression();
        type();
        construct();
        variable();
        function();
        bean();
        ifThenElse();
        elvis();
        safeOperate();
        collectionSelection();
        collectionProjection();
        template();
    }

    /**
     * 解析基本类型
     */
    public void literaExpression(){
        // evals to "Hello World"
        String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
        System.out.println("str: " + helloWorld);

        // double 类型
        double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
        System.out.println("double: " + avogadrosNumber);

        // evals to 2147483647
        int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
        System.out.println("int: " + maxValue);

        boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
        System.out.println("bool: " + trueValue);

        Object nullValue = parser.parseExpression("null").getValue();
        System.out.println("null: " + nullValue);
    }

    /**
     * 数组类型
     */
    public void list(){
        List list = (List) parser.parseExpression("{1,2,3,4}").getValue();
        System.out.println("list: " + list);

        List listOfList = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue();
        System.out.println("List<List> : " + listOfList);
    }

    /**
     * map类型
     */
    private void map(){
        Map map = (Map) parser.parseExpression("{'txt':'Nikola','dob':'10-July-1856'}").getValue();
        System.out.println("map: " + map);
        Map mapOfMaps =
                (Map) parser.parseExpression("{'txt':{'first':'Nikola','last':'Tesla'},'dob':{'day':10,'month':'July','year':1856}}")
                        .getValue();
        System.out.println("Map<Map>: " + mapOfMaps);
    }

    /**
     * 数组类型
     */
    private void array(){
        int[] arr = (int[]) parser.parseExpression("new int[4]").getValue();
        System.out.println("array:" + JSONObject.toJSONString(arr));

        int[] arr2 = (int[]) parser.parseExpression("new int[]{1,2,3,4}").getValue();
        System.out.println("array:" + JSONObject.toJSONString(arr2));

        int[][] arr3 = (int[][]) parser.parseExpression("new int[4][5]").getValue();
        System.out.println("multi array: " + JSONObject.toJSONString(arr3));

        int[] num = new int[]{1,3,5};
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("num",num);

        Integer value = parser.parseExpression("#num[1]").getValue(context,Integer.class);
        System.out.println("num value in arr: " + value);

    }

    /**
     * 表达式
     */
    public void expression(){
        // 运算
        System.out.println("1+2 = "+parser.parseExpression("1+2").getValue());

        // 比较
        System.out.println("1<2 = "+parser.parseExpression("1<2").getValue());

        System.out.println("true ? hello : false > " + parser.parseExpression("3 > 2 ? 'hello' : 'false'").getValue());

        // instanceof 判断，请注意静态类，用T进行包装
        System.out.println("instanceof String: "+parser.parseExpression("'abc' instanceof T(String)").getValue());

        // 正则表达式
        System.out.println("22 是否为两位数字: "+parser.parseExpression("'22' matches '\\d{2}'").getValue());
    }

    /**
     * 静态类
     */
    public static class StaClz {
        public static String txt = "静态属性";

        public static String hello(String tag) {
            return txt + " : " + tag;
        }
    }

    public void type(){
        String clz = parser.parseExpression("T(com.tan.spel.demo.BasicSpelDemo.StaClz).txt")
                .getValue(String.class);
        System.out.println("txt：" + clz);

        String methodReturn = parser.parseExpression("T(com.tan.spel.demo.BasicSpelDemo.StaClz).hello('tag')")
                .getValue(String.class);
        System.out.println("静态类方法：" + methodReturn);

        // class类获取
        Class aClass = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println("静态类：" + aClass.getName());
    }

    public static class Person {
        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }

    public void construct(){
        Person person = parser.parseExpression("new com.tan.spel.demo.BasicSpelDemo.Person('p1',20)")
                .getValue(Person.class);
        System.out.println("person: " + person);
    }

    public void variable(){
        Person person = new Person("p2", 22);
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("person", person);

        String personName = parser.parseExpression("#person.getName()")
                .getValue(context, String.class);
        System.out.println("variable name: " + personName);

        Integer age = parser.parseExpression("#person.age").getValue(context, Integer.class);
        System.out.println("variable age: " + age);
    }

    public void function(){
        try {
            EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
            // 注册一个方法变量，参数为method类型
            context.setVariable("hello", StaClz.class.getDeclaredMethod("hello", String.class));

            String result = parser.parseExpression("#hello('test hello method')")
                    .getValue(context, String.class);
            System.out.println("function call: " + result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void bean(){
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));

        // 获取bean对象
        BeanDemo obj = parser.parseExpression("@beanDemo").getValue(context, BeanDemo.class);
        System.out.println("beanDemo: " + obj);

        // 访问bean方法
        String result = parser.parseExpression("@beanDemo.hello('test beanDemo method')")
                .getValue(context, String.class);
        System.out.println("bean method return: " + result);
    }

    public void ifThenElse(){
        // 三元表达式
        String ans = parser.parseExpression("true ? '正确': '错误'").getValue(String.class);
        System.out.println("ifTheElse: " + ans);
    }

    public void elvis(){
        // xx != null ? xx : yy => xx?:yy
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", null);
        String name = parser.parseExpression("#name?:'Unknown'").getValue(context, String.class);
        System.out.println("elvis-before name is null: " + name);

        context.setVariable("name", "Exists!");
        name = parser.parseExpression("#name?:'Unknown'").getValue(context, String.class);
        System.out.println("elvis-after name is Exists: " + name);
    }

    public void safeOperate() {
        // 防 nullpointexception写法, xx == null ? null : xx.yy  => xx?.yy
        Person person = new Person(null, 18);

        String name = parser.parseExpression("name?.length()").getValue(person, String.class);
        System.out.println("safeOperate-before: " + name);

        person.name = "p3";
        name = parser.parseExpression("name?.length()").getValue(person, String.class);
        System.out.println("safeOperate-after: " + name);
    }

    public void collectionSelection() {
        // 容器截取，返回满足条件的子集
        // xx.?[expression] , 将满足expression的子元素保留，返回一个新的集合，类似容器的 filter
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4, 6, 7, 8, 9));

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("list", list);
        // 用 #this 来指代列表中的迭代元素
        List<Integer> subList = (List<Integer>) parser.parseExpression("#list.?[#this>5]").getValue(context);
        System.out.println("subList: " + subList);


        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 10);
        map.put("c", 4);
        map.put("d", 7);
        context.setVariable("map", map);
        // 表达式内部用key, value 来指代map的k,v
        Map subMap = parser.parseExpression("#map.?[value < 5]").getValue(context, Map.class);
        System.out.println("subMap: " + subMap);

        subMap = parser.parseExpression("#map.?[key == 'a']").getValue(context, Map.class);
        System.out.println("subMap: " + subMap);
    }

    public void collectionProjection() {
        // 容器操作之后，生成另一个容器, 类似lambda中的map方法
        // xx.![expression]

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4, 6, 7, 8, 9));
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("list", list);

        // 用 #this 来指代列表中的迭代元素
        List newList = parser.parseExpression("#list.![#this * 2]").getValue(context, List.class);
        System.out.println("newList: " + newList);


        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 10);
        map.put("c", 4);
        map.put("d", 7);
        context.setVariable("map", map);
        List newListByMap = parser.parseExpression("#map.![value * 2]").getValue(context, List.class);
        System.out.println("newListByMap: " + newListByMap);
    }

    public void template() {
        // 模板，混合字面文本与表达式，使用 #{} 将表达式包裹起来
        String randomPhrase = parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                ParserContext.TEMPLATE_EXPRESSION).getValue(String.class);
        System.out.println("template: " + randomPhrase);

    }

}
