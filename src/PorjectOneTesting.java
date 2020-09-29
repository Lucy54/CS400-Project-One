package main.java.Testing;

import main.java.Movie;
import main.java.backend.MainBackEnd;

/**
 * This class will test the functionality and correctness of Project One. It 
 * contains unit tests for each developers role in the program.
 * 
 * @author Juan Gutierrez
 * @version 1.0 
 */
public class ProjectOneTests {

	/**
	 * Contains all the unit tests for the back end developers code. Will print
	 * out message if any test fails. This message will indicate which test
	 * within this class has triggerred unexpected behavior.
	 * 
	 * @return true if all tests passed and false if any test fails.
	 */
	public static boolean backEndTests() {

		// Create new MainBackEnd object to test.
		MainBackEnd backEnd = new MainBackEnd();

		// T1
		// Test addToTable method.
		if (!backEnd.addToTable("Top Gun", new Movie("Top Gun", "1986", "6.9", "Action"))) {
			System.out.println("backEndTest() failed at T1");
			return false;
		}

		// T2
		// Add same movie again. addToTable should return false.
		if (backEnd.addToTable("Top Gun", new Movie("Top Gun", "1986", "6.9", "Action"))) {
			System.out.println("backEndTest() failed at T2");
			return false;
		}

		// T3
		// Add new movie. Should return true.
		if (!backEnd.addToTable("Harry Potter and the Philosopher's Stone", 
				new Movie("Harry Potter and the Philosopher's Stone", "2001",
						"7.6", "Fantasy"))) {
			System.out.println("backEndTest() failed at T3");
			return false;
		}

		// T4
		// Get Top Gun
		if (backEnd.getMovie("Top Gun") == null) {
			System.out.println("backEndTest() failed at T4");
			return false;
		}

		// T5
		// Get Movie not in hashTable
		if (backEnd.getMovie("Harry Potter and the Order of the Pheonix") != null) {
			System.out.println("backEndTest() failed at T5");
			return false;
		}

		// T6
		// Remove Harry Potter
		if (!backEnd.removeMovie("Harry Potter and the Philosopher's Stone")) {
			System.out.println("backEndTest() failed at T6");
			return false;
		}

		// T7
		// Remove movie not in hashTable.
		if (backEnd.removeMovie("The Shawshank Redemption")) {
			System.out.println("backEndTest() failed at T7");
			return false;
		}

		return true;
	}

	/**
	 * The main driver for the testing class. Driver will call each testing
	 * method in this class. Each individual method will print out its own 
	 * feedback. If nothing prints, all tests were successful.
	 * 
	 * @param args - String array of command line arguments.
	 */
	public static void main (String[] args) {
		backEndTests();
	}
}