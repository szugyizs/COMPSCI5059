package project.requests;

import java.io.PrintStream;

public abstract class Request
{

	private RequestStatusType requestStatus; // The status of the request.

	protected Request(final RequestStatusType requestStatus)
	{
		this.requestStatus = requestStatus;
	}

	public RequestStatusType getRequestStatus()
	{
		return this.requestStatus;
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
	 * Called to print the status of the request to the print stream.
	 * 
	 * @param printStream The PrintStream to which the print should occur.
	 */
	public void printRequest(final PrintStream printStream)
	{
		printStream.println(String.format("Request Status: %s", this.requestStatus.getName()));
	}

	@Override
	public String toString()
	{
		return String.format("Request status: %s - %s", requestStatus.getName(), requestStatus.getMessage());
	}

}
