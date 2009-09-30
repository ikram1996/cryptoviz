					   

public class SDES
{

	//http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf



	
	private static int[] IP_Map = {2, 6, 3, 1, 4, 8, 5, 7};

	private static int[] IPinverse_Map = {4, 1, 3, 5, 7, 2, 8, 6};
//Commented out lines are todo
	//private static int[] PC1_Map = {};

	//private static int[] PC2_Map = {};

	//private static int[] E_Map = {};

	//private static int[] P_Map = {};


//using John's functions (Should work the same)
	public static boolean[] Permute(boolean[] in, int[] map)
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
	public static Object[] Permute (Object[] in, int[] map)
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




}

	


