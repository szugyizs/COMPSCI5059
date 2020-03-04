package project.controllers;

import java.io.PrintStream;
import java.util.Scanner;

import project.requests.course.Qualifications;
import project.requests.course.SkillType;
import project.storage.Storage;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public abstract class Controller
{
	protected Storage storage;
	protected PrintStream printStream;

	private ListOfTeachers listOfTeachers;
	private ListOfCourses listOfCourses;

	/**
	 * Constructor explicitly instantiates the instance, informing about the needed
	 * data parameters in the process.
	 * 
	 * @param storage     The storage class, that holds the list of teachers and
	 *                    courses.
	 * @param printStream An printStream to output data to.
	 */
	protected Controller(final Storage storage, final PrintStream printStream)
	{
		this.storage = storage;
		this.printStream = printStream;

		this.listOfTeachers = storage.getListOfTeachers();
		this.listOfCourses = storage.getListOfCourses();
	}

	/**
	 * Constructor explicitly instantiates the instance, informing about the needed
	 * data parameters in the process.
	 * 
	 * @param storage The storage class, that holds the list of teachers and
	 *                courses.
	 */
	protected Controller(final Storage storage)
	{
		this(storage, System.out);
	}

	/**
	 * Getter for the storage instance.
	 * 
	 * @return The storage class that holds the data.
	 */
	public Storage getStorage()
	{
		return storage;
	}

	/**
	 * Getter for the print stream instance.
	 * 
	 * @return The printStream instance to output data to.
	 */
	public PrintStream getPrintStream()
	{
		return printStream;
	}

	/**
	 * Getter for the teacher data.
	 * 
	 * @return A list of all stored teacher instances
	 */
	public ListOfTeachers getListOfTeachers()
	{
		return this.listOfTeachers;
	}

	/**
	 * Getter for the course data.
	 * 
	 * @return A list of all stored course instances
	 */
	public ListOfCourses getListOfCourses()
	{
		return this.listOfCourses;
	}

	/**
	 * Abstract method for helper messages, needs to be implemented.
	 */
	public abstract void printHelpMessages();

	/**
	 * Abstract method to progress command, needs to be implemented.
	 */
	public abstract boolean processCommand(final String command, final String... args);

	/**
	 * Abstract method for logout of the controller, needs to be implemented.
	 */
	public abstract void logout();

	/**
	 * Reads in qualifications in the form of <SkillType> <0-5>. Several Skills can
	 * be entered, exit by writing done. If no skill is successfully entered, null
	 * is returned.
	 * 
	 * @return Qualifications instance if it could be instantiated, null otherwise.
	 */
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
				// Try to parse the skill type.
				try {
					type = SkillType.valueOf(commandArgs[0]);
				} catch (IllegalArgumentException e) {
					printStream.println("Invalid skill type, use:");
					SkillType.printSkillTypes(this.printStream);
				}

				// Try to parse a Short
				try {
					level = Short.parseShort(commandArgs[1]);
				} catch (NumberFormatException e) {
					printStream.println("Level invalid, use 0-5.");
				}

				// Try to set a skill
				if (qualificaitons.setSkill(type, level) == true) {
					printStream.println("Skill set, add another or \"done\".");
					continue;
				}
			}

			printStream.println("Failed to process command; type \"help\" or \"done\"");

		} while (!(command = scanner.nextLine()).equalsIgnoreCase("done"));

		return qualificaitons;
	}

	/**
	 * Print help commands for the Qualifications scanner. *
	 */
	private void printQualiHelp()
	{

		// General help commands.
		this.printStream.println("Enter qualifications:");
		this.printStream.println("<SkillType> <0-5>");
		this.printStream.println("done to exit");

	}
}
