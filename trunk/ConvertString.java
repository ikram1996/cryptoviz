
// ConvertString.java
// functions for converting between BitLists and ascii/hex
// these should have been in BitList.java but oh well


import java.awt.*;
import java.io.*;
import java.util.*;

public class ConvertString{


//functions by john
	public static char BitListToChar(BitList in)
	{
		int x=0;
		int p2=128;
		for (int i=0;i<8;i++)
		{
			if (in.get(i)) x+=p2;
			p2=p2/2;
		}
		return (char)x;
	}
	
	
		
	public static String BitListToString (BitList in)
	{
		if ( (in.size()%8)!=0 ) return null;
		
		String out=new String();
	
		for(int i=0;i<in.size();i+=8)
			out=out+BitListToChar(in.get(i,i+8));
		
		return out;		
	
	}	
	
	
	public static BitList CharToBitList (char in)
	{
		BitList out=new BitList(8);
		int k=128;
		for (int i=0;i<8;i++)
		{
			if (in>=k)
			{
				out.set(i);
				in-=k;
			}
			k=k/2;
		}
		return out;
	}
	
	
	public static BitList StringToBitList (String in)
	{
		BitList out=new BitList(8*in.length());
		
		for (int i=0;i<in.length();i++)
		{
			BitList temp = CharToBitList(in.charAt(i));
			for(int j=0;j<8;j++)
				out.set(8*i+j,temp.get(j));
		}
		return out;
	}
	
	
	public static BitList HexStringToBitList (String in)
	{
		BitList out=new BitList(4*in.length());
		
		for(int i=0;i<in.length();i++)
		{
			int temp = Integer.parseInt(in.substring(i,i+1),16);
			if (temp>7) { temp-=8; out.set(4*i+0);}
			if (temp>3) { temp-=4; out.set(4*i+1);}
			if (temp>1) { temp-=2; out.set(4*i+2);}
			if (temp>0) { temp-=1; out.set(4*i+3);}			
		}
		//System.out.println(out);
		return out;
		
	}	

}
