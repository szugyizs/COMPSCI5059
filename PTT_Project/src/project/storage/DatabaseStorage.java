package project.storage;

/**
 * A class to handle the database storage data management.
 * This class provides methods to load and save system data to and from an external database.
 */
public class DatabaseStorage extends Storage
{
	/**
	 * A method to load data from the database to the system.
	 * @return boolean: status, based on whether the operation was successful.
	 */
	@Override
	public boolean load() 
	{
		return false;
	}

	/**
	 * A method to save data to the database from the system.
	 * @return boolean: a status variable, based on whether the operation was successful.
	 */
	@Override
	public boolean save() 
	{
		return false;
	}

	/**
	 * A rudimentary error-checking method to ensure the lists to be saved from/to in the system actually exist.
	 * @return boolean: a status variable signalling whether there were any errors detected with this operation
	 */
	@Override
	public boolean isAvailable() 
	{
		return false;
	}
	
}
