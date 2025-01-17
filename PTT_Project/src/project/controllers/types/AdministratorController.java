package project.controllers.types;

import java.util.Scanner;

import project.controllers.Controller;
import project.requests.TrainingRequest;
import project.requests.TrainingRequirement;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Qualifications;
import project.requests.course.Teacher;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

/**
 * AdministratorController handles inputs from Administrator class.
 *
 */
public class AdministratorController extends Controller
{
	private Scanner scanner;
	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;

	/**
	 * Constructor explicitly instantiates the instance, informing about the needed
	 * data parameters in the process.
	 * 
	 * @param storage The storage class, that holds the list of teachers and
	 *                courses.
	 */
	public AdministratorController(final Storage storage)
	{
		super(storage, System.out);
		this.scanner = new Scanner(System.in);
		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}

	/**
	 * Prints help messages that describe the available commands.
	 * 
	 */
	@Override
	public void printHelpMessages()
	{
		// Administrator Specific help commands
		super.printStream.println("\nAdministrator commands:");
		super.printStream.println("get req <courseId>: 		get list of teaching requests for a course");
		super.printStream.println("get req <teacher>:  		get list of training requests for a teacher (use GUID)");
		super.printStream.println("get courses:        		get list of courses and their description");
		super.printStream.println("get teachers:       		get list of teachers and their qualifications");
		super.printStream.println("get teachers <courseId>: get list of teachers for a a specific course");

		super.printStream.println("set course <courseID> <ContactType> <teacher>:  adds a teacher (use GUID) to a course request");
		super.printStream.println("set req <teacher>:                              adds a training request to a teacher (use GUID)");
		super.printStream.println("set quali <teacher> <ReqID>:                    changes the skill level of a teacher (use GUID) ");

		super.printStream.println("");

	}

	/**
	 * Processes the command sent to the administrator controller instance.
	 * 
	 * @param command A String of the whole command that was sent.
	 * @param args    An array of optional String command arguments.
	 * @return True if the command was processed successfully.
	 */
	@Override
	public boolean processCommand(final String command, final String... args)
	{
		String[] commandArgs;

		// Splits the command for further processing.
		commandArgs = command.split(" ");

		// Check all the get commands
		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("get")) {
	
			// Print list of teachers
			if (commandArgs[1].equalsIgnoreCase("teachers")) {
				
				// If there is a third argument for the specific course id, print all teachers for it.
				if (commandArgs.length >= 3 && commandArgs[2] != null && !commandArgs[2].isEmpty()) {
					
					// Get the course for which the teachers should be listed.
					final Course course = listOfCourses.getCourse(commandArgs[2]);
					if (course == null) {
						super.printStream.println("Course not found, use a valid course ID");
						return true;
					}
					
					// Print the list of teachers for the course.
					course.printTeachers(super.printStream);
					return true;
				} 
				
				// Simply list all of the teachers.
				this.listOfTeachers.printTeachers(super.printStream);
				return true;
			}
			
			// Print list of courses
			else if (commandArgs[1].equalsIgnoreCase("courses")) {
				this.listOfCourses.print(super.printStream);
				return true;
			}
			
			// Print request for a specific teacher or course
			else if (commandArgs.length == 3 && commandArgs[1].equalsIgnoreCase("req")) {

				// Try to find both teacher or course with the specified ID
				Course course = this.listOfCourses.getCourse(commandArgs[2]);
				Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[2]);

				if (teacher == null && course == null) {
					super.printStream.println("No course or teacher not found");
					return false;
				} else if (course != null) {
					course.printTeachingRequests(super.printStream);
					return true;
				} else if (teacher != null) {
					teacher.printTrainingRequests(super.printStream);
					return true;
				}
			}

		}
		// Check all the set commands
		else if (commandArgs.length >= 3 && commandArgs[0].equalsIgnoreCase("set")) {

			// Try to add a teacher to a course
			if (commandArgs[1].equalsIgnoreCase("course")) {
				return attemptAddTeacher(commandArgs);
			}
			// Try to add a training request to a teacher
			else if (commandArgs[1].equalsIgnoreCase("req")) {
				return attemptAddTrainingReq(commandArgs);
			}
			// Try to update a teacher qualification
			else if (commandArgs[1].equalsIgnoreCase("quali")) {
				return attemptUpdateQulai(commandArgs);
			}

		}

		return false;

	}

	/**
	 * Print logout message and save changes.
	 */
	@Override
	public void logout()
	{
		super.printStream.println("Administrator logged out.");
	}

	/**
	 * Try to add a teacher to a course
	 * 
	 * @param commandArgs The entered command arguments.
	 * @return True if the teacher was added successfully.
	 */
	private boolean attemptAddTeacher(String[] commandArgs)
	{
		if (commandArgs.length != 5) {
			super.printStream.println("Invalid number of arguments.");
			return false;
		} else {
			// Find the specified course
			Course course = this.listOfCourses.getCourse(commandArgs[2]);
			if (course == null) {
				super.printStream.println("Course not found, use course ID");
				return false;
			}

			// Find the specified teacher
			Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[4]);
			if (teacher == null) {
				super.printStream.println("Teacher not found, use GUID");
				return false;
			}

			// Try catch the contact type
			ContactType type = null;
			try {
				type = ContactType.valueOf(commandArgs[3]);
			} catch (IllegalArgumentException e) {
				super.printStream.println("Invalid contact type, use:");
				ContactType.printContactTypes(super.printStream);
				return false;
			}

			// Try to add the teacher, check requirements 
			if (course.addTeachingStaff(type, teacher) == false) {
				super.printStream.println("Teacher could not be added. Check requirements and skills.");
			} else {

				super.printStream.println("Teacher successfully added to course.");
				course.printCourse(super.printStream);
			}

		}

		return true;
	}
	
	/**
	 * Try to add a training requirement to a teacher
	 * 
	 * @param commandArgs The entered command arguments.
	 * @return True if the training request was added successfully.
	 */
	private boolean attemptAddTrainingReq(String[] commandArgs)
	{
		if (commandArgs.length != 3) {
			printStream.println("Invalid number of arguments.");
			return false;
		} else {

			// Find the specified teacher
			Teacher teacher = this.listOfTeachers.getTeacher(commandArgs[2]);
			if (teacher == null) {
				super.printStream.println("Teacher not found, use GUID");
				return false;
			}

			// Qualifications can be added line by line by specifying 
			// <SkillType> <level> (level valid from 0-5)
			Qualifications qualifications = super.addQualifications(scanner);

			if (qualifications.isEmpty() == true) {
				super.printStream.println("Qualifications are empty.");
				return false;
			}

			// Try to produce a training requirement.
			TrainingRequirement trainingRequirement = new TrainingRequirement(qualifications);
			TrainingRequest trainingRequest = teacher.addTrainingRequest(trainingRequirement);
			if (trainingRequest == null) {
				super.printStream.println("Training request could not be added.");
				return false;
			} else {

				super.printStream.println("Training request  successfully added to teacher.");
				trainingRequest.printRequest(super.printStream);
			}

		}

		return true;
	}
	
	/**
	 * Try to update a teachers qualifications
	 * 
	 * @param commandArgs The entered command arguments.
	 * @return True if the qualifications were updated successfully.
	 */
	private boolean attemptUpdateQulai(String[] commandArgs)
	{
		if (commandArgs.length != 4) {
			super.printStream.println("Invalid number of arguments.");
			return false;
		} else {
			super.printStream.println("Updating of teacher qualifications currently not supported");
		}

		return true;
	}
}
