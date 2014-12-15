package pais; /**
 * Created by lidan on 14/12/14.
 */
import pais.exceptions.SaveShowResult;
import pais.models.Seat;
import pais.models.Show;

import java.util.List;


public interface PaisBridge {
    public SaveShowResult addShow(Show show);
    public Show newShowPage();
    public Show openShowPage(int showId);
    public boolean orderTickets(Show s, String name, String phone, List<Seat> seats);
    public void removeAllShows();
}
