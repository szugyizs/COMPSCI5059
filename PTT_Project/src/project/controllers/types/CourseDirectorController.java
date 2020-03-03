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

	public CourseDirectorController(final Storage storage, final PrintStream printStream, final Course course)
	{
		super(storage, printStream);
		this.course = course;
		this.scanner = new Scanner(System.in);
	}

	public CourseDirectorController(final Storage storage, final Course course)
	{
		this(storage, System.out, course);
	}

	public Course getCourse()
	{
		return course;
	}

	@Override
	public void printHelpMessages() // TODO review
	{
		// Course Director Specific help commands
		super.printStream.println("\nCourse Director commands:");
		super.printStream.println("req <contactType numberOfStudents numberOfStaff contactHours>");
		super.printStream.println("    <SkillType level>");
		super.printStream.println("    <SkillType level>");
//		super.printStream.println("show request - view the teaching request and its details");

	}

	@Override
	public boolean processCommand(final String command, final String... args) // TODO
	{
		String[] commandArgs;

		commandArgs = command.split(" ");

		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("req")) { // TODO do
			ContactType type = null;
			int nbrStudents = 0;
			int nbrStaff = 0;
			int cHours = 0;
			Qualifications qualifications;

			try {
				type = ContactType.valueOf(commandArgs[1]);
			} catch (IllegalArgumentException e) {
				super.printStream.println("Invalid contact type, use:");
				// TODO: print all skill types
				return false;
			}

			try {
				nbrStudents = Integer.parseInt(commandArgs[2]); // TODO: negative values?
			} catch (NumberFormatException e) {
				printStream.println("Number of students invalid.");
				return false;
			}

			try {
				nbrStaff = Integer.parseInt(commandArgs[3]); // TODO: negative values?
			} catch (NumberFormatException e) {
				printStream.println("Number of staff invalid.");
				return false;
			}

			try {
				cHours = Integer.parseInt(commandArgs[4]);
			} catch (NumberFormatException e) {
				printStream.println("Contact hours invalid.");
				return false;
			}

			qualifications = super.addQualifications(scanner);
			if (qualifications.isEmpty() == true) {
				printStream.println("Qualifications are empty.");
				return false;
			}
			if (type == null) {
				printStream.println("Type was not set.");
				return false;
			}

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

		if (commandArgs.length == 1 && commandArgs[0].equalsIgnoreCase("show")) {
			course.printTeachingRequests(printStream);
			return true;
		}

		return false;
	}

	@Override
	public void logout() // TODO
	{
		// storage.save();
		// destroy self?
	}

	


}
