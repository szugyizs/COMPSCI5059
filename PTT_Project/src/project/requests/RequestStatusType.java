package project.requests;

/**
 * RequestStatusType denotes the possible states that a Request can possess. Consequently,
 * this prevents the request from obtaining an illegal state.
 */
public enum RequestStatusType 
{
	
	PENDING("Pending", "Request pending response."),
	ACCEPTED("Accepted", "Request successfully accepted."),
	DENIED("Denied", "Request denied.");
	
	private String name; // The user friendly name of the state.
	private String message; // The user friendly message associated with the state.
	
	/**
	 * Constructor implicitly used to define the RequestStatusType enumerator arguments.
	 * Note, no attempts should be made to invoke this outside this RequestStatusType 
	 * enumerator.
	 * 
	 * @param name The user friendly name of the status.
	 * @param message The user friendly message associated with the status.
	 */
	private RequestStatusType(final String name, final String message)
	{
		this.name = name;
		this.message = message;
	}
	
	/**
	 * Getter for the user friendly name of the status.
	 * 
	 * @return A string instance representing the user friendly name.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Getter for the user friendly message of the status.
	 * 
	 * @return A string instance representing the user friendly message of the status.
	 */
	public String getMessage()
	{
		return message;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
}
