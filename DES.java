

public class DES
{

	private static int[] E_Map = {32,  1,  2,  3,  4,  5,  4,  5,  6,  7,  8,  9,  8,  9, 10, 11,
					12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
					22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,  1};

	public static boolean[] E (boolean[] in)
	{  

/*  expands 32 bits into 48 like so:

32  1  2  3  4  5
 4  5  6  7  8  9
 8  9 10 11 12 13
12 13 14 15 16 17
16 17 18 19 20 21
20 21 22 23 24 25
24 25 26 27 28 29
28 29 30 31 32  1        */


		if ( in.length !=32) return null; //really should throw an exception here

		boolean[] out = new boolean[48];

		for(int i=0;i<48;i++)
			out[i]=in[E_Map[i]];
		
		return out;

	}

}
