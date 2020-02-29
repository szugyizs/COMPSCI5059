package project.requests.course;

import java.util.HashMap;
import java.util.Map;

public class Qualifications
{

	// The maximum and minimum skill levels acceptable.
	private static final short MAX_SKILL_LEVEL = 5;
	private static final short MIN_SKILL_LEVEL = 0;

	// Mapping from skills to levels.
	private Map<Skill, Short> skills;
	
	/**
	 * Constructor explicitly instantiates the Qualifications instance with 
	 * initial skills and their corresponding levels.
	 * 
	 * @param skills The Map<Skill, Short> [HashMap] instance of the skills alongside 
	 * 		their corresponding levels.
	 */
	public Qualifications(final Map<Skill, Short> skills)
	{
		this.skills = skills;
	}

	/**
	 * Constructor instantiates the Qualifications instance. However,
	 * the Map containing the skills is implicitly initialised to an empty HashMap. 
	 */
	public Qualifications()
	{
		this(new HashMap<Skill, Short>());
	}

	/**
	 * Getter for the map of skills and their corresponding levels. 
	 * 
	 * @return A new instance (not the original) of the skills map. This is done
	 * 		to prevent the users from bypassing the limitations / checks imposed by the
	 * 		Qualifications class. 
	 */
	public Map<Skill, Short> getSkills()
	{
		return new HashMap<Skill, Short>(skills);
	}

	/**
	 * Checks whether or not the passed Qualifications (qualificationsToCheck) parameter meets the 
	 * skill levels associated with the invoked (this) instance.
	 * 
	 * Iteratively compares the passed Qualifications (qualificationsToCheck) skills against this instance
	 * in order to identify the missing SkillType's and their corresponding levels.
	 * 
	 * @param qualificationsToCheck The instance containing the skills which are to be tested against.
	 * @return A new instance of Qualifications representing the skills and levels that are missing.
	 * 		If there are no skills missing, a new Qualifications instance is still returned, however, 
	 * 		the skills map will be empty.
	 */
	public Qualifications getMissingSkills(final Qualifications qualificationsToCheck)
	{
		return new Qualifications(getMissingSkills(qualificationsToCheck.skills));
	}

	/**
	 * Checks whether or not the passed skills map parameter meets the skill levels
	 * associated with the invoked (this) instance.
	 * 
	 * Iteratively compares the passed skills map SkillType's against this instance in order
	 * to identify the missing SkillType's and their corresponding levels.
	 * 
	 * @param skills The map containing the SkillType's alongside their levels. This map is the one
	 * 		that's to be checked i.e. whether or not it meets the skill levels of the invoked (this)
	 * 		instance.  
	 * @return A new instance of a map (HashMap) containing the missing SkillType's and their corresponding
	 * 		levels. 
	 */
	private Map<Skill, Short> getMissingSkills(final Map<Skill, Short> skills)
	{

		// If all requirements have been met, return an empty map.
		Map<Skill, Short> requiredSkills = new HashMap<Skill, Short>();
		if (!hasSkills(skills))
		{
			return requiredSkills;
		}

		// Find the skills that are required.
		for (final Skill skill : skills.keySet())
		{
			if (!this.skills.containsKey(skill) || this.skills.get(skill) > skills.get(skill))
			{
				requiredSkills.put(skill, this.skills.get(skill));
			}
		}

		return requiredSkills;
	}

	/**
	 * Checks if the current instance fulfills the required qualifications.
	 * 
	 * TODO: not sure if this is right
	 * 
	 * @param The qualifications [Qualifications] that are needed/wanted.
	 * @return A boolean, true if the qualifications are met.
	 */
	public boolean hasSkills(final Qualifications qualifications)
	{
		return hasSkills(qualifications.skills);
	}

