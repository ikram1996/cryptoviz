					   

public class DES
{

	// http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf

	//  http://www.tero.co.uk/des/test.php



	//initial permutation (64bits)
	public final static int[] IP_Map = {58, 50, 42, 34, 26, 18, 10,  2,
					 60, 52, 44, 36, 28, 20, 12,  4,
					 62, 54, 46, 38, 30, 22, 14,  6,
					 64, 56, 48, 40, 32, 24, 16,  8,
					 57, 49, 41, 33, 25, 17,  9,  1,
					 59, 51, 43, 35, 27, 19, 11,  3,
					 61, 53, 45, 37, 29, 21, 13,  5,
					 63, 55, 47, 39, 31, 23, 15,  7};


	//inverse of initial permutation (64bits)
	public final static int[] IPinverse_Map = {40,  8, 48, 16, 56, 24, 64, 32,
						39,  7, 47, 15, 55, 23, 63, 31,
						38,  6, 46, 14, 54, 22, 62, 30,
						37,  5, 45, 13, 53, 21, 61, 29,
						36,  4, 44, 12, 52, 20, 60, 28,
						35,  3, 43, 11, 51, 19, 59, 27,
						34,  2, 42, 10, 50, 18, 58, 26,
						33,  1, 41,  9, 49, 17, 57, 25};


	//permuted choice 1 -- used for subkey generation
	public final static int[] PC1_Map = {57, 49, 41, 33, 25, 17,  9,
					   1, 58, 50, 42, 34, 26, 18,
					   10,  2, 59, 51, 43, 35, 27,
					   19, 11,  3, 60, 52, 44, 36,
					   63, 55, 47, 39, 31, 23, 15,
					    7, 62, 54, 46, 38, 30, 22,
					   14,  6, 61, 53, 45, 37, 29,
					   21, 13,  5, 28, 20, 12,  4};


	//permuted choice 2 -- used for subkey generation
	public final static int[] PC2_Map = {14, 17, 11, 24,  1,  5,
					   3, 28, 15,  6, 21, 10,
					  23, 19, 12,  4, 26,  8,
					  16,  7, 27, 20, 13,  2,
					  41, 52, 31, 37, 47, 55,
					  30, 40, 51, 45, 33, 48,
					  44, 49, 39, 56, 34, 53,
					  46, 42, 50, 36, 29, 32};


	//left shift of two 28-bit blocks at the same time -- used for subkey generation
	public final static int[] LS_Map =  { 2,  3,  4,  5,  6,  7,  8,
					   	9, 10, 11, 12, 13, 14, 15,
						16, 17, 18, 19, 20, 21, 22,
						23, 24, 25, 26, 27, 28,  1,

					  30, 31, 32, 33, 34, 35, 36,
					  37, 38, 39, 40, 41, 42, 43,
					  44, 45, 46, 47, 48, 49, 50,
					  51, 52, 53, 54, 55, 56, 29};
					  

	public final static int[] double_LS_Map =  { 3,  4,  5,  6,  7,  8,
					   	9, 10, 11, 12, 13, 14, 15,
						16, 17, 18, 19, 20, 21, 22,
						23, 24, 25, 26, 27, 28,  1, 2,

					  31, 32, 33, 34, 35, 36,
					  37, 38, 39, 40, 41, 42, 43,
					  44, 45, 46, 47, 48, 49, 50,
					  51, 52, 53, 54, 55, 56, 29, 30};					  
					  
					  
					  
					  


	//expand 32 bits into 48
	public final static int[] E_Map = {32,  1,  2,  3,  4,  5,  4,  5,  6,  7,  8,  9,  8,  9, 10, 11,
					12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
					22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,  1};


	//32-->32 permutation function
	public final static int[] P_Map = {16,  7, 20, 21, 29, 12, 28, 17,  1, 15, 23, 26,  5, 18, 31, 10,
					 2,  8, 24, 14, 32, 27,  3,  9, 19, 13, 30,  6, 22, 11,  4, 25};


