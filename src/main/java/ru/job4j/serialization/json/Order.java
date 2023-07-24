package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private final String name;
    private final int price;
    private final String[] positions;
    private final Contact contact;
    private final boolean inWork;

    public Order(String name, int price, String[] positions, Contact contact, boolean inWork) {
        this.name = name;
        this.price = price;
        this.positions = positions;
        this.contact = contact;
        this.inWork = inWork;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isInWork() {
        return inWork;
    }

    public String[] getPositions() {
        return positions;
    }

    public Contact getContact() {
        return contact;
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
        /* Contact contact = new Contact("+7 (111) 111-11-11");
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
        System.out.println(orderMod); */
        final Order order = new Order("Black T.", 21000,
                new String[]{"printer HP1010", "paper A4-21"},
                new Contact("+7(924)111-111-11-11"), true);

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        List<String> list = new ArrayList<>();
        list.add("printer HP1010");
        list.add("paper A4-21");
        JSONArray jsonPositions = new JSONArray(list);

        //new Contact("+7(924)111-111-11-11"),
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", order.getName());
        jsonObject.put("price", order.getPrice());
        jsonObject.put("positions", jsonPositions);
        jsonObject.put("contact", jsonContact);
        jsonObject.put("inWork", order.isInWork());

        System.out.println(jsonObject);

        System.out.println(new JSONObject(order));
    }
}
