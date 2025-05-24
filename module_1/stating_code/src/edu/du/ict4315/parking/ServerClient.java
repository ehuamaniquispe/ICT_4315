package edu.du.ict4315.parking;

public class ServerClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 4444; // adjust to match your server port
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ServerClient <COMMAND> <key=value> <key=value> ...");
            return;
        }

        String command = args[0];
        Properties props = new Properties();

        for (int i = 1; i < args.length; i++) {
            String[] parts = args[i].split("=", 2);
            if (parts.length == 2) {
                props.setProperty(parts[0], parts[1]);
            } else {
                System.out.println("Invalid parameter: " + args[i]);
                return;
            }
        }

        // Build request
        ParkingRequest request = new ParkingRequest(command, props);

        try (
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Send JSON request
            String jsonRequest = request.toJson();
            out.println(jsonRequest);
            out.flush();

            // Read JSON response
            String jsonResponse = in.readLine();
            ParkingResponse response = ParkingResponse.fromJson(jsonResponse);

            // Display response
            System.out.println("Server Response:");
            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Message: " + response.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}