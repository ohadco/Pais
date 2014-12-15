package pais;

import pais.PaisBridge;
import pais.exceptions.SaveShowResult;
import pais.models.Seat;
import pais.models.Show;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lidan on 15/12/14.
 */
public class PaisProxyBridge implements PaisBridge {
    @Override
    public SaveShowResult addShow(Show show) {
        // TODO: check for intersection / duplication
        return null;
    }

    @Override
    public Show newShowPage() {
        return null;
    }

    @Override
    public Show openShowPage(int showId) {
        return null;
    }

    @Override
    public boolean orderTickets(Show show, String name, String phone, List<Seat> seats) {
        return false;
    }

    @Override
    public void removeAllShows() {

    }
}
