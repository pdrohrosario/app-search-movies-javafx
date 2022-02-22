package controllers;

import java.awt.Image;
import movie.Movie;
import movie.MovieListener;
import org.json.JSONObject;
import request.OmdbListener;
import request.OmdbRequest;
import utils.MovieUtils;

public class MovieScreenController implements OmdbListener
{
	private static MovieScreenController instance;
	private MovieListener listener;
	private OmdbRequest omdb;

	public MovieScreenController(){
		omdb = new OmdbRequest();
		omdb.setListener(this);
	}
	
	public static MovieScreenController getInstance()
	{
		if(instance == null){
			instance = new MovieScreenController();
		}
		return instance;
	}

	public void requestData(String inputText){
		inputText = MovieUtils.convertMovieTitle(inputText);
		omdb.searchMovieByTitle(inputText);
	}

	public void setListener(MovieListener msc)
	{
		listener =  msc;
	}

	@Override
	public void movieDataReady(String movieData, Image poster)
	{
		JSONObject responseMovie = new JSONObject(movieData);
		String movieTitle = responseMovie.get("Title").toString();
		String sinopse = responseMovie.get("Plot").toString();
		String released = responseMovie.get("Released").toString();
		Movie movie = new Movie(movieTitle, sinopse,released, poster);
		listener.infoMovieLoaded(movie);
	}
}
