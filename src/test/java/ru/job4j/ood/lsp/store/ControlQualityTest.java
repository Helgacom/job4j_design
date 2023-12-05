package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenFoodExpiration10PercentThenFoodGoesToWarehouseStore() {
        ControlQuality controlQuality = new ControlQuality();
        Store<Food> store = new Warehouse();
        controlQuality.addStore(store);
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 90);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -10);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        controlQuality.addFood(food);
        List<Food> foods = controlQuality.getStoreList().get(0).findAll();
        assertThat(foods).contains(food);
    }

    @Test
    public void whenRefreshStoresThenFoodRemovedFromWarehouseStore() {
        ControlQuality controlQuality = new ControlQuality();
        Store<Food> store = new Warehouse();
        controlQuality.addStore(store);
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
        controlQuality.addFood(food);
        expiryDate.add(Calendar.DAY_OF_MONTH, -5);
        controlQuality.refreshStores();
        List<Food> foods = controlQuality.getStoreList().get(0).findAll();
        assertThat(foods).isEmpty();
    }

    @Test
    public void whenRefreshStoresThenFoodMovedFromWarehouseToTrashStore() {
        ControlQuality controlQuality = new ControlQuality();
        Store<Food> store1 = new Warehouse();
        Store<Food> store2 = new Trash();
        controlQuality.addStore(store1);
        controlQuality.addStore(store2);
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 8);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -2);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                15);
        controlQuality.addFood(food);
        List<Food> warehouseFoods = controlQuality.getStoreList().get(0).findAll();
        List<Food> trashFoods = controlQuality.getStoreList().get(1).findAll();
        assertThat(warehouseFoods).contains(food);
        assertThat(trashFoods).isEmpty();
        expiryDate.add(Calendar.DAY_OF_MONTH, -9);
        controlQuality.refreshStores();
        assertThat(warehouseFoods).isEmpty();
        assertThat(trashFoods).contains(food);
    }

    @Test
    public void whenRefreshStoresThenFoodMovedToShopWithDiscountedPrice() {
        ControlQuality controlQuality = new ControlQuality();
        Store<Food> store = new Shop();
        controlQuality.addStore(store);
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 70);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_MONTH, -30);
        Food food = new Food(
                "AppleJuice",
                expiryDate,
                createDate,
                100,
                30);
        controlQuality.addFood(food);
        expiryDate.add(Calendar.DAY_OF_MONTH, -50);
        createDate.add(Calendar.DAY_OF_MONTH, -50);
        controlQuality.refreshStores();
        List<Food> foods = controlQuality.getStoreList().get(0).findAll();
        assertThat(foods).contains(food)
                .element(0)
                .matches((e) -> e.getPrice() == 70);
    }
}