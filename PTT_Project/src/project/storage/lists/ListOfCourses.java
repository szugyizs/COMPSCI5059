package project.storage.lists;
import java.io.PrintStream;
import java.util.LinkedList;

import project.requests.RequestStatusType;
import project.requests.course.ContactType;
import project.requests.course.Course;

public class ListOfCourses 
{

	//TODO - comment this class
	//TODO - review functionality
	private LinkedList<Course> courses;
	
	public ListOfCourses(final LinkedList<Course> courses)
	{
		this.courses = courses;
	}
	
	public ListOfCourses()
	{
		this(new LinkedList<Course>());
	}
	
	public LinkedList<Course> getCourses()
	{
		return this.courses;
	}
	
	public void addCourse(final Course course)
	{
		courses.add(course);
	}

	public LinkedList<Course> getCoursesWithContactTypesState(final RequestStatusType requestStatusType)
	{
		LinkedList<Course> coursesWithRequestStatusTypes = new LinkedList<Course>();
		for (final Course course : this.courses) {
			for (final ContactType contactType : ContactType.values()) {
				if (course.hasContactTypeTeachingRequest(contactType, requestStatusType)) {
					coursesWithRequestStatusTypes.add(course);
				}
			}
		}
		return coursesWithRequestStatusTypes;
	}
	
	public LinkedList<Course> getCoursesWithContactTypeState(final ContactType contactType, final RequestStatusType requestStatusType)
	{
		LinkedList<Course> coursesWithStatus = new LinkedList<Course>();
		for (final Course course : this.courses) {
			if (course.hasContactTypeTeachingRequest(contactType, requestStatusType)) {
				coursesWithStatus.add(course);
			}
		}
		return coursesWithStatus;
	}
	
	public LinkedList<Course> getUnfulfilledCourses()
	{
		LinkedList<Course> unfulfilledCourses = new LinkedList<Course>();
		for (final Course course : this.courses) {
			if (!course.areRequirementsFulfilled()) {
				unfulfilledCourses.add(course);
			}
		}
		return unfulfilledCourses;
	}
	
	public Course getCourse(final String courseId)
	{
		for (final Course course : this.courses) {
			if (course.getCourseID().equalsIgnoreCase(courseId)) {
				return course;
			}
		}
		return null;
	}

	public void printUnfulfilledCourses(final PrintStream printStream)
	{
		
		// Get the list of unfulfilled courses.
		final LinkedList<Course> unfulfilledCourses = getUnfulfilledCourses();
		if (unfulfilledCourses.isEmpty()) {
			printStream.println("There are no unfulfilled courses.");
		}
		
		// Print all of the unfulfilled courses.
		for (final Course course : unfulfilledCourses) {
			course.printCourse(printStream);
			printStream.println();
			course.printUnfulfillments(printStream);
		}
	}
	

	public void print(final PrintStream printStream)
	{

		//	Print all of the courses.
		for (final Course course : this.courses) {
			course.printCourse(printStream);
			printStream.println();
		}
	}
}
