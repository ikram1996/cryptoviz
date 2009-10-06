
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;


public class IPvisualization extends JPanel{

	String binaryLine = new String();
	BitSet IPbits;

	public IPvisualization(){

		this.setSize(898,200);
		this.setBackground(Color.white);
		//center in panel 5 and give some padding to the top
		this.setLocation(1, 1);

	}

	public void setBinary(String binLine){
		binaryLine = binLine;
	}

	public void setIP(BitSet bits){
		IPbits = bits;
	}



	  public void paintComponent(Graphics g) {
		
	    	super.paintComponent(g);
	    	Graphics2D g2d = (Graphics2D)g;

		g2d.drawString("Binary String: ", 10, 40);

		for(int i=0; i<64; i++){
			Ellipse2D.Double circle = new Ellipse2D.Double((i*12)+50, 50, 10, 10);
			if(binaryLine.charAt(i)=='0') g2d.setPaint(Color.blue);
			if(binaryLine.charAt(i)=='1') g2d.setPaint(Color.red);	
		    	g2d.fill(circle);
			
			//g2d.setPaint(Color.yellow);
			//g2d.drawString(binaryLine.substring(i, i+1), (i*21)+7, 25);
		}

		g2d.drawString("IP()", 10, 40);

		for(int i=0; i<64; i++){
			Ellipse2D.Double circle = new Ellipse2D.Double((i*12)+50, 150, 10, 10);
			if(IPbits.get(i)==false) g2d.setPaint(Color.blue);
			if(IPbits.get(i)==true) g2d.setPaint(Color.red);	
		    	g2d.fill(circle);
		}
	  }


}
