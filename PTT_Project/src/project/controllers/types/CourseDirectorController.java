package project.controllers.types;

import java.io.PrintStream;
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
	public void printHelpMessages() //TODO
	{	
		//Course Director Specific help commands
		printStream.println("\nCourse Director commands:");
		printStream.println("make request <params>- ");
		printStream.println("get status - ");
		
	}
	
	@Override
	public boolean processCommand(final String command, final String... args) //TODO
	{
		
		
		return false;
	}

	@Override
	public void logout() //TODO
	{
		//storage.save();
		//destroy self?
	}
	
}
