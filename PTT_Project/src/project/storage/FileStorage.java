package project.storage;

import static org.junit.Assert.assertThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import project.requests.CourseRequirement;
import project.requests.RequestStatusType;
import project.requests.TeachingRequest;
import project.requests.TrainingRequest;
import project.requests.TrainingRequirement;
import project.requests.course.ContactType;
import project.requests.course.Course;
import project.requests.course.Qualifications;
import project.requests.course.SkillType;
import project.requests.course.Teacher;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public class FileStorage extends Storage
{

	String path;
	
	public FileStorage(final String path)
	{
		this.path = path;
		load();
	}

	@Override
	public boolean load()
	{	
		JSONParser jsonParser = new JSONParser();
		try (FileReader fileReader = new FileReader(path)) {
			
			// Parse the file and the root elements of the JSON file.
			JSONObject jsonRoot = (JSONObject) jsonParser.parse(fileReader);
			JSONArray jsonTeachers = (JSONArray) jsonRoot.get("teachers");
			JSONArray jsonCourses = (JSONArray) jsonRoot.get("courses");
			
			// Parse the root elements of the json file.
			super.listOfTeachers = parseTeachers(jsonTeachers);
			super.listOfCourses = parseCourses(listOfTeachers, jsonCourses);
			
			Teacher teacher = listOfTeachers.getTeachers().get(0);
			teacher.printTeacher(System.out);
			teacher.printTrainingRequests(System.out);
			
		} catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// test
	
	private ListOfTeachers parseTeachers(final JSONArray jsonTeachers)
	{
		ListOfTeachers listOfTeachers = new ListOfTeachers();
		
		// Loop through all of the teachers objects, and create objects for each.
		Iterator<JSONObject> iter = jsonTeachers.iterator();
		Teacher teacher;
		while (iter.hasNext()) {
			
			// If the parsed JSON object i.e. teacher is not null, add it to the list of teachers.
			teacher = parseTeacher(iter.next());
			if (teacher == null) {
				return null;
			}
			listOfTeachers.addTeacher(teacher);
		}
		
		return listOfTeachers;
	}
	
	private Teacher parseTeacher(final JSONObject jsonTeacher)
	{
		
		// Get the basic teacher information.
		final String teacherGUID = (String) jsonTeacher.get("guid");
		final String teacherForename = (String) jsonTeacher.get("forename");
		final String teacherSurname = (String) jsonTeacher.get("surname");
		
		// If the teacherGUID, forename or surname is missing, return null.
		if (teacherGUID == null || teacherForename == null || teacherSurname == null) {
			return null;
		}
		
		// Attempt to parse the qualifications of the teacher.
		final JSONArray jsonQualifications = (JSONArray) jsonTeacher.get("qualifications");
		Qualifications qualifications;
		if (jsonQualifications != null) {
			qualifications = parseQualifications(jsonQualifications);
			if (qualifications == null) {
				return null;
			}
		} else {
			qualifications = new Qualifications();
		}
		
		// The training requests associated with the teacher.
		List<TrainingRequest> teacherTrainingRequests = new ArrayList<TrainingRequest>();
		
		// Check whether or not there are any training requests.
		JSONArray jsonTeacherTrainingRequests = (JSONArray) jsonTeacher.get("trainingRequests");
		if (jsonTeacherTrainingRequests == null) {
			return new Teacher(teacherGUID, teacherForename, teacherSurname, qualifications);
		}
		
		// Parse each of the training requests for the teacher.
		Iterator<JSONObject> iter = jsonTeacherTrainingRequests.iterator();
		TrainingRequest trainingRequest;
		while (iter.hasNext()) {
			
			// Attempt to parse the training request, return null if this cannot be parsed.
			trainingRequest = parseTrainingRequest(iter.next());
			if (trainingRequest == null) {
				return null;
			}
			trainingRequest.printRequest(System.out);
			teacherTrainingRequests.add(trainingRequest);
		}
		
		// We have successfully parsed the teachers qualifications.
		return new Teacher(teacherGUID, teacherForename, teacherSurname,
				qualifications, teacherTrainingRequests);
	}
	
	private Qualifications parseQualifications(final JSONArray jsonQualifications)
	{
		HashMap<SkillType, Short> skills = new HashMap<SkillType, Short>();
		
		// Iterate through skill object that define the qualifications instance.
		Iterator<JSONObject> iter = jsonQualifications.iterator();
		JSONObject jsonSkill;
		SkillType skillType;
		short skillLevel;
		while (iter.hasNext()) {
			
			// Get the next JSON skill object.
			jsonSkill = iter.next();
			
			// Parse the skill type.
			skillType = SkillType.valueOf((String) jsonSkill.get("skillType"));
			if (skillType == null) {
				return null;
			}
			
			// Parse the skill level.
			try {
				skillLevel = Short.parseShort((String) jsonSkill.get("level"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
			
			// Add the skill type and level to the HashMap.
			skills.put(skillType, skillLevel);
		}
		
		return new Qualifications(skills);
	}
	
	private TrainingRequest parseTrainingRequest(final JSONObject jsonRequest)
	{
		
		// Get the status of the training request.
		final String jsonTrainingRequestStatusType = (String) jsonRequest.get("status");
		if (jsonTrainingRequestStatusType == null) {
			return null;
		}
		
		// Convert the string request status type to RequestStatusType enumerator.
		final RequestStatusType requestStatusType = RequestStatusType.valueOf(jsonTrainingRequestStatusType);
		if (requestStatusType == null) {
			return null;
		}
		
		// Get the training request.
		final JSONObject jsonTrainingRequest = (JSONObject) jsonRequest.get("trainingRequest");
		if (jsonTrainingRequest == null) {
			return null;
		}
		
		// Parse the training request id.
		final int teacherTrainingRequestId;
		try {
			teacherTrainingRequestId = Integer.parseInt((String) jsonTrainingRequest.get("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		
		// Parse the training requirement for the teacher.
		final JSONObject jsonTrainingRequirement = (JSONObject) jsonTrainingRequest.get("trainingRequirement");
		final TrainingRequirement teacherTrainingRequirement = parseTrainingRequirement(jsonTrainingRequirement);
		
		// Finally, create and return an instance that represents the teachers qualifications.
		return new TrainingRequest(teacherTrainingRequestId, teacherTrainingRequirement);
	}
	
	private TrainingRequirement parseTrainingRequirement(final JSONObject jsonTrainingRequirement)
	{
		final JSONArray jsonQualifications = (JSONArray) jsonTrainingRequirement.get("qualifications");
		final Qualifications teacherQualifications = parseQualifications(jsonQualifications);
		return new TrainingRequirement(teacherQualifications);
	}
	
	private ListOfCourses parseCourses(final ListOfTeachers listOfTeachers, final JSONArray jsonCourses)
	{
		ListOfCourses listOfCourses = new ListOfCourses();
		
		// Iterate through the JSON course array, parsing each of the course JSON objects.
		Iterator<JSONObject> iter = jsonCourses.iterator();
		Course course;
		while (iter.hasNext()) {
			course = parseCourse(listOfTeachers, (JSONObject) iter.next());
			if (course == null) {
				return null;
			}
			listOfCourses.addCourse(course);
		}
		
		return listOfCourses;
	}
	
	private Course parseCourse(final ListOfTeachers listOfTeachers, final JSONObject jsonCourse)
	{
		
		// Parse the basic course attributes.
		final String courseID = (String) jsonCourse.get("courseID");
		final String courseName = (String) jsonCourse.get("name");
		final String courseDescription = (String) jsonCourse.get("description");
		
		// If the basic course attributes are not present, return null.
		if (courseID == null || courseName == null || courseDescription == null) {
			return null;
		}
		
		// Get the JSON array containing the allocated teachers.
		final JSONObject jsonTeachingStaff = (JSONObject) jsonCourse.get("teachingStaff");
		HashMap<ContactType, List<Teacher>> teachingStaff;
		if (jsonTeachingStaff != null) {
			teachingStaff = parseTeachingStaff(listOfTeachers, jsonTeachingStaff);
			if (teachingStaff == null) {
				return null;
			}
		} else {
			teachingStaff = new HashMap<ContactType, List<Teacher>>();
		}
		
		// Get the JSON object containing the teachingRequests.
		final JSONObject jsonTeachingRequests = (JSONObject) jsonCourse.get("teachingRequests");
		HashMap<ContactType, TeachingRequest> teachingRequests;
		if (jsonTeachingRequests != null) {
			teachingRequests = parseTeachingRequests(jsonTeachingRequests);
			if (teachingRequests == null) {
				return null;
			}
		} else {
			teachingRequests = new HashMap<ContactType, TeachingRequest>();
		}
		
		// Finally, create the course based on the parsed values.
		return new Course(courseID, courseName, courseDescription,
				teachingRequests, teachingStaff);
	}
	
	private HashMap<ContactType, List<Teacher>> parseTeachingStaff(final ListOfTeachers listOfTeachers, final JSONObject jsonTeachingStaff)
	{
		
		// The HashMap containing all of the teaching staff for each of the contact types.
		HashMap<ContactType, List<Teacher>> teachingStaff = new HashMap<ContactType, List<Teacher>>();
		teachingStaff.put(ContactType.LAB, new ArrayList<Teacher>());
		teachingStaff.put(ContactType.LECTURE, new ArrayList<Teacher>());
		teachingStaff.put(ContactType.TUTORIAL, new ArrayList<Teacher>());
		
		// Loop through the contact types in order to obtain the teaching staff for each.
		for (Object jsonKey : jsonTeachingStaff.keySet()) {
			
			// Parse the contact type.
			String jsonKeyString = (String) jsonKey;
			ContactType contactType = ContactType.valueOf(jsonKeyString);
			if (contactType == null) {
				return null;
			}
			
			// Parse the teacher GUIDs.
			JSONArray jsonContactTypeTeachingStaffGUIDs = (JSONArray) jsonTeachingStaff.get(jsonKeyString);
			Iterator<String> teacherGUIDiter = jsonContactTypeTeachingStaffGUIDs.iterator();
			while (teacherGUIDiter.hasNext()) {
				String teacherGUID = teacherGUIDiter.next();
				Teacher teacher = listOfTeachers.getTeacher(teacherGUID);
				if (teacher == null) {
					return null;
				}
				teachingStaff.get(contactType).add(teacher);
			}
		}
		return teachingStaff;
	}
	
	private HashMap<ContactType, TeachingRequest> parseTeachingRequests(final JSONObject jsonTeachingRequests)
	{
		HashMap<ContactType, TeachingRequest> teachingRequests = new HashMap<ContactType, TeachingRequest>();
		
		// Loop through the teaching requests, serialising them.
		for (Object jsonKey : jsonTeachingRequests.keySet()) {
			
			// Parse the contact type.
			String jsonKeyString = (String) jsonKey;
			ContactType contactType = ContactType.valueOf(jsonKeyString);
			if (contactType == null) {
				return null;
			}
			
			// Parse the teaching request.
			JSONObject jsonTeachingRequest = (JSONObject) jsonTeachingRequests.get(jsonKeyString);
			if (jsonTeachingRequest == null) {
				return null;
			}
			
			// Serialise the teaching request.
			TeachingRequest teachingRequest = parseTeachingRequest(jsonTeachingRequests);
			if (teachingRequest == null) {
				return null;
			}
			teachingRequests.put(contactType, teachingRequest);
		}
		return teachingRequests;
	}
	
	private TeachingRequest parseTeachingRequest(final JSONObject jsonRequest)
	{
		
		// Parse the status of the teaching request.
		final String jsonRequestStatus = (String) jsonRequest.get("status");
		if (jsonRequestStatus == null) {
			return null;
		}
		final RequestStatusType requestStatus = RequestStatusType.valueOf(jsonRequestStatus);
		if (requestStatus == null) {
			return null;
		}
		
		// Get the course requirement for parsing.
		final JSONObject jsonTeachingRequest = (JSONObject) jsonRequest.get("teachingRequest");
		if (jsonTeachingRequest == null) {
			return null;
		}
		
		// Parse the course requirement.
		final JSONObject jsonCourseRequirement = (JSONObject) jsonTeachingRequest.get("courseRequirement");
		if (jsonCourseRequirement == null) {
			return null;
		}
		CourseRequirement courseRequirement = parseCourseRequirement(jsonCourseRequirement);
		if (courseRequirement == null) {
			return null;
		}
		return new TeachingRequest(requestStatus, courseRequirement);
	}
	
	private CourseRequirement parseCourseRequirement(final JSONObject jsonCourseRequirement)
	{
		
		// Parse the contact type associated with the course requirement.
		final String jsonContactType = (String) jsonCourseRequirement.get("contactType");
		final ContactType contactType = ContactType.valueOf(jsonContactType);
		if (contactType == null) {
			return null;
		}
		
		// Parse the numerical values.
		int numberOfStudents;
		int numberOfStaff;
		int contactHours;
		try {
			numberOfStudents = Integer.parseInt((String) jsonCourseRequirement.get("numberOfStudents"));
			numberOfStaff = Integer.parseInt((String) jsonCourseRequirement.get("numberOfStaff"));
			contactHours = Integer.parseInt((String) jsonCourseRequirement.get("contactHours"));
		} catch (final NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		
		// Parse the qualifications associated with the course requirement.
		final JSONArray jsonQualifications = (JSONArray) jsonCourseRequirement.get("qualifications");
		if (jsonQualifications == null) {
			return null;
		}
		final Qualifications qualifications = parseQualifications(jsonQualifications);
		if (qualifications == null) {
			return null;
		}
		
		// Finally create the course requirement.
		return new CourseRequirement(contactType, numberOfStudents, numberOfStaff, 
				contactHours, qualifications);
	}

	@Override
	public boolean save()
	{
		return false;
	}

	@Override
	public boolean isAvailable() 
	{
		return listOfTeachers != null && listOfCourses != null;
	}	
}
