
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;


class CryptMain extends JFrame implements ActionListener
{

	File input;

	JTextArea plainTextArea, binaryTextArea, IPtextArea, keyTextArea, keyTextArea2, DESplainTextArea, DEScypherTextArea;

	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3, jScrollPane4,jScrollPane5,jScrollPane6, jScrollPane7, mainScroll;

	JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7;

	private final static String newline = "\n";

	final JFileChooser fc = new JFileChooser();

	protected JButton b1, b2, b3, b4, b5, b6;

	JMenuItem newAction, openAction, exitAction, cutAction, copyAction, pasteAction, IPAction, KeyAction, DESAction;

	JTabbedPane tabbedPane;

	String line, binaryLine;

	IPvisualization IP;

	BitSet bits, IPbits, keyBits, DESbits;

	String key;

	static JDesktopPane desktop;

	//Constructor
	public CryptMain(){

		setTitle("CryptoViz");	

		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		createFrame(new InputFrame()); //create first "window"
		setContentPane(desktop);
		//setJMenuBar(createMenuBar());

	
		createMenuBar();
		
		//createPanels();

		//createTabbedPane();

		//createButtons();
	
		//createTextAreas();

		//createLayout();
		
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
		DESMenu.add(KeyAction);
		DESMenu.add(DESAction);
		

		newAction.addActionListener(this);
		DESAction.addActionListener(this);
		KeyAction.addActionListener(this);


	}

	private void createPanels(){

	    
    		//add(panel);

		panel3 = new JPanel();	
		panel3.setBackground(Color.lightGray);				
		
		panel4 = new JPanel();
		panel4.setBackground(Color.lightGray);
		panel4.setVisible(true);
		panel4.setLayout(null);
		panel4.setLocation(0,0);

		panel5 = new JPanel();
		panel5.setSize(900, 300);
		panel5.setLocation(10, 10);
		panel5.setBorder(BorderFactory.createLineBorder(Color.black));
		panel5.setBackground(Color.white);
		panel5.setLayout(null);
		panel5.setVisible(true);
		panel4.add(panel5);

		panel6 = new JPanel();
		panel6.setBackground(Color.lightGray);
		panel6.setVisible(true);
		panel6.setLayout(null);
		panel6.setLocation(0,0);

		panel7 = new JPanel();
		panel7.setSize(900, 500);
		panel7.setLocation(10, 10);
		panel7.setBorder(BorderFactory.createLineBorder(Color.black));
		panel7.setBackground(Color.white);
		panel7.setLayout(null);
		panel7.setVisible(true);
		panel6.add(panel7);

				
		
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();

		if(source == DESAction) createFrame(new DESFrame());
		if(source == KeyAction) createFrame(new KeyFrame());

	}

	private void doDES(){
		String binary = binaryTextArea.getText();
		bits = ConvertString.asciiToBinary(binary);
		DESbits = DES.encrypt(bits, keyBits);
		for(int i=0; i<64; i++){
			if(DESbits.get(i) == true) DEScypherTextArea.append("0");
			else DEScypherTextArea.append("1");
		}
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
