package project.requests.course;

/**
 * Enum skill type.
 * This enum specifies the possible skills that can be
 * requirements for a course or a teacher can have.
 */
public enum Skill
{

	SOFTWARE_ENGINEERING("Software Engineering"), 
	PROGRAMMING_LANGUAGES("Programming Languages"),
	ALGORITHMS_AND_DATA_STRUCTURES("Algorithms and Data Structures"),
	INTERNET_TECHNLOLOGY("Internet Technology"), 
	WEB_SCIENCE("Web Science"), 
	NETWORKING("Networking"),
	CRYPTOGRAPHY("Cryptography"), 
	CYBER_SECURITY("Cyber Security"),
	HUMAN_COMPUTER_INTERACTION("Human Computer Interaction"),
	MOBILE_HUMAN_COMPUTER_INTERACTION("Mobile Human Computer Interaction"),
	SAFETY_CRITICAL_SYSTEMS_DEVELOPMENT("Safety Critical Systems Development"), 
	ROBOTICS("Robotics"),
	BIG_DATA("Big Data"), 
	INFORMATION_RETRIEVAL("Information Retrieval");

	private String name;
	
	private Skill(final String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter for the skill name.
	 * 
	 * @return The name [String] of the skill.
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
