

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class EncryptPanel extends JPanel{


	Dimension offDimension;
	Image     offImage;
	Graphics  offGraphics;
	ImageIcon crossIcon = new ImageIcon("imgs/cross.jpg");

	JLabel crossLabel[] = new JLabel[16];
	JLabel leftLabel[] = new JLabel[16];
	JLabel rightLabel[] = new JLabel[16];
	JPanel crossPanel[] = new JPanel[16];


	public EncryptPanel(){
		this.setBackground(Color.white);
		this.setVisible(true);		
		this.setPreferredSize(new Dimension(600, 3000));
		//this.setLocation(0, 0);

		createGUI();			
	}	

	private void createGUI(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(550, 3000));
		panel.setLocation(0, 0);

		

		

		JLabel inputLabel = new JLabel();
		inputLabel.setText("Input");
		inputLabel.setSize(300, 20);
		inputLabel.setLocation(100, 20);
		inputLabel.setOpaque(true);
		inputLabel.setHorizontalAlignment(JLabel.CENTER);
		inputLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		inputLabel.setForeground(Color.black);
		inputLabel.setBackground(Color.white);
		inputLabel.setVisible(true);
		panel.add(inputLabel);

		JPanel linePanel = new LinePanel();
		linePanel.setLocation(250, 40);
		linePanel.setSize(10, 30);
		linePanel.setVisible(true);
		panel.add(linePanel);

		JLabel P1label = new JLabel();
		P1label.setText("Initial Permutation");
		P1label.setSize(200, 20);
		P1label.setLocation(150, 70);
		P1label.setOpaque(true);
		P1label.setHorizontalAlignment(JLabel.CENTER);
		P1label.setBorder(BorderFactory.createLineBorder(Color.black));
		P1label.setForeground(Color.black);
		P1label.setBackground(Color.white);
		P1label.setVisible(true);
		panel.add(P1label);

		JPanel splitLinePanel = new SplitLinePanel();
		splitLinePanel.setLocation(150, 90);
		splitLinePanel.setSize(200, 50);
		splitLinePanel.setVisible(true);
		panel.add(splitLinePanel);

		for(int i = 1; i<16; i++){
			leftLabel[i] = new JLabel("<html>L<sub>" + Integer.toString(i-1) + "</sub></html> ");
			leftLabel[i].setHorizontalAlignment(JLabel.CENTER);
			leftLabel[i].setSize(100, 20);
			leftLabel[i].setLocation(100, (i*160)-20);
			leftLabel[i].setOpaque(true);
			leftLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			leftLabel[i].setForeground(Color.black);
			leftLabel[i].setBackground(Color.white);
			leftLabel[i].setVisible(true);
			panel.add(leftLabel[i]);

			rightLabel[i] = new JLabel("<html>R<sub>" + Integer.toString(i-1) + "</sub></html> ");
			rightLabel[i].setSize(100, 20);
			rightLabel[i].setLocation(300, (i*160)-20);
			rightLabel[i].setHorizontalAlignment(JLabel.CENTER);		
			rightLabel[i].setOpaque(true);
			rightLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			rightLabel[i].setForeground(Color.black);
			rightLabel[i].setBackground(Color.white);
			rightLabel[i].setVisible(true);
			panel.add(rightLabel[i]);

			crossPanel[i] = new CrossPanel();
			crossPanel[i].setLocation(100, i*160);
			crossPanel[i].setSize(300, 140);
			crossPanel[i].setVisible(true);
			panel.add(crossPanel[i]);
		}	

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(590,800));

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(scrollPane);

		
	}

	
	
	private class CrossPanel extends JPanel{


		CrossPanel(){
			this.setBackground(Color.white);	
			this.setPreferredSize(new Dimension(300, 140));
		}

	
		public void paint(Graphics g) {
	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;

			g2d.drawLine(50,0, 50, 50);
			g2d.drawLine(250,0, 250, 60);
			g2d.drawOval(37, 50, 25, 25);//left
			g2d.drawString("+", 46,66);
			g2d.drawOval(247, 60, 5, 5);//right
			g2d.drawOval(137, 50, 25, 25);//middle
			g2d.drawString("f", 147,67);
			g2d.drawLine(63,63, 137, 63);
			g2d.drawLine(163,63, 247, 63);
			g2d.drawLine(50,75, 50, 85);
			g2d.drawLine(250,65, 250, 85);
			g2d.drawLine(50,85, 250, 130);//left to right arrow
			g2d.drawLine(250,85, 50, 130);//right to left arrow
			g2d.drawLine(250,130, 250, 140);
			g2d.drawLine(50,130, 50, 140);


		}//end paintcomponent
	}

	private class LinePanel extends JPanel{

		LinePanel(){
			this.setBackground(Color.white);	
			this.setPreferredSize(new Dimension(10, 30));
		}

	
		public void paint(Graphics g) {
	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;

			g2d.drawLine(0,0, 0, 30);
			
		}//end paintcomponent
	}

	private class SplitLinePanel extends JPanel{


		SplitLinePanel(){
			this.setBackground(Color.white);	
			this.setPreferredSize(new Dimension(200, 50));
		}

	
		public void paint(Graphics g) {
	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;

			g2d.drawLine(100,0, 100, 22);
			g2d.drawOval(97, 22, 6, 6);//right
			g2d.drawLine(0,25, 97, 25);
			g2d.drawLine(103,25, 200, 25);
			g2d.drawLine(0,25, 0, 50);
			g2d.drawLine(199,25, 199, 50);
			
		}//end paintcomponent
	}
					  
}//end panel class




























