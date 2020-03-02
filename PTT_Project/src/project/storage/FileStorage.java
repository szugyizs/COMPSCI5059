package project.storage;

import java.io.FileWriter;
import java.io.IOException; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileStorage extends Storage
{
	
	public FileStorage(final String path)
	{
		
	}

	@Override
	public boolean isAvailable() 
	{
		return false;
	}

	@Override
	public boolean reload() 
	{
		return false;
	}

	@Override
	public void load() 
	{	
	}

	@Override
	public boolean save() 
	{
		return false;
	}
	
}
