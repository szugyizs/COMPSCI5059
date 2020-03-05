package project.requests.course;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import project.requests.RequestStatusType;
import project.requests.TrainingRequest;
import project.requests.TrainingRequirement;

public class Teacher
{

	private String guid; // The GUID of the teacher.

	private String forename; // The forename of the teacher.
	private String surname; // The surname of the teacher

	private Qualifications qualifications; // The qualifications (skills) the teacher possesses.

	private List<TrainingRequest> trainingRequests; // The training requests associated with the teacher.

	/**
	 * Constructor explicitly instantiates the teacher instance. This constructor
	 * permits the definition of all teacher attributes.
	 * 
	 * @param guid             The unique GUID of the teacher; a means of
	 *                         identification.
	 * @param forename         The first name of the teacher.
	 * @param surname          The last name of the teacher.
	 * @param qualifications   The teachers qualifications.
	 * @param trainingRequests the training requirements requests submitted for the
	 *                         teacher.
	 */
	public Teacher(final String guid, final String forename, final String surname, final Qualifications qualifications,
			final List<TrainingRequest> trainingRequests)
	{
		this.guid = guid;

		this.forename = forename;
		this.surname = surname;

		this.qualifications = qualifications;

		this.trainingRequests = trainingRequests;
	}

	/**
	 * Constructor instantiates the Teacher instance with basic values. The
	 * trainingRequests array will be instantiated to an empty array, implicitly.
	 * 
	 * @param guid           The unique GUID of the teacher; a means of
	 *                       identification.
	 * @param forename       The first name of the teacher.
	 * @param surname        The last name of the teacher.
	 * @param qualifications The teachers qualifications.
	 */
	public Teacher(final String guid, String forename, final String surname, final Qualifications qualifications)
	{
		this(guid, forename, surname, qualifications, new ArrayList<TrainingRequest>());
	}

	/**
	 * Getter for the GUI.
	 * 
	 * @return The GUID [String] of the teacher.
	 */
	public String getGUID()
	{
		return guid;
	}

	/**
	 * Setter for the GUID.
	 * 
	 * @param The GUID of the teacher.
	 */
	public void setGUID(final String guid)
	{
		this.guid = guid;
	}

	/**
	 * Getter for the forename.
	 * 
	 * @return The forename of the teacher.
	 */
	public String getForename()
	{
		return forename;
	}

	/**
	 * Setter for the forename.
	 * 
	 * @return The forename of the teacher
	 */
	public void setForename(final String forename)
	{
		this.forename = forename;
	}

	/**
	 * Getter for the surname.
	 * 
	 * @return The surname of the teacher
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Setter for the surname.
	 * 
	 * @return The surname of the teacher.
	 */
	public void setSurname(final String surname)
	{
		this.surname = surname;
	}

	/**
	 * Getter for the qualifications.
	 * 
	 * @return The qualifications instance of the teacher.
	 */
	public Qualifications getQualifications()
	{
		return this.qualifications;
	}

	/**
	 * Setter for the qualifications. Overwrites possible existing qualifications.
	 * 
	 * @return The qualifications (skills) of the teacher.
	 */
	public void setQualifications(final Qualifications qualifications)
	{
		this.qualifications = qualifications;
	}

	/**
	 * Getter for the raw SkillType map defined within the teachers qualifications
	 * instance.
	 * 
	 * @return The SkillType map of the teacher that's held within qualifications.
	 */
	public Map<SkillType, Short> getSkills()
	{
		return this.qualifications.getSkills();
	}

	/**
	 * Compares the teachers qualifications to qualifications that are provided as
	 * an input and returns the ones that the teacher is missing.
	 * 
	 * @param qualifications The qualifications that the teacher should have.
	 * @return The missing qualifications [Qualifications] .
	 */
	public Qualifications getMissingSkills(final Qualifications qualifications)
	{
		return this.qualifications.getMissingSkills(qualifications);
	}

	/**
	 * Adds a training request to the teachers trainingRequests list. Fundamentally,
	 * this function wraps the parameter trainingRequirements in a TrainingRequest
	 * instance.
	 * 
	 * @param trainingRequirement The training requirement that needs to be met with
	 *                            a training.
	 * @return request The instantiated training request for the requirement.
	 */
	public TrainingRequest addTrainingRequest(final TrainingRequirement trainingRequirement)
	{
		final TrainingRequest request = new TrainingRequest(getNextTrainingRequestId(), trainingRequirement);
		this.trainingRequests.add(request);
		return request;
	}

	/**
	 * Returns all of the TrainingRequest instances associated with the teacher
	 * instance.
	 * 
	 * @return A list containing the training requirements.
	 */
	public List<TrainingRequest> getTrainingRequests()
	{
		return this.trainingRequests;
	}

	/**
	 * Returns all of the TrainingRequests instances with a particular status that
	 * are associated with the teacher instance.
	 * 
	 * @param requestStatusType The status of the requests that are required.
	 * @return A list of TrainingRequests with the said status.
	 */
	private List<TrainingRequest> getTrainingRequests(final RequestStatusType requestStatusType)
	{
		List<TrainingRequest> statusTrainingRequests = new ArrayList<TrainingRequest>();
		for (final TrainingRequest trainingRequest : this.trainingRequests) {
			if (trainingRequest.getRequestStatus() == requestStatusType) {
				statusTrainingRequests.add(trainingRequest);
			}
		}
		return statusTrainingRequests;
	}

	/**
	 * Fundamentally, this function is responsible for generating the next
	 * TrainingRequest id. This is done in order to differentiate between the
	 * various training requirements requests submitted for the teacher.
	 * 
	 * @return The next TrainingRequest id.
	 */
	private int getNextTrainingRequestId()
	{
		return this.trainingRequests.size();
	}

	public void printTeacher(final PrintStream printStream)
	{
		printStream.print(String.format("Teacher GUID: %s Forename: %s Surname: %s", guid, forename, surname));
		printStream.print("; Qualifications: ");
		qualifications.printSkills(printStream);
		printStream.println();
	}

	public void printTrainingRequests(final PrintStream printStream)
	{
		for (final RequestStatusType requestStatusType : RequestStatusType.values()) {
			printTrainingRequests(printStream, requestStatusType);
		}
	}

	private void printTrainingRequests(final PrintStream printStream, final RequestStatusType requestStatusType)
	{

		// Get the list of training requests for a request status type.
		List<TrainingRequest> trainingRequests = getTrainingRequests(requestStatusType);
		if (trainingRequests.isEmpty()) {
			printStream.println(String.format("There are no %s training requests", requestStatusType.getName()));
			return;
		}

		// Print the training requests with that type.
		for (final TrainingRequest trainingRequest : trainingRequests) {
			trainingRequest.printRequest(printStream);
			printStream.println("\n----------------------------------------\n");
		}
	}

	@Override
	public String toString()
	{
		return String.format("%s, %s %s, qualifications: %s.", guid, forename, surname, qualifications.toString());
	}

}
