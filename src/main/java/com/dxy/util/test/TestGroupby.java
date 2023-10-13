package com.dxy.util.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TestGroupby {
    @Data
    public static class User {
        private Integer id;
        private Integer schoolId;
        private String userName;
        private String edu;
        private double price;
    }

    public static List<User> users = new ArrayList<>();

    static {
        TestGroupby.User u1 = new TestGroupby.User();
        u1.setId(1001);
        u1.setSchoolId(100);
        u1.setUserName("小1");
        u1.setEdu("001");
        u1.setPrice(0.01);

        TestGroupby.User u2 = new TestGroupby.User();
        u2.setId(1002);
        u2.setSchoolId(100);
        u2.setUserName("小2");
        u2.setEdu("002");
        u2.setPrice(0.20);

        TestGroupby.User u3 = new TestGroupby.User();
        u3.setId(2010);
        u3.setSchoolId(200);
        u3.setUserName("小3");
        u3.setEdu("001");
        u3.setPrice(3.00);

        TestGroupby.User u4 = new TestGroupby.User();
        u4.setId(3001);
        u4.setSchoolId(300);
        u4.setEdu("001");
        u4.setPrice(40.0);

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    //需要一个参数：按照该参数进行分组。结果返回一个Map集合，每个Map的key默认是分组参数的类型，value是一个List集合。
    public static void test1() {
        Map<String, List<User>> collect = users.stream().collect(Collectors.groupingBy(User::getEdu));
        String s = "";
    }

    //可以对结果进行映射
    public static void test2() {
        Map<String, List<Integer>> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //第二个参数对Map的value进行处理（映射）
                Collectors.mapping(User::getId, Collectors.toList())));
        String s = "";
    }

    //可以对结果进行求和
    public static void test3() {
        Map<String, Double> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //对参数进行累计求和
                Collectors.summingDouble(User::getPrice)));
        System.out.println(collect);
        String s = "";
    }

    //对结果的统计
    public static void test4() {
        Map<String, Long> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //获取count数量
                Collectors.counting()));
        System.out.println(collect);
        String s = "";
    }

    //需要三个参数，第三个参数添加了对结果Map的生成方式，默认是HashMap
    public static void test5() {
        Map<String, Double> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //决定map的生成方式，使用TreeMap
                TreeMap::new,
                //对参数进行累计求和
                Collectors.summingDouble(User::getPrice)));
        System.out.println(collect);
        String s = "";
    }

}
