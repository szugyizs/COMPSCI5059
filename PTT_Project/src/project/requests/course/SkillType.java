package project.requests.course;

import java.io.PrintStream;

/**
 * SkillType enumerator specifies the possible skills that a Teacher instance
 * can possess. Alternatively, these enumerators can be used to define the requirements
 * of a given course.
 * 
 * Skills are represented by enumerators as they provide a standard whilst eliminating
 * potential errors. Moreover, they are easy to convert into from files, for instance.
 */
public enum SkillType
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
	
	private String name; // SkillType user-friendly name.
	
	/**
	 * Constructor implicitly used to define the SkillType enumerator arguments.
	 * Note, no attempts should be made to invoke this outside this SkillType 
	 * enumerator.
	 * 
	 * @param name A String instance of the user friendly SkillType name.
	 */
	private SkillType(final String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter for the user friendly SkillType name.
	 * 
	 * @return A string instance of the SkillType name.
	 */
	public String getName()
	{
		return name;
	}
	
	public static void printSkillTypes(final PrintStream printStream)
	{
		for(SkillType skillType : SkillType.values()) {
			printStream.println(skillType.name());
		}
	}

	@Override
	public String toString()
	{
		return name;
	}

}
