
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.lang.StringBuilder;

import  java.io.*;


public class KeyFrame extends JInternalFrame implements ActionListener{

	final int xOffset = 10, yOffset = 10;

	private JPanel panel, bottomPanel;

	private JButton clearButton, doAllButton, PC1Button, toBinaryButton;
	
	private JButton shiftButtons[] = new JButton[16];
	private JButton PC2buttons[] = new JButton[16];

	private JTextField textfields[] = new JTextField[19];
	private JTextField subKeyFields[] = new JTextField[16];
	
	private JLabel animSpeedLabel = new JLabel("Animation Speed: ");
	private JTextField animSpeedField = new JTextField();

	BitList keyBits, PC1bits;

	BitList keys[] = new BitList[16];

	BitList LSbits[] = new BitList[16];

	private JLabel keylabels[] = new JLabel[16];

	Font textfieldFont = new Font("Sans-Serif", Font.PLAIN, 10);
	Font buttonFont = new Font("Sans-Serif", Font.BOLD, 10);

	int width, height;

	private final static String newline = "\n";

	VisualizationPanel viz = new VisualizationPanel();;

	    public KeyFrame() {
		super("Make Key", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		width = CryptMain.screenWidth - 150;

		height = CryptMain.screenHeight - 100;

		setSize(width,height);

		setLocation(0,0);

		createGUI();
	    }

	
	private void createGUI(){
		panel = new JPanel(new BorderLayout(5,5));
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

	
		toBinaryButton = new JButton("To Binary");
		toBinaryButton.addActionListener(this);
		toBinaryButton.setFont(buttonFont);
		
		PC1Button = new JButton("PC1");
		PC1Button.setEnabled(false);
		PC1Button.addActionListener(this);
		PC1Button.setFont(buttonFont);
			
		
		animSpeedField.setColumns(30);
		animSpeedField.setEditable(true);
		animSpeedField.setFont(textfieldFont);
		animSpeedField.setDocument(new JTextFieldLimit(2));
		
		animSpeedLabel.setVisible(true);
		animSpeedLabel.setSize(20,10);
		animSpeedLabel.setForeground(Color.white);
		animSpeedLabel.setText("Animation Speed: ");
		
		for(int i = 0; i<16; i++)
		{	

			if((i == 0)||(i == 1)||(i == 8)||(i == 15))
				shiftButtons[i]= new JButton("Left Shift");  
			else
				shiftButtons[i]= new JButton("Double Left Shift");
				
			shiftButtons[i].setEnabled(false);
			shiftButtons[i].addActionListener(this);
			shiftButtons[i].setFont(buttonFont);
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
			textfields[i].setColumns(30);
			if(i == 0)textfields[i].setEditable(true);
			else textfields[i].setEditable(false);
			textfields[i].setFont(textfieldFont);
			textfields[i].setDocument(new JTextFieldLimit(64));
		}

		for(int i = 0; i<16; i++)
		{
			subKeyFields[i] = new JTextField();
			subKeyFields[i].setColumns(30);
			subKeyFields[i].setEditable(false);
			subKeyFields[i].setFont(textfieldFont);
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
		
		buttonGroup.addComponent(toBinaryButton);
		buttonGroup.addComponent(PC1Button);
		for(int i = 0; i < 16; i++) buttonGroup.addComponent(shiftButtons[i]);

		GroupLayout.Group textfieldGroup = layout.createParallelGroup();
		for(int i = 0; i < 19; i++) textfieldGroup.addComponent(textfields[i]);

		GroupLayout.Group buttonGroup2 = layout.createParallelGroup();
		for(int i = 0; i < 16; i++) {
				buttonGroup2.addComponent(PC2buttons[i]);
			}

		GroupLayout.Group textfieldGroup2 = layout.createParallelGroup();
		textfieldGroup2.addComponent(doAllButton);
		textfieldGroup2.addComponent(animSpeedField);
		textfieldGroup2.addComponent(animSpeedLabel);
		for(int i = 0; i < 16; i++) {
				textfieldGroup2.addComponent(subKeyFields[i]);
			}

		GroupLayout.Group verticalGroups[] = new GroupLayout.Group[18];
		for(int i = 0; i < 18; i++) verticalGroups[i] = layout.createParallelGroup();
		verticalGroups[0].addComponent(toBinaryButton);
		verticalGroups[0].addComponent(animSpeedField);
		verticalGroups[0].addComponent(animSpeedLabel);
		verticalGroups[1].addComponent(PC1Button);

		
		for(int i = 0; i < 18; i++)
		{
			if (i>1) verticalGroups[i].addComponent(shiftButtons[i-2]);
			
			verticalGroups[i].addComponent(textfields[i+1]);
			
			if(i >1 )
			{
				verticalGroups[i].addComponent(PC2buttons[i-2]);
				verticalGroups[i].addComponent(subKeyFields[i-2]);
				verticalGroups[i].addComponent(keylabels[i-2]);
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


		layout.linkSize(SwingConstants.VERTICAL, animSpeedField, textfields[0],
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
		viz.stop();
		viz.clear();	
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

		String key = textfields[0].getText()+"          ";	
		key = key.substring(0,8);
		keyBits = ConvertString.StringToBitList(key);
		String tmp = keyBits.toString();
		textfields[1].setText(tmp);
					
		PC1Button.setEnabled(true);

		viz.setBitsOne(keyBits);
		//viz.setSizeOne(keyBits.length());
		viz.makeNodesOne();
		viz.repaint();
		validate();
						
	}

	private void funcPC1(){

		PC1bits = DES.permute(keyBits, DES.PC1_Map);			
		String tmp = PC1bits.toString();
		textfields[2].setText(tmp);			 

		shiftButtons[0].setEnabled(true);
		
		if(animSpeedField.getText().trim().length() == 0) viz.setDelay(1);
		else{
			int delay = Integer.parseInt(animSpeedField.getText().trim());
			viz.setDelay(delay);
		}

		viz.setBitsOne(keyBits);
		viz.setBitsTwo(PC1bits);
		viz.setMap(DES.PC1_Map);
		viz.makeNodesOne();
		viz.makeNodesTwo();
		viz.start();				
		
	}

	//PC2 VIZ DOES NOT WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
	
		if(animSpeedField.getText().trim().length() == 0) viz.setDelay(1);
		else{
			int delay = Integer.parseInt(animSpeedField.getText().trim());
			viz.setDelay(delay);
		}
		viz.setBitsOne(LSbits[keyNum]);
		viz.setBitsTwo(keys[keyNum]);
		viz.setMap(DES.PC2_Map);
		//viz.setSize();
		viz.makeNodesOne();
		viz.makeNodesTwo();
		viz.start();

	}

	//double left shift VIZ does not work
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
			viz.setBitsOne(LSbits[num-1]);
			viz.setBitsTwo(LSbits[num]);
		}
	
		if(animSpeedField.getText().trim().length() == 0) viz.setDelay(1);
		else{
			int delay = Integer.parseInt(animSpeedField.getText().trim());
			viz.setDelay(delay);
		}
		
		if (doubleShift) viz.setMap(DES.double_LS_Map);
		else viz.setMap(DES.LS_Map);
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

























