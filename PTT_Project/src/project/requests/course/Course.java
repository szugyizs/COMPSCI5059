package project.requests.course;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.requests.CourseRequirement;
import project.requests.RequestStatusType;
import project.requests.TeachingRequest;

public class Course
{

	private String courseID; // Used to uniquely identify the course.
	private String name; // The name of the course.
	private String description; // Course description.

	// The teaching requests submitted for the course; one for each ContactType.
	private HashMap<ContactType, TeachingRequest> teachingStaffRequirementsRequests;

	// Lists containing the teaching staff for each of the ContactTypes.
	private HashMap<ContactType, List<Teacher>> teachingStaff;

	/**
	 * Constructor explicitly instantiates the instance, defining all Course
	 * parameters in the process.
	 * 
	 * @param courseID                          Is the string instance representing
	 *                                          the unique id of the course.
	 * @param name                              Is the string instance representing
	 *                                          the user friendly name of the
	 *                                          course.
	 * @param description                       The course description.
	 * @param teachingStaffRequirementsRequests A mapping from the ContactType to
	 *                                          the TeachingRequest i.e. teaching
	 *                                          requirements that must be met by the
	 *                                          teachers.
	 * @param teachingStaff                     A Mapping from ContactType to the
	 *                                          list of teachers i.e. teachers that
	 *                                          teach for each of the ContactTypes.
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
		this.courseID = courseID;
		this.name = name;
		this.description = description;

		this.teachingStaffRequirementsRequests = new HashMap<ContactType, TeachingRequest>();

		this.teachingStaff = new HashMap<ContactType, List<Teacher>>();
		this.teachingStaff.put(ContactType.LAB, new ArrayList<Teacher>());
		this.teachingStaff.put(ContactType.TUTORIAL, new ArrayList<Teacher>());
		this.teachingStaff.put(ContactType.LECTURE, new ArrayList<Teacher>());
	}

	/**
	 * Getter for the course ID.
	 * 
	 * @return The unique course id for the course.
	 */
	public String getCourseID()
	{
		return this.courseID;
	}

	/**
	 * Setter for the course ID.
	 * 
	 * @param The unique courseID for the course.
	 */
	public void setCourseID(String courseID)
	{
		this.courseID = courseID;
	}

	/**
	 * Getter for the course name.
	 * 
	 * @return The user friendly name of the course.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Setter for the course name.
	 * 
	 * @param The user friendly name of the course.
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Getter for the course description.
	 * 
	 * @return A string instance of the general course description.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Setter for the course description.
	 * 
	 * @param description The user friendly general course description.
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
		return this.teachingStaffRequirementsRequests;
	}

	/**
	 * Sets the TeachingRequest for a given ContactType from a course request.
	 * 
	 */
	public boolean addCourseRequirement(final CourseRequirement courseRequirement)
	{

		ContactType key = courseRequirement.getContactType();

		// Simply check that the requirements have not been accepted, we assume that
		// they're being overwritten.
		if (this.teachingStaffRequirementsRequests.containsKey(key)) {
			if (this.teachingStaffRequirementsRequests.get(key).getRequestStatus() == RequestStatusType.ACCEPTED) {
				return false;
			}
			// Potentially clear the teaching staff.
			this.teachingStaff.put(key, new ArrayList<Teacher>());
		}

		this.teachingStaffRequirementsRequests.put(key, new TeachingRequest(courseRequirement));
		return true;

	}

	/**
	 * Checks whether or not the course has a ContactType TeachingRequest.
	 * 
	 * @param contactType The ContactType being checked.
	 * @return True if the ContactType TeachingRequest exists, false otherwise.
	 */
	public boolean hasContactTypeTeachingRequest(final ContactType contactType)
	{
		return teachingStaffRequirementsRequests.containsKey(contactType);
	}

	/**
	 * Checks whether or not the course has a ContactType with the provided
	 * RequestStatusType.
	 * 
	 * @param contactType       The ContactType that is being queried.
	 * @param requestStatusType The state of the contactType TeachingRequest.
	 * @return True if the course possesses the contactType with the state
	 *         requestStatusType.
	 */
	public boolean hasContactTypeTeachingRequest(final ContactType contactType,
			final RequestStatusType requestStatusType)
	{
		return teachingStaffRequirementsRequests.containsKey(contactType)
				&& teachingStaffRequirementsRequests.get(contactType).getRequestStatus() == requestStatusType;
	}

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
	 * Add a teacher for the given contact type. Also, check if the teacher meets
	 * the requirements for the contact type.
	 * 
	 * Make sure that the number of teachers does not exceed the course
	 * requirements.
	 * 
	 * @param contactType The ContactType for which the teaching is being added.
	 * @param teacher     The teacher instance being added to the course.
	 * @return True if the teacher has been successfully added, false otherwise.
	 */
	public boolean addTeachingStaff(final ContactType contactType, final Teacher teacher)
	{

		// The requirements must be present.
		if (!this.teachingStaffRequirementsRequests.containsKey(contactType)
				|| !this.teachingStaff.containsKey(contactType)) {
			return false;
		}

		// Can't add a teacher twice.
		else if (this.teachingStaff.get(contactType).contains(teacher) == true) {
			return false;
		}

		// Check that the teacher allocation meets the requirements.
		else if (!checkTeacherMeetsRequirements(contactType, teacher)) {
			return false;
		}

		// Finally, add the teacher.
		return this.teachingStaff.get(contactType).add(teacher);
	}

