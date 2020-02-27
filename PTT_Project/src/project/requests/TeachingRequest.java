package project.requests;

import project.course.CourseRequirement;

public class TeachingRequest extends Request
{
	
	private CourseRequirement courseRequirement;
	
	/**
	 * Explicit constructor used to initialise an instance of the TeachingRequest.
	 * 
	 * @param requestStatus - the status of the request.
	 * @param courseRequirement - the CourseRequirement instance for which the request is being made.
	 */
	public TeachingRequest(final RequestStatus requestStatus, final CourseRequirement courseRequirement)
	{
		super(requestStatus);
		this.courseRequirement = courseRequirement;
	}
	
	/**
	 * Implicit constructor used to initialise the instance of TeachingRequest;
	 * the request status of the Request is set to PENDING implicitly.
	 * 
	 * @param courseRequirement - the instance of the CourseRequirement for which the request is being made.
	 */
	public TeachingRequest(final CourseRequirement courseRequirement)
	{
		this(RequestStatus.PENDING, courseRequirement);
	}
	
	/**
	 * Getter for the CourseRequirement instance for which the request is being made.
	 * 
	 * @return - an instance of the CourseRequirement; can return null.
	 */
	public CourseRequirement getCourseRequirement()
	{
		return courseRequirement;
	}
	
	/**
	 * Setter for the CourseRequirement; should not be used. However, if used, do not
	 * set this to null!
	 * 
	 * @param courseRequirement - the instance for which the request is being made.
	 */
	public void setCourseRequirement(final CourseRequirement courseRequirement)
	{
		this.courseRequirement = courseRequirement;
	}
	
}
