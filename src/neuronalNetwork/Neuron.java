package neuronalNetwork;

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
	
	
	Neuron(String propFunc){
		//check validity of propFunc with isValidPropFunc
	}
	
	// +++++++++++++++++++++++++++PROPAGATION+++++++++++++++++++++++++++++++++++
	private double propagation(double[] input, double[] weights){

	}
	
	public boolean isValidPropFunc(String propFunc){
		
	}
	
	public void infoPropagationFunction(){
		
	}
	
	
	// +++++++++++++++++++++++++++ACTIVITY+++++++++++++++++++++++++++++++++++
	private void updateActivity(double currentPropagation){
		
	}
	
	
	// +++++++++++++++++++++++++++OUTPUT+++++++++++++++++++++++++++++++++++
	public double calcOutput(double[] input, double[] weights){
		
	}

	
}
