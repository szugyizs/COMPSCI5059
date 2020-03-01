package project.requests.course;

import java.util.List;
import java.util.Map;

import project.requests.TrainingRequest;

public class Teacher
{

	private String guid; // The GUID of the teacher.
	
	private String forename; // The forename of the teacher.
	private String surname; // The surname of the teacher

	private Qualifications qualifications; // The qualifications (skills) the teacher possesses.

	private List<TrainingRequest> trainingRequests; // The training requests associated with the teacher.

	/**
	 * Constructor explicitly instantiates the Teacher instance.
	 * 
	 * @param guid           The GUID of the teacher
	 * @param forename       The forename of the teacher
	 * @param surname        The surname of the teacher
	 * @param qualifications The qualifications of the teacher
	 * 
	 */
	public Teacher(final String guid, final String forename, final String surname, final Qualifications qualifications)
	{
		this.guid = guid;
		this.forename = forename;
		this.surname = surname;

		this.qualifications = qualifications;
	}

	/**
	 * Constructor explicitly instantiates the Teacher instance. This teacher will
	 * does not have any initial qualifications.
	 * 
	 * @param guid     The GUID of the teacher
	 * @param forename The forename of the teacher
	 * @param surname  The surname of the teacher
	 * 
	 */
	public Teacher(final String guid, String forename, final String surname)
	{
		this(guid, forename, surname, new Qualifications());
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
	 * Setter for the GUI.
	 * 
	 * @param The GUID [String] of the teacher.
	 */
	public void setGUID(final String guid)
	{
		this.guid = guid;
	}

	/**
	 * Getter for the forename.
	 * 
	 * @return The forename [String] of the teacher.
	 */
	public String getForename()
	{
		return forename;
	}

	/**
	 * Setter for the forename.
	 * 
	 * @return The forename [String] of the teacher
	 */
	public void setForename(final String forename)
	{
		this.forename = forename;
	}

	/**
	 * Getter for the surname.
	 * 
	 * @return The surname [String] of the teacher
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Setter for the surname.
	 * 
	 * @return The surname [String] of the teacher.
	 */
	public void setSurname(final String surname)
	{
		this.surname = surname;
	}

	/**
	 * Getter for the qualifications.
	 * 
	 * @return The qualifications [Qualifications] of the teacher.
	 */
	public Qualifications getQualifications()
	{
		return qualifications;
	}

	/**
	 * Setter for the qualifications. Overwrites possible existing qualifications.
	 * 
	 * @return The qualifications [Qualifications] of the teacher.
	 */
	public void setQualifications(final Qualifications qualifications)
	{
		this.qualifications = qualifications;
	}
	
	/**
	 * Getter for the skill map, derrived from the qualifications.
	 * 
	 * @return The skill map [Map<Skill, Short>] of the teacher.
	 */
	public Map<SkillType, Short> getSkills()
	{
		return qualifications.getSkills();
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
	 * Checks if the current teacher fulfills the required qualifications.
	 * 
	 * @param The qualifications [Qualifications] that are needed.
	 * @return A boolean, true if the qualifications are met.
	 */
	public boolean hasSkills(final Qualifications qualifications)
	{
		return this.qualifications.hasSkills(qualifications);
	}

	/**
	 * Checks if the current teacher has the required (single) skill.
	 * 
	 * TODO: not sure if this is right, no level needed here?
	 * 
	 * @param The skill [Skill] that is needed/wanted.
	 * @return A boolean, true if the teacher has the skill.
	 */
	public boolean hasSkill(final SkillType skillType)
	{
		return qualifications.hasSkill(skillType);
	}

	public boolean hasSkill(final SkillType skillType, final short level)
	{
		return qualifications.hasSkill(skillType, level);
	}

	/**
	 * Gets the level of a specified skill of the teacher.
	 * 
	 * @param The skill [Skill] of which the level is inquired.
	 * @return The level [short] of the inquired skill, -1 if the skill does not
	 *         exist.
	 */
	public short getSkillLevel(final SkillType skill)
	{
		return qualifications.getSkillLevel(skill);
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
		return qualifications.setSkill(skill, level);
	}

	/**
	 * Get the request Id for the next request.
	 * 
	 * @return The number of current training requests, which can be used to give an
	 *         ID to the next one.
	 */
	private int getNextTrainingRequestId()
	{// when a new training request is added, it needs a
		return trainingRequests.size();
	}

	@Override
	public String toString()
	{
		return String.format("%s, %s %s, qualifications: %s.", guid, forename, surname, qualifications.toString());
	}

}
