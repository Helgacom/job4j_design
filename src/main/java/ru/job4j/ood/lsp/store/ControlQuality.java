package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControlQuality {

    private final List<Store<Food>> storeList = new ArrayList<>();

    public void addStore(Store<Food> store) {
        storeList.add(store);
    }

    public void addFood(Food food) {
        storeList.forEach(s -> s.add(food));
    }

    public void refreshStores() {
        storeList.stream()
                .map(Store::clear)
                .flatMap(Collection::stream)
                .forEach(this::addFood);
    }

    public List<Store<Food>> getStoreList() {
        return storeList;
    }
}