	public final static int[] S_Boxes = {
14,  4, 13,  1,  2, 15, 11,  8,   3, 10,  6, 12,  5,  9,  0,  7,
 0, 15,  7,  4, 14,  2, 13,  1,  10,  6, 12, 11,  9,  5,  3,  8,
 4,  1, 14,  8, 13,  6,  2, 11,  15, 12,  9,  7,  3, 10,  5,  0,
15, 12,  8,  2,  4,  9,  1,  7,   5, 11,  3, 14, 10,  0,  6, 13,

15,  1,  8, 14,  6, 11,  3,  4,   9,  7,  2, 13, 12,  0,  5, 10,
 3, 13,  4,  7, 15,  2,  8, 14,  12,  0,  1, 10,  6,  9, 11,  5,
 0, 14,  7, 11, 10,  4, 13,  1,   5,  8, 12,  6,  9,  3,  2, 15,
13,  8, 10,  1,  3, 15,  4,  2,  11,  6,  7, 12,  0,  5, 14,  9,

10,  0,  9, 14,  6,  3, 15,  5,   1, 13, 12,  7, 11,  4,  2,  8,
13,  7,  0,  9,  3,  4,  6, 10,   2,  8,  5, 14, 12, 11, 15,  1,
13,  6,  4,  9,  8, 15,  3,  0,  11,  1,  2, 12,  5, 10, 14,  7,
 1, 10, 13,  0,  6,  9,  8,  7,   4, 15, 14,  3, 11,  5,  2, 12,

 7, 13, 14,  3,  0,  6,  9, 10,   1,  2,  8,  5, 11, 12,  4, 15,
13,  8, 11,  5,  6, 15,  0,  3,   4,  7,  2, 12,  1, 10, 14,  9,
10,  6,  9,  0, 12, 11,  7, 13,  15,  1,  3, 14,  5,  2,  8,  4,
 3, 15,  0,  6, 10,  1, 13,  8,   9,  4,  5, 11, 12,  7,  2, 14,

 2, 12,  4,  1,  7, 10, 11,  6,   8,  5,  3, 15, 13,  0, 14,  9,
14, 11,  2, 12,  4,  7, 13,  1,   5,  0, 15, 10,  3,  9,  8,  6,
 4,  2,  1, 11, 10, 13,  7,  8,  15,  9, 12,  5,  6,  3,  0, 14,
11,  8, 12,  7,  1, 14,  2, 13,   6, 15,  0,  9, 10,  4,  5,  3,

12,  1, 10, 15,  9,  2,  6,  8,   0, 13,  3,  4, 14,  7,  5, 11,
10, 15,  4,  2,  7, 12,  9,  5,   6,  1, 13, 14,  0, 11,  3,  8,
 9, 14, 15,  5,  2,  8, 12,  3,   7,  0,  4, 10,  1, 13, 11,  6,
 4,  3,  2, 12,  9,  5, 15, 10,  11, 14,  1,  7,  6,  0,  8, 13,

 4, 11,  2, 14, 15,  0,  8, 13,   3, 12,  9,  7,  5, 10,  6,  1,
13,  0, 11,  7,  4,  9,  1, 10,  14,  3,  5, 12,  2, 15,  8,  6,
 1,  4, 11, 13, 12,  3,  7, 14,  10, 15,  6,  8,  0,  5,  9,  2,
 6, 11, 13,  8,  1,  4, 10,  7,   9,  5,  0, 15, 14,  2,  3, 12,

13,  2,  8,  4,  6, 15, 11,  1,  10,  9,  3, 14,  5,  0, 12,  7,
 1, 15, 13,  8, 10,  3,  7,  4,  12,  5,  6, 11,  0, 14,  9,  2,
 7, 11,  4,  1,  9, 12, 14,  2,   0,  6, 10, 13, 15,  3,  5,  8,
 2,  1, 14,  7,  4, 10,  8, 13,  15, 12,  9,  0,  3,  5,  6, 11};


	//given a 64-bit plaintext and a 64-bit key,
	//generates all 16 subkeys and
	//performs all 16 rounds of the entire DES algorithm,
	//including initial and final permutations
	//returns a 64-bit ciphertext
	public static BitList encrypt(BitList in, BitList key)
	{
		BitList[] keys = generateKeys(key);

		BitList out = permute(in,IP_Map);
		
		for(int i=0;i<16;i++)
			out=round(out,keys[i]);

		return permute(out,IPinverse_Map);

	}

