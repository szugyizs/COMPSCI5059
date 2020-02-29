package project.requests;

public class TrainingRequest extends Request
{
	
	private TrainingRequirement trainingRequirement;
	
	/**
	 * Explicit constructor initialises TrainingRequest instance. 
	 * 
	 * @param requestStatus The status of the TrainingRequest instance.
	 * @param trainingRequirement The TrainingRequirement instance; qualifications that are required.
	 */
	public TrainingRequest(final RequestStatus requestStatus, final TrainingRequirement trainingRequirement)
	{
		super(requestStatus);
		this.trainingRequirement = trainingRequirement;
	}
	
	/**
	 * Implicit constructor initialises TrainingRequest instance with an initial requestStatus of PENDING. 
	 * 
	 * @param trainingRequirement The TrainingRequrement instance for which the request is/was to be made.
	 */
	public TrainingRequest(final TrainingRequirement trainingRequirement)
	{
		this(RequestStatus.PENDING, trainingRequirement);
	}
	
	/**
	 * @return The TrainingRequirement instance for which the request is being made. 
	 */
	public TrainingRequirement getTrainingRequirement()
	{
		return trainingRequirement;
	}
	
	/**
	 * @param trainingRequirement The TrainingRequirement instance for which the request is/was made.
	 */
	public void setTrainingRequirement(final TrainingRequirement trainingRequirement)
	{
		this.trainingRequirement = trainingRequirement;
	}
	
}
