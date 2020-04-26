package com.javaAdvanced;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String name;
    int age;
    public User(String name, int age){
        this.name=name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("Z3",22);
        User z4 = new User("li4",25);
        AtomicReference<User> atomicReference = new AtomicReference<User> ();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, z4)+"\t"+atomicReference.toString());
        System.out.println(atomicReference.compareAndSet(z3, z4)+"\t"+atomicReference.toString());
    }
}
