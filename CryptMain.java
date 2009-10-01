
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import  java.io.*;


class CryptMain extends JFrame
{

	File input;
	JTextArea plainTextArea;
	JTextArea binaryTextArea;
	JPanel panel;
	private final static String newline = "\n";
	String line;
	String binaryLine;

	public CryptMain(){

		setTitle("CryptoViz");	
		
		//Create a file chooser
		final JFileChooser fc = new JFileChooser();


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

		// Add a listener to the New menu item. actionPerformed() method will
		// invoked, if user triggred this menu item
		newAction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        System.out.println("You have clicked on the new action");
		    }
		});

		openAction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        int returnVal = fc.showOpenDialog(CryptMain.this);

			    //Process the results.
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			    input = fc.getSelectedFile();
			} 

			readFile(input);
			//Reset the file chooser for the next time it's shown.
			fc.setSelectedFile(null);
		    }
		});

		panel = new JPanel();
		panel.setSize(200,200);
		panel.setBackground(Color.black);
		panel.setVisible(true);

		

		plainTextArea = new JTextArea(40, 40);
		JScrollPane scrollPane = new JScrollPane(plainTextArea); 
		plainTextArea.setEditable(true);

		binaryTextArea = new JTextArea(40, 40);
		JScrollPane scrollPane2 = new JScrollPane(binaryTextArea); 
		binaryTextArea.setEditable(true);

		panel.add(plainTextArea);
		panel.add(binaryTextArea);

		add(panel);

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
			binaryLine = ConvertString.stringToBinary(line);
			plainTextArea.append(line + newline);
			binaryTextArea.append(binaryLine + newline);
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
	        crypt.setSize(600,800);

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
