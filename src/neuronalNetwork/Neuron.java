package neuronalNetwork;

import java.util.Arrays;

/* This class implements the functionality that each
 * Neuron needs internally. A single Neuron does not
 * know who it is connected to. It gets information on
 * its input by the Class Network. 
 */
/**
 * @author Holger Vornholt, Tobias Eidmann, Michael Martin
 */
public class Neuron {
	// A Neuron always remembers its previous Activity and needs
	// to calculate a new Activity when it needs to calculate an output value.
	private double currentActivity;
	private double lastOutput;
	//used by Backpropagation Algorithm
	private double lastPropagation;
	// There are different ways to propagate weighted input. The different functions
	// are defined in the propagation Method. The String in propagationFunction
	// defines which function will be used.
	private String propagationFunction;
	private String[] possiblePropFunc = {"sum","prod","max","min"};
	//As with the propagation functions the used function will be saved in the String activationFunction.
	//However there might be parameters that will be saved in activationParam
	private String activationFunction;
	private double[] activationParam;
	private String[] possibleActFunc = {"lin","sig"};
	
	public Neuron(){
		currentActivity = 0;
		this.propagationFunction = "sum";
	}
	
	public Neuron(String propFunc){
		currentActivity = 0;
		activationFunction = "lin";
		this.setPropagationFunction(propFunc);
	}
	
	public Neuron(String propFunc,String actFunc, double[] actParam){
		currentActivity = 0;
		this.setActivationFunction(actFunc,actParam);
		this.setPropagationFunction(propFunc);
	}
	
	public Neuron(Neuron neuron){
		currentActivity = neuron.getCurrentActivity();
		propagationFunction = neuron.getPropagationFunction();
		activationFunction = neuron.getActivationFunction();
		activationParam = neuron.getActivationParam();
	}
	
	// +++++++++++++++++++++++++++PROPAGATION+++++++++++++++++++++++++++++++++++
	private double propagation(double[] input, double[] weights){
		double result = 0;
		switch(this.propagationFunction){
		//The sum function calculates the sum of all inputs multiplied by their respective weights
		case "sum":
			for (int i = 0; i<input.length;i++){
				result = result + input[i]*weights[i];
			}
			break;
		//The prod function calculates the product of all inputs multiplied by their respective weights
		case "prod":
			for (int i = 0; i<input.length;i++){
				result = result * input[i]*weights[i];
			}
			break;
		//The max function calculates the maximum of all inputs multiplied by their respective weights
		case "max":
			result = input[0]*weights[0];
			for (int i = 1; i<input.length;i++){
				if(input[i]*weights[i] > result){
					result = input[i]*weights[i];
				}
			}
			break;
		//The min function calculates the minimum of all inputs multiplied by their respective weights
		case "min":
			result = input[0]*weights[0];
			for (int i = 1; i<input.length;i++){
				if(input[i]*weights[i] < result){
					result = input[i]*weights[i];
				}
			}
			break;
		}
		lastPropagation = result;
		return result;
	}
	
	public boolean isValidPropFunc(String propFunc){
		return java.util.Arrays.asList(this.possiblePropFunc).contains(propFunc);
	}
	
	public boolean isValidActFunc(String actFunc){
		return java.util.Arrays.asList(this.possibleActFunc).contains(actFunc);
	}
	
	public void infoPropagationFunction(){
		
	}
	
	// +++++++++++++++++++++++++++ACTIVITY+++++++++++++++++++++++++++++++++++
	private void updateActivity(double currentPropagation){
		//different activation functions can be implemented. We choose one for all Neurons.
		//No changes can be made by user. 
		switch(this.activationFunction){
		case "lin":
			//linear/identity
			this.currentActivity = currentPropagation;
			break;
		case "sig":
			//sigmoid
			//activationParam[0] gibt die Steilheit der Kurve an
			this.currentActivity = 1/(1+Math.exp(-activationParam[0]*currentPropagation));
			break;
		}
	}
	
	
	// +++++++++++++++++++++++++++OUTPUT+++++++++++++++++++++++++++++++++++
	public double calcOutput(double[] input, double[] weights){
		//Calculate the input/propagation and pass on the result to update the neurons activity.
		this.updateActivity(this.propagation(input, weights));
		
		//the output function works with the current activity
		//we start by using the identity as output function
		lastOutput = this.currentActivity;
		return this.currentActivity;	
	}
	
	//++++++++++++++++++++++++++++GETTER+++++++++++++++++++++++++++++++++++
	public String getPropagationFunction(){
		return this.propagationFunction;
	}
	
	public String getActivationFunction(){
		return this.activationFunction;
	}
	
	public double[] getActivationParam(){
		return this.activationParam;
	}
	
	public double getCurrentActivity(){
		return this.currentActivity;
	}
	
	public double getLastOutput(){
		return this.lastOutput;
	}	
	
	public String[] getPossiblePropFunc(){
		return this.possiblePropFunc;
	}
	
	public String[] getPossibleActFunc(){
		return this.possibleActFunc;
	}
	
	public double getLastPropagation(){
		return this.lastPropagation;
	}
	//+++++++++++++++++++++++++++++SETTER+++++++++++++++++++++++++++++++++++++++
	public boolean setPropagationFunction(String propFunc){
		if(isValidPropFunc(propFunc)){
			this.propagationFunction = propFunc;
			return true;
		} else {
			System.out.println("The name " + propFunc +" is not a defined propagation function.");
			System.out.println("Possible Strings are " + Arrays.toString(possiblePropFunc));
			return false;
		}
	}
	
	public boolean setActivationFunction(String actFunc,double[] actParam){
		if(isValidActFunc(actFunc)){
			this.activationFunction = actFunc;
			if(activationFunction.equals("sig")){
				if(actParam.length>0){
				if(actParam[0]>0){
					this.activationParam = actParam;
				}else{
					System.out.println("parameter needs to be greater than 0!");
					return false;
				}
				}else{
					System.out.println("not enough parameters!");
					return false;
				}
			}
			return true;
		} else {
			System.out.println("The name " + actFunc +" is not a defined activation function.");
			System.out.println("Possible Strings are " + Arrays.toString(possibleActFunc));
			return false;
		}
	}
}
