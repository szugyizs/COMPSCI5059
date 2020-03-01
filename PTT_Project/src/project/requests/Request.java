package project.requests;

public abstract class Request 
{
	
	private RequestStatusType requestStatus;
	
	protected Request(final RequestStatusType requestStatus)
	{
		this.requestStatus = requestStatus;
	}
	
	protected Request()
	{
		this(RequestStatusType.PENDING);
	}
	
	/**
	 * Getter for the request state.
	 * 
	 * @return - an instance of the RequestState that represents the current request state.
	 */
	public RequestStatusType getRequestStatus()
	{
		return requestStatus;
	}
	
	/**
	 * Explicitly set the state of the request.
	 * 
	 * @param requestStatus - the new state of the request.
	 */
	public void setRequestStatus(final RequestStatusType requestStatus)
	{
		this.requestStatus = requestStatus;
	}
	
	/**
	 * Accept the request i.e. implicitly set the request state to ACCEPTED.
	 */
	public void acceptRequest()
	{
		requestStatus = RequestStatusType.ACCEPTED;
	}
	
	/**
	 * Deny the request i.e. implicitly set the request state to DENIED.
	 */
	public void denyRequest()
	{
		requestStatus = RequestStatusType.DENIED;
	}
	
	@Override
	public String toString()
	{
		return String.format("Request status: %s - %s",requestStatus.getName(), requestStatus.getMessage());
	}
	
}
