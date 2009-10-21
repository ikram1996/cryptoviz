
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;


class CryptMain extends JFrame implements ActionListener
{

	JMenuItem newAction, openAction, exitAction, cutAction, copyAction, pasteAction, IPAction, KeyAction, DESAction, inputAction;

	static JDesktopPane desktop;

	InputFrame IF = new InputFrame();
	KeyFrame KF = new KeyFrame();

	//Constructor
	public CryptMain(){

		setTitle("CryptoViz");	

		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		desktop.setBackground(Color.lightGray);
		//createFrame(new InputFrame()); //create first "window"
		setContentPane(desktop);
		//setJMenuBar(createMenuBar());

		createMenuBar();	
	}

	//Create a new internal frame.
    	protected static void createFrame(JInternalFrame frame) {
		frame.setVisible(true); //necessary as of 1.3
		desktop.add(frame);
		try {
		    frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
    	}

	private void createMenuBar(){
		// Creates a menubar for a JFrame
		JMenuBar menuBar = new JMenuBar();
		
		// Add the menubar to the frame
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu DESMenu = new JMenu("DES");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(DESMenu);
		

		newAction = new JMenuItem("New");
		openAction = new JMenuItem("Open");
		exitAction = new JMenuItem("Exit");
		cutAction = new JMenuItem("Cut");
		copyAction = new JMenuItem("Copy");
		pasteAction = new JMenuItem("Paste");
		inputAction = new JMenuItem("Input plain text");
		KeyAction = new JMenuItem("Make Key");
		DESAction = new JMenuItem("DES");
		
		// Create and add CheckButton as a menu item to one of the drop down
		// menu
		JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");

		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(checkAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		editMenu.addSeparator();
		editMenu.add(checkAction);
		DESMenu.add(inputAction);
		DESMenu.add(KeyAction);
		DESMenu.add(DESAction);
		
		inputAction.addActionListener(this);
		newAction.addActionListener(this);
		DESAction.addActionListener(this);
		KeyAction.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();

		if(source == inputAction) createFrame(IF);
		if(source == DESAction) createFrame(new DESFrame(IF, KF));
		if(source == KeyAction) createFrame(KF);
	}
		
	public static void main(String[] args) {
		CryptMain crypt = new CryptMain();
		//frame.setContentPane(menu.createContentPane());
        	crypt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        crypt.setSize(1000,800);

		//place frame in middle of screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - crypt.getWidth()) / 2;  
		int y = (screenSize.height - crypt.getHeight()) / 2;  
 		crypt.setLocation(x, y); 
		crypt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	crypt.setVisible(true);
	}
}
