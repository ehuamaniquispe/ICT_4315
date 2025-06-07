package edu.du.ict4315.parking;



public interface IPermitManager {
    ParkingPermit register(Car car);
    ParkingPermit findPermit(String id);
}