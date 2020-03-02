package project.controllers.types;

import java.io.PrintStream;
import java.util.Scanner;

import project.controllers.Controller;
import project.storage.Storage;

public class PTTDirectorController extends Controller
{

	//TODO - comment this class
	//TODO - review functionality
	private PrintStream printStream;
	private Scanner scanner;
	
	public PTTDirectorController(final Storage storage) 
	{
		super(storage);
		printStream = System.out;
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
