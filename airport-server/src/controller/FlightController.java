package controller;

import model.Flight;
import model.Seat;

import java.util.*;

public class FlightController {

    public static Integer NUMBER_OF_FLIGHTS = 10;
    private List<Flight> flights;

    public FlightController() {
    }

    public Integer getFlightStatus(String flightCode, Integer seatNumber) {
        Flight flight = getFlight(flightCode);

        if(flight != null) {
            Seat seat = getSeat(flight, seatNumber);

            if(seat != null) {
                return seat.getAvailable() ? 0 : 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public Integer bookFlight(String flightCode, Integer seatNumber) {
        Flight flight = getFlight(flightCode);

        if(flight != null) {
            Seat seat = getSeat(flight, seatNumber);

            if(seat != null) {
                flights.get(flights.indexOf(flight)).getSeats().get(flight.getSeats().indexOf(seat)).setAvailable(false);
            } else {
                return 2;
            }
        } else {
            return 3;
        }

        return 3;
    }

    public Flight getFlight(String code) {
        return flights.stream().filter(f -> f.getCode().equals(code)).findFirst().orElse(null);
    }

    public Seat getSeat(Flight flight, Integer seatNumber) {
        return flight.getSeats()
                .stream()
                .filter(s -> s.getNumber().equals(seatNumber)).findFirst().orElse(null);
    }

    public static void generateRandomFlights() {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();
        for (char c = 'A'; c <= 'Y'; c++) {
            String code = c + "1";
            List<Seat> seats = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                boolean available = random.nextBoolean();
                Seat seat = new Seat(i, available);
                seats.add(seat);
            }
            Collections.sort(seats);
            Flight flight = new Flight(code, seats);
            flights.add(flight);
        }
        System.out.println(flights);
    }

}
