import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class VisualizationPanel extends JPanel {

		int openFrameCount = 1;
		String binaryLine = new String();
		BitSet bitsone, bitstwo;
		JPanel panel;
		final int xOffset = 30, yOffset = 30;
		Node nodeSetOne[] = new Node[64];
		Node nodeSetTwo[] = new Node[64];
		double intervals[] = new double[64];
		double xInterval = 0;
		double distance;
		int direction = 1;
		Node startNode;
		int size, startIndex, sizeSetOne, sizeSetTwo;
		int[] map;
		boolean ready;
		
		public VisualizationPanel(BitSet bitsone, BitSet bitstwo, int[] map){

			this.sizeSetOne = bitsone.length();
			this.sizeSetTwo = bitstwo.length();
			this.bitsone = bitsone;
			this.bitstwo = bitstwo;
			this.map = map;

			makeNodes();

			this.setBackground(Color.lightGray);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(850, 250));
			this.setDoubleBuffered(true);
			//this.setLocation(0, 0);

			//this.nodeSetOne = setOne;
			//this.nodeSetTwo = setTwo;
			
			
			if(sizeSetOne > sizeSetTwo) size = sizeSetTwo;
			else size = sizeSetOne;

				
		}

		public VisualizationPanel(BitSet bitsone){
			this.sizeSetOne = bitsone.length();

			this.bitsone = bitsone;

			makeNodes();

			this.setBackground(Color.lightGray);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(850, 250));
			this.setDoubleBuffered(true);

			size = sizeSetOne;
		}

		public VisualizationPanel(){
			this.setBackground(Color.white);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(850, 250));
			this.setDoubleBuffered(true);
			//this.setLocation(0, 0);

			size = 0;
			sizeSetOne = 0;				
		}	

		public void makeNodes(){
			for(int i=0; i<sizeSetOne; i++){//64
				nodeSetOne[i] = new Node((i*10)+120, 50, bitsone.get(i), new Color(255-4*i,20+2*i,50+3*i));
			}

			for(int i=0; i<sizeSetTwo; i++){//56
				nodeSetTwo[i] = new Node((i*10)+150, 120,bitstwo.get(i),new Color(255-4*i,20+2*i,50+3*i));
			}
		}

		public void getIntervals(){
				for(int i = 0; i<size; i++){				
					startIndex = map[i];
					startNode = nodeSetOne[startIndex];            
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
				nodeSetOne[i].x=(i*10)+120;
				nodeSetOne[i].y=50;				
			}

			for(int i=0; i<sizeSetTwo; i++){
				nodeSetTwo[i].x=(i*10)+120;
				nodeSetTwo[i].y=150;	
			}
		}

		public void start() {
			
			getIntervals();
			ready = true;
			validate();					
		}

		public void restart(){
			ready = false;
			resetNodePositions();
			repaint();
		}
	
		public void paintComponent(Graphics g) {
		
		    	super.paintComponent(g);
		    	Graphics2D g2d = (Graphics2D)g;


			g2d.setColor(Color.white);
			g2d.fillRect(0,0,getWidth(), getHeight());//set background white

			g2d.setPaint(Color.black);
			
				for(int i = 0; i < sizeSetOne; i++){					
					g2d.setPaint(nodeSetOne[i].c);
					if ( nodeSetOne[i].value ==true)
					{
						Rectangle2D.Double circle = new Rectangle2D.Double(nodeSetOne[i].x+3,
						nodeSetOne[i].y, 4, 10);
						g2d.fill(circle);
					}
					
					else
					{					
						Ellipse2D.Double circle = new Ellipse2D.Double(nodeSetOne[i].x,
						nodeSetOne[i].y, 10, 10);
						g2d.fill(circle);
					}
				}

				/************This displays nodeSetTwo, may be useful**************/

				/*
				for(int i = 0; i < sizeSetTwo; i++){
					if(nodeSetTwo[i].bit == true) g2d.setPaint(Color.red);
					else g2d.setPaint(Color.blue);
					Ellipse2D.Double circle = new Ellipse2D.Double(nodeSetTwo[i].x, nodeSetTwo[i].y, 10, 10);
					g2d.fill(circle);
				}*/

				if(ready){
											
					for(int i = 0; i<size; i++){					
						startIndex = map[i];
						startNode = nodeSetOne[startIndex];  					
						if(startNode.y < 150 ){							
							startNode.x += intervals[i];	
							startNode.y++;
							repaint();	
						}
					}			
				}//end ready

		}//end paintcomponent
			
		  
	}//end panel class
