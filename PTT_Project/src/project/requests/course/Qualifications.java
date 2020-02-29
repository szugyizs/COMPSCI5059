package project.requests.course;

import java.util.HashMap;
import java.util.Map;

public class Qualifications
{

	private static final short MAX_SKILL_LEVEL = 5;
	private static final short MIN_SKILL_LEVEL = 0;

	private Map<Skill, Short> skills;

	/**
	 * Constructor explicitly instantiates the Qualifications instance.
	 * 
	 * @param skills The skills [Map<Skill, Short>] a hash map of the skills and
	 *               levels for each skill.
	 */
	public Qualifications(final Map<Skill, Short> skills)
	{
		this.skills = skills;
	}

	/**
	 * Constructor explicitly instantiates the Qualifications instance. This
	 * constructor assumes all skills to be the minimum level.
	 * 
	 */
	public Qualifications()
	{
		this(new HashMap<Skill, Short>());
	}

	/**
	 * Getter for the whole skill map. TODO: not sure if this is right
	 * 
	 * @return The skills [HashMap] of the qualification.
	 */
	public Map<Skill, Short> getSkills()
	{
		return new HashMap<Skill, Short>(skills);
	}

	/**
	 * Getter for the qualifications, only returning the required qualification
	 * skills. TODO: not sure if this is right
	 * 
	 * @return The required qualifications [Qualifications] .
	 */
	public Qualifications getRequiredSkills(final Qualifications qualifications)
	{
		return new Qualifications(getRequiredSkills(qualifications.skills));
	}

	/**
	 * Getter for the skill map, only returning the required skills. TODO: not sure
	 * if this is right
	 * 
	 * @return The requiredSkills [Map<Skill, Short>] of the qualification.
	 */
	private Map<Skill, Short> getRequiredSkills(final Map<Skill, Short> skills)
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
			skills.putAll(skills); // TODO: don't know what that does exactly
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
	 *         False if a single one was not. //TODO: is that intended? Should it
	 *         really return after finding one skill out of bounds? (see proposed
	 *         change)
	 */
	private boolean checkSkillBounds(final Map<Skill, Short> skills)
	{
		// boolean allInBounds = true;
		for (final Skill skill : skills.keySet())
		{
			if (!checkSkillBounds(skills.get(skill)))
			{
				// allInBounds = false;
				return false;
			}
		}
		// return allInBounds;
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
		if (level < MIN_SKILL_LEVEL)
		{
			System.err.println(String.format("Skill level \"%d\" out of bounds, min skill level \"%d\"set.", level,
					MIN_SKILL_LEVEL));
			return false;
		} else if (level > MAX_SKILL_LEVEL)
		{
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
