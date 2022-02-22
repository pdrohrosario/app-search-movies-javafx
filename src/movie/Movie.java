package movie;

import java.awt.Image;

public class Movie {
	public String title;
	public String sinopse;
	public String released;
	public Image poster;

	public Movie(String title, String sinopse, String released, Image poster)
	{
		this.title = title;
		this.sinopse = sinopse;
		this.released = released;
		this.poster = poster;
	}

	public Movie(){
	}
}