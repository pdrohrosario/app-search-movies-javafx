package view;

import static utils.MovieUtils.configurateWarning;

import controllers.MovieScreenController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import movie.Movie;
import movie.MovieListener;

public class MovieScreen extends JFrame implements ActionListener, MovieListener
{

	JLabel titleMovie = new JLabel("");
	JLabel sinopseMovie = new JLabel("");
	JLabel releasedMovie = new JLabel("");
	JButton backButton = new JButton("");
	JLabel poster = new JLabel();
	JLabel warning = new JLabel("");
	private String searchMovie;

	public MovieScreen(String searchMovie)
	{
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(1500, 700);
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

		poster.setMaximumSize(new Dimension(1400, 500));
		poster.setAlignmentX(LEFT_ALIGNMENT);
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.setText("Back to Home");

		warning = configurateWarning("Loading",45,CENTER_ALIGNMENT);

		jp.add(backButton);

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
				new HomeScreen();
				setVisible(false);

			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Override
	public void infoMovieLoaded(Movie movie)
	{

		if (movie.title != null){
			remove(warning);
			titleMovie.setText("TITLE:\n\n" + movie.title);
			titleMovie.setFont(new Font("arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
			sinopseMovie.setText("PLOT:\n\n" + movie.sinopse);
			sinopseMovie.setFont(new Font("arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
			releasedMovie.setText("REALEASED: \n" + movie.released);
			releasedMovie.setFont(new Font("arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
			poster.setIcon(new ImageIcon(movie.poster));
			poster.setSize(200, 300);
		}
		else{
			warning.setText("Filme não encontrado");
		}
	}
}
