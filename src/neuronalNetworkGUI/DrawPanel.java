package neuronalNetworkGUI;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.*;

import neuronalNetwork.Network;

/**
 *
 * @author Koronis
 */
public class DrawPanel extends JPanel {
    private NeuronalNetworkGUI gui;
    
    DrawPanel(NeuronalNetworkGUI gui){
        super();
        this.gui= gui;
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
          Graphics2D g2D = (Graphics2D)g;
          if (gui.networkInitiated){
        	  	double[][] weightMatrix = gui.getNetwork().getWeightMatrix();
        	  	int gridRowsBefore;
        	  	int position;
        	  	int posInGrid;
        	  	GridLayout layout = (GridLayout) gui.getGridPanel().getLayout();
        	  	int gridCols = layout.getColumns();
        	  	Network network = gui.getNetwork();
        	  	JPanel gridPanel = gui.getGridPanel();
        	  	
        	  	Point locGrid = gui.getGridPanelLocation();
                Point locSource;
                Point locTarget;
                Point center = new Point();
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2D.setStroke(new BasicStroke(3F));  // set stroke width of 10
                Arrow pfeil = new Arrow();
        	  	
                //weightMatrix.length can be bigger than the addedNeurons so its important to use network.getAddedNeurons()
                for(int row = 0;row<network.getAddedNeurons();row++){
        	  	for(int col = 0;col<network.getAddedNeurons();col++){
        	  		if (weightMatrix[row][col] !=0){
        	  			//getPosition of Buttons to draw Arrows.
            	  		gridRowsBefore = network.calcRelativePosition(row)[0];
            	  		position = network.calcRelativePosition(row)[1];
            	  		posInGrid = gridRowsBefore*gridCols + position;
            	  		NeuronButton source = (NeuronButton) gridPanel.getComponent(posInGrid);
            	  		locSource = source.getLocation();
            	  		locSource.x = locSource.x + locGrid.x +source.getWidth()/2;
            	  		locSource.y = locSource.y + locGrid.y + source.getHeight()/2;
            	  		System.out.println("Source: " + locSource.x + " und "+locSource.y);
            	  		
            	  		gridRowsBefore = network.calcRelativePosition(col)[0];
            	  		position = network.calcRelativePosition(col)[1];
            	  		posInGrid = gridRowsBefore*gridCols + position;
            	  		NeuronButton target = (NeuronButton) gridPanel.getComponent(posInGrid);
            	  		locTarget = target.getLocation();
            	  		locTarget.x = locTarget.x + locGrid.x +target.getWidth()/2;
            	  		locTarget.y = locTarget.y + locGrid.y +target.getHeight()/2;
            	  		System.out.println("Target: " + locTarget.x + " und "+locTarget.y);
            	  		
            	  		center.x = (locTarget.x+locSource.x)/2;
            	  		center.y = (locTarget.y+locSource.y)/2;
            	  		
            	  		//TODO Gewicht dranschreiben
            	  		pfeil.draw(g2D, locSource.x, locSource.y, center.x, center.y);
            	  		pfeil.draw(g2D, center.x, center.y, locTarget.x, locTarget.y);
        	  		}
        	  		
        	  		
        	  		
        	  		//weightMatrix[row][col];
        	  	}
        	  	}
        	  	
                this.repaint();
                
                System.out.println("current weightMatrix");
                network.printWeightMatrix();
          }
    }
}
