Mapper接口与sql文件映射的四种姿势
===

- 默认：在resource资源目录下，xml文件的目录层级与Mapper接口的包层级完全一致，且xml文件名与mapper接口文件名也完全一致
    - 如mapper接口： `com.tan.mybatis.xml.mapper.MoneyMapper`
    - 对应的xml文件:  `com/tan/mybatis/xml/mapper/MoneyMapper.xml`
- springboot配置参数:
    - application.yml配置文件中，指定 `mybatis.mapper-locations=classpath:sqlmapper/*.xml`
- mybatis-config配置文件
    - 这种姿势常见于非SpringBoot项目集成mybatis，通常将mybatis的相关配置放在 `mybatis-config.xml` 文件中
    - 首先在配置文件中，指定加载参数 `mybatis.config-location=classpath:mybatis-config.xml`
    - 然后指定映射器 ` <mappers><mapper resource="sqlmapper/money-mapper.xml"/></mappers>`
- SqlSessionFactory指定
    - 直接在SqlSessionFactory中指定即可Mapper文件

```java
// 设置mybatis的xml所在位置，这里使用mybatis注解方式，没有配置xml文件
bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*.xml"));
```