package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    public static final ActionDelegate STUB_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Menu simpleMenu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String menu = """
                    Введите действие:
                    1. Добавить элемент в корень меню;
                    2. Добавить элемент к родительскому элементу;
                    3. Вызвать действие, привязанное к пункту меню;
                    4. Вывести меню в консоль;
                    5. Выход;
                    """;
            System.out.println(menu);
            String answer = scanner.nextLine();
            if ("1".equals(answer)) {
                System.out.println("Введите название задачи");
                String itemName = scanner.nextLine();
                simpleMenu.add(Menu.ROOT, itemName, STUB_ACTION);
            } else if ("2".equals(answer)) {
                System.out.println("Введите название задачи и родительского элемента");
                String itemName = scanner.nextLine();
                String parentName = scanner.nextLine();
                simpleMenu.add(parentName, itemName, STUB_ACTION);
            } else if ("3".equals(answer)) {
                System.out.println("Введите название задачи");
                String itemName = scanner.nextLine();
                simpleMenu.select(itemName).ifPresentOrElse(i -> i.getActionDelegate().delegate(),
                        () -> System.out.println("Some action"));
            } else if ("4".equals(answer)) {
                menuPrinter.print(simpleMenu);
            } else if ("5".equals(answer)) {
                run = false;
            }
        }
    }
}
