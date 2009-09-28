import java.util.Random;
import java.util.ArrayList;


public class MonoAlpha
{
	private Tetra tet;
	private Random r;
	
	private char[] originaltext;
	
	private char[] currenttext;
	private char[] currentkey;
	private int currentscore;
	
	private String besttext;
	private String bestkey;
	private int bestscore;
	
	private int jump_distance;
	
			
	public MonoAlpha(String s)
	{
		tet=new Tetra();
		r  = new Random();
	
		originaltext = s.toCharArray();
		
		currenttext = s.toCharArray();
		currentkey="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		currentscore=tet.getValue(currenttext);
		
		besttext= new String ( currenttext);
		bestkey = new String (currentkey);
		bestscore=0;
		
		jump_distance=1;
		
		//System.out.println("New MonoAlpha: "+s);
	}
	
	public boolean conditionalSwap(char x, char y)
	{
		if (x==y) return false;
		
		swap(currenttext,x,y);
		int tempscore=tet.getValue(currenttext);
		if (tempscore<=currentscore)
		{
			swap(currenttext,x,y);
			//System.out.println("Swap "+x+" "+y+" failed: "+tempscore+" < "+currentscore);
			return false;
		}
		else
		{
			swap(currentkey,x,y);
			currentscore=tempscore;
			//System.out.println("Swap "+x+" "+y+": "+new String(currenttext));
			return true;
		}
	}
			

	private void swap(char[] a, char x, char y)
	{
		for (int i=0;i<a.length;i++)
		{
			if ( a[i] ==x) a[i]=y;
				else if ( a[i] ==y ) a[i]=x;
		}
	}
	
	
	private void encode(char[] a, char [] k)
	{
		if (k.length !=26) return;
		for (int i=0;i<a.length;i++)
			a[i] = k[a[i]-'A'];			
	}
	
	private void decode(char[] a, char [] k)
	{
		char [] kinverse =new char[26];
		for(int i=0;i<26;i++)
			kinverse[k[i]-'A']=(char)('A'+i);
			
		encode(a, kinverse);	
	}
	
	
	/*public void test()
	{
		char[] kk="BEASTCDFGHIJKLMNOPQRUVWXYZ".toCharArray();
		System.out.println(new String(currenttext));
		encode(currenttext,kk);
		System.out.println(new String(currenttext));
		decode(currenttext,kk);
		System.out.println(new String(currenttext));
	}*/
	
	private void shotgun()
	{
		ArrayList<Integer> templist= new ArrayList<Integer>();
		for(int i=0;i<26;i++)
			templist.add(i);
		
		for(int i=0;i<26;i++)
		{
			int x = templist.remove(r.nextInt(26-i)).intValue() ;
			swap (currenttext,(char)('A'+i),(char)('a'+x));
			swap (currentkey,(char)('A'+i),(char)('a'+x));
		}
		
		for(int i=0;i<26;i++)
		{
			swap (currenttext,(char)('A'+i),(char)('a'+i));
			swap (currentkey,(char)('A'+i),(char)('a'+i));
		}	

	
	}
	
	
	private void climb()
	{
	for (int i=0;i<10000;i++)
		if (conditionalSwap((char)('A'+r.nextInt(26)),(char)('A'+r.nextInt(26)))) i=0;
	}
	
	private boolean savebest()
	{
		if (currentscore<=bestscore) return false;
		
		besttext= new String ( currenttext);
		bestkey = new String (currentkey);
		bestscore = currentscore;
		return true;
	}

	private boolean restorebest()
	{
		if (currentscore>bestscore) return false;
		
		currenttext=besttext.toCharArray();
		currentkey = bestkey.toCharArray();
		currentscore = bestscore;
		return true;
	}


	
	
	
	public String solve()
	{
		for (int i=0;i<10;i++)
		{
			if (savebest()) i=0;
			shotgun();
			climb();
			restorebest();
		}
	System.out.println("KEY: "+new String(currentkey));
	return new String ( currenttext);
	}
			

	/*public void step()
	{
		for(int i=0;i<100*jump_distance;i++)
		{
			for (int j=0;j<jump_distance;j++)
				swap(currentkey,(char)('A'+r.nextInt(26)),(char)('A'+r.nextInt(26)));
			encode(currenttext,currentkey);
			currentscore=tet.getValue(currenttext);
			if ( savebest() ) { jump_distance=1; return; }
			restorebest();
		}
		jump_distance++;
	}*/
	
	public void step()
	{
		savebest();
		shotgun();
		climb();
		restorebest();
	}
	
	
	
	
	
	
	private void randomizeKey()
	{
		ArrayList<Integer> templist= new ArrayList<Integer>();
		for(int i=0;i<26;i++)
			templist.add(i);
		
		for(int i=0;i<26;i++)
		{
			int x = templist.remove(r.nextInt(26-i)).intValue() ;
			swap (currentkey,(char)('A'+i),(char)('a'+x));
		}
		
		for(int i=0;i<26;i++)
			swap (currentkey,(char)('A'+i),(char)('a'+i));
		
	}
	
			
	
	public String getText()
	{
		return new String(besttext);
	}
	
	public String getKey()
	{
		return new String(bestkey);
	}
	
	public int getScore()
	{
		return bestscore;
	}	
	
	
	public static void main(String args[]) 
	{
		
		
		Random r = new Random();
		MonoAlpha ma = new MonoAlpha(new String(args[0]));	
		//ma.test();
		
	//  System.out.println(ma.solve());
	
		
	
		int x=ma.getScore();
		while (true)
		{
			if (ma.getScore()>x)
			{
				x=ma.getScore();
				System.out.println(x);
				System.out.println(ma.getText());
				
				
			}
		ma.step();
		}
		//*/
	}
			
}