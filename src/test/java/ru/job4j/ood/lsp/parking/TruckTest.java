package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TruckTest {

    @Test
    public void checkTruck() {
        AbstractParking parking = new ParkingService(25, 25);
        AbstractCar car1 = new Truck(2);
        AbstractCar car2 = new Truck(3);
        AbstractCar car3 = new Truck(3);
        parking.getTrucks().add(car1);
        parking.getTrucks().add(car2);
        parking.getTrucks().add(car3);
        List<Car> expected = List.of(car1, car2, car3);
        assertThat(parking.getPassengerCars()).isEmpty();
        assertThat(parking.getTrucks()).hasSameElementsAs(expected)
                .hasSize(3);
    }
}