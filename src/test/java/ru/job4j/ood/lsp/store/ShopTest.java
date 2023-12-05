package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void whenExpiration25PercentThenStoreContainsFood() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 75);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -25);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        Store<Food> store = new Shop();
        store.add(food);
        assertThat(store.findAll()).contains(food);
    }

    @Test
    public void whenExpiration75PercentThenStoreContainsFood() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 25);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -75);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        Store<Food> store = new Shop();
        store.add(food);
        assertThat(store.findAll()).contains(food);
    }

    @Test
    public void whenExpiration24PercentThenStoreIsEmpty() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 76);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -24);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        Store<Food> store = new Shop();
        store.add(food);
        assertThat(store.findAll()).isEmpty();
    }

    @Test
    public void whenExpiryThenStoreIsEmpty() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, -1);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -5);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        Store<Food> store = new Shop();
        store.add(food);
        assertThat(store.findAll()).isEmpty();
    }

    @Test
    public void whenExpiration75PercentThenStoreContainsFoodAndDiscountFood() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 25);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -75);
        Food food = new Food(
                "olive_oil",
                expiryDate,
                createDate,
                1000,
                30);
        Store<Food> store = new Shop();
        store.add(food);
        double price =  store.findAll().get(0).getPrice();
        assertThat(store.findAll()).contains(food);
        assertThat(price).isEqualTo(700);
    }

    @Test
    public void whenExpiration85PercentThenStoreContainsFoodAndDiscountFood() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 15);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -85);
        Food food = new Food(
                "olive_oil",
                expiryDate,
                createDate,
                1000,
                30);
        Store<Food> store = new Shop();
        store.add(food);
        double price =  store.findAll().get(0).getPrice();
        assertThat(store.findAll()).contains(food);
        assertThat(price).isEqualTo(700);
    }

    @Test
    public void whenExpiration50PercentThenStoreContainsFoodAndNotDiscountFood() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 50);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -50);
        Food food = new Food(
                "olive_oil",
                expiryDate,
                createDate,
                1000,
                30);
        Store<Food> store = new Shop();
        store.add(food);
        double price =  store.findAll().get(0).getPrice();
        assertThat(store.findAll()).contains(food);
        assertThat(price).isEqualTo(1000);
    }
}