

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

//this is the fiestel structure
//fully functional now (we think) 5/10/10

public class EncryptPanel extends JPanel implements ActionListener{


	Dimension offDimension;
	Image     offImage;
	Graphics  offGraphics;
	ImageIcon crossIcon = new ImageIcon("imgs/cross.jpg");

	JLabel crossLabel[] = new JLabel[17];
	JLabel leftLabel[] = new JLabel[17];
	JLabel rightLabel[] = new JLabel[17];
	JPanel crossPanel[] = new JPanel[15];
	JLabel keyLabel[] = new JLabel[16];//c
	JTextField inputLabel1, inputLabel2, outputLabel;
	JTextField baseKey; //jpg
	JLabel P1label, P2label, label1, label2, charLabel1, charLabel2;
	JButton VizButtons[] = new JButton[16];//c
	JButton DoAllButton = new JButton();//c
	JButton getKeysButton;
	JButton reverseKeysButton;
	Font buttonFont = new Font("Sans-Serif", Font.BOLD, 10);
	Font keyFont = new Font("Sans-Serif", Font.PLAIN, 7);
        Color paleYellow = new Color(255, 255, 180);
        Color paleGreen = new Color(207, 255, 180);

	BitList keys[];
	BitList roundbits[] = new BitList[17];
	BitList inbits, IPbits, outbits;
//	BitList 
	

	public EncryptPanel(){
		this.setBackground(Color.white);
		this.setVisible(true);		
		this.setPreferredSize(new Dimension(650, 3000));
		createGUI();			
	}	


	private void createGUI(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(650, 3000));
		panel.setLocation(0, 0);		

