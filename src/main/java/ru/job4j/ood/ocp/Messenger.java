package ru.job4j.ood.ocp;

public class Messenger {

    private Logger log;

    public Messenger(Logger log) {
        this.log = log;
    }

    public void sendMessage(String message) {
        log.logToFile(message);
    }

    /* в данном примере классы связаны напрямую, то есть через реализацию, а не абстракцию, если нам понадобиться
    например, сохранять логи не в файл, а в БД, придется переписывать код, кроме того в поле класса так же добавлена
    реализация, а не абстракция, что нарушает принцип открытости-закрытости, выход из ситуации - интерфейс с абстрактным
    методом logTo() */
}
