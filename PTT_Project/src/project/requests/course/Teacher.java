package project.requests.course;

import java.util.Map;

public class Teacher
{
	
	private String guid;
	private String forename;
	private String surname;
	
	private Qualifications qualifications;
	
	public Teacher(final String guid, final String forename, final String surname, final Qualifications qualifications)
	{
		this.guid = guid;
		this.forename = forename;
		this.surname = surname;
		
		this.qualifications = qualifications;
	}
	
	public Teacher(final String guid, String forename, final String surname)
	{
		this(guid, forename, surname, new Qualifications());
	}
	
	public String getGUID()
	{
		return guid;
	}
	
	public void setGUID(final String guid)
	{
		this.guid = guid;
	}
	
	public String getForename()
	{
		return forename;
	}
	
	public void setForename(final String forename)
	{
		this.forename = forename;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(final String surname)
	{
		this.surname = surname;
	}

	public Qualifications getQualifications()
	{
		return qualifications;
	}

	public void setQualifications(final Qualifications qualifications)
	{
		this.qualifications = qualifications;
	}
	
	public Map<Skill, Short> getSkills()
	{
		return qualifications.getSkills();
	}
	
	public Qualifications getRequiredSkills(final Qualifications qualifications)
	{
		return this.qualifications.getRequiredSkills(qualifications);
	}
	
	public boolean hasSkills(final Qualifications qualifications)
	{
		return this.qualifications.hasSkills(qualifications);
	}
	
	public boolean hasSkill(final Skill skill)
	{
		return qualifications.hasSkill(skill);
	}
	
	public short getSkillLevel(final Skill skill)
	{
		return qualifications.getSkillLevel(skill);
	}
	
	public boolean addSkills(final Qualifications qualifications)
	{
		return this.qualifications.addSkills(qualifications);
	}
	
	public boolean setSkills(final Qualifications qualifications)
	{
		return this.qualifications.setSkills(qualifications);
	}
	
	public boolean setSkill(final Skill skill, final short level)
	{
		return qualifications.setSkill(skill, level);
	}
	
	@Override
	public String toString()
	{
		return String.format("%s, %s %s, qualifications: %s.", guid, forename, surname, qualifications.toString());
	}
	
}
