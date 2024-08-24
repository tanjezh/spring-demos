package com.tan.jpa.errorcase2.strategy;

import lombok.Setter;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 关键字处理方式二
 * 对于数据库表中的字段是关键字的处理，添加``拼接字段
 *
 * @author tanjezh
 * @create 2024-08-24 23:34
 */
public class JpaNamingStrategyStandardImpl extends PhysicalNamingStrategyStandardImpl {

    private static final long serialVersionUID = 1L;

    @Setter
    private static int mode = 0;

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (mode == 1) {
            if (name.isQuoted()) {
                return name;
            } else {
                return Identifier.toIdentifier("`" + name.getText() + "`", true);
            }
        } else {
            return name;
        }
    }

}
