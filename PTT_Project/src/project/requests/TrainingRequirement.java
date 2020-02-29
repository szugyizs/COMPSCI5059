package project.requests;

import project.requests.course.Qualifications;
import project.requests.course.Teacher;

public class TrainingRequirement 
{
	
	private Teacher teacher;
	private Qualifications qualifications;
	
	/**
	 * Constructor explicitly instantiates the TrainingRequirement instance.
	 * 
	 * @param teacher The Teacher [instance] that requires additional training.
	 * @param qualifications The Qualifications [instance] that'll be obtained post training.
	 */
	public TrainingRequirement(final Teacher teacher, final Qualifications qualifications)
	{
		this.teacher = teacher;
		this.qualifications = qualifications;
	}
	
	/**
	 * @return The Teacher [instance] that requires additional training.
	 */
	public Teacher getTeacher() 
	{
		return teacher;
	}
	
	/**
	 * @param teacher The Teacher [instance] that requires additional training.
	 */
	public void setTeacher(final Teacher teacher) 
	{
		this.teacher = teacher;
	}
	
	/**
	 * @return The qualifications that are to be obtained post training by the Teacher instance.
	 */
	public Qualifications getQualifications()
	{
		return qualifications;
	}
	
	/**
	 * @param qualifications The qualifications that are to be obtained post training by the Teacher instance.
	 */
	public void setQualifications(final Qualifications qualifications)
	{
		this.qualifications = qualifications;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s %s for %s", 
				teacher.getForename(),
				teacher.getSurname(), 
				qualifications.toString());
	}
	
}
