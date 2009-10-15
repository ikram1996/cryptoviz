import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;


//NEED TO MAKE SURE USER INPUTS AT LEAST 8 PLAINTEXT CHARS


public class InputFrame extends JInternalFrame implements ActionListener{

	int openFrameCount = 0;

	final int xOffset = 30, yOffset = 30;

	File input;

	public JTextArea plainTextArea, binaryTextArea, IPtextArea;

	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;

	//JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7;

	private final static String newline = "\n";

	final JFileChooser fc = new JFileChooser();

	protected JButton b1, b2, b3, b4, b5, b6;




	public String binary, line, binaryLine, text;

	IPvisualization IP;

	BitSet bits, IPbits;

	String key;


	    public InputFrame() {
		super("Input", 
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

			JPanel textPanel = new JPanel(new BorderLayout(5,50));
			//textPanel.setSize(500,200);
			textPanel.setVisible(true);
			panel.add(textPanel, BorderLayout.CENTER);
	
			b1 = new JButton("Input Plain Text");
			b1.setVerticalTextPosition(AbstractButton.CENTER);
			b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
			b1.setMnemonic(KeyEvent.VK_D);
			b1.setEnabled(true);
			b1.addActionListener(this);
			buttonPanel.add(b1);


			b2 = new JButton("Convert to Binary");
			b2.setVerticalTextPosition(AbstractButton.CENTER);
			b2.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
			b2.setMnemonic(KeyEvent.VK_D);
			b2.addActionListener(this);
			b2.setEnabled(true);
			buttonPanel.add(b2);


			b3 = new JButton("Initial Permutation");
			b3.setVerticalTextPosition(AbstractButton.CENTER);
			b3.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
			b3.setMnemonic(KeyEvent.VK_D);
			b3.addActionListener(this);
			b3.setEnabled(false);
			buttonPanel.add(b3);


			b4 = new JButton("Visualize IP");
			b4.addActionListener(this);
			b4.setEnabled(false);
			b4.setSize(30,10);
			buttonPanel.add(b4);


			
			plainTextArea = new JTextArea();
			plainTextArea.setColumns(20);
			plainTextArea.setLineWrap(true);
			plainTextArea.setRows(10);
			plainTextArea.setWrapStyleWord(true);
			plainTextArea.setBorder(BorderFactory.createLineBorder(Color.black));		
			plainTextArea.setEditable(true);
		
			jScrollPane1 = new JScrollPane(plainTextArea); 
			jScrollPane1.setPreferredSize(new Dimension(200, 100));
	

			binaryTextArea = new JTextArea();
			binaryTextArea.setColumns(20);
			binaryTextArea.setLineWrap(true);
			binaryTextArea.setRows(10);
			binaryTextArea.setWrapStyleWord(true);
			binaryTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
			binaryTextArea.setEditable(true);
		
			jScrollPane2 = new JScrollPane(binaryTextArea); 
			jScrollPane2.setPreferredSize(new Dimension(200, 100));


			IPtextArea = new JTextArea();
			IPtextArea.setColumns(20);
			IPtextArea.setLineWrap(true);
			IPtextArea.setRows(10);
			IPtextArea.setWrapStyleWord(true);
			IPtextArea.setBorder(BorderFactory.createLineBorder(Color.black));
			IPtextArea.setEditable(true);
			
			jScrollPane3 = new JScrollPane(IPtextArea); 
			jScrollPane3.setPreferredSize(new Dimension(200, 100));


			textPanel.add(jScrollPane1, BorderLayout.LINE_START);
			textPanel.add(jScrollPane2, BorderLayout.CENTER);
			textPanel.add(jScrollPane3, BorderLayout.LINE_END);
	
		}

		public void actionPerformed(ActionEvent evt){

			Object source = evt.getSource();

			if(source == b1) getPlainText();
			if(source == b2) outputBinary();
			if(source == b3) outputIP();
			if(source == b4) IPvisualization();

		}

		
		private void IPvisualization(){
			IPvisualization IP = new IPvisualization();
			IP.setBinary(binaryLine);
			IP.setIP(IPbits);
			CryptMain.createFrame(IP);

		}

		private void getPlainText(){

			text = plainTextArea.getText();
			if(text.length() == 0) 
			{
				int returnVal = fc.showOpenDialog(InputFrame.this);

				    //Process the results.
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    input = fc.getSelectedFile();
				} 

				text = readFile(input);
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
			}
			//b2.setEnabled(true);
			plainTextArea.append(text);
		}

		private void outputBinary(){
	
			String plain = plainTextArea.getText();
			binaryLine = ConvertString.stringToAscii(plain);
			binaryTextArea.append(binaryLine + newline);		
			b3.setEnabled(true);
		
		}

		private void outputIP(){

			binary = binaryTextArea.getText();
			bits = ConvertString.stringToBinary(binary);
			IPbits = DES.permute(bits, DES.IP_Map);
			for(int i=0; i<64; i++){
				if(bits.get(i) == true) IPtextArea.append("0");
				else IPtextArea.append("1");
			}
			b4.setEnabled(true);
		}

		private String readFile(File input){

			 File file = input;
			String out = new String("");

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
				out += line;
				
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

			return out;
		}

	
	}
