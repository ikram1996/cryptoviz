import java.io.*;

public class ADFGX
{
	private static int[] lookup = {	0,0,0,1,0,
									2,3,0,0,0,
									0,0,0,0,0,
									0,0,0,0,0,
									0,0,0,4,0,0};
	private int keylength;
	private int[] ciphertext;
	
	public MonoAlpha[] MAs;
	public int numMAs;
	
	public ADFGX(String s)
	{
		ciphertext=new int[s.length()];
		for(int i=0;i<s.length();i++)
			ciphertext[i]=lookup[(int)(s.charAt(i)-'A')];
		
		keylength=7;
		
		numMAs=1;
		for(int i=1;i<=keylength;i++) numMAs=numMAs*i;
		
		MAs = new MonoAlpha[numMAs];	
		
	char[] e1 = "AEFD".toCharArray();
	char[] e2 = "BCG".toCharArray();
	int[] ind1;
	int[] ind2;
	int[] tempints = new int[s.length()];
	char[] tempchars = new char[s.length()/2];
	char[] key=new char[7];
	int z=0;
	PermutationGenerator x = new PermutationGenerator (4);
	while (x.hasMore ())
	{
		ind1 = x.getNext ();
			
		PermutationGenerator y = new PermutationGenerator (3);
		while (y.hasMore ())
		{
			ind2 = y.getNext ();
			
			for (int i = 0; i < ind1.length; i++) 
				key[i]=e1[ind1[i]];			
			for (int i = 0; i < ind2.length; i++) 
				key[i+ind1.length]=e2[ind2[i]];
						
			
			for (int j=0;j<s.length();j++)
				tempints[j]=ciphertext[j/keylength  +  key[j%keylength]-'A'];

			for (int j=0;j<s.length()/2;j++)
			{
				tempchars[j]=(char)('A' + 5*tempints[2*j] + tempints[2*j+1]);
				if(tempchars[j]>'I') tempchars[j]++;
			}
			
			MAs[z]=new MonoAlpha(new String(tempchars));
			
			z++;	
			
			System.out.println(z+": "+new String(key));

			
		}
	}
		
		
		

	}


	public static void main(String args[]) 
	{
		ADFGX  xx= new ADFGX(args[0]);
	
		int bestscore=0;
		
		while (true)
			for(int i=0;i<144;i++)
			{
				xx.MAs[i].step();
				if (xx.MAs[i].getScore()>bestscore)
				{
					bestscore=xx.MAs[i].getScore();
					System.out.println(i+":  "+bestscore+" "+xx.MAs[i].getText());
				}
				else
					System.out.println(i+":  "+xx.MAs[i].getScore());
			}			
	
	}

}