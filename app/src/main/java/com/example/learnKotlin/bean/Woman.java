package com.example.learnKotlin.bean;

public class Woman extends Person{

    public Woman(String name) {
        super(name);
        System.out.println("in current calss, this object is:" + this);
    }

    @Override
    public void eat(String food) {
        super.eat(food);
        System.out.println("in current calss, this object is:" + this + " eat food: " + food);
    }
}
