package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListUsage {
    public static void main(String[] args) {
        /*boolean add (new elements)*/
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        for (String s : rsl) {
            System.out.println("Текущий элемент: " + s);
        }

        /*void add(int index, E element)*/
        rsl.add(1, "four");
        for (String s : rsl) {
            System.out.println("Текущий элемент: " + s);
        }

        /*boolean addAll(Collection<? extends E> c) */
        List<String> list = new ArrayList<>();
        list.add("four");
        list.add("five");

        rsl.addAll(list);

        /*boolean addAll(int index, Collection<? extends E> c)*/
        List<String> list1 = new ArrayList<>();
        list.add("four");
        list.add("five");

        rsl.addAll(2, list1);

        /*List<E> of(E ... elements) - returns immutable list*/
        List<String> rsl1 = List.of("one", "two", "three");

        /*E get(int index), throws IndexOutOfBoundsException if index < 0 or index > size()*/
        for (int i = 0; i < rsl.size(); i++) {
            System.out.println("Текущий элемент: " + rsl.get(i));
        }

        /*usage iterator(), ListIterator<E> listIterator(), ListIterator<E> listIterator(int index)*/
        Iterator<String> iterator = rsl.iterator();
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }

        ListIterator<String> iterator1 = rsl.listIterator();
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }

        ListIterator<String> iterator2 = rsl.listIterator(2);
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }

        /*E set(int index, E element) –
        changes element with position = index by element-argument (element)*/
        rsl.set(1, "two and second");

        /*default void replaceAll(UnaryOperator<E> operator) –
        changes each element of list by (operator)*/
        rsl.replaceAll(String::toUpperCase);

        /*E remove(int index) removes by index and returns removed element*/
        rsl.remove(1);

        /*boolean remove(E e) – removes first entering of element if it exists, works by for
        so bad way - us it in for*/
        rsl.remove("three");

        /*boolean removeAll(Collection<?> col), returns true if col was changed*/
        List<String> list2 = new ArrayList<>();
        list2.add("one");
        list2.add("three");

        rsl.removeAll(list2);

        /*default boolean removeIf(Predicate<? super E> filter), works with lambda,
        returns true if list was changed*/
        rsl.removeIf(s -> s.length() <= 3);
    }
}
