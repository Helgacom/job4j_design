package ru.job4j.ood.lsp;

public class Customer extends Person {
    /* в данном примере нарушается принцип подстановки так как в классе-наследнике не сохранены базовые
    условия класса-родителя, не сохранена валидация имени, подстановка подкласса приведет к нарушению
    ожидаемого поведения программы при попытке создать заказчика с невалидным именем*/
    public Customer(String name, int age) {
        super(name, age);
    }
    /* в данном примере нарушается принцип подстановки так как в классе-наследнике усилено предусловие
    класса-родителя, подстановка подкласса приведет к нарушению ожидаемого поведения программы при попытке
    создать заказ*/
    @Override
    public boolean canBuy(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("The person isn't old enough");
        }
        return true;
    }

    public static void main(String[] args) {
        Person person = new Customer("Ivanov_I", 16);
        /* if (person.canBuy(person.age)) {
            createOrder();
           }*/
    }
}
