
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;

public class KeyFrame extends JInternalFrame implements ActionListener{

	int openFrameCount = 2;

	final int xOffset = 30, yOffset = 30;

	JTextField keyTextField, keyTextField2, keyTextField3, keyTextField4,keyTextField5,keyTextField6,keyTextField7,keyTextField8,
	keyTextField9,keyTextField10,keyTextField11,keyTextField12,keyTextField13,keyTextField14,keyTextField15,keyTextField16,keyTextField17,
	keyTextField18,keyTextField19;

	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;

	protected JButton b1,b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;

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
		


		//left
		JLabel entertxt = new JLabel("Enter 8 char key: ");
		b2 = new JButton("To Binary");
		b2.addActionListener(this);
		b2.setEnabled(true);
		b2.setLocation(0,0);
		keyTextField = new JTextField();
		keyTextField.setColumns(20);
		keyTextField.setEditable(true);
		keyTextField2 = new JTextField();
		keyTextField2.setColumns(20);
		keyTextField2.setEditable(true);
		b3 = new JButton("PC1");
		b3.addActionListener(this);
		b3.setEnabled(true);
		keyTextField3 = new JTextField();
		keyTextField3.setColumns(20);
		keyTextField3.setEditable(true);
		b4 = new JButton("Left Shift");
		b4.addActionListener(this);
		b4.setEnabled(true);
		b5 = new JButton("Left Shift");
		b5.addActionListener(this);
		b5.setEnabled(true);
		b6 = new JButton("Double Left Shift");
		b6.addActionListener(this);
		b6.setEnabled(true);
		b7 = new JButton("Double Left Shift");
		b7.addActionListener(this);
		b7.setEnabled(true);
		b8 = new JButton("Double Left Shift");
		b8.addActionListener(this);
		b8.setEnabled(true);
		b9 = new JButton("Double Left Shift");
		b9.addActionListener(this);
		b9.setEnabled(true);
		b10 = new JButton("Double Left Shift");
		b10.addActionListener(this);
		b10.setEnabled(true);
		b11 = new JButton("Double Left Shift");
		b11.addActionListener(this);
		b11.setEnabled(true);
		b12 = new JButton("Left Shift");
		b12.addActionListener(this);
		b12.setEnabled(true);
		b13 = new JButton("Double Left Shift");
		b13.addActionListener(this);
		b13.setEnabled(true);
		b14 = new JButton("Double Left Shift");
		b14.addActionListener(this);
		b14.setEnabled(true);
		b15 = new JButton("Double Left Shift");
		b15.addActionListener(this);
		b15.setEnabled(true);
		b16 = new JButton("Double Left Shift");
		b16.addActionListener(this);
		b16.setEnabled(true);
		b17 = new JButton("Double Left Shift");
		b17.addActionListener(this);
		b17.setEnabled(true);
		b18 = new JButton("Double Left Shift");
		b18.addActionListener(this);
		b18.setEnabled(true);
		b19 = new JButton("Left Shift");
		b19.addActionListener(this);
		b19.setEnabled(true);
		keyTextField4 = new JTextField();
		keyTextField4.setColumns(20);
		keyTextField4.setEditable(true);
		keyTextField5 = new JTextField();
		keyTextField5.setColumns(20);
		keyTextField5.setEditable(true);
		keyTextField6 = new JTextField();
		keyTextField6.setColumns(20);
		keyTextField6.setEditable(true);
		keyTextField7 = new JTextField();
		keyTextField7.setColumns(20);
		keyTextField7.setEditable(true);
		keyTextField8 = new JTextField();
		keyTextField8.setColumns(20);
		keyTextField8.setEditable(true);
		keyTextField9 = new JTextField();
		keyTextField9.setColumns(20);
		keyTextField9.setEditable(true);
		keyTextField10 = new JTextField();
		keyTextField10.setColumns(20);
		keyTextField10.setEditable(true);
		keyTextField11 = new JTextField();
		keyTextField11.setColumns(20);
		keyTextField11.setEditable(true);
		keyTextField12 = new JTextField();
		keyTextField12.setColumns(20);
		keyTextField12.setEditable(true);
		keyTextField13 = new JTextField();
		keyTextField13.setColumns(20);
		keyTextField13.setEditable(true);
		keyTextField14 = new JTextField();
		keyTextField14.setColumns(20);
		keyTextField14.setEditable(true);
		keyTextField15 = new JTextField();
		keyTextField15.setColumns(20);
		keyTextField15.setEditable(true);
		keyTextField16 = new JTextField();
		keyTextField16.setColumns(20);
		keyTextField16.setEditable(true);
		keyTextField17 = new JTextField();
		keyTextField17.setColumns(20);
		keyTextField17.setEditable(true);
		keyTextField18 = new JTextField();
		keyTextField18.setColumns(20);
		keyTextField18.setEditable(true);
		keyTextField19 = new JTextField();
		keyTextField19.setColumns(20);
		keyTextField19.setEditable(true);

	
		//make layout for left panel
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(entertxt)
			.addComponent(b2)
			.addComponent(b3)
			.addComponent(b4)
			.addComponent(b5)
			.addComponent(b6)
			.addComponent(b7)
			.addComponent(b8)
			.addComponent(b9)
			.addComponent(b10)
			.addComponent(b11)
			.addComponent(b12)
			.addComponent(b13)
			.addComponent(b14)
			.addComponent(b15)
			.addComponent(b16)
			.addComponent(b17)
			.addComponent(b18)
			.addComponent(b19))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(keyTextField)
			.addComponent(keyTextField2)
			.addComponent(keyTextField3)
			.addComponent(keyTextField3)
			.addComponent(keyTextField4)
			.addComponent(keyTextField5)
			.addComponent(keyTextField6)
			.addComponent(keyTextField7)
			.addComponent(keyTextField8)
			.addComponent(keyTextField9)
			.addComponent(keyTextField10)
			.addComponent(keyTextField11)
			.addComponent(keyTextField12)
			.addComponent(keyTextField13)
			.addComponent(keyTextField14)
			.addComponent(keyTextField15)
			.addComponent(keyTextField16)
			.addComponent(keyTextField17)
			.addComponent(keyTextField18)
			.addComponent(keyTextField19))
		);

		layout.linkSize(SwingConstants.HORIZONTAL, b2, b3);


		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(entertxt)
			.addComponent(keyTextField))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b2)
			.addComponent(keyTextField2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b3)
			.addComponent(keyTextField3))
		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b4)
			.addComponent(keyTextField4))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b5)
			.addComponent(keyTextField5))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b6)
			.addComponent(keyTextField6))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b7)
			.addComponent(keyTextField7))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b8)
			.addComponent(keyTextField8))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b9)
			.addComponent(keyTextField9))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b10)
			.addComponent(keyTextField10))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b11)
			.addComponent(keyTextField11))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b12)
			.addComponent(keyTextField12))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b13)
			.addComponent(keyTextField13))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b14)
			.addComponent(keyTextField14))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b15)
			.addComponent(keyTextField15))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b16)
			.addComponent(keyTextField16))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b17)
			.addComponent(keyTextField17))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b18)
			.addComponent(keyTextField18))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b19)
			.addComponent(keyTextField19))
			
		);


		panel.add(leftPanel, BorderLayout.CENTER);
		//panel.add(rightPanel, BorderLayout.LINE_END);	
		
	}

	private void clearText(){
		keyTextField.setText("");
		keyTextField2.setText("");
	}

	
	private void convertKey(){
		key = keyTextField.getText();
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
		
			if(keyBits.get(i) == true) x = keyTextField2.getText() + "0";
			else x = keyTextField2.getText() + "1";
			keyTextField2.setText(x);
		}
				
	}

	private void funcPC1(){
		String binary = keyTextField2.getText();
		BitSet bin = ConvertString.StringToBitSet(binary);
		bin = DES.permute(bin, DES.PC1_Map);
		String x;
		for(int i=0; i<64; i++){
		
			if(bin.get(i) == true) x = keyTextField3.getText() + "0";
			else x = keyTextField3.getText() + "1";
			keyTextField3.setText(x);
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

		Object source = evt.getSource();
		if(source == b2) convertKey();
		if(source == b3) funcPC1();
		if(source == b4) leftShift(keyTextField3, keyTextField4, false);
		if(source == b5) leftShift(keyTextField4, keyTextField5, false);
		if(source == b6) leftShift(keyTextField5, keyTextField6, true);
		if(source == b7) leftShift(keyTextField6, keyTextField7, true);
		if(source == b8) leftShift(keyTextField7, keyTextField8, true);
		if(source == b9) leftShift(keyTextField8, keyTextField9, true);
		if(source == b10) leftShift(keyTextField9, keyTextField10, true);
		if(source == b11) leftShift(keyTextField10, keyTextField11, true);
		if(source == b12) leftShift(keyTextField11, keyTextField12, false);
		if(source == b13) leftShift(keyTextField12, keyTextField13, true);
		if(source == b14) leftShift(keyTextField13, keyTextField14, true);
		if(source == b15) leftShift(keyTextField14, keyTextField15, true);
		if(source == b16) leftShift(keyTextField15, keyTextField16, true);
		if(source == b17) leftShift(keyTextField16, keyTextField17, true);
		if(source == b18) leftShift(keyTextField17, keyTextField18, true);
		if(source == b19) leftShift(keyTextField18, keyTextField19, false);
		
	}
}
