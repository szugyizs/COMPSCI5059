package project.requests.course;

import java.util.HashMap;
import java.util.Map;

public class Qualifications 
{
	 
	private static final short MAX_SKILL_LEVEL = 5;
	private static final short MIN_SKILL_LEVEL = 0;
	
	private Map<Skill, Short> skills;
	
	public Qualifications(final Map<Skill, Short> skills)
	{
		this.skills = skills;
	}
	
	public Qualifications()
	{
		this(new HashMap<Skill, Short>());
	}
	
	public Map<Skill, Short> getSkills()
	{
		return new HashMap<Skill, Short>(skills);
	}
	
	public Qualifications getRequiredSkills(final Qualifications qualifications)
	{
		return new Qualifications(getRequiredSkills(qualifications.skills));
	}
	
	private Map<Skill, Short> getRequiredSkills(final Map<Skill, Short> skills)
	{
		
		// If all requirements have been met, return an empty map.
		Map<Skill, Short> requiredSkills = new HashMap<Skill, Short>();
		if (!hasSkills(skills)) {
			return requiredSkills;
		}
		
		// Find the skills that are required.
		for (final Skill skill : skills.keySet()) {
			if (!this.skills.containsKey(skill) || this.skills.get(skill) > skills.get(skill)) {
				requiredSkills.put(skill, this.skills.get(skill));
			}
		}
		
		return requiredSkills;
	}
	
	public boolean hasSkills(final Qualifications qualifications)
	{
		return hasSkills(qualifications.skills);
	}
	
	private boolean hasSkills(final Map<Skill, Short> skills)
	{
		for (final Skill skill : skills.keySet()) {
			if (!this.skills.containsKey(skill) || this.skills.get(skill) > skills.get(skill)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasSkill(final Skill skill)
	{
		return skills.containsKey(skill);
	}
	
	public short getSkillLevel(final Skill skill)
	{
		if (skills.containsKey(skill)) {
			return skills.get(skill);
		}
		return -1;
	}
	
	public boolean addSkills(final Qualifications qualifications)
	{
		return addSkills(qualifications.skills);
	}
	
	private boolean addSkills(final Map<Skill, Short> skills)
	{
		if (checkSkillBounds(skills)) {
			skills.putAll(skills);
			return true;
		}
		return false;
	}
	
	public boolean setSkills(final Qualifications qualifications)
	{
		return setSkills(qualifications.skills);
	}
	
	private boolean setSkills(final Map<Skill, Short> skills)
	{
		if (checkSkillBounds(skills)) {
			skills.clear();
			skills.putAll(skills);
			return true;
		}
		return false;
	}
	
	public boolean setSkill(final Skill skill, final short level)
	{
		if (checkSkillBounds(level)) {
			skills.put(skill, level);
			return true;
		}
		return false;
	}
	
	private boolean checkSkillBounds(final Map<Skill, Short> skills)
	{
		for (final Skill skill : skills.keySet()) {
			if (!checkSkillBounds(skills.get(skill))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkSkillBounds(final short level)
	{
		if (level < MIN_SKILL_LEVEL) {
			System.err.println(String.format("Skill level \"%d\" out of bounds, min skill level \"%d\"set.", level, MIN_SKILL_LEVEL));
			return false;
		} else if (level > MAX_SKILL_LEVEL) {
			System.err.println(String.format("Skill level \"%d\" out of bounds, max skill level \"%d\"set.", level, MAX_SKILL_LEVEL));
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public String toString()
	{
		StringBuilder skillsString = new StringBuilder();
		for (final Skill skill : skills.keySet()) {
			skillsString.append(String.format("%s: %d, ", skill.getName(), skills.get(skill)));
		}
		return skillsString.delete(skillsString.length() - 2, skillsString.length()).toString();
	}
}
