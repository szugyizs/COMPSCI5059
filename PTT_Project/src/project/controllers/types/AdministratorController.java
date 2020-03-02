package project.controllers.types;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import project.controllers.Controller;
import project.requests.course.Course;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public class AdministratorController extends Controller
{

	//TODO - comment this class
	//TODO - review functionality
	private PrintStream printStream;
	private Scanner scanner;
	private Storage storage;

	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;
	
	public AdministratorController(final Storage storage)
	{
		super(storage);
		printStream = System.out;
		scanner = new Scanner(System.in);
		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}
	
	@Override
	public void printHelpMessages() //TODO
	{
		//Administrator Specific help commands
		printStream.println("\nAdministrator commands:");
		printStream.println("get teaching-requests - get list of teaching requests");
		printStream.println("get teachers - get list of teachers and their skills");
		printStream.println("make training-request <status id skillType skillLevel> - ");
		printStream.println("update-skill <teacher_first_name last_name> - updates the skill level of a teacher");
		
	}
	
	@Override
	public boolean processCommand(final String command, final String... args) //TODO
	{
		String[] commandArgs;
		do {
			// Splits the command for further processing.
			commandArgs = command.split(" ");
				
			if (command.equalsIgnoreCase("logout")) {
				logout();
				return true;
			}
			if(command.equalsIgnoreCase("get teachers")) {
				//printStream.println(getListOfTeachers().toString());
				return true;
			}
			if(command.equalsIgnoreCase("get teaching-requests")) {
				//printStream.println(getTeachingRequests());
				return true;
			}
			
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("update-skill")) {
					
			}
		} while (!(command.equalsIgnoreCase("quit")));
		
		return false;
	}

	@Override
	public void logout() //TODO
	{
		//storage.save();
		//destroy self?
	}

	public ListOfCourses getListOfCourses()
	{
		return this.listOfCourses;
	}

	public ListOfTeachers getListOfTeachers()
	{
		return this.listOfTeachers;
	}
	
	public String getTeachingRequests() {
		String out = "";
		LinkedList<Course> requests = getListOfCourses().getCourses();
		ListIterator listIterator = requests.listIterator();
		while(listIterator.hasNext()){ 
			//(listIterator.next())
	        //   out.concat(); 
	        } 
		return out;
	}
}
