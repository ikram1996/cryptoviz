
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class CryptMain extends JFrame implements ActionListener
{

	JMenuItem newAction, openAction, exitAction, cutAction, copyAction, pasteAction, IPAction, KeyAction, DESAction, inputAction, EncryptAction, AniTestAction
			,TestEncryptAction, aboutAction;

	static JDesktopPane desktop;
        
	static Toolkit toolkit = Toolkit.getDefaultToolkit();  
	static Dimension screenSize = toolkit.getScreenSize();
	static public int screenWidth = screenSize.width;
	static	public int screenHeight = screenSize.height;

	//Constructor
	public CryptMain(){

		setTitle("CryptoViz");	
		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		desktop.setBackground(Color.lightGray);
		setContentPane(desktop);

                createFrame(new MenuFrame(0, 0));
	}

	//Create a new internal frame.
    	public static void createFrame(JInternalFrame frame) {
		frame.setVisible(true); //necessary as of 1.3
		desktop.add(frame);
		try {
		    frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
    	}

    
	public void actionPerformed(ActionEvent evt){

	}
	
	public static void main(String[] args) {
		CryptMain crypt = new CryptMain();
		//frame.setContentPane(menu.createContentPane());
        	crypt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//place frame in middle of screen
		
		//int x = (screenSize.width - crypt.getWidth()) / 2;  
		//int y = (screenSize.height - crypt.getHeight()) / 2;  
 		//crypt.setLocation(x, y); 
		crypt.setSize(screenWidth, screenHeight);
		crypt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	crypt.setVisible(true);
	}
}
