package project.storage;

import project.storage.lists.ListOfTeachers;
import project.storage.lists.ListOfCourses;

public abstract class Storage 
{

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
	
	public abstract boolean isAvailable();
	
	public abstract boolean reload();
	
	public abstract void load();
	
	public abstract boolean save();
	
}
