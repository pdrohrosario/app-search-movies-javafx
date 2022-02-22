package view;

import static utils.MovieUtils.configurateWarning;

import java.awt.*;
import utils.MovieUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomeScreen extends JFrame implements ActionListener
{

	JTextField searchBar = new JTextField();
	JButton searchButton = new JButton("");
	JButton clearButton = new JButton("");
	JLabel warning = new JLabel("");

	public HomeScreen()
	{
		setTitle("IMovie - Encontre o filme que você gosta.");
		setSize(1100, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initializeLayout();
	}

	private void initializeLayout()
	{
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		jp.setAlignmentX(Component.LEFT_ALIGNMENT);
		jp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		searchBar.setAlignmentX(LEFT_ALIGNMENT);
		searchBar.setMaximumSize(new Dimension(1100, 105));

		searchButton.setText("Search");
		searchButton.setAlignmentX(LEFT_ALIGNMENT);
		searchButton.setSize(new Dimension(50, 50));

		clearButton.setText("Clear");
		clearButton.setAlignmentX(LEFT_ALIGNMENT);
		clearButton.setSize(new Dimension(50, 50));

		warning = configurateWarning("Digite o nome de um filme !!",30, LEFT_ALIGNMENT);
		warning.setVisible(false);

		jp.add(searchButton);
		jp.add(clearButton);

		add(searchBar);
		add(warning);
		add(jp);

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
				if (!searchBar.getText().isEmpty())
				{
					setVisible(false);
					warning.setVisible(false);
					new MovieScreen(MovieUtils.convertMovieTitle(searchBar.getText()));
				}
				else
				{
					warning.setText("Digite o nome de um filme para fazer a busca !!");
					warning.setVisible(true);
				}
			}
			else if (e.getActionCommand().equalsIgnoreCase("Clear"))
			{
				searchBar.setText("");
				warning.setVisible(false);
				searchBar.requestFocus();
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
}
