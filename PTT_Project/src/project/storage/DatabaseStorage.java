package project.storage;
public class DatabaseStorage extends Storage
{
	
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
