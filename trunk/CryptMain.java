
// CryptMain.java
// main class for the program. To compile and run:
// 	javac CryptMain.java
// 	java CryptMain

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
        JMenuItem quickMenu, DESMenu, KeyMenu, helpMenu, aboutMenu, exitMenu,
                cascMenu, minAllMenu, maxAllMenu;

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

                JMenu fileMenu = new JMenu("File");
                JMenu encMenu = new JMenu("Encryption");
                JMenu winMenu = new JMenu("Window");
                JMenu mainHelpMenu = new JMenu("Help");

                quickMenu = new JMenuItem("Quick DES");
                DESMenu = new JMenuItem("DES Visualization");
                KeyMenu = new JMenuItem("Key Visualization");
                helpMenu = new JMenuItem("Help");
                aboutMenu = new JMenuItem("About");
                exitMenu = new JMenuItem("Exit");
                cascMenu = new JMenuItem("Cascade");
                minAllMenu = new JMenuItem("Minimize All");
                maxAllMenu = new JMenuItem("Restore All");
                fileMenu.add(exitMenu);
                encMenu.add(quickMenu);
                encMenu.add(DESMenu);
                encMenu.add(KeyMenu);
                winMenu.add(cascMenu);
                winMenu.add(minAllMenu);
                winMenu.add(maxAllMenu);
                mainHelpMenu.add(helpMenu);
                mainHelpMenu.add(aboutMenu);
                menuBar.add(fileMenu);
                menuBar.add(encMenu);
                menuBar.add(winMenu);
                menuBar.add(mainHelpMenu);

                quickMenu.addActionListener(this);
                DESMenu.addActionListener(this);
                KeyMenu.addActionListener(this);
                helpMenu.addActionListener(this);
                aboutMenu.addActionListener(this);
                cascMenu.addActionListener(this);
                minAllMenu.addActionListener(this);
                maxAllMenu.addActionListener(this);
        }

	public void actionPerformed(ActionEvent evt){
                Object source = evt.getSource();
                if(source == quickMenu) createFrame(new DESFrame2());
                if(source == DESMenu) createFrame(new EncryptFrame());
                if(source == KeyMenu) createFrame(new KeyFrame());
                //if(source == helpMenu) //createFrame();
                if(source == aboutMenu) createFrame(new StartFrame());
                if(source == cascMenu)  cascade();
                if(source == minAllMenu) minAll();
                if(source == maxAllMenu) maxAll();
	}

        private void minAll(){
            JInternalFrame[] frames = desktop.getAllFrames();
            if ( frames.length == 0) return;
            for ( int i = 0; i < frames.length; i++) {
                try{frames[i].setIcon(true);}
                catch(Exception e) {}
            }
        }

        private void maxAll(){
            JInternalFrame[] frames = desktop.getAllFrames();
            if ( frames.length == 0) return;
            for ( int i = 0; i < frames.length; i++) {
                try{frames[i].setIcon(false);}
                catch(Exception e) {}
            }
        }

        private void cascade(){
            JInternalFrame[] frames = desktop.getAllFrames();
            if ( frames.length == 0) return;
            Rectangle dBounds = desktop.getBounds();
            int separation = 24;
            int margin = frames.length*separation + separation;
            int width = dBounds.width - margin;
            int height = dBounds.height - margin;
            for ( int i = 0; i < frames.length; i++) {
                frames[i].setBounds( separation + dBounds.x + i*separation,
                                     separation + dBounds.y + i*separation,
                                     width, height );
            }

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
