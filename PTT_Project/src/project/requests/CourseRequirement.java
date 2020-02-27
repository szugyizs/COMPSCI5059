package project.requests;

import project.requests.course.Course;
import project.requests.course.Qualification;

public class CourseRequirement 
{

	private Course course;
	private int contactHours;
	private String typeOfContact;
	private int numberOfStaff;
	private Qualification qualification;
	private int classSize;
	
	public CourseRequirement(final Course course, final int contactHours, final String typeOfContact,
							 final int numberOfStaff, final Qualification qualification, final int classSize)
	{
		this.course = course;
		this.contactHours = contactHours;
		this.typeOfContact = typeOfContact;
		this.numberOfStaff = numberOfStaff;
		this.qualification = qualification;
		this.classSize = classSize;
	}
	
	public int getContactHours() {
		return contactHours;
	}

	public void setContactHours(int contactHours) {
		this.contactHours = contactHours;
	}

	public String getTypeOfContact() {
		return typeOfContact;
	}

	public void setTypeOfContact(String typeOfContact) {
		this.typeOfContact = typeOfContact;
	}

	public int getNumberOfStaff() {
		return numberOfStaff;
	}

	public void setNumberOfStaff(int numberOfStaff) {
		this.numberOfStaff = numberOfStaff;
	}

	public int getClassSize() {
		return classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}
	
	public Course getCourse()
	{
		return course;
	}
	
	public void setCourse(final Course course)
	{
		this.course = course;
	}

	public Qualification getSkill() {
		return qualification;
	}

	public void setSkill(Qualification skill) {
		this.qualification = skill;
	}

}
