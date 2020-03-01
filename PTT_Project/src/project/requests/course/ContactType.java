package project.requests.course;

/**
 * ContactType enumerator specifies the possible contact types for a course i.e. a course
 * has lectures, tutorials and labs. These are most frequently used alongside the CourseRequirements
 * instance.
 * 
 * Use of enumerators provides a standard whilst making for an easy mapping from files.
 */
public enum ContactType
{
	
	LAB("Laboratory"),
	TUTORIAL("Tutorial"),
	LECTURE("Lecture");
	
	private String name; // The user friendly name of the enumerator.
	
	/**
	 * Constructor implicitly used to define the ContactType enumerator arguments.
	 * Note, no attempts should be made to invoke this outside this ContactType 
	 * enumerator.
	 * 
	 * @param name A string instance of the user friendly ContactType name.
	 */
	private ContactType (final String name) 
	{
		this.name = name;
	}
	
	/**
	 * Getter for the user friendly ContactType name.
	 * 
	 * @return A string instance of the ContactType name.
	 */
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
}
