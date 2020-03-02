package project.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import project.storage.lists.ListOfCourses;
import project.storage.lists.ListOfTeachers;

public class FileStorage extends Storage
{

	//TODO - comment this class
	//TODO - review functionality
	String path;
	String temp = "{'name':'Jon Snow','age':22,'student':{'id':'Jon_Snow_22','subjects':['Maths','Science']}}";

	public FileStorage(final String path) {
		super.listOfTeachers = new ListOfTeachers();
		super.listOfCourses = new ListOfCourses();
		this.path = path;
	}

	@Override
	public boolean isAvailable() //TODO do
	{

		//error checking
		return false;
	}

	@Override
	public boolean reload() //TODO do
	{
		if (isAvailable()) 
		{
			save();
			load();
		}
		
		return false;
	}

	@Override
	public void load() //TODO do
	{	
		if (isAvailable()) 
		{
			setListOfTeachers(null);
			setListOfCourses(null);
		}
	}

	@Override
	public boolean save() //TODO do
	{
		if (isAvailable()) 
		{
			
		}
		return false;
	}



	public String loadLists(ListOfCourses courseList, ListOfTeachers teacherList) { //TODO review if necessary
		return "";
	}
	
	public void reloadLists(ListOfCourses courseList, ListOfTeachers teacherList) { //TODO review if necessary
		saveLists(courseList, teacherList);
		loadLists(courseList, teacherList);
	}
	
	public String saveLists(ListOfCourses courseList, ListOfTeachers teacherList) { //TODO review if necessary
		path = "lib/out.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(path)) {
    		gson.toJson(courseList, writer);
    		gson.toJson(teacherList, writer);
        }
        catch (IOException e) {
        	e.printStackTrace();
			return "Error saving";
        }

		return "Success Saving!";
	}
	
	public String saveAllToFile() { //TODO review if necessary
		path = "lib/out.json";
		
		try (FileWriter writer = new FileWriter(path)) {
			writer.write(temp);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
			return "Error saving";
		}
		
		return "Success Saving!";
	}

	public String readAllToString() { //TODO review if necessary

    	String json = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
        	try {
        	    StringBuilder builder = new StringBuilder();
        	    String line = reader.readLine();

        	    while (line != null) {
        	    	builder.append(line);
        	        line = reader.readLine();
        	    }
        	    json = builder.toString().replaceAll("\\s+","");
        	} finally {
        	    reader.close();
        	}
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        
		return json;
	}
	
	
}
