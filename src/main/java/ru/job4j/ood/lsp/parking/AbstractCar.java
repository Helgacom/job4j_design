package ru.job4j.ood.lsp.parking;

public class AbstractCar implements Car {

    private final int size;
    private int placesNumber;

    public AbstractCar(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }
}
