package project.controllers.types;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import project.controllers.Controller;
import project.requests.CourseRequirement;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Qualifications;
import project.requests.course.SkillType;
import project.storage.Storage;

//TODO: add super before every printStream
public class CourseDirectorController extends Controller
{

	// TODO - comment this class
	// TODO - review functionality
	private Scanner scanner;
	private Course course;

	/**
	 * Constructor explicitly instantiates the instance, informing about the needed
	 * data parameters in the process.
	 * 
	 * @param storage     The storage instance, that holds the list of teachers and
	 *                    courses.
	 * @param printStream An printStream to output data to.
	 * @param course      The course instance that is handled by this controller.
	 */
	public CourseDirectorController(final Storage storage, final PrintStream printStream, final Course course)
	{
		super(storage, printStream);
		this.course = course;
		this.scanner = new Scanner(System.in);
	}
	/**
	 * Constructor explicitly instantiates the instance, informing about the needed
	 * data parameters in the process.
	 * 
	 * @param storage     The storage instance, that holds the list of teachers and
	 *                    courses.
	 * @param course      The course instance that is handled by this controller.
	 */
	public CourseDirectorController(final Storage storage, final Course course)
	{
		this(storage, System.out, course);
	}

	/**
	 * Getter for the course instance that the controller handles.
	 * 
	 * @return The course instance.
	 */
	public Course getCourse()
	{
		return course;
	}

	/**
	 * Prints help messages that describe the available commands.
	 * 
	 */
	@Override
	public void printHelpMessages()
	{
		// Course Director Specific help commands
		super.printStream.println("\nCourse Director commands:");
		super.printStream.println("req <contactType> <numberOfStudents> <numberOfStaff> <contactHours>: ");
		super.printStream.println("show:   view the teaching request and its details");

	}

	/**
	 * Processes the command sent to the course director controller instance.
	 * 
	 * @param command A String of the whole command that was sent.
	 * @param args    An array of optional String command arguments.
	 * @return True if the command was processed successfully.
	 */
	@Override
	public boolean processCommand(final String command, final String... args)
	{
		String[] commandArgs;

		commandArgs = command.split(" ");
		
		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("req")) {
			ContactType type = null;
			int nbrStudents = 0;
			int nbrStaff = 0;
			int cHours = 0;
			Qualifications qualifications;

			// Try catch to parse ContactType
			try {
				type = ContactType.valueOf(commandArgs[1]);
			} catch (IllegalArgumentException e) {
				super.printStream.println("Invalid contact type, use:");
				ContactType.printContactTypes(printStream);
				return false;
			}

			// Try catch to parse Integer for number of students
			try {
				nbrStudents = Integer.parseInt(commandArgs[2]);
			} catch (NumberFormatException e) {
				printStream.println("Number of students invalid.");
				return false;
			}

			// Try catch to parse Integer for number of staff
			try {
				nbrStaff = Integer.parseInt(commandArgs[3]);
			} catch (NumberFormatException e) {
				printStream.println("Number of staff invalid.");
				return false;
			}

			// Try catch to parse Integer for contact hours
			try {
				cHours = Integer.parseInt(commandArgs[4]);
			} catch (NumberFormatException e) {
				printStream.println("Contact hours invalid.");
				return false;
			}

			// Qualifications can be added line by line by specifying 
			// <SkillType> <level> (level valid from 0-5)
			qualifications = super.addQualifications(scanner);
			if (qualifications.isEmpty() == true) {
				printStream.println("Qualifications are empty.");
				return false;
			}
			if (type == null) {
				printStream.println("Type was not set.");
				return false;
			}

			// Try to produce a course requirement.
			CourseRequirement courseRequirement = new CourseRequirement(type, nbrStudents, nbrStaff, cHours,
					qualifications);
			if (course.addCourseRequirement(courseRequirement) == false) {
				printStream.println("Course requirements not set.");
				return false;
			}

			printStream.println("Course requirements set successfully.");
			course.printTeachingRequests(printStream);
			return true;
		}

		// show course requirements for this course
		if (commandArgs.length == 1 && commandArgs[0].equalsIgnoreCase("show")) {
			course.printTeachingRequests(printStream);
			return true;
		}

		return false;
	}

	/**
	 * Print logout message and save changes.
	 */
	@Override
	public void logout()
	{
		// storage.save(); //TODO
		printStream.println("Course Director logged out.");
	}

}
