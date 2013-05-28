package neuronalNetworkGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author Holger Vornholt, Tobias Eidmann, Michael Martin
 */
public class NeuronButton extends JButton implements ActionListener{
	private static final long serialVersionUID = 1L;
	private NeuronalNetworkGUI gui;
    public int layer;
    public int position;
    int absPos;
    
      
    NeuronButton(String caption,int layer,int position,NeuronalNetworkGUI gui){
        super(caption);
        this.gui = gui;
        this.layer = layer;
        this.position = position;
        this.absPos = gui.getNetwork().calcAbsolutePosition(layer,position);
        //this.setActionCommand("button"+i);
        this.addActionListener(this);
        String path = "Images/Neuron" + gui.currentZoom +".png";
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
        	this.setIcon(new ImageIcon(imgURL, ""));
        } else {
            System.err.println("Couldn't find file: " + path);
        }
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(gui.editMode){
    		if(gui.selectOrigin){
                gui.fromButton = this;
                gui.selectOrigin = false;
            } else {
            	if (gui.fromButton.equals(this)){
            		JOptionPane.showMessageDialog(gui, "Please select a target thats different from the source Neuron.");
            	} else{
                try{                	
                	String result = (String)JOptionPane.showInputDialog(
                            gui,
                            "Please enter weight:",
                            "Weight",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            null);
                	if(result != null){
                		//throws NumberFormatException
                		double weight = Double.parseDouble(result);
                		gui.toButton = this;
                        gui.selectOrigin = true;
                        gui.editMode = false;
                    	gui.getNetwork().updateWeight(gui.fromButton.layer, gui.fromButton.position, gui.toButton.layer, gui.toButton.position, weight);
                    	gui.getAddEditEdgeButton().setEnabled(true);
                    	gui.getRemoveButton().setEnabled(true);
                	}
                }catch(NumberFormatException ex){
                	JOptionPane.showMessageDialog(gui, "Please enter a valid double value. Select a new target and try again.");
                }
            	}
            }
    	}else{
    		//We can also press the Button to set output values to input or BIAS-Neurons.
    		//We identify these by checking if they have any edge leading to them.
    		double[][] weightMatrix = gui.getNetwork().getWeightMatrix();
    		boolean inputNeuron = true;
    		for(int row=0;row<weightMatrix.length;row++){
    			if(weightMatrix[row][absPos] != 0){
    				inputNeuron = false;
    			}
    		}
    		if(inputNeuron){
    			try{                	
                	String result = (String)JOptionPane.showInputDialog(
                            gui,
                            "Please enter a value for the input Neuron:",
                            "Enter output",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            null);
                	if(result != null){
                		//throws NumberFormatException
                		double value = Double.parseDouble(result);
                    	gui.getNetwork().setInput(layer, position, value);
                    	gui.updateButtonTexts();
                	}
                }catch(NumberFormatException ex){
                	JOptionPane.showMessageDialog(gui, "Please enter a valid double value. Select a new target and try again.");
                }
    		}
    	}
    	
       
    }    
}
