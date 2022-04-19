package com.example.learnKotlin.bean;

public class Person {
    public String name;

    public Person(String name) {
        this.name = name;
        System.out.println("in super calss, this object is:" + this);
        System.out.println("in super calss, this object's name is:" + this.name);
    }

    public void eat(String food) {
        System.out.println("in super calss, this object is:" + this + " eat food: " + food);
    }
}
