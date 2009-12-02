
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.lang.StringBuilder;

import  java.io.*;


public class EncryptFrame extends JInternalFrame implements ActionListener{

	final int xOffset = 10, yOffset = 10;

	private JPanel panel, bottomPanel;

	private JButton clearButton, doAllButton, PC1Button, toBinaryButton;
	
	private JButton LeftButtons[] = new JButton[16];
	private JButton RightButtons[] = new JButton[16];

	private JTextField InputField = new JTextField();
	private JTextField LeftFields[] = new JTextField[19];
	private JTextField RightFields[] = new JTextField[16];

	BitList keyBits, PC1bits;

	BitList keys[] = new BitList[16];

	BitList LSbits[] = new BitList[16];

	private JLabel keylabels[] = new JLabel[16];

	Font textfieldFont = new Font("Sans-Serif", Font.PLAIN, 10);
	Font buttonFont = new Font("Sans-Serif", Font.BOLD, 10);

	int width, height;

	private final static String newline = "\n";

	VisualizationPanel viz = new VisualizationPanel();

	    public EncryptFrame() {
		super("DES Encryption", 
		      false, //resizable
		      true, //closable
		      false, //maximizable
		      true);//iconifiable

		width = CryptMain.screenWidth - 50;

		height = CryptMain.screenHeight - 100;

		setSize(width,height);

		setLocation(0,0);

		createGUI();
	    }

	
	private void createGUI(){
		panel = new JPanel(new BorderLayout(5,5), true);//double buffered
		panel.setBackground(Color.white);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(800, 850));
		panel.setLocation(0, 0);
		this.setContentPane(panel);	

		JPanel buttonPanel = new JPanel();
		buttonPanel.setVisible(true);	
		panel.add(buttonPanel, BorderLayout.PAGE_START);
	
		clearButton = new JButton("Clear");
		clearButton.setVerticalTextPosition(AbstractButton.CENTER);
		clearButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		clearButton.setMnemonic(KeyEvent.VK_D);
		clearButton.addActionListener(this);
		//clearButton.setEnabled(true);
		buttonPanel.add(clearButton);

