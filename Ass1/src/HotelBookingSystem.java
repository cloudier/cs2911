import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class HotelBookingSystem {
	/**
	 * Reads in a list of commands and processes them according to the
	 * assignment specification. Uses BufferedReader for the ReadLine method.
	 * Also allows for faster performance when reading large files.
	 * 
	 * @param args
	 *            [1] Name of input text file.
	 */
	public static void main(String[] args) {
		try {
			Reader file = new FileReader(args[0]);
			BufferedReader reader = new BufferedReader(file);

			HotelManager hotels = new HotelManager();

			for (String line = reader.readLine(); line != null; line = reader
					.readLine()) {
				String[] inputs = line.split(" ");
				if (inputs[0].equals("Hotel")) {
					String hotelName = inputs[1];
					int roomNumber = Integer.parseInt(inputs[2]);
					int capacity = Integer.parseInt(inputs[3]);

					hotels.makeNewRoom(hotelName, roomNumber, capacity);
				} else if (inputs[0].equals("Booking")) {
					int bookingID = Integer.parseInt(inputs[1]);
					String month = inputs[2];
					int startDate = Integer.parseInt(inputs[3]);
					int duration = Integer.parseInt(inputs[4]);

					int roomsSingle = 0;
					int roomsDouble = 0;
					int roomsTriple = 0;

					for (int i = 5; i < inputs.length; i = i + 2) {
						String type = inputs[i];
						int numRooms = Integer.parseInt(inputs[i + 1]);
						if (type.equals("single")) {
							roomsSingle = numRooms;
						} else if (type.equals("double")) {
							roomsDouble = numRooms;
						} else if (type.equals("triple")) {
							roomsTriple = numRooms;
						}
					}

					hotels.bookRooms(bookingID, month, startDate, duration,
							roomsSingle, roomsDouble, roomsTriple);
				} else if (inputs[0].equals("Change")) {
					int bookingID = Integer.parseInt(inputs[1]);
					String month = inputs[2];
					int startDate = Integer.parseInt(inputs[3]);
					int duration = Integer.parseInt(inputs[4]);

					int roomsSingle = 0;
					int roomsDouble = 0;
					int roomsTriple = 0;

					for (int i = 5; i < inputs.length; i = i + 2) {
						String type = inputs[i];
						int numRooms = Integer.parseInt(inputs[i + 1]);
						if (type.equals("single")) {
							roomsSingle = numRooms;
						} else if (type.equals("double")) {
							roomsDouble = numRooms;
						} else if (type.equals("triple")) {
							roomsTriple = numRooms;
						}
					}

					hotels.changeRooms(bookingID, month, startDate, duration,
							roomsSingle, roomsDouble, roomsTriple);
				} else if (inputs[0].equals("Cancel")) {
					int bookingID = Integer.parseInt(inputs[1]);
					hotels.cancelBooking(bookingID);
				} else if (inputs[0].equals("Print")) {
					String hotelName = inputs[1];
					hotels.findHotelByName(hotelName).print();
				}
			}

			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occured while trying to read '%s'.",
					args[0]);
			e.printStackTrace();
		}
	}
}