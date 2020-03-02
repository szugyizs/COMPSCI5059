package project.controllers.types;

import java.io.PrintStream;

import project.controllers.Controller;
import project.requests.course.Course;
import project.storage.Storage;

public class CourseDirectorController extends Controller
{
	
	private Course course;
	
	public CourseDirectorController(final Storage storage, final Course course) 
	{
		super(storage);
		this.course = course;
	}
	
	public Course getCourse()
	{
		return course;
	}
	
	@Override
	public void printHelpMessages() 
	{	
	}
	
	@Override
	public boolean processCommand(final String command, final String... args)
	{
		
		
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
