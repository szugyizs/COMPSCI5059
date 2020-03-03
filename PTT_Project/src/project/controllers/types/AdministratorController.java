package project.controllers.types;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import project.controllers.Controller;
import project.requests.CourseRequirement;
import project.requests.TrainingRequest;
import project.requests.TrainingRequirement;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Qualifications;
import project.requests.course.SkillType;
import project.requests.course.Teacher;
import project.storage.FileStorage;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public class AdministratorController extends Controller
{

	// TODO - comment this class
	// TODO - review functionality
	private Scanner scanner;
	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;

	public AdministratorController(final Storage storage)
	{
		super(storage, System.out);
		scanner = new Scanner(System.in);
		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}

	@Override
	public void printHelpMessages() // TODO review
	{
		// Administrator Specific help commands
		printStream.println("\nAdministrator commands:");
		printStream.println("get req <courseId>: get list of teaching requests for a course");
		printStream.println("get req <teacher>:  get list of training requests for a teacher");
		printStream.println("get courses:        get list of courses and their description"); 
		printStream.println("get teachers:       get list of teachers and their qualifications");

		printStream.println("set course <courseID> <ContactType> <teacher>:  adds a teacher to a course request");
		printStream.println("set req <teacher>:                              adds a training request to a teacher");
		printStream.println("set quali <teacher> <ReqID>:                    changes the skill level of a teacher");

		printStream.println("");

	}

	@Override
	public boolean processCommand(final String command, final String... args)
	{
		String[] commandArgs;

		// Splits the command for further processing.
		commandArgs = command.split(" ");

		// Check all the get commands
		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("get")) {
			
			if (commandArgs[1].equalsIgnoreCase("teachers")) {
				this.listOfTeachers.printTeachers(printStream);
				return true;
			} else if (commandArgs[1].equalsIgnoreCase("courses")) { 
				this.listOfCourses.print(printStream);// TODO: empty lists throws nullpointer exception
				return true;
			} else if ( commandArgs.length == 3 && commandArgs[1].equalsIgnoreCase("req")) {

				Course course = this.listOfCourses.getCourse(commandArgs[2]);
				Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[2]);
				if (teacher == null && course == null) {
					printStream.println("No course or teacher not found");
					return false;
				} else if(course != null){
					course.printTeachingRequests(printStream);
					return true;
				} else if(teacher != null){
					teacher.printTrainingRequests(printStream);
					return true;
				}
			}

		}
		// Check all the set commands
		else if (commandArgs.length >= 3 && commandArgs[0].equalsIgnoreCase("set")) {

			if (commandArgs[1].equalsIgnoreCase("course")) {
				return attemptAddTeacher(commandArgs);
			} else if (commandArgs[1].equalsIgnoreCase("req")) {
				return attemptAddTrainingReq(commandArgs);
			} else if (commandArgs[1].equalsIgnoreCase("quali")) {
				
			}

		}

		return false;

	}

	@Override
	public void logout()
	{
		// TODO: storage save?
		printStream.println("Administrator logged out.");
	}

	private boolean attemptAddTeacher(String[] commandArgs)
	{
		if (commandArgs.length != 5) {
			printStream.println("Invalid number of arguments.");
			return false;
		} else {
			Course course = this.listOfCourses.getCourse(commandArgs[2]);
			if (course == null) {
				printStream.println("Course not found, use course ID");
				return false;
			}

			Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[4]);
			if (teacher == null) {
				printStream.println("Teacher not found, use GUID");
				return false;
			}

			ContactType type = null;
			try {
				type = ContactType.valueOf(commandArgs[3]);
				course.addTeachingStaff(type, teacher);
			} catch (IllegalArgumentException e) {
				printStream.println("Invalid contact type, use:");
				ContactType.printContactTypes(super.printStream);
				return false;
			}

			if (course.addTeachingStaff(type, teacher) == false) {
				printStream.println("Teacher could not be added. Check requirements and skills.");
				return false;
			} else {

				printStream.println("Teacher successfully added to course.");
				course.printCourse(printStream);
			}

		}

		return true;
	}

	private boolean attemptAddTrainingReq(String[] commandArgs)
	{
		if (commandArgs.length != 3) {
			printStream.println("Invalid number of arguments.");
			return false;
		} else {

			Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[2]);
			if (teacher == null) {
				printStream.println("Teacher not found, use GUID");
				return false;
			}

			Qualifications qualifications = super.addQualifications(scanner);
			
			if (qualifications.isEmpty() == true) {
				printStream.println("Qualifications are empty.");
				return false;
			}

			TrainingRequirement trainingRequirement = new TrainingRequirement(qualifications);
			TrainingRequest trainingRequest = teacher.addTrainingRequest(trainingRequirement);
			if (trainingRequest == null) {
				printStream.println("Training request could not be added.");
				return false;
			} else {

				printStream.println("Training request  successfully added to teacher.");
				trainingRequest.printRequest(printStream);
			}

		}

		return true;
	}
	
	private boolean attemptUpdateQulai(String[] commandArgs)
	{
		if (commandArgs.length != 4) {
			printStream.println("Invalid number of arguments.");
			return false;
		} else {
			printStream.println("Updating of teacher qualifications currently not supoorted");
		}

		return true;
	}
}
