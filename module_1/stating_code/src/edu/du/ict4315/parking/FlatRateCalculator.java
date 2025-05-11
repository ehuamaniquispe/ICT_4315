package edu.du.ict4315.parking;
import edu.du.ict4315.currency.Money;

import java.time.LocalDateTime;
public class FlatRateCalculator extends ParkingChargeCalculator {
    private final Money baseRate;

    public FlatRateCalculator(Money baseRate) {
        this.baseRate = baseRate;
    }

    @Override
    public Money getParkingCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime) {
        return baseRate; // Just a flat fee
    }
}
