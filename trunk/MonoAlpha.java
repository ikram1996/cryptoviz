import java.util.Random;

public class MonoAlpha
{
	private Tetra tet;
	private Random r = new Random();

	
	private char[] originaltext;
	
	private char[] currenttext;
	private char[] currentkey;
	private int currentscore;
	
	private String besttext;
	private String bestkey;
	private int bestscore;
	
			
	public MonoAlpha(String s)
	{
		tet=new Tetra();
	
		originaltext = s.toCharArray();
		
		currenttext = s.toCharArray();
		currentkey="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		currentscore=tet.getValue(currenttext);
		
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
	
	private void shotgun(int n)
	{
		for(int i=0;i<n;i++)
		{
			char x= (char)('A'+r.nextInt(26));
			char y= (char)('A'+r.nextInt(26));
			swap(currenttext, x,  y);
			swap(currentkey,  x,  y);
		}
	
	}
	
	
	private void climb()
	{
	for (int i=0;i<100;i++)
		if (conditionalSwap((char)('A'+r.nextInt(26)),(char)('A'+r.nextInt(26)))) i=0;
	}
	
	public String solve()
	{
		for (int i=0;i<50;i++)
		{
	
	
	
	
	
	
	
	
	public static void main(String args[]) 
	{
		Random r = new Random();
		MonoAlpha ma = new MonoAlpha("TJJTPBTXGJUNLQLGOALVBTXLTJABTJUGGJUTRLGJUTRLVBTNJLVKLIJTJPLNJHGBTEKBNGLDJUNLUXUGNUGQZJZLGZLRLDLRTBXZNGBNGUODKBTVBTXLTROUPDKBTRJZQZLNQLGNZLNQJPRBGZBGNZJUXZNNZLNPLTNZPJUXZAFDPLBTLKKTBXZNNZLNBRBJNBHQJPRNZLNZLPRLGBRNPFNJOUTBNRJQTQLGLKQLFGWUGNLTBTHZJPNQJJUNJEAFXPLGOEJQKJPEJUKJPCJQJPCJFLKLQJPRQZBHZDFLGGJHBLNBJTDPJUXZNBTNJOKLFLTBTHJTXPUJUGALGGLTRALXALJETJUTGBRBJAGGKJXLTGLTRGLFBTXGLHJTEUGBTXLAJPOZJUGJUNOJUPBTXQZBHZBGJUXZNBTCLBTNJHJTNPJKJPNUPTJEEDUNQZBHZQJUTRLPJUTRAFABTRLQZBPKQBTRJELHJPRLQZBOKLGZJELHJPRLHJPRNZLNQJUKRGOKBNLXLBTLTRLXLBTQJUKRVTBNLXLBTLTRLXLBTJEQJPRGQBNZJUNHJAAUTBHLNBJTJPLTFOJGGBDBKBNFJEHJADBTLNBJTQJPRGQBNZJUNOPJTUTHBLNBJTGBXTBEBHLNBJTJPNPLTGHPBONBJTDUNJUNJEQZBHZTJNQBNZGNLTRBTXQLGDPJUXZNEJPNZLEKUILHJTNBTUJUGHJAOLHNLTRKUHBREKJQLTBTNUBNBJTLCLHBKKLNBTXEPBGGJTJEBKKUABTLNBJTLGBEHLUXZNBTLEKLGZJEKBXZNTBTXJPBTLABGNLDPUONKFPBGBTXNJUTGZPJURLTJDCBJUGGBXTDUNLGBXTLKLGNZLNQJUKRKLGNLTBTGNLTNJTKFNJCLTBGZEJPXJJR");
		
		
			System.out.println(ma.solve());
	}
	
	
	
	


}