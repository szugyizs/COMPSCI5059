package project.controllers.types;

import java.io.PrintStream;
import java.util.Scanner;

import project.controllers.Controller;
import project.storage.Storage;

public class PTTDirectorController extends Controller
{

	//TODO - comment this class
	//TODO - review functionality
	
	private Scanner scanner;
	
	public PTTDirectorController(final Storage storage) 
	{
		super(storage, System.out);
		scanner = new Scanner(System.in);
	}
	
	@Override
	public void printHelpMessages() //TODO
	{
		//PTT Director Specific help commands
		printStream.println("\nPTT Director commands:");
		printStream.println("get teaching-requests");
		printStream.println("get training-requests");
		printStream.println("set status of teaching request <id status>");
		printStream.println("set status of training request <teacher-firstname teacher-surname id status>");
	}
	
	@Override
	public boolean processCommand(final String command, final String... args) //TODO
	{
		String[] commandArgs;
	
			// Splits the command for further processing.
		commandArgs = command.split(" ");
			
		if(command.equalsIgnoreCase("get teaching-requests")) {
			//printStream.println(getListOfTeachers().toString());
			return true;
		}
		if(command.equalsIgnoreCase("get training-requests")) {
			//printStream.println(getTeachingRequests());
			return true;
		}
		
		return false;
	}
	
	public void logout() //TODO
	{
		//storage.save();
		//destroy self?
	}
	
	public void getTeachingRequests() {}
	public void getTrainingRequests() {}
	public void setStatus(String type) {
		
	}
}
