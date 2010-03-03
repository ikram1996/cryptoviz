

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class EncryptPanel extends JPanel implements ActionListener{


	Dimension offDimension;
	Image     offImage;
	Graphics  offGraphics;
	ImageIcon crossIcon = new ImageIcon("imgs/cross.jpg");

	JLabel crossLabel[] = new JLabel[17];
	JLabel leftLabel[] = new JLabel[17];
	JLabel rightLabel[] = new JLabel[17];
	JPanel crossPanel[] = new JPanel[17];
	JLabel keyLabel[] = new JLabel[16];//c
	JTextField inputLabel;
	JLabel P1label;
	JButton RoundButtons[] = new JButton[16];//c
	JButton VizButtons[] = new JButton[16];//c
	JButton IPButton = new JButton();//c
	JButton IIPButton = new JButton();//c
	JButton getKeysButton = new JButton();
	Font buttonFont = new Font("Sans-Serif", Font.BOLD, 10);
	Font keyFont = new Font("Sans-Serif", Font.PLAIN, 7);

	BitList keys[] = new BitList[16];
	BitList IPbits;

	public EncryptPanel(){
		this.setBackground(Color.white);
		this.setVisible(true);		
		this.setPreferredSize(new Dimension(700, 3000));
		createGUI();			
	}	


	private void createGUI(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(700, 3000));
		panel.setLocation(0, 0);		

		inputLabel = new JTextField();
		inputLabel.setText("Input");
		inputLabel.setSize(300, 20);
		inputLabel.setLocation(150, 20);
		inputLabel.setOpaque(true);
		inputLabel.setHorizontalAlignment(JLabel.CENTER);
		inputLabel.setForeground(Color.black);
		inputLabel.setBackground(Color.white);
		inputLabel.setVisible(true);
		panel.add(inputLabel);

		JPanel linePanel = new LinePanel();
		linePanel.setLocation(300, 40);
		linePanel.setSize(10, 30);
		linePanel.setVisible(true);
		panel.add(linePanel);
		
		IPButton = new JButton("Do Permutation");
		IPButton.setLocation(0, 70);
		IPButton.setSize(130,20);
		IPButton.setFont(buttonFont);
		IPButton.addActionListener(this);
		IPButton.setVisible(true);
		panel.add(IPButton);

		P1label = new JLabel();
		P1label.setText("Initial Permutation");
		P1label.setSize(300, 20);
		P1label.setLocation(150, 70);
		P1label.setOpaque(true);
		P1label.setHorizontalAlignment(JLabel.CENTER);
		P1label.setBorder(BorderFactory.createLineBorder(Color.black));
		P1label.setForeground(Color.black);
		P1label.setBackground(Color.white);
		P1label.setVisible(true);
		panel.add(P1label);

		JPanel splitLinePanel = new SplitLinePanel();
		splitLinePanel.setLocation(200, 90);
		splitLinePanel.setSize(200, 50);
		splitLinePanel.setVisible(true);
		panel.add(splitLinePanel);

		for(int i = 1; i<=17; i++){
			leftLabel[i-1] = new JLabel("<html>L<sub>" + Integer.toString(i-1) + "</sub></html> ");
			leftLabel[i-1].setHorizontalAlignment(JLabel.CENTER);
			leftLabel[i-1].setSize(200, 20);
			leftLabel[i-1].setLocation(100, (i*160)-20);
			leftLabel[i-1].setOpaque(true);
			leftLabel[i-1].setBorder(BorderFactory.createLineBorder(Color.black));
			leftLabel[i-1].setForeground(Color.black);
			leftLabel[i-1].setBackground(Color.white);
			leftLabel[i-1].setVisible(true);
			panel.add(leftLabel[i-1]);

			rightLabel[i-1] = new JLabel("<html>R<sub>" + Integer.toString(i-1) + "</sub></html> ");
			rightLabel[i-1].setSize(200, 20);
			rightLabel[i-1].setLocation(300, (i*160)-20);
			rightLabel[i-1].setHorizontalAlignment(JLabel.CENTER);		
			rightLabel[i-1].setOpaque(true);
			rightLabel[i-1].setBorder(BorderFactory.createLineBorder(Color.black));
			rightLabel[i-1].setForeground(Color.black);
			rightLabel[i-1].setBackground(Color.white);
			rightLabel[i-1].setVisible(true);
			panel.add(rightLabel[i-1]);
			
			if(i!=17)
			{
				crossPanel[i-1] = new CrossPanel();
				crossPanel[i-1].setLocation(150, i*160);
				crossPanel[i-1].setSize(300, 140);
				crossPanel[i-1].setVisible(true);
				panel.add(crossPanel[i-1]);
			}
	
			if(i!=1)
			{
				RoundButtons[i-2] = new JButton("Do Round "+(i-1));
				RoundButtons[i-2].setLocation(15, (i*160)-105);
				RoundButtons[i-2].setSize(120,20);
				RoundButtons[i-2].setFont(buttonFont);
				RoundButtons[i-2].addActionListener(this);
				RoundButtons[i-2].setVisible(true);
				panel.add(RoundButtons[i-2]);
		
				VizButtons[i-2] = new JButton("Visualize Round "+(i-1));
				VizButtons[i-2].setLocation(460, (i*160)-105);
				VizButtons[i-2].setSize(150,20);
				VizButtons[i-2].setFont(buttonFont);
				VizButtons[i-2].addActionListener(this);
				VizButtons[i-2].setVisible(true);
				panel.add(VizButtons[i-2]);
			}
			if(i!=17){
				keyLabel[i-1] = new JLabel("<html>Key<sub>" + Integer.toString(i) + "</sub></html> ");
				keyLabel[i-1].setSize(250, 20);
				keyLabel[i-1].setLocation(450, (i*160)+25);
				keyLabel[i-1].setHorizontalAlignment(JLabel.CENTER);		
				keyLabel[i-1].setOpaque(true);
				keyLabel[i-1].setBorder(BorderFactory.createLineBorder(Color.black));
				keyLabel[i-1].setForeground(Color.black);
				keyLabel[i-1].setBackground(Color.white);
				keyLabel[i-1].setVisible(true);
				panel.add(keyLabel[i-1]);
			}
			
			
		}	

		//end last left/right*/
		//inverse initial permutation
		JPanel reverseSplitLinePanel = new ReverseSplitLinePanel();
		reverseSplitLinePanel.setLocation(200, (17*160));
		reverseSplitLinePanel.setSize(200, 50);
		reverseSplitLinePanel.setVisible(true);
		panel.add(reverseSplitLinePanel);


		JTextField outputLabel = new JTextField();
		outputLabel.setText("Output");
		outputLabel.setSize(300, 20);
		outputLabel.setLocation(150, (17*160)+100);
		outputLabel.setOpaque(true);
		outputLabel.setHorizontalAlignment(JLabel.CENTER);
		//outputLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		outputLabel.setForeground(Color.black);
		outputLabel.setBackground(Color.white);
		outputLabel.setVisible(true);
		panel.add(outputLabel);

		JPanel linePanel2 = new LinePanel();
		linePanel2.setLocation(300, (17*160)+70);
		linePanel2.setSize(10, 30);
		linePanel2.setVisible(true);
		panel.add(linePanel2);		

		IIPButton = new JButton("Do Permutation");
		IIPButton.setLocation(0, 50+(160*17));
		IIPButton.setSize(130,20);
		IIPButton.setFont(buttonFont);
		IIPButton.setVisible(true);
		panel.add(IIPButton);

		getKeysButton = new JButton("Get Keys");
		getKeysButton.setLocation(470,70);
		getKeysButton.setSize(130,20);
		getKeysButton.addActionListener(this);
		getKeysButton.setFont(buttonFont);
		getKeysButton.setVisible(true);
		panel.add(getKeysButton);

		JLabel P2label = new JLabel();
		P2label.setText("Inverse Initial Permutation");
		P2label.setSize(250, 20);
		P2label.setLocation(175, 50+(160*17));
		P2label.setOpaque(true);
		P2label.setHorizontalAlignment(JLabel.CENTER);
		P2label.setBorder(BorderFactory.createLineBorder(Color.black));
		P2label.setForeground(Color.black);
		P2label.setBackground(Color.white);
		P2label.setVisible(true);
		panel.add(P2label);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(800,800));

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(scrollPane);
	}

	private void getKeys(){		
		this.keys = KeyFrame.getKeys();
		setKeyText();
	}

	private void setKeyText(){
		for(int i = 0; i<16; i++){
			keyLabel[i].setFont(keyFont);
			keyLabel[i].setText(keys[i].toString());
		}
	}

	private void IPpermute(){
		P1label.setFont(keyFont);
		String txt = inputLabel.getText()+"          ";
		txt = txt.substring(0,8);
		IPbits = ConvertString.StringToBitList(txt);
		IPbits = DES.permute(IPbits, DES.IP_Map);
		String tmp = IPbits.toString();
		P1label.setText(tmp);
	}

	private BitList getLeftHalf(BitList in){
		BitList temp=in.get(0,32);
		return temp;
	}

	private BitList getRightHalf(BitList in){
		BitList temp=in.get(32,64);
		return temp;
	}

	private void round(int num){
		if(num == 0){
			leftLabel[num].setFont(keyFont);
			rightLabel[num].setFont(keyFont);

			//first, split up the IPbits into left and right halves
			leftLabel[num].setText(getLeftHalf(IPbits).toString());
			rightLabel[num].setText(getRightHalf(IPbits).toString());

			leftLabel[num+1].setFont(keyFont);
			rightLabel[num+1].setFont(keyFont);

			//right half is copied directly to left half
			leftLabel[num+1].setText(rightLabel[num].getText());						

			//THIS IS BROKEN RIGHT NOW
			//also we XOR the left half of the input with f(right_input,key) 
			BitList temp=ConvertString.StringToBitList(leftLabel[num].getText());
			System.out.println(temp.toString());						
			temp.xor(DES.cipherFunction(ConvertString.StringToBitList(rightLabel[num].getText()), keys[num]));
			rightLabel[num+1].setText(temp.toString());
		}
		else{
			//logic needs to be done
		}
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();
		if(source == getKeysButton) getKeys();
		if(source == IPButton) IPpermute();

		for(int i = 0; i <16; i++)
		{
			if(source == RoundButtons[i]){
				round(i);											
			}
			
			if(source == VizButtons[i]){
				CryptMain.createFrame(new RoundFrame(i,getLeftHalf(IPbits),getRightHalf(IPbits),keys[i]));
							
			}		
			
		}

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
			g2d.drawLine(149,50,149,35);
			g2d.drawLine(149,35,245,35);
			g2d.drawArc(245,30,10,10,0,180);
			g2d.drawLine(255,35,300,35);


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
	
	private class ReverseSplitLinePanel extends JPanel{


		ReverseSplitLinePanel(){
			this.setBackground(Color.white);	
			this.setPreferredSize(new Dimension(200, 50));
		}

	
		public void paint(Graphics g) {
	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;

			g2d.drawLine(100,28, 100, 50);
			g2d.drawOval(97, 22, 6, 6);//right
			g2d.drawLine(0,25, 97, 25);
			g2d.drawLine(103,25, 200, 25);
			g2d.drawLine(0,0, 0, 25);
			g2d.drawLine(199,0, 199, 25);
			
		}//end paintcomponent
	}			  
}//end panel class




























