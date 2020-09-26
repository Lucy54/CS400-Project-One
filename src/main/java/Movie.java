package main.java;

public class Movie {
    private String movieTitle;
    private int year;
    private int rating;
    private String genre;

    public Movie(String movieTitle, int year, int rating, String genre) {
        this.movieTitle = movieTitle;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
