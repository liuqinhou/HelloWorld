package com.example.helloworld;

import com.example.learnJava.bean.Customer;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyUnitTest {

    @Test
    public void testReflect() {
        Customer customer = new Customer();
        customer.setId(10L);
        customer.setAge(10);
        customer.setName("liuqinhou");
        System.out.println(customer);
        System.out.println("hello custom");
        try {
            copy(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class customclass = Class.forName("com.example.learnJava.bean.Customer");
            customclass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    public Object copy(Object object) throws Exception {
        Class classType = object.getClass();
        System.out.println("Class:" + classType.getName());
        Object objectCopy = classType.getConstructor().newInstance();
        Object objectCopy2 = classType.getConstructor().newInstance();
        Field[] fields = classType.getDeclaredFields();

        for (int i = 0; i < 1; i++) {
            Field field = fields[i];
            String fieldName = field.getName();

            Class [] arrayClass = new Class[] {String.class, Customer.class};
            Customer [] customers1 = new Customer[]{new Customer(), new Customer()};
            Method getName = classType.getDeclaredMethod("getName");
            Method setName = classType.getDeclaredMethod("setName", field.getType());



            Object value = getName.invoke(object);
            System.out.println("value = " + value);

            setName.invoke(objectCopy, "luoxiuyun");
            Object value1 = getName.invoke(objectCopy);
            System.out.println("value = " + value1);
        }
        return objectCopy;
    }
}
