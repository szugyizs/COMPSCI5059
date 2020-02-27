package project.requests;

import project.course.TrainingRequirement;

public class TrainingRequest extends Request
{
	
	private TrainingRequirement trainingRequirement;
	
	public TrainingRequest(final RequestStatus requestStatus, final TrainingRequirement trainingRequirement)
	{
		super(requestStatus);
		
		this.trainingRequirement = trainingRequirement;
	}
	
	public TrainingRequirement getTrainingRequirement()
	{
		return trainingRequirement;
	}
	
	public void setTrainingRequirement(final TrainingRequirement trainingRequirement)
	{
		this.trainingRequirement = trainingRequirement;
	}
	
}
