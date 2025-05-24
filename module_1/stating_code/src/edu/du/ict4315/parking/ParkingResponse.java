package edu.du.ict4315.parking;

public class ParkingResponse {
    private int statusCode;
    private String message;

    public ParkingResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String toJson() {
        return new com.google.gson.Gson().toJson(this);
    }

    public static ParkingResponse fromJson(String json) {
        return new com.google.gson.Gson().fromJson(json, ParkingResponse.class);
    }

    @Override
    public String toString() {
        return "Status: " + statusCode + ", Message: " + message;
    }
}