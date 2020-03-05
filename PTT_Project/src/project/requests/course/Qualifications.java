package project.requests.course;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Qualifications
{

	private static final short MAX_SKILL_LEVEL = 5; // The maximum skill level that can be set.
	private static final short MIN_SKILL_LEVEL = 0; // The minimum skill level that can be set.

	private HashMap<SkillType, Short> skills; // A mapping from the skill types to their corresponding level.

	/**
	 * Constructor explicitly instantiates the Qualifications instance with initial
	 * skills and their corresponding levels.
	 * 
	 * @param skills The Map<Skill, Short> [HashMap] instance of the skills
	 *               alongside their corresponding levels.
	 */
	public Qualifications(final HashMap<SkillType, Short> skills)
	{
		this.skills = skills;
	}

	/**
	 * Constructor instantiates the Qualifications instance. However, the Map
	 * containing the skills is implicitly initialised to an empty HashMap.
	 */
	public Qualifications()
	{
		this(new HashMap<SkillType, Short>());
	}

	/**
	 * Getter for the map of skills and their corresponding levels.
	 * 
	 * @return A new instance (not the original) of the skills map. This is done to
	 *         prevent the users from bypassing the limitations / checks imposed by
	 *         the Qualifications class.
	 */
	public Map<SkillType, Short> getSkills()
	{
		return new HashMap<SkillType, Short>(skills);
	}

	/**
	 * Iteratively checks whether or not the invoked Qualifications instance (this)
	 * possesses the SkillTypes and the corresponding skill level as defined by the
	 * qualificationsToBeMet parameter.
	 * 
	 * Note, that a SkillType is defined as missing in the event the invoked
	 * Qualifications instance this does not possess it, or the level is less than
	 * that one defined in qualificationsToBeMet.
	 * 
	 * @param qualificationsToBeMet The instance containing the skills that should
	 *                              be met by the invoked Qualifications instance
	 *                              (this).
	 * @return An instance of Qualifications, representing the missing SkillTypes
	 *         and the corresponding levels. Note, if no skills are missing, a
	 *         Qualifications instance will still be returned to prevent the need to
	 *         deal with nulls. Thus, check if skills is empty.
	 */
	public Qualifications getMissingSkills(final Qualifications qualificationsToBeMet)
	{
		return new Qualifications(getMissingSkills(qualificationsToBeMet.skills));
	}

	/**
	 * Iteratively checks whether or not the invoked Qualifications instance (this)
	 * possesses the SkillTypes and the corresponding skill level as defined by the
	 * qualificationsToBeMet HashMap parameter.
	 * 
	 * Note, that a SkillType is defined as missing in the event the invoked
	 * Qualifications instance this does not possess it, or the level is less than
	 * that one defined in qualificationsToBeMet.
	 * 
	 * @param qualificationsToBeMet The HashMao instance containing the skills that
	 *                              should be met by the invoked Qualifications
	 *                              instance (this).
	 * @return An instance of HashMap, representing the missing SkillType's and the
	 *         corresponding levels. Note, if no skills are missing, an empty
	 *         HashMap will be returned.
	 */
	private HashMap<SkillType, Short> getMissingSkills(final HashMap<SkillType, Short> qualificationsToBeMet)
	{

		// If all the skills have been met, return an empty hashmap.
		HashMap<SkillType, Short> missingSkills = new HashMap<SkillType, Short>();
		if (hasSkills(qualificationsToBeMet)) {
			return missingSkills;
		}

		// Find the skills that are required.
		for (final SkillType skillType : qualificationsToBeMet.keySet()) {
			if (!skills.containsKey(skillType) || skills.get(skillType) < qualificationsToBeMet.get(skillType)) {
				missingSkills.put(skillType, qualificationsToBeMet.get(skillType));
			}
		}

		return missingSkills;
	}

	/**
	 * Query the skills map for whether or not it is empty.
	 * 
	 * @return True if there are no skills, false otherwise.
	 */
	public boolean isEmpty()
	{
		return skills.isEmpty();
	}

	/**
	 * Checks whether or not the invoked Qualification instance (this) contains the
	 * SkillType's and the corresponding skill levels as defined in the
	 * qualifications HashMap parameter.
	 * 
	 * @param requiredSkills A HashMap containing SkillType's and levels that are to
	 *                       be checked for.
	 * @return True if all the SkillType's and levels are present within the invoked
	 *         Qualifications instance skill map, false otherwise.
	 */
	private boolean hasSkills(final Map<SkillType, Short> requiredSkills)
	{
		for (final SkillType skillType : requiredSkills.keySet()) {
			if (!skills.containsKey(skillType) || skills.get(skillType) < requiredSkills.get(skillType)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the level of a specified SkillType.
	 * 
	 * @param The SkillType of which the level is inquired.
	 * @return The level of the inquired skill, -1 if the skill does not exist.
	 */
	public short getSkillLevel(final SkillType skill)
	{
		if (skills.containsKey(skill)) {
			return skills.get(skill);
		}
		return -1;
	}

	/**
	 * Sets a SkillType and level. If the skillType is not present, one will be
	 * added.
	 * 
	 * @param skillType The skill that is to be set.
	 * @param level     The level of the skillType that is being set.
	 * @return True if the skillType and level have been successfully set, false
	 *         otherwise.
	 */
	public boolean setSkill(final SkillType skillType, final short level)
	{
		if (skillType != null && checkSkillBounds(level)) {
			skills.put(skillType, level);
			return true;
		}
		return false;
	}

	/**
	 * Checks whether or not the level of the skills falls within the valid bounds
	 * i.e. MIN_SKILL_LEVEL <= levels <= MAX_SKILL_LEVEL.
	 * 
	 * @param The level that is to be checked.
	 * @return True if the level falls within the valid bounds, false otherwise.
	 */
	private boolean checkSkillBounds(final short level)
	{
		if (level < MIN_SKILL_LEVEL) {
			System.err.println(String.format("Skill level \"%d\" out of bounds, min skill level \"%d\".", level,
					MIN_SKILL_LEVEL));
			return false;
		} else if (level > MAX_SKILL_LEVEL) {
			System.err.println(String.format("Skill level \"%d\" out of bounds, max skill level \"%d\".", level,
					MAX_SKILL_LEVEL));
			return false;
		}
		return true;
	}

	public void printSkills(final PrintStream printStream)
	{
		for (final SkillType skillType : skills.keySet()) {
			printStream.print(String.format("%s: %d, ", skillType.getName(), this.skills.get(skillType)));
		}
	}

	@Override
	public String toString()
	{
		StringBuilder skillsString = new StringBuilder();
		for (final SkillType skill : skills.keySet()) {
			skillsString.append(String.format("%s: %d, ", skill.getName(), skills.get(skill)));
		}
		return skillsString.delete(skillsString.length() - 2, skillsString.length()).toString();
	}
}
