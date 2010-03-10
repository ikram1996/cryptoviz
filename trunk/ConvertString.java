
import java.awt.*;
import java.io.*;
import java.util.*;

public class ConvertString{


	//This method is no longer needed (hopefully)
	/*public static String stringToBinary(String line){

		String binaryString = new String();

		for(int i = 0; i < line.length(); i++){//only do 64 bits of the input
			int x = (int)line.charAt(i);
			binaryString += Integer.toBinaryString(x);
			if(binaryString.length()>63) return binaryString;//only return the first 64 bit block
		}

		return binaryString;
	}*/


	public static String charToAscii(char letter)
	{
		String output = "";
		int ascii = (int)letter;
		for(int j = 0; j<8; j++)//for each 8 bits in the character
			{
				if(j==0){
					if(ascii >= 128){
						output+="1";
						ascii-=128;
						}
					else
						output+="0";
					}
				if(j==1){
					if(ascii >= 64){
						output+="1";
						ascii-=64;
						}
					else
						output+="0";
					}
				if(j==2){
					if(ascii >= 32){
						output+="1";
						ascii-=32;
						}
					else
						output+="0";
					}
				if(j==3){
					if(ascii >= 16){
						output+="1";
						ascii-=16;
						}
					else
						output+="0";
					}
				if(j==4){
					if(ascii >= 8){
						output+="1";
						ascii-=8;
						}
					else
						output+="0";
					}
				if(j==5){
					if(ascii >= 4){
						output+="1";
						ascii-=4;
						}
					else
						output+="0";
					}
				if(j==6){
					if(ascii >= 2){
						output+="1";
						ascii-=2;
						}
					else
						output+="0";
					}
				if(j==7){
					if(ascii >= 1){
						output+="1";
						ascii-=1;
						}
					else
						output+="0";
					}
			}
		return output;
	}


	public static String stringToAscii(String input)
	{//will truncate to 64 bit
		String output = "";	
		int length;
		if(input.length() < 8) length = input.length();
		else length = 8;	
		for(int i =0; i<length; i++)//for each letter in the 64 bits
		{
			char letter = input.charAt(i);
			//System.out.println((int)letter);
			output += charToAscii(letter);
		}		
		return	output;
	}

	public static BitList stringToBinary(String binary)
	{
		binary = stringToAscii(binary);
		BitList bits = new BitList(64);
		for(int i = 0; i<64; i++)
		{
			if(i >= binary.length()  )
			{
				System.out.println("Debug: string too short");
				break;
			}
			
			if(binary.charAt(i) == '1')
			{
				bits.set(i, true);
				//System.out.print(bits.get(i));
			}
		}
		return bits;
		
	}


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
		System.out.println(out);
		return out;
		
	}
	
	
	
	
	


/*	public static void main(String[] args){

	BitList b=new BitList(8);

	for(int i=0;i<254;i++)
	{
		int k=8;
		do
		{
			k--;
			b.flip(k);
		}
		while (!b.get(k));

		System.out.print(i+1+" "+BitListToChar(CharToBitList (BitListToChar(b)))+"     ");
		
		if ((i%8)==0) System.out.println();
			
		
			
		

	}
	
	
	}*/



/*
	public static void main(String[] args){
	
	
		String s = "ABCabc your mom";
		BitList b = StringToBitList(s);
		String t = BitListToString(b);
		
		System.out.println(s);
		
		for (int i=0;i<b.length();i++)
		{
		
			if (b.get(i)) System.out.print(1);
			else System.out.print(0);
			
		
			if((i%8)==7) System.out.println();
				
		}
				
		System.out.println(t);
	}
*/	
}
