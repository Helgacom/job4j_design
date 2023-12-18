package ru.job4j.ood.dip;

/* Объявив Keyboard и Monitor с помощью ключевого слова new, мы тесно связали
эти три класса вместе. Конструктор класса компьютер зависит от конкретных реализаций классов Keyboard и Monitor,
а не от абстракций, это нарушение DIP */
public class Computer {

    private final Keyboard keyboard;
    private final Monitor monitor;

    public Computer() {
        monitor = new Monitor();
        keyboard = new Keyboard();
    }
}

class Monitor {
}

class Keyboard {
}
