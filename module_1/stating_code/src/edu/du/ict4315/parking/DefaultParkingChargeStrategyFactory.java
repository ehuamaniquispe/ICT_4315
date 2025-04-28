package edu.du.ict4315.parking;

import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingChargeStrategy;
import edu.du.ict4315.parking.EntryBasedDailyStrategy;
import edu.du.ict4315.parking.ExitBasedHourlyStrategy;

public class DefaultParkingChargeStrategyFactory implements ParkingChargeStrategyFactory {

    @Override
    public ParkingChargeStrategy createStrategy(ParkingLot lot) {
        // Logic: based on lot name or id, choose a strategy
        if (lot.getName().toLowerCase().contains("daily")) {
            return new EntryBasedDailyStrategy();
        } else {
            return new ExitBasedHourlyStrategy();
        }
    }
}