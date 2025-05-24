package edu.du.ict4315.parking;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
public class Server {
    private static final int PORT = 4444;
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        System.out.println("Server started, listening on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.err.println("Client handling failed: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String jsonRequest = in.readLine();
            ParkingRequest request = ParkingRequest.fromJson(jsonRequest);

            System.out.println("Received command: " + request.getCommand());
            ParkingResponse response = processRequest(request);

            String jsonResponse = response.toJson();
            out.println(jsonResponse);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Failed to close client socket.");
            }
        }
    }

    private static ParkingResponse processRequest(ParkingRequest request) {
        String command = request.getCommand();
        Properties props = request.getProperties();

        switch (command.toUpperCase()) {
            case "CUSTOMER":
                return handleCustomerCommand(props);
            case "CAR":
                return handleCarCommand(props);
            default:
                return new ParkingResponse(400, "Unknown command: " + command);
        }
    }

    private static ParkingResponse handleCustomerCommand(Properties props) {
        String firstName = props.getProperty("firstname", "Unknown");
        String lastName = props.getProperty("lastname", "Unknown");
        return new ParkingResponse(200, "Customer registered: " + firstName + " " + lastName);
    }

    private static ParkingResponse handleCarCommand(Properties props) {
        String plate = props.getProperty("plate", "N/A");
        String color = props.getProperty("color", "N/A");
        return new ParkingResponse(200, "Car registered: Plate=" + plate + ", Color=" + color);
    }
}