
import java.lang.StringBuilder;
import java.util.BitSet;
import java.awt.Color;




public class BitList extends BitSet
{
	private int size;
	public Color c[];  
	
	public BitList() { super(0); size=0; }
	public BitList(int s)
	{
		super(s);
		size=s;
		for(int i=0;i<size;i++) c[i]=new Color((int)(255-255*i/size),(int)(20+100*i/size),(int)(50+200*i/size));
		
	}


	//copy constructor
	public BitList(BitList b)
	{
		super(b.size());
		size=b.size();
		xor(b); //fast way of doing this=b
		
		//copy colors
		for(int i=0;i<size;i++) c[i]=b.c[i];
		
	}	
	
	public int length() { return size;}  //fuck it, we never use the "real" length()
	
	public int size() { return size;}

	public String toString()
	{
		StringBuilder out=new StringBuilder();
		for(int i=0;i<size;i++)
		{
			if (get(i)) out.append('1');
			else out.append('0');
		}
		return out.toString();
	}

	
	public String toASCII()
	{
		StringBuilder out=new StringBuilder();
		for(int i=0;i<size-7;i+=8)	//if we are not a multiple of 8, the last bits are discarded...
			out.append(ConvertString.BitListToChar(get(i,i+8)));
		return out.toString();	
	}
	
	public BitList get(int start, int end)
	{
		BitList out = new BitList(end - start);
		out.xor(super.get(start,end));
		return out;
	}
	
	


}
