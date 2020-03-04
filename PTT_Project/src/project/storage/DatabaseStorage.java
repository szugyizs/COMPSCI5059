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
		throw new UnsupportedOperationException("The load() method has not been implemented; saved for future use.");
	}

	/**
	 * A method to save data to the database from the system.
	 * @return boolean: a status variable, based on whether the operation was successful.
	 */
	@Override
	public boolean save() 
	{
		throw new UnsupportedOperationException("The save() method has not been implemented; saved for future use.");
	}

	/**
	 * A rudimentary error-checking method to ensure the lists to be saved from/to in the system actually exist.
	 * @return boolean: a status variable signalling whether there were any errors detected with this operation
	 */
	@Override
	public boolean isAvailable() 
	{
		throw new UnsupportedOperationException("The isAvailable method has not been implemented; saved for future use.");
	}
	
}
