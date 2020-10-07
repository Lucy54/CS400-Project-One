// --== CS400 File Header Information ==--
// Name: <Shin-Tsz Lucy Kuo>
// Email: <skuo8@wisc.edu>
// Team: <GD>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap <KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private int capacity;
  private LinkedList<Pair>[] list;
  private int size;
  
  public class Pair {
    public KeyType key;
    public ValueType value;
    
    public Pair(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }
    
    public KeyType getKey() {
      return key;
    }
    
    public ValueType getValue() {
      return value;
    }
  }

  public HashTableMap(int capacity) {
    this.capacity = capacity;
    size = 0;
    list = new LinkedList[capacity];
    
    for (int i = 0; i < capacity; i++) {
      list[i] = new LinkedList<Pair>();
    }
  }
  
  public HashTableMap() {
    capacity = 10;
    size = 0;
    list = new LinkedList[10];
    
    for (int i = 0; i < capacity; i++) {
      list[i] = new LinkedList<Pair>();
    }
  }
  
  /**
   * Helper method to grow and rehash
   */
  private void grow() {
    capacity = 2 * capacity;
    LinkedList<Pair>[] temp = new LinkedList[capacity];
    int key;
    
    // initialize temp with empty LinkedLists
    for (int i = 0; i < capacity; i++) {
      temp[i] = new LinkedList<Pair>();
    }
    
    //rehash
    for (int i = 0; i < capacity / 2; i++) {
      for (int j = 0; j < list[i].size(); j++) {
        key = Math.abs(list[i].get(j).getKey().hashCode());
        temp[Math.abs(key % capacity)] = list[i];
      }
    }
    
    list = temp;
  }

  @Override
  public boolean put(KeyType key, ValueType value) {
    if (Math.abs(((size + 1) - capacity * 0.8)) < 0.001 || ((size + 1) > capacity * 0.8)) {
      grow();
    }
    //see if there are duplicates
      int index = Math.abs(key.hashCode() % capacity);
      
      for (Pair p : list[index]) {
        if (p.getKey().equals(key)) {
          return false;
        }
      }
      
      list[index].add(new Pair(key, value));
      size++;
    
    return true;
  }

  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key)) {
      throw new NoSuchElementException("no value found");
    }
    int index = Math.abs(key.hashCode() % capacity);
    
    for (int i = 0; i < list[index].size(); i++) {
      if (list[index].get(i).getKey().equals(key)) {
        return list[index].get(i).getValue();
      }
    }
    
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean containsKey(KeyType key) {
    int index = Math.abs(key.hashCode() % capacity);
    
    for (int i = 0; i < list[index].size(); i++) {
      if (list[index].get(i).getKey().equals(key)) {
        return true;
      }
    }
    
    return false;
  }

  @Override
  /**
   * Remove key
   * 
   * @return removed key or null if key does not exist
   */
  public ValueType remove(KeyType key) {
    int index = Math.abs(key.hashCode() % capacity);
    ValueType temp = null;
    
    for (int i = 0; i < list[index].size(); i++) {
      if (list[index].get(i).getKey().equals(key)) {
        temp = list[index].get(i).getValue();
        list[index].remove(i);
        size--;
      }
    }
    
    return temp;
  }

  @Override
  /**
   * Remove all key-value pairs from this collection.
   */
  public void clear() {
    list = new LinkedList[capacity];
    
    for (int i = 0; i < capacity; i++) {
      list[i] = new LinkedList<Pair>();
    }
    size = 0;
  }

}
