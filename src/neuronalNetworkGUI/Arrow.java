//-------------------------------------------------------------------------
// Die Klasse Arrow dient zur Definition von Pfeilen in der Bildschirmebene.
// Der Grafikkontext Graphics g wird ben�tigt.
// Zur Klasse geh�ren folgende Methoden:
//   Arrow()             Konstruktor: Initialisierung mit Default-Werten
//   setShape(a,len)     �ffnungswinkel a und L�nge len der Pfeilspitze
//   setColor(r,g,b)     Farbe auf die RGB-Werte r, g, b setzen
//   draw(g,x0,y0,x1,y1) Pfeil vom Anfangspunkt 0 zum Endpunkt 1 zeichnen

package neuronalNetworkGUI;
import java.awt.*;
import java.lang.Math;

public class Arrow {
  private double ang, length;       // Winkel und L�nge der Spitze
  private Color color;              // Farbe 

  // Konstruktor: Pfeil-Parameter mit Default-Werten initialisieren
  Arrow() {
    length=10;                      // L�nge der Spitze 10 Pixel
    ang=20*Math.PI/180;             // �ffnungswinkel der Spitze 20�
    color = new Color(0,0,0);       // Farbe schwarz
  }
  
  // Form der Spitze festlegen 
  public void setShape(double a, double len) {
    ang=a;                          // �ffnungswinkel der Spitze
    if(ang<5) ang=5;                // minimaler �ffnungswinkel ist 5�
    ang*=Math.PI/180;               // Winkel in Bogenma� umrechnen
    length=len;                     // L�nge der Spitze
    if(len<5) len=5;                // Minimale L�nge ist 5 Pixel
  }

  // Zeichenfarbe setzen
  public void setColor(int r, int g, int b) { color=new Color(r,g,b); }

  // Zeichenfarbe setzen
  public void setColor(Color col) { color=col; }

  
  // Pfeil mit vorbesetzten Werten von Punkt 0 zu Punkt 1 zeichnen
  public void draw(Graphics2D g, double x0, double y0, double x1, double y1) 
  {
    int ix2,iy2,ix3,iy3;                  //           3 
    double sinPhi,cosPhi,dx,dy,x,y,s;     //            \
    dx=x1-x0;                             //   0---------1
    dy=y1-y0;                             //            /
    s=Math.sqrt(dy*dy+dx*dx);             //           2
    sinPhi=dy/s;                          // Winkel des Pfeils 
    cosPhi=dx/s;                          // mit der X-Achse
    if(s<length) {                        // Der Pfeil 
      x0=x1-length*cosPhi;                // .. darf nicht k�rzer 
      y0=y1-length*sinPhi;                // .. als die Spitze sein
    }
    x=-length*Math.cos(ang);              // Koordinaten
    y=length*Math.sin(ang);               // .. der Pfeilspitze
    ix2=(int)(x1 + x*cosPhi - y*sinPhi);  // Pfeilspitze 
    iy2=(int)(y1 + x*sinPhi + y*cosPhi);  // .. um Winkel Phi 
    ix3=(int)(x1 + x*cosPhi + y*sinPhi);  // .. rotieren 
    iy3=(int)(y1 + x*sinPhi - y*cosPhi);  // .. und translatieren
    Color c=g.getColor();                 // Aktuelle Farbe holen
    g.setColor(color);                    // Pfeilfarbe setzen
    g.drawLine((int)x0,(int)y0,(int)x1,(int)y1); // Pfeilachse 
    g.drawLine(ix2,iy2,(int)x1,(int)y1);  // Pfeilspitze
    g.drawLine(ix3,iy3,(int)x1,(int)y1);  // .. zeichnen
    g.setColor(c);                        // Farbe zur�cksetzen
  }

}
