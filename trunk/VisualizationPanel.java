
import javax.swing.*;
import java.awt.*;


public class VisualizationPanel extends JPanel implements Runnable{

		final static int spacing = 8;
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
		int size = 0;
		int sizeSetOne = 0;
		int sizeSetTwo = 0;
		int[] map;
		boolean ready;
		Color backgroundColor = new Color(255,255,255);
		Thread animThread;
		private int delay = 25;
		private int index = 0;
		Font f = new Font("Sans-Serif", Font.BOLD, 12);
                boolean animate = false;

		  Dimension offDimension;
		  Image     offImage;
		  Graphics  offGraphics;


		public VisualizationPanel(){
			this.setBackground(Color.white);
                        //this.setOpaque(false);
			this.setVisible(true);		
			this.setPreferredSize(new Dimension(800, 100));
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
		/*
		public void setBitsTwo(BitList two){
			this.bitstwo = two;
			this.sizeSetTwo = bitstwo.length();
		}
                */
		public void setSize(){
			if(sizeSetOne > sizeSetTwo) this.size = sizeSetOne;
			else this.size = sizeSetTwo;
		}
		
		public void setMap(int[] map){
			this.map = map;
                        this.sizeSetTwo = map.length;
                        size = map.length;
                        animate = true;
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
			for(int i=0; i<sizeSetOne; i++){
				nodeSetOne[i] = new Node((i*spacing)+100, 50, bitsone.get(i), bitsone.c[i]);
			}
		}
                //makes another node set based on the map
                //this is the set we want to animate
		public void makeNodesTwo(){
			for(int i=0; i < map.length; i++){
				nodeSetTwo[i] = new Node(nodeSetOne[map[i]-1].x, 50 ,bitsone.get(map[i]-1), bitsone.c[map[i]-1]);
			}
		}

		public void getIntervals(){
                    int endPos;
                        for(int i = 0; i<size; i++){
                            Node endNode;
                            startNode = nodeSetTwo[i];
                            endNode = nodeSetOne[i];
                            endPos = (i*spacing)+100;

                            distance = Math.abs(startNode.x - endPos);

                            if(endPos < startNode.x) direction = -1; //reverse the direction
                            else direction = 1;

                            xInterval = distance/50.0;
                            xInterval = xInterval*direction;
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
				nodeSetTwo[i].y=50;	
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
			if(nodeSetTwo[i].y < 100 ){
				nodeSetTwo[i].x += intervals[i];
				nodeSetTwo[i].y++;
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

                        for(int i = 0; i < sizeSetOne; i++){
                         offGraphics2D.setPaint(nodeSetOne[i].c);
                                if ( nodeSetOne[i].value ==true)
                                     offGraphics2D.drawString("1", (float)nodeSetOne[i].x,(float)nodeSetOne[i].y);
                                else
                                     offGraphics2D.drawString("0", (float)nodeSetOne[i].x,(float)nodeSetOne[i].y);
                        }
                        if(animate){
                            for(int i = 0; i < size; i++){
                                    offGraphics2D.setPaint(nodeSetTwo[i].c);
                                        step(i);
                                        if ( nodeSetTwo[i].value ==true)
                                             offGraphics2D.drawString("1", (float)nodeSetTwo[i].x,(float)nodeSetTwo[i].y);
                                        else
                                             offGraphics2D.drawString("0", (float)nodeSetTwo[i].x,(float)nodeSetTwo[i].y);

                            }
                        }
		    // Copy the off screen buffer to the screen.
			g2d.drawImage(offImage, 0, 0, this);

		}//end paintcomponent
					  
	}//end panel class
