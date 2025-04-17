package edu.du.ict4315.parking.test;


import edu.du.ict4315.parking.*;
import edu.du.ict4315.currency.Money;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class EntryBasedDailyStrategyTest {
    private final ParkingChargeStrategy strategy = new EntryBasedDailyStrategy();
    private final Money baseRate = Money.of(10.0);



    @Test
    void testSingleNightCompactCarWeekend() {

        RealParkingOffice office = new RealParkingOffice();
        // Register the customer
        Customer customer = new Customer( "1234", "Eduardo", "Huamani", "971044899", new Address.Builder().build());
        office.register(customer); // Registering customer in the office

        // Register the car associated with the customer
        Car car = new Car(CarType.COMPACT,"ABC123" ,customer);
        office.register(car); // This registers the car and assigns a permit

        ParkingPermit permit = new ParkingPermit("123",car,LocalDateTime.of(2025, 4, 13, 2, 0));
        LocalDateTime entry = LocalDateTime.of(2024, 4, 13, 2, 0); // Saturday 2 AM
        LocalDateTime exit = LocalDateTime.of(2024, 4, 13, 5, 0);

        Money charge = strategy.calculateCharge(permit, entry, exit, baseRate);
        // Expected: 1 night * $10 * 0.8 * 1.2 = $9.60
        assertEquals(Money.of(9.60), charge);
    }

    @Test
    void testMultipleNightsStandardCarWeekday() {
        RealParkingOffice office = new RealParkingOffice();
        // Register the customer
        Customer customer = new Customer( "1234", "Eduardo", "Huamani", "971044899", new Address.Builder().build());
        office.register(customer); // Registering customer in the office

        // Register the car associated with the customer
        Car car = new Car(CarType.SUV,"ABC123" ,customer);
        office.register(car); // This registers the car and assigns a permit

        ParkingPermit permit = new ParkingPermit("123",car,LocalDateTime.of(2025, 4,13, 2, 0));


        LocalDateTime entry = LocalDateTime.of(2024, 4, 15, 2, 0); // Monday
        LocalDateTime exit = LocalDateTime.of(2024, 4, 17, 3, 0); // Wednesday

        Money charge = strategy.calculateCharge(permit, entry, exit, baseRate);
        // Expected: 3 nights * $10 = $30
        assertEquals(Money.of(30.0), charge);
    }




}