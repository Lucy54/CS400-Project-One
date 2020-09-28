
import java.util.NoSuchElementException;

public interface MapADT<Integer, ValueType> {
  public boolean putMovie(Integer key, Movies value);
  public ValueType getMovie(Integer key) throws NoSuchElementException;
  public int size();
  public boolean containsMovie(Integer key);
  public ValueType removeMovie(Integer key);
  public void clear();

}