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
/**
 * @author Holger Vornholt, Tobias Eidmann, Michael Martin
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

	@SuppressWarnings("unchecked")
	public Network(int layerCount){
		if (layerCount <=0){
			System.out.println(layerCount + " is not a valid layerCount and has to be positive. layerCount is set to 1.");
			layerCount = 1;
		}
		this.addedNeurons = 0;
		this.layerCount = layerCount;
		this.neuronLayer = (LinkedList<Neuron>[]) new LinkedList[layerCount];
		for(int i=0; i < neuronLayer.length; i++){
		    neuronLayer[i] = new LinkedList<Neuron>();
		}
		weightMatrix = new double[0][0];
	}
	
	public void addNeuron(int targetLayer, Neuron neuron){
		this.addNeurons(targetLayer,1, neuron);
	}

	public void addNeurons(int targetLayer, int amount, Neuron neuron){
		
		//there have to be new columns and rows starting at the absolute position of the
		//first new Neuron. This will be the absolutePosition of (targetLayer,howManyInThatLayer) because of zero-based-numbering.
		int insertAt = this.calcAbsolutePosition(targetLayer, this.howManyInLayer(targetLayer));
		this.weightMatrix = this.insertRowCol(this.weightMatrix, insertAt, amount);
		this.addedNeurons = this.addedNeurons + amount;
		
		//clone the neuron "amount" times and add it to the Network.
		for (int i=0;i<amount;i++){
			neuronLayer[targetLayer].add(new Neuron(neuron));
		}
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
	
	public void setInput(int targetLayer, int position,double value){
		double[] input = new double[1];
		double[] weights = new double[1];
		input[0] = value;
		weights[0] = 1;
		neuronLayer[targetLayer].get(position).calcOutput(input, weights);
	}
	
	//recursive Method to calculate one specific output value.
	private double calcOutput(int targetLayer,int position){
		int[] relPos;
		int absPos = this.calcAbsolutePosition(targetLayer, position);
		double[] input = new double[addedNeurons];
		double[] weights = new double[addedNeurons];
		boolean inputNeuron=true;

		for(int row = 0; row < addedNeurons;row++){
			if(weightMatrix[row][absPos] != 0){
				inputNeuron = false;
				relPos = calcRelativePosition(row);
				weights[row] = weightMatrix[row][absPos];				
				input[row] = calcOutput(relPos[0],relPos[1]);
			}else{
				weights[row] = 0;
				input[row] = 0;
			}
		}		

		if (inputNeuron){
			//Input Neurons don't have any edges connect to them and have an output preseted
			//that output is saved in lastOutput.
			return neuronLayer[targetLayer].get(position).getLastOutput();			
		}else{
			//All the other Neurons calculate their output with the calcOutput function.
			return neuronLayer[targetLayer].get(position).calcOutput(input, weights);
		}		
	}
	
	//We only calculate the result for the last layer. Custom layer outputs are not supported
	public double[] calcResultVector(){
		double[] result = new double[howManyInLayer(layerCount-1)];
		for (int position = 0; position < howManyInLayer(layerCount-1); position++){
			result[position] = calcOutput(layerCount-1,position);
		}
		return result;
	}
	
	/* The Neurons in the Network are organized in layers
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
	
	public void updateWeight(int from, int to,double weight){
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
	
	public double[][] insertRowCol(double[][] weightMatrix, int insertAt, int howMany){
		int oldDim = weightMatrix.length;
		int newDim = oldDim + howMany;
		//the new weightMatrix will have a new row and column for each added Neuron
		double[][] tempMatrix = new double[newDim][newDim];
		
		//1) copy the rows of the old matrix to the new one. 
		// a row that looks like this 1,2,3 will look like 1,2, 0, ... , 0,3 
		if(insertAt > 0){
			for(int row = 0; row<insertAt;row++){
				//copy left part of the rows i.e. 1,2
				java.lang.System.arraycopy(weightMatrix[row], 0, tempMatrix[row], 0, insertAt);
				//set new columnvalues to 0 i.e. 0,...,0
				for(int insertCol = insertAt;insertCol < insertAt+howMany;insertCol++){
					tempMatrix[row][insertCol] = 0;
				}
				//if we reached the last column of the matrix we are done with this part
				//if not we need to copy the right part of the original row. i.e. 3
				if(insertAt+howMany-1 != (tempMatrix.length-1)){
					java.lang.System.arraycopy(weightMatrix[row],insertAt,tempMatrix[row],(insertAt+howMany),(weightMatrix.length-insertAt));
				}
			}
		}
		//2) add zero rows
		for(int row = insertAt;row<insertAt+howMany;row++){
			for(int col = 0; col<tempMatrix.length;col++){
			tempMatrix[row][col] = 0;	
			}
		}
		//3) copy old rows after the inserted ones as in 1)
		if(insertAt < weightMatrix.length){
			for(int row = insertAt+howMany; row<tempMatrix.length;row++){
				//copy left part of the rows i.e. 1,2
				java.lang.System.arraycopy(weightMatrix[row-howMany], 0, tempMatrix[row], 0, insertAt);
				//set new columnvalues to 0 i.e. 0,...,0
				for(int insertCol = insertAt;insertCol < insertAt+howMany;insertCol++){
					tempMatrix[row][insertCol] = 0;
				}
				//if we reached the last column of the matrix we are done with this part
				//if not we need to copy the right part of the original row. i.e. 3
				if(insertAt+howMany-1 != (tempMatrix.length-1)){
					java.lang.System.arraycopy(weightMatrix[row-howMany],insertAt,tempMatrix[row],(insertAt+howMany),(weightMatrix.length-insertAt));
				}
			}
		}
		return tempMatrix;
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
	
	public Neuron getNeuron(int absPos){
		int[] relPos = calcRelativePosition(absPos);
		return neuronLayer[relPos[0]].get(relPos[1]);
	}
	
	public int howManyInLayer(int layer){
		return this.neuronLayer[layer].size();
	}
	
	public double[][] getWeightMatrix(){
		return weightMatrix;
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
