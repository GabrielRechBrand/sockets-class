package socket;

import controller.FlightController;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FlightServerSocket {

    FlightController flightController = new FlightController();

    public void run() {
        try {
            ServerSocket server = new ServerSocket(Integer.parseInt(JOptionPane.showInputDialog("Insert the port")));
            Socket socket = server.accept();

            System.out.println("Connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = "";

            while((message = in.readLine()) != null) {

                String[] parts = message.split(";");
                String flightCode = parts[1];
                Integer seatNumber = Integer.parseInt(parts[2]);

                switch (message.charAt(0)) {
                    case 'C':
                        flightController.getFlightStatus(flightCode, seatNumber);
                        break;
                    case 'B':
                        flightController.bookFlight(flightCode, seatNumber);
                        break;
                }
            }

            System.out.println("Disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
