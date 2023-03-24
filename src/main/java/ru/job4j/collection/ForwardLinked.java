package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        size++;
        modCount++;
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = head;
        for (int i = 0; i <= index; i++) {
            if (i != index) {
                currentNode = currentNode.next;
            }
        }
        return currentNode.item;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, head);
        head = newNode;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T delete = head.item;
        Node<T> first = head;
        head = first.next;
        first.next = null;
        first.item = null;
        return delete;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = currentNode.item;
                currentNode = currentNode.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
