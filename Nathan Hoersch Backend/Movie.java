// author Nathan Hoersch

public class Movie {
    private Object movieTitle;
    private Object year;
    private Object rating;
    private Object genre;

    public Movie(Object movieTitle, Object year, Object rating, Object genre) {
        this.movieTitle = movieTitle;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    public Object getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(Object movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Object getYear() {
        return year;
    }

    public void setYear(Object year) {
        this.year = year;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Object getGenre() {
        return genre;
    }

    public void setGenre(Object genre) {
        this.genre = genre;
    }
}
