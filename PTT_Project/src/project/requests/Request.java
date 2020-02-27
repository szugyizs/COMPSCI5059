package project.requests;

public abstract class Request 
{
	
	private RequestStatus requestStatus;
	
	protected Request(final RequestStatus requestStatus)
	{
		this.requestStatus = requestStatus;
	}
	
	protected Request()
	{
		this(RequestStatus.PENDING);
	}
	
	public RequestStatus getRequestStatus()
	{
		return requestStatus;
	}
	
	public void setRequestStatus(final RequestStatus requestStatus)
	{
		this.requestStatus = requestStatus;
	}
	
	public void acceptRequest()
	{
		requestStatus = RequestStatus.ACCEPTED;
	}

	public void denyRequest()
	{
		requestStatus = RequestStatus.DENIED;
	}
	
	@Override
	public String toString()
	{
		return String.format("Request status: %s - %s",requestStatus.getName(), requestStatus.getMessage());
	}
	
}
