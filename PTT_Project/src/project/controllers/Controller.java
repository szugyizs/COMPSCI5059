package project.controllers;

import java.io.PrintStream;

import project.storage.FileStorage;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public abstract class Controller
{
	// TODO - comment this class
	// TODO - review functionality
	private Storage storage;

	protected PrintStream printStream;

	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;

	protected Controller(final Storage storage, final PrintStream printStream)
	{
		this.storage = storage;
		this.printStream = printStream;

		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}
	
	protected Controller(final Storage storage)
	{
		this(storage, System.out);
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
