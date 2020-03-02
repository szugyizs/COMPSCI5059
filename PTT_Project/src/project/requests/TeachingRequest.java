package project.requests;

import java.io.PrintStream;

public class TeachingRequest extends Request
{

	private CourseRequirement courseRequirement; // The course requirement for which the teaching request is being submitted.

	/**
	 * Explicit constructor used to initialise an instance of the TeachingRequest.
	 * 
	 * @param requestStatus The status of the request.
	 * @param courseRequirement The CourseRequirement instance for which the
	 * 		request is being made.
	 */
	public TeachingRequest(final RequestStatusType requestStatus,
			final CourseRequirement courseRequirement)
	{
		super(requestStatus);
		this.courseRequirement = courseRequirement;
	}

	/**
	 * Implicit constructor used to initialise the instance of TeachingRequest; the
	 * request status of the Request is set to PENDING implicitly.
	 * 
	 * @param courseRequirement The instance of the CourseRequirement for which
	 * 		the request is being made.
	 */
	public TeachingRequest(final CourseRequirement courseRequirement)
	{
		this(RequestStatusType.PENDING, courseRequirement);
	}

	/**
	 * Getter for the CourseRequirement instance for which the request is being
	 * made.
	 * 
	 * @return An instance of the CourseRequirement; can return null.
	 */
	public CourseRequirement getCourseRequirement()
	{
		return this.courseRequirement;
	}

	/**
	 * Setter for the CourseRequirement; should not be used. However, if used, do
	 * not set this to null!
	 * 
	 * @param courseRequirement The instance for which the request is being made.
	 */
	public void setCourseRequirement(final CourseRequirement courseRequirement)
	{
		this.courseRequirement = courseRequirement;
	}
	
	@Override
	public void printRequest(final PrintStream printStream)
	{
		super.printRequest(printStream);
		courseRequirement.printCourseRequirement(printStream);
	}
	
	@Override
	public String toString()
	{
		return String.format("Status: %s; Course Requirements: %s", courseRequirement.toString());
	}

}
