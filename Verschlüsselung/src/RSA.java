import java.io.IOException;

public class RSA 
{

	public static int e = 5; 		//public key  
	public static int N = 21; 		//public key
	public static int d = 5;		//private key
	
	public static void main(String[]args) throws IOException
	{
		//Read the file from the given path and save the content in Step1
		//Null WOULD be the content, when writing something in
		FileIO Step1 = new FileIO("D:\\texte pr\\test.txt", "Null", false);	
		System.out.println("Eingelesen von "+Step1.path+" : "+Step1.path);

		//The content from Step1 is going to be encrypted and written into the given path 		 
		String enc = Encryption(Step1);		
		FileIO Step2 = new FileIO("D:\\texte pr\\Versch.txt", enc , true);
		System.out.println("Der Inhalt von "+Step2.path+" wurde verschlüsselt("+enc+") in "+Step2.path+ " eingetragen.");
		
		//Reads out the encrypted content and decrypt it
		FileIO Step3 = new FileIO("D:\\texte pr\\Versch.txt", "Null", false);		
		String dec = Decryption(Step3);
		
		//Write the decrypted content into the given path
		FileIO Step4 = new FileIO("D:\\texte pr\\Ent.txt", dec, true);
		System.out.println("Der Inhalt von "+Step4.path+" wurde entschlüsselt("+dec+") in "+Step4.path+ " eingetragen.");	
	}
	
	//Method : Encryption
	public static String Encryption(FileIO Step)
	{
		String Step2="";
		for(int i = 0; i < (Step.content).length(); i++)
		{
			//---Fischer Sheet---
			int k1 = Step.content.charAt(i)/20; 
			int k2 = Step.content.charAt(i)%20; 
			
			int c1 = (int) (Math.pow(k1, e)) % N;
			int c2 = (int) (Math.pow(k2, e)) % N;			
			//-------------------
			//Code = ?
			Step2 += (char)c1+""+(char)c2;
		}
		return Step2;
	}

	//Method : Decryption
	public static String Decryption(FileIO Step)
	{		
		String Enc = "";
		for (int i = 0; i < (Step.content).length()-1; i++) 
		{
			
			char c = Step.content.charAt(i);				
			char c2 = Step.content.charAt(i+1);
			//---Fischer Sheet---		
			int var1 = (int) (Math.pow(c, d)) % N;
			int var2 = (int) (Math.pow(c2, d)) % N;
			//-------------------
			//Decryption + write on string Enc
			char letter = (char) ((var1 * 20) + var2);						
			Enc += letter;
			i++;		
		}
		return Enc;	
	}
}
