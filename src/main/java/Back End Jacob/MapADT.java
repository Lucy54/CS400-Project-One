
import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {
  public boolean putMovie(KeyType key, ValueType value);
  public ValueType getMovie(KeyType key) throws NoSuchElementException;
  public int size();
  public boolean containsMovie(KeyType key);
  public ValueType removeMovie(KeyType key);
  public void clear();

}