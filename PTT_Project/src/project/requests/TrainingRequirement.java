package project.requests;

import project.requests.course.Qualifications;

public class TrainingRequirement
{

	private Qualifications qualifications;

	/**
	 * Constructor explicitly instantiates the TrainingRequirement instance.
	 * 
	 * @param teacher        The Teacher [instance] that requires additional
	 *                       training.
	 * @param qualifications The Qualifications [instance] that'll be obtained post
	 *                       training.
	 */
	public TrainingRequirement(final Qualifications qualifications)
	{
		this.qualifications = qualifications;
	}

	/**
	 * @return The qualifications that are to be obtained post training by the
	 *         Teacher instance.
	 */
	public Qualifications getQualifications()
	{
		return qualifications;
	}

	/**
	 * @param qualifications The qualifications that are to be obtained post
	 *                       training by the Teacher instance.
	 */
	public void setQualifications(final Qualifications qualifications)
	{
		this.qualifications = qualifications;
	}

	@Override
	public String toString()
	{
		return String.format("Required training: %s", qualifications.toString());
	}

}
