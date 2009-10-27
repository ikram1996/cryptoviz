
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;


public class AnimationTest extends JInternalFrame{


	int openFrameCount = 1;
	String binaryLine = new String();
	BitSet IPbits;
	JPanel panel;
	final int xOffset = 30, yOffset = 30;

	public AnimationTest(){

		super("IP viz", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(850,250);

		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

		panel= new IPtestPanel();
		this.setContentPane(panel);

	}


	 
	public class IPtestPanel extends JPanel{

	public int positionX=0;
	public int positionY=0;



	public IPtestPanel(){
			this.setBackground(Color.lightGray);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(850, 250));
			this.setDoubleBuffered(true);
			//this.setLocation(0, 0);
	}


	public void paintComponent(Graphics g) {
		
		    	super.paintComponent(g);
		    	Graphics2D g2d = (Graphics2D)g;


			g2d.setColor(Color.white);
			g2d.fillRect(0,0,getWidth(), getHeight());//set background white

			g2d.setPaint(Color.black);
			if((positionX > this.getWidth()) && (positionY > this.getHeight()))
			{
				Ellipse2D.Double circle = new Ellipse2D.Double(positionX, positionY, 10, 10);
				positionX++;
				positionY++;
				g2d.fill(circle);
				//sleep(5);
			}
			else
			{
				g2d.setPaint(Color.black);
				g2d.drawString("Done", 10, 140);
			}





			/*g2d.drawString("Binary String: ", 10, 40);

			for(int i=0; i<64; i++){
				Ellipse2D.Double circle = new Ellipse2D.Double((i*12)+50, 50, 10, 10);
				if(binaryLine.charAt(i)=='0') g2d.setPaint(Color.blue);
				if(binaryLine.charAt(i)=='1') g2d.setPaint(Color.red);	
			    	g2d.fill(circle);
			
				//g2d.setPaint(Color.yellow);
				//g2d.drawString(binaryLine.substring(i, i+1), (i*21)+7, 25);
			}
	
			g2d.setPaint(Color.black);
			g2d.drawString("IP()", 10, 140);

			for(int i=0; i<64; i++){
				Ellipse2D.Double circle = new Ellipse2D.Double((i*12)+50, 150, 10, 10);
				if(IPbits.get(i)==false) g2d.setPaint(Color.blue);
				if(IPbits.get(i)==true) g2d.setPaint(Color.red);	
			    	g2d.fill(circle);
			}*/
		  }

	}

/*	public void update(Graphics g){

	super.update();


	}

	public void repaint(Graphics g){

	super.repaint();

	}*/

	public void setBinary(String binLine){
		binaryLine = binLine;
	}

	public void setIP(BitSet bits){
		IPbits = bits;
	}


}
