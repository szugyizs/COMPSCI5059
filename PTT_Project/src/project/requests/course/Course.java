package project.requests.course;

import java.util.HashMap;
import java.util.List;

import project.requests.RequestStatusType;
import project.requests.TeachingRequest;

public class Course
{

	private String courseID;
	private String name;
	private String description;

	private HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequests; // The TeachingRequest made for the
																					// given contact type.
	private HashMap<ContactType, List<Teacher>> teachingStaff; // ContactType's alongside the teachers taking the
																// classes.

	/**
	 * Constructor explicitly instantiates the instance.
	 * 
	 * @param courseID                          Is the string instance representing
	 *                                          the unique id of the course.
	 * @param name                              Is the string instance representing
	 *                                          the name of the course.
	 * @param description                       The course description.
	 * @param teachingStaffRequirementsRequests A mapping from the ContactType to
	 *                                          the TeachingRequest.
	 * @param teachingStaff                     A Mapping from ContactType to the
	 *                                          list of teachers.
	 */
	public Course(final String courseID, final String name, final String description,
			final HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequests,
			final HashMap<ContactType, List<Teacher>> teachingStaff)
	{
		this.courseID = courseID;
		this.name = name;
		this.description = description;
		this.teachingStaffRequirementsRequests = teachingStaffRequirementsRequests;
		this.teachingStaff = teachingStaff;
	}

