import controller.FlightController;
import model.Flight;
import socket.FlightServerSocket;

public class Main {
    public static void main(String[] args) {
        FlightController.generateRandomFlights();

        FlightServerSocket flightServerSocket = new FlightServerSocket();
        flightServerSocket.run();
    }
}
