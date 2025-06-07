// File: Server.java
package edu.du.ict4315.parking.server;

import com.google.gson.Gson;
import edu.du.ict4315.parking.protocol.ParkingRequest;
import edu.du.ict4315.parking.protocol.ParkingResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 4444;
    private static final Gson gson = new Gson();
    private static final int MAX_THREADS = 10; // adjustable
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);

    public static void main(String[] args) {
        System.out.println("Multithreaded Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                threadPool.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    // Inner class for handling client connections
    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();

            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String jsonRequest = in.readLine();
                ParkingRequest request = ParkingRequest.fromJson(jsonRequest);

                System.out.println("[" + Thread.currentThread().getName() + "] Handling command: " + request.getCommand());

                ParkingResponse response = processRequest(request);
                out.println(response.toJson());
                out.flush();

            } catch (IOException e) {
                System.err.println("Client handling error: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket");
                }
                long duration = System.currentTimeMillis() - start;
                System.out.println("[" + Thread.currentThread().getName() + "] Finished in " + duration + "ms");
            }
        }

        private ParkingResponse processRequest(ParkingRequest request) {
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

        private ParkingResponse handleCustomerCommand(Properties props) {
            String firstName = props.getProperty("firstname", "Unknown");
            String lastName = props.getProperty("lastname", "Unknown");
            return new ParkingResponse(200, "Customer registered: " + firstName + " " + lastName);
        }

        private ParkingResponse handleCarCommand(Properties props) {
            String plate = props.getProperty("plate", "N/A");
            String color = props.getProperty("color", "N/A");
            return new ParkingResponse(200, "Car registered: Plate=" + plate + ", Color=" + color);
        }
    }
}
