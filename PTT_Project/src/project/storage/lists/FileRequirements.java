package project.storage.lists;
import project.requests.CourseRequirement;
import project.requests.course.Course;
import project.requests.course.Qualifications;

public class FileRequirements extends CourseRequirement{

	public FileRequirements(Course course, short numLecturers, short numTutors, short numDemonstrators,
			short numStudents, short lectureContactHours, short tutorialContactHours, short labContactHours,
			Qualifications requiredStaffQualifications)
	{
		super(course, numLecturers, numTutors, numDemonstrators, numStudents, lectureContactHours, tutorialContactHours,
				labContactHours, requiredStaffQualifications);
		// TODO Auto-generated constructor stub
	}
}
