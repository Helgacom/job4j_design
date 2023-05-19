package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((count * 1.0) / capacity >= LOAD_FACTOR) {
            expand();
        }
        int hashCode = (key == null) ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        }
        return rsl;
    }

    private int hash(int hashCode) {

        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> capacity));
    }

    private int indexFor(int hash) {

        return (hash == 0) ? 0 : hash & (table.length - 1);
    }

    private void expand() {
        MapEntry[] oldTable = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> e : oldTable) {
            if (e != null) {
                int hashCode = e.key == null ? 0 : e.key.hashCode();
                int index = indexFor(hash(hashCode));
                table[index] = e;
            }
        }
    }

    @Override
    public V get(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        if (table[indexFor((hash(hashCode)))] != null
                && Objects.equals(table[indexFor(hash(hashCode))].key, key)) {
            return table[indexFor(hash(hashCode))].value;
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(K key) {
        boolean removed = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        for (MapEntry<K, V> e : table) {
            if (table[indexFor(hash(hashCode))] != null
                    && Objects.equals(table[indexFor(hash(hashCode))].key, key)) {
                table[indexFor(hash(hashCode))] = null;
                removed = true;
                modCount++;
                break;
            }
        }
        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            MapEntry<K, V> next;
            MapEntry<K, V> current;
            int expectedModCount;
            int index;

            {
                expectedModCount = modCount;
                index = 0;
                if (count > 0) {
                    do {
                        next = table[index++];
                    } while (index < table.length && next == null);
                }
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return next != null;
            }

            @Override
            public K next() {
                MapEntry<K, V> e = next;
                if (e == null) {
                    throw new NoSuchElementException();
                }
                do {
                    current = e;
                    next = null;
                } while (index < table.length && (next = table[index++]) == null);
                return e.key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(null, "0000");
        map.put(0, "0");
        System.out.println("Count:" + map.count);
        System.out.println(map.get(null));
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
    }
}
