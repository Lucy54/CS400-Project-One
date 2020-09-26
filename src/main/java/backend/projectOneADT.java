package main.java.backend;

import main.java.Movie;

public interface projectOneADT {

    public boolean addToTable(String movieTitle, Movie movie);
    public Movie getMovie(String movieTitle);
    public boolean removeMovie(String movieTitle);

}
