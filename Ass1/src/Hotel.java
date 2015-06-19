import java.util.ArrayList;

public class Hotel {
	private String name;
	private ArrayList<Room> rooms;

	/**
	 * Creates a new Hotel object. Contains an empty ArrayList of rooms.
	 * 
	 * @param name
	 *            The name of the new hotel as a string.
	 */
	public Hotel(String name) {
		super();
		this.name = name;
		this.rooms = new ArrayList<Room>();
	}

	/**
	 * Returns the name of the hotel.
	 * 
	 * @return The name of the hotel as a string.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Makes a new room in this hotel with the given room number and capacity.
	 * 
	 * @param roomNumber
	 *            The number of the room as an int.
	 * @param capacity
	 *            The capacity of the room as an int. This capacity ranges from
	 *            1 (single) to 3 (triple).
	 */
	public void makeNewRoom(int roomNumber, int capacity) {
		rooms.add(new Room(roomNumber, capacity));
	}

	/**
	 * Checks if this Hotel has the required number of Rooms available on the
	 * specified dates.
	 * 
	 * @param capacity
	 *            The capacity of the Room as an int. This capacity ranges from
	 *            1 (single) to 3 (triple).
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date the booking starts on as an int.
	 * @param duration
	 *            The length of the booking as an int.
	 * @param numberRooms
	 *            The number of rooms to book of the given capacity.
	 * @return boolean indicating if rooms are available in this Hotel.
	 */
	public boolean isAvailable(int capacity, String month, int startDate,
			int duration, int numberRooms) {
		int i = 0;
		for (Room r : rooms) {
			if (r.getCapacity() == capacity
					&& r.isRoomAvailable(month, startDate, duration)) {
				i++;
			}
		}
		return i >= numberRooms;
	}

	/**
	 * Checks if this Hotel has the required number of Rooms available on the
	 * specified dates ignoring the booking with the given ID.
	 * 
	 * @param capacity
	 *            The capacity of the Room as an int. This capacity ranges from
	 *            1 (single) to 3 (triple).
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date the booking starts on as an int.
	 * @param duration
	 *            The length of the booking as an int.
	 * @param numberRooms
	 *            The number of rooms to book of the given capacity.
	 * @param bookingID
	 *            The ID of the booking to ignore as an int.
	 * @return boolean indicating if rooms are available in this Hotel.
	 */
	public boolean isChangeAvailable(int capacity, String month, int startDate,
			int duration, int numberRooms, int bookingID) {
		int i = 0;
		for (Room r : rooms) {
			if (r.getCapacity() == capacity
					&& r.isRoomChangeAvailable(month, startDate, duration,
							bookingID)) {
				i++;
			}
		}
		return i >= numberRooms;
	}

	/**
	 * Books rooms in this hotel with the given requirements. Rooms are booked
	 * in order of input.
	 * 
	 * @param bookingID
	 *            The ID of the booking as an int.
	 * @param capacity
	 *            The capacity of the booking as an int. This capacity ranges
	 *            from 1 (single) to 3 (triple).
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date that the booking begins on as an int.
	 * @param duration
	 *            The length of the booking as an int.
	 * @param numberRooms
	 *            The number of rooms to book as an int.
	 */
	public void bookRooms(int bookingID, int capacity, String month,
			int startDate, int duration, int numberRooms) {
		int i = 0;
		for (Room r : rooms) {
			if (r.getCapacity() == capacity
					&& r.isRoomAvailable(month, startDate, duration)
					&& i < numberRooms) {
				r.makeNewBooking(bookingID, month, startDate, duration);
				i++;
			}
		}
	}

	/**
	 * Prints out booked rooms associated with a particular booking ID in the
	 * hotel in the format " bookingID hotelName roomNumber1 roomNumber2 ...".
	 * 
	 * @param bookingID
	 *            The ID of the booking as an int.
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date the booking begins on as an int.
	 * @param hotelName
	 *            The name of the hotel as a string.
	 */
	public void printBookedRooms(int bookingID, String month, int startDate,
			String hotelName) {
		System.out.printf(" %d %s", bookingID, hotelName);
		for (Room r : rooms) {
			if (r.getBookingID(month, startDate) == bookingID) {
				System.out.print(" ");
				System.out.print(r.getNumber());
			}
		}
	}

	/**
	 * Cancels bookings in any room of this hotel with the given booking ID.
	 * 
	 * @param bookingID
	 *            The ID of the booking as an int.
	 */
	public void cancelBooking(int bookingID) {
		for (Room r : rooms) {
			r.cancelBooking(bookingID);
		}
	}

	/**
	 * Prints out each room and their bookings in this Hotel in the format
	 * "hotelName roomNumber bookingMonth1 bookingDate1 duration1 bookingMonth2 bookingDate2 duration2"
	 * . For example, "Surfers 101 Jan 23 3 Jan 27 2".
	 */
	public void print() {
		for (Room r : rooms) {
			System.out.print(this.name + " " + r.getNumber());
			r.printBookings();
			System.out.print("\n");
		}
	}
}