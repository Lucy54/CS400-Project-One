
// Nathan Hoersch

package main.java.backend;

import main.java.Movie;

// Class for starting to implement back end functionality
public class MainBackEnd {

    // Pre-defined table capacity of 60 as per project proposal.
    private HashTableMap<String, Movie> hashTable = new HashTableMap<String, Movie>(60);

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

    /*
        getMovie takes in a String called movieTitle and returns a Movie after retrieving it from the hash table.
        If the movie doesn't exist in the hash table, it return null after printing a message.
     */
    public Movie getMovie(String movieTitle) {
        Movie movieToReturn = null;
        if (hashTable.containsKey(movieTitle)) movieToReturn = hashTable.get(movieTitle);

        if (movieToReturn != null) return movieToReturn;

        // at this point the movie would not have been found.
        // could throw an exception here if we decided instead.
        System.out.println("Movie was not found in database.");
        return null;
    }


    /*
        removeMovie takes in a String called movieTitle and returns true if the movie was found and deleted.
        Returns false after printing message if movie was unable ot be deleted.
     */
    public boolean removeMovie(String movieTitle) {
        if (movieTitle != null && hashTable.containsKey(movieTitle)) {
            hashTable.remove(movieTitle);
            return true;
        }
        System.out.println("Movie wasn't found in database. Delete failed.");
        return false;
    }









}