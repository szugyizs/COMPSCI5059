package project.requests.course;

public class Course 
{
	
	private String name;
	
	protected Course(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
}
