package edu.du.ict4315.parking;
import java.util.Properties;
public class ParkingRequest {
    private String command;
    private Properties params;

    public ParkingRequest(String command, Properties params) {
        this.command = command;
        this.params = params;
    }

    // Getters
    public String getCommand() { return command; }
    public Properties getParams() { return params; }

    // JSON methods
    public String toJson() {
        return new com.google.gson.Gson().toJson(this);
    }

    public static ParkingRequest fromJson(String json) {
        return new com.google.gson.Gson().fromJson(json, ParkingRequest.class);
    }

    @Override
    public String toString() {
        return "Command: " + command + ", Params: " + params;
    }
}