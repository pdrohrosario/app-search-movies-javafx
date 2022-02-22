package request;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import org.json.JSONObject;


public class OmdbRequest
{
	public static final String SEARCH_URL = "http://www.omdbapi.com/?t=TITLE&apikey=648cdebe";

	private OmdbListener listener;

	public OmdbRequest(){
		// TODO document why this constructor is empty
	}

	public void searchMovieByTitle(String searchMovie)
	{
		if(searchMovie != null)
		{
			String requestUrl = SEARCH_URL.replaceAll("TITLE",searchMovie);
			sendGetRequest(requestUrl);
		}
	}

	public static HttpURLConnection connectionOmdb(String urlPassed) throws IOException
	{
		HttpURLConnection con= null;
		URL url = new URL(urlPassed);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		return con;
	}

	public void sendGetRequest(String urlPassed){

		Thread t = new Thread()
		{
			@Override
			public void run()
			{
				String dataMovie = null;
				Image poster = null;
				try
				{
					HttpURLConnection connection = connectionOmdb(urlPassed);
					dataMovie = takeInfoOfMovieToOmdb(connection);
					poster = takePosterMovieToOmdb(getURLPoster(dataMovie));
					listener.movieDataReady(dataMovie,poster);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};

		t.start();
	}

	public static String takeInfoOfMovieToOmdb(HttpURLConnection connection) throws IOException
	{
		InputStream stream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder responce = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			responce.append(line);
			responce.append("\r");
		}
		reader.close();

		return responce.toString();
	}

	public static Image takePosterMovieToOmdb(String posterUrl) throws IOException
	{
		Image poster = null;
		URL url = new URL(posterUrl);
		poster = ImageIO.read(url);
		return poster;
	}

	public static String getURLPoster(String dataMovie){
		JSONObject js = new JSONObject(dataMovie);
		return js.get("Poster").toString();
	}

	public void setListener(OmdbListener omdb)
	{
		listener = omdb;
	}
}