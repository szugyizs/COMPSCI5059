package project.controllers.types;

import project.controllers.Controller;
import project.storage.Storage;

public class AdministratorController extends Controller
{
	
	public AdministratorController(final Storage storage)
	{
		super(storage);
	}
	
	@Override
	public boolean processCommand(final String... args)
	{
		return false;
	}
	
}
