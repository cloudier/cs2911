import java.util.*;

public class Room {
	private static final int NO_BOOKING = 0;
	private static final int DAYS_IN_YEAR = 365;
	private int number;
	private int capacity;
	private int[] bookings;

	/**
	 * Creates a new Room with the given room number and capacity. The Room also
	 * contains an array of ints called bookings. The index of a booking
	 * represents the day in the year that it occurs on. The value at
	 * booking[index] is the booking ID if it is booked, and 0 otherwise.
	 * 
	 * @param number
	 *            The number of the room as an int.
	 * @param capacity
	 *            The capacity of the room as an int. This capacity ranges from
	 *            1 (single) to 3 (triple).
	 */
	public Room(int number, int capacity) {
		//super();
		this.number = number;
		this.capacity = capacity;
		this.bookings = new int[DAYS_IN_YEAR];
	}

	/**
	 * Returns the number of the room as an int.
	 * 
	 * @return The number of the room as an int.
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Returns the booking ID at a given date. If there is no booking on that
	 * date, 0 is returned.
	 * 
	 * @param month
	 *            The month that the booking occurs in.
	 * @param startDate
	 *            The date that the booking begins on.
	 * @return
	 */
	public int getBookingID(String month, int startDate) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, convertMonths(month));
		date.set(Calendar.DATE, startDate);
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		return bookings[dayOfYear - 1];
	}

	/**
	 * Makes a new booking in this Room starting from the given date for the
	 * given duration.
	 * 
	 * @param bookingID
	 *            The ID of the booking as an int.
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date the booking begins as an int.
	 * @param duration
	 *            The length of the booking as an int.
	 */
	public void makeNewBooking(int bookingID, String month, int startDate,
			int duration) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, startDate);
		date.set(Calendar.MONTH, convertMonths(month));
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		for (int index = dayOfYear - 1; index < (dayOfYear + duration - 1); index++) {
			bookings[index] = bookingID;
		}
	}

	/**
	 * Cancels the booking in this room with the given booking ID.
	 * 
	 * @param bookingID
	 *            The ID of the booking to cancel.
	 */
	public void cancelBooking(int bookingID) {
		for (int index = 0; index < DAYS_IN_YEAR; index++) {
			if (bookings[index] == bookingID) {
				bookings[index] = NO_BOOKING;
			}
		}
	}

	/**
	 * Returns the capacity of the room as an int.
	 * 
	 * @return the capacity of the room as an int. This capacity ranges from 1
	 *         (single) to 3 (triple).
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Prints the bookings in a room in the format
	 * " 3LetterMonth startDate duration", e.g. " Jan 1 1".
	 */
	public void printBookings() {
		int prevID = NO_BOOKING;
		int duration = 0;
		int startDate = 0;
		Calendar dayOfYear = Calendar.getInstance();
		for (int index = 0; index < DAYS_IN_YEAR; index++) {
			if (prevID == NO_BOOKING && bookings[index] != NO_BOOKING) {
				prevID = bookings[index];
				duration = 1;
				startDate = index;
			} else if (prevID == bookings[index]
					&& bookings[index] != NO_BOOKING) {
				duration++;
			} else if (prevID != bookings[index]
					&& bookings[index] == NO_BOOKING) {
				dayOfYear.set(Calendar.DAY_OF_YEAR, startDate + 1);
				System.out.print(" ");
				System.out.print(dayOfYear.getDisplayName(Calendar.MONTH,
						Calendar.SHORT, Locale.getDefault()));
				System.out.print(" ");
				System.out.print(dayOfYear.get(Calendar.DATE));
				System.out.print(" ");
				System.out.print(duration);

				prevID = NO_BOOKING;
				duration = 0;
			} else if (prevID != bookings[index]
					&& bookings[index] != NO_BOOKING) {
				dayOfYear.set(Calendar.DAY_OF_YEAR, startDate + 1);
				System.out.print(" ");
				System.out.print(dayOfYear.getDisplayName(Calendar.MONTH,
						Calendar.SHORT, Locale.getDefault()));
				System.out.print(" ");
				System.out.print(dayOfYear.get(Calendar.DATE));
				System.out.print(" ");
				System.out.print(duration);

				prevID = bookings[index];
				duration = 1;
				startDate = index;
			} else if (bookings[index] != NO_BOOKING
					&& index == DAYS_IN_YEAR - 1) {
				dayOfYear.set(Calendar.DAY_OF_YEAR, startDate + 1);
				duration++;

				System.out.print(" ");
				System.out.print(dayOfYear.getDisplayName(Calendar.MONTH,
						Calendar.SHORT, Locale.getDefault()));
				System.out.print(" ");
				System.out.print(dayOfYear.get(Calendar.DATE));
				System.out.print(" ");
				System.out.print(duration);
			}
		}
	}

	/**
	 * Returns a boolean indicating where the room is available during the given
	 * dates.
	 * 
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date the booking begins as an int.
	 * @param duration
	 *            The length of the booking in days as an int.
	 * @return true if room is available and false if not.
	 */
	public boolean isRoomAvailable(String month, int startDate, int duration) {
		int index;
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, convertMonths(month));
		date.set(Calendar.DATE, startDate);
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		for (index = dayOfYear - 1; index < (dayOfYear + duration - 1); index++) {
			if (bookings[index] != NO_BOOKING) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a boolean indicating where the room is available during the given
	 * dates. Ignores any bookings with the given booking ID.
	 * 
	 * @param month
	 *            The month of the booking as a 3-letter string.
	 * @param startDate
	 *            The date the booking begins as an int.
	 * @param duration
	 *            The length of the booking in days as an int.
	 * @param bookingID
	 *            The ID of the booking to ignore.
	 * @return true if room is available and false if not.
	 */
	public boolean isRoomChangeAvailable(String month, int startDate,
			int duration, int bookingID) {
		boolean roomAvailability = true;
		int index;
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, convertMonths(month));
		date.set(Calendar.DATE, startDate);
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		for (index = dayOfYear - 1; index < (dayOfYear + duration - 1); index++) {
			if (bookings[index] != NO_BOOKING && bookings[index] != bookingID) {
				roomAvailability = false;
			}
		}
		return roomAvailability;
	}
	
	private static int convertMonths(String month) {
		if (month.equals("Jan")) return 0;
		if (month.equals("Feb")) return 1;
		if (month.equals("Mar")) return 2;
		if (month.equals("Apr")) return 3;
		if (month.equals("May")) return 4;
		if (month.equals("Jun")) return 5;
		if (month.equals("Jul")) return 6;
		if (month.equals("Aug")) return 7;
		if (month.equals("Sep")) return 8;
		if (month.equals("Oct")) return 9;
		if (month.equals("Nov")) return 10;
		if (month.equals("Dec")) return 11;
		return -1;
	}
}
