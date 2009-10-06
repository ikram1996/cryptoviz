
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;


class CryptMain extends JFrame implements ActionListener
{

	File input;

	JTextArea plainTextArea, binaryTextArea, IPtextArea;

	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3, mainScroll;

	JPanel panel, panel2, panel3, panel4, panel5;

	private final static String newline = "\n";

	final JFileChooser fc = new JFileChooser();

	protected JButton b1, b2, b3, b4;

	JMenuItem newAction, openAction, exitAction, cutAction, copyAction, pasteAction, IPAction;

	JTabbedPane tabbedPane;

	String line, binaryLine;

	IPvisualization IP;

	BitSet IPbits;

	//Constructor
	public CryptMain(){

		setTitle("CryptoViz");	
	
		createMenuBar();
		
		createPanels();

		createTabbedPane();

		createButtons();
	
		createTextAreas();

		createLayout();
		
	}

	private void createMenuBar(){
		// Creates a menubar for a JFrame
		JMenuBar menuBar = new JMenuBar();
		
		// Add the menubar to the frame
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		//JMenu visualizationMenu = new JMenu("Visualizations");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		//menuBar.add(visualizationMenu);
		

		newAction = new JMenuItem("New");
		openAction = new JMenuItem("Open");
		exitAction = new JMenuItem("Exit");
		cutAction = new JMenuItem("Cut");
		copyAction = new JMenuItem("Copy");
		pasteAction = new JMenuItem("Paste");
		//IPAction = new JMenuItem("Initial Permutation");
		
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
		//visualizationMenu.add(IPAction);

		newAction.addActionListener(this);
		//IPAction.addActionListener(this);


	}

	private void createPanels(){
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setVisible(true);		
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800, 800));
	        panel.setLocation(0, 0);
		//this.setContentPane(panel);

		panel2 = new JPanel();
		panel2.setSize(600, 600);
		panel2.setPreferredSize(new Dimension(600, 800));
		panel2.setBackground(Color.white);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel2.setVisible(true);
		panel2.setLocation(10,10);

		panel.add(panel2);

		mainScroll = new JScrollPane(panel);
		mainScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainScroll.setSize(1000,800);
	    
    		add(panel);

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
		
	}

	private void createTabbedPane(){
		tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Binary", panel);
		//tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		//tabbedPane.addTab("Tab 2", panel3);
		//tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		add(tabbedPane);
	}

	private void createButtons(){
		b1 = new JButton("Input Plain Text");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b1.setMnemonic(KeyEvent.VK_D);
		b1.setEnabled(true);
		b1.addActionListener(this);
		//panel.add(b1);

		b2 = new JButton("Convert to Binary");
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b2.setMnemonic(KeyEvent.VK_D);
		b2.addActionListener(this);
		b2.setEnabled(true);
		//panel.add(b2);

		b3 = new JButton("Initial Permutation");
		b3.setVerticalTextPosition(AbstractButton.CENTER);
		b3.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b3.setMnemonic(KeyEvent.VK_D);
		b3.addActionListener(this);
		b3.setEnabled(false);

		b4 = new JButton("Visualize IP");
		b4.addActionListener(this);
		b4.setEnabled(false);
		b4.setLayout(null);
		b4.setLocation(10, 10);

	}

	private void createTextAreas(){

		

		plainTextArea = new JTextArea();
		plainTextArea.setColumns(30);
		plainTextArea.setLineWrap(true);
		plainTextArea.setRows(10);
		plainTextArea.setWrapStyleWord(true);
		plainTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		jScrollPane1 = new JScrollPane(plainTextArea); 
		plainTextArea.setEditable(true);
		
		jScrollPane1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setPreferredSize(new Dimension(150, 50));

		binaryTextArea = new JTextArea();
		binaryTextArea.setColumns(30);
		binaryTextArea.setLineWrap(true);
		binaryTextArea.setRows(10);
		binaryTextArea.setWrapStyleWord(true);
		binaryTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		binaryTextArea.setEditable(true);
		
		jScrollPane2 = new JScrollPane(binaryTextArea); 
		jScrollPane2.setPreferredSize(new Dimension(150, 20));
		jScrollPane2.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		

		IPtextArea = new JTextArea();
		IPtextArea.setColumns(30);
		IPtextArea.setLineWrap(true);
		IPtextArea.setRows(10);
		IPtextArea.setWrapStyleWord(true);
		IPtextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		IPtextArea.setEditable(true);
		IPtextArea.setPreferredSize(new Dimension(150, 20));
		jScrollPane3 = new JScrollPane(IPtextArea); 
		jScrollPane3.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	}

	private void createLayout(){
		GroupLayout layout = new GroupLayout(panel2);
		panel2.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.linkSize(jScrollPane1, jScrollPane2, jScrollPane3);
		//layout.linkSize(SwingConstants.VERTICAL, jScrollPane1, jScrollPane2, jScrollPane3);
		// omg layouts are confusing lol / this kinda works

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(b1)
				.addComponent(b2)
				.addComponent(b3)
				.addComponent(b4))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addComponent(jScrollPane1)
			    .addComponent(jScrollPane2)
        			.addComponent(jScrollPane3))		    
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b1)
			.addComponent(jScrollPane1))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(b2)
			.addComponent(jScrollPane2))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(b3)
			.addComponent(jScrollPane3))
		    .addComponent(b4)
		);
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();

		if(source == b1) getPlainText();
		if(source == b2) outputBinary();
		if(source == b3) outputIP();
		if(source == newAction) System.out.println("new");
		if(source == b4) IPvisualization();

	}

	private void IPvisualization(){
		IP = new IPvisualization();
		IP.setBinary(binaryLine);
		IP.setIP(IPbits);
		panel5.add(IP);
		tabbedPane.addTab("IP", panel4);
	}

	private void getPlainText(){
		int returnVal = fc.showOpenDialog(CryptMain.this);

			    //Process the results.
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			    input = fc.getSelectedFile();
			} 

			readFile(input);
			//Reset the file chooser for the next time it's shown.
			fc.setSelectedFile(null);
		
		//b2.setEnabled(true);
	}

	private void outputBinary(){
	
		String plain = plainTextArea.getText();
		binaryLine = ConvertString.stringToBinary(plain);
		binaryTextArea.append(binaryLine + newline);		
		b3.setEnabled(true);
		
	}

	private void outputIP(){

		String binary = binaryTextArea.getText();
		BitSet bits = ConvertString.asciiToBinary(binary);
		IPbits = DES.permute(bits, DES.IP_Map);
		for(int i=0; i<64; i++){
			if(bits.get(i) == true) IPtextArea.append("0");
			else IPtextArea.append("1");
		}
		b4.setEnabled(true);
	}

	private void readFile(File input){

		 File file = input;

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		    DataInputStream dis = null;

		    try {
		      fis = new FileInputStream(file);

		      // Here BufferedInputStream is added for fast reading.
		      bis = new BufferedInputStream(fis);
		      dis = new DataInputStream(bis);

		      // dis.available() returns 0 if the file does not have more lines.
		      while (dis.available() != 0) {

		      // this statement reads the line from the file and print it to
			// the console.
			line = dis.readLine();		
			plainTextArea.append(line + newline);
		      }

		      // dispose all the resources after using them.
		      fis.close();
		      bis.close();
		      dis.close();

		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
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
