
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Movies {
  
  private static String movieTitle;
  private static String rating;
  private static String genre;
  private static String year;
  private static Object key;
  
  public Movies(String name, String genre, String year, String rating) throws FileNotFoundException {
    movieTitle = name;
    this.rating = rating;
    this.genre = genre;
    this.year = year;
    this.key = getKey();
  }
  
  public Object getKey() {
    int key = Integer.parseInt(year); // however we make our hash key
    return key;
  }
  
  public String getYear() {
    return year;
  }
  
  public String getMovieTitle() {
    return movieTitle;
  }
  
  public String getRating() {
    return rating;
  }
  
  public String getGenre() {
    return genre;
  }
  
  public static void display() {
    System.out.println("" + movieTitle + ", " + genre + ", " + year + ", " + rating);
  }
  
}
