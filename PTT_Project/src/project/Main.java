package project;

import java.util.Scanner;

import project.controllers.Controller;
import project.storage.Storage;

public class Main 
{
	
	private Scanner scanner;
	
	private Storage storage;
	private Controller controller;
	
	public Main()
	{
		scanner = new Scanner(System.in);
	}
	
	public Scanner getScanner()
	{
		return scanner;
	}
	
	public Storage getStorage()
	{
		return storage;
	}
	
	public Controller getController()
	{
		return controller;
	}
	
	private void startParsing(final String... args)
	{
		
	}
	
	public static void main(String... args)
	{
		Main main = new Main();
		main.startParsing(args);
	}
	
}
