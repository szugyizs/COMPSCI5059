package project;

import java.util.Scanner;

import project.controllers.Controller;
import project.requests.course.SkillType;
import project.storage.Storage;
import project.storage.FileStorage;

public class Main 
{
	
	private Scanner scanner;
	
	private Storage storage;
	private Controller controller;
	
	public Main()
	{
		scanner = new Scanner(System.in);
		storage = new FileStorage("");
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
		
		// java program_name [you need to log in]
		// Java program_name admin
		// Java program name pttdirector
		// java program_name coursedirector name
	}
	
	public static void main(String... args)
	{		
		Main main = new Main();
		main.startParsing(args);
	}
	
}
