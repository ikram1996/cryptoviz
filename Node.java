
import java.awt.Color;


public class Node{

public double x, y;
public Color c;
public boolean value;

Node(double x, double y, boolean v, Color c)
{
        this.x = x;
        this.y = y;
        value=v;
        this.c=c;
}
	
Node(double x, double y)
{
        this.x = x;
        this.y = y;
        value=true;
        this.c=Color.GREEN;
}
	
}
