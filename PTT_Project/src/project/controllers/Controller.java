package project.controllers;

import project.storage.Storage;

public abstract class Controller
{
	
	protected Storage storage;
	
	protected Controller(final Storage storage)
	{
		this.storage = storage;
	}
	
	public Storage getStorage()
	{
		return storage;
	}
	
	public void setStorage(final Storage storage)
	{
		this.storage = storage;
	}
	
	public abstract boolean processCommand(final String... args);

}
