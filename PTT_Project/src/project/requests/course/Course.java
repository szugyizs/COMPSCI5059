package project.requests.course;

public class Course
{

	private String courseID;
	private String name;
	private String description;

	/**
	 * Constructor explicitly instantiates the Course instance.
	 * 
	 * @param courseID The courseID [String] of the course
	 * @param name     The name [String] of the course
	 */
	protected Course(final String courseID, final String name)
	{
		this.courseID = courseID;
		this.name = name;
	}

	/**
	 * Constructor explicitly instantiates the Course instance.
	 * 
	 * @param courseID    The courseID [String] of the course
	 * @param name        The name [String] of the course
	 * @param description The description [String] of the course
	 */
	protected Course(final String courseID, final String name, final String description)
	{
		this.courseID = courseID;
		this.name = name;
		this.description = description;
	}

	/**
	 * Getter for the course name.
	 * 
	 * @return The name [String] of the course.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Getter for the course name.
	 * 
	 * @return The name [String] of the course.
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Getter for the course ID.
	 * 
	 * @return The courseID [String] of the course.
	 */
	public String getCourseID()
	{
		return courseID;
	}

	/**
	 * Setter for the course ID.
	 * 
	 * @param The courseID [String] of the course.
	 */
	public void setCourseID(String courseID)
	{
		this.courseID = courseID;
	}

	/**
	 * Getter for the course description.
	 * 
	 * @return The description [String] of the course.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Setter for the course description.
	 * 
	 * @param description The description [String] of the course.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

}
