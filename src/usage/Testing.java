package usage;
import java.util.LinkedList;

import neuronalNetwork.*;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Neuron creation test with arbitrary inputs and weights.
		Neuron myFirstNeuron = new Neuron("min");
		double input[] = {5,20,-1};
		double weights[] = {0,1,1};
		System.out.println(myFirstNeuron.calcOutput(input, weights));
				
				
		Network myFirstNetwork = new Network(2);
		myFirstNetwork.addNeurons(0, 2, myFirstNeuron);
		
		//test if Neurons in Network can be changed correctly
		Neuron firstN;
		Neuron secondN;
		System.out.println("before");
		firstN = myFirstNetwork.getNeuron(0,0);
		secondN = myFirstNetwork.getNeuron(0,1);
		System.out.println(firstN.getPropagationFunction());
		System.out.println(secondN.getPropagationFunction());
		firstN.setPropagationFunction("prod");
		System.out.println("after");
		firstN = myFirstNetwork.getNeuron(0,0);
		secondN = myFirstNetwork.getNeuron(0,1);
		System.out.println(firstN.getPropagationFunction());
		System.out.println(secondN.getPropagationFunction());
		firstN.setPropagationFunction("prod");
		System.out.println("Layer 0: " + myFirstNetwork.howManyInLayer(0));
		
		myFirstNetwork.updateWeight(0, 0, 0, 1, 0.5);
		myFirstNetwork.printWeightMatrix();
	}
}
