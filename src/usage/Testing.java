package usage;
import neuronalNetwork.*;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Neuron myFirstNeuron = new Neuron("min");
		double input[] = {5,20,-1};
		double weights[] = {0,1,1};
		System.out.println(myFirstNeuron.calcOutput(input, weights));
	}
}