	//reverse the previous -- only difference is order the keys are applied
	public static BitList decrypt(BitList in, BitList key)
	{
		BitList[] keys = generateKeys(key);

		BitList out = permute(in,IP_Map);
		
		for(int i=15;i>=0;i--)
			out=round(out,keys[i]);

		return permute(out,IPinverse_Map);

	}





	//takes a 64-bit key (8 of these bits are irrelevant) and returns an array of 16 48-bit subkeys
	public static BitList[] generateKeys(BitList key)
	{

		BitList[] out = new BitList[16];
		
		BitList temp= permute(key,PC1_Map);

		for(int i=1;i<=16;i++)
		{
			//shift left once...
			temp= permute(temp,LS_Map);

			//...most rounds have two shifts
			switch(i)
			{
				case  1: case  2: case  9: case 16: break;
				default: temp= permute(temp,LS_Map); break;
			}

			out[i-1]=permute(temp,PC2_Map);
		}

		return out;
	}
		
	//takes a 64-bit input and a 48-bit key
	//returns a 64-bit output.
	public static BitList round(BitList in, BitList key)
	{
		//the right half of the input is copied directly to the left half of the output
		BitList out=in.get(32,64);
		
		//also we XOR the left half of the input with f(right_input,key) 
		BitList temp=in.get(0,32);
		temp.xor(cipherFunction(in.get(32,64), key));
		
		//...and then append that as the right half of the output
		for(int i=0;i<32;i++)
			out.set(i+32,temp.get(i));


		return out;
	}





	//this function is called just "f" in the pdf
	//takes a 32-bit "R" and 48 bit subkey, returns 32 bit output
	public static BitList cipherFunction(BitList in, BitList key) 
	{
		BitList out = permute(in, E_Map);
		out.xor(key);
		return S_Boxes(out);
	}




	//returns a 32-bit bitset, given a 48-bit input bitset
	public static BitList S_Boxes(BitList in)
	{
		BitList out = new BitList(32);		

		for(int i=0;i<8;i++)
		{
			BitList temp=S_Box(i+1,in.get(6*i,6*i+6));
			for(int j=0;j<4;j++)
				if (temp.get(j)) out.set(4*i+j);
		}

	return out;
		
	}



	//returns a 4-bit bitset, given a s-box and a 6-bit input bitset
	//note that the "0" bit is the most significant in our bitsets
	public static BitList S_Box(int box, BitList in)
	{
		//boxes are numbered 1 through 8
		int x=64*(box-1);
		if (in.get(0)) x+=32;
		if (in.get(5)) x+=16;
		if (in.get(1)) x+= 8;
		if (in.get(2)) x+= 4;
		if (in.get(3)) x+= 2;
		if (in.get(4)) x+= 1;

		return intToNibble(S_Boxes[x]);

	}    


	//note that the "0" bit is the most significant in our bitsets
	private static BitList intToNibble(int x)
	{
		BitList out = new BitList(4);
		
		for(int i=3;i>=0;i--)
		{
			if ( x != 2*(x/2) ) out.set(i);
			x=x/2;
		}
		return out;
	}




	//takes an input array of boolean, permutes it according to the input map, returns the result in a new boolean array
	public static BitList permute(BitList in, int[] map)
	{
		int insize = in.size();
		int outsize = map.length;

		BitList out = new BitList(outsize);

		for(int i=0;i<outsize;i++)
		{
			int temp=map[i];
			if (temp>insize) out.set(i,false); //probably should throw an exception here
			else out.set(i,in.get(temp-1)); //minus one because arrays are zero indexed but our map values are 1 indexed
		}
	

		return out;
	}


//same as previous function except expands an array of objects instead of an array of booleans...
//this could be handy for visualization
//or maybe not
	public static Object[] permute (Object[] in, int[] map)
	{  
		int insize = in.length;
		int outsize = map.length;

		Object[] out = new Object[outsize];

		for(int i=0;i<outsize;i++)
		{
			int temp=map[i];
			if (temp>insize) return null;
			out[i]=in[temp-1];  //should probably clone here rather than returning a reference. oh well
		}

		return out;
	}




/*public static void main(String[] args) {




	}*/


}
