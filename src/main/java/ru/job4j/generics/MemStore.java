package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        for (String key : storage.keySet()) {
            if (key.equals(id)) {
                storage.put(key, model);
                break;
            }
        }
        return storage.containsValue(model);
    }

    @Override
    public boolean delete(String id) {
        boolean delete = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            delete = true;
        }
        return delete;
    }

    @Override
    public T findById(String id) {
        T value = null;
        for (String key : storage.keySet()) {
            if (key.equals(id)) {
                value = storage.get(key);
            }
        }
        return value;
    }
}
