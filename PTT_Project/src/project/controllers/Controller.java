package project.controllers;

import java.io.PrintStream;

import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public abstract class Controller
{
	
	private Storage storage;
	
	private PrintStream printStream;
	
	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;
	
	protected Controller(final Storage storage)
	{
		this.storage = storage;
		
		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}
	
	public Storage getStorage()
	{
		return storage;
	}
	
	public PrintStream getPrintStream()
	{
		return printStream;
	}
	
	public ListOfTeachers getListOfTeachers()
	{
		return this.listOfTeachers;
	}
	
	public ListOfCourses getListOfCourses()
	{
		return this.listOfCourses;
	}
	
	public abstract void printHelpMessages();
	
	public abstract boolean processCommand(final String command, final String... args);

	public abstract void logout();
	
}
