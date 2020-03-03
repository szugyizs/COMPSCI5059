package project.controllers;

import java.io.PrintStream;
import java.util.Scanner;

import project.requests.course.Qualifications;
import project.requests.course.SkillType;
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
	
	protected Qualifications addQualifications(Scanner scanner)
	{

		String command = "help";
		String[] commandArgs;

		SkillType type = null;
		Short level = null;

		Qualifications qualificaitons = new Qualifications();

		do {
			commandArgs = command.split(" ");
			// Always print help as the first command.
			if (command.equalsIgnoreCase("help")) {
				printQualiHelp();
				continue;
			} else if (commandArgs.length == 2) {
				try {
					type = SkillType.valueOf(commandArgs[0]);
				} catch (IllegalArgumentException e) {
					printStream.println("Invalid skill type, use:");
					SkillType.printSkillTypes(this.printStream);
				}

				try {
					level = Short.parseShort(commandArgs[1]); // TODO: negative values?
				} catch (NumberFormatException e) {
					printStream.println("Level invalid, use 0-5.");
				}

				if (qualificaitons.setSkill(type, level) == true) {
					printStream.println("Skill set.");
					continue;
				}
			}

			printStream.println("Failed to process command; type \"help\" or \"done\"");

		} while (!(command = scanner.nextLine()).equalsIgnoreCase("done"));

		return qualificaitons;
	}
	
	protected void printQualiHelp()
	{

		// General help commands.
		this.printStream.println("Explain get qulaifications:");

	}
}
