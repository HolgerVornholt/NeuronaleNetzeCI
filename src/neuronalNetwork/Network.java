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
	private int addedNeurons;
	// 2 dimensional matrix using absolute positions for edge weights. To be initialized in Constructor
	private double[][] weightMatrix;
	// Neurons in the Network will be saved in layers and saved in multiple LinkedLists.
	// Is that a good idea?
	private LinkedList<Neuron>[] neuronLayer;

	public Network(int layerCount){
		this.addedNeurons = 0;
		this.layerCount = layerCount;
		this.neuronLayer = (LinkedList<Neuron>[]) new LinkedList[layerCount];
		for(int i=0; i < neuronLayer.length; i++){
		    neuronLayer[i] = new LinkedList<Neuron>();
		}
		weightMatrix = new double[10][10];
	}
	
	public void addNeuron(int targetLayer, Neuron neuron){
		this.addNeurons(targetLayer,1, neuron);
	}
	
	public void addNeurons(int targetLayer, int amount, Neuron neuron){
		//update size of weightMatrix
		int noSpaceFor;
		int newDim;
		int oldDim = weightMatrix.length;
		// If the weightMatrix isn't big enough we need to add rows and columns to it.
		if (this.addedNeurons+amount > oldDim){
			noSpaceFor = (this.addedNeurons+amount-oldDim);
		    //always add 10 columns minimum.
			if (noSpaceFor <10){
				newDim = oldDim+10;
			} else {
				newDim = oldDim+noSpaceFor;
			}
			//resize
			double[][] weightMatrixTemp = new double[newDim][newDim];
			//copy old values
			for (int i = 0; i < oldDim;i++){
				java.lang.System.arraycopy(weightMatrix[i], 0, weightMatrixTemp[i], 0, oldDim);
			}
			this.weightMatrix = weightMatrixTemp;
		}
		this.addedNeurons = this.addedNeurons + amount;
		
		//clone the neuron "amount" times and add it to the Network.
		for (int i=0;i<amount;i++){
			neuronLayer[targetLayer].add(new Neuron(neuron));
		}
	}
	
	public void addNeurons(int targetLayer, int amount, String propFunc){
		this.addNeurons(targetLayer,amount,new Neuron(propFunc));
	}
	
	public void removeNeuron(int targetLayer){
		//remove the last added Neuron of a Layer and update the weightMatrix.
		if(this.howManyInLayer(targetLayer)>0){
			int absolutePos = calcAbsolutePosition(targetLayer,howManyInLayer(targetLayer)-1);

			//remove the respective row and column to fix the absolute positions of the Neurons coming in later layers.
			weightMatrix = removeRowColumn(weightMatrix,absolutePos,absolutePos);			
			neuronLayer[targetLayer].removeLast();
			this.addedNeurons = this.addedNeurons -1;
		}
	}
		
	/*
	* The Neurons in the Network are organized in layers
	* To Update the weight of an edge between to Neurons
	* you need to know the "coordinates" of the Neurons.
	* The coordinate of a Neuron is the layer and its position in that layer
	* I.e. if you have 6 Neurons in layer 2 you can access the neuron (1,3) which is the fourth
	* in layer 2. (zero-based-numbering)
	* 
	* In the weightMatrix we don't have these layers. Every Neuron has an absolute number
	* which can be calculated by calculating the sum of the Neurons on layers below
	* and the remaining Neurons that come before the Neuron of interest on the same level.
	* 
	* If you have 2 layers of Neurons, 6 on the first layer and 4 on the second, you can
	* access the last Neuron (1,3) by calculating 6+3 = 9. (absolute Neuron positions go from 0 to 9.) 
	*
	*/
	public void updateWeight(int layerFrom, int posInLayerFrom, int layerTo, int posInLayerTo,double weight){
		//change relative coordinates (layer,position) to absolute positions.
		int from = calcAbsolutePosition(layerFrom,posInLayerFrom);
		int to = calcAbsolutePosition(layerTo,posInLayerTo);
				
		weightMatrix[from][to] = weight;
	}
	
	//+++++++++++++++++++++++++++++Utilities++++++++++++++++++++++++++++
	
	public int[] calcRelativePosition(int absolutePosition){
		int layer = 0;
		int position = 0;
		//get the absolutePos of the last Neuron in the first layer
		int absPosOfLastInLayer = howManyInLayer(0)-1; //zero-based-numbering
		//if the neuron is in the first layer,
		//absolutePosition is <= absPosOfLastInLayer
		while(absolutePosition>absPosOfLastInLayer){
			//if not, we need to calculate the absPosOfLastInLayer for the next layer.
			layer = layer + 1;
			absPosOfLastInLayer = absPosOfLastInLayer + howManyInLayer(layer);
		}
		if(layer !=0){
			//When we arrive here, layer contains the correct layer
			//for the position we first calculate the absPosOfLastInLayer for the previous layer
			absPosOfLastInLayer = absPosOfLastInLayer - howManyInLayer(layer);
			//Then we substract absPos-absPosOfLastInLayer to get on which position the 
			//Searched Neuron is. Because the relative position is zero-based we need to substract one more.
			position = absolutePosition-absPosOfLastInLayer-1;	
		} else {
			position = absolutePosition;
		}
		int[] relativePos = {layer,position};
		return relativePos;
	}
	
	public int calcAbsolutePosition(int layer,int position){
		int absolutePos = 0;
		
		for (int i = 0; i < layer; i++ ){
			absolutePos = absolutePos + this.howManyInLayer(i);
		}
		absolutePos = absolutePos + position;
		
		return absolutePos;
	}
	
	
	//+++++++++++++++++++++++++++static methods++++++++++++++++++++++++++++++++++++
	public static double[][] removeRowColumn(double[][] origMatrix, int row, int col){
		double[][] destMatrix = new double[origMatrix.length-1][origMatrix.length-1];

        int p = 0;
        for( int i = 0; i < origMatrix.length; ++i)
        {
            if ( i == row)
                continue;


            int q = 0;
            for( int j = 0; j < origMatrix.length; ++j)
            {
                if ( j == col)
                    continue;

                destMatrix[p][q] = origMatrix[i][j];
                ++q;
            }

            ++p;
        }
        return destMatrix;
	}
		
	//+++++++++++++++++++++++++++GETTER AND INFO+++++++++++++++++++++++++++++++++
	public int getLayerCount(){
		return this.layerCount;
	}
	
	public int maxLayerSize(){
		int max = 0;
		for (int i = 0;i < this.getLayerCount(); i++){
			if (max < this.howManyInLayer(i)){
				max = this.howManyInLayer(i);
			}
		}
		return max;
	}
	
	public Neuron getNeuron(int layer,int position){
		return neuronLayer[layer].get(position);
	}
	
	public int howManyInLayer(int layer){
		return this.neuronLayer[layer].size();
	}
	
	public void printWeightMatrix(){
		String zeile = "";
		for (int i = 0; i < this.weightMatrix.length;i++){
			for (int j = 0; j< this.weightMatrix.length;j++){
				zeile = zeile + " " + weightMatrix[i][j];
			}
			System.out.println(zeile);
			zeile = "";
		}
	}
	
	public int getAddedNeurons(){
		return this.addedNeurons;
	}
}
