import java.util.Scanner;
import java.util.Arrays;


public class WordsLinks {

	public static void main(String[] args) 
	{
		
		
		//This program works best if there are no spaces between the words and commas.
		boolean running = true;
		Scanner inputScanner = new Scanner (System.in);
		while(running)
		{
			System.out.println("Enter a comma separated list of words (or an empty list to quit):");
			String input = inputScanner.nextLine();
			String[] inputList = readWordList( input);	
			
			if(input.equals(""))
			{
				System.out.println("Quitting");
				System.exit(0);
			}
			String[] dictionaryArray = readDictionary();
			int validEnglishWords =0;
			int numberOfDifferentWords =0;
			
			for(int count =0; count <inputList.length; count++)
			{
				if(isEnglishWord(dictionaryArray, inputList, count))			
					validEnglishWords++;			
			}
			for(int index=0;index < inputList.length-1;index++)
			{
				if(isDifferentByOne(inputList, index))
				{
					numberOfDifferentWords++;	
				}
			}
			
			boolean validList = isWordChain(inputList, validEnglishWords, numberOfDifferentWords);
			if(validList)
				System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
			else
				System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
		
		}		
	}
	public static String[] readDictionary()
	{
		In in = new In("words.txt");
		String wordList = in.readAll();
		String[] dictionaryArray = wordList.split("\n");
		return dictionaryArray;
	}
	public static String[] readWordList(String input)
	{
		String[] inputList = input.split(",");
		
		return inputList;
	}
	public static boolean isUniqueList(String[] inputList)
	{
		for(int count =0; count< inputList.length; count++)
		{
			for( int index =0; index< inputList.length; index++)
			{
				if(inputList[count].equals(inputList[index]) && count != index)
					return false;
			}
		}
		return true;
	}
	public static boolean isEnglishWord(String[] dictionaryArray, String[] inputList, int count)
	{
		int wordCheck = Arrays.binarySearch(dictionaryArray, inputList[count]);
		if (wordCheck < 1)
			return false;
		else 
			return true;
	}
	public static boolean isDifferentByOne(String[] inputList, int index)
	{
		if(inputList[index].length()!= inputList[index+1].length())
			return false;
		
		int similarLetters = 0;
		int wordLength =inputList[0].length();
		
		for(int count=0; count < wordLength; count++)
		{	
		 if(inputList[index].charAt(count)==inputList[index+1].charAt(count))
				similarLetters++;
		}
		if(similarLetters == wordLength-1)
			return true;
		else
			return false;
	}
	public static boolean isWordChain(String[] inputList, int validEnglishWords, int numberOfDifferentWords)
	{
		if(isUniqueList(inputList) && validEnglishWords == inputList.length && numberOfDifferentWords == inputList.length-1)
			return true;
		else return false;
	}
}
