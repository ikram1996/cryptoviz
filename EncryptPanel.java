

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

	JLabel crossLabel[] = new JLabel[3];
	JLabel leftLabel[] = new JLabel[3];
	JLabel rightLabel[] = new JLabel[3];


	public EncryptPanel(){
		this.setBackground(Color.white);
		this.setVisible(true);		
		this.setPreferredSize(new Dimension(500, 900));
		//this.setLocation(0, 0);

		createGUI();			
	}	

	private void createGUI(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(500, 900));
		panel.setLocation(0, 0);
		this.add(panel);

		JLabel inputLabel = new JLabel("Input");
		inputLabel.setSize(200, 30);
		inputLabel.setLocation(100, 20);
		inputLabel.setOpaque(true);
		inputLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		inputLabel.setForeground(Color.black);
		inputLabel.setBackground(Color.white);
		inputLabel.setVisible(true);

		for(int i = 0; i<3; i++){
			leftLabel[i] = new JLabel("Lo");
			leftLabel[i].setSize(100, 30);
			leftLabel[i].setLocation(100, (i+1)*170);
			leftLabel[i].setOpaque(true);
			leftLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			leftLabel[i].setForeground(Color.black);
			leftLabel[i].setBackground(Color.white);
			leftLabel[i].setVisible(true);
			panel.add(leftLabel[i]);

			rightLabel[i] = new JLabel("Ro");
			rightLabel[i].setSize(100, 30);
			rightLabel[i].setLocation(300, (i+1)*170);
			rightLabel[i].setOpaque(true);
			rightLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			rightLabel[i].setForeground(Color.black);
			rightLabel[i].setBackground(Color.white);
			rightLabel[i].setVisible(true);
			panel.add(rightLabel[i]);

			if(i > 0){
				crossLabel[i] = new JLabel();
				crossLabel[i].setIcon(crossIcon);
				crossLabel[i].setSize(crossIcon.getImage().getWidth(null), crossIcon.getImage().getHeight(null));
				crossLabel[i].setLocation(80, i*200);
				crossLabel[i].setVisible(true);
				panel.add(crossLabel[i]);
			}
		}
	

		panel.add(inputLabel);


	}

	
	
/*	
	public void paint(Graphics g) {
	
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawString("hi", 50, 50);

		createGUI();

	}//end paintcomponent
*/

					  
}//end panel class
