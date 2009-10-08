
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


	    public DESFrame() {
		super("DES", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(600,600);

		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

		createGUI();
	    }

	
	private void createGUI(){
		JPanel panel = new JPanel(new BorderLayout(5,5));
		panel.setBackground(Color.lightGray);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(600, 600));
		panel.setLocation(0, 0);
		this.setContentPane(panel);

		
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

		panel.add(plainTextArea, BorderLayout.LINE_START);
		panel.add(cypherTextArea, BorderLayout.LINE_END);
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();
		//if(source == b1) convertKey();
		
	}

}
