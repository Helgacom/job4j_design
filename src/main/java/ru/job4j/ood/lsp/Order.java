package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Order {
    Person person;
    List<String> list = new ArrayList<>();

    public Order(Person person, List<String> list) {
        this.person = person;
        this.list = list;
    }

    public void add(String product) {
        if (!validate(product)) {
            throw new IllegalArgumentException("Wrong format");
        }
        list.add(product);
    }

    public boolean validate(String product) {
        if (product.length() == 0) {
            throw new IllegalArgumentException("Empty line");
        }
        if (product.startsWith(" ")) {
            throw new IllegalArgumentException("Product name starts with extra space");
        }
        return true;
    }

    class IOrder extends Order {
        Person person;
        List<String> list = new ArrayList<>();

        public IOrder(Person person, List<String> list) {
            super(person, list);
        }

        @Override
        public void add(String product) {
            super.add(product);
        }
        /* в данном примере происходит нарушение принципа подстановки, так как ослабление условий валидации
        наименования продукта может привести при подстановке к созданию заказа подкласса, который не мог быть
        создан в результате проверки в классе-родителе*/
        @Override
        public boolean validate(String product) {
            if (product.length() == 0) {
                throw new IllegalArgumentException("Empty line");
            }
            return true;
        }
    }
}
