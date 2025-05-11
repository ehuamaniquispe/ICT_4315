package edu.du.ict4315.parking;
import edu.du.ict4315.currency.Money;

import java.time.LocalDateTime;

public abstract class ParkingChargeCalculator {

    public abstract Money getParkingCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime);
}
