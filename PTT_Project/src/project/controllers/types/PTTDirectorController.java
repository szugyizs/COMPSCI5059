package project.controllers.types;

import java.io.PrintStream;
import java.util.Scanner;

import project.controllers.Controller;
import project.requests.course.Course;
import project.requests.course.Teacher;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public class PTTDirectorController extends Controller
{

	//TODO - comment this class
	//TODO - review functionality
	
	private Scanner scanner;
	
	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;
	
	public PTTDirectorController(final Storage storage) 
	{
		super(storage, System.out);
		scanner = new Scanner(System.in);
		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}
	
	@Override
	public void printHelpMessages() //TODO
	{
		//PTT Director Specific help commands
		printStream.println("\nPTT Director commands:");
		printStream.println("get teachreqs <courseID>: get list of teaching requests for a course");
		printStream.println("get trainreq <teacherID>: get list of training requests for a teacher");
		printStream.println("set status teachreq <id status>");
		printStream.println("set status trainreq <teacher-firstname teacher-surname id status>");
	}
	
	@Override
	public boolean processCommand(final String command, final String... args) //TODO
	{
		String[] commandArgs;
	
		// Splits the command for further processing.
		commandArgs = command.split(" ");
		
		// check the get commands
		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("get")) {
		
			
			if(commandArgs[1].equalsIgnoreCase("teachreq")) {
				getTeachingRequests(commandArgs[2]);
				return true;
			}
			if(commandArgs[1].equalsIgnoreCase("trainreq")) {
				getTrainingRequests(commandArgs[2]);
				
				return true;
			}
		}
		
		// Check all the set commands
		else if (commandArgs.length >= 4 && commandArgs[0].equalsIgnoreCase("set")) {
			if(commandArgs[2].equalsIgnoreCase("teachreq")) {
				setStatus("");
				return true;
			}
			
			if(commandArgs[2].equalsIgnoreCase("trainreq")) {
				setStatus("");
				return true;
			}
			
			
		}
		
		return false;
	}
	
	public void logout() //TODO
	{
		//storage.save();
		//destroy self?
	}
	
	public void getTeachingRequests(String courseID) {
		Course course = this.listOfCourses.getCourse(courseID);
		course.printTeachingRequests(printStream);
		
		
		
		
	}
	public void getTrainingRequests(String teacherID) {
		
		Teacher teacher = this.listOfTeachers.getTeacher(teacherID);
		teacher.printTrainingRequests(printStream);
		
		
	}
	public void setStatus(String type) {
		
	}
}