		inputLabel2 = new JTextField();
		inputLabel2.setSize(300, 20);
		inputLabel2.setLocation(150, 20);
		inputLabel2.setOpaque(true);
		inputLabel2.setHorizontalAlignment(JLabel.CENTER);
		inputLabel2.setForeground(Color.black);
		inputLabel2.setBackground(Color.white);
		inputLabel2.setVisible(true);
		inputLabel2.setToolTipText("<html>Enter one block of data here<br>(64 bits, ASCII or hex)");
                inputLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        inputTextfieldKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        inputTextfieldKeyReleased(evt);
                    }
                });
		panel.add(inputLabel2);

                label1 = new JLabel();
                label1.setText("Input Text");
                label1.setSize(90,20);
                label1.setLocation(150, 3);
                label1.setVisible(true);
                panel.add(label1);

                charLabel1 = new JLabel();
                charLabel1.setText("0 chars");
                charLabel1.setSize(70,20);
                charLabel1.setLocation(280, 3);
                charLabel1.setVisible(true);
                panel.add(charLabel1);

		
		
	/*	inputLabel1 = new JTextField();
		inputLabel1.setText("Input");
		inputLabel1.setSize(100, 20);
		inputLabel1.setLocation(10, 20);
		inputLabel1.setOpaque(true);
		inputLabel1.setHorizontalAlignment(JLabel.CENTER);
		inputLabel1.setForeground(Color.black);
		inputLabel1.setBackground(Color.white);
		inputLabel1.setVisible(true);
		panel.add(inputLabel1);            */
		
		
		
		
		
		
		baseKey = new JTextField();
		baseKey.setSize(150, 20);
		baseKey.setLocation(460, 20);
		baseKey.setOpaque(true);
		baseKey.setHorizontalAlignment(JLabel.CENTER);
		baseKey.setForeground(Color.black);
		baseKey.setBackground(Color.white);
		baseKey.setVisible(true);
		baseKey.setToolTipText("<html>Enter the key here<br>(64 bits, ASCII or hex)");
                baseKey.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        keyTextfieldKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        keyTextfieldKeyReleased(evt);
                    }
                });
		panel.add(baseKey);		

                label2 = new JLabel();
                label2.setText("Key");
                label2.setSize(30,20);
                label2.setLocation(460, 3);
                label2.setVisible(true);
                panel.add(label2);

		charLabel2 = new JLabel();
                charLabel2.setText("0 chars");
                charLabel2.setSize(70,20);
                charLabel2.setLocation(520, 3);
                charLabel2.setVisible(true);
                panel.add(charLabel2);

		JPanel linePanel = new LinePanel();
		linePanel.setLocation(300, 40);
		linePanel.setSize(10, 30);
		linePanel.setVisible(true);
		panel.add(linePanel);
		
		DoAllButton = new JButton("Encrypt");
				
		DoAllButton.setLocation(20, 70);
		DoAllButton.setSize(100,20);
		DoAllButton.setFont(buttonFont);
		DoAllButton.addActionListener(this);
		DoAllButton.setVisible(true);
		panel.add(DoAllButton);

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
		P1label.setToolTipText("Initial Permutation");

		JPanel splitLinePanel = new SplitLinePanel();
		splitLinePanel.setLocation(200, 90);
		splitLinePanel.setSize(200, 50);
		splitLinePanel.setVisible(true);
		panel.add(splitLinePanel);

		for(int i = 0; i<=16; i++){
			leftLabel[i] = new JLabel("<html>L<sub>" + Integer.toString(i) + "</sub></html> ");
			leftLabel[i].setHorizontalAlignment(JLabel.CENTER);
			leftLabel[i].setSize(200, 20);
			leftLabel[i].setLocation(100, (i*160)+140);
			leftLabel[i].setOpaque(true);
			leftLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			leftLabel[i].setForeground(Color.black);
			leftLabel[i].setBackground(paleGreen);
			leftLabel[i].setVisible(true);
			panel.add(leftLabel[i]);

			rightLabel[i] = new JLabel("<html>R<sub>" + Integer.toString(i) + "</sub></html> ");
			rightLabel[i].setSize(200, 20);
			rightLabel[i].setLocation(300, (i*160)+140);
			rightLabel[i].setHorizontalAlignment(JLabel.CENTER);		
			rightLabel[i].setOpaque(true);
			rightLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			rightLabel[i].setForeground(Color.black);
			rightLabel[i].setBackground(paleGreen);
			rightLabel[i].setVisible(true);
			panel.add(rightLabel[i]);
			
			leftLabel[i].setToolTipText("<html>L<sub>" + Integer.toString(i) + "</sub>  (32 bits)</html> ");
			rightLabel[i].setToolTipText("<html>R<sub>" + Integer.toString(i) + "</sub>  (32 bits)</html> ");
			
			
			
			if(i<15)
			{
				crossPanel[i] = new CrossPanel();
				crossPanel[i].setLocation(150, 160+i*160);
				crossPanel[i].setSize(300, 140);
				crossPanel[i].setVisible(true);
				panel.add(crossPanel[i]);
			}
			
			if(i==15)
			{
				CrossPanel2 cp2 = new CrossPanel2();
				cp2.setLocation(150, 160+i*160);
				cp2.setSize(300, 140);
				cp2.setVisible(true);
				panel.add(cp2);
			}
	
			
			
			
			
	
			if(i!=0)
			{
				VizButtons[i-1] = new JButton("Visualize Round "+(i));
				VizButtons[i-1].setLocation(10, (i*160)+55);
				VizButtons[i-1].setSize(140,20);
				VizButtons[i-1].setFont(buttonFont);
				VizButtons[i-1].addActionListener(this);
				VizButtons[i-1].setVisible(true);
				VizButtons[i-1].setEnabled(false);
				panel.add(VizButtons[i-1]);
			}
			if(i!=16){
				keyLabel[i] = new JLabel("<html>Key<sub>" + Integer.toString(i+1) + "</sub></html> ");
				keyLabel[i].setSize(150, 20);
				keyLabel[i].setLocation(450, (i*160)+185);
				keyLabel[i].setHorizontalAlignment(JLabel.CENTER);		
				keyLabel[i].setOpaque(true);
				keyLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
				keyLabel[i].setForeground(Color.black);
				keyLabel[i].setBackground(paleYellow);
				keyLabel[i].setVisible(true);
				panel.add(keyLabel[i]);
				keyLabel[i].setToolTipText("<html>K<sub>" + Integer.toString(i+1) + "</sub> (48 bits)</html> ");
			}
			
			
		}	

		//end last left/right*/
		//inverse initial permutation
		JPanel reverseSplitLinePanel = new ReverseSplitLinePanel();
		reverseSplitLinePanel.setLocation(200, (17*160));
		reverseSplitLinePanel.setSize(200, 50);
		reverseSplitLinePanel.setVisible(true);
		panel.add(reverseSplitLinePanel);


		outputLabel = new JTextField();
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
		
		getKeysButton = new JButton("Generate Keys");
		getKeysButton.setLocation(470,70);
		getKeysButton.setSize(130,20);
		getKeysButton.addActionListener(this);
		getKeysButton.setFont(buttonFont);
		getKeysButton.setVisible(true);
		panel.add(getKeysButton);
		
		reverseKeysButton = new JButton("Reverse keys");
		reverseKeysButton.setLocation(470,100);
		reverseKeysButton.setSize(130,20);
		reverseKeysButton.addActionListener(this);
		reverseKeysButton.setFont(buttonFont);
		reverseKeysButton.setVisible(true);
		reverseKeysButton.setEnabled(false);
		panel.add(reverseKeysButton);
		
		
		

		P2label = new JLabel();
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
		P2label.setToolTipText("Inverse Initial Permutation");
		

		this.add(panel);
	}

        private void inputTextfieldKeyPressed(java.awt.event.KeyEvent evt) {
            int length = inputLabel2.getText().length();
            String tmp = Integer.toString(length) + " chars";
            charLabel1.setText(tmp);
        }

        private void inputTextfieldKeyReleased(java.awt.event.KeyEvent evt) {
            int length = inputLabel2.getText().length();
            String tmp = Integer.toString(length) + " chars";
            charLabel1.setText(tmp);
        }

        private void keyTextfieldKeyPressed(java.awt.event.KeyEvent evt) {
            int length = baseKey.getText().length();
            String tmp = Integer.toString(length) + " chars";
            charLabel2.setText(tmp);
        }

        private void keyTextfieldKeyReleased(java.awt.event.KeyEvent evt) {
            int length = baseKey.getText().length();
            String tmp = Integer.toString(length) + " chars";
            charLabel2.setText(tmp);
        }

	private void getKeys(){	
		
		String s = baseKey.getText();
		BitList temp;
		if (s.length() == 8 )
		{
			temp = new BitList(s);
			baseKey.setText(temp.toHexString());
		}
		else temp = ConvertString.HexStringToBitList(s);
		
		if (temp.length() !=64) return;  
		
		keys= DES.generateKeys(temp);
		setKeyText();
		reverseKeysButton.setEnabled(true);
		DoAllButton.setText("Encrypt");	
		DoAllButton.setForeground(Color.BLACK);
		
		for(int i=0;i<16;i++)
			keyLabel[i].setToolTipText("<html>K<sub>" + Integer.toString(i+1) + "</sub> (48 bits)</html> ");
		
	}

	private void setKeyText(){
		for(int i = 0; i<16; i++){
			//keyLabel[i].setFont(keyFont);
			keyLabel[i].setText(keys[i].toHexString());
		}
	}

	private void IPpermute(){
		P1label.setFont(keyFont);
		String txt = inputLabel2.getText()+"          ";
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


	private void reverseKeys()
	{
	
		if (keys==null) return;
	
		String temp;
		BitList temp2;
		
		for (int i=0;i<8;i++)
		{
			temp = keyLabel[i].getText();
			keyLabel[i].setText(keyLabel[15-i].getText());
			keyLabel[15-i].setText(temp);
			temp = keyLabel[i].getToolTipText();
			keyLabel[i].setToolTipText(keyLabel[15-i].getToolTipText());
			keyLabel[15-i].setToolTipText(temp);		
			temp2=keys[i];
			keys[i]=keys[15-i];
			keys[15-i]=temp2;			
		}	
		
		if (DoAllButton.getText() =="Encrypt")
		{
			 DoAllButton.setText("Decrypt");
			 DoAllButton.setForeground(Color.RED);
		}
		else 
		{
			DoAllButton.setText("Encrypt");		
			DoAllButton.setForeground(Color.BLACK);
		}
	}




	private void DoAll()
	{
		String in = inputLabel2.getText();
		if ( in.length() == 8) //use ascii box, put results in hex box
		{
			inbits = new BitList(in);
			inputLabel2.setText( inbits.toHexString() );
		}
		else		//use hex box
		{
			inbits = ConvertString.HexStringToBitList(in);	
			if ( inbits.length() !=64) return;  //neither ascii nor hex box had a 64-bit input
			inputLabel2.setText( inbits.toHexString() );
		}
		
		IPbits = DES.permute(inbits,DES.IP_Map);
		P1label.setText( IPbits.toHexString());
		
		roundbits[0]  =  IPbits;
		leftLabel[0].setText(getLeftHalf(roundbits[0]).toHexString());
		rightLabel[0].setText(getRightHalf(roundbits[0]).toHexString());
		
				
		if (keys == null) return;	//user needs to make keys first
		
		for(int i = 1; i<=16; i++)
			roundbits[i]= DES.round(roundbits[i-1], keys[i-1]);
		
		roundbits[16]=DES.permute(roundbits[16],DES.LR_Swap_Map);
		
		for(int i = 1; i<=16; i++)
		{
			leftLabel[i].setText(getLeftHalf(roundbits[i]).toHexString());
			rightLabel[i].setText(getRightHalf(roundbits[i]).toHexString());	
			VizButtons[i-1].setEnabled(true);			
			VizButtons[i-1].setToolTipText("Opens a new window to show this round in detail");
		}
				
		P2label.setText(roundbits[16].toHexString());
		
		outbits = DES.permute(roundbits[16],DES.IPinverse_Map);

		outputLabel.setText(outbits.toHexString());	
	
	}
	

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();
		if(source == getKeysButton) getKeys();
		if(source == reverseKeysButton) reverseKeys();
		if(source == DoAllButton) DoAll();

		for(int i = 0; i <16; i++)
		{
			
			if(source == VizButtons[i]){
				
				if (keys == null) return; //cant visualize without a key
				if (roundbits[i] == null) return;
				CryptMain.createFrame(new RoundFrame(i,getLeftHalf(roundbits[i]),getRightHalf(roundbits[i]),keys[i]));
							
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
                        g2d.setStroke(new BasicStroke(3));


			g2d.drawLine(50,0, 50, 52);
			g2d.drawLine(250,0, 250, 60);
                        g2d.setColor(Color.red);
			g2d.drawOval(40, 53, 19, 19);//left
			g2d.drawLine(50,53,50,72);
			g2d.drawLine(40,63,59,63);
                        g2d.setColor(Color.black);
			g2d.fillOval(244, 56, 10, 10);//right
			g2d.drawOval(137, 50, 25, 25);//middle
			g2d.drawString("f", 147,67);
			g2d.drawLine(63,63, 137, 63);
			g2d.drawLine(163,63, 247, 63);
			g2d.drawLine(50,74, 50, 85);
			g2d.drawLine(250,65, 250, 85);
			g2d.drawLine(50,85, 250, 130);//left to right arrow
			g2d.drawLine(250,85, 50, 130);//right to left arrow
			g2d.drawLine(250,130, 250, 140);
			g2d.drawLine(50,130, 50, 140);
			g2d.drawLine(149,50,149,35);
			g2d.drawLine(149,35,245,35);
			g2d.drawArc(245,30,10,10,0,180);
			g2d.drawLine(255,35,300,35);
			
			//added by john 3/21/2010: pointy arrows!
			g2d.drawLine(61,63, 65, 67);
			g2d.drawLine(61,63, 65, 59);	
			g2d.drawLine(163, 63, 167, 67);
			g2d.drawLine(163, 63, 167, 59);
			
			g2d.drawLine(149,49,145,45);
			g2d.drawLine(149,49,153,45);	
			g2d.drawLine(50,51,54,47);
			g2d.drawLine(50,51,46,47);		

		}//end paintcomponent
	}




	private class CrossPanel2 extends JPanel{

		CrossPanel2(){
			this.setBackground(Color.white);	
			this.setPreferredSize(new Dimension(300, 140));
		}

		public void paint(Graphics g) {
	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;

                        g2d.setStroke(new BasicStroke(3));
			g2d.drawLine(50,0, 50, 52);
			g2d.drawLine(250,0, 250, 60);
                        g2d.setColor(Color.red);
       			g2d.drawOval(40, 53, 19, 19);//left
			g2d.drawLine(50,53,50,72);
			g2d.drawLine(40,63,59,63);
                        g2d.setColor(Color.black);
			g2d.fillOval(244, 56, 10, 10);//right
			g2d.drawOval(137, 50, 25, 25);//middle
			g2d.drawString("f", 147,67);
			g2d.drawLine(63,63, 137, 63);
			g2d.drawLine(163,63, 247, 63);
			g2d.drawLine(50,74, 50, 140);
			g2d.drawLine(250,65, 250, 140);
			g2d.drawLine(149,50,149,35);
			g2d.drawLine(149,35,245,35);
			g2d.drawArc(245,30,10,10,0,180);
			g2d.drawLine(255,35,300,35);
			
			//added by john 3/21/2010: pointy arrows!
			g2d.drawLine(61,63, 65, 67);
			g2d.drawLine(61,63, 65, 59);	
			g2d.drawLine(163, 63, 167, 67);
			g2d.drawLine(163, 63, 167, 59);
			
			g2d.drawLine(149,49,145,45);
			g2d.drawLine(149,49,153,45);	
			g2d.drawLine(50,51,54,47);
			g2d.drawLine(50,51,46,47);	
		}
	}

	private class LinePanel extends JPanel{

		LinePanel(){
			this.setBackground(Color.white);	
			this.setPreferredSize(new Dimension(10, 30));
		}

	
		public void paint(Graphics g) {
	
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(3));
			g2d.drawLine(2,0, 2, 30);
			
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
                        g2d.setStroke(new BasicStroke(3));

			g2d.drawLine(100,0, 100, 22);                             
			g2d.drawLine(0,25, 97, 25);
			g2d.drawLine(103,25, 200, 25);
			g2d.drawLine(2,25, 2, 50);
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
			
                        g2d.setStroke(new BasicStroke(3));

			g2d.drawLine(100,28, 100, 50);
			g2d.drawLine(0,25, 97, 25);
			g2d.drawLine(103,25, 200, 25);
			g2d.drawLine(2,0, 2, 25);
			g2d.drawLine(199,0, 199, 25);
			
		}//end paintcomponent
	}			  
}//end panel class




























