// --== CS400 File Header Information ==--
// Name: <Jacob Donovan>
// Email: <jdonovan3@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Prof. Dahl>
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

public interface MapADT<KeyType, KeyValue> {
  public boolean putMovie(String key, Movies value);
  public Movies getMovie(String key) throws NoSuchElementException;
  public int size();
  public boolean containsMovie(String key);
  public boolean removeMovie(String key);
  public void clear();
  
}