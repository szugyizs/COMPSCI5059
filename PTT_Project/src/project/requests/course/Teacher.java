package project.requests.course;

public class Teacher
{
	private String name;
	private Qualification qualification;
	
	protected Teacher(String name)
	{
		this.name = name;
		this.qualification = new Qualification("None", 0); 
		//TODO: how to handle teachers with no skills? empty string or "None"?
		// should they be allowed at all? Should the skill (name) be allowed to change?
	}
	
	protected Teacher(String name, Qualification qualification)
	{
		this.name = name;
		this.qualification = qualification;
	}

	protected Teacher(String name, String skill, int level)
	{
		this.name = name;
		this.qualification = new Qualification(skill, level);
	}
	
	public String getName()
	{
		return name;
	}

	public Qualification getQualification()
	{
		return qualification;
	}

	public void setQualification(Qualification qualification)
	{
		this.qualification = qualification;
	}

	//TODO: needed
	public void setQualification(String skill, int level)
	{
		this.qualification.setSkill(skill, level);
	}
	
	
}
