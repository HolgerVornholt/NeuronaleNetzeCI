package neuronalNetworkGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
        if(gui.selectOrigin){
            gui.fromButton = this;
            gui.selectOrigin = false;
            gui.repaint();
        } else {
            gui.toButton = this;
            gui.selectOrigin = true;
            gui.done = true;
            gui.repaint();
        }
        System.out.println(gui.done);
        System.out.println(this.getWidth());
        System.out.println(this.getHeight());
    }    
}
