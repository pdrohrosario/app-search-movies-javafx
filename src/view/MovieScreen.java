package view;

import controllers.MovieScreenController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import movie.Movie;
import movie.MovieListener;

public class MovieScreen extends JFrame implements ActionListener, MovieListener
{

	JPanel contentPane;
	JScrollPane scrollPane;
	JLabel titleMovie = new JLabel("");
	JLabel sinopseMovie = new JLabel("");
	JLabel releasedMovie = new JLabel("");
	JButton backButton = new JButton("Back to Home");
	JLabel poster = new JLabel();
	private String searchMovie;

	public MovieScreen(String searchMovie){
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(700,600);
		setVisible(true);
		MovieScreenController.getInstance().setListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.searchMovie = searchMovie;
		initializeLayout();
	}

	private void initializeLayout()
	{
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(700, 600));
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButton.setMaximumSize(new Dimension(700,25));
		poster.setMaximumSize(new Dimension(500,500));
		sinopseMovie.setMaximumSize(new Dimension(700,55));

		add(poster);
		add(titleMovie);
		add(sinopseMovie);
		add(releasedMovie);
		add(backButton);

		backButton.setActionCommand("Back to Home");
		backButton.addActionListener(this);
		MovieScreenController.getInstance().requestData(this.searchMovie);

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

	@Override
	public void infoMovieLoaded(Movie movie)
	{
		titleMovie.setText("Title: \n" + movie.title);
		sinopseMovie.setText("Plot: \n"+ movie.sinopse);
		releasedMovie.setText("Released: \n" + movie.released);
		poster.setIcon(new ImageIcon(movie.poster));
		poster.setSize(200,300);
	}
}
