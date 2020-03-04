package project.storage.lists;

import java.io.PrintStream;
import java.util.LinkedList;

import project.requests.course.Teacher;

/**
 * Class for List of Teachers. It collects and manages a list of teacher
 * objects.
 */
public class ListOfTeachers
{

	private LinkedList<Teacher> teachers;

	/**
	 * Constructor for the List of Teachers, which creates a ListOfTeachers object
	 * with a pre-supplied list of teachers.
	 * 
	 * @param teachers
	 */
	private ListOfTeachers(final LinkedList<Teacher> teachers)
	{
		this.teachers = teachers;
	}

	/**
	 * Constructor for the List of Teachers, which creates an empty ListOfTeachers
	 * object.
	 */
	public ListOfTeachers()
	{
		this(new LinkedList<Teacher>());
	}

	/**
	 * A getter method to access the teachers variable from outside.
	 * 
	 * @return teachers: a linked list of teachers.
	 */
	public LinkedList<Teacher> getTeachers()
	{
		return teachers;
	}

	/**
	 * A method to add a teacher to the list of teachers.
	 * 
	 * @param teacher: the teacher object to be added.
	 */
	public void addTeacher(final Teacher teacher)
	{
		teachers.add(teacher);
	}

	/**
	 * A getter method to access a specific teacher variable from outside.
	 * 
	 * @param guid: the Glasgow University ID number which uniquely identifies all
	 *              staff
	 * @return teacher: a teacher object which has the specified GUID
	 */
	public Teacher getTeacher(final String guid)
	{
		for (final Teacher teacher : teachers) {
			if (teacher.getGUID().equalsIgnoreCase(guid)) {
				return teacher;
			}
		}
		return null;
	}

	/**
	 * A method to print out all the teacher objects stored in this class, in a
	 * String format.
	 * 
	 * @param printStream: the print stream into which the data is to be sent, and
	 *                     through which it is printed.
	 */
	public void printTeachers(final PrintStream printStream)
	{
		if (this.teachers.isEmpty() == true) {
			printStream.println("\nNo teachers in system. Polulate teachers.");
			printStream.println("----------------------------------------\n");
		}
		for (final Teacher teacher : this.teachers) {
			teacher.printTeacher(printStream);
			printStream.println("\n----------------------------------------\n");
		}
	}
}
