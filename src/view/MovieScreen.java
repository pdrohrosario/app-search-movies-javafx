package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import movie.Movie;
import request.OmdbRequest;

public class MovieScreen extends JFrame implements ActionListener
{

	JLabel titleMovie = new JLabel("");
	JLabel sinopseMovie = new JLabel("");
	JLabel releasedMovie = new JLabel("");
	JButton backButton = new JButton("Back to Home");
	private String searchMovie;

	public MovieScreen(String searchMovie){
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.searchMovie = searchMovie;
		initializeLayout();
	}

	private void initializeLayout()
	{
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButton.setMaximumSize(new Dimension(700,25));

		add(titleMovie);
		add(sinopseMovie);
		add(releasedMovie);
		add(backButton);

		backButton.setActionCommand("Back to Home");
		backButton.addActionListener(this);

//		OmdbRequest.searchMovieByTitle(this.searchMovie);
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getActionCommand().equalsIgnoreCase("Back to Home"))
			{
				String nameMovie = titleMovie.getText();
				setVisible(false);
				new HomeScreen(nameMovie);
			}
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}

	public void infoMovieLoaded(Movie movie){
		titleMovie.setText(movie.title);
		sinopseMovie.setText(movie.sinopse);
		releasedMovie.setText(movie.released);
	}
}
