package project.requests;

import project.requests.course.Qualification;
import project.requests.course.Teacher;

public class TrainingRequirement 
{
	private Qualification qualification;
	private Teacher teacher;
	
	public TrainingRequirement (final Qualification qualification, final Teacher teacher) {
		this.qualification = qualification;
		this.teacher = teacher;
	}
	
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
