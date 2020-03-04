package project.storage.lists;
import java.io.PrintStream;
import java.util.LinkedList;

import project.requests.RequestStatusType;
import project.requests.course.ContactType;
import project.requests.course.Course;

/**
 * Class for List of Courses. It collects and manages a list of course objects.
 */
public class ListOfCourses 
{
	private LinkedList<Course> courses;
	
	/**
	 * Constructor for the List of Courses, which creates a ListOfCourses object with a pre-supplied list of courses.
	 * @param courses
	 */
	public ListOfCourses(final LinkedList<Course> courses)
	{
		this.courses = courses;
	}
	
	/**
	 * Constructor for the List of Courses, which creates an empty ListOfCourses object.
	 */
	public ListOfCourses()
	{
		this(new LinkedList<Course>());
	}
	
	/**
	 * A getter method to access the courses variable from outside.
	 * @return courses: a linked list of courses.
	 */
	public LinkedList<Course> getCourses()
	{
		return this.courses;
	}
	
	/**
	 * A method to add a course to the list of courses.
	 * @param course: the course object to be added.
	 */
	public void addCourse(final Course course)
	{
		courses.add(course);
	}

	/**
	 * A method to get a list of courses which have a specific request status type.
	 * @param requestStatusType: a type of request status
	 * @return coursesWithRequestStatusTypes: a Linked List of Course objects which have a specific request status type
	 */
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
	
	/**
	 * A method to get a list of courses which have a specific type of class contact and request status type.
	 * @param contactType: the type of course contact component (lab, tutorial or lecture)
	 * @param requestStatusType: the type of request status
	 * @return coursesWithStatus: a linked list of course objects which have specific contact and status types
	 */
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
	
	/**
	 * A method to get a list of courses with unfulfilled teaching requirements.
	 * @return: a linked list of course objects with requirements that have not been met.
	 */
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
	
	/**
	 * A method to get a course by ID.
	 * @param courseId: the course ID
	 * @return course: a course object which has a specific course ID
	 */
	public Course getCourse(final String courseId)
	{
		for (final Course course : this.courses) {
			if (course.getCourseID().equalsIgnoreCase(courseId)) {
				return course;
			}
		}
		return null;
	}

	/**
	 * A method to print out all the courses with unfulfilled teaching requirements.
	 * @param printStream: the print stream to which the data should be added, and through which it should be printed
	 */
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
	
	/**
	 * A method to print a complete course.
	 * @param printStream: the printing stream object to which the data should be passed and through which it should be printed.
	 */
	public void print(final PrintStream printStream)
	{

		//	Print all of the courses.
		for (final Course course : this.courses) {
			course.printCourse(printStream);
			printStream.println();
		}
	}
}
