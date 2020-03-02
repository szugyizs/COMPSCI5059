package project.requests;

public class TrainingRequest extends Request
{
	
	private int id; // The unique id associated with the training requirement.
	private TrainingRequirement trainingRequirement; // The training requirement the request is being made for.
	
	/**
	 * Explicit constructor initialises TrainingRequest instance. 
	 * 
	 * @param requestStatus The status of the TrainingRequest instance.
	 * @param id The request identification relative to a teacher instance. 
	 * @param trainingRequirement The TrainingRequirement instance; qualifications that are required.
	 */
	public TrainingRequest(final RequestStatusType requestStatus, int id, 
			final TrainingRequirement trainingRequirement)
	{
		super(requestStatus);
		
		this.id = id;
		this.trainingRequirement = trainingRequirement;
	}
	
	/**
	 * Implicit constructor initialises TrainingRequest instance with an initial requestStatus of PENDING. 
	 * 
	 * @param id The unique ID of the training request.
	 * @param trainingRequirement The TrainingRequrement instance for which the request is/was to be made.
	 */
	public TrainingRequest(int id, final TrainingRequirement trainingRequirement)
	{
		this(RequestStatusType.PENDING, id, trainingRequirement);
	}
	
	/**
	 * Getter for the training request id.
	 * 
	 * @return The id associated with the training requirement request.
	 */
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * Getter for the TrainingRequirement for which the request is being made.
	 * 
	 * @return The TrainingRequirement instance for which the request is being made. 
	 */
	public TrainingRequirement getTrainingRequirement()
	{
		return this.trainingRequirement;
	}
	
	/**
	 * Setter for the TrainingRequirement for which the request is being made.
	 * 
	 * @param trainingRequirement The TrainingRequirement instance for which the request is/was made.
	 */
	public void setTrainingRequirement(final TrainingRequirement trainingRequirement)
	{
		this.trainingRequirement = trainingRequirement;
	}
	
}
