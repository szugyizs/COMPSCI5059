package project.requests;

import java.io.PrintStream;

import project.requests.course.ContactType;
import project.requests.course.Qualifications;

public class CourseRequirement 
{
	
	private ContactType contactType;
	
	private int numberOfStudents;
	private int numberOfStaff;
	private int contactHours;
	
	private Qualifications requiredStaffQualifications;
	
	/**
	 * Constructor explicitly instantiates the CourseRequirement instance.
	 * 
	 * @param course 						The [Course] course
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

	public ContactType getContactType()
	{
		return contactType;
	}


	public void setContactType(ContactType contactType) 
	{
		this.contactType = contactType;
	}


	public int getNumberOfStudents()
	{
		return numberOfStudents;
	}


	public void setNumberOfStudents(int numberOfStudents) 
	{
		this.numberOfStudents = numberOfStudents;
	}


	public int getNumberOfStaff() {
		return numberOfStaff;
	}


	public void setNumberOfStaff(int numberOfStaff)
	{
		this.numberOfStaff = numberOfStaff;
	}


	public int getContactHours() {
		return contactHours;
	}


	public void setContactHours(int contactHours) 
	{
		this.contactHours = contactHours;
	}


	public Qualifications getRequiredStaffQualifications() 
	{
		return requiredStaffQualifications;
	}


	public void setRequiredStaffQualifications(Qualifications requiredStaffQualifications)
	{
		this.requiredStaffQualifications = requiredStaffQualifications;
	}
	
	public void printCourseRequirement(final PrintStream printStream)
	{
		printStream.println(String.format("Contact Type: %s\nNo. of Students: %d\nNo. of Staff: %d\nNo. Contact Hours: %d", 
				contactType.getName(), numberOfStudents, numberOfStaff, contactHours));
		printStream.println("Required Staff Qualifications:");
		requiredStaffQualifications.printSkills(printStream);
	}
	
	@Override
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

