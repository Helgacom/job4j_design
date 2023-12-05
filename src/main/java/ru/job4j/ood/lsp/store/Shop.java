package ru.job4j.ood.lsp.store;

import java.util.List;

public class Shop extends AbstractStore {

    @Override
    public void add(Food food) {
        if (isExpired(DISCOUNT_FROM, TRASH_FROM, food)) {
            food.setPrice(getDiscountPrice(food));
        }
        if (isExpired(SHOP_FROM, TRASH_FROM, food)) {
            super.add(food);
        }
    }

    @Override
    public List<Food> clear() {
        findAll().stream()
                .filter(food -> isExpired(DISCOUNT_FROM, TRASH_FROM, food))
                .forEach((food -> food.setPrice(getDiscountPrice(food))));
        List<Food> list = findAll().stream()
                .filter(food -> !isExpired(SHOP_FROM, TRASH_FROM, food))
                .toList();
        super.removeAll(list);
        return list;
    }

    private float getDiscountPrice(Food food) {
        return food.getPrice() - food.getPrice() * food.getDiscount() / 100;
    }
}
