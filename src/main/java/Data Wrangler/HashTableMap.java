// --== CS400 File Header Information ==--
// Name: <Tzu-Chi Huang (Aris)>
// Email: <thuang245@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <I've created a private class called Movie within this class to help store
// the key-value pairs in
// the hashTable>

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class contains all the method use to alter or access the key-value pairs in the hashTable.
 * 
 * @author Aris Huang
 *
 * @param <KeyType>   The generic type that contain the type of the key
 * @param <ValueType> The generic type that contain the type of the value
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  /**
   * This is private class that help to store the key-value pairs as HashNode containing KeyType and
   * ValueType. This class also included methods to access HashNode with the key-value pairs.
   * 
   * @author Aris Huang
   */
  private class Movie {
    private String movieTitle;
    private int year;
    private int rating;
    private String genre;

    public Movie(String movieTitle, int year, int rating, String genre) {
        this.movieTitle = movieTitle;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
  int capacity;
  int size;
  private LinkedList<Movie>[] hashTable; // Zach's rec
  private LinkedList<Movie> list;
  
  //DATA WRANGLER 
  File movie;
  File year;
  File rating;
  File genre;

  /**
   * The constructor use to initialize the variables within this class. The hashTable created will
   * have a capacity that is equal to the argument passed in this constructor
   * 
   * @param capacity an int that specified all the possible spaces of the hashTable
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    hashTable = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      hashTable[i] = new LinkedList<Movie>();
    }
    //DATA WRANGLER 
     movie = new File("movie.txt");
     year = new File("year.txt");
     rating = new File("rating.txt");
     genre = new File("genre.txt");
  }

  /**
   * The default constructor without any argument will create a hashTable with capacity 10 and
   * initialized all variables.
   * 
   */
  public HashTableMap() {
    this.capacity = 10;// with default capacity = 10
    this.size = 0;
    hashTable = new LinkedList[10];
    for (int i = 0; i < capacity; i++) {
      hashTable[i] = new LinkedList<Movie>();
    }
    
    //DATA WRANGLER 
    movie = new File("movie.txt");
    year = new File("year.txt");
    rating = new File("rating.txt");
    genre = new File("genre.txt");

  }

  // DATA WRANGLER ADDITION
  /**
   * This method is used to access the data of a specified file and store the file's content into an
   * array. The file can either be movie titles, rating, year, or genre. This is mainly for the
   * purpose of creating a node (Movie class) to store the key-value pairs in this case will be
   * movie titles(key) - year,rating,& genre (value) pair.
   * 
   * @param fileName the file in the project directory that contains data of various information in
   *                 regard to the movie
   * @return fullContent  a two-dimensional array containing the content of that specified file
   * @throws FileNotFoundException if file cannot be found or does not exist in the directory
   */
  public String[] getSingleTypeData(File fileName) throws FileNotFoundException {
    // 1. create an array of movie titles in string
    Scanner scan = new Scanner(fileName);
    ArrayList<String> data = new ArrayList<String>();
    while (scan.hasNextLine()) {
      data.add(scan.nextLine());
    }
    String[] fullContent = data.toArray(new String[] {});

    return fullContent;
  }

  // DATA WRANGLER DATA ACCESS
  /**
   * This method is used to generate an array containing one data (string) object that has both the
   * key (movie titles) and the value (year, rating, genre) of that specific index. Essentially, we
   * are pairing them after before sorting them into the hashtable. The index, in this case, will be
   * the data that you want to collect. In this application, there will be 60 data available
   * starting from index 0 to 59. We are selecting the top 3 movies from year 2001 to 2020.
   * 
   * @param index       the data you want to access for that specific index
   * @param movieArray  the 2D array that was generated from getSingleTypeData(File fileName)
   * @param yearArray   the 2D array that was generated from getSingleTypeData(File fileName)
   * @param ratingArray the 2D array that was generated from getSingleTypeData(File fileName)
   * @param genreArray  the 2D array that was generated from getSingleTypeData(File fileName)
   * @return dataArray the 2D array of 1 data as a node
   */
  public String[] getData(int index, String[] movieArray, String[] yearArray, String[] ratingArray,
      String[] genreArray) {
    ArrayList<String> data = new ArrayList<String>();
    data.add(movieArray[index]);
    data.add(yearArray[index]);
    data.add(ratingArray[index]);
    data.add(genreArray[index]);
    String[] dataArray = data.toArray(new String[] {});
    return dataArray;
  }

  // DATA WRANGLER CLEARING THE DATA in the Hashtable
  /**
   * This method will clear everything in this hashTable by setting the hashTable to a new
   * LinkedList.
   *
   */
  @Override
  public void clear() {
    LinkedList<Movie>[] newHashTable;
    newHashTable = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      newHashTable[i] = new LinkedList<Movie>();
    }
    hashTable = newHashTable; // setting a new reference
    size = 0;
  }
  
//DATA WRANGLER DEMONSTRATION OF ACCESSING DATA
  public static void main(String[] args) throws IOException {
    HashTableMap<String, Integer> table = new HashTableMap<String, Integer>();
    File movie = new File("movie.txt");
    String[] movieArray;
    movieArray = table.getSingleTypeData(movie);
    
    File year = new File("year.txt");
    String[] yearArray;
    yearArray = table.getSingleTypeData(year);

    File rating = new File("ratings.txt");
    String[] ratingArray;
    ratingArray = table.getSingleTypeData(rating);
    
    File genre = new File("genre.txt");
    String[] genreArray;
    genreArray = table.getSingleTypeData(genre);
    
    //an example of getting one data (think of data as a node containing the key (at 0 index) and the value (from index 1 to 3). 
    String[] singleData = table.getData(1, movieArray, yearArray, ratingArray, genreArray); //you can try changing the index to get different datas
    System.out.print(Arrays.toString(singleData));
  }
  
  

  //fill in the rest from Back End Developer 
  @Override
  public boolean put(KeyType key, ValueType value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean containsKey(KeyType key) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public ValueType remove(KeyType key) {
    // TODO Auto-generated method stub
    return null;
  }

}