	/**
	 * Constructor instantiates an instance of Course. This constructor implicitly
	 * initialises the teachingStaffRequirementsRequests and teachingStaff HashMaps
	 * to new empty HashMaps.
	 * 
	 * @param courseID    Representing the unique id of the course.
	 * @param name        Representing the user friendly name of the course.
	 * @param description The course description.
	 */
	public Course(final String courseID, final String name, final String description)
	{
		this(courseID, name, description, new HashMap<ContactType, TeachingRequest>(),
				new HashMap<ContactType, List<Teacher>>());
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
	 * @return The map containing the map of teacher requirement requests. Return a
	 *         new instance to prevent the users from bypassing the limitations /
	 *         checks imposed by the Qualifications class.
	 */
	public HashMap<ContactType, TeachingRequest> getTeachingStaffRequirementsRequests()
	{
		return new HashMap<ContactType, TeachingRequest>(teachingStaffRequirementsRequests);
	}

	/**
	 * Setter for the map of teachingStaffRequirementsRequests.
	 * 
	 * @param teachingStaffRequirementsRequest Represents the map of teaching
	 *                                         requirements requests.
	 */
	public boolean setTeachingStaffRequirementsRequests(
			final HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequests)
	{
		// TODO Implement.
		// Hint: look at checkSkillBounds for a HashMap.
		for (final ContactType contactType : teachingStaffRequirementsRequests.keySet())
		{
			if (this.teachingStaffRequirementsRequests.get(contactType)
					.getRequestStatus() == RequestStatusType.ACCEPTED)
			{
				return false;
			}
		}

		this.teachingStaffRequirementsRequests.clear();
		this.teachingStaffRequirementsRequests.putAll(teachingStaffRequirementsRequests);
		this.teachingStaff.clear();
		
		return true;
	}

	/**
	 * Sets the TeachingRequest for a given ContactType.
	 * 
	 * @param contactType     The ContactType for which the TeachingRequest is set.
	 * @param teachingRequest The TeachingRequest instance that is to be set for the
	 *                        said contactType.
	 * @return True if the TeachingRequest instance has been successfully set, false
	 *         otherwise.
	 */
	public boolean setTeachingStaffRequirementsRequest(final ContactType contactType,
			final TeachingRequest teachingRequest)
	{
		// Hint: look at checkSkillBounds
		// Hint: call checkTeachingRequrementRequest.

		// TODO: would it not be simpler and make more sense if setting a new
		// teachingRequest, just deletes the old stuff?
		if (teachingRequest.getRequestStatus() == RequestStatusType.ACCEPTED)
		{
			return false;
		}

		this.teachingStaffRequirementsRequests.put(contactType, teachingRequest);
		this.teachingStaff.remove(contactType); // Does not fail if noting is mapped
		return true;
	}

	// TODO: removed for now. Setting a new teaching request clears the teacher list for now.
//	/**
//	 * Method takes into account any teachers that are present within the
//	 * teachingStaff HashMap. For instance, if there are teachers present, all of
//	 * the teachers must meet the TeachingRequest requirements that are being set,
//	 * otherwise it will not be set; if there are more current teachers than the
//	 * requirements (teachingRequest) parameter defines, it will not pass.
//	 * 
//	 * Moreover, if the teachingRequest cannot be set if the request status is
//	 * DENIED, for instance.
//	 * 
//	 * @param contactType     The ContactType for which we are setting.
//	 * @param teachingRequest The TeachingRequest instance that is to be checked for
//	 *                        the said contactType.
//	 * @return True if the new TeachingRequirements can be set, false otherwise.
//	 */
//	// TODO: I do not quite understand where this function would be needed? Why
//	// should it be possible to change the teaching requirement once the teachers
//	// are set? also, this is a private function...
//	// why not just delete the teacher list if this is set?
//	private boolean checkTeachingRequirementRequest(final ContactType contactType,
//			final TeachingRequest teachingRequest)
//	{
//		// hint, loop throuch teachers,
//		// teacher.getMissingRequirements(teachingRequest.getCourseRequirements()).empty
//		// -> no skills are missing 'n the fucker can teach lo.
//
//		if (teachingRequest.getRequestStatus() != RequestStatusType.PENDING)
//		{
//
//			System.out.println(String.format("Only pending requests can be changed."));
//			return false;
//
//		}
//
//		return false;
//	}

	/**
	 * Getter for the HashMap of ContactType and their corresponding Teacher
	 * instances.
	 * 
	 * @return The HashMap instance containing the ContactType alongside the
	 *         allocated teachers.
	 */
	public HashMap<ContactType, List<Teacher>> getTeachingStaff()
	{
		return this.teachingStaff;
	}

	/**
	 * Set, but check using checkTeacher //TODO: I do not understand what this
	 * function is supposed to do..? I this supposed to add a lot of teachers at the
	 * same time?
	 * 
	 * use checkTeacher for the contact type!! Look at user stories to see what to
	 * check for!
	 * 
	 * @param teachingStaff
	 * @return
	 */
	public boolean setTeachingStaff(final HashMap<ContactType, List<Teacher>> teachingStaff)
	{

		if ((teachingStaff != null) && (checkTeacher(teachingStaff) == true))
		{
			this.teachingStaff.clear();
			this.teachingStaff.putAll(teachingStaff);
		}

		return false;
		
	}

	/**
	 * Add a teacher for the given contact type. Also, check if the teacher meets
	 * the requirements for the contact type.
	 * 
	 * Make sure that the number of teachers does not exceed the course
	 * requirements.
	 * 
	 * @param contactType
	 * @param teacher
	 * @return
	 */
	public boolean addTeachingStaff(final ContactType contactType, final Teacher teacher)
	{
		int neededNbrStaff = this.teachingStaffRequirementsRequests.get(contactType).getCourseRequirement()
				.getNumberOfStaff();

		int currentNbrStaff = this.teachingStaff.get(contactType).size();

		if (this.teachingStaff.get(contactType).contains(teacher) == true)
		{
			System.out.println(String.format("Teacher is already allocated."));
		} else if (neededNbrStaff > currentNbrStaff)
		{
			System.out.println(String.format("Enough staff has been allocated."));
		} else
		{
			if (checkTeacher(contactType, teacher) == true)
			{
				this.teachingStaff.get(contactType).add(teacher);
				System.out.println(String.format("Teacher added to course."));
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove a teacher from a given contact type.
	 * 
	 * @param contactType The contact type where to remove the teacher from.
	 * @param guid        The guid of the teacher that should be removed.
	 * @return True if successful, false otherwise.
	 */
	public boolean removeTeacher(final ContactType contactType, final String guid)
	{

		for (Teacher teacher : this.teachingStaff.get(contactType))
		{
			if (teacher.getGUID().equalsIgnoreCase(guid) == true)
			{
				return removeTeacher(contactType, teacher);
			}
		}

		return false;
	}

	/**
	 * Remove a teacher from a given contact type.
	 * 
	 * @param contactType The contact type where to remove the teacher from.
	 * @param teacher     The teacher that should be removed.
	 * @return True if successful, false otherwise.
	 */
	public boolean removeTeacher(final ContactType contactType, final Teacher teacher)
	{

		if ((this.teachingStaff.containsKey(contactType) == true) &&
			(this.teachingStaff.get(contactType).contains(teacher) == true))
		{
			return this.teachingStaff.get(contactType).remove(teacher);
		}
	
		return false;
	}

	/**
	 * Checks whether or not the HashMap of teachingStaff meets the requirements for
	 * all the the ContactTypes.
	 * 
	 * @param teachingStaff
	 * @return True if the all the teachers for all the meet the requirements for
	 *         all the the ContactTypes, false otherwise.
	 */
	private boolean checkTeacher(final HashMap<ContactType, List<Teacher>> teachingStaff)
	{
		for (final ContactType contactType : teachingStaff.keySet())
		{
			for (Teacher teacher : teachingStaff.get(contactType))
			{
				if (checkTeacher(contactType, teacher) == false)
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if teacher meets requirements for the specified contact type.
	 * 
	 * @param contactType The contact type where to remove the teacher from.
	 * @param teacher     The teacher that should be removed.
	 * @return True if the teacher meets the requirements for the ContactType, false
	 *         otherwise.
	 */
	private boolean checkTeacher(final ContactType contactType, final Teacher teacher)
	{
		Qualifications reqiredQualifications = this.teachingStaffRequirementsRequests.get(contactType)
				.getCourseRequirement().getRequiredStaffQualifications();
		Qualifications missingQualifications = teacher.getMissingSkills(reqiredQualifications);

		if (missingQualifications != null)
		{
			System.err.println(
					String.format("Teacher is missing these qualificaitons: %s", missingQualifications.toString()));
			return false;
		}

		return true;
	}

}
