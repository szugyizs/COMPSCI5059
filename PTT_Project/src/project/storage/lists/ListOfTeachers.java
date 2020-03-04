package project.storage.lists;
import java.io.PrintStream;
import java.util.LinkedList;

import project.requests.RequestStatusType;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Teacher;

/**
 * Class for List of Teachers. It collects and manages a list of teacher objects.
 */
public class ListOfTeachers 
{

	private LinkedList<Teacher> teachers;
	
	/**
	 * Constructor for the List of Teachers, which creates a ListOfTeachers object with a pre-supplied list of teachers.
	 * @param teachers
	 */
	public ListOfTeachers(final LinkedList<Teacher> teachers)
	{
		this.teachers = teachers;
	}
	
	/**
	 * Constructor for the List of Teachers, which creates an empty ListOfTeachers object.
	 */
	public ListOfTeachers()
	{
		this(new LinkedList<Teacher>());
	}
	
	/**
	 * A getter method to access the teachers variable from outside.
	 * @return teachers: a linked list of teachers.
	 */
	public LinkedList<Teacher> getTeachers()
	{
		return teachers;
	}
	
	/**
	 * A method to add a teacher to the list of teachers.
	 * @param teacher: the teacher object to be added.
	 */
	public void addTeacher(final Teacher teacher)
	{
		teachers.add(teacher);
	}
	
	/**
	 * A getter method to access a specific teacher variable from outside.
	 * @param guid: the Glasgow University ID number which uniquely identifies all staff
	 * @return teacher: a teacher object which has the specified GUID
	 */
	public Teacher getTeacher(final String guid)
	{
		for (final Teacher teacher : teachers) {
			if (teacher.getGUID().equalsIgnoreCase(guid)) {
				return teacher;
			}
		}
		return null;
	}
	
	/**
	 * A method to get all the teachers that have specific statuses of training requests attached to them.
	 * @param requestStatusType: The type of request status looked for
	 * @return teachersWithTrainingRequestState: the list of teachers who ave training requests with the specific status
	 */
	public LinkedList<Teacher> getTeachersWithTrainingRequestState(final RequestStatusType requestStatusType)
	{
		LinkedList<Teacher> teachersWithTrainingRequestState = new LinkedList<Teacher>();
		for (final Teacher teacher : this.teachers) {
			if (teacher.hasTrainingRequestsWithState(requestStatusType)) {
				teachersWithTrainingRequestState.add(teacher);
			}
		}
		return teachersWithTrainingRequestState;
	}
	
	/**
	 * A method to get all teachers which have the qualifications to teach at a certain contact of a class.
	 * @param course: the course we look at teachers for
	 * @param contactType: the type of contact the teachers need to be qualified to teach at
	 * @return teachersWithQualifications: the linked list of teachers who satisfy the criteria
	 */
	public LinkedList<Teacher> getTeachersWithQualificationsForCourse(final Course course, final ContactType contactType)
	{
		LinkedList<Teacher> teachersWithQualifications = new LinkedList<Teacher>();
		for (final Teacher teacher : this.teachers) {
			if (course.checkTeacherMeetsQualifications(contactType, teacher)) {
				teachersWithQualifications.add(teacher);
			}
		}
		return teachersWithQualifications;
	}
	
	/**
	 * A method to print all of the training requests as string into the console.
	 * 
	 * @param printStream: the print stream into which the data is to be sent, and through which it is printed.
	 */
	public void printTrainingRequests(final PrintStream printStream)
	{
		if (this.teachers.isEmpty()) {
			printStream.println("There are no teachers for which to print training requests.");
		}
		
		// Print all of the training requests for the teachers.
		for (final Teacher teacher : this.teachers) {
			teacher.printTeacher(printStream);
			
			// Loop through all of the states in order.
			for (final RequestStatusType requestStatusType : RequestStatusType.values()) {
				printStream.println(String.format("\nTraining requests with status %s:", requestStatusType.getName()));
				teacher.printTrainingRequests(printStream, requestStatusType);
				printStream.println("\n");
			}
			printStream.println("\n----------------------------------------\n");
		}
	}
	
	/**
	 * A method to return all the training or teaching requests which have a specific request status.
	 * 
	 * @param printStream: the print stream into which the data is to be sent, and through which it is printed.
	 * @param requestStatusType: the type of request status for which we are looking at classes for
	 */
	public void printTrainingRequests(final PrintStream printStream, final RequestStatusType requestStatusType)
	{
		
		// Get the teachers for the request status type.
		LinkedList<Teacher> teachersWithTrainingRequests = getTeachersWithTrainingRequestState(requestStatusType);
		if (teachersWithTrainingRequests.isEmpty()) {
			printStream.println(String.format("There are no training requests with the status: %s", requestStatusType.getName()));
			return;
		}
		
		printStream.println(String.format("Displaying teacher training requests with status %s", 
				requestStatusType.getName()));
		
		// Print all of the teachers alongside the training requests.
		for (final Teacher teacher : teachersWithTrainingRequests) {
			teacher.printTeacher(printStream);
			printStream.println(String.format("\nTraining requests with status %s:", requestStatusType.getName()));
			teacher.printTrainingRequests(printStream, requestStatusType);
			printStream.println("\n----------------------------------------\n");
		}
	}
	
	/**
	 * A method to print out all the teacher objects which have the qualifications to teach at a 
	 * certain course contact type, in a String format.
	 * @param printStream: the print stream into which the data is to be sent, and through which it is printed.
	 * @param course: the course which the teachers looked for are related to
	 * @param contactType: the class contact type which relates to the course object and for which the teacher applies
	 */
	public void printTeachersWithQualificationsForCourse(final PrintStream printStream, final Course course, final ContactType contactType)
	{
		final LinkedList<Teacher> teachersWithQualifications = getTeachersWithQualificationsForCourse(course, contactType);
		for (final Teacher teacher : teachersWithQualifications) {
			teacher.printTeacher(printStream);
			printStream.println("\n----------------------------------------\n");
		}
	}
	
	/**
	 * A method to print out all the teacher objects stored in this class, in a String format.
	 * @param printStream: the print stream into which the data is to be sent, and through which it is printed.
	 */
	public void printTeachers(final PrintStream printStream)
	{
		if( this.teachers.isEmpty() == true) {
			printStream.println("\nNo teachers in system. Polulate teachers.");
			printStream.println("----------------------------------------\n");
		}
		for (final Teacher teacher : this.teachers) {
			teacher.printTeacher(printStream);
			printStream.println("\n----------------------------------------\n");
		}
	}
}
