package project.requests.course;

import java.util.HashMap;
import java.util.List;	

import project.requests.TeachingRequest;	


public class Course
{

	private String courseID;
	private String name;
	private String description;
	
	private HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequests; // The TeachingRequest made for the given contact type.
	private HashMap<ContactType, List<Teacher>> teachingStaff; // ContactType's alongside the teachers taking the classes.

	/**
	 * Constructor explicitly instantiates the instance.
	 * 
	 * @param courseID Is the string instance representing the unique id of the course.
	 * @param name Is the string instance representing the name of the course.
	 * @param description The course description.
	 * @param teachingStaffRequirementsRequests A mapping from the ContactType to the TeachingRequest.
	 * @param teachingStaff A Mapping from ContactType to the list of teachers.
	 */
	public Course(final String courseID, final String name, final String description,
			final HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequests,
			final HashMap<ContactType, Teacher> teachingStaff)
	{
		// TODO: Implement this.
	}
	
	/**
	 * Constructor instantiates an instance of Course. This constructor implicitly
	 * initialises the teachingStaffRequirementsRequests and teachingStaff HashMaps to new empty HashMaps.
	 * 
	 * @param courseID Representing the unique id of the course.
	 * @param name Representing the user friendly name of the course.
	 * @param description The course description.
	 */
	public Course(final String courseID, final String name, final String description)
	{
		
		// TODO: Hint, look at this(....) to invoke the explicit constructor i.e. look at Qualifications constructors.
		
		this.courseID = courseID;
		this.name = name;
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
	 * Setter for the course name.
	 * 
	 * @param The name [String] of the course.
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

	/**
	 * Getter for the map of ContactTypes and the requirements requests.
	 * 
	 * @return The map containing the map of teacher requirement requests.
	 */
	public HashMap<ContactType, TeachingRequest> getTeachingStaffRequirementsRequests()
	{
		// TODO: Implement.
		return null;
	}
	
	/**
	 * Setter for the map of teachingStaffRequirementsRequests. 
	 * 
	 * @param teachingStaffRequirementsRequest Represents the map of teaching requirements requests.
	 */
	public boolean setTeachingStaffRequirementsRequests(final HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequest)
	{
		// TODO Implement.
		// Hint: look at checkSkillBounds for a HashMap.
		return false;
	}
	
	/**
	 * Sets the TeachingRequest for a given ContactType. 
	 * 
	 * @param contactType The ContactType for which the TeachingRequest is set.
	 * @param teachingRequest The TeachingRequest instance that is to be set for the said contactType.
	 * @return True if the TeachingRequest instance has been successfully set, false otherwise.
	 */
	public boolean setTeachingStaffRequirementsRequest(final ContactType contactType, final TeachingRequest teachingRequest)
	{
		// Hint: look at checkSkillBounds
		// Hint: call checkTeachingRequrementsRequest.
		return false;
	}
	
	/**
	 * Method takes into account any teachers that are present within the teachingStaff HashMap. 
	 * For instance, if there are teachers present, all of the teachers must meet the TeachingRequest 
	 * requirements that are being set, otherwise it will not be set; if there are more current teachers than the
	 * requirements (teachingRequest) parameter defines, it will not pass. 
	 * 
	 * Moreover, if the teachingRequest cannot be set if the request status is DENIED, for instance.
	 * 
	 * @param contactType The ContactType for which we are setting.
	 * @param teachingRequest The TeachingRequest instance that is to be checked for the said contactType.
	 * @return True if the new TeachingRequirements can be set, false otherwise.
	 */
	private boolean checkTeachingRequirementRequest(final ContactType contactType, final TeachingRequest teachingRequest)
	{
		// hint, loop throuch teachers, teacher.getMissingRequirements(teachingRequest.getCourseRequirements()).empty -> no skills are missing 'n the fucker can teach lo.
		return false;
	}
	
	/**
	 * Getter for the HashMap of ContactType and their corresponding Teacher instances.
	 * 
	 * @return The HashMap instance containing the ContactType alongside the allocated teachers.
	 */
	public HashMap<ContactType, List<Teacher>> getTeachingSTaff()
	{
		return null;
	}
	
	/**
	 * Set, but check using checkTeacher
	 * 
	 * use checkTeacher for the contact type!!
	 * Look at user stories to see what to check for!
	 * 
	 * @param teachingStaff
	 * @return
	 */
	public boolean setTeachingStaff(final HashMap<ContactType, List<Teacher>> teachingStaff)
	{
		return false;
	}
	
	/**
	 * Add a teacher for the given contact type. Also, check if the teacher meets the requirements
	 * for the contact type.
	 * 
	 * Make sure that the number of teachers does not exceed the course requirements.
	 * 
	 * @param contactType
	 * @param teacher
	 * @return
	 */
	public boolean addTeachingStaff(final ContactType contactType, final Teacher teacher)
	{
		return false;
	}
	
	public boolean removeTeacher(final ContactType contactType, final String guid)
	{
		// Hint find the teacher with the guid from the list that corresponds to the contact type 
		// pass this onto the removeTeacher method i.e. the found instance.
		// If not found, return false.
		return false;
	}
	
	/**
	 * Remove a teacher from a given contact type.
	 * @param contactType
	 * @param teacher
	 * @return True if successful, false otherwise.
	 */
	public boolean removeTeacher(final ContactType contactType, final Teacher teacher)
	{
		return false;
	}
	
	/**
	 * Do the opposite, check if the teacher meets the requirements for the particular 
	 * contacttype i.e. query teachingRequirementsRequest's course requirements & qualifications.
	 * 
	 * @param contactType
	 * @param teacher
	 * @return True if the teacher meets the requirements for the ContactType, false otherwise.
	 */
	public boolean checkTeacher(final ContactType contactType, final Teacher teacher)
	{
		return false;
	}
	
}
