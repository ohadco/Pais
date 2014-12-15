package pais.models;

/**
 * Created by lidan on 14/12/14.
 */
public class Seat {
    public int row;
    public int col;
    public boolean isReserved;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.isReserved = false;
    }
}
