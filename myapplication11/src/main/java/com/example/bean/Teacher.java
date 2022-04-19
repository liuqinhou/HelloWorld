package com.example.bean;


import com.example.annotationlib2.annotation.GenerateInterface;

@GenerateInterface
public class Teacher {

    private void teach() {
        System.out.println("teach...");
    }

    public void walk() {
        System.out.println("walk...");
    }
}
