package edu.du.ict4315.parking;

import java.util.Properties;

public class RegisterCarCommand implements Command{

    private RealParkingOffice office ;

    public RegisterCarCommand(RealParkingOffice office){
        this.office = office;
    }

    private void checkParameters(Properties properties){
        if (!properties.containsKey("id")
                || !properties.containsKey("firstName")
                || !properties.containsKey("lastName")
                || !properties.containsKey("phoneNumber")
                || !properties.containsKey("streetAddress1")
                || !properties.containsKey("streetAddress2")
                || !properties.containsKey("city")
                || !properties.containsKey("state")
                || !properties.containsKey("zip")
                || !properties.containsKey("type")
                || !properties.containsKey("licensePlate")) {
            throw new IllegalArgumentException("Missing required parameter");
        }
    }

    public String execute(Properties properties){



        CarType type = CarType.valueOf(properties.getProperty("type"));
        String licensePlate = properties.getProperty("licensePlate");



        String id = properties.getProperty("id");
        String firstName = properties.getProperty("firstName");
        String lastName = properties.getProperty("lastName");
        String phoneNumber = properties.getProperty("phoneNumber");

        String streetAddress1 = properties.getProperty("streetAddress1");
        String streetAddress2 = properties.getProperty("streetAddress2");
        String city = properties.getProperty("city");
        String state = properties.getProperty("state");
        String zip = properties.getProperty("zip");
        Address address = new Address.Builder()
                .withStreetAddress1(streetAddress1)
                .withStreetAddress2(streetAddress2)
                .withCity(city)
                .withState(state)
                .withZip(zip)
                .build();
        Customer owner = new Customer(id, firstName,lastName, phoneNumber,address);
        new Car(type,licensePlate,owner);
        return "Execution done";
    }


    public String getCommandName(){

        return "CAR";
    }
    public String getDisplayName(){

        return "Register Car";
    }


}
