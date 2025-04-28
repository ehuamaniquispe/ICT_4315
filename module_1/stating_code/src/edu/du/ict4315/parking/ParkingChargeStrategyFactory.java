package edu.du.ict4315.parking;

import edu.du.ict4315.parking.ParkingChargeStrategy;
import edu.du.ict4315.parking.ParkingLot;

public interface ParkingChargeStrategyFactory {
    ParkingChargeStrategy createStrategy(ParkingLot parkingLot);
}