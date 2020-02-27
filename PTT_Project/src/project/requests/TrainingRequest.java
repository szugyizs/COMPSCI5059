package project.requests;

import project.course.TrainingRequirement;

public class TrainingRequest extends Request
{
	
	private TrainingRequirement trainingRequirement;
	
	/**
	 * Explicit constructor initialises the TrainingRequest instance.
	 * 
	 * @param requestStatus - the status of the training request.
	 * @param trainingRequirement - the training requirement instance for the request.
	 */
	public TrainingRequest(final RequestStatus requestStatus, final TrainingRequirement trainingRequirement)
	{
		super(requestStatus);
		this.trainingRequirement = trainingRequirement;
	}
	
	/**
	 * Implicit constructor initialises the TrainingRequest instance;
	 * the requestStatus is set to PENDING implicitly.
	 * 
	 * @param trainingRequirement - the training requirement instance for which the request is being made.
	 */
	public TrainingRequest(final TrainingRequirement trainingRequirement)
	{
		this(RequestStatus.PENDING, trainingRequirement);
	}
	
	/**
	 * Getter for the training requirement for which the request is being made.
	 * 
	 * @return - an instance of the training requirement.
	 */
	public TrainingRequirement getTrainingRequirement()
	{
		return trainingRequirement;
	}
	
	/**
	 * Explicitly set the training requirements for the request. Though should not be
	 * used.
	 * 
	 * @param trainingRequirement - the requirement for which the request is being made.
	 */
	public void setTrainingRequirement(final TrainingRequirement trainingRequirement)
	{
		this.trainingRequirement = trainingRequirement;
	}
	
}
