package project;

import java.io.PrintStream;
import java.util.Scanner;

import project.controllers.Controller;
import project.controllers.types.AdministratorController;
import project.controllers.types.CourseDirectorController;
import project.controllers.types.PTTDirectorController;
import project.requests.course.Course;
import project.storage.Storage;
import project.storage.FileStorage;

public class Main
{
	private PrintStream printStream;
	private Scanner scanner;

	private Storage storage;
	private Controller controller;
	// Tests, comment in to perform
//	private Test test;

	public Main()
	{
		printStream = System.out;
		scanner = new Scanner(System.in);

		storage = new FileStorage("lib/database.json");
		storage.load();

//		// Tests, comment in to perform
//		test = new Test();
//		test.testAdminCommands();
//		test.testCourseDirectorCommands();
//		test.testPTTCommands();
	}

	public PrintStream getPrintStream()
	{
		return printStream;
	}

	public Scanner getScanner()
	{
		return scanner;
	}

	public Storage getStorage()
	{
		return storage;
	}

	public Controller getController()
	{
		return controller;
	}

	private void startParsing(final String... args)
	{
		String command = "help";
		String[] commandArgs;
		do {

			// Splits the command for further processing.
			commandArgs = command.split(" ");

			// Always print help as the first command.
			if (command.equalsIgnoreCase("help")) {
				printHelp();
				continue;
			}
			// If the command is logout, logout of the existing controller.
			else if (command.equalsIgnoreCase("logout")) {
				if (controller == null) {
					printStream.println("In order to logout, you must first be logged in. See \"help\"");
					continue;
				}
				controller.logout();
				controller = null;
				printStream.println("Successfully logged out!");
				continue;
			}

			// If the controller is not equal to null, then attempt to process the command.
			if (controller != null) {
				if (!controller.processCommand(command, commandArgs)) {
					printStream.println(String.format("Failed to process command \"%s\"; type \"help\"", command));
					continue;
				}
				continue;
			}
			// The controller is null, therefore process the login code.
			else if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("login")) {
				this.controller = attemptLogin(command, commandArgs);
				if (controller != null) {
					printHelp();
					continue;
				}
			}

			// We failed to process the command, pass it on.
			printStream.println("Failed to process command; type \"help\" and login as a user!");
		} while (!(command = scanner.nextLine()).equalsIgnoreCase("quit"));

		printStream.println("Successfully quit the program.");
		// If we quit and a controller exists, save the storage.
		if (controller != null) {
			controller.logout();
			printStream.println("Successfully logged out!");
		}

		storage.save();
		printStream.println("Work saved.");
	}

	private void printHelp()
	{

		// General help commands.
		printStream.println("General commands:");
		printStream.println("login cd <course id>");
		printStream.println("login ptt - login as a PTT director.");
		printStream.println("login admin - login as an administrator.");
		printStream.println("logout - will logout of the current controller");
		printStream.println("quit - will save and quit all work.");

		// Controller specific help commands (if not null).
		if (controller != null) {
			controller.printHelpMessages();
		}
	}

	private Controller attemptLogin(final String command, final String[] commandArgs)
	{
		if (commandArgs.length >= 2 && commandArgs[0].equalsIgnoreCase("login")) {
			if (commandArgs[1].equalsIgnoreCase("admin")) {
				return new AdministratorController(storage);
			} else if (commandArgs[1].equalsIgnoreCase("ptt")) {

				// Simply
				return new PTTDirectorController(storage);
			} else if (commandArgs.length == 3 && commandArgs[1].equalsIgnoreCase("cd") && !commandArgs[2].isEmpty()) {

				// Attempt to find the course, if it exists, create a new course director for
				// it.
				if (storage.getListOfCourses().getCourses().isEmpty() == true) {
					printStream.println("No courses specified, login not possible.");
					return null;
				}

				final Course course = storage.getListOfCourses().getCourse(commandArgs[2]);
				if (course == null) {
					printStream.println("Specified course not found.");
					return null;
				}
				return new CourseDirectorController(storage, course);
			}
		}
		return null;
	}

	public static void main(String... args)
	{
		Main main = new Main();
		main.startParsing(args);

	}

}
