// --== CS400 File Header Information ==--
// Name: Nathan Hoersch
// Email: nhoersch@wisc.edu
// Team: GD
// TA: Dan
// Lecturer: Florian HEIMERL
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.util.Scanner;

public class Start {

    // call methods needed to load data and start front end interface.
    public static void main(String[] args) {

        Loader load = new Loader();
        load.LoadData();
        Commands.main(null);

    }

}
