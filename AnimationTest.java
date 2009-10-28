
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;


public class AnimationTest extends JInternalFrame{


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
	int size, startIndex;
	int[] map;

	public AnimationTest(BitSet one, BitSet two, int[] map){

		super("IP viz", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(850,250);

		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

		this.map = map;

		this.bitsone = one;
		this.bitstwo = two;

		if(bitsone.length() > bitstwo.length()) size = bitstwo.length();
		else size = bitsone.length();

		makeNodes();
		getIntervals();

		panel= new IPtestPanel(nodeSetOne, nodeSetTwo, bitsone.length(), bitstwo.length(), map, intervals);
		this.setContentPane(panel);

	}

	//makes two sets of nodes with x,y coords based off on bitsets
	public void makeNodes(){
		for(int i=0; i<bitsone.length(); i++){//64
			nodeSetOne[i] = new Node(i*10, 50, bitsone.get(i), new Color(255-4*i,20+2*i,50+3*i));
		}

		for(int i=0; i<bitstwo.length(); i++){//56
			nodeSetTwo[i] = new Node(i*10, 150,bitstwo.get(i),new Color(255-4*i,20+2*i,50+3*i));
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
				//System.out.println(distance);
						
					
				xInterval = distance/100.0;
				xInterval = xInterval*direction;
				//System.out.println(xInterval);
				intervals[i] = xInterval;
				System.out.println("X1: " +startNode.x);
				System.out.println("X2: " +nodeSetTwo[i].x);
				System.out.println("Interval: " +intervals[i]);
					
														
		 	}			
	}


	 
	public class IPtestPanel extends JPanel implements ActionListener{

	Node nodeSetOne[] = new Node[64];
	Node nodeSetTwo[] = new Node[64];
	int sizeSetOne, sizeSetTwo;
	double xInterval = 0;
	int[] map;
	Node startNode;
	int distance;
	int size, startIndex;
	int direction = 1;
	JButton start = new JButton("Start");
	JButton restart = new JButton("Restart");
	boolean ready = false;
	int nodeCount = 0;
	double intervals[];


	public IPtestPanel(Node setOne[], Node setTwo[], int sizeone, int sizetwo, int[] map, double[] intervals){
			this.setBackground(Color.lightGray);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(850, 250));
			this.setDoubleBuffered(true);
			//this.setLocation(0, 0);

			this.nodeSetOne = setOne;
			this.nodeSetTwo = setTwo;
			this.sizeSetOne = sizeone;
			this.sizeSetTwo = sizetwo;
			this.map = map;
			this.intervals = intervals;
			
			if(sizeSetOne > sizeSetTwo) size = sizeSetTwo;
			else size = sizeSetOne;

			start.addActionListener(this);
			start.setEnabled(true);
			restart.addActionListener(this);
			restart.setEnabled(true);
			this.add(start);
			this.add(restart);
				
	}

	public void resetNodePositions(){
		for(int i=0; i<sizeSetOne; i++){
			nodeSetOne[i].x=i*10;
			nodeSetOne[i].y=50;				
		}

		for(int i=0; i<sizeSetTwo; i++){
			nodeSetTwo[i].x=i*10;
			nodeSetTwo[i].y=150;	
		}
	}

	public void actionPerformed(ActionEvent evt){
		
		Object source = evt.getSource();
		if(source == start) start();
		if(source == restart) restart();

	}

	public void start() {


			while(nodeSetOne[map[0]].y<150)  	{	
	
				repaint();
			
				for(int i = 0; i<size; i++){
					
					startIndex = map[i];
					startNode = nodeSetOne[startIndex];  
					
														
						startNode.x += intervals[i];						
						startNode.y++;
						repaint();	
				}
				
		}
		
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
						Rectangle2D.Double circle = new Rectangle2D.Double(nodeSetOne[i].x+3, nodeSetOne[i].y, 4, 10);
						g2d.fill(circle);
						}
					
					else
						{					
						Ellipse2D.Double circle = new Ellipse2D.Double(nodeSetOne[i].x, nodeSetOne[i].y, 10, 10);
						g2d.fill(circle);
						}
					

					
					
					
					//System.out.println(nodeSetOne[i].x);
					//System.out.println(nodeSetTwo[i].x);
				}/*
				for(int i = 0; i < sizeSetTwo; i++){
					if(nodeSetTwo[i].bit == true) g2d.setPaint(Color.red);
					else g2d.setPaint(Color.blue);
					Ellipse2D.Double circle = new Ellipse2D.Double(nodeSetTwo[i].x, nodeSetTwo[i].y, 10, 10);
					g2d.fill(circle);
				}*/

		}
			
		  
	}

/*	public void update(Graphics g){

	super.update();


	}

	public void repaint(Graphics g){

	super.repaint();

	}

	public void setBinary(String binLine){
		binaryLine = binLine;
	}

	public void setIP(BitSet bits){
		IPbits = bits;
	}
*/


}
