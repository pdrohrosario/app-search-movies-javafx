package request;

import java.awt.Image;

public interface OmdbListener
{
	void movieDataReady(String movieData, Image poster);
}
