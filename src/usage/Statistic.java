package usage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import neuronalNetwork.Learning;
import neuronalNetwork.Network;

public class Statistic
{
	static final String directoryPath = "E:\\Files\\";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Test Start!");
		runTest();
		System.out.println("Test Complete!");
	}

	static void runTest()
	{
		final String PATH_OF_NN[] = { 
				directoryPath + "xor_1.nn",
				directoryPath + "xor_2.nn", 
				directoryPath + "xor_3.nn",
				directoryPath + "xor_3_geht.nn"
				};
		final String PATH_OF_TRAINING_FILE = directoryPath + "ScenarioXOR.csv";
		final String PATH_OF_CSV = directoryPath + "results.csv";

		for (String path : PATH_OF_NN)
		{
			neuronalNetworkSerializable.Serializable.deserialize(path);
			Network n = neuronalNetworkSerializable.Serializable.getNetwork();

			double threshold = 0.1;
			int maxIterations = 250000;

			for (int count = 0; count < 100; count++)
			{
				n = randomizeWeights(n);

				for (double learningRate = 0.1; learningRate <= 1.0; learningRate += 0.1)
				{
					String resultWeights = "";
					double[][] weightMatrix = n.getWeightMatrix();
					double[][] tempweightMatrix = new double[weightMatrix.length][weightMatrix[0].length];
					copyArray(weightMatrix, tempweightMatrix);
					for (int i = 0; i < weightMatrix.length; i++)
					{
						for (int j = 0; j < weightMatrix[i].length; j++)
						{
							resultWeights = resultWeights + String.valueOf(weightMatrix[i][j]);
							if (j < weightMatrix[i].length - 1)
								resultWeights = resultWeights + ", ";
						}
						if (i < weightMatrix.length - 1)
							resultWeights = resultWeights + " | ";
					}
					// n.printWeightMatrix();
					Learning learning = new Learning(PATH_OF_TRAINING_FILE, n,
							"backprop");
					learning.setLearningRate(learningRate);
					learning.run(maxIterations, threshold);
					String[] info = learning.getInfo();
					// n.printWeightMatrix();
					double sumOfAllErrors = Double.parseDouble(info[1]
							.substring(info[1].indexOf(':') + 2));
					boolean hasLearned = sumOfAllErrors <= threshold ? true
							: false;
					String result = String.format(
							"%s;%s;%s;%f;%d;%f;%s;%f;%b;%s;%n", path,
							PATH_OF_TRAINING_FILE, resultWeights, learningRate,
							maxIterations, threshold,
							info[0].substring(info[0].indexOf(':') + 2),
							sumOfAllErrors, hasLearned, info[2]);

					writeCSV(PATH_OF_CSV, result);

					System.out.println("Round: " + String.valueOf(count + 1)
							+ " - Learning Rate = "
							+ String.valueOf(learningRate) + " - hasLearned = "
							+ hasLearned + " - finished!");
					// copyArray(tempweightMatrix, weightMatrix);
					n.setWeightMatrix(tempweightMatrix);
				}
			}
		}
	}

	static Network randomizeWeights(Network n)
	{
		Random rand = new Random();
		for (int i = 0; i < n.getAddedNeurons(); i++)
		{
			for (int j = i + 1; j < n.getAddedNeurons(); j++)
			{
				if (n.getWeightMatrix()[i][j] != 0)
				{
					int randNumber = rand.nextInt(11) - 5;
					while (randNumber == 0)
						randNumber = rand.nextInt(11) - 5;
					n.updateWeight(i, j, randNumber);
				}
			}
		}
		return n;
	}

	static double[][] copyArray(double[][] target, double[][] destination)
	{

		for (int i = 0; i < target.length; i++)
		{
			for (int j = 0; j < target[i].length; j++)
			{
				destination[i][j] = target[i][j];
			}
		}
		return target;
	}

	static void writeCSV(String sFileName, String text)
	{
		try
		{
			boolean writeHeader = false;
			File csv = new File(sFileName);
			if (!csv.exists())
				writeHeader = true;

			FileWriter writer = new FileWriter(csv, true);
			if (writeHeader)
				writer.append("NN_Path;Learn_Path;WeigthMatrix;LearningRate;MaxIterations;Threshold;CurrentIteration;SumOfAllErrors;Learned;Error;\n");

			writer.append(text);
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
