package project;

import static org.junit.Assert.*;

import project.controllers.types.AdministratorController;
import project.controllers.types.CourseDirectorController;
import project.controllers.types.PTTDirectorController;
import project.storage.FileStorage;
import project.storage.Storage;

public class Test { // TODO ensure test class tests all user stories 
	private Storage storage = new FileStorage("");
	
    public void testAdminCommands() { // TODO
    	AdministratorController adminController = new AdministratorController(storage);
    	boolean result = adminController.processCommand("logout", "");
    	assertEquals(true, result);
    	result = adminController.processCommand("get teachers", "");
    	assertEquals(true, result);
    	result = adminController.processCommand("get teaching-requests", "");
    	assertEquals(true, result);
    	result = adminController.processCommand("quit", "");
    	assertEquals(false, result);
    	}
    
    public void testPTTCommands() { // TODO
    	PTTDirectorController pTTController = new PTTDirectorController(storage);
    	boolean result = pTTController.processCommand("logout", "");
    	assertEquals(true, result);
    	result = pTTController.processCommand("get teaching-requests", "");
    	assertEquals(true, result);
    	result = pTTController.processCommand("get training-requests", "");
    	assertEquals(true, result);
    	
    	result = pTTController.processCommand("quit", "");
    	assertEquals(false, result);
    	}
    
    public void testCourseDirectorCommands() { // TODO
    	CourseDirectorController directorController = new CourseDirectorController(storage, null);
    	boolean result = directorController.processCommand("logout", "");
    	assertEquals(true, result);
    	result = directorController.processCommand("get teaching-requests", "");
    	assertEquals(true, result);
    	result = directorController.processCommand("get training-requests", "");
    	assertEquals(true, result);
    	
    	result = directorController.processCommand("quit", "");
    	assertEquals(false, result);
    	}
    
    public void testHelpMessages() {
		// TODO
	}
    
    
    public void testAddTeachingRequirement() { // TODO
    	CourseDirectorController directorController = new CourseDirectorController(storage, null);
    }
    
    
    public void testRequestGenerated() { // TODO
    	
    	
    }
    
    public void testStartPrompt() { // TODO
    	
    }
    
    public void testCorrectRole () { // TODO
    	
    }
    
    // admin tests
    
    //	ptt tests
    
    // course director tests
    
    
    
    
    
        

    
}