package project.storage;

import static org.junit.Assert.assertThrows;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
/**
 * A class to handle the file storage data management.
 * This class provides methods to load and save system data to and from an external JSON File.
 */
public class FileStorage extends Storage
{

	String path;
	/**
	 * The constructor to the FileStorage class. It not only specifies the path but 
	 * loads the data into the system at "bootup".
	 * @param path: the path to the file which serves as the storage
	 */
	public FileStorage(final String path)
	{
		this.path = path;
		load();
	}
	/**
	 * A method to save all the data to the file storage from the system, it parses java objects and converts them into
	 * JSON Data.
	 * @return boolean: a binary value signalling the success of the operation
	 */
	@Override
	public boolean save()
	{
		JSONObject jsonRoot = new JSONObject();
		
		LinkedList<Teacher> teachersJSON = listOfTeachers.getTeachers();
		ListIterator listIterator = teachersJSON.listIterator(); 
		JSONArray teacherListArray = new JSONArray();

		//call the teacher specific data parser
		try {
			teacherListArray = teacherConvert(teachersJSON);
			jsonRoot.put("teachers", teacherListArray);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		LinkedList<Course> coursesJSON = listOfCourses.getCourses();
		listIterator = coursesJSON.listIterator(); 
		JSONArray courseListArray = new JSONArray();

		//call the course specific data parser
		try {
	        courseListArray = courseConvert(coursesJSON); 
	        jsonRoot.put("courses", courseListArray);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		//write to file, and possibly catch errors
		 try {
			 BufferedWriter file=new BufferedWriter(new FileWriter(path));
			 
		     file.write(jsonRoot.toJSONString());
		     
		     file.flush();
		     file.close();
		     
		    } catch (IOException e) {
		        System.out.println("Error saving JSON into "+path);
		        e.printStackTrace();
		    } 
		
		return false;
	}

	/**
	 * A method to convert the list of teachers from java objects to JSON Objects which can then
	 * be written to a JSON file.
	 * 
	 * @param teacherList: the list of teachers contained in the system, as a Java object
	 * @return allTeachers: A JSONArray which can be added to a root JSON file to be written to a file
	 */
	public JSONArray teacherConvert(LinkedList<Teacher> teacherList) {
		JSONArray allTeachers = new JSONArray();
		ListIterator<Teacher> listIterator = teacherList.listIterator();
		
		//do the data processing for each teacher object in the list
		while(listIterator.hasNext()){
	        	Teacher teacher = (Teacher)listIterator.next(); 
	        	JSONObject teacherObject = new JSONObject();
	        	
	        	teacherObject.put("guid", teacher.getGUID());
	        	teacherObject.put("surname", teacher.getSurname());
	        	teacherObject.put("forename", teacher.getForename());
	        	
	        	//parse qualifications
			    JSONArray qualifications = new JSONArray();
			    Qualifications qualificationList = teacher.getQualifications();

			    Map<SkillType, Short> qualificationSkills = qualificationList.getSkills();
			    
			    for (SkillType skillType : qualificationSkills.keySet()) {
			    	
			    }
			    Iterator mapIterator = qualificationSkills.entrySet().iterator();
				    
		        while (mapIterator.hasNext()) { 
				    JSONObject skillInfo = new JSONObject();
			        Map.Entry mapElement = (Map.Entry)mapIterator.next();
			        
				    
				    //parse skilltype
				    SkillType skillType = (SkillType)mapElement.getKey();
				    String skillTypeString = skillType.name();
				    skillInfo.put("skillType", skillTypeString);
				    
				    //parse level
				    String level = Short.toString(qualificationList.getSkillLevel(skillType));
				    skillInfo.put("level", level);
				    
					qualifications.add(skillInfo);
			    }
	        	
	        	teacherObject.put("qualifications", qualifications);

	        	//parse trainingRequests
	        	JSONArray trainingRequests = new JSONArray();
	        	List<TrainingRequest> trainingRequestList = teacher.getTrainingRequests();
	    		ListIterator<TrainingRequest> listIterator2 = trainingRequestList.listIterator();
	        	
	    		while(listIterator2.hasNext()){
		        	
	    			TrainingRequest trainingRequest = (TrainingRequest)listIterator2.next(); 
		        	JSONObject trainingRequestObject = new JSONObject();
		        	
		        	//parse status
					RequestStatusType requestStatusType = trainingRequest.getRequestStatus();
			        String requestStatus = requestStatusType.name();
			        trainingRequestObject.put("status", requestStatus);
		        	
		        	//parse trainingRequest
			        JSONObject requestObject = new JSONObject();
			        
			        //parse id
			        requestObject.put("id", trainingRequest.getId());
			        
			        //parse trainingRequirement
		        	//parse qualifications
				    qualifications = new JSONArray();
				    qualificationList = trainingRequest.getTrainingRequirement().getQualifications();

				    qualificationSkills = qualificationList.getSkills();
				    mapIterator = qualificationSkills.entrySet().iterator();
					    
			        while (mapIterator.hasNext()) { 
					    JSONObject skillInfo = new JSONObject();
				        Map.Entry mapElement = (Map.Entry)mapIterator.next();
					    
					    //parse skilltype
					    SkillType skillType = (SkillType)mapElement.getKey();
					    String skillTypeString = skillType.name();
					    skillInfo.put("skillType", skillTypeString);
					    
					    //parse level
					    String level = Short.toString(qualificationList.getSkillLevel(skillType));
					    skillInfo.put("level", level);
					    
						qualifications.add(skillInfo);
				    }
		        	
		        	requestObject.put("qualifications", qualifications);
			        
			        trainingRequestObject.put("trainingRequest", requestObject);
			        
	    		}
	        	teacherObject.put("trainingRequests", trainingRequests);
	        	allTeachers.add(teacherObject);
		}
	
		return allTeachers;
	}
	
	/**
	 * A method to convert the list of courses from java objects to JSON Objects which can then
	 * be written to a JSON file.
	 * 
	 * @param courseList: the list of courses contained in the system, as a Java object
	 * @return allCourses: A JSONArray which can be added to a root JSON file to be written to a file
	 */
	public JSONArray courseConvert(LinkedList<Course> courseList) {
		JSONArray allCourses = new JSONArray();
		ListIterator<Course> listIterator = courseList.listIterator();
		
		//do the data translation for each of the course objects in the list
		while(listIterator.hasNext()){
	        Course course = (Course)listIterator.next(); 
	        JSONObject courseObject = new JSONObject();
	        	
	        courseObject.put("courseID", course.getCourseID());
	        courseObject.put("name", course.getName());
	        courseObject.put("description", course.getDescription());
		
	        
	        //parse teachingStaff
	        JSONObject teachingStaff = new JSONObject();
	        HashMap<ContactType, List<Teacher>> teachingStaffMap = course.getTeachingStaff();
	        Iterator mapIterator = teachingStaffMap.entrySet().iterator();
	        
	        while (mapIterator.hasNext()) { 
		        
		        Map.Entry mapElement = (Map.Entry)mapIterator.next();
		        ContactType contactType = (ContactType)mapElement.getKey();
		        String contactLabel = contactType.name();
		        
		        JSONArray teachingStaffTeachers = new JSONArray();
		        List<Teacher> teachers = new LinkedList<Teacher>();
		        ListIterator teacherListIterator = teachers.listIterator(); 
		        
		        while(teacherListIterator.hasNext()){ 
		        	Teacher teacherItem = (Teacher)teacherListIterator.next();
		        	teachingStaffTeachers.add(teacherItem.getGUID()); 
		        } 
		        
		        teachingStaff.put(contactLabel, teachingStaffTeachers);
	        }

		    courseObject.put("teachingStaff", teachingStaff);
	        
	        //parse teachingRequests
		    JSONObject teachingRequirements = new JSONObject();
	        
	        HashMap<ContactType, TeachingRequest> teachingRequestMap = new HashMap <ContactType, TeachingRequest>();
	        teachingRequestMap = course.getTeachingStaffRequirementsRequests();
	        mapIterator = teachingRequestMap.entrySet().iterator();
	        
	        //parse contact and teaching req
	        while (mapIterator.hasNext()) { 
		        
		        Map.Entry mapElement  = (Map.Entry)mapIterator.next();
		        ContactType contactType = (ContactType)mapElement.getKey();
		        String contactLabel = contactType.name();
		        
		        JSONObject contactDetails = new JSONObject();
		        TeachingRequest request = (TeachingRequest)mapElement.getValue();
		        
			        //parse status
					RequestStatusType requestStatusType = request.getRequestStatus();
			        String requestStatus = requestStatusType.name();
			        contactDetails.put("status", requestStatus);
			        
			        //parse teaching request
				    JSONObject teachingRequest = new JSONObject();
				    
				    	//parse course Requirement
					    JSONObject courseRequirements = new JSONObject();
					    CourseRequirement courseRequirement = request.getCourseRequirement();
					    
					    	//parse contact type
					    	String contactTypeString = courseRequirement.getContactType().name();
					    	courseRequirements.put("contactType", contactTypeString);
						    
						    //parse numberOfStudents
						    String numberOfStudents = Integer.toString(courseRequirement.getNumberOfStudents());
					    	courseRequirements.put("numberOfStudents", numberOfStudents);
						    
						    //parse numberOfStaff
					    	String numberOfStaff = Integer.toString(courseRequirement.getNumberOfStaff());
					    	courseRequirements.put("numberOfStaff", numberOfStaff);
						    
						    //parse contactHours
					    	String contactHours = Integer.toString(courseRequirement.getContactHours());
					    	courseRequirements.put("contactHours", contactHours);
						    
						    //parse qualifications
						    JSONArray qualifications = new JSONArray();
						    Qualifications qualificationList = courseRequirement.getRequiredStaffQualifications();

						    Map<SkillType, Short> qualificationSkills = new HashMap<SkillType, Short>();
						    qualificationSkills = qualificationList.getSkills();
					        mapIterator = qualificationSkills.entrySet().iterator();
					        
					        //parse contact and teaching req
					        while (mapIterator.hasNext()) { 
							    JSONObject skillInfo = new JSONObject();
						        mapElement = (Map.Entry)mapIterator.next();
							    
							    //parse skilltype
							    SkillType skillType = (SkillType)mapElement.getKey();
							    String skillTypeString = skillType.name();
							    skillInfo.put("skillType", skillTypeString);
							    
							    //parse level
							    String level = Short.toString(qualificationList.getSkillLevel(skillType));
							    skillInfo.put("level", level);
							    
								qualifications.add(skillInfo);
						    }
						    
					    	courseRequirements.put("qualifications", qualifications);
					    		
					    teachingRequest.put("courseRequirement", courseRequirements);
					
				    contactDetails.put("teachingRequest", teachingRequest);

		        teachingRequirements.put(contactLabel, contactDetails);
	        }
	        
		    courseObject.put("teachingRequests", teachingRequirements);
			allCourses.add(courseObject);
		}
		return allCourses;
	}
	
	/**
	 * A method to load all the data from the file storage to the system, it parses JSON data and converts them into
	 * Java objects and fills the required lists in the system.
	 * @return boolean: a binary value signalling the success of the operation
	 */
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
			
			Teacher teacher = super.listOfTeachers.getTeachers().get(0);
			System.out.println(teacher.getForename() + " " + teacher.getTrainingRequests().size());
			super.listOfTeachers.printTrainingRequests(System.out);
			
			listOfCourses.print(System.out);
			
		} catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * A method to convert a list of teachers from a JSON Object to a ListOfTeachers Object.
	 * @param jsonTeachers: the JSON data read in, to be formatted
	 * @return ListOfTeachers: the ListOfTeachers object which can be handled more easily by the program
	 */
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
			System.out.println("\n\n" + teacher.getTrainingRequests().size());
			teacher.printTeacher(System.out);
			teacher.printTrainingRequests(System.out);
			System.out.println("\n\n");
			listOfTeachers.addTeacher(teacher);
		}
		
