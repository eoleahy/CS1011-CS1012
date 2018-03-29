package HighScores;

import java.util.Scanner;
public class HighScores
{

	public static void main(String[] args)
	{
		System.out.println("How many scores would you like to maintain?");
		Scanner input = new Scanner(System.in);
		int numberOfScores = input.nextInt();
		
		System.out.println("Enter your list of numbers:");
		int arrayOfScores[] = new int[numberOfScores];
		initialiseHighScores( arrayOfScores);
		
		Scanner inputList = new Scanner(System.in);	
			
		for (int index=0; index < arrayOfScores.length; index++) 
			{
				arrayOfScores[index] = inputList.nextInt();	
			}
		System.out.print("The high scores are: ");
		higherThan(inputList, arrayOfScores);
		printHighScores( arrayOfScores);

		}
		
	//This function set all high scores to 0
	
	public static int[] initialiseHighScores( int[] arrayOfScores)
	{
		for ( int count=0; count < arrayOfScores.length; count++)
		{
			arrayOfScores[count] = 0;
		}
		return arrayOfScores;
		
	}
	
	// This function prints out the high scores in the correct order with
	// the correct grammar ( "," or "and" )
	public static String printHighScores( int[] arrayOfScores)
	{
		for (int count = 0; count < arrayOfScores.length; count++)
		{
			if (count == 0)
				System.out.print("" + arrayOfScores[count]);
			
			else if (count == arrayOfScores.length - 1 )
				System.out.print(" and " + arrayOfScores[count] );
		
			else
				System.out.print(", " + arrayOfScores[count]);
		}
		String highScores = null;
		return highScores;
	}
	
	//This function checks if the next score is higher than any other scores
	//then it will put it in the appropriate position.
	
	
	public static int[] higherThan(Scanner inputList, int[] arrayOfScores)
	{
		int[] tempArray = new int[arrayOfScores.length];
		for(int index=0; index < arrayOfScores.length; index++)
		{
			for(int count=0; count<arrayOfScores.length; count++)
			{
				if (arrayOfScores[index] > arrayOfScores[count])
				{
					tempArray[index] = arrayOfScores[index];
					arrayOfScores[index] = arrayOfScores[count];
					arrayOfScores[count] = tempArray[index];
				}			
			}
		}
		return arrayOfScores;
	}
	
	
	

	
	public static int[] insertScore(int index, Scanner inputList,  int[] arrayOfScores)
	{		
		arrayOfScores[index] = inputList.nextInt();
		return arrayOfScores;
	}
}
