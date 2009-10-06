
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;


public class IPvisualization extends JPanel{

	String binaryLine = new String();

	public IPvisualization(){

		this.setSize(900,200);
		this.setBackground(Color.white);
		//center in panel 5 and give some padding to the top
		this.setLocation(50, 1);

	}

	public void setBinary(String binLine){
		binaryLine = binLine;
	}



	  public void paintComponent(Graphics g) {
		
	    	super.paintComponent(g);
	    	Graphics2D g2d = (Graphics2D)g;
		for(int i=0; i<64; i++){
			Ellipse2D.Double circle = new Ellipse2D.Double(i*21, 10, 20, 20);
			g2d.setPaint(Color.black);	
		    	g2d.fill(circle);
			g2d.setPaint(Color.yellow);
			g2d.drawString(binaryLine.substring(i, i+1), (i*21)+7, 25);
		}
	  }


}
