package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        size++;
        modCount++;
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = head;
        for (int i = 0; i <= index; i++) {
            if (i != index) {
                currentNode = currentNode.next;
            }
        }
        return currentNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = currentNode.item;
                currentNode = currentNode.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        var list = new SimpleLinkedList<>();
        list.add(1);
        System.out.println("size " + list.size);
        list.add(2);
        System.out.println("size " + list.size);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        list.add(3);
        System.out.println("size " + list.size);
        list.add(4);
        System.out.println("size " + list.size);
        System.out.println(list.get(2));
        System.out.println(list.get(3));
    }
}

