import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class RoundFrame extends JPanel implements ActionListener{

	private JButton clearButton, doAllButton, E_Button, XOR1_Button, P_Button, XOR2_Button, switchButton;
	
	private JTextField L_In_Field, R_In_Field, key_Field, R_E_Field, 
		 EK_XOR_Field, S_boxed_Field, P_Field, L_Out_Field, R_Out_Field;

	Font textfieldFont = new Font("Sans-Serif", Font.PLAIN, 10);
	Font buttonFont = new Font("Sans-Serif", Font.BOLD, 10);

	
	BitList Left,Right,Key;

	private final static String newline = "\n";

	//VisualizationPanel viz = new VisualizationPanel();

	    public RoundFrame(int x, BitList L, BitList R, BitList K) {

				this.setBackground(Color.green);
		this.setVisible(true);		
		this.setPreferredSize(new Dimension(800, 800));

		Left=L;Right=R;Key=K;
		
		
	
			  System.out.println(x+"    "+L+"    "+R+"    "+K);
			  

		JPanel panel = new JPanel();
		panel.setLayout(null);		
		panel.setBackground(Color.blue);
		panel.setVisible(true);		
		panel.setPreferredSize(new Dimension(800, 850));
		panel.setLocation(0, 0);
		
		
	/*		
		clearButton = new JButton("Clear");
		clearButton.setMnemonic(KeyEvent.VK_D);
		clearButton.addActionListener(this);
		//clearButton.setEnabled(true);
		panel.add(clearButton);

		doAllButton = new JButton("Do All");
		doAllButton.addActionListener(this);
		doAllButton.setEnabled(true);
		doAllButton.setFont(buttonFont);

		//JLabel entertxt = new JLabel("Enter 8 char key: ");

		*/
	}

	private void clearText(){
	
		L_In_Field.setText("");
		R_In_Field.setText("");
		key_Field.setText("");
		R_E_Field.setText(""); 
		EK_XOR_Field.setText("");
		S_boxed_Field.setText("");
		P_Field.setText("");
		L_Out_Field.setText("");
		R_Out_Field.setText("");
		
				validate();
	}



/*	private void doAll(){
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
		visualize(keyBits);
				
	}

	private void funcPC1(){
		//String binary = textfields[1].getText();
		//PC1bits = ConvertString.StringToBitList(binary);
		PC1bits = DES.permute(keyBits, DES.PC1_Map);
		
		//System.out.println(keyBits.length());
		//System.out.println(PC1bits.length());

		String x="";
			
		for(int i=0; i<PC1bits.size(); i++){
		
			if(PC1bits.get(i) == true) x = x + "1";
			else x = x + "0";
			
		}
		textfields[2].setText(x);
		textfields[2].setText(" "+DES.PC1_Map.length);
		
		shiftButtons[0].setEnabled(true);
		panel.remove(bottomPanel);
		visualize(keyBits, PC1bits, DES.PC1_Map);
				
		
	}

	private void funcPC2(int keyNum){
		//String binary = textfields[keyNum+3].getText();
		//BitList bin = ConvertString.StringToBitList(binary);
		keys[keyNum] = DES.permute(LSbits[keyNum], DES.PC2_Map);
		String x;

		subKeyFields[keyNum].setText("");
		for(int i=0; i<keys[keyNum].size(); i++){
		
			if(keys[keyNum].get(i) == true) x = subKeyFields[keyNum].getText() + "1";
			else x = subKeyFields[keyNum].getText() + "0";
			subKeyFields[keyNum].setText(x);
		}
		keylabels[keyNum].setForeground(Color.black);
	
		visualize(LSbits[keyNum], keys[keyNum], DES.PC2_Map);
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

		panel.remove(viz);
		validate();
		if(num==0) visualize(PC1bits, LSbits[0], DES.LS_Map);
		else visualize(LSbits[num-3], LSbits[num-2], DES.LS_Map);
		
	}


	private void visualize(BitList one){
		viz = new VisualizationPanel(one);
		panel.add(viz , BorderLayout.PAGE_END);
		viz.start();
		validate();
	}

	private void visualize(BitList one, BitList two, int[] map){

		viz = new VisualizationPanel(one, two, map);
		panel.add(viz , BorderLayout.PAGE_END);
		viz.start();
		validate();
	}*/


	public void actionPerformed(ActionEvent evt){
		/*
		Object source = evt.getSource();
		if(source == clearButton) clearText();
		if(source == doAllButton); //doAll();
		
		if(source == E_Button);
		if(source == XOR1_Button);
		if(source == P_Button);
		if(source == XOR2_Button);
		if(source == switchButton);*/

	}
	
	
	
	
	public void paintComponent(Graphics g) {
	

	
	}
	
	
	
	
}





















