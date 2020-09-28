
import java.util.LinkedList;
import java.util.NoSuchElementException;
import main.java.BackEndJ.Movies;

public class BackEndHashTable implements MapADT {

  private static int capacity = 60;
  private static LinkedList<Movies>[] movies;
  private int size;
  private int hashKey;


  // initializes the table
  public BackEndHashTable() {
    movies = new LinkedList[capacity];
    size = 0;
  }

  // returns the hashKey (currently commented out for testing)
  private static int hashKey(Integer key) {
    // return Math.abs(key.hashCode()) % capacity;
    return key % capacity;
  }

  
  @Override
  public boolean putMovie(Object key, Movies movie) {
    hashKey = hashKey((Integer) key);
    Movies movieHolder;
    
    if (movies[hashKey] == null) {
      movies[hashKey] = new LinkedList<Movies>();
    }
    
    for (int j = 0; j < movies[hashKey].size(); j++) {
      movieHolder = movies[hashKey].get(j);
      if (movieHolder.getKey().equals(key)) {
        return false;
      }
    }
      
      movies[hashKey].add(movie);
      size++;
    
    /*
      if (((double) (size) / capacity) >= 0.80) {
        movieHolder = movie;
        LinkedList<Movies>[] movieList = new LinkedList[capacity];
        capacity = capacity * 2;
        movies = new LinkedList[capacity];

        for (int j = 0; j < movieList.length; j++) {
          movies[j] = movieList[j];
        }
      }
      */
    return true;
  }

  @Override
  public LinkedList<Movies> getMovie(Object key) throws NoSuchElementException {
    hashKey = hashKey((Integer) key);

    if (movies[hashKey] == null) {
      throw new NoSuchElementException();
    }
    return movies[hashKey];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean containsMovie(Object key) {
    hashKey = hashKey((Integer) key);

    if (movies[hashKey] != null && movies[hashKey].equals(key)) {
      return true;
    }
    return false;
  }

  @Override
  public Object removeMovie(Object key) {
    hashKey = hashKey((Integer) key);

    if (movies[hashKey] == null) {
      return null;
    }
    movies[hashKey] = null;
    size--;
    return movies[hashKey];
  }

  @Override
  public void clear() {
    movies = new LinkedList[60];
    size = 0;
  }



}
