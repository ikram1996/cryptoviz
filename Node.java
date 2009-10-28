import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class Node{

public double x, y;
public boolean bit = false;

	Node(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void setBit(boolean sign){
		bit = sign;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

}
