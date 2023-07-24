package ru.job4j.serialization.xml;

public class Phone {
    private final String phone;

    public Phone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phone {"
                + "phone='" + phone + '\''
                + '}';
    }
}
