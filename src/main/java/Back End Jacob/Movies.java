
public class Movies<KeyType, ValueType> {
  
  private static Object[] movieInfo;
  
  public Movies(Object key, Object movie) {
    movieInfo = new Movies[5];
    movieInfo[0] = key;
    
    for (int i = 1; i < movieInfo.length; i++) {
    //  movieInfo[i] = DataWrangler.getMovieData(key, movie); // from the data wrangler
    }
  }
  
  public Object key() {
    return movieInfo[0];
  }
  
  public String getMovieTitle() {
    return (String) movieInfo[1];
  }
  
  public int getRating() {
    return (int) movieInfo[2];
  }
  
  public String getGenre() {
    return (String) movieInfo[3];
  }
  
  
}
