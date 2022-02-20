package view;

import utils.MovieUtils;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomeScreen extends JFrame implements ActionListener
{

	JTextField searchBar = new JTextField();
	JButton searchButton = new JButton("Search");
	JButton clearButton = new JButton("Clean");
	private String lastMovie;

	public HomeScreen(){
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initializeLayout();
	}

	public HomeScreen(String lastMovie){
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initializeLayout();
		this.lastMovie = lastMovie;
	}

	private void initializeLayout()
	{
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		searchBar.setAlignmentX(LEFT_ALIGNMENT);
		searchBar.setMaximumSize(new Dimension(700,25));

		searchButton.setAlignmentX(LEFT_ALIGNMENT);
		searchButton.setSize(new Dimension(50,50));

		clearButton.setAlignmentX(LEFT_ALIGNMENT);
		clearButton.setSize(new Dimension(50,50));

		add(searchBar);
		add(searchButton);
		add(clearButton);

		searchButton.setActionCommand("Search");
		clearButton.setActionCommand("Clear");

		clearButton.addActionListener(this);
		searchButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getActionCommand().equalsIgnoreCase("Search"))
			{
				setVisible(false);
				new MovieScreen(MovieUtils.convertMovieTitle(searchBar.getText()));
			}
			else if (e.getActionCommand().equalsIgnoreCase("Clear"))
			{
				searchBar.setText("");
				searchBar.requestFocus();
			}
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
}