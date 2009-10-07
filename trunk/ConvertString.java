


import java.awt.*;
import  java.io.*;
import java.util.*;

public class ConvertString{



	public static String stringToBinary(String line){

		String binaryString = new String();

		for(int i = 0; i < line.length(); i++){//only do 64 bits of the input
			int x = (int)line.charAt(i);
			binaryString += Integer.toBinaryString(x);
			if(binaryString.length()>63) return binaryString;//only return the first 64 bit block
		}

		return binaryString;//incase the plaintext was less than 64 bits
	}

/*
	public static String stringToAscii(String input)
	{//will truncate to 64 bit
		String output = "";		
		for(int i =0; i<8; i++)//for each letter in the 64 bits
		{
			char letter = input.charAt(i);
			//System.out.println((int)letter);
			int ascii = (int)letter;
			for(int j = 0; j<8; j++)//for each 8 bits in the character
			{
				if(j==0){if(ascii > 128)}
				if(j==1){}
				if(j==2){}
				if(j==3){}
				if(j==4){}
				if(j==5){}
				if(j==6){}
				if(j==7){}
			}
		}		
		return	output;
	}*/

	public static BitSet asciiToBinary(String binary)
	{
		BitSet bits = new BitSet(64);
		for(int i = 0; i<64; i++)
		{
			if(i >= binary.length())
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
	public static char BitSetToChar(BitSet in)
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
	
/*	
	public static String BitSetToString (BitSet in)
	{
		for(int i=0;i<in.size()/8
	
	}	*/
	
	
	
	public static BitSet CharToBitSet (char in)
	{
		BitSet out=new BitSet(8);
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
	
	
	
	


	public static void main(String[] args){

	BitSet b=new BitSet(8);

	for(int i=0;i<254;i++)
	{
		int k=8;
		do
		{
			k--;
			b.flip(k);
		}
		while (!b.get(k));

		System.out.print(i+1+" "+BitSetToChar(CharToBitSet (BitSetToChar(b)))+"     ");
		
		if ((i%8)==0) System.out.println();
			
		
			
		

	}
	}





/*
		int character=0;
		int index = 0;
		boolean[] out= new boolean[64];	
		for(int i = 0; i < 8; i++)
		{	if(i >= input.length())
			{	System.out.println("Debug: string too short");
				break;}
			
			character = input.charAt(i);
			for(int j=0; j<8; j++)
			{	if((character - Math.pow(2,7-j)) > 0)
				{	out[index] = true;
					index++;
					character = character - (int)Math.pow(2,7-j);}
				else
				{out[index] = false;		
					index++;}}}
		return out;}

	
	public static void main(String[] args){

		String test = "hello world";
		System.out.println(stringToBinary(test));
		for(int i =0; i<64; i++)
		{
			if(asciiToBinary(stringToBinary(test)).get(i)==true)
			System.out.print(1);
			else
			System.out.print(0);
		}
		System.out.println();
		stringToAscii(test);
	}
*/	
}
