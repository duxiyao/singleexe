package com.dxy;

import com.dxy.data.User;
import com.dxy.mapper.UserMapper;
import com.dxy.util.FileHelper;
import com.dxy.util.MyBatisH2Util;
import com.dxy.util.VersionCtlUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.tools.Server;

import javax.sql.DataSource;


public class WorkerMain {
//    static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
//            60L, TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>());

    private static void test1(){
        // 初始化MyBatis和H2
//        MyBatisH2Util.getSqlSessionFactory();

        // 自动创建表
        MyBatisH2Util.createTables();

        // 使用MyBatis操作数据库
        try (SqlSession session = MyBatisH2Util.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            // 插入用户
            User user = new User();
            user.set名字("john_doe");
            user.setEmail("john@example.com");
            user.setCreateTime(new Date());

            userMapper.insert(user);
            session.commit();

            // 查询用户
            User retrievedUser = userMapper.selectById(1L);
            System.out.println("Retrieved user: " + retrievedUser.get名字());
        }
    }
    private static void test(){

        try {
            //http://localhost:8082/
            Server webServer = Server.createWebServer("-webAllowOthers").start();
            // webServer.stop();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

// 1. 配置 H2 数据源 (使用内存数据库)
//        DataSource dataSource = new PooledDataSource(
//                "org.h2.Driver",
//                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", // DB_CLOSE_DELAY=-1 让内存数据库在连接关闭后也不销毁
//                "sa",
//                ""
//        );
        DataSource dataSource = new PooledDataSource(
                "org.h2.Driver",
                "jdbc:h2:mem:test;", // DB_CLOSE_DELAY=-1 让内存数据库在连接关闭后也不销毁
                "sa",
                "123"
        );

        // 2. 配置事务工厂
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // 3. 创建数据库环境
        Environment environment = new Environment("development", transactionFactory, dataSource);

        // 4. 创建 MyBatis 配置对象，并添加环境设置和映射器
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class); // 添加你的 Mapper 接口

        // 5. 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 6. 获取 SqlSession 并执行数据库操作
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 获取 Mapper 接口的代理对象
            UserMapper userMapper = session.getMapper(UserMapper.class);

            // 创建表 (可选，通常通过初始化脚本完成)
            session.getConnection().createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(255), " +
                            "email VARCHAR(255))"
            );

            // 插入数据
            User newUser = new User();
            newUser.set名字("John Doe");
            newUser.setEmail("john.doe@example.com");
            userMapper.insertUser(newUser);
            session.commit(); // 提交事务
            System.out.println("插入用户成功，ID: " + newUser.getId());

            // 查询数据
            User user = userMapper.selectUser(newUser.getId());
            if (user != null) {
                System.out.println("查询到的用户: " + user.get名字() + ", Email: " + user.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        test1();
        Scanner scan = null;
        try {
            try {
                VersionCtlUtil.test("1");
                VersionCtlUtil.test("2");
                VersionCtlUtil.test("3");
                VersionCtlUtil.test0("6");

                File workspace = new File(System.getProperty("user.dir"), "WK5");
                if (!workspace.exists()) {
                    workspace.mkdirs();
                }

                workspace = new File(System.getProperty("user.dir"), "workspace4");
                FileHelper.deleteDir(workspace.getAbsolutePath());
                if (!workspace.exists()) {
                    workspace.mkdirs();
                }
                VersionCtlUtil.up("");
            } catch (Exception e) {
            }

            scan = new Scanner(System.in);
            boolean flag = true;
            while (flag) {
                System.out.println();
                System.out.println("请将对应要处理的数据文件，放入对应的workerspace1 或 workerspace2 中.");
                System.out.println("请选择要执行的功能，并敲回车确定：");
                System.out.println("1：处理【多多店铺商品资料】");
                System.out.println("2：处理【销售报表】");
                System.out.println("3：处理【销售报表加上推广数据】");
                System.out.println("4：处理【标准推广-关键词-添加关键词列表】");
                System.out.println("5：处理【弹窗比价】");
                System.out.println("6：处理【图片】");
                System.out.println("q：退出程序");
                System.out.println("请输入：");
                if (scan.hasNext()) {
                    String s = scan.next();

                    if ("1".equals(s)) {
                        VersionCtlUtil.test("1");
                        Tasks.exe1();
                        System.err.println("1：【多多店铺商品资料】 处理完成");
                    }
                    if ("2".equals(s)) {
                        VersionCtlUtil.test("2");
                        Tasks.exe2();
                        System.err.println("2：【销售报表】 处理完成");
                    }
                    if ("3".equals(s)) {
                        VersionCtlUtil.test("3");
                        Tasks.exe3();
                        System.err.println("3：【销售报表加上推广数据】 处理完成");
                    }
                    if ("4".equals(s)) {
                        Tasks.exe4();
                        System.err.println("4：【标准推广-关键词-添加关键词列表】 处理完成");
                    }
                    if ("5".equals(s)) {
                        Tasks.exe5();
                        System.err.println("5：【弹窗比价】 处理完成");
                    }
                    if ("6".equals(s)) {
                        Tasks.exe6();
                        System.err.println("5：【图片】 处理完成");
                    }
                    if ("q".equals(s)) {
                        flag = false;
                        Tasks.E.shutdownNow();
                    }

                    System.err.println();
                    System.err.println();
                    System.err.println();
                    System.err.println("电脑要爆炸了！！！！！！！");
                    long millis = 500;
                    Thread.sleep(millis);
                    System.out.print(3);
                    Thread.sleep(millis);
                    System.out.print(2);
                    Thread.sleep(millis);
                    System.out.print(1);
                    Thread.sleep(millis);
                    System.out.println("逗你玩~");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scan != null) {
                scan.close();
            }
        }

    }
}
