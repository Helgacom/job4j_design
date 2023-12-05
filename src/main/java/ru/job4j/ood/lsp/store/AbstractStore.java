package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store<Food> {

    private final List<Food> foodList = new ArrayList<>();
    public static final int WAREHOUSE_FROM = 0;
    public static final int SHOP_FROM = 25;
    public static final int DISCOUNT_FROM = 75;
    public static final int TRASH_FROM = 100;

    @Override
    public List<Food> findAll() {
        return foodList;
    }

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public List<Food> findBy(Predicate<Food> predicate) {
        return findAll().stream().filter(predicate).collect(Collectors.toList());
    }

    protected boolean isExpired(int from, int to, Food food) {
        long expiration = food.getExpiryDate().getTime().getTime() - food.getCreateDate().getTime().getTime();
        long passed = System.currentTimeMillis() - food.getCreateDate().getTime().getTime();
        long percent = passed * 100 / expiration;
        return from <= percent && percent < to;
    }

    protected void removeAll(List<Food> removedFoods) {
        foodList.removeAll(removedFoods);
    }
}
