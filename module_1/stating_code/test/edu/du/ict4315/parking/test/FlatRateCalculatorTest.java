package edu.du.ict4315.parking.test;


import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FlatRateCalculatorTest {
    @Test
    public void testCompactCarGetsDiscount() {

        // Register the customer
        Customer customer = new Customer( "1234", "Eduardo", "Huamani", "971044899", new Address.Builder().build());


        // Register the car associated with the customer
        Car car = new Car(CarType.SUV,"ABC123" ,customer);


        ParkingPermit permit = new ParkingPermit("123",car,LocalDateTime.of(2025, 4, 13, 2, 0));
        LocalDateTime entry = LocalDateTime.now().minusHours(2);
        LocalDateTime exit = LocalDateTime.now();

        ParkingChargeCalculator calc = new CompactCarDiscountDecorator(
                new FlatRateCalculator(Money.of(10.00))
        );

        Money charge = calc.getParkingCharge(permit, entry, exit);
        assertEquals(Money.of(8.00), charge); // 20% discount
    }
}