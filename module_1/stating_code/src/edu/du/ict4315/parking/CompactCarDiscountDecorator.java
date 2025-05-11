package edu.du.ict4315.parking;
import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;

public class CompactCarDiscountDecorator extends ParkingChargeCalculatorDecorator {

    public CompactCarDiscountDecorator(ParkingChargeCalculator calculator) {
        super(calculator);
    }

    @Override
    public Money getParkingCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime) {
        Money originalCharge = calculator.getParkingCharge(permit, entryTime, exitTime);
        if (permit.getCar().getType() == CarType.COMPACT) {
            return Money.times(originalCharge, 0.8); // 20% discount
        }
        return originalCharge;
    }
}