package project.controllers.types;

import project.controllers.Controller;
import project.requests.RequestStatusType;
import project.requests.TeachingRequest;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Teacher;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

/**
 * PTTController handles the input from the PPT Director
 */

public class PTTDirectorController extends Controller {

	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;

	/**
	 * Constructor instantiates the CourseRequirement instance.
	 * 
	 * @param storage The storage class, containing the list of teachers and courses
	 */

	public PTTDirectorController(final Storage storage) {
		super(storage, System.out);
		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}

	/**
	 * Prints a list of available commands
	 */
	@Override
	public void printHelpMessages()
	{
		// PTT Director Specific help commands
		super.printStream.println("\nPTT Director commands:");
		super.printStream.println("get teachreq <courseID>: get list of teaching requests for a course");
		super.printStream.println("get trainreq <teacherID>: get list of training requests for a teacher");
		super.printStream.println("set status teachreq <courseID> <type> <id status>");
		super.printStream.println("set status trainreq <GUID> <id status>");
	}

	/**
	 * Processes commands inputted by the PTT
	 * 
	 * @param command A string of the command that was inputed.
	 * @param args    An array of optional String command arguments.
	 * @return True if the command was successfully processed.
	 */
	@Override
	public boolean processCommand(final String command, final String... args) {
		String[] commandArgs;

		// Splits the command for further processing.
		commandArgs = command.split(" ");

		// Checks if a get command was input
		if (commandArgs.length >= 3 && commandArgs[0].equalsIgnoreCase("get")) {

			if (commandArgs[1].equalsIgnoreCase("teachreq")) {
				getTeachingRequests(commandArgs[2]);
				return true;
			}
			if (commandArgs[1].equalsIgnoreCase("trainreq")) {
				getTrainingRequests(commandArgs[2]);

				return true;
			}
		}

		// Check if a set command was input
		else if (commandArgs.length >= 4 && commandArgs[0].equalsIgnoreCase("set")) {
			if (commandArgs[2].equalsIgnoreCase("teachreq")) {
				setStatusTeachReq(commandArgs[3], commandArgs[4], commandArgs[5]);
				return true;
			}

			// Approves/Rejects a training request (not implemented)
			if (commandArgs[2].equalsIgnoreCase("trainreq")) {
				super.printStream.println("Approving training requests currently not supported");
				return true;
			}

		}

		return false;
	}

	/**
	 * Print logout message.
	 */
	public void logout() {
		super.printStream.println("PTT logged out.");
	}

	/**
	 * Gets the teaching requests for a course
	 * 
	 * @param courseID A String containing the course ID.
	 * @return True if the course was found and displayed successfully
	 */
	public boolean getTeachingRequests(String courseID) {
		Course course = this.listOfCourses.getCourse(courseID);
		if (course == null) {
			super.printStream.println("Course not found, use course ID");
			return false;
		}
		course.printTeachingRequests(super.printStream);
		return true;
	}

	/**
	 * Gets the training requests for a teacher
	 * 
	 * @param teacherID A String containing the teacher ID.
	 * @return True if the request was found and displayed successfully
	 */
	public boolean getTrainingRequests(String teacherID) {
		Teacher teacher = this.listOfTeachers.getTeacher(teacherID);
		if (teacher == null) {
			super.printStream.println("Teacher not found, use GUID");
			return false;
		}
		teacher.printTrainingRequests(super.printStream);
		return true;
	}

	/**
	 * Sets the status of a teaching request.
	 * 
	 * @param courseID A String containing the course ID.
	 * @param type     A String containing the type of contact
	 *                 (LECTURE/LAB/TUTORIAL)
	 * @param courseID A String indicating the status inputed.
	 * 
	 * @return True if the request was accepted/rejected successfully.
	 */
	public boolean setStatusTeachReq(String courseID, String type, String status) {
		Course course = this.listOfCourses.getCourse(courseID);
		if (course == null) {
			super.printStream.println("Course not found, use course ID");
			return false;
		}
		RequestStatusType eStatus = null;
		try {
			eStatus = RequestStatusType.valueOf(status);
		} catch (IllegalArgumentException e) {
			super.printStream.println("Invalid request type, use:");
			ContactType.printContactTypes(super.printStream);
			return false;
		}

		ContactType eType = null;
		try {
			eType = ContactType.valueOf(type);
		} catch (IllegalArgumentException e) {
			super.printStream.println("Invalid request type, use:");
			ContactType.printContactTypes(super.printStream);
			return false;
		}

		TeachingRequest temp = course.getTeachingStaffRequirementsRequests().get(eType);
		temp.setRequestStatus(eStatus);

		super.printStream.println("Request status set:");
		course.printTeachingRequests(printStream, eType);

		return true;

	}

}
