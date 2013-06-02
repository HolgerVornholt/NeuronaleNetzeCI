package neuronalNetwork;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class Learning {
	private Network myNetwork;
	private int inputNeurons;
	private int outputNeurons;
	//Arrays that contain the scenario number in the first bracket
	//and the respective values in the second.
	private double[][] inputValues;
	private double[][] outputValues;
	private String learnMethod;
	private static String[] possibleLearnMethods = {"Hebb","Delta","Backprop"};
	
	private double learningRate = 1;
	private int maxIterations = 100;
	private int lastScenario = -1;
	private int currentIteration = 0;
	private double currentError= 99999;
	
	public Learning(String trainingFilePath,Network network,String learnMethod){
		if(isValidLearnMethod(learnMethod)){
			this.learnMethod = learnMethod;
		}else{
			this.learnMethod = "Backprop";
		}
		
		myNetwork = network;
		inputNeurons = myNetwork.howManyInLayer(0);
		outputNeurons = myNetwork.howManyInLayer(myNetwork.getLayerCount()-1);

		try{		
	    StreamTokenizer st = new StreamTokenizer(new FileReader(trainingFilePath) );
	    st.eolIsSignificant( true );

	    int tval;
	    String word;
	    boolean scenarios =false;
	    boolean nextIsInput = true;
	    int scenarioCounter = 0;
	    int counter = 0;
	    
	    while ( ( tval=st.nextToken() ) != st.TT_EOF ){
	    switch (tval){
	    	case StreamTokenizer.TT_NUMBER:
	    		if (scenarios){
	    			scenarios = false;
	    			lastScenario = ((int) st.nval)-1;
	    			inputValues = new double[lastScenario+1][inputNeurons];
	    			outputValues = new double[lastScenario+1][outputNeurons];
	    		} else {
	    			if (nextIsInput){
	    				inputValues[scenarioCounter][counter] = st.nval;
	    				counter++;
	    			}else{
	    				outputValues[scenarioCounter][counter] = st.nval;
	    				counter++;
	    				if(counter == outputNeurons){
	    		    		scenarioCounter++;
	    				}
	    			}
	    		}
	    		break;
	    	case StreamTokenizer.TT_WORD:
	    		word = st.sval;
	    		if (word.equals("Scenarios")){
	    			// the next value will be the number of scenarios to be read
	    			scenarios = true;
	    		}
	    		break;
	    	case StreamTokenizer.TT_EOL:
	    		//next line will contain the next scenario starting with the input values
	    		nextIsInput = true;
	    		counter = 0;
	    		break;
	    	default:
	    		//Semicolon = change from input to output
	    		if (st.ttype == 59){
	    			nextIsInput = false;
	    			counter = 0;
	    		}
	    		break;
	    }
	    }//end while 
		}catch(IOException e){
			System.out.println("Pfad ungültig");
		}
	}

	public void nextIteration(){
		currentIteration++; 
		currentError = 0;
		double[] currentOutput;
		for(int scenario = 0; scenario <= lastScenario;scenario++){
			//set input
			for (int i = 0; i<inputNeurons;i++){
				myNetwork.setInput(0, i, inputValues[scenario][i]);
			}
			//calculate output
			currentOutput = myNetwork.calcResultVector();
			//calculate Error
			for(int j = 0; j <outputNeurons;j++){
				currentError = currentError + Math.abs(outputValues[scenario][j] - currentOutput[j]);
			}
			//learn
			switch(learnMethod){
			case "Hebb":
				//TODO Hebb
				break;
			case "Delta":
				//TODO Delta
				break;
			case "Backprop":
				int[] relPos = new int[2];
				double[] activationParam;
				Neuron currentNeuron;
				double[] delta = new double[myNetwork.getAddedNeurons()]; 
				double g = 0;
				double[][] weightMatrix = myNetwork.getWeightMatrix();
				//calculate deltas
				for(int j = myNetwork.getAddedNeurons()-1; j>=0;j--){
					relPos = myNetwork.calcRelativePosition(j);
					currentNeuron = myNetwork.getNeuron(relPos[0], relPos[1]);
					//for backpropagation we need continous differentiable functions. In our case we only implemented sigmoid functions.
					if(currentNeuron.getActivationFunction().equals("sig")){
						activationParam = currentNeuron.getActivationParam();
						g = activationParam[0];
					} else{
						System.out.println("no sigmoid function -> exit");
						currentError = 0;
						currentIteration = maxIterations;
					}
					//output neuron or hidden neuron?
					if(relPos[0] == myNetwork.getLayerCount()-1){
						//outputNeuron
						//delta[j] = derivative of propagationfunction (net[j])*(out[j]-o_j);
						delta[j] = (myNetwork.getNeuron(relPos[0],relPos[1]).getLastPropagation());
						delta[j] = g*Math.exp(-g*delta[j])/(Math.pow(1+Math.exp(-g*delta[j]), 2));
						delta[j] = delta[j]*(outputValues[scenario][relPos[1]]-currentNeuron.getLastOutput());
					} else {
						//hidden or inputNeuron
						//derivative of propagationfunction (net[j])*sum_k>j (delta[k]*w_jk);
						delta[j] = (myNetwork.getNeuron(relPos[0],relPos[1]).getLastPropagation());
						delta[j] = g*Math.exp(-g*delta[j])/(Math.pow(1+Math.exp(-g*delta[j]), 2));
						double sum = 0;
						for (int k = j+1; k < myNetwork.getAddedNeurons();k++){
							sum = sum + delta[k]*weightMatrix[j][k];
						}						
						delta[j] = delta[j]*sum;
					}
				} //calculated deltas
				//set new weights w_ij = w_ij + learningrate*delta[j]*o_i
				for(int i = 0;i<myNetwork.getAddedNeurons(); i++){
				for(int j = i+1;j<myNetwork.getAddedNeurons();j++){
					relPos = myNetwork.calcRelativePosition(i);
					//only feed-forward allowed so edges in a layer will be ignored
					if ((relPos[0] !=myNetwork.calcRelativePosition(j)[0])&&(weightMatrix[i][j]!=0)){
						currentNeuron = myNetwork.getNeuron(relPos[0], relPos[1]);
						myNetwork.updateWeight(i, j, weightMatrix[i][j] + this.learningRate*delta[j]*currentNeuron.getLastOutput());
					}
				}
				}
				break;
			}
		}
	}
	
	public void run(int maxIterations,double errorThreshold){
		//for info purposes
		this.maxIterations = maxIterations;
		while(currentError > errorThreshold && currentIteration < maxIterations){
			nextIteration();
		}
	}
	
	public void setLearningRate(double learningRate){
		this.learningRate = learningRate;
	}
	
	public void setMaxIterations(int maxIterations){
		this.maxIterations = maxIterations;
	}
	
	public static String[] getPossibleLearnMethods(){
		return possibleLearnMethods;
	}
	
	public boolean isValidLearnMethod(String learnMethod){
		return java.util.Arrays.asList(possibleLearnMethods).contains(learnMethod);
	}
	
	public String[] getInfo(){
		String[] info = new String[2];
		info[0]= "Current Iteration: " + currentIteration + "/"+maxIterations;
		info[1]= "Sum of all Errors: " + currentError;
		return info;
	}

	public void printScenarios(){
		System.out.println("");
		System.out.println("Scenarios: " + inputValues.length);
		for(int i=0;i<inputValues.length;i++){
		System.out.println("");
		System.out.println("Scenario " + i);
		System.out.print("Input: ");
		for(int j=0;j<inputNeurons;j++){
			System.out.print(inputValues[i][j]);
		}
		System.out.println("");
		System.out.println("Output: ");
		for(int j=0;j<outputNeurons;j++){
			System.out.print(outputValues[i][j]);
		}
		}
	}
	
}
