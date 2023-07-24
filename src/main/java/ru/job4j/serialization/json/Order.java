package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Order {
    private final String name;
    private final int price;
    private final String[] positions;
    private Contact contact;
    private final boolean inWork;

    public Order(String name, int price, String[] positions, Contact contact, boolean inWork) {
        this.name = name;
        this.price = price;
        this.positions = positions;
        this.contact = contact;
        this.inWork = inWork;
    }

    @Override
    public String toString() {
        return "Order{"
                + "name='"
                + name + '\''
                + ", price=" + price
                + ", positions=" + Arrays.toString(positions)
                + ", contact=" + contact
                + ", inWork=" + inWork
                + '}';
    }

    public static void main(String[] args) {
        Contact contact = new Contact("+7 (111) 111-11-11");
        String[] positions = new String[]{"printer HP1010", "paper A4-21"};
        Order order = new Order("Black T.", 21000, positions, contact, true);
        System.out.println(order);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(order));

        final String orderJson =
                "{"
                        + "\"name\":\"Black T.\","
                        + "\"price\":21000,"
                        + "\"positions\":"
                        + "[\"printer HP1010\",\"paper A4-21\"],"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7 (111) 111-11-11\""
                        + "},"
                        + "\"inWork\":true"
                        + "}";
        final Order orderMod = gson.fromJson(orderJson, Order.class);
        System.out.println(orderMod);
    }
}
