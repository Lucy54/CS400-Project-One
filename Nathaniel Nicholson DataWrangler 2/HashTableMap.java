import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//--== CS400 File Header Information ==--
//Name: Nathaniel Nicholson
//Email: nnicholson2@wisc.edu
//Team: GD
//Role: Data Wrangler 2
//TA: Dan
//Lecturer: Gary Dahl
//Notes to Grader: My HashTableMap

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private int size = 0;
  private LinkedList<Object>[] list;

  /**
   * Constructor for the HashTableMap Class
   * 
   * @param capacity - array of hash keys and values
   */
  public HashTableMap(int capacity) {
    list = new LinkedList[capacity];
  }

  /**
   * Default constructor for the class
   */
  public HashTableMap() {
    list = new LinkedList[10];
  }

  /**
   * Private helper method to get the index from the hashcode of a key object
   * 
   * @param key - the key object passed from the parent method
   */
  private int getIndex(KeyType key) {
    return Math.abs(key.hashCode()) % list.length;
  }

  /**
   * Method responsible for the addition of elements into the hash table
   * 
   * @param key   - the key to be hashed
   * @param value - the value associated with it's respective key
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // if (size + 1 >= list.length * 0.8) {
    // growTable();
    // }

    int index = getIndex(key);

    // System.out.println(index);

    if (list[index] != null && list[index].equals(key)) { // class cast exception here??
      return false;
    }

    // Check if table at capacity

    LinkedList listNode = new LinkedList(); // create linked list
    listNode.add(key);
    // System.out.println(key); // debug
    listNode.add(value);
    // System.out.println(value); // debug

    if (list[index] == null) {
      list[index] = listNode;
    } else {
      list[index].add(key);
      list[index].add(value);
    }

    size++; // increment size

    // System.out.println("Added a LinkedList to array" + list[index]);
    return true;


  }

  /**
   * Method responsible for doubling the size of the hash table once it has reached capacity
   * 
   * @return True if table was successfully updated
   */
  private boolean growTable() {
    // Check if the table has reached it's capacity

    LinkedList<Object>[] tempList = list; // copy old map

    // System.out.println(list.length);
    // System.out.println(tempList.length);

    list = new LinkedList[list.length * 2]; // Create new list twice the size

    // System.out.println(list.length);

    for (int i = 0; i < tempList.length; i++) { // rehash
      if (tempList[i] != null) {
        for (int k = 0; k < tempList[i].size() - 2; k = k + 2) {
          put((KeyType) tempList[i].getFirst(), (ValueType) tempList[i].get(k + 1));
        }
      }
    }
    return true;
  }

  /**
   * Method responsible for getting a value associated with a key
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    ValueType needed = null;

    int index = getIndex(key); // find index

    if (list[index] == null) { // if key is invalid
      throw new NoSuchElementException("Error 404, key not associated with a list index");
    }

    // System.out.println(list[index]);

    if (list[index].getFirst() == key) { // checks that key matches value at the index
      // System.out.println(list[index].get(1));
      needed = (ValueType) list[index].get(1);
      return needed;
    } else {
      for (int i = 0; i < list[index].size(); i++) {
        if (list[index].get(i).equals(key)) {
          // System.out.println(list[index].get(i + 1));
          needed = (ValueType) list[index].get(i + 1);
          return needed;
        }
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
    int index = Math.abs(key.hashCode() % list.length);
    for (int i = 0; i < list[index].size(); i++) {
      if (key.equals(list[index].get(i))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public ValueType remove(KeyType key) {
    int index = getIndex(key);
    ValueType removed = null;

    if (list[index] == null) {
      System.out.println("no element at that index");
      return null;
    }

    if (list[index].getFirst() == key) {
      // System.out.println(list[index].get(1));
      // System.out.println("removed" + list[index]);
      removed = (ValueType) list[index].get(1);
      list[index] = null;

      size--; // decrement size

      return removed;
    } else {
      for (int i = 0; i < list[index].size(); i++) {
        if (list[index].get(i).equals(key)) {
          // System.out.println(list[index].get(i + 1));
          removed = (ValueType) list[index].get(i + 1);
          size--;
          return removed;
        }
      }
    }

    return null;
  }


  @Override
  public void clear() {
    new HashTableMap();

    size = 0;
  }

  /**
   * Method responsible for drawing from and compiling data from a file. Uses an array list
   * to store values then converts the array list to a String array before returning it.
   * 
   * 
   */
  public String[] getDataFromFile(File fileName) throws FileNotFoundException {
    Scanner getInput = null;

    try {
      getInput = new Scanner(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("File cannot be located or does not exist. Check spelling and try again");
    }
    
    ArrayList<String> fileValues = new ArrayList<String>();
    while (getInput.hasNextLine()) {
      fileValues.add(getInput.nextLine());
    }
    String[] completedData = fileValues.toArray(new String[] {});

    getInput.close();

    return completedData;
  }

  /**
   *
   */
  public String[] compileData(int index, String[] movieArray, String[] yearArray,
      String[] ratingArray, String[] genreArray) {
    ArrayList<String> movieData = new ArrayList<String>();
    movieData.add(movieArray[index]);
    movieData.add(yearArray[index]);
    movieData.add(ratingArray[index]);
    movieData.add(genreArray[index]);
    String[] movieDataArray = movieData.toArray(new String[] {}); //convert data to an array
    return movieDataArray;
  }
}