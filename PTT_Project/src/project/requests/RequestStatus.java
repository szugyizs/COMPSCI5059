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
	 * @return - a user friendly string representation of the request status enumerator.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return - a user friendly string message associated with the request status enumerator.
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
