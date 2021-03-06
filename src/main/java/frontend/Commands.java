// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <front end>
package main.java.frontend;
import java.util.InputMismatchException;
import main.java.*;
import main.java.backend.MainBackEnd;
import java.util.Scanner;

public class Commands {

  public static MainBackEnd backEnd = Loader.backEnd;
  private static Scanner scan = new Scanner(System.in);
  
  /**
   * Add movie
   */
  public static Movie addMovie(String title) {
    //check table size
    
    System.out.println("Enter year as integer: ");
    int year = 0;
    int rating = 0;
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
    
    Movie movie = new Movie(title, year, rating, genre);
    backEnd.addToTable(title, movie);

    if (!backEnd.addToTable(title, movie)) {
      return null;
    }
    
    System.out.println("Movie added!");
    
    return movie;
  }
  
  /**
   * Look up / Display movie
   */
  public static void display(String title) {
    try {
      System.out.println("Title: " + backEnd.getMovie(title).getMovieTitle() + ", Year: " + 
          backEnd.getMovie(title).getYear() + ", Rating: " + backEnd.getMovie(title).getRating() + 
          ", Genre: " + backEnd.getMovie(title).getGenre());
    }
    catch(NullPointerException e) {
      System.out.println("Please enter a valid movie title");
    }
  }
  
  /**
   * Help message with user commands.
   */
  public static void help() {
    System.out.println("********************\n<a Title> to add Movie, <l Title> to display movie "
        + "info, <d Title> to delete movie, <h> to redisplay commands, and <q> to quit"
        + "\n********************");
  }
  
  //
  
  public static void main(String[] args) {
//    System.out.println(backEnd);
//    backEnd.addToTable("Up", new Movie("Up", 0, 0, ""));
//    System.out.println(backEnd.getMovie("Up").getMovieTitle());
//    
//    Movie newMovie = new Movie("It", 0, 0, "thriller");
//    backEnd.addToTable("It", newMovie);
//    Movie secondMovie;
//    System.out.println(backEnd.getMovie("It").getMovieTitle());
//    secondMovie = backEnd.getMovie("It");
//    System.out.println(secondMovie.getMovieTitle() + secondMovie.getGenre());


    
    
    //Welcome Message
    System.out.println("********************\nWelcome to the TeamGD Movie "
        + "Database Program for prominent movies of the 21st century! \nThis program contains movie"
        + " objects containing fields for year, rating, and genre.");
    help();
    
    Scanner scan = new Scanner(System.in);
    String command = scan.next();
    String title;
    
    while(!command.equalsIgnoreCase("q")) {
      //add movie
      if (command.equals("a")) {
        title = scan.nextLine();
        addMovie(title);
      }
      
      //look up / display movie class info
      else if (command.equals("l")) {
        title = scan.nextLine();
        display(title);
      }
      
      //delete movie
      else if (command.equals("d")) {
        title = scan.nextLine();
        backEnd.removeMovie(title);
      }
      
      //help
      else if (command.equals("h")) {
        help();
      }
      
      //invalid input
      else {
        System.out.println("invalid command");
      }
      
      command = scan.next();
    }
    
    scan.close();
    System.out.println("Thank you for using the Movie Database program!");
  }
}
