package main.java.backend;// --== CS400 File Header Information ==--
// Name: Nathan Hoersch
// Email: nhoersch@wisc.edu
// Team: GD
// TA: Dan
// Lecturer: Florian HEIMERL
// Notes to Grader: <optional extra notes>
import java.lang.Math;
import java.util.NoSuchElementException;


public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    private int capacity;
    private HashTableMapNode<KeyType, ValueType>[] hashTable;

    // Constructor
    public HashTableMap(int capacity) {
        this.capacity = capacity;
        hashTable = new HashTableMapNode[capacity];
    }

    // Constructor - default capacity = 10
    public HashTableMap() {
        this(10);
    }

    @Override
    public boolean put(KeyType key, ValueType value) {
        if(resizeNeeded()) resize();
        int index = getHashIndex(key);
        HashTableMapNode<KeyType, ValueType> node = hashTable[index];
        if (node == null) {
            hashTable[index] =  new HashTableMapNode<>(key, value);
            return true;
        }
        HashTableMapNode<KeyType, ValueType> lastNode;
        if (!containsKey(key)){
            lastNode = findLastNodeInList(index, hashTable);
            lastNode.setNext(new HashTableMapNode<>(key, value));
            return true;
        }
        return false; // put failed.
    }

    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        int index = getHashIndex(key);
        HashTableMapNode<KeyType, ValueType> currentNode = hashTable[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) return currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        int count = 0;
        HashTableMapNode<KeyType, ValueType> currentNode;
        for (int i = 0; i < hashTable.length; i++) {
            currentNode = hashTable[i];
            while (currentNode != null) {
                count++;
                currentNode = currentNode.getNext();
            }
        }
        return count;
    }

    @Override
    public boolean containsKey(KeyType key) {
        int index = getHashIndex(key);
        if (hashTable[index] == null) { return false; }
        HashTableMapNode<KeyType, ValueType> currentNode = hashTable[index];
        while (currentNode.getNext() != null) {
            if(currentNode.getKey().equals(key)) return true;
            else currentNode = currentNode.getNext();
        }
        if(currentNode.getKey().equals(key)) return true; // while loop checks all but last
        return false;
    }

    @Override
    public ValueType remove(KeyType key) {
        if (!containsKey(key)) return null;
        int index = getHashIndex(key);
        HashTableMapNode<KeyType, ValueType> currentNode = hashTable[index];
        HashTableMapNode<KeyType, ValueType> removedNode = null;
        HashTableMapNode<KeyType, ValueType> previousNode = null;

        if (!currentNode.hasNext()) {
            removedNode = currentNode;
            hashTable[index] = null;
        }
        else {
            boolean removed = false;
            while (!removed) {
                if (currentNode.getKey().equals(key)) {
                    removedNode = currentNode;
                    // remove key
                    if(currentNode.hasNext()) {
                        previousNode.setNext(currentNode.getNext());
                    }
                    else previousNode.setNext(null);
                    removed = true;
                }
                else {
                    previousNode = currentNode;
                    currentNode = currentNode.getNext();
                }
            }
        }
        return removedNode.getValue();
    }


    @Override
    public void clear() {
        hashTable = new HashTableMapNode[capacity];
    }

    public int getHashIndex(KeyType key) {
        return (Math.abs(key.hashCode()) % this.capacity);
    }

    private HashTableMapNode<KeyType, ValueType> findLastNodeInList(int index, HashTableMapNode<KeyType, ValueType>[] table) {
        HashTableMapNode<KeyType, ValueType> currentNode = table[index];
        //if(currentNode == null) return currentNode;
        while (currentNode.hasNext()) {
            if (currentNode.hasNext()) currentNode = currentNode.getNext();
            else break;
        }
        return currentNode;
    }

    private boolean resizeNeeded() {
        if (((this.size() + 1.0) / this.capacity) >= 0.80 ) return true;
        return false;
    }

    private void resize() {
        HashTableMapNode<KeyType, ValueType>[] currentHashTable = hashTable;
        this.capacity = 2 * this.capacity;
        HashTableMapNode<KeyType, ValueType>[] newHashTable = new HashTableMapNode[this.capacity];
        HashTableMapNode<KeyType, ValueType> currentNode;
        HashTableMapNode<KeyType, ValueType> lastNode;
        int index;
        // rehash everything into the table.
        for(int i=0; i < currentHashTable.length; i++) {
            currentNode = currentHashTable[i];
            while(currentNode != null) { // add current node
                 index = getHashIndex(currentNode.getKey());
                 if (newHashTable[index] == null) newHashTable[index] = new HashTableMapNode<>(currentNode.getKey(), currentNode.getValue());
                 else {
                     lastNode = findLastNodeInList(index, newHashTable);
                     lastNode.setNext(new HashTableMapNode<>(currentNode.getKey(), currentNode.getValue()));
                 }
                 currentNode = currentNode.getNext();
            }
        }
        hashTable = newHashTable;
    }


}
