package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class AbstractParking implements Parking {

    private final int placesForTrucks;
    private final int placesForPassengerCars;
    private int busyPlacesByTrucks;
    private int busyPlacesByPassengerCars;
    private List<Car> passengerCars = new ArrayList<>();
    private List<Car> trucks = new ArrayList<>();

    public AbstractParking(int placesForTrucks, int placesForPassengerCars) {
        this.placesForTrucks = placesForTrucks;
        this.placesForPassengerCars = placesForPassengerCars;
        this.busyPlacesByTrucks = 0;
        this.busyPlacesByPassengerCars = 0;
    }

    @Override
    public void add(AbstractCar car) {
    }

    public List<Car> getPassengerCars() {
        return passengerCars;
    }

    public List<Car> getTrucks() {
        return trucks;
    }
}
