
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;

public class KeyFrame extends JInternalFrame implements ActionListener{

	int openFrameCount = 2;

	final int xOffset = 30, yOffset = 30;

	JTextArea keyTextArea, keyTextArea2, middleTextArea;

	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;

	protected JButton b1,b2, b3;

	BitSet bits, keyBits;

	String key;

	private final static String newline = "\n";

	    public KeyFrame() {
		super("Make Key", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(700,200);

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

		JPanel textPanel = new JPanel(new BorderLayout(5,50));
		//textPanel.setSize(500,200);
		textPanel.setVisible(true);
		panel.add(textPanel, BorderLayout.CENTER);	

		keyTextArea = new JTextArea();
		keyTextArea.setColumns(20);
		keyTextArea.setLineWrap(true);
		keyTextArea.setRows(5);
		keyTextArea.setWrapStyleWord(true);
		keyTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		keyTextArea.setEditable(true);
		keyTextArea.setPreferredSize(new Dimension(150, 20));
		jScrollPane1 = new JScrollPane(keyTextArea); 
		jScrollPane1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		middleTextArea = new JTextArea();
		middleTextArea.setColumns(20);
		middleTextArea.setLineWrap(true);
		middleTextArea.setRows(5);
		middleTextArea.setWrapStyleWord(true);
		middleTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		middleTextArea.setEditable(true);
		middleTextArea.setPreferredSize(new Dimension(150, 20));
		jScrollPane3 = new JScrollPane(middleTextArea); 
		jScrollPane3.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		keyTextArea2 = new JTextArea();
		keyTextArea2.setColumns(20);
		keyTextArea2.setLineWrap(true);
		keyTextArea2.setRows(5);
		keyTextArea2.setWrapStyleWord(true);
		keyTextArea2.setBorder(BorderFactory.createLineBorder(Color.black));
		keyTextArea2.setEditable(true);
		keyTextArea2.setPreferredSize(new Dimension(150, 20));
		jScrollPane2 = new JScrollPane(keyTextArea2); 
		jScrollPane2.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


		textPanel.add(jScrollPane1, BorderLayout.LINE_START);
		textPanel.add(jScrollPane3, BorderLayout.CENTER);
		textPanel.add(jScrollPane2, BorderLayout.LINE_END);
	}

	private void clearText(){
		keyTextArea.setText("");
		middleTextArea.setText("");
		keyTextArea2.setText("");
	}

	
	private void convertKey(){
		key = keyTextArea.getText();
		String block;
		int length;
		if(key.length() < 8) length = key.length();
		else length = 8;
		for(int i = 0; i < length; i++){
			middleTextArea.append(key.charAt(i) + " : ");
			block = ConvertString.charToAscii(key.charAt(i));
			middleTextArea.append(block + newline);
			
		}

		keyBits = ConvertString.stringToBinary(ConvertString.stringToAscii(key));
		for(int i=0; i<64; i++){
			if(keyBits.get(i) == true) keyTextArea2.append("0");
			else keyTextArea2.append("1");
		}
				
	}


	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();
		if(source == b1) convertKey();
		if(source == b3) clearText();
		
	}
}
