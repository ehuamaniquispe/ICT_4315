package edu.du.ict4315.parking;
import java.util.Properties;
public interface Command {

    String getCommandName();
    String getDisplayName();
    String execute(Properties properties);
}
