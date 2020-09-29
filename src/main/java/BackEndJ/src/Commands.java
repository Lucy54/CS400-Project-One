// --== CS400 File Header Information ==--
// Name: <Jacob Donovan>
// Email: <jdonovan3@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Prof. Dahl>
// Notes to Grader: <The Commands.java file contains the work of the Front End members edited
//                  slightly to work with my Back End and Hash Table implementation>

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class runs the front end of the application and allows users
 * to select from a set of commands to access movies and their data
 */
public class Commands {
  
  private static Scanner scan = new Scanner(System.in);
  
  /**
   * Receives and checks input from users for the correct format. Then it calls the back end
   * putMovie() method to add the requested movie to the hash table
   * 
   * @author Lucy Kuo
   * 
   * @param the title of the movie
   * @param the hash table of movies
   * @throws FileNotFoundException 
   */
  public static Movies addMovie(String title, BackEndHashTable table) throws FileNotFoundException {
    
    System.out.println("Enter year as integer: ");
    Integer year = 0;
    Integer rating = 0;
    boolean invalid = true;
    boolean falseYear = true;
    
    //year input
    while(invalid) {
      try {
        year = scan.nextInt();
        
        while(falseYear) {
          try {
            if ((year < 2000) || (year >= 2021)) {
              throw new NumberFormatException();
            }
            falseYear = false;
          }
          finally {
            //
          }
        }
        
        invalid = false;
      } 
      catch(InputMismatchException e) {
        System.out.println("Error: please enter year as an integer");
        invalid = true;
        scan.next();
      }
      catch(NumberFormatException a) {
        System.out.println("Error: please enter integer as valid year in 21st century");
      }
    }
    
    //rating input
    System.out.println("Enter rating % as integer: ");
    invalid = true;
    while(invalid) {
      try {
        rating = scan.nextInt();
        invalid = false;
      } 
      catch(InputMismatchException e) {
        System.out.println("Error: please enter rating % as an integer");
        invalid = true;
        scan.next();
      }
    }
    
    System.out.println("Enter genre: ");
    String genre = scan.next();
    
    // I edited these lines to work with my Movies.java class and hash table
    Movies movie = new Movies((String) title,(String) genre, year.toString(), rating.toString());
    table.putMovie(movie.getMovieTitle(), movie);
    
    return movie;
  }
  
  /**
   * Formats and displays the data for the requested movie by calling the getMovie() method from
   * the back end and the get() methods for the respective data
   * 
   * @author Lucy Kuo
   * 
   * @param the title of the movie
   * @param the hash table
   */
  public static void display(String title, BackEndHashTable table) {
    try {
      System.out.println("Title: " + table.getMovie(title).getMovieTitle() + ", Year: " + 
          table.getMovie(title).getYear() + ", Rating: " + table.getMovie(title).getRating() + 
          ", Genre: " + table.getMovie(title).getGenre());
    }
    catch(NullPointerException e) {
      System.out.println("Please enter a valid movie title");
    }
  }
  
  /**
   * Prints the list of possible commands
   * 
   * @author Lucy Kuo
   */
  public static void help() {
    System.out.println("********************\n<a Title> to add Movie, <l Title> to display movie "
        + "info, <d Title> to delete movie, <h> to redisplay commands, and <q> to quit"
        + "\n********************");
  }
  
  /**
   * Runs the application and allows users to enter commands
   * 
   * @author Lucy Kuo
   * 
   * @param the hash table
   */
  public static void runCommands(BackEndHashTable table) {
    
    Scanner scan = new Scanner(System.in);
    String command;
    String title;
    System.out.println("********************\nWelcome to the TeamGD Movie "
      + "Database Program for prominent movies of the 21st century! \nThis program contains movie"
      + " objects containing fields for year, rating, and genre.");
    help();
    boolean go = true;
    while (go) {
      command = scan.next();
      switch (command) {
        case "l":
          title = scan.nextLine().trim();
          System.out.println(title);
          display(title, table);
          break;

        case "q":
          System.exit(0);
          break;

        case "d":
          title = scan.nextLine().trim();
          if (table.containsMovie(title)) {
            System.out.println("Movie does not exist in database or has already been deleted");
            break;
          }
          if (table.removeMovie(title)) System.out.println("successfully deleted");
          break;

        case "h":
          help();
          break;

        case "a":
          title = scan.nextLine().trim();
          System.out.println(title);
          try {
          addMovie(title, table);
          } catch (Exception e) {
            System.out.println("File not found");
          }
          break;

        default:
          System.out.println("invalid command");
          break;
      }
    }
    scan.close();
    System.out.println("Thank you for using the Movie Database program!");
  }
  
}