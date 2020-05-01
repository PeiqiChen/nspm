package com.nspm.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User {

    public String id;

    public String name;
    public Integer age;

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name +", age"+age+ "]";
    }
}
