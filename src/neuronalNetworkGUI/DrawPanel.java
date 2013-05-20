package neuronalNetworkGUI;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.*;

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
          if (gui.done){
                Point locGrid = gui.getGridPanelLocation();
                Point locFrom = gui.fromButton.getLocation();
                Point locTo = gui.toButton.getLocation();
                //g2D.drawString("hallo",70,70);
                //g2D.drawOval(locGrid.x-10,locGrid.y-10,100,100);
                
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2D.setStroke(new BasicStroke(3F));  // set stroke width of 10
                Arrow pfeil = new Arrow();
                pfeil.draw(g2D, 1, 1, 450, 450);
                pfeil.draw(g2D, 450, 450, 550, 550);
                pfeil.draw(g2D, 1, 50, 50, 50);
                pfeil.draw(g2D, 10, 34, 15, 15);
                g2D.drawLine(locGrid.x+locFrom.x + gui.fromButton.getWidth()/2,locGrid.y+locFrom.y+gui.fromButton.getHeight()/2,locGrid.x+locTo.x+ gui.toButton.getWidth()/2,locGrid.y+locTo.y+gui.toButton.getHeight()/2);
          }
    }
}
