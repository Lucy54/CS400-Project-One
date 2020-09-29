// --== CS400 File Header Information ==--
// Name: <Jacob Donovan>
// Email: <jdonovan3@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Prof. Dahl>
// Notes to Grader: <My hash table and a portion of my back end
// in the group submission the other
// group member's back end is used>

import java.io.IOException;

/**
 * This class starts the application
 */
public class Start {

  /**
   * Calls loader method to start the program
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    try {
      Loader.runLoader();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to load movies and run the application. Please try again.");
    }
  }

}
