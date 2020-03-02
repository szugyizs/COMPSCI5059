package project;

import java.io.PrintStream;
import java.util.Scanner;

import project.controllers.Controller;
import project.controllers.types.AdministratorController;
import project.controllers.types.CourseDirectorController;
import project.controllers.types.PTTDirectorController;
import project.requests.CourseRequirement;
import project.requests.TeachingRequest;
import project.requests.TrainingRequest;
import project.requests.TrainingRequirement;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Qualifications;
import project.requests.course.SkillType;
import project.requests.course.Teacher;
import project.storage.Storage;
import project.storage.FileStorage;

public class Main 
{

	//TODO - comment this class
	//TODO - review functionality
	private PrintStream printStream;
	private Scanner scanner;
	
	private FileStorage storage;
	private Controller controller;
	
	public Main()
	{
		printStream = System.out;
		scanner = new Scanner(System.in);
		
		storage = new FileStorage("");
	}
	
	public PrintStream getPrintStream()
	{
		return printStream;
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
		String command = "help";
		String[] commandArgs;
		do {
			
			// Splits the command for further processing.
			commandArgs = command.split(" ");
			
			// Always print help as the first command.
			if (command.equalsIgnoreCase("help")){
				printHelp();
				continue;
			}
			
			// If the controller is not equal to null, then attempt to process the command.
			if (controller != null) {
				if (!controller.processCommand(command, commandArgs)) {
					printStream.println(String.format("Failed to process command \"%s\"; type \"help\"", command));
					continue;
				}
			}

			
			// If the command is logout, logout of the existing controller.
			else if (command.equalsIgnoreCase("logout")) {
				if (controller == null) {
					printStream.println("In order to logout, you must first be logged in. See \"help\"");
					continue;
				}
				controller.logout();
			}
			
			// The controller is null, therefore process the login code.
			if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("login")) {
				this.controller = attemptLogin(command, commandArgs);
				if (controller != null) {
					printHelp();
					continue;
				}
			}
			
			// We failed to process the command, pass it on.
			printStream.println("Failed to process command; type \"help\" and login as a user!");
		} while (!(command = scanner.nextLine()).equalsIgnoreCase("quit"));
		
		// If we quit and a controller exists, save the storage.
		if (controller != null) {
			controller.logout();
			printStream.println("Successfully logged out! Work saved!");
		}
		printStream.println("Successfully quit the program.");
	}
	
	private void printHelp()
	{
		
		// General help commands.
		printStream.println("General commands:");
		printStream.println("login coursedirector <course id>");
		printStream.println("login pttdirector - login as a PTT director.");
		printStream.println("login admin - login as an administrator.");
		printStream.println("logout - will save all work and logout of the current controller");
		printStream.println("quit - will save and quit all work.");
		
		// Controller specific help commands (if not null).
		if (controller != null) {
			controller.printHelpMessages();
		}
	}
	
	private Controller attemptLogin(final String command, final String[] commandArgs)
	{
		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("login")) {
			if (commandArgs[1].equalsIgnoreCase("admin")) {
				return new AdministratorController(storage);
			} else if (commandArgs[1].equalsIgnoreCase("pttdirector")) {
				
				// Simply 
				return new PTTDirectorController(storage);
			} else if (commandArgs.length == 3 && commandArgs[1].equalsIgnoreCase("coursedirector") && !commandArgs[2].isEmpty()) {
				
				// Attempt to find the course, if it exists, create a new course director for it.
				if( storage.getListOfCourses().getCourses().isEmpty() == true ) {
					printStream.println("No courses specified, login not possible.");
					return null;					
				}

				final Course course = storage.getListOfCourses().getCourse(commandArgs[2]);	
				if (course == null) {
					printStream.println("Specified course not found.");
					return null;
				}		
				return new CourseDirectorController(storage, course);		
			}
		}
		return null;
	}
	
	public static void main(String... args)
	{	
		Main main = new Main();
		//TODO: load file !!!
		//main.storage.load();
		
		main.startParsing(args);
		
		/*
		Qualifications qualifications = new Qualifications();
		qualifications.setSkill(SkillType.ALGORITHMS_AND_DATA_STRUCTURES, (short) 2);
		qualifications.setSkill(SkillType.BIG_DATA, (short) 3);
		qualifications.setSkill(SkillType.CYBER_SECURITY, (short) 2);
		
		TrainingRequirement trainingRequirement = new TrainingRequirement(qualifications);
		TrainingRequest trainingRequest = new TrainingRequest(0, trainingRequirement);
		trainingRequest.printRequest(System.out);
		
		System.out.println();
		
		Teacher teacher = new Teacher("123", "Daniels", "Vasiljevs", qualifications);
		teacher.printTeacher(System.out);
		
		System.out.println();
		teacher.printTrainingRequests(System.out);
		
		System.out.println();
		
		// TODO: We dont need a parameter for contact type because it's defined within course requirement.
		CourseRequirement courseRequirement = new CourseRequirement(ContactType.LAB, 10, 20, 200, qualifications);
		TeachingRequest teachingRequest = new TeachingRequest(courseRequirement);
		teachingRequest.printRequest(System.out);
		
		System.out.println();
		
		Course course = new Course("COMPSCI", "Software Engineering", "Basics of software engineering");
		course.printCourse(System.out);
		
		System.out.println();
		
		course.printTeachingRequests(System.out);
		*/
	}
	
}
