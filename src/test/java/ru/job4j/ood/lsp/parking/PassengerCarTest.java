package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PassengerCarTest {

    @Test
    public void checkPassengerCar() {
        AbstractParking parking = new ParkingService(5, 5);
        AbstractCar car1 = new PassengerCar(1);
        AbstractCar car2 = new PassengerCar(1);
        AbstractCar car3 = new PassengerCar(1);
        parking.add(car1);
        parking.add(car2);
        parking.add(car3);
        List<Car> expected = List.of(car1, car2, car3);
        assertThat(parking.getTrucks()).isEmpty();
        assertThat(parking.getPassengerCars()).hasSameElementsAs(expected)
                .hasSize(3);
    }

    @Test
    void checkWhenParkingLotFull() {
        AbstractParking parking = new ParkingService(2, 2);
        AbstractCar car1 = new PassengerCar(1);
        AbstractCar car2 = new PassengerCar(1);
        AbstractCar car3 = new PassengerCar(1);
        parking.add(car1);
        parking.add(car2);
        assertThatThrownBy(() -> parking.add(car3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Parking doesn't have free places");
    }
}