package project.requests.course;

public class Qualification {
	private static final int MAX_SKILL = 5;
	private static final int MIN_SKILL = 0;
	
	private String skill;
	private int level;
	
	protected Qualification(String skill, int level ) {
		this.skill = skill;
		this.level = checkSkillBounds(level);
	}

	public String getSkill()
	{
		return skill;
	}

	public void setSkill(String skill, int level)
	{
		this.skill = skill;
		//TODO: changing the skill should require re-setting the level, to avoid 
		// having to call two functions, it is probably better to force setting
		// the level at the same time
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = checkSkillBounds(level);
	}
	
	private int checkSkillBounds(int level)
	{
		
		if( level < MIN_SKILL )
		{
			System.out.print("Skill level \""+level+"\" out of bounds, min skill level " + MIN_SKILL + "set.");
			level = MIN_SKILL;
		}
		else if ( level > MAX_SKILL )
		{
			System.out.print("Skill level \""+level+"\" out of bounds, max skill level " + MAX_SKILL + "set.");
			level = MAX_SKILL;
		}
		
		return level;
	}
}
