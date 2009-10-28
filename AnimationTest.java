
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

		panel= new VisualizationPanel(nodeSetOne, nodeSetTwo, bitsone.length(), bitstwo.length(), map, intervals);
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
	
				xInterval = distance/100.0;
				xInterval = xInterval*direction;
				//System.out.println(xInterval);
				intervals[i] = xInterval;
																			
		 	}			
	}
}
