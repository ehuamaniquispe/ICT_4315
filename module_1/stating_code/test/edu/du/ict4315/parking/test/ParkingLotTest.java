package edu.du.ict4315.parking.test;


import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotTest {

    @Test
    void testGetParkingChargesDelegatesToStrategy() {
        ParkingLot lot = new ParkingLot("L1", "Test Lot", new Address.Builder().build(), Money.of(5.0));
        lot.setParkingChargeStrategy(new ExitBasedHourlyStrategy());

        RealParkingOffice office = new RealParkingOffice();
        // Register the customer
        Customer customer = new Customer( "1234", "Eduardo", "Huamani", "971044899", new Address.Builder().build());
        office.register(customer); // Registering customer in the office

        // Register the car associated with the customer
        Car car = new Car(CarType.SUV,"ABC123" ,customer);
        office.register(car); // This registers the car and assigns a permit

        ParkingPermit permit = new ParkingPermit("123",car,LocalDateTime.of(2025, 4, 13, 2, 0));
        LocalDateTime in = LocalDateTime.of(2024, 4, 15, 8, 0);
        LocalDateTime out = LocalDateTime.of(2024, 4, 15, 10, 0);

        Money result = lot.getParkingCharges(permit, in, out);
        assertEquals(Money.of(10.0), result); // 2 hrs * $5
    }
}



