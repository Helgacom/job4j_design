package ru.job4j.ood.isp;

public interface Animal {

    void eat();

    void fly();

    void jump();

    void run();
}

/*в данном случае интерфейс содержит методы, не являющиеся универсальными для всех видов животных, что приводит
к необходимости классам животных реализовывать методы, которые им не нужны, это является нарушением
принципа разделения интерфейсов*/
class Fish implements Animal {
    @Override
    public void eat() {
    }

    @Override
    public void fly() {
    }

    @Override
    public void jump() {
    }

    @Override
    public void run() {
    }
}

class Snake implements Animal {
    @Override
    public void eat() {
    }

    @Override
    public void fly() {
    }

    @Override
    public void jump() {
    }

    @Override
    public void run() {
    }
}

class Bird implements Animal {
    @Override
    public void eat() {
    }

    @Override
    public void fly() {
    }

    @Override
    public void jump() {
    }

    @Override
    public void run() {
    }
}

