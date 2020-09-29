import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates a movie object, containing the movie's data, and allows for access
 * to the movie data. These movie items are added to the hash table
 * 
 * @author Jacob Donovan
 */
public class Movies {
  
  private Object[] data = new Object[4]; // contains all of the movie's data for easier access
  private String movieTitle;
  private String rating;
  private String genre;
  private String year;
  private Integer key;
  
  /**
   * Creates a new Movies object with given data
   * 
   * @param the name of the movie
   * @param the genre of the movie
   * @param the year the movie was made
   * @param the rating of the movie
   */
  public Movies(String name, String genre, String year, String rating) {
    movieTitle = name;
    this.rating = rating;
    
    this.genre = genre;
    this.year = year;
    
    data[0] = movieTitle;
    data[1] = this.genre;
    data[2] = this.year;
    data[3] = this.rating;
    
    key = Math.abs(name.hashCode()) % 60;
  }
  
  /**
   * Gets the movie's hashKey
   * 
   * @return the hash key
   */
  public int getKey() {// however we make our hash key
     return key;
  }
  
  /**
   * Gets the year the movie was made
   * 
   * @return the year the movie was made
   */
  public String getYear() {
    return year;
  }
  
  /**
   * Gets the name of the movie
   * 
   * @return the name of the movie
   */
  public String getMovieTitle() {
    return movieTitle;
  }
  
  /**
   * Gets the rating of the movie
   * 
   * @return the rating of the movie
   */
  public String getRating() {
    return rating;
  }
  
  /**
   * Gets the genre of the movie
   * 
   * @return the genre of the movie
   */
  public String getGenre() {
    return genre;
  }
  
  /**
   * Gets the array containing all of the movie data
   * 
   * @return an array containing the movie data
   */
  public Object[] getData() {
    return data;
  }
  
  /**
   * Formats and returns a string with the movie data
   * 
   * @return a string with the movie data
   */
  public String display() {
    return "" + movieTitle + ", " + genre + ", " + year + ", " + rating;
  }
  
  /**
   * Formats and returns an array containing all of the movie data
   * 
   * @return an array containing all of the movie data
   */
  public Object[] movieInfo() {
    return data;
  }
}
