package project;

import static org.junit.Assert.*;

import project.controllers.types.AdministratorController;
import project.controllers.types.CourseDirectorController;
import project.controllers.types.PTTDirectorController;
import project.storage.FileStorage;
import project.storage.Storage;

/**
 * A test class to test certain methods
 */

public class Test { 
	private Storage storage = new FileStorage("lib/database.json");
	PTTDirectorController pTTController = new PTTDirectorController(storage);
	AdministratorController adminController = new AdministratorController(storage);
	CourseDirectorController directorController = new CourseDirectorController(storage, adminController.getListOfCourses().getCourse("COMPSCI5059"));
	
	/**
	 * Tests that the administrator commands work as expected.
	 */
	public void testAdminCommands() { 

		boolean result = adminController.processCommand("logout", "");
		assertEquals(false, result);
		result = adminController.processCommand("failTest", "");
		assertEquals(false, result);
		result = adminController.processCommand("get req COMPSCI5059", "");
		assertEquals(true, result);
		result = adminController.processCommand("get req 2500414V", "");
		assertEquals(true, result);
		result = adminController.processCommand("get courses", "");
		assertEquals(true, result);
		result = adminController.processCommand("get teachers", "");
		assertEquals(true, result);
		// Needs correct database with teaching requirement and fitting teacher
		result = adminController.processCommand("set status teachreq COMPSCI1023 LAB ACCEPTED", "");
		assertEquals(true, result);
		// Prints "not implemented" but passes
		result = pTTController.processCommand("set status trainreq 2504756K 0 DENIED", ""); 
		assertEquals(true, result);
	}
	
	/**
	 * Tests that the PTT commands work as expected.
	 */
	public void testPTTCommands() { 
		boolean result = pTTController.processCommand("logout", "");
		assertEquals(false, result);
		result = pTTController.processCommand("failTest", "");
		assertEquals(false, result);
		result = pTTController.processCommand("get teachreq COMPSCI5059", "");
		assertEquals(true, result);
		result = pTTController.processCommand("get trainreq 2500414V", "");
		assertEquals(true, result);
		result = pTTController.processCommand("get trainreq 25414V", "");
		assertEquals(false, result);
		result = pTTController.processCommand("set status teachreq COMPSCI5059 LAB ACCEPTED", "");
		assertEquals(true, result);
		result = pTTController.processCommand("set status teachreq COMPSCI5059 ACCEPTED LAB", "");
		assertEquals(false, result);
		// Needs correct database with teaching requirement and fitting teacher
		result = pTTController.processCommand("set course COMPSCI1023 LAB 2504756K", ""); 
		assertEquals(true, result);
		// This test needs user input by the tester: e.g. "SOFTWARE_ENGINEERING 1" and "done"
		result = pTTController.processCommand("set req 2504756K", ""); 
		assertEquals(true, result);
		// Prints "not implemented" but passes
		result = pTTController.processCommand("set quali 2504756K 0", ""); 
		assertEquals(true, result);
		result = pTTController.processCommand("quit", "");
		assertEquals(false, result);

	}
	
	/**
	 * Tests that the course director commands work as expected.
	 */
	public void testCourseDirectorCommands() {  

		boolean result = directorController.processCommand("logout", "");
		assertEquals(false, result);
		result = directorController.processCommand("failTest", "");
		assertEquals(false, result);
		// This test needs user input by the tester: e.g. "SOFTWARE_ENGINEERING 1" and "done"
		result = directorController.processCommand("req LAB 1 1 1", "");
		assertEquals(true, result);
		result = directorController.processCommand("quit", "");
		assertEquals(false, result);
	}
}