package main;

import view.HomeScreen;

public class Main
{
	public static HomeScreen homeScreen;

	public static void main(String[] args)
	{
		homeScreen = new HomeScreen();
		homeScreen.setVisible(true);
	}
}
