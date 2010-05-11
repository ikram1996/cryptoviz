
//holds an encryptpanel (the feistel structure etc)


import javax.swing.*;
import java.awt.*;

public class EncryptFrame extends JInternalFrame {

	JPanel panel;

	public EncryptFrame() {
		super("Encrypt", 
		      true, //resizable
		      true, //closable
		      true, //maximizable
		      true);//iconifiable

		setSize(900,700);
		setLocation(0,0);
                setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon.png"))); // NOI18N

		JPanel encryptpanel = new EncryptPanel();
		encryptpanel.setVisible(true);
		
		
		JScrollPane scrollPane = new JScrollPane(encryptpanel);
		scrollPane.setPreferredSize(new Dimension(900,900));

		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		this.setContentPane(scrollPane);

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
