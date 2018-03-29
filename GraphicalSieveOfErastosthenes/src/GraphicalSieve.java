import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.Scanner;

public class GraphicalSieve 
{
	public static final int MINIMUM_NUMBER = 2;
	public static final int SQUARES_PER_ROW = 20;
	public static final int PRIMES_PER_ROW = 5;
	public static final Color BLACK = Color.black;
	public static final Color WHITE = Color.white;
	public static final Color GREY = Color.gray;
	public static void main(String[] args) 
	{
		try{
			System.out.println("What is your maximum number?");
			Scanner inputScanner = new Scanner (System.in);
			int maxNumber = inputScanner.nextInt();
			inputScanner.close();
		
			int[] numberSequence = new int[maxNumber+1];
			int[] higherMultiples = new int[numberSequence.length];
			int[] sievedSequence = new int[0];
			int multiplier = MINIMUM_NUMBER;//the multiplier used to cross out the higher multiples
			if ( maxNumber < MINIMUM_NUMBER)
			{
				System.out.println("The number must be greater or equal to 2.");
				System.exit(0);
			}
			else
				numberSequence = createSequence(maxNumber);
			
			while(multiplier <= Math.sqrt(maxNumber))
			{
				System.out.print("\n");
				crossOutHigherMultiples(maxNumber, numberSequence, multiplier, 
						higherMultiples);
				multiplier++;
			}
			System.out.print("\n");
			sievedSequence = sieve(maxNumber, numberSequence, higherMultiples);
		
			System.out.print(sequenceToString(maxNumber, numberSequence, higherMultiples)+"\n");
			System.out.print(nonCrossedOutSubSeqToString(sievedSequence));
			

			// Main for Graphical Sieve
			int[]primeNumbers = new int[sievedSequence.length];
			System.arraycopy(sievedSequence, 0, primeNumbers, 0, sievedSequence.length);
			displayNumber2toN(maxNumber);
			
			
			int primeCount = primeNumbers.length;	
			
			
			multiplier = MINIMUM_NUMBER;
			
			while(multiplier < Math.sqrt(maxNumber))
			{
				displayComposite(maxNumber, primeCount, multiplier, primeNumbers);
				multiplier++;
			}
			
			displayPrime(primeNumbers, primeCount);
			
			
			
			
			
			
			
			
			}catch(java.util.InputMismatchException exception)
				{
				System.out.println("Invalid input, please type in an integer with a value <=2");
				}
	}

	/*	Each element of the array is indexed to its corresponding value for convenience/
	 * i.e int 4 is at index 4/
	 */
	public static int[] createSequence(int maxNumber)
	{
		int[] numberSequence = new int[maxNumber+1];
		for(int index=MINIMUM_NUMBER; index <= maxNumber; index++)
		{
			numberSequence[index] = index;
			if(index==maxNumber)
				System.out.print(numberSequence[index]); //Fixes punctuation
			else
				System.out.print(numberSequence[index] + ",");
		}
		return numberSequence;
	}
	/*The following function works by comparing every digit * n with every digit.
	 * The second block of code prints the number sequence iteratively with the non-prime numbers
	 * surrounded by square brackets.
	 * The function then returns the non primes for future use.
	 */
	public static int[] crossOutHigherMultiples(int maxNumber, int[] numberSequence, int multiplier,
			int[] higherMultiples)
	{	
		for(int index= MINIMUM_NUMBER; index < numberSequence.length; index++)
		{
			for(int count= MINIMUM_NUMBER; count<numberSequence.length; count++)
			{
				if (numberSequence[index] * multiplier == numberSequence[count])
				{
					higherMultiples[count] = numberSequence[count];					
				}
			}
		}
		
		for(int arrayAddress =MINIMUM_NUMBER; arrayAddress <=maxNumber; arrayAddress++)
		{			
			if(higherMultiples[arrayAddress] == numberSequence[arrayAddress])
			{
				if(arrayAddress == maxNumber) //Fixes punctuation
					System.out.print("[" +numberSequence[arrayAddress] + "]");
				else
					System.out.print("[" + numberSequence[arrayAddress]+ "], ");
			}
			else
			{
				if(arrayAddress == maxNumber) //Fixes punctuation
					System.out.print(numberSequence[arrayAddress]);
				else
					System.out.print(numberSequence[arrayAddress] + ", ");
			}
		}		
		return higherMultiples;		
	}	
	/* Since this function mainly uses the higherMultiples array, which does not contain prime numbers
	 * the position in which a prime number would be contained is = 0.
	 * Through this we can replace each 0 with a prime number and store it in a new array.
	 * A new array of size "i" is then created for later use.
	 */
	public static int[] sieve(int maxNumber, int[] numberSequence, int[] higherMultiples)

