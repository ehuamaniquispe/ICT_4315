package edu.du.ict4315.parking;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;
public class PakingService
{
    private RealParkingOffice office;
    private Map<String, Command> commands;

    public PakingService(RealParkingOffice office){
        this.office = office;
        this.commands = new HashMap<>();
        register( new RegisterCustomerCommand(office));
        register(new RegisterCarCommand(office));
    }

    private void register(Command command){
        commands.put(command.getCommandName(), command);
    }

    public String performCommand(String name, String[] parameters){
        Command command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + name);
        }
        Properties props = convertArgsToProperties(args);
        command.execute(props);
        return "command performed";
    }

    // Helper to convert the string to properties
    private Properties convertArgsToProperties(String[] args) {
        Properties props = new Properties();
        for (String arg : args) {
            String[] parts = arg.split("=", 2);
            if (parts.length == 2) {
                props.setProperty(parts[0], parts[1]);
            }
        }
        return props;
    }


}
