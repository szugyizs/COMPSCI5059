package project.requests;

import project.course.CourseRequirement;

public class TeachingRequest extends Request
{
	
	private CourseRequirement courseRequirement;
	
	public TeachingRequest(final RequestStatus requestStatus, final CourseRequirement courseRequirement)
	{
		super(requestStatus);
		
		this.courseRequirement = courseRequirement;
	}
	
	public TeachingRequest(final CourseRequirement courseRequirement)
	{
		this(RequestStatus.PENDING, courseRequirement);
	}
	
	public CourseRequirement getCourseRequirement()
	{
		return courseRequirement;
	}
	
	public void setCourseRequirement(final CourseRequirement courseRequirement)
	{
		this.courseRequirement = courseRequirement;
	}
	
}
