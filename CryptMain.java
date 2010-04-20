
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class CryptMain extends JFrame implements ActionListener
{
	static JDesktopPane desktop;
        
	static Toolkit toolkit = Toolkit.getDefaultToolkit();  
	static Dimension screenSize = toolkit.getScreenSize();
	static public int screenWidth = screenSize.width;
	static	public int screenHeight = screenSize.height;
        JMenuItem quickMenu, DESMenu, KeyMenu, helpMenu, aboutMenu ;

	//Constructor
	public CryptMain(){

		setTitle("CryptoViz");	
		//Set up the GUI.
		desktop = new JDesktopPane(); //a specialized layered pane
		desktop.setBackground(Color.lightGray);
		setContentPane(desktop);

                //createFrame(new MenuFrame(0, 0));
                createMenuBar();
	}

	//Create a new internal frame.
    	public static void createFrame(JInternalFrame frame) {
		frame.setVisible(true); //necessary as of 1.3
		desktop.add(frame);
		try {
		    frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
    	}

        private void createMenuBar(){
                // Creates a menubar for a JFrame
                JMenuBar menuBar = new JMenuBar();

                // Add the menubar to the frame
                setJMenuBar(menuBar);

                JMenu encMenu = new JMenu("Encryption Tools");
                JMenu mainHelpMenu = new JMenu("Help");

                quickMenu = new JMenuItem("Quick DES");
                DESMenu = new JMenuItem("DES Visualization");
                KeyMenu = new JMenuItem("Key Visualization");
                helpMenu = new JMenuItem("Help");
                aboutMenu = new JMenuItem("About");
                encMenu.add(quickMenu);
                encMenu.add(DESMenu);
                encMenu.add(KeyMenu);
                mainHelpMenu.add(helpMenu);
                mainHelpMenu.add(aboutMenu);
                menuBar.add(encMenu);
                menuBar.add(mainHelpMenu);

                quickMenu.addActionListener(this);
                DESMenu.addActionListener(this);
                KeyMenu.addActionListener(this);
                helpMenu.addActionListener(this);
                aboutMenu.addActionListener(this);
        }

	public void actionPerformed(ActionEvent evt){
                Object source = evt.getSource();
                if(source == quickMenu) createFrame(new DESFrame2());
                if(source == DESMenu) createFrame(new EncryptFrame());
                if(source == KeyMenu) createFrame(new KeyFrame());
                if(source == helpMenu) //createFrame();
                if(source == aboutMenu) createFrame(new StartFrame() );
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
