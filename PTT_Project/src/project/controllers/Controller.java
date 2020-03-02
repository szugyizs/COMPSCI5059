package project.controllers;

import java.io.PrintStream;

import project.storage.FileStorage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public abstract class Controller
{
	// TODO - comment this class
	// TODO - review functionality
	private FileStorage storage;

	private PrintStream printStream;

	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;

	protected Controller(final FileStorage storage)
	{
		this.storage = storage;

		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}

	public FileStorage getStorage()
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
