package view;

import controllers.MovieScreenController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import movie.Movie;
import movie.MovieListener;

public class MovieScreen extends JFrame implements ActionListener, MovieListener
{

	JLabel titleMovie = new JLabel("");
	JLabel sinopseMovie = new JLabel("");
	JLabel releasedMovie = new JLabel("");
	JButton backButton = new JButton("Back to Home");
	JLabel poster = new JLabel();
	JLabel warning = new JLabel("");
	private String searchMovie;

	public MovieScreen(String searchMovie){
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(1100,600);
		setVisible(true);
		MovieScreenController.getInstance().setListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.searchMovie = searchMovie;
		initializeLayout();
	}

	private void initializeLayout()
	{
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		jp.setAlignmentX(Component.CENTER_ALIGNMENT);
		jp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		poster.setMaximumSize(new Dimension(1400,500));
		poster.setAlignmentX(LEFT_ALIGNMENT);
		sinopseMovie.setMaximumSize(new Dimension(1100,55));
		backButton.setAlignmentX(CENTER_ALIGNMENT);

		jp.add(backButton);

		warning.setAlignmentX(CENTER_ALIGNMENT);
		warning.setText("=Loading=");
		warning.setFont(new Font("Serif",Font.BOLD,45));
		warning.setAlignmentY(CENTER_ALIGNMENT);

		add(warning);
		add(poster);
		add(titleMovie);
		add(sinopseMovie);
		add(releasedMovie);
		add(jp);

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
		remove(warning);
		titleMovie.setText("Title: \n" + movie.title);
		sinopseMovie.setText("Plot: \n"+ movie.sinopse);
		releasedMovie.setText("Released: \n" + movie.released);
		poster.setIcon(new ImageIcon(movie.poster));
		poster.setSize(200,300);
	}
}
