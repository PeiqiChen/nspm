package com.nspm.demo;

import java.util.List;
import java.util.Arrays;

import com.nspm.demo.entity.User;
import com.nspm.demo.mapper.UserMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


//    @Bean
//    CommandLineRunner demo(UserMapper userMapper) {
//        return args -> {
//            //
//        };
//
//    }

//    @Bean
//    CommandLineRunner demo(UserMapper userMapper) {
//        return args -> {
//
//            List<User> users = Arrays.asList(
//                    new User("001","foo",23),
//                    new User("002","bar",23),
//                    new User("003","baz",23),
//                    new User("004","qux",23),
//                    new User("005","quux",23),
//                    new User("006","corge",23),
//                    new User("007","grault",23),
//                    new User("008","garply",23),
//                    new User("009","waldo",23),
//                    new User("010","fred",23),
//                    new User("011","plugh",23),
//                    new User("011","xyzzy",23),
//                    new User("012","thud",23)
//
//            );
////            users.forEach(user -> {
////                userMapper.insert(user.id,user.name,user.age);
////                System.err.println("插入数据:" + user.toString());
////            });
//            System.err.println("-----------query all-----------------");
//            users.forEach(user -> {
//                User u=userMapper.findByName(user.name);
//                System.err.println(u.toString());
//            });
//
//        };
//
//    }

}
