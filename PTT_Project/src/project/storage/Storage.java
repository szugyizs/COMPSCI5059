package project.storage;

import project.storage.lists.ListOfTeachers;

import java.util.LinkedList;

import project.requests.course.Course;
import project.storage.lists.ListOfCourses;

public abstract class Storage 
{

	protected ListOfTeachers listOfTeachers; // The list of teachers held within storage.
	protected ListOfCourses listOfCourses; // The list of courses held within storage.
	
	public ListOfTeachers getListOfTeachers()
	{
		return listOfTeachers;
	}
	
	public void setListOfTeachers(final ListOfTeachers listOfTeachers)
	{
		this.listOfTeachers = listOfTeachers;
	}
	
	public ListOfCourses getListOfCourses()
	
	{
		return listOfCourses;
	}
	
	public void setListOfCourses(final ListOfCourses listOfCourses)
	{
		this.listOfCourses = listOfCourses;
	}
	
	// test
	
	public abstract boolean load();
	
	public abstract boolean save();
	
	public abstract boolean isAvailable();
}
