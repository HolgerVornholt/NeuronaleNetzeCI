package neuronalNetwork;

import java.util.Arrays;

/* This class implements the functionality that each
 * Neuron needs internally. A single Neuron does not
 * know who it is connected to. It gets information on
 * its input by the Class Network. 
 */

public class Neuron {
	// A Neuron always remembers its previous Activity and needs
	// to calculate a new Activity when it needs to calculate an output value.
	private double currentActivity;
	// There are different ways to propagate weighted input. The different functions
	// are defined in the propagation Method. The String in propagationFunction
	// defines which function will be used.
	private String propagationFunction;
	private String[] possiblePropFunc = {"sum","prod","max","min"};
	
	public Neuron(){
		currentActivity = 0;
		this.propagationFunction = "sum";
	}
	
	public Neuron(String propFunc){
		currentActivity = 0;
		this.setPropagationFunction(propFunc);
	}
	
	public Neuron(Neuron neuron){
		currentActivity = neuron.getCurrentActivity();
		propagationFunction = neuron.getPropagationFunction();
	}
	
	// +++++++++++++++++++++++++++PROPAGATION+++++++++++++++++++++++++++++++++++
	private double propagation(double[] input, double[] weights){
		double result = 0;
		switch(this.propagationFunction){
		//The sum function calculates the sum of all inputs multiplied by their respective weights
		case "sum":
			System.out.println("sum");
			for (int i = 0; i<input.length;i++){
				result = result + input[i]*weights[i];
			}
			break;
		//The prod function calculates the product of all inputs multiplied by their respective weights
		case "prod":
			System.out.println("prod");
			for (int i = 0; i<input.length;i++){
				result = result * input[i]*weights[i];
			}
			break;
		//The max function calculates the maximum of all inputs multiplied by their respective weights
		case "max":
			System.out.println("max");
			result = input[0]*weights[0];
			for (int i = 1; i<input.length;i++){
				if(input[i]*weights[i] > result){
					result = input[i]*weights[i];
				}
			}
			break;
		//The min function calculates the minimum of all inputs multiplied by their respective weights
		case "min":
			System.out.println("min");
			result = input[0]*weights[0];
			for (int i = 1; i<input.length;i++){
				if(input[i]*weights[i] < result){
					result = input[i]*weights[i];
				}
			}
			break;
		}
		return result;
	}
	
	public boolean isValidPropFunc(String propFunc){
		return java.util.Arrays.asList(this.possiblePropFunc).contains(propFunc);
	}
	
	public void infoPropagationFunction(){
		
	}
	
	// +++++++++++++++++++++++++++ACTIVITY+++++++++++++++++++++++++++++++++++
	private void updateActivity(double currentPropagation){
		//different activation functions can be implemented. We choose one for all Neurons.
		//No changes can be made by user. 
	    int activationFunction = 1;
		switch(activationFunction){
		case 1:
			//linear/identity
			this.currentActivity = currentPropagation;
			break;
		case 2:
			//sigmoid
			//g gibt die Steilheit der Kurve an
			double g = 1;
			this.currentActivity = 1/(1+Math.exp(-g*currentPropagation));
			break;
		}
	}
	
	
	// +++++++++++++++++++++++++++OUTPUT+++++++++++++++++++++++++++++++++++
	public double calcOutput(double[] input, double[] weights){
		//Calculate the input/propagation and pass on the result to update the neurons activity.
		this.updateActivity(this.propagation(input, weights));
		
		//the output function works with the current activity
		//we start by using the identity as output function
		return this.currentActivity;	
	}
	
	//++++++++++++++++++++++++++++GETTER+++++++++++++++++++++++++++++++++++
	public String getPropagationFunction(){
		return this.propagationFunction;
	}
	
	public double getCurrentActivity(){
		return this.currentActivity;
	}
	//+++++++++++++++++++++++++++++SETTER+++++++++++++++++++++++++++++++++++++++
	public void setPropagationFunction(String propFunc){
		if(isValidPropFunc(propFunc)){
			this.propagationFunction = propFunc;
		} else {
			System.out.println("The name " + propFunc +" is not a defined propagation function.");
			System.out.println("Possible Strings are " + Arrays.toString(possiblePropFunc));
		}
	}

	
}
