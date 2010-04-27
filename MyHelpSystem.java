/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.datatransfer.*;

public class MyHelpSystem extends JInternalFrame
{
  private JEditorPane html;
  private String ContentsPath = "help.html";
  private ArrayList<URL> history;
  private ImageIcon icon = null;
  private int historyPos = 0;

	private JToolBar MainTools;
	private JMenuBar MainMenu;

	public ClassLoader cl = this.getClass().getClassLoader();
	private Clipboard clipboard;  // System Clipboard

  public MyHelpSystem()
  {
  	super("Help System");
  	icon = new ImageIcon(cl.getResource("HelpImages/HelpImage.GIF"));
  	setupHelpSystem();
	}

  public MyHelpSystem(ImageIcon helpIcon)
  {
  	super("Help System");
  	icon = helpIcon;
  	setupHelpSystem();
	}

  public MyHelpSystem(String title)
  {
  	super(title);
  	icon = new ImageIcon(cl.getResource("HelpImages/HelpImage.GIF"));
  	setupHelpSystem();
	}

  public MyHelpSystem(String title, ImageIcon helpIcon)
  {
  	super(title);
  	icon = helpIcon;
  	setupHelpSystem();
	}

  public MyHelpSystem(String cp, String title)
  {
  	super(title);
	  ContentsPath = cp;
	  icon = new ImageIcon(cl.getResource("HelpImages/HelpImage.GIF"));
	  setupHelpSystem();
	}

  public MyHelpSystem(String cp, String title, ImageIcon helpIcon)
  {
  	super(title);
	  ContentsPath = cp;
	  icon = helpIcon;
	  setupHelpSystem();
	}

  private void setupHelpSystem()
  {

  	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
  	setBounds((int)(0.2*screen.width),20,(int)(0.75*screen.width),(int)(0.75*screen.height));
  	setBackground(new Color(255, 255, 255));
  	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		history = new ArrayList<URL>();

		createMenu();
		createToolBar();

    html = new JEditorPane();
    html.setEditable(false);
    html.addHyperlinkListener(createHyperLinkListener());

		JScrollPane scroller = new JScrollPane();
		JViewport vp = scroller.getViewport();
		vp.add(html);

		setJMenuBar(MainMenu);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(MainTools, BorderLayout.NORTH);
		getContentPane().add(scroller, BorderLayout.CENTER);

    try
    {
			html.setPage(cl.getResource(ContentsPath));
			history.add(cl.getResource(ContentsPath));
		}
    catch (Exception e)
    {
      System.out.println("Exception: " + e);
    }

 		try
		{
			Toolkit toolkit = getToolkit().getDefaultToolkit();
 			clipboard = toolkit.getSystemClipboard();
		}
		catch (HeadlessException hex)
		{
			clipboard = null;
		}

    setVisible(true);
  }

  public HyperlinkListener createHyperLinkListener()
  {
		return new HyperlinkListener()
		{
	    public void hyperlinkUpdate(HyperlinkEvent e)
	    {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				{
		    	if (e instanceof HTMLFrameHyperlinkEvent)
		    	{
		    		//  Not put into the history list.
						((HTMLDocument)html.getDocument()).processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
		    	}
		    	else
		    	{
						try
						{
			    		html.setPage(e.getURL());
			    		while (historyPos < history.size()-1)
			    			history.remove(history.size()-1);
			    		history.add(e.getURL());
			    		historyPos = history.size()-1;
						}
						catch (IOException ioe)
						{
			    		System.out.println("IOE: " + ioe);
						}
		    	}
				}
	    }
		};
  }

  void updateDragEnabled(boolean dragEnabled)
  {
    html.setDragEnabled(dragEnabled);
  }

