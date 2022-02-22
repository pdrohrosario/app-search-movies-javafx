package utils;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.LEFT_ALIGNMENT;

import java.awt.Font;
import javax.swing.JLabel;
import movie.Movie;

public class MovieUtils
{
	public static String convertMovieTitle(String titleMovie)
	{
		return titleMovie.replaceAll(" ", "+");
	}

	public static JLabel configurateWarning(String text, Integer fontSize, Float align){
		JLabel warning = new JLabel();
		warning.setAlignmentX(align);
		warning.setText(text);
		warning.setFont(new Font("Serif", Font.BOLD, fontSize));
		warning.setAlignmentY(CENTER_ALIGNMENT);
		return warning;
	}
}