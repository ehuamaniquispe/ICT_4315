package edu.du.ict4315.parking;

public interface ParkingObserver {

    void update(ParkingEvent event); // will be called for all changes
}
