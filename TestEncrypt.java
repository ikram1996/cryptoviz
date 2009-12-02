import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import  java.io.*;



public class TestEncrypt extends JInternalFrame {

	JPanel panel;

	public TestEncrypt() {
		super("Encrypt", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(500,900);
		setLocation(0,0);

		JPanel encryptpanel = new EncryptPanel();
		encryptpanel.setVisible(true);
		this.setContentPane(encryptpanel);

	}

/*
	public void actionPerformed(ActionEvent evt){

		Object source = evt.getSource();

		if(source == b1) getPlainText();
		if(source == b2) outputBinary();
		if(source == b3) clearText();
	}
*/	
}
