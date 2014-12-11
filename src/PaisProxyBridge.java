import java.util.Date;


public class PaisProxyBridge implements PaisBridge {

	@Override
	public boolean addShowPageIsPresented() {
		//nothing to check with this function on proxy bridge
		return true;
	}

	@Override
	public boolean addShow(String name,
							String description,
							String hall,
							Date dateAndTime,
							double price, 
							Date lastOrders ) {
		
		if (name == null || description == null || hall == null||
				dateAndTime == null || price == 0 || lastOrders == null) return false;
		//TODO - need to check if [date, time, hall] are already taken.
		return true;
	}

	@Override
	public int[][] openShowPage() {
		//occupied seat = 1 
		//unoccupied seat = 0
		int[][] seats = new int[][]{
				  { 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				  { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
				  { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0 },
				  { 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 }
				};
		return seats;
	}

	@Override
	public boolean orderTickets(String name, String phone, int[][] seats) {
		// TODO Auto-generated method stub
		return false;
	}

}
