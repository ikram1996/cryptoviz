
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
		}		
		return	output;
	}

	public static BitSet stringToBinary(String binary)
	{
		binary = stringToAscii(binary);
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
/*
	public static String stringToBinary(String in){

		char a;
		BitSet bits;
		String out = new String("");;
		in = in.substring(0,7);//cut it down to 8 characters for now
		for(int i=0; i<8; i++){
			bits = CharToBitSet(in.charAt(i));
			a = BitSetToChar(bits);
			out += a;
		}
		return out;
	}*/
	
	
	
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
*/
	
/*	public static void main(String[] args){

		String test = "hello world";
		//System.out.println(stringToAscii(test));
		for(int i =0; i<64; i++)
		{
			if(stringToBinary(test).get(i)==true)
			System.out.print(1);
			else
			System.out.print(0);
		}
		System.out.println();
		//stringToAscii(test);
	}*/
	
}
