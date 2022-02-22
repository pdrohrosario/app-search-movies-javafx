package utils;

import movie.Movie;

public class MovieUtils
{
	public static String convertMovieTitle(String titleMovie)
	{
		return titleMovie.replaceAll(" ", "+");
	}
}