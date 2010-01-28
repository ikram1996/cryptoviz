import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import  java.io.*;

import javax.swing.ImageIcon;



public class AboutFrame extends JInternalFrame implements ActionListener {

	public JTextArea plainTextArea, binaryTextArea, middleTextArea;
	private final static String newline = "\n";
	protected JButton closeButton;


	    public AboutFrame() {
		super("About ", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(350,650);

		setLocation(50, 50);

			JPanel panel = new JPanel(new BorderLayout(5,5));
			panel.setBackground(Color.lightGray);
			panel.setVisible(true);		
			panel.setLocation(0, 0);
			this.setContentPane(panel);


			closeButton = new JButton("OK");
			closeButton.setSize(70,30);
			closeButton.setMnemonic(KeyEvent.VK_ENTER);
			closeButton.addActionListener(this);
			closeButton.setEnabled(true);
			panel.add(closeButton);
			

			ImageIcon pic = new ImageIcon("imgs/left.gif");
			panel.add(new javax.swing.JLabel(pic));
			
			
			plainTextArea = new JTextArea();
			plainTextArea.setColumns(20);
			plainTextArea.setLineWrap(true);
			plainTextArea.setRows(10);
			plainTextArea.setWrapStyleWord(true);
			plainTextArea.setBorder(BorderFactory.createLineBorder(Color.black));		
			plainTextArea.setEditable(true);

			binaryTextArea = new JTextArea();
			binaryTextArea.setColumns(20);
			binaryTextArea.setLineWrap(true);
			binaryTextArea.setRows(10);
			binaryTextArea.setWrapStyleWord(true);
			binaryTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
			binaryTextArea.setEditable(true);


			middleTextArea = new JTextArea();
			middleTextArea.setColumns(20);
			middleTextArea.setLineWrap(true);
			middleTextArea.setRows(10);
			middleTextArea.setWrapStyleWord(true);
			middleTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
			middleTextArea.setEditable(true);
			

	
		}

		public void actionPerformed(ActionEvent evt){

			if( evt.getSource() == closeButton) this.dispose();
		}


	
	}
