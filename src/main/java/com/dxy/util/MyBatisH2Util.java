package com.dxy.util;

import com.dxy.data.User;
import com.dxy.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.tools.Server;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyBatisH2Util {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        startwebserver();
    }

    private static void startwebserver(){
        try {
            //http://localhost:8082/
            Server webServer = Server.createWebServer("-webAllowOthers").start();
            // webServer.stop();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
//            DataSource dataSource = new PooledDataSource(
//                    "org.h2.Driver",
//                    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:init.sql'",
//                    "sa",
//                    ""
//            );
            DataSource dataSource = new PooledDataSource(
                    "org.h2.Driver",
                    "jdbc:h2:mem:test;",
                    "sa",
                    ""
            );

            Environment environment = new Environment(
                    "development",
                    new JdbcTransactionFactory(),
                    dataSource
            );

            Configuration configuration = new Configuration(environment);
            // 添加映射器
            configuration.addMapper(UserMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
        return sqlSessionFactory;
    }

    public static void createTables() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            // 生成并执行创建表的SQL
            String createUserTableSQL = TableGenerator.generateCreateTableSQL(User.class);
            session.getConnection().createStatement().execute(createUserTableSQL);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}