	/**
	 * Checks if the current instance has the required skills.
	 * 
	 * TODO: not sure if this is right
	 * 
	 * @param The skills [Map<Skill, Short>] that are needed/wanted.
	 * @return A boolean, true if the qualifications are met.
	 */
	private boolean hasSkills(final Map<Skill, Short> skills)
	{
		for (final Skill skill : skills.keySet())
		{
			if (!this.skills.containsKey(skill) || this.skills.get(skill) > skills.get(skill))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the current instance has the required (single) skill.
	 * 
	 * TODO: not sure if this is right, no level needed here?
	 * 
	 * @param The skill [Skill] that is needed/wanted.
	 * @return A boolean, true if the skill has the required level. TODO:
	 */
	public boolean hasSkill(final Skill skill)
	{
		return skills.containsKey(skill);
	}

	/**
	 * Gets the level of a specified skill.
	 * 
	 * @param The skill [Skill] of which the level is inquired.
	 * @return The level [short] of the inquired skill, -1 if the skill does not
	 *         exist.
	 */
	public short getSkillLevel(final Skill skill)
	{
		if (skills.containsKey(skill))
		{
			return skills.get(skill);
		}
		return -1;
	}

	/**
	 * Adds a skill to the qualifications.
	 * 
	 * @param The Qualifications [Qualifications] to add.
	 * @return A boolean TODO what does it say?.
	 */
	public boolean addSkills(final Qualifications qualifications)
	{
		return addSkills(qualifications.skills);
	}

	/**
	 * Adds a skill to the qualifications.
	 * 
	 * @param The skills [Map<Skill, Short>] to add.
	 * @return A boolean TODO what does it say?.
	 */
	private boolean addSkills(final Map<Skill, Short> skills)
	{
		if (checkSkillBounds(skills))
		{
			skills.putAll(skills);
			return true;
		}
		return false;
	}

	/**
	 * Sets skill levels.
	 * 
	 * @param The skills [Map<Skill, Short>] to set.
	 * @return A boolean TODO what does it say?.
	 */
	public boolean setSkills(final Qualifications qualifications)
	{
		return setSkills(qualifications.skills);
	}

	/**
	 * Sets skill levels.
	 * 
	 * @param The skills [Map<Skill, Short>] to set.
	 * @return A boolean TODO what does it say?.
	 */
	private boolean setSkills(final Map<Skill, Short> skills)
	{
		if (checkSkillBounds(skills))
		{
			skills.clear();
			skills.putAll(skills);
			return true;
		}
		return false;
	}

	/**
	 * Sets skill levels.
	 * 
	 * @param The skill [Skill] of which the level is to be set.
	 * @param The level [short] to set the skill to.
	 * @return A boolean TODO what does it say?.
	 */
	public boolean setSkill(final Skill skill, final short level)
	{
		if (checkSkillBounds(level))
		{
			skills.put(skill, level);
			return true;
		}
		return false;
	}

	/**
	 * Checks if the skill bounds are within the specified limits.
	 * 
	 * @param The skill map [Map<Skill, Short>] to check.
	 * @return A boolean that indicates if all the levels were within the limits.
	 *         False if a single one was not.
	 */
	private boolean checkSkillBounds(final Map<Skill, Short> skills)
	{
		for (final Skill skill : skills.keySet()) {
			if (!checkSkillBounds(skills.get(skill))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the skill bounds are within the specified limits.
	 * 
	 * @param The level [short] to set.
	 * @return A boolean that indicates if all the level is within the limits.
	 */
	private boolean checkSkillBounds(final short level)
	{
		if (level < MIN_SKILL_LEVEL) {
			System.err.println(String.format("Skill level \"%d\" out of bounds, min skill level \"%d\"set.", level,
					MIN_SKILL_LEVEL));
			return false;
		} else if (level > MAX_SKILL_LEVEL) {
			System.err.println(String.format("Skill level \"%d\" out of bounds, max skill level \"%d\"set.", level,
					MAX_SKILL_LEVEL));
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder skillsString = new StringBuilder();
		for (final Skill skill : skills.keySet())
		{
			skillsString.append(String.format("%s: %d, ", skill.getName(), skills.get(skill)));
		}
		return skillsString.delete(skillsString.length() - 2, skillsString.length()).toString();
	}
}
