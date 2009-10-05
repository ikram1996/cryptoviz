
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import  java.io.*;


class CryptMain extends JFrame implements ActionListener
{

	File input;

	JTextArea plainTextArea, binaryTextArea;

	private JScrollPane jScrollPane1, jScrollPane2;

	JPanel panel, panel2, panel3;

	private final static String newline = "\n";

	final JFileChooser fc = new JFileChooser();

	protected JButton b1, b2, b3;

	JTabbedPane tabbedPane;

	String line, binaryLine;

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

	public void createMenuBar(){
		// Creates a menubar for a JFrame
		JMenuBar menuBar = new JMenuBar();
		
		// Add the menubar to the frame
		setJMenuBar(menuBar);
		
		// Define and add two drop down menu to the menubar
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		// Create and add simple menu item to one of the drop down menu
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem cutAction = new JMenuItem("Cut");
		JMenuItem copyAction = new JMenuItem("Copy");
		JMenuItem pasteAction = new JMenuItem("Paste");
		
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


		/*
		// Add a listener to the New menu item. actionPerformed() method will
		// invoked, if user triggred this menu item
		newAction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        System.out.println("You have clicked on the new action");
		    }
		});

		openAction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        
		    }
		});
		*/
	}

	public void createPanels(){
		panel = new JPanel();
		//panel.setSize(800,100);
		panel.setBackground(Color.lightGray);
		panel.setVisible(true);		
		panel.setLayout(null);
	        panel.setLocation(0, 0);
		//this.setContentPane(panel);
		add(panel);

		panel2 = new JPanel();
		panel2.setSize(800, 300);
		panel2.setBackground(Color.white);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel2.setVisible(true);
		//panel2.setLayout(null);
		panel2.setLocation(10,10);
		panel.add(panel2);

		panel3 = new JPanel();
	}

	public void createTabbedPane(){
		tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Binary", panel);
		//tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("Tab 2", panel3);
		//tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		add(tabbedPane);
	}

	public void createButtons(){
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
		b2.setEnabled(false);
		//panel.add(b2);
	}

	public void createTextAreas(){
		plainTextArea = new JTextArea();
		plainTextArea.setColumns(10);
		plainTextArea.setLineWrap(true);
		plainTextArea.setRows(10);
		plainTextArea.setWrapStyleWord(true);
		plainTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		jScrollPane1 = new JScrollPane(plainTextArea); 
		plainTextArea.setEditable(true);
		
		jScrollPane1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setPreferredSize(new Dimension(250, 250));

		binaryTextArea = new JTextArea();
		binaryTextArea.setColumns(10);
		binaryTextArea.setLineWrap(true);
		binaryTextArea.setRows(10);
		binaryTextArea.setWrapStyleWord(true);
		binaryTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		jScrollPane2 = new JScrollPane(binaryTextArea); 
		binaryTextArea.setEditable(true);
	}

	public void createLayout(){
		GroupLayout layout = new GroupLayout(panel2);
		panel2.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// omg layouts are confusing lol / this kinda works

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(b1)
				.addComponent(b2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addComponent(jScrollPane1)
			    .addComponent(jScrollPane2))		    
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(b1)
			.addComponent(jScrollPane1))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(b2)
			.addComponent(jScrollPane2))
		);
	}

	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();

		if(source == b1) getPlainText();
		if(source == b2) outputBinary();

	}

	public void getPlainText(){
		int returnVal = fc.showOpenDialog(CryptMain.this);

			    //Process the results.
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			    input = fc.getSelectedFile();
			} 

			readFile(input);
			//Reset the file chooser for the next time it's shown.
			fc.setSelectedFile(null);
		
		b2.setEnabled(true);
	}

	public void outputBinary(){
	
		String plain = plainTextArea.getText();
		binaryLine = ConvertString.stringToBinary(line);
		binaryTextArea.append(binaryLine + newline);
		
	}

	public void readFile(File input){

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
