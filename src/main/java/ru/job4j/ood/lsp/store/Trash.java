package ru.job4j.ood.lsp.store;

import java.util.List;

public class Trash extends AbstractStore {

    @Override
    public void add(Food food) {
        if (!isExpired(WAREHOUSE_FROM, TRASH_FROM, food)) {
            super.add(food);
        }
    }

    @Override
    public List<Food> clear() {
        List<Food> list = findAll().stream()
                .filter((food) -> isExpired(WAREHOUSE_FROM, TRASH_FROM, food))
                .toList();
        super.removeAll(list);
        return list;
    }
}
