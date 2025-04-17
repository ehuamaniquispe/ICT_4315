package edu.du.ict4315.parking;

import java.time.LocalDateTime;
import edu.du.ict4315.currency.Money;

public interface ParkingChargeStrategy {
    Money calculateCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime, Money baseRate);
}