package edu.du.ict4315.parking;

import java.util.Properties;

public class RegisterCustomerCommand  implements Command{
    private RealParkingOffice office;

    public RegisterCustomerCommand(RealParkingOffice office){
        this.office = office;
    }

    private void checkParameters(Properties properties){
        if (!properties.containsKey("id") || !properties.containsKey("firstName")
                || !properties.containsKey("lastName")
                || !properties.containsKey("phoneNumber")
                || !properties.containsKey("streetAddress1")
                || !properties.containsKey("streetAddress2")
                || !properties.containsKey("city")
                || !properties.containsKey("state")
                || !properties.containsKey("zip")) {
            throw new IllegalArgumentException("Missing required parameter");
        }
    }

    public String execute(Properties properties){

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
      Customer customer = new Customer(id, firstName,lastName, phoneNumber,address);
      this.office.register(customer);

      return "Execution done";

    }


    public String getCommandName(){

        return "CUSTOMER";
    }
    public String getDisplayName(){

        return "Register Customer";
    }
}

