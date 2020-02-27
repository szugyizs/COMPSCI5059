package project.course;

public class CourseRequirement 
{

	private Course course;
	
	// Attribs.
	
	public CourseRequirement(final Course course)
	{
		this.course = course;
	}
	
	public Course getCourse()
	{
		return course;
	}
	
	public void setCourse(final Course course)
	{
		this.course = course;
	}

}
