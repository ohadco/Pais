package pais.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidan on 14/12/14.
 */
public class Hall {
    private String hallName;
    private boolean[][] seats;

    public Hall(String hallName, int rows, int cols) {
        this.hallName = hallName;
        seats = new boolean[rows][cols];
    }
}
