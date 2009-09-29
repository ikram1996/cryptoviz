

public class DES
{

	//http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf


	private static int[] E_Map = {32,  1,  2,  3,  4,  5,  4,  5,  6,  7,  8,  9,  8,  9, 10, 11,
					12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
					22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,  1};

	private static int[] P_Map = {16,  7, 20, 21, 29, 12, 28, 17,  1, 15, 23, 26,  5, 18, 31, 10,
					 2,  8, 24, 14, 32, 27,  3,  9, 19, 13, 30,  6, 22, 11,  4, 25};

	public static boolean[] Permute(boolean[] in, int[] map)
	{
		int insize = in.length;
		int outsize = map.length;

		boolean[] out = new boolean[outsize];

		for(int i=0;i<outsize;i++)
		{
			int temp=map[i];
			if (temp>insize) return null; /really should throw an exception here
			out[i]=in[temp-1];
		}

		return out;
	}

//same as previous function except expands an array of objects instead of an array of booleans...
//this could be handy for visualization
//or maybe not
public static Object[] Permute (Object[] in, int[] map)
	{  
		int insize = in.length;
		int outsize = map.length;

		Object[] out = new Object[outsize];

		for(int i=0;i<outsize;i++)
		{
			int temp=map[i];
			if (temp>insize) return null; /really should throw an exception here
			out[i]=in[temp-1].clone();
		}

		return out;
	}




}
