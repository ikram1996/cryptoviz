import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class RoundFrame extends JInternalFrame{

	private JButton clearButton, doAllButton, E_Button, XOR1_Button, P_Button, XOR2_Button, switchButton;
	
	private JTextField L_In_Field, R_In_Field, key_Field, R_E_Field, 
		 EK_XOR_Field, S_boxed_Field, P_Field, L_Out_Field, R_Out_Field;


	BitList Left,Right,Key;

	private final static String newline = "\n";


            public RoundFrame() {

                super("Round ",
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(700,550);
		setLocation(0,0);

		JPanel panel = new RoundPanel();
		panel.setVisible(true);
		this.setContentPane(panel);
		panel.setPreferredSize(new Dimension(700, 550));
		panel.setBackground(Color.white);
            }


	    public RoundFrame(int x, BitList L, BitList R, BitList K) {
		
		super("Round ", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(700,550);
		setLocation(0,0);

		JPanel panel = new RoundPanel(x, L, R, K);
		panel.setVisible(true);
		this.setContentPane(panel);
		panel.setPreferredSize(new Dimension(700, 550));
		panel.setBackground(Color.white);

		Left=L;Right=R;Key=K;		
			  
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
}
