
// Nathan Hoersch

package main.java.backend;

import main.java.Movie;

// Class for starting to implement back end functionality
public class MainBackEnd {

    // Pre-defined table capacity of 60 as per project proposal.
    private HashTableMap hashTable = new HashTableMap(60);

    /*  addToTable will take in a key String which will be the movie title. It will also take in a value Movie which
        will be the movie being stored.
        The method will check for if here is room to add another movie and will print a message and then return false
        if table is full.
        The method will return true, if adding the movie was successful, and false if it failed.
    */
    public boolean addToTable(String movieTitle, Movie movie) {
        int numberOfMovies = hashTable.size();

        if (numberOfMovies == 60) {

            System.out.println("Table has a capacity of 20 records. Table is currently full.\n" +
                    "Please delete a record before adding another.");

            return false;
        }

        return hashTable.put(movieTitle, movie);

    }









}