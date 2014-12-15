package pais.models;

import pais.exceptions.Hall;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by lidan on 14/12/14.
 */
public class Show {
    private int id;
    private String name;
    private String description;
    private Hall hall;
    private LocalDateTime showStart;
    private LocalDateTime showEnd;
    private double ticketPrice;
    private LocalDateTime lastTimeForPurchasing;
    private Seat[][] seats;

    public Show() {

    }

    public Show(int id,
                String name,
                String description,
                String hallName,
                LocalDateTime showStart,
                LocalDateTime showEnd,
                double ticketPrice,
                LocalDateTime lastTimeForPurchasing,
                int rows,
                int cols) {
        initSeats(rows, cols);
    }

    private void initSeats(int rows, int cols) {
        seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    public int getShowId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Hall getHall() { return hall; }
    public LocalDateTime getShowStart() { return showStart; }
    public LocalDateTime getShowEnd() { return showEnd; }
    public double getTicketPrice() { return ticketPrice; }
    public LocalDateTime getLastTimeForPurchasing() { return lastTimeForPurchasing; }

    /* Need to implement setters for input validation */
}
