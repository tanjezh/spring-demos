package com.tan.mybatis.plus.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @author tanjezh
 * @create 2024-09-01 22:52
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + "/spring-boot-demo/038-mybatis-plus-generator";
//        System.out.println(projectPath);
        String url = "jdbc:mysql://127.0.0.1:3306/story?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        String codePath = "/src/main/java";
        String resourcePath = "/src/main/resources/mapper";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("tan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(projectPath + codePath); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                // 不额外指定模块，如果指定为 test，则生成的xml会在 mapper/test/ 目录下
                .packageConfig(builder ->
                        builder.parent("com.tan.mybatis.plus.generator") // 设置父包名
                                .moduleName("mapper") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + resourcePath)) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("userT0", "story_t0") // 设置需要生成的表名
//                                .addTablePrefix("t_", "c_") // 设置过滤表前缀

                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
