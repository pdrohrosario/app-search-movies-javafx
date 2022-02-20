package utils;

public class MovieUtils
{
	public static String convertMovieTitle(String titleMovie){
		return titleMovie.replaceAll(" ","+");
	}
}
