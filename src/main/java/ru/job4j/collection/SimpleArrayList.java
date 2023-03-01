package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private  T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            lengthGrow(container);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T beforeSet = container[index];
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return beforeSet;
    }

    @Override
    public T remove(int index) {
        T beforeRemoved = container[index];
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        size--;
        container[size] = null;
        modCount++;
        return beforeRemoved;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }

    private void lengthGrow(T[] container) {
        this.container = container.length == 0
                ? Arrays.copyOf(container, 1)
                : Arrays.copyOf(container, container.length * 2);
    }
}
