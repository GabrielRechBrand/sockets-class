package model;

import java.util.List;

public class Flight {

    public static Integer numberSeats = 50;
    private String code;
    private List<Seat> seats;

    public Flight(String code) {
        this.code = code;
    }

    public Flight(String code, List<Seat> seats) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Seat findSeatByNumber(int i) {
        return this.getSeats().stream().filter(seat -> seat.getNumber().equals(i)).findFirst().orElse(null);
    }

}
