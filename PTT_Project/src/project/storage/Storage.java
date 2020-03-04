package project.storage;

import project.storage.lists.ListOfTeachers;

import java.util.LinkedList;

import project.requests.course.Course;
import project.storage.lists.ListOfCourses;

/**
 * The abstract superclass Storage, which implements methods and abstract methods 
 * which may be implemented or overwritten by children subclasses. It also serves as a collection
 * of shared parameters.
 */
public abstract class Storage 
{

	protected ListOfTeachers listOfTeachers; // The list of teachers held within storage.
	protected ListOfCourses listOfCourses; // The list of courses held within storage.
	
	/**
	 * A getter method to gain the contents of the List of Teachers from outside the class.
	 * @return listOfTeachers: the contents of the variable list of teachers, a ListOfTeachers object.
	 */
	public ListOfTeachers getListOfTeachers()
	{
		return listOfTeachers;
	}
	
	/**
	 * A setter method to manipulate the contents of the List of Teachers from outside the class.
	 * @param listOfTeachers: a list of teachers variable to be set as the new value, a ListOfTeachers object.
	 */
	public void setListOfTeachers(final ListOfTeachers listOfTeachers)
	{
		this.listOfTeachers = listOfTeachers;
	}
	
	/**
	 * A getter method to gain the contents of the List of Courses from outside the class.
	 * @return listOfCourses: the contents of the variable list of courses, a ListOfCourses object.
	 */
	public ListOfCourses getListOfCourses()
	
	{
		return listOfCourses;
	}
	
	/**
	 * A setter method to manipulate the contents of the List of Courses from outside the class.
	 * @param listOfCourses: a list of courses variable to be set as the new value, a ListOfCourses object.
	 */
	public void setListOfCourses(final ListOfCourses listOfCourses)
	{
		this.listOfCourses = listOfCourses;
	}
	
	/**
	 * A method to load data from any type of storage to the system.
	 * @return boolean: status, based on whether the operation was successful.
	 */
	public abstract boolean load();
	
	/**
	 * A method to save data to any type of storage from the system.
	 * @return boolean: a status variable, based on whether the operation was successful.
	 */
	public abstract boolean save();
	
	/**
	 * A rudimentary error-checking method to ensure the lists to be saved from/to in the system actually exist.
	 * @return boolean: a status variable signalling whether there were any errors detected with this operation
	 */
	public abstract boolean isAvailable();
}
