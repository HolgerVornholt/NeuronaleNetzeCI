package neuronalNetwork;

import java.util.LinkedList;
/*
 * This Class manages the edges, weights and Neuron layers
 * of the neuronal network.
 * 
 * There are two different representations for a Neuron in the network graph:
 * coordinates i.e. (layer,position)
 * absolute position i.e. the 6th Neuron of 22.
 */
public class Network {
	// How many layers will the Network have. To be specified in Constructor.
	private int layerCount;
	// 2 dimensional matrix using absolute positions for edge weights. To be initialized in Constructor
	private double[][] weightMatrix;
	// Neurons in the Network will be saved in layers and saved in multiple LinkedLists.
	private LinkedList<Neuron>[] neuronLayer;
	
	public Network(int layerCount){
		
	}
	
	public int howManyInLayer(int layer){

	}
	
	public void addNeuron(int targetLayer, Neuron neuron){
		
	}
	
	public void addNeurons(int targetLayer, int amount, Neuron neuron){
		
	}
	
	public void addNeurons(int targetLayer, int amount, String propFunc){
		//check for correct propFunc
	}
	
	/*
	* The Neurons in the Network are organized in layers
	* To Update the weight of an edge between to Neurons
	* you need to know the "coordinates" of the Neurons.
	* The coordinate of a Neuron is the layer and its position in that layer
	* I.e. if you have 6 Neurons in layer 2 you can access the neuron (2,3) which is the third
	* in layer 2.
	* 
	* In the weightMatrix we don't have these layers. Every Neuron has an absolute number
	* which can be calculated by calculating the sum of the Neurons on layers below
	* and the remaining Neurons that come before the Neuron of interest on the same level.
	* 
	* If you have 2 layers of Neurons, 6 on the first layer and 4 on the second, you can
	* access the Neuron (2,3) by calculating 6+2 = 8.
	*
	*/
	public void updateWeight(int layerFrom, int posInLayerFrom, int layerTo, int posInLayerTo){
		
	}
}
