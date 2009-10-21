
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;

public class KeyFrame extends JInternalFrame implements ActionListener{

	int openFrameCount = 2;

	final int xOffset = 30, yOffset = 30;


	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;

	protected JButton b1,b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;

	private JButton buttons[] = new JButton[18];

	private JTextField textfields[] = new JTextField[19];

	BitSet bits, keyBits;

	String key;

	private final static String newline = "\n";

	    public KeyFrame() {
		super("Make Key", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(700,700);

		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

		createGUI();
	    }

	
	private void createGUI(){
		JPanel panel = new JPanel(new BorderLayout(5,5));
		panel.setBackground(Color.lightGray);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(700, 700));
		panel.setLocation(0, 0);
		this.setContentPane(panel);	

		JPanel buttonPanel = new JPanel();
		buttonPanel.setVisible(true);	
		panel.add(buttonPanel, BorderLayout.PAGE_START);

		b1 = new JButton("Convert Key");
		b1.addActionListener(this);
		b1.setEnabled(true);
		buttonPanel.add(b1);
	
		b3 = new JButton("Clear");
		b3.setVerticalTextPosition(AbstractButton.CENTER);
		b3.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b3.setMnemonic(KeyEvent.VK_D);
		b3.addActionListener(this);
		b3.setEnabled(true);
		buttonPanel.add(b3);



		JPanel leftPanel = new JPanel();
		GroupLayout layout = new GroupLayout(leftPanel);
 		leftPanel.setLayout(layout);
		leftPanel.setVisible(true);
		//leftPanel.setSize(100,500);
		//leftPanel.setBackground(Color.red);
		

		JPanel rightPanel = new JPanel();
		//rightPanel.setLayout(null);
		rightPanel.setSize(100,500);
		rightPanel.setVisible(true);
		rightPanel.setBackground(Color.blue);
		

		
		for(int i = 0; i<18; i++)
		{
			
			if(i == 0) buttons[i] = new JButton("To Binary");
			else if(i == 1) buttons[i]= new JButton("PC1");
			else if(i == 2) buttons[i]= new JButton("Left Shift");
			else if(i == 3) buttons[i]= new JButton("Left Shift");
			else if(i == 10) buttons[i]= new JButton("Left Shift");
			else if(i == 17) buttons[i]= new JButton("Left Shift");
			else buttons[i]= new JButton("Double Left Shift");
			buttons[i].addActionListener(this);
			buttons[i].setEnabled(true);
		}

		for(int i = 0; i<19; i++)
		{
			textfields[i] = new JTextField();
			textfields[i].setColumns(20);
			textfields[i].setEditable(true);

		}

		//left
		JLabel entertxt = new JLabel("Enter 8 char key: ");

		
		//make layout for left panel
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.Group buttonGroup = layout.createParallelGroup();
		buttonGroup.addComponent(entertxt);
		for(int i = 0; i < 18; i++) {
				buttonGroup.addComponent(buttons[i]);
			}

		GroupLayout.Group textfieldGroup = layout.createParallelGroup();
		for(int i = 0; i < 19; i++) {
				textfieldGroup.addComponent(textfields[i]);
			}

		GroupLayout.Group verticalGroups[] = new GroupLayout.Group[18];
		for(int i = 0; i < 18; i++) {
				verticalGroups[i] = layout.createParallelGroup();
				verticalGroups[i].addComponent(buttons[i]);
				verticalGroups[i].addComponent(textfields[i+1]);
			}

		

		layout.setHorizontalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(buttonGroup))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(textfieldGroup))
		);

		//layout.linkSize(SwingConstants.HORIZONTAL, b2, b3);


		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(entertxt)
			.addComponent(textfields[0]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[0]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[1]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[2]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[3]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[4]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[5]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[6]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[7]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[8]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[9]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[10]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[11]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[12]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[13]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[14]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[15]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[16]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[17]))
		    //.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE), verticalGroups[18])
		        );


		panel.add(leftPanel, BorderLayout.CENTER);
		//panel.add(rightPanel, BorderLayout.LINE_END);	
		
	}

	private void clearText(){

		for(int i = 0; i < 19; i++){
			textfields[i].setText("");
		}

	}

	
	private void convertKey(){
		key = textfields[0].getText();
		String block, x;
		int length;
		if(key.length() < 8) length = key.length();
		else length = 8;

		/*
		for(int i = 0; i < length; i++){
			//binaryTextField.append(key.charAt(i) + " : ");
			block = ConvertString.charToAscii(key.charAt(i));
			x = binaryTextField.getText() + block;
			binaryTextField.setText(x + newline);	
		}*/

		keyBits = ConvertString.stringToBinary(ConvertString.stringToAscii(key));
		for(int i=0; i<64; i++){
		
			if(keyBits.get(i) == true) x = textfields[1].getText() + "0";
			else x = textfields[1].getText() + "1";
			textfields[1].setText(x);
		}
				
	}

	private void funcPC1(){
		String binary = textfields[1].getText();
		BitSet bin = ConvertString.StringToBitSet(binary);
		bin = DES.permute(bin, DES.PC1_Map);
		String x;
		for(int i=0; i<64; i++){
		
			if(bin.get(i) == true) x = textfields[2].getText() + "0";
			else x = textfields[2].getText() + "1";
			textfields[2].setText(x);
		}
	}

	private void leftShift(JTextField tf1, JTextField tf2,boolean doubleShift){
		String binary = tf1.getText();
		BitSet bin = ConvertString.StringToBitSet(binary);
		bin = DES.permute(bin, DES.LS_Map);	
		if(doubleShift) bin = DES.permute(bin, DES.LS_Map);
		String x;
		for(int i=0; i<64; i++){	
			if(bin.get(i) == true) x = tf2.getText() + "0";
			else x = tf2.getText() + "1";
			tf2.setText(x);
		}
	}


	public void actionPerformed(ActionEvent evt){
		boolean doubleShift = false;
		Object source = evt.getSource();
		if(source == buttons[0]) convertKey();
		if(source == buttons[1]) funcPC1();
			for(int i = 2; i <19; i++)
			{
				if(source == buttons[i]){
					if(i == 2 || i ==3 || i == 10 || i == 17) doubleShift = false;
					else doubleShift = true;
					leftShift(textfields[i], textfields[i+1], doubleShift);
				}
			}
		}
	
}
