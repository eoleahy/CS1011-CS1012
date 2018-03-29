import java.util.Random;
import java.util.Scanner;

public class Cipher
{
	public static final char[] ALPHABET_ARRAY = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
	public static void main(String[] args) 
	{
		System.out.println("Type out your sentence to be encrypted.");
		Scanner inputScanner = new Scanner(System.in);
		String inputString = inputScanner.nextLine();
		inputScanner.close();
		String LCinputString = inputString.toLowerCase();
		char[] inputChar= LCinputString.toCharArray();
		
		char[] encryptedAlphabet = createCipher(ALPHABET_ARRAY).clone();
			
		char[] encryptedString = encrypt( ALPHABET_ARRAY, encryptedAlphabet, inputChar).clone();
		
		System.out.println("Your encrypted sentence is: ");
		System.out.println(encryptedString);
		
		System.out.println("Your decrypted sentence is: ");
		System.out.println( crypt( encryptedAlphabet, encryptedString, ALPHABET_ARRAY));
	}
	
	public static char[] createCipher(char[] ALPHABET_ARRAY) //adopted from Randomise Order on blackboard from week 12, by Kenneth Dawson-Howe
	{
		char[] encryptedAlphabet = ALPHABET_ARRAY.clone();
		Random generator = new Random();
		for (int index=0; index<ALPHABET_ARRAY.length; index++ )
		{
			int otherIndex = generator.nextInt(ALPHABET_ARRAY.length);
		    int temp = encryptedAlphabet[index];
		    encryptedAlphabet[index] = encryptedAlphabet[otherIndex];
		    encryptedAlphabet[otherIndex] = (char) temp;
		    
		}
		return encryptedAlphabet;
	}
	
	public static char[] encrypt(char[] ALPHABET_ARRAY, char[]encryptedAlphabet,  char[] inputChar)
	{
		for(int index=0; index< inputChar.length; index++)
		{
			for(int count=0; count < ALPHABET_ARRAY.length; count++)
			{
				if(inputChar[index] == ALPHABET_ARRAY[count])
					inputChar[index] = encryptedAlphabet[count];	
			}
		}
		
		char[] encryptedString = inputChar.clone();
		return encryptedString;
	}	
	
	public static String crypt(char[] encryptedAlphabet, char[] encryptedString, char[] ALPHABET_ARRAY)
	{
		for(int index=0; index < encryptedString.length; index++)
		{
			for(int count=0; count < encryptedAlphabet.length;count++)
			{
				if(encryptedString[index] == encryptedAlphabet[count])
					encryptedString[index] = ALPHABET_ARRAY[count];
			}
		}
		
		String decryptedString = new String( encryptedString);
		return decryptedString;
	}	
}
	


