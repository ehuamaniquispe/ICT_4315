package edu.du.ict4315.parking.test;


import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParkingObserverTest {

    @Test
    public void testObserverReceivesExitEvent() {


        // Setup
        RealParkingOffice office = new RealParkingOffice();
        TransactionManager manager = new TransactionManager(office);
        ParkingLot lot = new ParkingLot("1", "Lot A", new Address.Builder().build(), Money.of(5), new FlatRateStrategyFactory());
        ConcreteParkingObserver observer = new ConcreteParkingObserver(manager);

        lot.addObserver(observer);

        // Register the customer
        Customer customer = new Customer( "1234", "Eduardo", "Huamani", "971044899", new Address.Builder().build());
        office.register(customer); // Registering customer in the office

        // Register the car associated with the customer
        Car car = new Car(CarType.SUV,"ABC123" ,customer);
        office.register(car); // This registers the car and assigns a permit

        ParkingPermit permit = new ParkingPermit("PERMIT-1", car, LocalDateTime.now().plusDays(1));

        LocalDateTime entry = LocalDateTime.now().minusHours(2);
        LocalDateTime exit = LocalDateTime.now();

        // Act
        lot.exit(permit, entry, exit);

        // Assert
        Money charges = manager.getParkingCharges(permit);
        assertNotNull(charges);
        assertTrue(charges.getAmount() > 0);
    }
}