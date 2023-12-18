package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            if (menuItemInfo.getNumber().length() == 2) {
                System.out.println(menuItemInfo.getName() + " " + menuItemInfo.getNumber());
            } else {
                System.out.println("-".repeat((int) Math.pow(menuItemInfo.getNumber()
                        .chars()
                        .filter(Character::isDigit)
                        .count(), 2)) + " " + menuItemInfo.getName() + " " + menuItemInfo.getNumber());
            }
        }
    }
}
