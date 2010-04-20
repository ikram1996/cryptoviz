

// BitList.java
// extends java's built-in BitSet class with
// a bunch of functions for creating, manipulating, and printing
//
// BitLists are a core component for our DES engine.



import java.lang.StringBuilder;
import java.util.BitSet;
import java.awt.Color;
import java.lang.Math;




public class BitList extends BitSet
{
	private int size;
	public Color c[];
	
	public BitList() { super(0); size=0; }
	
	//create an empty bitlist of size s
	public BitList(int s)
	{
		super(s);
		size=s;
		c = new Color[size];		
		colorize();
	}	
	
	//create a bitlist of length 8 with contents based on a char
	public BitList(char in)
	{
		super(8);
		size=8;
		c = new Color[8];
		colorize();
					
		int k=128;
		for (int i=0;i<8;i++)
		{
			if (in >= k)
			{
				set(i);
				in -= k;
			}
			k=k/2;
		}
	}


	//create a bitlist from an ascii string
	public BitList (String in)
	{
		super(8*in.length());
		size=8*in.length();
		c = new Color[size];
		colorize();	
	
		for (int i=0;i<in.length();i++)
		{
			BitList temp = new BitList(in.charAt(i));
			for(int j=0;j<8;j++)
				set(8*i+j,temp.get(j));
		}
	}
	




	//copy constructor
	public BitList(BitList b)
	{
		super(b.size());
		size=b.size();
		c = new Color[size];
		xor(b); //fast way of doing this=b
		
		//copy colors
		for(int i=0;i<size;i++) c[i]=b.c[i];
		
	}	

	
	private void colorize()
	{
		for(int i=0;i<size;i++)
		{
			if (i<size/2) c[i]=new Color((int)(255-500*i/size),0,(int)(255-255*Math.abs(2*i-size)/size));
			else c[i]=new Color(0,(int)((500*i-250*size )/size),(int)(  255-255*Math.abs(2*i-size)/size));
		}	
	}	
	
	
	public int length() { return size;}  //fuck it, we never use the "real" (superclass) length()
	
	public int size() { return size;}

	public String toString()
	{
		return toBinaryString();
	}


	public String toBinaryString()
	{
		StringBuilder out=new StringBuilder();
		for(int i=0;i<size;i++)
		{
			if (get(i)) out.append('1');
			else out.append('0');
		}
		return out.toString();
	}
	
	
	public String toHexString()
	{
		StringBuilder out=new StringBuilder();		

		for(int i=0;i<size-3;i+=4) //if we are not a multiple of 4, the last bits are discarded...
		{
			int temp=0;
			for(int j=0;j<4;j++)
			{
				temp=2*temp;
				if (get(i + j )) temp++;
			}				
			out.append(Integer.toHexString(temp));
			
		}
		return out.toString();
	}


	
	public String toASCII()
	{
		StringBuilder out=new StringBuilder();
		for(int i=0;i<size-7;i+=8)	//if we are not a multiple of 8, the last bits are discarded...
			out.append(ConvertString.BitListToChar(get(i,i+8)));
		return out.toString();	
	}
	
	public BitList get(int start, int end)
	{
		BitList out = new BitList(end - start);
		out.xor(super.get(start,end));
		return out;
	}
	
	


}

