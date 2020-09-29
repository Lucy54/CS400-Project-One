//author Nathan Hoersch


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

    public static MainBackEnd backEnd = new MainBackEnd();
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> genres = new ArrayList<String>();
    private ArrayList<String> years = new ArrayList<String>();
    private ArrayList<String> ratings = new ArrayList<String>();

    public Loader() {

    }

    // Load method to load data into hash table.
    public boolean LoadData() {
        loadTitles();
        loadGenres();
        loadRatings();
        loadYears();
        loadMovies();

//        for (int i=0; i<60; i++) {
//            System.out.println(backEnd.getMovie(titles.get(i)).getMovieTitle());
//        }

        return true;
    }

    private void loadTitles() {
        File movieFile = null;
        try {
            movieFile = new File("movie.txt");
            //System.out.println(movieFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't find file...");
        }
        Scanner scan = null;
        try {
            scan = new Scanner(movieFile);
        } catch (Exception e) {
            System.out.println("Couldn't create scanner.");
        }
        while (scan.hasNext()) {
            titles.add(scan.nextLine());
        }
        scan.close();
    }

    private void loadGenres() {
        File movieFile = null;
        try {
            movieFile = new File("genre.txt");
            //System.out.println(movieFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't find file...");
        }
        Scanner scan = null;
        try {
            scan = new Scanner(movieFile);
        } catch (Exception e) {
            System.out.println("Couldn't create scanner.");
        }
        while (scan.hasNext()) {
            genres.add(scan.nextLine());
        }
        scan.close();
    }

    private void loadYears() {
        File movieFile = null;
        try {
            movieFile = new File("year.txt");
            //System.out.println(movieFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't find file...");
        }
        Scanner scan = null;
        try {
            scan = new Scanner(movieFile);
        } catch (Exception e) {
            System.out.println("Couldn't create scanner.");
        }
        while (scan.hasNext()) {
            years.add(scan.nextLine());
        }
        scan.close();
    }

    private void loadRatings() {
        File movieFile = null;
        try {
            movieFile = new File("ratings.txt");
          //  System.out.println(movieFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't find file...");
        }
        Scanner scan = null;
        try {
            scan = new Scanner(movieFile);
        } catch (Exception e) {
            System.out.println("Couldn't create scanner.");
        }
        while (scan.hasNext()) {
            ratings.add(scan.nextLine());
        }
        scan.close();
    }

    private void loadMovies() {
        Object[] yearArray = years.toArray();
        Object[] titleArray = titles.toArray();
        Object[] ratingsArray = ratings.toArray();
        Object[] genreArray = genres.toArray();

        for (int i=0; i<60; i++) {
            backEnd.addToTable(titleArray[i].toString(), new Movie(titleArray[i], yearArray[i], ratingsArray[i], genreArray[i]));
        }
    }
}
