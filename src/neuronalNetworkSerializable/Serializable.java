package neuronalNetworkSerializable;

import java.io.*;
import neuronalNetwork.Network;

public class Serializable
{
	static Network network;
	static NeuronalNetworkGUIProperties nnGUIProp;
	
	public static void serialize(String filename, Network n, NeuronalNetworkGUIProperties nnGUIProp)
	{
		try
		{
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream o = new ObjectOutputStream(file);
			o.writeObject(n);
			o.writeObject(nnGUIProp);
			o.close();
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}
	
	public static void deserialize(String filename)
	{
		try
		{
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream o = new ObjectInputStream(file);
			Network network = (Network) o.readObject();
			NeuronalNetworkGUIProperties nnGUIProp = (NeuronalNetworkGUIProperties) o.readObject();
			o.close();
			Serializable.network = network;
			Serializable.nnGUIProp = nnGUIProp;
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
		catch (ClassNotFoundException e)
		{
			System.err.println(e);
		}
	}

	/**
	 * @return the network
	 */
	public static Network getNetwork()
	{
		return network;
	}

	/**
	 * @return the NeuornalNetworkGUIProperties
	 */
	public static NeuronalNetworkGUIProperties getNeuornalNetworkGUIProperties()
	{
		return nnGUIProp;
	}
}
