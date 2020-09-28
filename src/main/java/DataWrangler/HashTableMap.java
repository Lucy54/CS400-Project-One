// --== CS400 File Header Information ==--
// Name: <Tzu Chi Huang (Aris)>
// Email: <thuang245@wisc.edu>
// Team: <GD>
// Role: <Data Wrangler 1>
// TA: <Dan Kiel>
// Lecturer: <Gary Dahl>
// Notes to Grader: <I'vecreated a private class called HashNode to help store the key-value pairs>

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
  private class HashNode {
    private KeyType key;
    private ValueType value;

    /**
     * This is the constructor use to create a new node with key and value.
     * 
     * @param key   the key specified as KeyType
     * @param value the value specified as ValueType
     */
    public HashNode(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Returns the value of a specific HashNode
     * 
     * @return value the value specified as ValueType
     */
    public ValueType getValue() {
      return value;
    }

    /**
     * Return the key of a specific HashNode
     * 
     * @return key the key specified as KeyType
     */
    public KeyType getKey() {
      return key;
    }
  }

  int capacity;
  int size;
  private LinkedList<HashNode>[] hashTable; // Zach's rec
  private LinkedList<HashNode> list;

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
      hashTable[i] = new LinkedList<HashNode>();
    }
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
      hashTable[i] = new LinkedList<HashNode>();
    }
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
   * @return fullContent a two-dimensional array containing the content of that specified file
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


  /**
   * Helper method for expanding the hashTable once the load capacity exceeds or reaches 80% of
   * original capacity.
   * 
   * @param hashTable the LinkedList array (hashtable) with initial capacity
   * @return newHashTable the LinkedList array (hashtable) with twice the initial capacity
   */
  public LinkedList<HashNode>[] expandHashTable(LinkedList<HashNode>[] HashTable) {
    LinkedList<HashNode>[] newHashTable = null;
    int index;
    if ((double) size / capacity >= 0.8) {
      this.capacity = capacity * 2;
      newHashTable = new LinkedList[capacity];
      for (int i = 0; i < capacity; i++) {
        newHashTable[i] = new LinkedList<HashNode>();
      }
      for (int i = 0; i < hashTable.length; i++) {
        for (int j = 0; j < hashTable[i].size(); j++) {
          index = Math.abs(hashTable[i].get(j).getKey().hashCode() % capacity);
          newHashTable[index]
              .add(new HashNode(hashTable[i].get(j).getKey(), hashTable[i].get(j).getValue()));
        }
      }
    }
    return newHashTable;
  }

  /**
   * This method will store the new key-value pair into the hashTable. If the hashTable has a load
   * factor that is equal to or greater than 80 percent of its initial capacity, it will further
   * call the expandHashTable() to create a new hashTable with increased capacity. If the key is
   * already in the hashTable, this method will return false.
   *
   * @param key   the key specified as KeyType
   * @param value the value specified as ValueType
   * @return true if the key-value pair is added to the hashTable, otherwise false.
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    int index;
    index = Math.abs(key.hashCode() % capacity);
    for (int i = 0; i < hashTable[index].size(); i++) {
      if (key.equals(hashTable[index].get(i).getKey())) {
        return false;
      }
    }
    hashTable[index].add(new HashNode(key, value));
    size++;
    if ((double) size / capacity >= 0.8) {
      hashTable = expandHashTable(hashTable);
    }
    return true;
  }

  /**
   * This method will get the value from the hashTable based on the key passed in. If the key does
   * not exist in the hashTable, throw a NoSuchElementException.
   * 
   * @param key the Key specified as KeyType
   * @return value if the key exists in the HashTable
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = Math.abs(key.hashCode() % capacity);
    for (int i = 0; i < hashTable[index].size(); i++) {
      if (key.equals(hashTable[index].get(i).getKey())) {
        return hashTable[index].get(i).getValue();
      }
    }
    if (hashTable[index].contains(key) == false) {
      throw new NoSuchElementException("The key does not exist in this hashTable");
    }
    return null;
  }

  /**
   * This method will return the size of the hashTable; meaning the amount of key-value pairs in the
   * hashtable.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * This method will determine if the hashTable already has a key that is same as the key passed
   * in.
   * 
   * @param key the Key specified as KeyType
   * @return true if the key exists in the hashtable, false otherwise.
   */
  @Override
  public boolean containsKey(KeyType key) {
    int index = Math.abs(key.hashCode() % capacity);
    for (int i = 0; i < hashTable[index].size(); i++) {
      if (key.equals(hashTable[index].get(i).getKey())) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method will remove the value in the hashTable based on the key passed in. If the key
   * exists in the hashTable, it will return a reference of the value corresponding to the key. If
   * the key does not exists, it will instead return a null.
   *
   * @param key the Key specified as KeyType
   * @return value specified as ValueType if the key exists in the hashtable, otherwise null.
   */
  @Override
  public ValueType remove(KeyType key) {
    if (key.equals(null)) {
      return null;
    } else {
      ValueType value;
      int nodeIndex = -1;
      int index = Math.abs(key.hashCode() % capacity);
      for (int i = 0; i < hashTable[index].size(); i++) {
        if (key.equals(hashTable[index].get(i).getKey())) {
          nodeIndex = i;
        }
      }
      if (nodeIndex == -1) {
        return null;
      } else {
        value = hashTable[index].get(nodeIndex).getValue();
        hashTable[index].clear();
        size--;
        return value;
      }
    }
  }

  /**
   * This method will clear everything in this hashTable by setting the hashTable to a new
   * LinkedList.
   *
   */
  @Override
  public void clear() {
    LinkedList<HashNode>[] newHashTable;
    newHashTable = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      newHashTable[i] = new LinkedList<HashNode>();
    }
    hashTable = newHashTable; // setting a new reference
    size = 0;
  }

  // DATA WRANGLER DEMONSTRATION OF ACCESSING DATA
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

    // an example of getting one data (think of data as a node containing the key (at 0 index) and
    // the value (from index 1 to 3).
    String[] singleData = table.getData(1, movieArray, yearArray, ratingArray, genreArray); // you
                                                                                            // can
                                                                                            // try
                                                                                            // changing
                                                                                            // the
                                                                                            // index
                                                                                            // to
                                                                                            // get
                                                                                            // different
                                                                                            // datas
    System.out.print(Arrays.toString(singleData));
  }


}
