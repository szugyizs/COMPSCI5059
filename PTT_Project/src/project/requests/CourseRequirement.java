package project.requests;

import project.requests.course.Course;
import project.requests.course.Qualifications;

public class CourseRequirement 
{

	private Course course;
	
	private short numLecturers;
	private short numTutors;
	private short numDemonstrators;
	private short numStudents;
	
	private short lectureContactHours;
	private short tutorialContactHours;
	private short labContactHours; 
	
	private Qualifications requiredStaffQualifications;
	
	/**
	 * Constructor is used to explicitly initialise the course requirements with the required parameters.
	 * 
	 * @param course Instance for which the requirements are being requested. 
	 * @param numLecturers Represents the number of lecturers required for the said Course.
	 * @param numTutors Represents the number of tutors required for tutorials.
	 * @param numDemonstrators Represents the number of lab demonstrators required for the said Course.
	 * @param numStudents Maximum number of students taking the Course.
	 * @param lectureContactHours Number of lecture contact hours for the said Course.
	 * @param tutorialContactHours Number of tutorials for the said Course instance.
	 * @param labContactHours Number of lab contact hours for the said Course instance.
	 * @param requiredStaffQualifications The qualifications required by lecturers, tutors, and demonstrators.
	 */
	public CourseRequirement(final Course course, final short numLecturers, final short numTutors, 
			final short numDemonstrators, final short numStudents, final short lectureContactHours, 
			final short tutorialContactHours, final short labContactHours, final Qualifications requiredStaffQualifications)
	{
		this.course = course;
		
		this.numLecturers = numLecturers;
		this.numTutors = numTutors;
		this.numDemonstrators = numDemonstrators;
		this.numStudents = numStudents;
		
		this.lectureContactHours = lectureContactHours;
		this.tutorialContactHours = tutorialContactHours;
		this.labContactHours = labContactHours;
		
		this.requiredStaffQualifications = requiredStaffQualifications;
	}
	
	/**
	 * @return The Course for which the requirements are being made.
	 */
	public Course getCourse() 
	{
		return course;
	}

	/**
	 * @param course Course instance for which the requirements are being made.
	 */
	public void setCourse(final Course course) 
	{
		this.course = course;
	}
	
	/**
	 * @return The number of required lecturers for the Course instance.
	 */
	public short getNumLecturers() 
	{
		return numLecturers;
	}

	/**
	 * @param numLecturers The number of lecturers required for the Course instance.
	 */
	public void setNumLecturers(final short numLecturers)
	{
		this.numLecturers = numLecturers;
	}

	/**
	 * @return The number of tutors required to take the tutorials for the Course.
	 */
	public short getNumTutors() 
	{
		return numTutors;
	}

	/**
	 * @param numTutors The number of tutors required to take the tutorials for the course.
	 */
	public void setNumTutors(final short numTutors)
	{
		this.numTutors = numTutors;
	}

	public short getNumDemonstrators() 
	{
		return numDemonstrators;
	}

	public void setNumDemonstrators(final short numDemonstrators)
	{
		this.numDemonstrators = numDemonstrators;
	}

	public short getNumStudents()
	{
		return numStudents;
	}

	public void setNumStudents(final short numStudents) 
	{
		this.numStudents = numStudents;
	}
	
	public short getLectureContactHours() 
	{
		return lectureContactHours;
	}

	public void setLectureContactHours(final short lectureContactHours) 
	{
		this.lectureContactHours = lectureContactHours;
	}

	public short getTutorialContactHours()
	{
		return tutorialContactHours;
	}

	public void setTutorialContactHours(final short tutorialContactHours) 
	{
		this.tutorialContactHours = tutorialContactHours;
	}

	public short getLabContactHours()
	{
		return labContactHours;
	}

	public void setLabContactHours(final short labContactHours) 
	{
		this.labContactHours = labContactHours;
	}

	public Qualifications getRequiredStaffQualifications()
	{
		return requiredStaffQualifications;
	}

	public void setRequiredStaffQualifications(final Qualifications requiredStaffQualifications)
	{
		this.requiredStaffQualifications = requiredStaffQualifications;
	}
	
	@Override
	public String toString()
	{
		return String.format("Course: %s, Lecturers: %d, Tutors: %d, Demonstrators: %d, Students: %d," 
				+ " Lecture contact hours: %d, Tutorial contact hours: %d, Lab contact hours: %d, Qualifications: %s",
				course.getName(),
				numLecturers, 
				numTutors, 
				numDemonstrators,
				numStudents,
				lectureContactHours, 
				tutorialContactHours, 
				labContactHours, 
				requiredStaffQualifications.toString());
	}

}
