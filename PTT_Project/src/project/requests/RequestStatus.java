package project.requests;

public enum RequestStatus 
{
	
	PENDING("Pending", "Request pending response."),
	ACCEPTED("Accepted", "Request successfully accepted."),
	DENIED("Denied", "Request denied.");
	
	private String name;
	private String message;
	
	private RequestStatus(final String name, final String message)
	{
		this.name = name;
		this.message = message;
	}
	
	public String getName()
	{
		return name;
	}
	
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
