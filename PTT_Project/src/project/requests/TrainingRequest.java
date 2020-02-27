package project.requests;

import project.course.Teacher;

public class TrainingRequest extends Request
{
	
	private Teacher teacher;
	
	public TrainingRequest(final RequestStatus requestStatus, final Teacher teacher)
	{
		super(requestStatus);
		
		this.teacher = teacher;
	}
	
	public Teacher getTeacher()
	{
		return teacher;
	}
	
	public void setTeacher(final Teacher teacher)
	{
		this.teacher = teacher;
	}
	
}
