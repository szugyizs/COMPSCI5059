package project.storage.lists;
import java.io.PrintStream;
import java.util.LinkedList;

import project.requests.RequestStatusType;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Teacher;

public class ListOfTeachers 
{

	//TODO - comment this class
	//TODO - review functionality
	private LinkedList<Teacher> teachers;
	
	public ListOfTeachers(final LinkedList<Teacher> teachers)
	{
		this.teachers = teachers;
	}
	
	public ListOfTeachers()
	{
		this(new LinkedList<Teacher>());
	}
	
	public LinkedList<Teacher> getTeachers()
	{
		return teachers;
	}
	
	public Teacher getTeacher(final String guid)
	{
		for (final Teacher teacher : teachers) {
			if (teacher.getGUID().equalsIgnoreCase(guid)) {
				return teacher;
			}
		}
		return null;
	}
	
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
	
	public void printTeachersWithQualificationsForCourse(final PrintStream printStream, final Course course, final ContactType contactType)
	{
		final LinkedList<Teacher> teachersWithQualifications = getTeachersWithQualificationsForCourse(course, contactType);
		for (final Teacher teacher : teachersWithQualifications) {
			teacher.printTeacher(printStream);
			printStream.println("\n----------------------------------------\n");
		}
	}
	
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
