package neuronalNetworkSerializable;

import java.io.Serializable;

public class NeuronalNetworkGUIProperties implements Serializable
{
	// Learning
	boolean learnOptionsIsSet = false;
	String trainingFilePath, learningRule;
	double learningRate;
	int maxIterations;
	
	// View
	int zoomOptionIndex, hSpace, vSpace;

	
	/**
	 * @return the trainingFilePath
	 */
	public String getTrainingFilePath()
	{
		return trainingFilePath;
	}

	/**
	 * @return the learningRate
	 */
	public double getLearningRate()
	{
		return learningRate;
	}

	/**
	 * @return the maxIterations
	 */
	public int getMaxIterations()
	{
		return maxIterations;
	}

	/**
	 * @return the learningRule
	 */
	public String getLearningRule()
	{
		return learningRule;
	}

	/**
	 * @return the zoomOptionIndex
	 */
	public int getZoomOptionIndex()
	{
		return zoomOptionIndex;
	}

	/**
	 * @return the hSpace
	 */
	public int gethSpace()
	{
		return hSpace;
	}

	/**
	 * @return the vSpace
	 */
	public int getvSpace()
	{
		return vSpace;
	}

	/**
	 * @param trainingFilePath the trainingFilePath to set
	 */
	public void setTrainingFilePath(String trainingFilePath)
	{
		this.trainingFilePath = trainingFilePath;
	}

	/**
	 * @param learningRate the learningRate to set
	 */
	public void setLearningRate(double learningRate)
	{
		this.learningRate = learningRate;
	}

	/**
	 * @param maxIterations the maxIterations to set
	 */
	public void setMaxIterations(int maxIterations)
	{
		this.maxIterations = maxIterations;
	}

	/**
	 * @param learningRule the learningRule to set
	 */
	public void setLearningRule(String learningRule)
	{
		this.learningRule = learningRule;
	}

	/**
	 * @param zoomOptionIndex the zoomOptionIndex to set
	 */
	public void setZoomOptionIndex(int zoomOptionIndex)
	{
		this.zoomOptionIndex = zoomOptionIndex;
	}

	/**
	 * @param hSpace the hSpace to set
	 */
	public void sethSpace(int hSpace)
	{
		this.hSpace = hSpace;
	}

	/**
	 * @param vSpace the vSpace to set
	 */
	public void setvSpace(int vSpace)
	{
		this.vSpace = vSpace;
	}
	
	/**
	 * Marks the LearnOptions as set
	 */
	public void setLearnOptions()
	{
		learnOptionsIsSet = true;
	}
	
	/**
	 * @return the learnOptionsIsSet
	 */
	public boolean learnOptionsIsSet()
	{
		return learnOptionsIsSet;
	}
}
