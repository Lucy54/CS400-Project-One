// --== CS400 File Header Information ==--
// Name: <Jacob Donovan>
// Email: <jdonovan3@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Prof. Dahl>
// Notes to Grader: <The Commands.java file contains the work of the Data Wrangler members edited
//                  to work with my Back End and Hash Table implementation>

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
public class Loader {
  
  // DATA WRANGLER ADDITION
  /**
   * This method is used to access the data of a specified file and store the file's content into an
   * array. The file can either be movie titles, rating, year, or genre. This is mainly for the
   * purpose of creating a node (Movie class) to store the key-value pairs in this case will be
   * movie titles(key) - year,rating,& genre (value) pair.
   *
   * @author Aris Huang
   *
   * @param fileName the file in the project directory that contains data of various information in
   *        regard to the movie
   * @return fullContent a two-dimensional array containing the content of that specified file
   * @throws FileNotFoundException if file cannot be found or does not exist in the directory
   */
  public static String[] getSingleTypeData(File fileName) throws FileNotFoundException {
    // 1. create an array of movie titles in string
    Scanner scan = new Scanner(fileName);
    ArrayList<String> data = new ArrayList<String>();
    while (scan.hasNextLine()) {
      data.add(scan.nextLine());
    }
    String[] fullContent = data.toArray(new String[] {});

    return fullContent;
  }

  // DATA WRANGLER DEMONSTRATION OF ACCESSING DATA
  /**
   * This method is used to access the data of a specified file and store the file's content into an
   * array. The file can either be movie titles, rating, year, or genre. This is mainly for the
   * purpose of creating a node (Movie class) to store the key-value pairs in this case will be
   * movie titles(key) - year,rating,& genre (value) pair.
   *
   * @author Jacob Donovan
   * 
   * @throws IOException if file cannot be accessed found
   */
  public static void runLoader() throws IOException {
    BackEndHashTable table = new BackEndHashTable();
    Object[][] moviesAndInfo = new Object[4][60];
    Movies[] movies = new Movies[60];
    
    File movie = new File("names.txt");
    moviesAndInfo[0] = getSingleTypeData(movie);
    movie = new File("genre.txt");
    moviesAndInfo[1] = getSingleTypeData(movie);
    movie = new File("year.txt");
    moviesAndInfo[2] = getSingleTypeData(movie);
    movie = new File("rating.txt");
    moviesAndInfo[3] = getSingleTypeData(movie);
    
    Movies movieInfo;
    
    for (int i = 0; i < moviesAndInfo[0].length ; i++) {
      movieInfo = new Movies(moviesAndInfo[0][i].toString(), moviesAndInfo[1][i].toString(), moviesAndInfo[2][i].toString(), moviesAndInfo[3][i].toString());
      table.putMovie(movieInfo.getMovieTitle(), movieInfo);
    }
    
    Commands.runCommands(table);

  }


}