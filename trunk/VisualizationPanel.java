import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class VisualizationPanel extends JPanel implements Runnable{

		final static int spacing = 12;
		int openFrameCount = 1;
		String binaryLine = new String();
		BitList bitsone, bitstwo;
		JPanel panel;
		final int xOffset = 30, yOffset = 30;
		Node nodeSetOne[] = new Node[64];
		Node nodeSetTwo[] = new Node[64];
		double intervals[] = new double[64];
		double xInterval = 0;
		double distance;
		int direction = 1;
		Node startNode, node;
		int size, startIndex;
		int sizeSetOne = 0;
		int sizeSetTwo = 0;
		int[] map;
		boolean ready;
		Color backgroundColor = new Color(255,255,255);
		Thread animThread;
		private int delay = 1;
		private int index = 0;
		Font f = new Font("Sans-Serif", Font.BOLD, 14);

		  Dimension offDimension;
		  Image     offImage;
		  Graphics  offGraphics;


		public VisualizationPanel(){
			this.setBackground(Color.white);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(850, 250));
			this.setDoubleBuffered(true);
			//this.setLocation(0, 0);

			size = 0;
			sizeSetOne = 0;				
		}	
		
		public void setBitsOne(BitList one){
			this.bitsone = one;
			this.sizeSetOne = bitsone.length();
			this.size = this.sizeSetOne;
		}

		public void outBitsOne(){
			System.out.println(bitsone.toString());
		}
		
		public void setBitsTwo(BitList two){
			this.bitstwo = two;
			this.sizeSetTwo = bitstwo.length();
		}

		public void setSize(){
			if(sizeSetOne > sizeSetTwo) this.size = sizeSetOne;
			else this.size = sizeSetTwo;
		}
		
		public void setMap(int[] map){
			this.map = map;
		}

		public void setSizeTwo(int size){
			this.sizeSetTwo = size;
		}
		
		public void clear(){
			this.size = 0;
			this.sizeSetTwo = 0;
		}
		
		public void setDelay(int delay){
			this.delay = delay;
		}
		
		public void setBackground(Color color){
			this.backgroundColor = color;
		}

		public void makeNodesOne(){
			for(int i=0; i<sizeSetOne; i++){//64
				nodeSetOne[i] = new Node((i*spacing)+100, 50, bitsone.get(i), bitsone.c[i]);
			}
		}
			
		public void makeNodesTwo(){
			for(int i=0; i<sizeSetTwo; i++){//56
				nodeSetTwo[i] = new Node((i*spacing)+100, 120,bitstwo.get(i), bitstwo.c[i]);
			}
		}

		public void getIntervals(){
		
			if(sizeSetOne > sizeSetTwo) size = sizeSetTwo;
			else size = sizeSetOne;
		
				for(int i = 0; i<size; i++){				
					startIndex = map[i];
					startIndex -= 1;
					startNode = nodeSetOne[startIndex];  
					//System.out.println(sizeSetOne);
						
					          
					if(startNode.x > nodeSetTwo[i].x){
						distance = startNode.x - nodeSetTwo[i].x;
						direction = -1; //reverse the direction
					}
					else {
				
						distance = nodeSetTwo[i].x - startNode.x;
						direction = 1;
					}									
	
					xInterval = distance/100.0;
					xInterval = xInterval*direction;
					//System.out.println(xInterval);
					intervals[i] = xInterval;
																			
			 	}			
		}

		public void resetNodePositions(){
			for(int i=0; i<sizeSetOne; i++){
				nodeSetOne[i].x=(i*spacing)+100;
				nodeSetOne[i].y=50;				
			}

			for(int i=0; i<sizeSetTwo; i++){
				nodeSetTwo[i].x=(i*spacing)+100;
				nodeSetTwo[i].y=150;	
			}
		}

		public void start() {			
			getIntervals();	
			if (animThread == null) {
			      animThread = new Thread(this);
				index = 0;
			      animThread.start();
			    }
				
		}

		public void stop() {

		    // Stop the animation thread.

		    if (animThread != null) {
		      animThread.stop();
		      animThread = null;
		    }
		}


		public void restart(){
			ready = false;
			resetNodePositions();
			repaint();
		}

		public void run() {
			
		    while (Thread.currentThread() == animThread) {	
				try {
					Thread.currentThread().sleep(delay);
				}
				catch (InterruptedException e) {}
				repaint();		   
		    }
		}

		private void step(int i){
			startIndex = map[i];
			startIndex -= 1;
			node = nodeSetOne[startIndex];  					
			if(node.y < 150 ){							
				node.x += intervals[i];	
				node.y++;	
			}	
		}

		public void update(Graphics g) {
			paint(g);
		}
	
		public void paint(Graphics g) {
		
		    super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D)g;

		 Dimension d = getSize();

		    if (offGraphics == null ||
			d.width != offDimension.width || d.height != offDimension.height) {
		      offDimension = d;
		      offImage = createImage(d.width, d.height);
		      offGraphics = offImage.getGraphics();
		    }


		

			Graphics2D offGraphics2D = (Graphics2D)offGraphics;
			offGraphics.setFont(f);
		    	offGraphics.setColor(Color.white);
		    	offGraphics.fillRect(0, 0, d.width, d.height);

		   	offGraphics2D.setPaint(Color.black);
			offGraphics.drawLine(0,0,d.width,0);
			
			offGraphics.drawString("Visualization", 20, 20);


				for(int i = 0; i < size; i++){					
					offGraphics2D.setPaint(nodeSetOne[i].c);
					if(sizeSetTwo > 0) step(i);
					if ( nodeSetOne[i].value ==true)
					{
						//Rectangle2D.Double circle = new Rectangle2D.Double(nodeSetOne[i].x+3,
						//nodeSetOne[i].y, 4, 10);
						offGraphics2D.drawString("1", (float)nodeSetOne[i].x,(float)nodeSetOne[i].y);
						//offGraphics2D.fill(circle);
					}
					
					else
					{					
						//Ellipse2D.Double circle = new Ellipse2D.Double(nodeSetOne[i].x,
						//nodeSetOne[i].y, 10, 10);
						offGraphics2D.drawString("0", (float)nodeSetOne[i].x,(float)nodeSetOne[i].y);
						//offGraphics2D.fill(circle);
					}
				}

		    // Copy the off screen buffer to the screen.
			g2d.drawImage(offImage, 0, 0, this);

				/************This displays nodeSetTwo, may be useful**************/

				/*
				for(int i = 0; i < sizeSetTwo; i++){
					if(nodeSetTwo[i].bit == true) g2d.setPaint(Color.red);
					else g2d.setPaint(Color.blue);
					Ellipse2D.Double circle = new Ellipse2D.Double(nodeSetTwo[i].x, nodeSetTwo[i].y, 10, 10);
					g2d.fill(circle);
				}*/				

		}//end paintcomponent
					  
	}//end panel class
