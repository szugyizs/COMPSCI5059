package project.controllers.types;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import project.controllers.Controller;
import project.requests.TrainingRequirement;
import project.requests.course.Course;
import project.requests.course.Qualifications;
import project.requests.course.SkillType;
import project.requests.course.Teacher;
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
	public void printHelpMessages() //TODO review
	{
		//Administrator Specific help commands
		printStream.println("\nAdministrator commands:");
		printStream.println("get teaching-requests - get list of teaching requests");
		printStream.println("get teachers - get list of teachers and their skills");
		printStream.println("make training-request <guid status id skillType skillLevel> - create a training request with the");
		printStream.println("update-skill <guid skillType skillLevel> - updates the skill level of a teacher");
		
	}
	
	@Override
	public boolean processCommand(final String command, final String... args)
	{
		String[] commandArgs;
		do {
			// Splits the command for further processing.
			commandArgs = command.split(" ");
				
			if (command.equalsIgnoreCase("logout")) { //TODO do
				logout();
				return true;
			}
			
			if(command.equalsIgnoreCase("get teachers")) {
				//printStream.println(getListOfTeachers().toString()); //TODO do
				return true;
			}
			
			if(command.equalsIgnoreCase("get teaching-requests")) {
				//printStream.println(getTeachingRequests()); //TODO do
				return true;
			}
			
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("make")) {
				Teacher t = listOfTeachers.getTeacher(args[2]);
				HashMap<SkillType, Short> sk = new HashMap<SkillType, Short>();//args[5], Short.parseShort(args[6])); //TODO do
				Qualifications q = new Qualifications(sk);
				TrainingRequirement tr = new TrainingRequirement(q);
				t.addTrainingRequest(tr);
			}
			
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("update-skill")) {
				Teacher t = listOfTeachers.getTeacher(args[1]);
				//t.setSkill(args[2], Short.parseShort(args[3])); //TODO do
			}
		} while (!(command.equalsIgnoreCase("quit")));
		
		return false;
	}

	@Override
	public void logout() //TODO do
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
	
	public String getTeachingRequests() //TODO do
	{
		String out = "";
		LinkedList<Course> requests = getListOfCourses().getCourses();
		ListIterator listIterator = requests.listIterator();
		while(listIterator.hasNext()){ 
			//out.concat(listIterator.next().getTeachingRequests()); 
	    } 
		return out;
	}
}
