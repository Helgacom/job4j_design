package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenExpiration20PercentThenStoreContainsFood() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 80);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -20);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        Store<Food> store = new Warehouse();
        store.add(food);
        assertThat(store.findAll()).contains(food);
    }

    @Test
    public void whenExpiration24PercentThenStoreContainsFood() {
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
        Store<Food> store = new Warehouse();
        store.add(food);
        assertThat(store.findAll()).contains(food);
    }

    @Test
    public void whenExpiration25PercentThenStoreIsEmpty() {
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
        Store<Food> store = new Warehouse();
        store.add(food);
        assertThat(store.findAll()).isEmpty();
    }

    @Test
    public void whenExpiration40PercentThenStoreIsEmpty() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 60);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -40);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        Store<Food> store = new Warehouse();
        store.add(food);
        assertThat(store.findAll()).isEmpty();
    }
}