	private void createMenu()
	{
		//  File Menu Items

		JMenuItem ExitMenuItem = new JMenuItem("Exit", new ImageIcon(cl.getResource("HelpImages/RemoveImage.GIF")));
		ExitMenuItem.setMnemonic('x');
		ExitMenuItem.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ dispose(); }}
		);

		//  Navigate Menu Items

		JMenuItem ContentsMenuItem = new JMenuItem("Contents", new ImageIcon(cl.getResource("HelpImages/HelpImage.GIF")));
		ContentsMenuItem.setMnemonic('C');
		ContentsMenuItem.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onContents(); }}
		);

		JMenuItem BackMenuItem = new JMenuItem("Back", new ImageIcon(cl.getResource("HelpImages/Back.GIF")));
		BackMenuItem.setMnemonic('B');
		BackMenuItem.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onBack(); }}
		);

		JMenuItem ForwardMenuItem = new JMenuItem("Forward", new ImageIcon(cl.getResource("HelpImages/Forward.GIF")));
		ForwardMenuItem.setMnemonic('F');
		ForwardMenuItem.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onForward(); }}
		);

		JMenuItem RefreshMenuItem = new JMenuItem("Refresh", new ImageIcon(cl.getResource("HelpImages/RefreshImage.GIF")));
		RefreshMenuItem.setMnemonic('R');
		RefreshMenuItem.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onRefresh(); }}
		);

		//  Edit Menu Items

		JMenuItem CopyMenuItem = new JMenuItem("Copy", new ImageIcon(cl.getResource("HelpImages/CopyImage.GIF")));
		CopyMenuItem.setMnemonic('C');
		CopyMenuItem.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onCopy(); }}
		);

		//  Build Menu System

		JMenu FileMenu = new JMenu("File");
		FileMenu.setMnemonic('F');

		//FileMenu.addSeparator();
		FileMenu.add(ExitMenuItem);

		JMenu NavigateMenu = new JMenu("Navigate");
		NavigateMenu.setMnemonic('N');

		NavigateMenu.add(BackMenuItem);
		NavigateMenu.add(ForwardMenuItem);
		NavigateMenu.addSeparator();
		NavigateMenu.add(RefreshMenuItem);
		NavigateMenu.addSeparator();
		NavigateMenu.add(ContentsMenuItem);

		JMenu EditMenu = new JMenu("Edit");
		EditMenu.setMnemonic('E');

		EditMenu.add(CopyMenuItem);

		MainMenu = new JMenuBar();
		MainMenu.add(FileMenu);
		MainMenu.add(EditMenu);
		MainMenu.add(NavigateMenu);

	}

	private void createToolBar()
	{
		MainTools = new JToolBar("PascGalois Tools");
		MainTools.setFloatable(false);

		ToolbarButton CopyTool = new ToolbarButton(new ImageIcon(cl.getResource("HelpImages/CopyImage.GIF")), "Copy", 25, 25);
		CopyTool.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onCopy(); }}
		);

		ToolbarButton BackTool = new ToolbarButton(new ImageIcon(cl.getResource("HelpImages/Back.GIF")), "Back", 25, 25);
		BackTool.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onBack(); }}
		);

		ToolbarButton ForwardTool = new ToolbarButton(new ImageIcon(cl.getResource("HelpImages/Forward.GIF")), "Forward", 25, 25);
		ForwardTool.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onForward(); }}
		);

		ToolbarButton RefreshTool = new ToolbarButton(new ImageIcon(cl.getResource("HelpImages/RefreshImage.GIF")), "Refresh", 25, 25);
		RefreshTool.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onRefresh(); }}
		);

		ToolbarButton ContentsTool = new ToolbarButton(new ImageIcon(cl.getResource("HelpImages/HelpImage.GIF")), "Contents", 25, 25);
		ContentsTool.addActionListener( new ActionListener()
			{	public void actionPerformed(ActionEvent evt)
      	{ onContents(); }}
		);

		MainTools.add(BackTool);
		MainTools.add(ForwardTool);
		MainTools.add(new JLabel(new ImageIcon(cl.getResource("HelpImages/ToolSeparatorImage.GIF"))));
		MainTools.add(RefreshTool);
		MainTools.add(new JLabel(new ImageIcon(cl.getResource("HelpImages/ToolSeparatorImage.GIF"))));
		MainTools.add(ContentsTool);
		MainTools.add(new JLabel(new ImageIcon(cl.getResource("HelpImages/ToolSeparatorImage.GIF"))));
		MainTools.add(CopyTool);
	}

	private void onContents()
	{
    try
    {
			html.setPage(cl.getResource(ContentsPath));
  		while (historyPos < history.size()-1)
  			history.remove(history.size()-1);
  		history.add(cl.getResource(ContentsPath));
  		historyPos = history.size()-1;
		}
    catch (Exception e)
    {
      System.out.println("Exception: " + e);
    }
	}

	private void onCopy()
	{
		StringSelection ClipString = new StringSelection(html.getSelectedText());
		clipboard.setContents(ClipString, null);
	}

	private void onBack()
	{
		if (historyPos > 0)
		{
			historyPos--;
			try
			{
    		html.setPage(history.get(historyPos));
			}
			catch (IOException ioe)
			{
    		System.out.println("IOE: " + ioe);
			}
		}
	}

	private void onForward()
	{
		if (historyPos < history.size()-1)
		{
			historyPos++;
			try
			{
    		html.setPage(history.get(historyPos));
			}
			catch (IOException ioe)
			{
    		System.out.println("IOE: " + ioe);
			}
		}
	}

	private void onRefresh()
	{
		try
		{
			Document doc = html.getDocument();
   		doc.putProperty(Document.StreamDescriptionProperty, null);
  		html.setPage(history.get(historyPos));
		}
		catch (IOException ioe)
		{
  		System.out.println("IOE: " + ioe);
		}
	}

}

