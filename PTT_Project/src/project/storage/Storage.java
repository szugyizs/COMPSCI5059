package project.storage;

import project.storage.lists.ListOfTeachers;
import project.storage.lists.ListOfCourses;

public abstract class Storage 
{

	//TODO - comment this class
	//TODO - review functionality
	protected ListOfTeachers listOfTeachers;
	protected ListOfCourses listOfCourses;
	
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
	
	public abstract void reload();
	
	public abstract void load();
	
	public abstract void save();
	
	public abstract void isAvailable();
}
