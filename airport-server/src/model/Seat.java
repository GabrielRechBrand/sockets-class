package model;

public class Seat implements Comparable<Seat> {

    private Integer number;
    private Boolean available;

    public Seat(Integer number) {
        this.number = number;
    }

    public Seat(Integer number, Boolean available) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                ", available=" + available +
                '}';
    }

    @Override
    public int compareTo(Seat other) {
        return Integer.compare(this.number, other.number);
    }
}
