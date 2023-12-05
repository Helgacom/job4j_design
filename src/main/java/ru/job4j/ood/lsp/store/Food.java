package ru.job4j.ood.lsp.store;

import java.util.Calendar;

public class Food {
    private String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private float price;
    private final float discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, float price, float discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }


}
