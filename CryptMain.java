
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import  java.io.*;


class CryptMain extends JFrame implements ActionListener
{

	File input;
	JTextArea plainTextArea;
	JTextArea binaryTextArea;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	JPanel panel;
	private final static String newline = "\n";
	final JFileChooser fc = new JFileChooser();
	protected JButton b1, b2, b3;


	String line;
	String binaryLine;

	public CryptMain(){

		setTitle("CryptoViz");	
		
		//Create a file chooser
		


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

		
		panel = new JPanel();
		panel.setSize(200,200);
		panel.setBackground(Color.gray);
		panel.setVisible(true);

/* THIS DOESN'T WORK LOLOL
		   GroupLayout layout = new GroupLayout(panel);
		   panel.setLayout(layout);
		 
		   // Turn on automatically adding gaps between components
		   layout.setAutoCreateGaps(true);
		 
		   // Turn on automatically creating gaps between components that touch
		   // the edge of the container and the container.
		   layout.setAutoCreateContainerGaps(true);
		 
		   // Create a sequential group for the horizontal axis.
		 
		   GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		 
		   // The sequential group in turn contains two parallel groups.
		   // One parallel group contains the labels, the other the text fields.
		   // Putting the labels in a parallel group along the horizontal axis
		   // positions them at the same x location.
		   //
		   // Variable indentation is used to reinforce the level of grouping.
		   hGroup.addGroup(layout.createParallelGroup().
			    addComponent(b1).addComponent(b2));
		   hGroup.addGroup(layout.createParallelGroup().
			    addComponent(plainTextArea).addComponent(binaryTextArea));
		   layout.setHorizontalGroup(hGroup);
		   
		   // Create a sequential group for the vertical axis.
		   GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		 
		   // The sequential group contains two parallel groups that align
		   // the contents along the baseline. The first parallel group contains
		   // the first label and text field, and the second parallel group contains
		   // the second label and text field. By using a sequential group
		   // the labels and text fields are positioned vertically after one another.
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			    addComponent(b1).addComponent(plainTextArea));
		   vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			    addComponent(b2).addComponent(binaryTextArea));
		   layout.setVerticalGroup(vGroup);
*/

		b1 = new JButton("Input Plain Text");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b1.setMnemonic(KeyEvent.VK_D);
		b1.setEnabled(true);
		b1.addActionListener(this);
		panel.add(b1);

		b2 = new JButton("Convert to Binary");
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b2.setMnemonic(KeyEvent.VK_D);
		b2.addActionListener(this);
		b2.setEnabled(false);
		panel.add(b2);

		


		plainTextArea = new JTextArea();
		plainTextArea.setColumns(20);
		plainTextArea.setLineWrap(true);
		plainTextArea.setRows(10);
		plainTextArea.setWrapStyleWord(true);
		jScrollPane1 = new JScrollPane(plainTextArea); 
		plainTextArea.setEditable(true);

		binaryTextArea = new JTextArea();
		binaryTextArea.setColumns(20);
		binaryTextArea.setLineWrap(true);
		binaryTextArea.setRows(10);
		binaryTextArea.setWrapStyleWord(true);
		jScrollPane2 = new JScrollPane(binaryTextArea); 
		binaryTextArea.setEditable(true);

		//panel.add(plainTextArea);
		//panel.add(binaryTextArea);

		add(panel);

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
	        crypt.setSize(1000,1000);

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
