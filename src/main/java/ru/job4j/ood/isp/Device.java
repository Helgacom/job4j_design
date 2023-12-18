package ru.job4j.ood.isp;

public interface Device {

    void readData();

    void writeData();
}
/*класс принтера зависит от метода интерфейса универсального устройства, который он не поддерживает,
что является нарушением принципа разделения интерфейсов*/
class Printer implements Device {

    @Override
    public void readData() {
        System.out.println("can't read");
    }

    @Override
    public void writeData() {
        System.out.println("can write");
    }
}
