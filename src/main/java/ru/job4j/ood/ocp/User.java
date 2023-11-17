package ru.job4j.ood.ocp;

public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    private static class Report {
        String message;

        public Report(String message) {
            this.message = message;
        }

        public void sendSms() {
        }

        public void sendEmail() {
        }
    }

    public static void main(String[] args) {
        var user = new User("Ivanov_I.");
        User.Report report = new Report("Your order in progress");
        report.sendEmail();
        report.sendSms();
    }

    /* в данном случае класс User жестко связан с реализацией класса Report, что нарушает принцип открытости-закрытости
    классы должны зависеть от абстракций, класс отчета может быть интерфейсом с абстрактным методом отправки сообщения
    класс может реализовать его по-своему, при этом способы отправки отчета могут быть расширены, плюс для закрытости
    от изменений классу User нужны геттер и сеттер */
}
