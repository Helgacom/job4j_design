package ru.job4j.ood.dip;

/* В этом классе нарушается DIP принцип, т.к в конструкторе для высокоуровнего класса Car мы зависим от
реализации низкоуровнего класса Engine */
class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine();
    }

    public void move() {
        engine.start();
    }
}

class Engine {
    public void start() {
        System.out.println("Car started moving");
    }
}