	/**
	 * Remove a teacher from a given contact type.
	 * 
	 * @param contactType The contact type from which the teacher instance is being
	 *                    removed from.
	 * @param teacher     The teacher that should be removed.
	 * @return True if successful, false otherwise.
	 */
	public boolean removeTeacher(final ContactType contactType, final Teacher teacher)
	{
		if (this.teachingStaff.containsKey(contactType) == true
				&& this.teachingStaff.get(contactType).contains(teacher) == true) {
			return this.teachingStaff.get(contactType).remove(teacher);
		}
		return false;
	}

	/**
	 * Checks whether or not the HashMap of teachingStaff meets the requirements for
	 * all the the ContactTypes.
	 * 
	 * @param teachingStaff The map containing the list of teacher for each of the
	 *                      ContactType's.
	 * @return True if the all the teachers for all the meet the requirements for
	 *         all the the ContactTypes, false otherwise.
	 */
	public boolean checkTeachersMeetRequirements(final HashMap<ContactType, List<Teacher>> teachingStaff)
	{
		for (final ContactType contactType : teachingStaff.keySet()) {
			if (!checkTeachersMeetRequirements(contactType, teachingStaff.get(contactType))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether or not a list of teachers meet the meet the course
	 * requirements for a given TeachingRequest.
	 * 
	 * @param contactType The contact type for which we're checking the list of
	 *                    teachers against.
	 * @param teachers    The list of teachers being checked against the
	 *                    teachingRequest.
	 * @return True if the requirements have been met for all teachers with respect
	 *         to the teachingRequets, false otherwise.
	 */
	public boolean checkTeachersMeetRequirements(final ContactType contactType, final List<Teacher> teachers)
	{
		if (!teachingStaffRequirementsRequests.containsKey(contactType)) {
			return false;
		}
		final TeachingRequest teachingRequest = this.teachingStaffRequirementsRequests.get(contactType);
		return checkTeachersMeetRequirements(teachingRequest, teachers);
	}

	/**
	 * Checks whether or not a list of teachers meet the meet the course
	 * requirements for a given TeachingRequest.
	 * 
	 * @param teachingRequest The instance against which we're checking the
	 *                        requirements of all teachers.
	 * @param teachers        The list of teachers being checked against the
	 *                        teachingRequest.
	 * @return True if the requirements have been met for all teachers with respect
	 *         to the teachingRequets, false otherwise.
	 */
	public boolean checkTeachersMeetRequirements(final TeachingRequest teachingRequest, final List<Teacher> teachers)
	{
		// Check whether or not there are teachers present, if not, requirements are
		// _not_ met.
		if (teachingRequest == null || teachers == null) {
			return false;
		} else if (teachers.isEmpty()) {
			return false;
		}

		// Check whether individual teachers meet the teaching requirements.
		for (final Teacher teacher : teachers) {
			if (!checkTeacherMeetsRequirements(teachingRequest, teacher)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether or not the teacher would meet the requirements for the course.
	 * This is called prior to adding the teaching to the course.
	 * 
	 * @param contactType Defines the requirements against which the teacher is
	 *                    being checked against.
	 * @param teacher     The instance that is to be added to the course.
	 * @return True if the requirements are met, false otherwise.
	 */
	public boolean checkTeacherMeetsRequirements(final ContactType contactType, final Teacher teacher)
	{

		// Check if there are requirements that are present.
		if (!this.teachingStaffRequirementsRequests.containsKey(contactType)) {
			return false;
		}

		// Finally check that the teacher meets the contact requirements.
		final TeachingRequest teachingRequest = teachingStaffRequirementsRequests.get(contactType);
		return checkTeacherMeetsRequirements(teachingRequest, teacher);
	}

	/**
	 * Checks whether or not the teacher would meet the requirements for the course.
	 * This is called prior to adding the teaching to the course, for instance.
	 * 
	 * @param teachingRequest Defines the requirements against which the teacher is
	 *                        being checked against.
	 * @param teacher         The instance that is to be added to the course.
	 * @return True if the requirements are met, false otherwise.
	 */
	public boolean checkTeacherMeetsRequirements(final TeachingRequest teachingRequest, final Teacher teacher)
	{
		// Teaching request and teacher must not be null; the teaching request must be
		// accepted.
		if (teachingRequest == null || teacher == null) {
			return false;
		} else if (teachingRequest.getRequestStatus() != RequestStatusType.ACCEPTED) {
			return false;
		}

		// Check that the number of teaching staff does not exceed the new requirements.
		final ContactType contactType = teachingRequest.getCourseRequirement().getContactType();
		if (!this.teachingStaff.containsKey(contactType) || teachingRequest.getCourseRequirement()
				.getNumberOfStaff() < this.teachingStaff.get(contactType).size()) {
			return false;
		}

		// Finally, check the teachers qualifications.
		return checkTeacherMeetsQualifications(contactType, teacher);
	}

	/**
	 * Checks whether or not a teacher instance meets the qualifications for a given
	 * ContactType.
	 * 
	 * @param contactType The ContactType for which we're checking the teachers
	 *                    qualifications against.
	 * @param teacher     The teacher instance that is being checked.
	 * @return True if the teacher meets the qualifications, false otherwise - if
	 *         the qualifications have not been set, for instance.
	 */
	public boolean checkTeacherMeetsQualifications(final ContactType contactType, final Teacher teacher)
	{
		if (!this.teachingStaffRequirementsRequests.containsKey(contactType)) {
			return false;
		}

		// Checks whether the teacher meets the qualifications for the given
		// contactType.
		final TeachingRequest teachingRequest = this.teachingStaffRequirementsRequests.get(contactType);
		return checkTeacherMeetsQualifications(teachingRequest, teacher);
	}

	/**
	 * Checks whether or not a teacher instance meets the qualifications for a given
	 * teachingRequest.
	 * 
	 * @param teachingRequest The teaching requirements request being checked.
	 * @param teacher         The teacher instance that is being checked.
	 * @return True if the teacher meets the qualifications, false otherwise - if
	 *         the qualifications have not been set, for instance.
	 */
	public boolean checkTeacherMeetsQualifications(final TeachingRequest teachingRequest, final Teacher teacher)
	{
		// Check whether the qualifications have been met for the teacher, or not.
		final Qualifications requiredQualifications = teachingRequest.getCourseRequirement()
				.getRequiredStaffQualifications();
		final Qualifications missingQualifications = teacher.getMissingSkills(requiredQualifications);
		return missingQualifications != null && missingQualifications.isEmpty();
	}

	/**
	 * Checks whether or not the requirements of the course have been fulfilled.
	 * 
	 * @return True if the course requirements have been fulfilled, false otherwise.
	 */
	public boolean areRequirementsFulfilled()
	{

		// If there are no teaching staff requirements, return null.
		if (this.teachingStaffRequirementsRequests.isEmpty()) {
			return false;
		}

		// Loop through any requirements checking if the corresponding list of teachers
		// meet the requirements.
		for (final ContactType contactType : teachingStaffRequirementsRequests.keySet()) {
			List<Teacher> teachersList = this.teachingStaff.get(contactType);
			if (teachersList == null) {
				return false;
			} else if (!checkTeachersMeetRequirements(contactType, teachersList)) {
				return false;
			}
		}
		return true;
	}

	public void printCourse(final PrintStream printStream)
	{
		printStream.println(String.format("Course ID: %s\nName: %s\nDescription: %s\nFulfilled: %b", this.courseID,
				this.name, this.description, areRequirementsFulfilled()));
		printStream.println();
		printTeachers(printStream);
		printStream.println("\n----------------------------------------\n");
	}

	public void printTeachers(final PrintStream printStream)
	{

		// Prompt the user that teachers are being displayed; checks if any teachers are
		// present. If not, prints a message and returns.
		printStream.println(String.format("Teachers for course %s:", this.courseID));
		if (this.teachingStaff.isEmpty()) {
			printStream.println("There are no allocated teachers for any of the contact types.");
			return;
		}

		// Print the teachers for each of the contact types.
		for (final ContactType contactType : ContactType.values()) {
			printTeachers(contactType, printStream);
		}
	}

	public void printTeachers(final ContactType contactType, final PrintStream printStream)
	{
		// If there are no teachers, print an appropriate message.
		if (!this.teachingStaff.containsKey(contactType) || this.teachingStaff.get(contactType).isEmpty()) {
			printStream.println(
					String.format("There are no allocated teachers for the courses %s", contactType.getName()));
			return;
		}

		// Print each of the teachers.
		printStream.println(String.format("Teachers for %s:", contactType.getName()));
		for (final Teacher teacher : this.teachingStaff.get(contactType)) {
			teacher.printTeacher(printStream);
		}
	}

	public void printTeachingRequests(final PrintStream printStream)
	{
		if (this.teachingStaffRequirementsRequests.isEmpty()) {
			printStream.println("There are no teaching requests / course requirements for this course");
		}
		for (final ContactType contactType : teachingStaffRequirementsRequests.keySet()) {
			printTeachingRequests(printStream, contactType);
			printStream.println("\n----------------------------------------\n");
		}
	}

	public void printTeachingRequests(final PrintStream printStream, final ContactType contactType)
	{
		if (!this.teachingStaffRequirementsRequests.containsKey(contactType)) {
			printStream.println(
					String.format("There is no teaching reqest / course requirement for %s", contactType.getName()));
			return;
		}
		this.teachingStaffRequirementsRequests.get(contactType).printRequest(printStream);
	}

}
