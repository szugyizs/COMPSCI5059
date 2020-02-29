package project.requests.course;

public enum ContactType
{
	
	LAB("Laboratory"),
	TUTORIAL("Tutorial"),
	LECTURE("Lecture");
	
	private String name;
	
	private ContactType (final String name) 
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name;
	}
	
	

}
