package project.requests;

public enum RequestStatus 
{
	
	PENDING("Pending", "Request pending response."),
	ACCEPTED("Accepted", "Request successfully accepted."),
	DENIED("Denied", "Request denied.");
	
	private String name;
	private String message;
	
	/**
	 * Constructor defining the request status.
	 * 
	 * @param name - the user friendly name of the status. 
	 * @param message - the user friendly message associated with the status.
	 */
	private RequestStatus(final String name, final String message)
	{
		this.name = name;
		this.message = message;
	}
	
	/**
	 * Getter for the user friendly state name.
	 * 
	 * @return - a string instance of the user friendly name.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Getter for the user friendly state message.
	 * 
	 * @return - a string instance of the user friendly state message.
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * Output the user friendly name of the request state.
	 */
	@Override
	public String toString()
	{
		return name;
	}
	
}
