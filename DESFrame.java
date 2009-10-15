
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;

public class DESFrame extends JInternalFrame implements ActionListener{


	JTextArea plainTextArea, cypherTextArea;

	private JScrollPane jScrollPane1, jScrollPane2;

	int openFrameCount = 0;

	final int xOffset = 30, yOffset = 30;

	protected JButton b1;

	String line, binaryLine;

	BitSet bits, IPbits, keyBits, DESbits;

	InputFrame IF;

	KeyFrame KF;


	    public DESFrame(InputFrame incomingIF, KeyFrame incomingKF) {
		super("DES", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(700,200);

		this.IF = incomingIF;

		this.KF = incomingKF;

		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

		createGUI();
	    }

	
	private void createGUI(){
		JPanel panel = new JPanel(new BorderLayout(5,5));
		panel.setBackground(Color.lightGray);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(700, 200));
		panel.setLocation(0, 0);
		this.setContentPane(panel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setVisible(true);	
		panel.add(buttonPanel, BorderLayout.PAGE_START);

		
		plainTextArea = new JTextArea();
		plainTextArea.setColumns(20);
		plainTextArea.setLineWrap(true);
		plainTextArea.setRows(5);
		plainTextArea.setWrapStyleWord(true);
		plainTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		plainTextArea.setEditable(true);
		plainTextArea.setPreferredSize(new Dimension(150, 20));
		//plainTextArea.append(InputFrame.text);
		jScrollPane1 = new JScrollPane(plainTextArea); 


		
		cypherTextArea = new JTextArea();
		cypherTextArea.setColumns(20);
		cypherTextArea.setLineWrap(true);
		cypherTextArea.setRows(5);
		cypherTextArea.setWrapStyleWord(true);
		cypherTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		cypherTextArea.setEditable(true);
		cypherTextArea.setPreferredSize(new Dimension(150, 20));
		jScrollPane2 = new JScrollPane(cypherTextArea); 

		b1 = new JButton("DES");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b1.setMnemonic(KeyEvent.VK_D);
		b1.setEnabled(true);
		b1.addActionListener(this);
		buttonPanel.add(b1);

		panel.add(plainTextArea, BorderLayout.LINE_START);
		panel.add(cypherTextArea, BorderLayout.LINE_END);
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();
		if(source == b1) doDES();
		
	}

	private void doDES(){
		String binary = IF.binaryTextArea.getText();
		bits = ConvertString.stringToBinary(binary);
		//plainTextArea.append(binary);
		plainTextArea.append(IF.plainTextArea.getText());
		keyBits = KF.keyBits;
		DESbits = DES.encrypt(bits, keyBits);

		String cyphertext = ConvertString.BitSetToString(DESbits);
		cypherTextArea.append(cyphertext);
		/*
		for(int i=0; i<64; i++){
			if(DESbits.get(i) == true) cypherTextArea.append("1");
			else cypherTextArea.append("0");
		}*/
	}

}
