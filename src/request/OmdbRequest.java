package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class OmdbRequest
{
	public static final String SEARCH_URL = "http://www.omdbapi.com/?t=TITLE&apikey=648cdebe";

	public OmdbRequest()
	{
	}

	public static void searchMovieByTitle(String searchMovie)
	{
		String requestUrl = SEARCH_URL.replaceAll("TITLE",searchMovie);
		sendGetRequest(requestUrl);
	}

	public static HttpURLConnection connectionOmdb(String urlPassed) throws IOException
	{
		HttpURLConnection connection = null;
		URL url = new URL(urlPassed);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		return connection;
	}

	public static void sendGetRequest(String urlPassed){

		Thread t = new Thread()
		{
			public void run()
			{
				String movieInfoData;

				try
				{
					HttpURLConnection connection = connectionOmdb(urlPassed);
					movieInfoData = takeInfoOfMovieToOmdb(connection);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				//movieInfoDataAllReady(movieInfoData);
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
}