package project.storage;

import project.storage.lists.ListOfTeachers;
import project.storage.lists.ListOfCourses;

public abstract class Storage 
{

	//TODO - comment this class
	//TODO - review functionality
	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;
	
	public ListOfTeachers getListOfTeachers()
	{
		return listOfTeachers;
	}
	
	public ListOfCourses getListOfCourses()
	{
		return listOfCourses;
	}
	
	public void setListOfTeachers(ListOfTeachers listOfTeachers)
	{
		this.listOfTeachers = listOfTeachers;
	}
	
	public void setListOfCourses(ListOfCourses listOfCourses)
	{
		this.listOfCourses = listOfCourses;
	}
	
	public abstract boolean isAvailable();
	
	public abstract boolean reload();
	
	public abstract void load();
	
	public abstract boolean save();
	
}
