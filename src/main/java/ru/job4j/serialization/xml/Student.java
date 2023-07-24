package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Student {
    private final String name;
    private final int age;
    private final boolean sex;
    private final Phone phone;
    private final String[] subjects;

    public Student(String name, int age, boolean sex, Phone phone, String[] subjects) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name
                + '\'' + ", age=" + age
                + ", sex=" + sex
                + ", phone=" + phone
                + ", subjects=" + Arrays.toString(subjects)
                + '}';
    }

    public static void main(String[] args) {
        String[] subjects = new String[]{"history", "literature", "english"};
        Student student = new Student("Ivanov Ivan", 20, true, new Phone("+7 (111) 111-11-11"), subjects);
        System.out.println(student);
    }
}
