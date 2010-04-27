/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.util.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class ToolbarButton extends JButton
{
	public ToolbarButton(Icon btnImage, String btnText, String ToolTip, int width, int height)
	{
		setIcon(btnImage);
		setText(btnText);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(0);
		setToolTipText(ToolTip);
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}

	public ToolbarButton(Icon btnImage, String btnText, String ToolTip)
	{
		setIcon(btnImage);
		setText(btnText);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(0);
		setToolTipText(ToolTip);
	}

	public ToolbarButton(Icon btnImage, String ToolTip)
	{
		setIcon(btnImage);
		setToolTipText(ToolTip);
	}

	public ToolbarButton(Icon btnImage, String ToolTip, int width, int height)
	{
		setIcon(btnImage);
		setToolTipText(ToolTip);
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}

		public ToolbarButton(Icon btnImage)
	{
		setIcon(btnImage);
	}

		public ToolbarButton(String btnText)
	{
		setText(btnText);
	}

}
