package org.example;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this(name, 20);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "SerializablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
