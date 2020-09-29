// --== CS400 File Header Information ==--
// Name: <Jacob Donovan>
// Email: <jdonovan3@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Prof. Dahl>
// Notes to Grader: <My hash table and a portion of my back end
// in the group submission the other
// group member's back end is used>

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class creates a hash table of movie objects and contains the methods to build and edit it
 * 
 * @author Jacob Donovan
 */
public class BackEndHashTable implements MapADT {

  private static int capacity = 60; // the predetermined capacity of our hash table
  private static LinkedList<Movies>[] movies; // the hash table: a linked list of movies
  private static int size; // the number of movies in the hash table
  private static int hashKey;


  /**
   * Creates a new hash table with length capacity
   */
  public BackEndHashTable() {
    movies = new LinkedList[capacity];
    size = 0;
  }

  /**
   * Calculates and returns the hash key
   * 
   * @param the name of the movie as the key
   * @returns an int that is the hash key for the movie
   */
  private static int hashKey(Object key) {
    return Math.abs(key.hashCode()) % capacity;

  }

  /**
   * Prints the contents of the hash tree when called
   */
  public static void print() {
    for (int i = 0; i < movies.length; i++) {
      if (movies[i] != null) {
        for (int j = 0; j < movies[i].size(); j++) {
          System.out.println(movies[i].get(j).display());
        }
      }
    }
  }

  /**
   * Places a movie at the correct location in the hash table. Checks for repeat
   * additions to the table
   * 
   * @param the key for the movie
   * @param the movie object to be added to the table
   * @returns a boolean that is true when added correctly and false otherwise
   */
  @Override
  public boolean putMovie(String key, Movies movie) {
    hashKey = hashKey(key);
    Movies movieHolder;

    if (movies[hashKey] == null) {
      movies[hashKey] = new LinkedList<Movies>();
    }

    for (int j = 0; j < movies[hashKey].size(); j++) {
      movieHolder = movies[hashKey].get(j);
      if (movieHolder.getMovieTitle().toLowerCase().equals(key)) {
        return false;
      }
    }
    try {
      movieHolder =
        new Movies(movie.getMovieTitle(), movie.getGenre(), movie.getYear(), movie.getRating());
      movies[hashKey].add(movieHolder);
      size++;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }

  /**
   * Retrieves and returns a movie object from the list based on the key
   * 
   * @param the key for the movie
   * @throws a NoSuchElementException when the requested movie is not found
   * @returns the movie object asked for or null
   */
  @Override
  public Movies getMovie(String key) throws NoSuchElementException {
    hashKey = hashKey(key);
    String movieName = key;

    if (movies[hashKey] == null) {
      throw new NoSuchElementException();
    }

    for (int i = 0; i < movies[hashKey].size(); i++) {
      if (movies[hashKey].get(i).getMovieTitle().equals(movieName)) {
        return movies[hashKey].get(i);
      }
    }
    return null;
  }

  /**
   * Returns the number of movies in the tree
   * 
   * @returns the size of the hash table
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Checks if the hash table has a movie in the hash table
   * 
   * @param the key for the movie
   * @returns true when found and false otherwise
   */
  @Override
  public boolean containsMovie(String key) {
    hashKey = hashKey(key);
    Movies movie;

    if (movies[hashKey] != null) {
      for (int j = 0; j < movies[hashKey].size(); j++) {
        movie = movies[hashKey].get(j);
        System.out.println(movie.getMovieTitle());
        if (movie.getMovieTitle().equals(key)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Removes a movie from the hash table based on its key
   * 
   * @param the key for the movie
   * @returns a boolean that is true when removed correctly and false otherwise
   */
  @Override
  public boolean removeMovie(String key) {
    hashKey = hashKey(key);

    if (movies[hashKey] == null) {
      return false;
    }

    for (int i = 0; i < movies[hashKey].size(); i++) {
      if (movies[hashKey].get(i).getMovieTitle().equals(key)) {
        Movies movie = movies[hashKey].get(i);
        movies[hashKey].remove(i);
        size--;
        return true;
      }
    }
    return false;
  }

  /**
   * Removes all movies from the hash table
   */
  @Override
  public void clear() {
    movies = new LinkedList[60];
    size = 0;
  }


}
