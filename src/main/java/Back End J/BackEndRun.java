import java.io.File;
import java.util.Scanner;

// Runs the program
public class BackEndRun {
  
  public static void getDataAll() {
    BackEndHashTable table = new BackEndHashTable();
    
    String[] holder = new String[4];
    String input;
   
    try {
      Scanner names = new Scanner(new File("names.txt"));
      Scanner genre = new Scanner(new File("genre.txt"));
      Scanner year = new Scanner(new File("year.txt"));
      Scanner rating = new Scanner(new File("rating.txt"));
      
     Movies movie1 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     //  System.out.print("" + (i + 1) + ". ");
     //  movie.display();
    //  }
       
     Movies movie2 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     Movies movie3 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     Movies movie4 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     Movies movie5 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     Movies movie6 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     Movies movie7 = new Movies(names.nextLine(), genre.nextLine(), year.nextLine(), rating.nextLine());
     
     table.putMovie((Object) 1, movie1);
     table.putMovie((Object) 2, movie2);
     table.putMovie((Object) 3, movie3);
     table.putMovie((Object) 4, movie4);
     table.putMovie((Object) 5, movie5);
     table.putMovie((Object) 6, movie6);
     table.putMovie((Object) 7, movie7);
     
     System.out.println(table.getMovie((Object) 2).get(0).getGenre());
     
    } catch (Exception e) {
      e.printStackTrace();
    }
   
  }
  
  public static void main(String[] args) {
    getDataAll();

  }

}
