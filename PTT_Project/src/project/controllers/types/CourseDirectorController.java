package project.controllers.types;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import project.controllers.Controller;
import project.requests.course.Course;
import project.storage.Storage;

public class CourseDirectorController extends Controller
{

	//TODO - comment this class
	//TODO - review functionality
	private PrintStream printStream;
	private Scanner scanner;
	
	private Course course;
	
	public CourseDirectorController(final Storage storage, final Course course) 
	{
		super(storage);
		this.course = course;
		printStream = System.out;
		scanner = new Scanner(System.in);
	}
	
	public Course getCourse()
	{
		return course;
	}
	
	@Override
	public void printHelpMessages() //TODO review
	{	
		//Course Director Specific help commands
		printStream.println("\nCourse Director commands:");
		printStream.println("add requirement <contactType numberOfStudents numberOfStaff contactHours requiredStaffQualifications> - specify the details of a course requirement and add to the request");
		printStream.println("make request - submit teaching request");
		printStream.println("show request - view the teaching request and its details");
		
	}
	
	@Override
	public boolean processCommand(final String command, final String... args) //TODO
	{
		String[] commandArgs;
		do {
			// Splits the command for further processing.
			commandArgs = command.split(" ");
				
			if (command.equalsIgnoreCase("logout")) { //TODO do
				logout();
				return true;
			}
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("add")) { //TODO do
				addRequirement();
			}
			
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("make")) { //TODO do
				
			}
			
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("show")) {
				printStream.println(course.toString());
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
	
	public void addRequirement() {}
}
