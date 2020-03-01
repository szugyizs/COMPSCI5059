package project.requests;

public class TrainingRequest extends Request
{
	
	private TrainingRequirement trainingRequirement;
	
	/**
	 * Explicit constructor initialises TrainingRequest instance. 
	 * 
	 * @param requestStatus The status of the TrainingRequest instance.
	 * @param id The request identification relative to a teacher instance. 
	 * @param trainingRequirement The TrainingRequirement instance; qualifications that are required.
	 */
	//TODO: should this be possible to call from outside? maybe this should be made private? 
	public TrainingRequest(final RequestStatusType requestStatus, int id, final TrainingRequirement trainingRequirement)
	{
		super(requestStatus, id);
		this.trainingRequirement = trainingRequirement;
	}
	
	/**
	 * Implicit constructor initialises TrainingRequest instance with an initial requestStatus of PENDING. 
	 * 
	 * @param trainingRequirement The TrainingRequrement instance for which the request is/was to be made.
	 */
	public TrainingRequest(final TrainingRequirement trainingRequirement, int id)
	{
		this(RequestStatusType.PENDING, id, trainingRequirement);
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
