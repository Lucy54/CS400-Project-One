package main.java;

public class Start {

    // call methods needed to load data and start front end interface.
    public static void main(String[] args) {

        Loader load = new Loader();
        load.LoadData();
        main.java.frontend.Commands.main(null);

    }

}
