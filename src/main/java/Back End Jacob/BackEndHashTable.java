
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BackEndHashTable implements MapADT {

  private static int capacity = 60;
  private static LinkedList<Movies>[] movies = new LinkedList[capacity];
  private int size;
  private int hashKey;


  public BackEndHashTable() {
    size = 0;
  }

  /**
   * Printer method that prints out the contents of the HashTableMap
   */
  public static void print() {
    for (int i = 0; i < capacity; i++) {
      if (movies[i] == null) {
        System.out.println("" + i + ". Empty");
      } else {
        for (int j = 0; j < movies[i].size(); j++) {
          System.out.println("" + i + ". " + movies[i].get(j).getMovieTitle() + "\nRating: "
            + movies[i].get(j).getRating() + "\nGenre: " + movies[i].get(j).getGenre());
        }
      }
    }

  }

  private static int hashKey(Object key) {
    return Math.abs(key.hashCode()) % capacity;
  }

  @Override
  public boolean putMovie(Object key, Object value) {
    hashKey = hashKey(key);
    Movies movie = new Movies(key, value);
    Movies movieHolder;

    if (movies[hashKey] == null) {
      movies[hashKey] = new LinkedList<Movies>();
      movies[hashKey].add(movie);
      size++;
    }
    else {
      for (int j = 0; j < movies[hashKey].size(); j++) {
        movieHolder = movies[hashKey].get(j);
        if (movieHolder.key().equals(key)) {
          return false;
        }
      }
      movies[hashKey].add(movie);
      size++;
    }
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
  public Object getMovie(Object key) throws NoSuchElementException {
    hashKey = hashKey(key);

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
    hashKey = hashKey(key);

    if (movies[hashKey] != null && movies[hashKey].equals(key)) {
      return true;
    }
    return false;
  }

  @Override
  public Object removeMovie(Object key) {
    hashKey = Math.abs(key.hashCode()) % capacity;

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
