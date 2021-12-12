package airplaneSeating;

import java.util.*;

public class AirplaneSeating {

	public static void main(String[] args) {

		// declaring and initializing variables
		int noOfSeat, totalRows, totalColumns, totalNoOfSection, tempColumns = 0;
		int count = 1;

		// Creating scanner object
		Scanner sc = new Scanner(System.in);

		// Get total number of seats from user
		System.out.println("Enter the number of seats: ");
		noOfSeat = sc.nextInt();

		// Get total number of rows from user
		System.out.println("Enter the number of rows: ");
		totalRows = sc.nextInt();

		// Get total number of columns from user
		System.out.println("Enter the number of Columns: ");
		totalColumns = sc.nextInt();

		// Get total number of section from user
		System.out.println("Enter the toal number of section: ");
		totalNoOfSection = sc.nextInt();

		// Creating seatNames and seatAssigned arrays
		String[][] seatNames = new String[totalRows][totalColumns];
		int[][] seatAssigned = new int[totalRows][totalColumns];

		// Getting section information from user and assigning seat names
		for (int i = 1; i <= totalNoOfSection; i++) {
			int sectionRows, sectionColumns;

			// Get section rows and section columns indexes
			System.out.println("Enter " + i + " section row index and column index: ");
			sectionRows = sc.nextInt();
			sectionColumns = sc.nextInt();

			// assigning seats to corresponding seat names
			assignSeating(totalNoOfSection, i, sectionRows, sectionColumns, totalRows, totalColumns, seatNames,
					tempColumns);
			tempColumns = tempColumns + sectionColumns;
		}

		// displaying seat name details
		System.out.println("Seat name details: ");
		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {
				System.out.print(seatNames[i][j] + " ");
			}
			System.out.println();
		}

		// assigning seats to passengers
		count = assigningPassengers("Aisle", totalRows, totalColumns, count, noOfSeat, seatAssigned, seatNames);
		count = assigningPassengers("Window", totalRows, totalColumns, count, noOfSeat, seatAssigned, seatNames);
		count = assigningPassengers("Middle", totalRows, totalColumns, count, noOfSeat, seatAssigned, seatNames);

		// displaying passengers seats details
		System.out.println("Assigning passengers to seats: ");
		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {
				System.out.print(seatAssigned[i][j] + " ");
			}
			System.out.println();
		}

		sc.close();

	}

	/**
	 * Assigning seats to passengers
	 * 
	 * @param seatName
	 * @param totalRows
	 * @param totalColumns
	 * @param count
	 * @param noOfSeat
	 * @param seatAssigned
	 * @param seatNames
	 * @return
	 */
	static int assigningPassengers(String seatName, int totalRows, int totalColumns, int count, int noOfSeat,
			int[][] seatAssigned, String[][] seatNames) {
		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {
				if (seatNames[i][j] == seatName && count <= noOfSeat) {
					// assigning seats to passengers
					seatAssigned[i][j] = count;
					// increasing seat numbers
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Assigning seats to corresponding seat name
	 * 
	 * @param totalSectionCount
	 * @param sectionCount
	 * @param noOfSectionRows
	 * @param noOfSectionColumns
	 * @param totalRows
	 * @param totalColumns
	 * @param seatNames
	 * @param startColumn
	 */
	static void assignSeating(int totalSectionCount, int sectionCount, int noOfSectionRows, int noOfSectionColumns,
			int totalRows, int totalColumns, String[][] seatNames, int startColumn) {
		int endColumn = startColumn + noOfSectionColumns;
		for (int i = 0; i < noOfSectionRows; i++) {
			for (int j = startColumn; j < endColumn; j++) {
				if (sectionCount == 1 && j == 0) {
					seatNames[i][j] = "Window"; // logic for first section window seat assignment
				} else if (sectionCount == totalSectionCount && j == (endColumn - 1) && j == (totalColumns - 1)) {
					seatNames[i][j] = "Window"; // logic for last section window seat assignment
				} else if (j == startColumn || j == (endColumn - 1)) {
					seatNames[i][j] = "Aisle"; // logic for assigning aisle seats
				} else {
					seatNames[i][j] = "Middle"; // logic for assigning middle seats
				}
			}
		}

	}

}
