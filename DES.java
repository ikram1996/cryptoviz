					   

public class DES
{

	// http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf



//todo: s-boxes and other shit


	public final static int[] IP_Map = {58, 50, 42, 34, 26, 18, 10,  2,
					 60, 52, 44, 36, 28, 20, 12,  4,
					 62, 54, 46, 38, 30, 22, 14,  6,
					 64, 56, 48, 40, 32, 24, 16,  8,
					 57, 49, 41, 33, 25, 17,  9,  1,
					 59, 51, 43, 35, 27, 19, 11,  3,
					 61, 53, 45, 37, 29, 21, 13,  5,
					 63, 55, 47, 39, 31, 23, 15,  7};

	public final static int[] IPinverse_Map = {40,  8, 48, 16, 56, 24, 64, 32,
						39,  7, 47, 15, 55, 23, 63, 31,
						38,  6, 46, 14, 54, 22, 62, 30,
						37,  5, 45, 13, 53, 21, 61, 29,
						36,  4, 44, 12, 52, 20, 60, 28,
						35,  3, 43, 11, 51, 19, 59, 27,
						34,  2, 42, 10, 50, 18, 58, 26,
						33,  1, 41,  9, 49, 17, 57, 25};

	public final static int[] PC1_Map = {57, 49, 41, 33, 25, 17,  9,
					   1, 58, 50, 42, 34, 26, 18,
					   10,  2, 59, 51, 43, 35, 27,
					   19, 11,  3, 60, 52, 44, 36,
					   63, 55, 47, 39, 31, 23, 15,
					    7, 62, 54, 46, 38, 30, 22,
					   14,  6, 61, 53, 45, 37, 29,
					   21, 13,  5, 28, 20, 12,  4};

	public final static int[] PC2_Map = {14, 17, 11, 24,  1,  5,
					   3, 28, 15,  6, 21, 10,
					  23, 19, 12,  4, 26,  8,
					  16,  7, 27, 20, 13,  2,
					  41, 52, 31, 37, 47, 55,
					  30, 40, 51, 45, 33, 48,
					  44, 49, 39, 56, 34, 53,
					  46, 42, 50, 36, 29, 32};

	public final static int[] LS_Map =  { 2,  3,  4,  5,  6,  7,  8,
					   9, 10, 11, 12, 13, 14, 15,
					  16, 17, 18, 19, 20, 21, 22,
					  23, 24, 25, 26, 27, 28,  1,
					  30, 31, 32, 33, 34, 35, 36,
					  37, 38, 39, 40, 41, 42, 43,
					  44, 45, 46, 47, 48, 49, 50,
					  51, 52, 53, 54, 55, 56, 29};


	public final static int[] E_Map = {32,  1,  2,  3,  4,  5,  4,  5,  6,  7,  8,  9,  8,  9, 10, 11,
					12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
					22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,  1};

	public final static int[] P_Map = {16,  7, 20, 21, 29, 12, 28, 17,  1, 15, 23, 26,  5, 18, 31, 10,
					 2,  8, 24, 14, 32, 27,  3,  9, 19, 13, 30,  6, 22, 11,  4, 25};


	
	//takes two equal-length (hopefully) arrays of boolean, does a bitwise xor and returns a new array with the result
	public static boolean[] XOR(boolean[] in1, boolean[] in2)
	{
		if ( in1.length !=  in2.length) return null; //should exception

		boolean[] out = new boolean[in1.length];

		for(int i=0;i<in1.length;i++)
			out[i]=in1[i]^in2[i]; //thats the xor symbol

		return out;
	}



	//takes an input array of boolean, permutes it according to the input map, returns the result in a new boolean array
	public static boolean[] permute(boolean[] in, int[] map)
	{
		int insize = in.length;
		int outsize = map.length;

		boolean[] out = new boolean[outsize];

		for(int i=0;i<outsize;i++)
		{
			int temp=map[i];
			if (temp>insize) return null; //really should throw an exception here
			out[i]=in[temp-1];  //minus one because arrays are zero indexed but the map values assume 1 indexed
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
			out[i]=in[temp-1];  //should probably clone  here rather than returning a reference
		}

		return out;
	}


	public static boolean[] initialPermutation(boolean[] in)
	{		return permute(in, IP_Map);		}






}
