package neuronalNetworkGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Koronis
 */
public class NeuronButton extends JButton implements ActionListener{
    private NeuronalNetworkGUI gui;
    public int layer;
    public int position;
    
      
    NeuronButton(String caption,int layer,int position,NeuronalNetworkGUI gui){
        super(caption);
        this.gui = gui;
        this.layer = layer;
        this.position = position;
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
       
    }    
}
