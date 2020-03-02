package project.requests.course;

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
	 * Constructor explicitly instantiates the teacher instance. This constructor permits the
	 * definition of all teacher attributes.
	 * 
	 * @param guid The unique GUID of the teacher; a means of identification.
	 * @param forename The first name of the teacher.
	 * @param surname The last name of the teacher.
	 * @param qualifications The teachers qualifications.
	 * @param trainingRequests the training requirements requests submitted for the teacher.
	 */
	public Teacher(final String guid, final String forename, final String surname, 
			final Qualifications qualifications, final List<TrainingRequest> trainingRequests)
	{
		this.guid = guid;
		
		this.forename = forename;
		this.surname = surname;

		this.qualifications = qualifications;
		
		this.trainingRequests = new ArrayList<TrainingRequest>();
	}

	/**
	 * Constructor instantiates the Teacher instance with basic values. The qualifications
	 * will be instantiated with no skills. Moreover, the trainingRequests array will be 
	 * instantiated to an empty array, implicitly.
	 * 
	 * @param guid The unique GUID of the teacher; a means of identification.
	 * @param forename The first name of the teacher.
	 * @param surname The last name of the teacher.
	 */
	public Teacher(final String guid, String forename, final String surname)
	{
		this(guid, forename, surname, new Qualifications(), new ArrayList<TrainingRequest>());
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
	 * Getter for the raw SkillType map defined within the teachers qualifications instance.
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
	 * Checks if the current teacher fulfils the required qualifications.
	 * 
	 * @param The qualifications that are needed.
	 * @return True if all the SkillType's within the qualifications instance have been
	 * 		met by the teacher. This includes the levels.
	 */
	public boolean hasSkills(final Qualifications qualifications)
	{
		return this.qualifications.hasSkills(qualifications);
	}

	/**
	 * Checks if the current teacher has the required (single) skill.
	 * 
	 * @param The SkillType that is needed/wanted.
	 * @return True if the teacher has skillType (regardless of the level), false
	 * 		otherwise.
	 */
	public boolean hasSkill(final SkillType skillType)
	{
		return this.qualifications.hasSkill(skillType);
	}

	/**
	 * Checks whether or not the teacher possesses a SkillType at a specific level.
	 * 
	 * @param skillType The SkillType that is being queried for the level and existence.
	 * @param level The level of the SkillType.
	 * @return True if the teacher possesses the skillType at the defined level (level).
	 */
	public boolean hasSkill(final SkillType skillType, final short level)
	{
		return this.qualifications.hasSkill(skillType, level);
	}

	/**
	 * Gets the level of the SkillType that the teacher possesses.
	 * 
	 * @param skillType The SkillType that is being queried for the level.
	 * @return The level of the skillType parameter, or -1 if the teach does not possess
	 * 		the skillType parameter.
	 */
	public short getSkillLevel(final SkillType skillType)
	{
		return this.qualifications.getSkillLevel(skillType);
	}

	/**
	 * Adds qualifications skills to the qualifications of the teacher.
	 * 
	 * @param The Qualifications [Qualifications] to add.
	 * @return True if the qualifications were set successfully, false if not.
	 */
	public boolean addSkills(final Qualifications qualifications)
	{
		return this.qualifications.addSkills(qualifications);
	}

	/**
	 * Sets skills in the qualifications of the teacher.
	 * 
	 * @param The Qualifications [Qualifications] to set.
	 * @return True if the qualifications were set successfully, false if not.
	 */
	public boolean setSkills(final Qualifications qualifications)
	{
		return this.qualifications.setSkills(qualifications);
	}

	/**
	 * Set a skill to the qualifications of the teacher.
	 * 
	 * @param The skill [Skill] to set.
	 * @return True if the qualifications were set successfully, false if not.
	 */
	public boolean setSkill(final SkillType skill, final short level)
	{
		return this.qualifications.setSkill(skill, level);
	}
	
	/**
	 * Adds a training request to the teachers trainingRequests list. Fundamentally,
	 * this function wraps the parameter trainingRequirements in a TrainingRequest instance.
	 * 
	 * @param trainingRequirement The training requirement that needs to be met with a training.
	 * @return request The instantiated training request for the requirement.
	 */
	public TrainingRequest addTrainingRequest(final TrainingRequirement trainingRequirement)
	{
		final TrainingRequest request = new TrainingRequest(getNextTrainingRequestId(), trainingRequirement);
		this.trainingRequests.add(request);
		return request;
	}

	/**
	 * Returns all of the TrainingRequest instances associated with the teacher instance.
	 * 
	 * @return A list containing the training requirements.
	 */
	public List<TrainingRequest> getTrainingRequests()
	{
		return this.trainingRequests;
	}
	
	/**
	 * Returns all of the TrainingRequests instances with a particular status that are associated
	 * with the teacher instance.
	 * 
	 * @param requestStatusType The status of the requests that are required.
	 * @return A list of TrainingRequests with the said status.
	 */
	public List<TrainingRequest> getTrainingRequests(final RequestStatusType requestStatusType)
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
	 * Fundamentally, this function is responsible for generating the next TrainingRequest id.
	 * This is done in order to differentiate between the various training requirements requests
	 * submitted for the teacher.
	 * 
	 * @return The next TrainingRequest id.
	 */
	private int getNextTrainingRequestId()
	{
		return this.trainingRequests.size();
	}

	@Override
	public String toString()
	{
		return String.format("%s, %s %s, qualifications: %s.", guid, forename, surname, qualifications.toString());
	}

}