		return listOfTeachers;
	}
	
	/**
	 * A method to convert a teacher from a JSON Object to a teacher Object.
	 * @param jsonTeacher: the JSON data read in, to be formatted
	 * @return Teacher: the Teacher object which can be handled more easily by the program
	 */
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
		
		// Check whether or not there are any training requests.
		JSONArray jsonTeacherTrainingRequests = (JSONArray) jsonTeacher.get("trainingRequests");
		if (jsonTeacherTrainingRequests == null) {
			return new Teacher(teacherGUID, teacherForename, teacherSurname, qualifications);
		}
		
		// The training requests associated with the teacher.
		List<TrainingRequest> teacherTrainingRequests = new ArrayList<TrainingRequest>();
				
		// Parse each of the training requests for the teacher.
		Iterator<JSONObject> iter = jsonTeacherTrainingRequests.iterator();
		TrainingRequest trainingRequest;
		while (iter.hasNext()) {
			
			// Attempt to parse the training request, return null if this cannot be parsed.
			trainingRequest = parseTrainingRequest(iter.next());
			if (trainingRequest == null) {
				return null;
			}
			teacherTrainingRequests.add(trainingRequest);
		}
		
		// We have successfully parsed the teachers qualifications.
		return new Teacher(teacherGUID, teacherForename, teacherSurname,
				qualifications, teacherTrainingRequests);
	}
	
	/**
	 * A method to convert qualifications from a JSON Object to a Qualifications Object.
	 * @param jsonQualifications: the JSON data read in, to be formatted
	 * @return Qualifications: the Qualifications object which can be handled more easily by the program
	 */
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
	
	/**
	 * A method to convert a training request from a JSON Object to a TrainingRequest Object.
	 * @param jsonRequest: the JSON data read in, to be formatted
	 * @return tr: the TrainingRequest object which can be handled more easily by the program
	 */
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
		TrainingRequest tr = new TrainingRequest(teacherTrainingRequestId, teacherTrainingRequirement);
		tr.printRequest(System.out);
		System.out.println("\n\n");
		return tr;
	}

	/**
	 * A method to convert a training requirement from a JSON Object to a TrainingRequirement Object.
	 * @param jsonTrainingRequirement: the JSON data read in, to be formatted
	 * @return TrainingRequirement: the training requirement object which can be handled more easily by the program
	 */
	private TrainingRequirement parseTrainingRequirement(final JSONObject jsonTrainingRequirement)
	{
		final JSONArray jsonQualifications = (JSONArray) jsonTrainingRequirement.get("qualifications");
		final Qualifications teacherQualifications = parseQualifications(jsonQualifications);
		return new TrainingRequirement(teacherQualifications);
	}

	/**
	 * A method to convert a list of courses from a JSON Object to a ListOfCourses Object.
	 * @param listOfTeachers: a list of teachers to be associated with the courses
	 * @param jsonCourses: the JSON data read in, to be formatted
	 * @return ListOfCourses: the list of Course objects which can be handled more easily by the program
	 */
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
	
	/**
	 * A method to convert a course from a JSON Object to a Course Object.
	 * @param listOfTeachers: a list of teachers to be associated with the course
	 * @param jsonCourse: the JSON data read in, to be formatted
	 * @return Course: the Course object which can be handled more easily by the program
	 */
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
		
		for (ContactType contactType : teachingStaff.keySet()) {
			for (Teacher t : teachingStaff.get(contactType)) {
				t.printTeacher(System.out);
			}
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

	/**
	 * A method to convert the list of teaching staff for a course, and their associated types of contact, 
	 * from a JSON Object to a java hashmap of contacttype and list of teacher objects.
	 * @param listOfTeachers: the list of teachers class for the JSON data to be loaded into
	 * @param jsonTeachingStaff: the JSON data read in, to be formatted
	 * @return teachingStaff: the contactType and teacher list hashmap object which can 
	 * 		   be handled more easily by the program
	 */
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
	
	/**
	 * A method to convert the list of teaching requests and their associated types of contact, 
	 * from a JSON Object to a java hashmap of contacttype and teachingrequest objects.
	 * @param jsonTeachingRequests: the JSON data read in, to be formatted
	 * @return teachingRequests: the contactType and TeachingRequest hashmap object which can 
	 * 		   be handled more easily by the program
	 */
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
			TeachingRequest teachingRequest = parseTeachingRequest(jsonTeachingRequest);
			if (teachingRequest == null) {
				return null;
			}
			teachingRequests.put(contactType, teachingRequest);
		}
		return teachingRequests;
	}

	/**
	 * A method to convert a teaching request from a JSON Object to a TeachingRequest Object.
	 * @param jsonRequest: the JSON data read in, to be formatted
	 * @return TeachingRequest: the TeachingRequest object which can be handled more easily by the program
	 */
	private TeachingRequest parseTeachingRequest(final JSONObject jsonRequest)
	{
		
		System.out.println(jsonRequest.toJSONString());
		
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
	
	/**
	 * A method to convert course requirement from a JSON Object to a CourseRequirement Object.
	 * @param jsonCourseRequirement: the JSON data read in, to be formatted
	 * @return CourseRequirement: the Course Requirement object which can be handled more easily by the program
	 */
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

	/**
	 * A rudimentary error-checking method to ensure the lists to be saved from/to in the system actually exist.
	 * @return boolean: a status variable signalling whether there were any errors detected with this operation
	 */
	@Override
	public boolean isAvailable() 
	{
		return listOfTeachers != null && listOfCourses != null;
	}	
}
