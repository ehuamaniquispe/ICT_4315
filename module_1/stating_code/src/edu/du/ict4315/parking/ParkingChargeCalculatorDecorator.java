package edu.du.ict4315.parking;
import edu.du.ict4315.currency.Money;

import java.time.LocalDateTime;


public abstract class ParkingChargeCalculatorDecorator extends ParkingChargeCalculator {
    protected final ParkingChargeCalculator calculator;

    public ParkingChargeCalculatorDecorator(ParkingChargeCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public abstract Money getParkingCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime);
}