	{
		int[] tempSequence=new int[maxNumber +1];
		int n=0;
		for(int index=MINIMUM_NUMBER; index <= maxNumber; index++)
		{			
			if(higherMultiples[index] == 0)
			{
				tempSequence[n] = numberSequence[index];
				n++;
			}
		}
		
		int i=0;
		while(tempSequence[i] != 0)
		{
			i++;
		}	
		int[] sievedSequence = new int[i];
		System.arraycopy(tempSequence, 0, sievedSequence,0, i);
		return sievedSequence;
	}
	public static String sequenceToString(int maxNumber, int[] numberSequence, int[] higherMultiples)
	{
		String sequenceString = "";
		for(int index = MINIMUM_NUMBER; index<= maxNumber; index++)
		{
			if(higherMultiples[index] ==0)
			{
				Integer.toString(numberSequence[index]);
				if( index == maxNumber)
					sequenceString +=(numberSequence[index]);
				else
					sequenceString +=(numberSequence[index] + ",");
			}
			else
			{
				Integer.toString(higherMultiples[index]);
				if(index == maxNumber)
					sequenceString +=("[" + higherMultiples[index] + "]");
				else
					sequenceString +=("[" + higherMultiples[index] + "],");
			}
		}
		return sequenceString;
	}	
	public static String nonCrossedOutSubSeqToString(int[]sievedSequence)
	{
		String nonCrossedOutSubSeqToString = "";
		try
		{	
			for(int index =0; index <= sievedSequence.length; index++)
			{
				Integer.toString(sievedSequence[index]);
				if( index == sievedSequence.length - 1)
					nonCrossedOutSubSeqToString += (sievedSequence[index] + " ");
				else
					nonCrossedOutSubSeqToString += (sievedSequence[index] + ",");			
			}		
		}catch(java.lang.ArrayIndexOutOfBoundsException exception)
		{	
		}
		return nonCrossedOutSubSeqToString;
	}
	public static void displayNumber( int maxNumber, int count)
	{
		int i = (count / SQUARES_PER_ROW);
		int j = (count % SQUARES_PER_ROW);
				
		StdDraw.setPenColor(GREY);
		StdDraw.filledSquare(4 +(3.1* j),95 - (3.1* i), 1.5);
		Font font = new Font("Arial", Font.BOLD, 12);
		StdDraw.setFont(font);
		StdDraw.setPenColor(BLACK);
		StdDraw.text(4 + (3.1* j), 95 - (3.1*i), "" + (count));
				
	}
	public static void displayNumber2toN( int maxNumber)
	{
		StdDraw.setCanvasSize(1000, 1000);
		StdDraw.setScale(0, 100);
		for(int count =MINIMUM_NUMBER; count<= maxNumber; count++)
		{
			displayNumber(maxNumber, count);
		}
	}
	public static void displayComposite(int maxNumber, int primeCount, int multiplier, int[] compositeNumbers)
	{
		
		Random randomGenerator = new Random();
		int r1 = randomGenerator.nextInt(255);
		int g1 = randomGenerator.nextInt(255);
		int b1 = randomGenerator.nextInt(255);
		Color c1 = new Color(r1, g1, b1);
		
		for(int count = MINIMUM_NUMBER; count <= maxNumber; count++)
		{
			int j = (count / SQUARES_PER_ROW);
			int i = (count % SQUARES_PER_ROW);
			
			if(count % multiplier == 0 && count != multiplier)
			{
			
				StdDraw.setPenColor(c1);
				StdDraw.filledSquare(4 +(3.1* i),95 - (3.1* j), 1.5);
				Font font = new Font("Arial", Font.BOLD, 12);
				StdDraw.setFont(font);
				StdDraw.setPenColor(BLACK);
				StdDraw.text(4 + (3.1* i), 95 - (3.1*j), "" + (count));
				StdDraw.show(50);
			}
		
		}
		
	}
	
	
	public static void  displayPrime(int[] primeNumbers, int primeCount)
	{
		Font font = new Font("Arial", Font.BOLD, 15);
		StdDraw.setFont(font);
		StdDraw.setPenColor(BLACK);
		StdDraw.text(87, 93, "Prime Numbers:");
		for(int count =0; count < primeCount ;count ++)
		{
			int j= (count / PRIMES_PER_ROW);
			int i= (count % PRIMES_PER_ROW);
			
			StdDraw.text(80 + (4* i), 90 - (3.1*j), "" + (primeNumbers[count]));
			StdDraw.show(50);
		}
	}
}
	