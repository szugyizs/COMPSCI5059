package project.requests;

import java.io.PrintStream;

import project.requests.course.ContactType;
import project.requests.course.Qualifications;

/**
 * CourseRequirement contains all the information about a requirement
 */

public class CourseRequirement 
{
	
	private ContactType contactType; // the type of contact hours required 
	private int numberOfStudents; // the number of students 
	private int numberOfStaff; // the number of staff required 
	private int contactHours; // the number of contact hours required 
	private Qualifications requiredStaffQualifications; // the staff qualifications required
	
	/**
	 * Constructor explicitly instantiates the CourseRequirement instance.
	 * 
	 * @param contactType					The [ContactType] type of contact hours required
	 * @param numberOfStudents				The [int] number of students
	 * @param numberOfStaff					The [int] number of staff required
	 * @param contactHours					The [int] number of contact hours required
	 * @param requiredStaffQualifications	The [Qualifications] staff qualifications required.
	 */
	
	public CourseRequirement(final ContactType contactType, final int numberOfStudents, 
			final int numberOfStaff, final int contactHours, final Qualifications requiredStaffQualifications)
	{
		this.contactType = contactType;
		this.numberOfStudents = numberOfStudents;
		this.numberOfStaff = numberOfStaff;
		this.contactHours = contactHours;
		this.requiredStaffQualifications = requiredStaffQualifications;
		
	}
	
	/**
	 * A getter method for contactType.
	 * 
	 * @return A contactType instance for the requirement.
	 */
	public ContactType getContactType()
	{
		return contactType;
	}

	/**
	 * A setter method for contactType
	 * 
	 * @param contactType	The [ContactType] type of contact hours required
	 */
	public void setContactType(ContactType contactType) 
	{
		this.contactType = contactType;
	}

	/**
	 * a getter method for numberOfStudents
	 * 
	 * @return A int instance representing the number of students in a requirement
	 */
	public int getNumberOfStudents()
	{
		return numberOfStudents;
	}

	/**
	 * a setter method for numberOfStudents
	 * 
	 * @param numberOfStudents	The [int] number of students
	 */
	public void setNumberOfStudents(int numberOfStudents) 
	{
		this.numberOfStudents = numberOfStudents;
	}


	/**
	 * a getter method for numberOfStaff
	 * 
	 * @return A int instance representing the number of staff in a requirement 
	 */
	public int getNumberOfStaff() {
		return numberOfStaff;
	}

	/**
	 * A setter method for numberOfStaff.
	 * 
	 * @param numberOfStaff		The [int] number of staff required
	 */
	public void setNumberOfStaff(int numberOfStaff)
	{
		this.numberOfStaff = numberOfStaff;
	}


	/**
	 * a getter method for contactHours
	 * 
	 * @return A int instance representing the contact hours required
	 */
	public int getContactHours() {
		return contactHours;
	}

	/**
	 * a setter method for contactHours
	 * 
	 *  @param contactHours	The [int] number of contact hours required
	 */
	public void setContactHours(int contactHours) 
	{
		this.contactHours = contactHours;
	}

	/**
	 * a getter method for requiredStaffQualifications
	 * 
	 * @return A qualifications instance representing the required staff qualification 
	 */
	public Qualifications getRequiredStaffQualifications() 
	{
		return requiredStaffQualifications;
	}

	/**
	 * a setter method for requiredStaffQualifications
	 * 
	 * @param requiredStaffQualifications	The [Qualifications] staff qualifications required.
	 */
	public void setRequiredStaffQualifications(Qualifications requiredStaffQualifications)
	{
		this.requiredStaffQualifications = requiredStaffQualifications;
	}
	
	/**
	 * a method to print the course requirement
	 * 
	 */
	public void printCourseRequirement(final PrintStream printStream)
	{
		printStream.println(String.format("Contact Type: %s\nNo. of Students: %d\nNo. of Staff: %d\nNo. Contact Hours: %d", 
				contactType.getName(), numberOfStudents, numberOfStaff, contactHours));
		printStream.println("Required Staff Qualifications:");
		requiredStaffQualifications.printSkills(printStream);
	}
	
	@Override
	/**
	 * a method to return the course requirement as a String
	 */
	public String toString()
	{
		
		return String.format("Contact Type: %s, Number Of Students: %d" 
				+ "Number of Staff: %d, Contact Hours: %d, Qualifications: %s",
				contactType.toString(),
				numberOfStudents,
				numberOfStaff,
				contactHours,
				requiredStaffQualifications.toString());
	}
}

