package project.controllers.types;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import project.controllers.Controller;
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
	private FileStorage storage;

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
		printStream.println("get teachers:       get list of teachers and their skills");
		printStream.println("get courses:        get list of courses and their description");
		printStream.println("set <courseID> <ContactType> <teacher>:  adds a teacher to a course request");
		printStream.println("set <teacher> <SkillType>:               adds a training request to a teacher");
		printStream.println("set <teacher> <SkillType> <Level>:       changes the skill level of a teacher");

		printStream.println("");

	}

	@Override
	public boolean processCommand(final String command, final String... args)
	{
		String[] commandArgs;

		// Splits the command for further processing.
		commandArgs = command.split(" ");

		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("get")) {
			if (commandArgs[1].equalsIgnoreCase("teachers")) { // TODO: empty lists throws nullpointer exception
				this.listOfTeachers.print(printStream);
				return true;
			} else if (commandArgs[1].equalsIgnoreCase("courses")) {
				this.listOfCourses.print(printStream);
				return true;
			}
//			else if{
//				
//			}

		}

		if (command.equalsIgnoreCase("get-teachingRequests")) {
			this.listOfCourses.printUnfulfilledCourses(printStream);
			return true;
		}

		if (commandArgs.length == 3 && commandArgs[0].equalsIgnoreCase("set")) {

			Course course = this.listOfCourses.getCourse(commandArgs[1]);
			if (course == null) {
				printStream.println("Course not found");
				return false;
			}

			Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[3]);
			if (teacher == null) {
				printStream.println("Teacher not found");
				return false;
			}

			ContactType type;
			try {
				type = ContactType.valueOf(commandArgs[2]);// TODO: currently needs Try catch
				course.addTeachingStaff(type, teacher);
			} catch (NumberFormatException e) {
				printStream.println("Invalid contact type, use LAB, TUTORIAL, LECTURE.");
				return false;
			}

			if (course.addTeachingStaff(type, teacher) == false) {
				printStream.println("Teacher could not be added. Check requirements and skills.");
			}

			return true;
		}

		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("make")) {
			Teacher t = listOfTeachers.getTeacher(args[2]);
			HashMap<SkillType, Short> sk = new HashMap<SkillType, Short>();// args[5], Short.parseShort(args[6]));
																			// //TODO do
			Qualifications q = new Qualifications(sk);
			TrainingRequirement tr = new TrainingRequirement(q);
			t.addTrainingRequest(tr);
		}

		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("update-skill")) {
			Teacher t = listOfTeachers.getTeacher(args[1]);
			// t.setSkill(args[2], Short.parseShort(args[3])); //TODO do
		}

		return false;
	}

	@Override
	public void logout() // TODO do
	{
		// TODO:
		// storage.save();
		// destroy self?
	}

	public ListOfCourses getListOfCourses()
	{
		return this.listOfCourses;
	}

	public ListOfTeachers getListOfTeachers()
	{
		return this.listOfTeachers;
	}

	public String getTeachingRequests() // TODO do
	{
		String out = "";
		LinkedList<Course> requests = getListOfCourses().getCourses();
		ListIterator listIterator = requests.listIterator();
		while (listIterator.hasNext()) {
			// out.concat(listIterator.next().getTeachingRequests());
		}
		return out;
	}
}
