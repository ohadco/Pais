import java.util.Date;


public interface PaisBridge {
	public boolean addShowPageIsPresented();
	public boolean addShow(String name,
							String description,
							String hall,
							Date dateAndTime,
							double price, 
							Date lastOrders );
	public int[][] openShowPage();
	public boolean orderTickets(String name, String phone, int[][] seats);
}
