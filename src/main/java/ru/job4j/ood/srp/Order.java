package ru.job4j.ood.srp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    List<Product> list;
    int totalPrice;
    String customerName;

    public Order(List<Product> list, int totalPrice, String customerName) {
        this.list = list;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
    }

    /*Данный класс отвечает и за создание заказа, и за сохранение его в файл, и за конвертацию стоимости продукта,
    * чем нарушает принцип единственной ответственности*/

    public void add(Product product) {
        list.add(product);
        totalPrice += product.price;
    }

    public void saveOrderToFile(File file, Order order) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Product {
        String name;
        int price;

        Product(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public int converter(Product product, int exchangeRate) {
            return exchangeRate / product.price;
        }
    }

    public static void main(String[] args) {
        var order = new Order(new ArrayList<>(), 0, "Ivanov_I");
        var product1 = new Product("milk", 100);
        var product2 = new Product("bread", 80);
        var product3 = new Product("butter", 210);
        order.add(product1);
        order.add(product2);
        order.add(product3);
        System.out.println(order.totalPrice);
        order.saveOrderToFile(new File(""), order);
    }
}
