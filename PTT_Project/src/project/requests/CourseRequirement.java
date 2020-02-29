package project.requests;

import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Qualifications;

public class CourseRequirement 
{
	
	private ContactType contactType;
	private int numberOfStudents;
	private int numberOfStaff;
	private int contactHours;
	private Qualifications requiredStaffQualifications;

	
	public CourseRequirement(final Course course, final ContactType contactType, final int contactHours, final String typeOfContact,
							 final int numberOfStaff, final Qualifications requiredStaffQualifications, final int numberOfStudents)
	{
		this.course = course;
		this.contactType = contactType;
		this.contactHours = contactHours;
		this.numberOfStaff = numberOfStaff;
		this.requiredStaffQualifications = requiredStaffQualifications;
		this.numberOfStudents = numberOfStudents;
	}
	

	private Course course;
	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public ContactType getContactType() {
		return contactType;
	}


	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}


	public int getNumberOfStudents() {
		return numberOfStudents;
	}


	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}


	public int getNumberOfStaff() {
		return numberOfStaff;
	}


	public void setNumberOfStaff(int numberOfStaff) {
		this.numberOfStaff = numberOfStaff;
	}


	public int getContactHours() {
		return contactHours;
	}


	public void setContactHours(int contactHours) {
		this.contactHours = contactHours;
	}


	public Qualifications getRequiredStaffQualifications() {
		return requiredStaffQualifications;
	}


	public void setRequiredStaffQualifications(Qualifications requiredStaffQualifications) {
		this.requiredStaffQualifications = requiredStaffQualifications;
	}

}
