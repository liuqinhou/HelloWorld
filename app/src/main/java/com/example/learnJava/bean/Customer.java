package com.example.learnJava.bean;

public class Customer {

    private String name;
    private Long id;
    private int age;

    public Customer() {}

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
