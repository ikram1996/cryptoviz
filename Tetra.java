import java.io.*;

public class Tetra
{
	private int[] values = new int[26*26*26*26];

	public Tetra()	{ this("TetLogFreq.txt"); }
	
	public Tetra(String filename)
	{
		try{
			FileInputStream fis = new FileInputStream(filename);
			int c;
			int p=0;
			while ((c = fis.read()) != -1)
			{
				values[p]= (char)c - 'A';
				p++;
				//System.out.print(values[p-1]+" ");
				//if ( (p%26) ==0) System.out.println();
			}
			
		}
	 	catch(FileNotFoundException fnfe){System.err.println("FileNotFoundException: " + fnfe.getMessage());}
		catch(IOException ioe){ System.err.println("IOException: " + ioe.getMessage());}
	}
	
	public int getFreq(char[] s)
	{
		if (s.length<4) return 0;
		return getFreq(s[0],s[1],s[2],s[3]);
	}
		
	public int getFreq(String s)
	{
		if ( s.length() <4) return 0;
		return getFreq(s.charAt(0),s.charAt(1),s.charAt(2),s.charAt(3));
	}	
		
	public int getFreq(char w, char x, char y, char z)
	{
		return getFreq(w-'A',x-'A',y-'A',z-'A');
	}
		
	public int getFreq(int w, int x, int y, int z)
	{
		int p=((w*26+x)*26+y)*26+z;
		if ( (p<0) || (p >= 26*26*26*26) ) return 0;
	
		return values[p];
	}
	
	public int getValue(String s)
	{
		int total=0;
		for(int i=0;i<s.length()-3;i++)
			total+=getFreq(s.substring(i,i+4));
		return total;		
	}	
	
	public int getValue(char[] s)
	{
		int total=0;
		for(int i=0;i<s.length-3;i++)
			total+=getFreq(new String(s, i,4));
		return total;		
	}	
	
	public void IJMerge()
	{
		int letterI=8;
		
		for(int w=0;w<26;w++)
			for(int x=0;x<26;x++)
				for(int y=0;y<26;y++)
				{
					int p=((w*26+x)*26+y)*26+letterI;
					values[p]+=values[p+1];
					values[p+1]=0;
				}
					
		for(int w=0;w<26;w++)
			for(int x=0;x<26;x++)
				for(int y=0;y<26;y++)
				{
					int p=((w*26+x)*26+letterI)*26+y;
					values[p]+=values[p+26];
					values[p+26]=0;
				}		

		for(int w=0;w<26;w++)
			for(int x=0;x<26;x++)
				for(int y=0;y<26;y++)
				{
					int p=((w*26+letterI)*26+x)*26+y;
					values[p]+=values[p+26*26];
					values[p+26*26]=0;
				}					
				
		for(int w=0;w<26;w++)
			for(int x=0;x<26;x++)
				for(int y=0;y<26;y++)
				{
					int p=((letterI*26+w)*26+x)*26+y;
					values[p]+=values[p+26*26*26];
					values[p+26*26*26]=0;
				}					
				
	}		
				
	
	
	/*public static void main(String args[]) {
	Tetra a = new Tetra();
	System.out.println("FIII  "+a.getFreq("FIII")	 );
	System.out.println("FIJI  "+a.getFreq("FIJI")	 ); 
	a.IJMerge();
	System.out.println("FIII  "+a.getFreq("FIII")	 );
	System.out.println("FIJI  "+a.getFreq("FIJI")	 ); 
	//System.out.println("THER  "+a.getFreq("THER")	 ); 
	//System.out.println("QZXQ  "+a.getFreq("QZXQ")	 );  
	//System.out.println("CKYO  "+a.getFreq("CKYO")	 );  
	//System.out.println("IKILLEDTHREEDOGSSIGNEDMICHAELVICK  "+a.getValue("IKILLEDTHREEDOGSSIGNEDMICHAELVICK")	 );  
	//System.out.println("ZZFGHHHJKLLLLLLQQQZXXZQWZXEXYYYYY  "+a.getValue("ZZFGHHHJKLLLLLLQQQZXXZQWZXEXYYYYY")	 );  
	
}	*/

}