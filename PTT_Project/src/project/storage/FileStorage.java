package project.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import project.requests.course.Course;
import project.requests.course.Teacher;
import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public class FileStorage extends Storage
{

	//TODO - comment this class
	//TODO - review functionality
	String path;
	
	public FileStorage(final String path) {
		this.path = path;
	}

	@Override
	public void reload()
	{
		save();
		load();
	}

	@Override
	public void load() //TODO in one file not two
	{	
		path = "lib/dataC.json";
		Gson gson = new Gson();
		
		Type courseType = new TypeToken<HashMap<String, LinkedList<Course>>>(){}.getType();
        try (FileReader reader = new FileReader(path)) {
			HashMap<String, LinkedList<Course>> course = gson.fromJson(reader, courseType);
			LinkedList<Course> listCourses = course.get("courses");
			listOfCourses = new ListOfCourses(listCourses);
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		path = "lib/dataT.json";
		gson = new Gson();
		
		Type teacherType = new TypeToken<HashMap<String, LinkedList<Teacher>>>(){}.getType();
        try (FileReader reader = new FileReader(path)) {
			HashMap<String, LinkedList<Teacher>> teacher = gson.fromJson(reader, teacherType);
			LinkedList<Teacher> listTeachers = teacher.get("teachers");
			listOfTeachers = new ListOfTeachers(listTeachers);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void save() //TODO do
	{
		path = "lib/dataCO.json";
		
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(listOfCourses);
        
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(listOfCourses, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		path = "lib/dataTO.json";
		
         gson = new GsonBuilder().setPrettyPrinting().create();
        
        json = gson.toJson(listOfTeachers);
        
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(listOfTeachers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