		JPanel leftPanel = new JPanel();
		GroupLayout layout = new GroupLayout(leftPanel);
 		leftPanel.setLayout(layout);
		leftPanel.setVisible(true);
		//leftPanel.setSize(100,500);
		leftPanel.setBackground(Color.white);

		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.white);
		bottomPanel.setMinimumSize(new Dimension(0, 250));
		bottomPanel.setVisible(true);

	
		//toBinaryButton = new JButton("To Binary");
		//toBinaryButton.addActionListener(this);
		//toBinaryButton.setFont(buttonFont);
		
		//PC1Button = new JButton("PC1");
		//PC1Button.setEnabled(false);
		//PC1Button.addActionListener(this);
		//PC1Button.setFont(buttonFont);
			
		
		for(int i = 0; i<16; i++)
		{	
			LeftButtons[i]= new JButton("Left "+i+1);  
			LeftButtons[i].setEnabled(false);
			LeftButtons[i].addActionListener(this);
			LeftButtons[i].setFont(buttonFont);
		}

		for(int i = 0; i<16; i++)
		{		
			RightButtons[i]= new JButton("Right "+i+1);
			RightButtons[i].addActionListener(this);
			RightButtons[i].setEnabled(false);
			RightButtons[i].setFont(buttonFont);
		}

		for(int i = 0; i<16; i++)
		{
			LeftFields[i] = new JTextField();
			LeftFields[i].setColumns(35);
			LeftFields[i].setEditable(false);
			LeftFields[i].setFont(textfieldFont);
			LeftFields[i].setDocument(new JTextFieldLimit(32));
		}

		for(int i = 0; i<16; i++)
		{
			RightFields[i] = new JTextField();
			RightFields[i].setColumns(35);
			RightFields[i].setEditable(false);
			RightFields[i].setFont(textfieldFont);
		}

		//doAllButton = new JButton("Do All");
		//doAllButton.addActionListener(this);
		//doAllButton.setEnabled(true);
		//doAllButton.setFont(buttonFont);

		JLabel entertxt = new JLabel("Enter Plaintext:");

		//for(int i = 0; i <16; i++){
		//	keylabels[i] = new JLabel("<html>= Key<sub>" + (i+1) + "</sub></html> ");
		//	keylabels[i].setVisible(true);
		//	keylabels[i].setSize(20,10);
		//	keylabels[i].setForeground(Color.white);
		//}

		
		//make layout for left panel
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.Group buttonGroup = layout.createParallelGroup();
		buttonGroup.addComponent(entertxt);
		
		//buttonGroup.addComponent(toBinaryButton);
		//buttonGroup.addComponent(PC1Button);
		for(int i = 0; i < 16; i++) buttonGroup.addComponent(LeftButtons[i]);

		GroupLayout.Group textfieldGroup = layout.createParallelGroup();
		for(int i = 0; i < 19; i++) textfieldGroup.addComponent(LeftFields[i]);

		GroupLayout.Group buttonGroup2 = layout.createParallelGroup();
		for(int i = 0; i < 16; i++) {
				buttonGroup2.addComponent(RightButtons[i]);
			}

		GroupLayout.Group textfieldGroup2 = layout.createParallelGroup();
		//textfieldGroup2.addComponent(doAllButton);

		for(int i = 0; i < 16; i++) {
				textfieldGroup2.addComponent(RightFields[i]);
			}

		GroupLayout.Group verticalGroups[] = new GroupLayout.Group[18];
		for(int i = 0; i < 18; i++) verticalGroups[i] = layout.createParallelGroup();
		//verticalGroups[0].addComponent(toBinaryButton);
		//verticalGroups[1].addComponent(PC1Button);

		
		for(int i = 0; i < 18; i++)
		{
			if (i>1) verticalGroups[i].addComponent(LeftButtons[i-2]);
			
			verticalGroups[i].addComponent(LeftFields[i+1]);
			
			if(i >1 )
			{
				verticalGroups[i].addComponent(RightButtons[i-2]);
				verticalGroups[i].addComponent(RightFields[i-2]);
				//verticalGroups[i].addComponent(keylabels[i-2]);
			}
		}

		GroupLayout.Group keyLabelGroup = layout.createParallelGroup();
		for(int i = 0; i < 16; i++) {
				keyLabelGroup.addComponent(keylabels[i]);
	
		

		layout.setHorizontalGroup(layout.createSequentialGroup()

		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(buttonGroup))
		   	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(textfieldGroup))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(buttonGroup2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(textfieldGroup2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(keyLabelGroup))			
		);

		


		layout.setVerticalGroup(layout.createSequentialGroup()
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(entertxt)
			.addComponent(LeftFields[0])
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


		layout.linkSize(SwingConstants.VERTICAL, LeftFields[0],
							LeftFields[1],
							LeftFields[2],
							LeftFields[3],
							LeftFields[4],
							LeftFields[5],
							LeftFields[6],
							LeftFields[7],
							LeftFields[8],
							LeftFields[9],
							LeftFields[10],
							LeftFields[11],
							LeftFields[12],
							LeftFields[13],
							LeftFields[14],
							LeftFields[15],
							LeftFields[16],
							LeftFields[17],
							LeftFields[18]);

		layout.linkSize(SwingConstants.VERTICAL, RightFields[0],
							RightFields[1],
							RightFields[2],
							RightFields[3],
							RightFields[4],
							RightFields[5],
							RightFields[6],
							RightFields[7],
							RightFields[8],
							RightFields[9],
							RightFields[10],
							RightFields[11],
							RightFields[12],
							RightFields[13],
							RightFields[14],
							RightFields[15]);

		panel.add(leftPanel, BorderLayout.CENTER);
		panel.add(viz, BorderLayout.PAGE_END);	
		
	}
	}

	private void clearText(){

		for(int i = 0; i < 19; i++){
			textfields[i].setText("");
			if(i <16)
			{
				subKeyFields[i].setText("");
				keylabels[i].setForeground(Color.white);
				PC2buttons[i].setEnabled(false);
				shiftButtons[i].setEnabled(false);
			}
		}
		
		//toBinaryButton.setEnabled(false);
		PC1Button.setEnabled(false);
		viz.setSizeOne(0);
		viz.repaint();
		validate();
	}



	private void doAll(){
		convertKey();
		funcPC1();
		for(int i = 0; i<16; i++){
			if(i == 0 || i ==1 || i == 8 || i == 15) leftShift(i, false);
			else leftShift(i, true);
			funcPC2(i);
		}			
	}

	
	private void convertKey(){
		String key = textfields[0].getText();
		String block, x;
		int length;
		if(key.length() < 8) length = key.length();
		else length = 8;

		textfields[1].setText("");
		keyBits = ConvertString.stringToBinary(ConvertString.stringToAscii(key));
		for(int i=0; i<keyBits.length(); i++){
		
			if(keyBits.get(i) == true) x = textfields[1].getText() + "1";
			else x = textfields[1].getText() + "0";
			textfields[1].setText(x);
		}

		PC1Button.setEnabled(true);

		viz.setBitsOne(keyBits);
		viz.makeNodesOne();
		viz.repaint();
		validate();				
	}

	private void funcPC1(){

		PC1bits = DES.permute(keyBits, DES.PC1_Map);
		String x="";
			
		for(int i=0; i<PC1bits.size(); i++){
		
			if(PC1bits.get(i) == true) x = x + "1";
			else x = x + "0";
			
		}
		textfields[2].setText(x);
		
		shiftButtons[0].setEnabled(true);

		viz.setBitsOne(keyBits);
		viz.setBitsTwo(PC1bits);
		viz.setMap(DES.PC1_Map);
		viz.makeNodesOne();
		viz.makeNodesTwo();
		viz.start();				
		
	}

	private void funcPC2(int keyNum){
		keys[keyNum] = DES.permute(LSbits[keyNum], DES.PC2_Map);
		String x;

		subKeyFields[keyNum].setText("");
		for(int i=0; i<keys[keyNum].size(); i++){		
			if(keys[keyNum].get(i) == true) x = subKeyFields[keyNum].getText() + "1";
			else x = subKeyFields[keyNum].getText() + "0";
			subKeyFields[keyNum].setText(x);
		}
		
		keylabels[keyNum].setForeground(Color.black);
	
		viz.setBitsOne(LSbits[keyNum]);
		viz.setBitsTwo(keys[keyNum]);
		viz.setMap(DES.PC2_Map);
		viz.makeNodesOne();
		viz.makeNodesTwo();
		viz.start();

	}

	private void leftShift(int num, boolean doubleShift){

		if(num == 0) LSbits[0] = DES.permute(PC1bits, DES.LS_Map);
		else LSbits[num] = DES.permute(LSbits[num-1], DES.LS_Map);

		if(doubleShift) LSbits[num] = DES.permute(LSbits[num], DES.LS_Map);
		
		StringBuilder x = new StringBuilder();

		for(int i=0; i<LSbits[num].size(); i++){	
			if(LSbits[num].get(i)) x.append('1');
			else x.append('0');
		}		
		textfields[num+3].setText(x.toString());
			
		if(num<15) shiftButtons[num+1].setEnabled(true);
		
		PC2buttons[num].setEnabled(true);
		
		if(num==0){
			viz.setBitsOne(PC1bits);
			viz.setBitsTwo(LSbits[0]);
		}
		else {
			viz.setBitsOne(LSbits[num]);
			viz.setBitsTwo(LSbits[num-1]);
		}

		viz.setMap(DES.LS_Map);
		viz.makeNodesOne();
		viz.makeNodesTwo();
		viz.start();
		
	}

	public void actionPerformed(ActionEvent evt){
		boolean doubleShift = false;
		Object source = evt.getSource();
		if(source == clearButton) clearText();
		if(source == doAllButton) doAll();
		if(source == toBinaryButton) convertKey();
		if(source == PC1Button) funcPC1();
		for(int i = 0; i <16; i++)
		{
			if(source == shiftButtons[i]){
				if(shiftButtons[i].getText()=="Left Shift") leftShift(i, false);
				else leftShift(i, true);							
			}
		}

		for(int i = 0; i<16; i++){
			if(source == PC2buttons[i]) funcPC2(i);
		}
	}
	
	
}
























