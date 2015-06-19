import java.util.ArrayList;

public class HotelManager {
	private static final int SINGLE = 1;
	private static final int DOUBLE = 2;
	private static final int TRIPLE = 3;
	private ArrayList<Hotel> hotels;

	/**
	 * Creates a new Hotels object. This Hotels object contains an empty
	 * ArrayList of Hotel objects.
	 */
	public HotelManager() {
		super();
		this.hotels = new ArrayList<Hotel>();
	}

	/**
	 * Makes a new Room in Hotel with name hotelName.
	 * 
	 * @param hotelName
	 *            The name of the Hotel as a string.
	 * @param roomNumber
	 *            The number of the Room as an int.
	 * @param capacity
	 *            The capacity of the Room as an int. This capacity ranges from
	 *            1 (single) to 3 (triple).
	 */
	public void makeNewRoom(String hotelName, int roomNumber, int capacity) {
		Hotel hotel = findHotelByName(hotelName);
		if (hotel != null) {
			hotel.makeNewRoom(roomNumber, capacity);
		} else {
			Hotel newHotel = new Hotel(hotelName);
			newHotel.makeNewRoom(roomNumber, capacity);
			hotels.add(newHotel);
		}
	}

	/**
	 * Finds a Hotel with a given name.
	 * 
	 * @param hotelName
	 *            Name of the hotel you want to find as a String.
	 * @return the Hotel if found, and null otherwise.
	 */
	public Hotel findHotelByName(String hotelName) {
		for (Hotel h : hotels) {
			if (h.getName().equals(hotelName)) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Checks if any hotel has the required number of rooms available with
	 * specified capacities on the given dates. If a hotel is found, that hotel
	 * is returned. Otherwise returns null.
	 * 
	 * @param month
	 *            The month that the booking occurs in as a 3-letter String.
	 * @param startDate
	 *            The date that the booking begins on as an int.
	 * @param duration
	 *            The duration or length of the booking in days as an int.
	 * @param roomsSingle
	 *            The number of single rooms to book as an int. A single room is
	 *            a room with capacity 1.
	 * @param roomsDouble
	 *            The number of double rooms to book as an int. A double room is
	 *            a room with capacity 2.
	 * @param roomsTriple
	 *            The number of triple rooms to book as an int. A triple room is
	 *            a room with capacity 3.
	 * @return a hotel that has the required number of rooms in specified
	 *         capacities on the given dates, if one is available. Otherwise,
	 *         returns null.
	 */
	public Hotel checkRoomsAvailable(String month, int startDate, int duration,
			int roomsSingle, int roomsDouble, int roomsTriple) {
		for (Hotel h : hotels) {
			if (h.isAvailable(SINGLE, month, startDate, duration, roomsSingle)
					&& h.isAvailable(DOUBLE, month, startDate, duration,
							roomsDouble)
					&& h.isAvailable(TRIPLE, month, startDate, duration,
							roomsTriple)) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Checks if any hotel has the required number of rooms available with
	 * specified capacities on the given dates, ignoring any bookings with the
	 * given booking ID. If a hotel is found, that hotel is returned. Otherwise
	 * returns null.
	 * 
	 * @param month
	 *            The month that the booking occurs in as a 3-letter String.
	 * @param startDate
	 *            The date that the booking begins on as an int.
	 * @param duration
	 *            The duration or length of the booking in days as an int.
	 * @param roomsSingle
	 *            The number of single rooms to book as an int. A single room is
	 *            a room with capacity 1.
	 * @param roomsDouble
	 *            The number of double rooms to book as an int. A double room is
	 *            a room with capacity 2.
	 * @param roomsTriple
	 *            The number of triple rooms to book as an int. A triple room is
	 *            a room with capacity 3.
	 * @param bookingID
	 *            The booking ID to ignore when checking availabilities as an
	 *            int.
	 * @return a hotel that has the required number of rooms in specified
	 *         capacities on the given dates, if one is available. Otherwise,
	 *         returns null.
	 */
	public Hotel checkChangeAvailable(String month, int startDate,
			int duration, int roomsSingle, int roomsDouble, int roomsTriple,
			int bookingID) {
		for (Hotel h : hotels) {
			if (h.isChangeAvailable(SINGLE, month, startDate, duration,
					roomsSingle, bookingID)
					&& h.isChangeAvailable(DOUBLE, month, startDate, duration,
							roomsDouble, bookingID)
					&& h.isChangeAvailable(TRIPLE, month, startDate, duration,
							roomsTriple, bookingID)) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Cancels any bookings in any hotel with the specified booking ID.
	 * 
	 * @param bookingID
	 *            The ID of the booking that will be cancelled as an int.
	 */
	public void cancelBooking(int bookingID) {
		for (Hotel h : hotels) {
			h.cancelBooking(bookingID);
		}
		System.out.printf("Cancel %d\n", bookingID);
	}

	/**
	 * Checks to see if the given number of rooms with specified capacities are
	 * available within any one hotel. If they are available, the rooms are
	 * booked. Otherwise, the function prints out 'Booking rejected'.
	 * 
	 * @param bookingID
	 *            The ID of the booking as an int.
	 * @param month
	 *            The month of the booking as a 3-letter String.
	 * @param startDate
	 *            The date the booking begins as an int.
	 * @param duration
	 *            The length of the booking in days as an int.
	 * @param roomsSingle
	 *            The number of rooms with capacity 1 (single) to book.
	 * @param roomsDouble
	 *            The number of rooms with capacity 2 (double) to book.
	 * @param roomsTriple
	 *            The number of rooms with capacity 3 (triple) to book.
	 */
	public void bookRooms(int bookingID, String month, int startDate,
			int duration, int roomsSingle, int roomsDouble, int roomsTriple) {
		Hotel hotelToBook = checkRoomsAvailable(month, startDate, duration,
				roomsSingle, roomsDouble, roomsTriple);
		if (hotelToBook != null) {
			hotelToBook.bookRooms(bookingID, SINGLE, month, startDate,
					duration, roomsSingle);
			hotelToBook.bookRooms(bookingID, DOUBLE, month, startDate,
					duration, roomsDouble);
			hotelToBook.bookRooms(bookingID, TRIPLE, month, startDate,
					duration, roomsTriple);
			System.out.print("Booking");
			hotelToBook.printBookedRooms(bookingID, month, startDate,
					hotelToBook.getName());
		} else {
			System.out.print("Booking rejected");
		}
		System.out.print("\n");
	}

	/**
	 * Checks to see if the given number of rooms with specified capacities are
	 * available within any one hotel. This function treats bookings with the
	 * given booking ID as un-booked. If they are available, the previous
	 * bookings are cancelled and the new rooms are booked. Otherwise, the
	 * function prints out 'Booking rejected'.
	 * 
	 * @param bookingID
	 *            The ID of the booking to change as an int.
	 * @param month
	 *            The month of the booking as a 3-letter String.
	 * @param startDate
	 *            The date the booking begins as an int.
	 * @param duration
	 *            The length of the booking in days as an int.
	 * @param roomsSingle
	 *            The number of rooms with capacity 1 (single) to book.
	 * @param roomsDouble
	 *            The number of rooms with capacity 2 (double) to book.
	 * @param roomsTriple
	 *            The number of rooms with capacity 3 (triple) to book.
	 */
	public void changeRooms(int bookingID, String month, int startDate,
			int duration, int roomsSingle, int roomsDouble, int roomsTriple) {
		Hotel hotelToBook = checkChangeAvailable(month, startDate, duration,
				roomsSingle, roomsDouble, roomsTriple, bookingID);
		if (hotelToBook != null) {
			for (Hotel h : hotels) {
				h.cancelBooking(bookingID);
			}
			hotelToBook.bookRooms(bookingID, SINGLE, month, startDate,
					duration, roomsSingle);
			hotelToBook.bookRooms(bookingID, DOUBLE, month, startDate,
					duration, roomsDouble);
			hotelToBook.bookRooms(bookingID, TRIPLE, month, startDate,
					duration, roomsTriple);
			System.out.print("Change");
			hotelToBook.printBookedRooms(bookingID, month, startDate,
					hotelToBook.getName());
		} else {
			System.out.print("Change rejected");
		}
		System.out.print("\n");
	}
}
