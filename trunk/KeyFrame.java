
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;

public class KeyFrame extends JInternalFrame implements ActionListener, ItemListener {

	int openFrameCount = 2;

	final int xOffset = 10, yOffset = 10;


	private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;

	protected JButton b1,b2, b3, doAllButton;

	private JButton buttons[] = new JButton[18];

	private JButton PC2buttons[] = new JButton[16];

	private JTextField textfields[] = new JTextField[19];

	private JTextField subKeyFields[] = new JTextField[16];

	private JCheckBox checkboxes[] = new JCheckBox[18];

	BitSet bits, keyBits;

	BitSet keys[] = new BitSet[16];

	String key;

	private JLabel keylabels[] = new JLabel[16];

	Font textfieldFont = new Font("Sans-Serif", Font.BOLD, 14);
	Font buttonFont = new Font("Sans-Serif", Font.BOLD, 11);


	private final static String newline = "\n";

	    public KeyFrame() {
		super("Make Key", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(900,700);

		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

		createGUI();
	    }

	
	private void createGUI(){
		JPanel panel = new JPanel(new BorderLayout(5,5));
		panel.setBackground(Color.lightGray);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(900, 700));
		panel.setLocation(0, 0);
		this.setContentPane(panel);	

		JPanel buttonPanel = new JPanel();
		buttonPanel.setVisible(true);	
		panel.add(buttonPanel, BorderLayout.PAGE_START);

		b1 = new JButton("Convert Key");
		b1.addActionListener(this);
		b1.setEnabled(true);
		//buttonPanel.add(b1);
	
		b3 = new JButton("Clear");
		b3.setVerticalTextPosition(AbstractButton.CENTER);
		b3.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		b3.setMnemonic(KeyEvent.VK_D);
		b3.addActionListener(this);
		b3.setEnabled(true);
		buttonPanel.add(b3);



		JPanel leftPanel = new JPanel();
		GroupLayout layout = new GroupLayout(leftPanel);
 		leftPanel.setLayout(layout);
		leftPanel.setVisible(true);
		//leftPanel.setSize(100,500);
		leftPanel.setBackground(Color.white);
		

		
		for(int i = 0; i<18; i++)
		{
			
			if(i == 0) { buttons[i] = new JButton("To Binary"); buttons[i].setEnabled(true);}
			else if(i == 1) {buttons[i]= new JButton("PC1"); buttons[i].setEnabled(false);}
			else if(i == 2){ buttons[i]= new JButton("Left Shift");  buttons[i].setEnabled(false);}
			else if(i == 3){ buttons[i]= new JButton("Left Shift");  buttons[i].setEnabled(false);}
			else if(i == 10){ buttons[i]= new JButton("Left Shift");  buttons[i].setEnabled(false);}
			else if(i == 17) {buttons[i]= new JButton("Left Shift");  buttons[i].setEnabled(false);}
			else {
				buttons[i]= new JButton("Double Left Shift");
				
				buttons[i].setEnabled(false);
			}
			buttons[i].addActionListener(this);
			buttons[i].setFont(buttonFont);
			//buttons[i].setBackground(Color.white);
		}

		for(int i = 0; i<16; i++)
		{		
			PC2buttons[i]= new JButton("PC2");
			PC2buttons[i].addActionListener(this);
			PC2buttons[i].setEnabled(false);
			PC2buttons[i].setFont(buttonFont);
		}

		for(int i = 0; i<19; i++)
		{
			textfields[i] = new JTextField();
			textfields[i].setColumns(20);
			if(i == 0)textfields[i].setEditable(true);
			else textfields[i].setEditable(false);
			textfields[i].setFont(textfieldFont);
			textfields[i].setDocument
                		(new JTextFieldLimit(64));//binary should be 64, rest 56? i think? ask john.

		}

		for(int i = 0; i<16; i++)
		{
			subKeyFields[i] = new JTextField();
			

			subKeyFields[i].setEditable(false);
			subKeyFields[i].setFont(textfieldFont);

		}

		for(int i = 0; i<18; i++)
		{
			checkboxes[i] = new JCheckBox();
			checkboxes[i].setSelected(false);
			checkboxes[i].setBackground(Color.white);
			checkboxes[i].addItemListener(this);
		}

		
		doAllButton = new JButton("Do All");
		doAllButton.addActionListener(this);
		doAllButton.setEnabled(true);
		doAllButton.setFont(buttonFont);

		JLabel entertxt = new JLabel("Enter 8 char key: ");

		for(int i = 0; i <16; i++){
			keylabels[i] = new JLabel("<html>= Key<sub>" + (i+1) + "</sub></html> ");
			keylabels[i].setVisible(true);
			keylabels[i].setSize(20,10);
			keylabels[i].setForeground(Color.white);
		}

		
		//make layout for left panel
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.Group buttonGroup = layout.createParallelGroup();
		buttonGroup.addComponent(entertxt);
		for(int i = 0; i < 18; i++) {
				buttonGroup.addComponent(buttons[i]);
			}

		

		GroupLayout.Group textfieldGroup = layout.createParallelGroup();
		for(int i = 0; i < 19; i++) {
				textfieldGroup.addComponent(textfields[i]);
			}

		GroupLayout.Group buttonGroup2 = layout.createParallelGroup();
		for(int i = 0; i < 16; i++) {
				buttonGroup2.addComponent(PC2buttons[i]);
			}

		GroupLayout.Group textfieldGroup2 = layout.createParallelGroup();
		textfieldGroup2.addComponent(doAllButton);

		for(int i = 0; i < 16; i++) {
				textfieldGroup2.addComponent(subKeyFields[i]);
			}

		GroupLayout.Group verticalGroups[] = new GroupLayout.Group[18];
		for(int i = 0; i < 18; i++) {
				verticalGroups[i] = layout.createParallelGroup();
				verticalGroups[i].addComponent(buttons[i]);
				verticalGroups[i].addComponent(textfields[i+1]);
				verticalGroups[i].addComponent(checkboxes[i]);
				//if(i==1) verticalGroups[i].addComponent(keylabel);
				if(i >1 ) {
					verticalGroups[i].addComponent(PC2buttons[i-2]);
					verticalGroups[i].addComponent(subKeyFields[i-2]);
					verticalGroups[i].addComponent(keylabels[i-2]);
				}
			}

		GroupLayout.Group keyLabelGroup = layout.createParallelGroup();
		for(int i = 0; i < 16; i++) {
				keyLabelGroup.addComponent(keylabels[i]);
			}

		GroupLayout.Group checkboxGroup = layout.createParallelGroup();
		for(int i = 0; i < 18; i++) {
				checkboxGroup.addComponent(checkboxes[i]);
			}

		

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(checkboxGroup))
		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(buttonGroup))
		   	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(textfieldGroup))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(buttonGroup2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(textfieldGroup2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(keyLabelGroup))			
		);

		


		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(entertxt)
			.addComponent(textfields[0])
			.addComponent(doAllButton))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[0]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[1]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[2]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[3]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[4]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[5]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[6]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[7]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[8]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[9]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[10]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[11]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[12]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[13]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[14]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[15]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[16]))
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup( verticalGroups[17]))
		    //.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE), verticalGroups[18])
		        );


		layout.linkSize(SwingConstants.VERTICAL, textfields[0],
							textfields[1],
							textfields[2],
							textfields[3],
							textfields[4],
							textfields[5],
							textfields[6],
							textfields[7],
							textfields[8],
							textfields[9],
							textfields[10],
							textfields[11],
							textfields[12],
							textfields[13],
							textfields[14],
							textfields[15],
							textfields[16],
							textfields[17],
							textfields[18]);

		layout.linkSize(SwingConstants.VERTICAL, subKeyFields[0],
							subKeyFields[1],
							subKeyFields[2],
							subKeyFields[3],
							subKeyFields[4],
							subKeyFields[5],
							subKeyFields[6],
							subKeyFields[7],
							subKeyFields[8],
							subKeyFields[9],
							subKeyFields[10],
							subKeyFields[11],
							subKeyFields[12],
							subKeyFields[13],
							subKeyFields[14],
							subKeyFields[15]);

		panel.add(leftPanel, BorderLayout.CENTER);
		//panel.add(rightPanel, BorderLayout.LINE_END);	
		
	}

	private void clearText(){

		for(int i = 0; i < 19; i++){
			textfields[i].setText("");
			if(i <16) {
				subKeyFields[i].setText("");
				keylabels[i].setForeground(Color.white);
				PC2buttons[i].setEnabled(false);
			}
			if(i > 0 && i < 18)buttons[i].setEnabled(false);
		}
	}



	private void doAll(){
		boolean doubleShift = false;
		convertKey();
		funcPC1();
		for(int i = 0; i<16; i++){
			if(i == 2 || i ==3 || i == 10 || i == 17) doubleShift = false;
			else doubleShift = true;
			leftShift(i+2, doubleShift);
			funcPC2(i);
		}			
	}

	
	private void convertKey(){
		key = textfields[0].getText();
		String block, x;
		int length;
		if(key.length() < 8) length = key.length();
		else length = 8;


		keyBits = ConvertString.stringToBinary(ConvertString.stringToAscii(key));
		for(int i=0; i<keyBits.length(); i++){
		
			if(keyBits.get(i) == true) x = textfields[1].getText() + "0";
			else x = textfields[1].getText() + "1";
			textfields[1].setText(x);
		}

		buttons[1].setEnabled(true);
				
	}

	private void funcPC1(){
		String binary = textfields[1].getText();
		BitSet bin = ConvertString.StringToBitSet(binary);
		bin = DES.permute(bin, DES.PC1_Map);
		String x;
		for(int i=0; i<bin.length(); i++){
		
			if(bin.get(i) == true) x = textfields[2].getText() + "0";
			else x = textfields[2].getText() + "1";
			textfields[2].setText(x);
		}
		buttons[2].setEnabled(true);
	}

	private void funcPC2(int keyNum){
		String binary = textfields[keyNum+3].getText();
		BitSet bin = ConvertString.StringToBitSet(binary);
		keys[keyNum] = DES.permute(bin, DES.PC2_Map);
		String x;
		for(int i=0; i<keys[keyNum].length(); i++){
		
			if(keys[keyNum].get(i) == true) x = subKeyFields[keyNum].getText() + "0";
			else x = subKeyFields[keyNum].getText() + "1";
			subKeyFields[keyNum].setText(x);
		}
		keylabels[keyNum].setForeground(Color.black);
	}

	private void leftShift(int num, boolean doubleShift){
		String binary = textfields[num].getText();
		BitSet bin = ConvertString.StringToBitSet(binary);
		bin = DES.permute(bin, DES.LS_Map);	
		if(doubleShift) bin = DES.permute(bin, DES.LS_Map);
		String x;
		for(int i=0; i<64; i++){	
			if(bin.get(i) == true) x = textfields[num+1].getText() + "0";
			else x = textfields[num+1].getText() + "1";
			textfields[num+1].setText(x);
		}
		if(num<17) buttons[num+1].setEnabled(true);
		PC2buttons[num-2].setEnabled(true);
	}


	public void itemStateChanged(ItemEvent e) {
	    
	    Object source = e.getItemSelectable();

		for(int i = 0; i <18; i++)
		{

		    if (source == checkboxes[i]) {
			//...make a note of it...
		    } 
		}

	   // if (e.getStateChange() == ItemEvent.DESELECTED)
		//...make a note of it...
	    
	   // updatePicture();
	}


	public void actionPerformed(ActionEvent evt){
		boolean doubleShift = false;
		Object source = evt.getSource();
		if(source == b3) clearText();
		if(source == doAllButton) doAll();
		if(source == buttons[0]) convertKey();
		if(source == buttons[1]) funcPC1();

		for(int i = 2; i <18; i++)
		{
			if(source == buttons[i]){
				if(i == 2 || i ==3 || i == 10 || i == 17) doubleShift = false;
				else doubleShift = true;
				leftShift(i, doubleShift);			
			}
		}

		for(int i = 0; i<16; i++){
			if(source == PC2buttons[i]) funcPC2(i);
		}
	}
}

























