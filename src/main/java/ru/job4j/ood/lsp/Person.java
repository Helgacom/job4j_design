package ru.job4j.ood.lsp;

public class Person {

    private String name;
    int age;

    public Person(String name, int age) {
        validate(name);
        this.name = name;
        this.age = age;
    }

    protected void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Empty line");
        }
        /*other checks*/
    }

    public boolean canBuy(int age) {
        if (age < 14) {
            throw new IllegalArgumentException("The person isn't old enough");
        }
        return true;
    }
}

