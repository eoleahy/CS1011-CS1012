import java.util.Scanner;

public class DiceGame 
{
	public static final int DEFAULT_BET_RETURN = 2;
	public static final int TRIPLE_BET_RETURN = 31;
	public static void main(String[] args)
	{
		try
		{
		Wallet wallet = new Wallet();
			
		boolean running = true;
		
		System.out.println("Welcome to Chuc-a-luck, how much cash would you like to place into your wallet?");
		Scanner inputCash= new Scanner(System.in);
		double cash = inputCash.nextDouble();	
		double initialCash = cash;
		wallet.put(cash);		
	
		while(running)
		{
			System.out.println("Your wallet has " + wallet.check() + ". What type of bet would you like to make?");
			Scanner input = new Scanner(System.in);
			String betType = input.nextLine();
			if(betType.equals("triple")||betType.equals("field")||betType.equals("high")||betType.equals("low"))
			{
				resolveBet(betType, wallet);
			
				if(wallet.check() ==0)
					running = false;
			}
			else if(betType.equals("Quit"))
			{
				System.out.println("Quitting");
				running = false;
			}
			else
				System.out.println("Invalid bet type.");
		}
		System.out.println("You finished with " + wallet.check() + " as compared to an initial amount of " + initialCash);
	}catch(java.util.InputMismatchException exception)
		{
		System.out.println("Please enter a number.");
		}
	}
	public static void resolveBet(String betType, Wallet wallet)
	{
		
		
		System.out.println("How much money would you like to bet?");
		Scanner scanner = new Scanner(System.in);
		double betAmount = scanner.nextDouble();
		
		if(betAmount > wallet.check())
		{
			System.out.println("You do not have enough cash to make this bet.");					
		}
		wallet.get(betAmount);
		
		
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		Dice dice3 = new Dice();
		
		dice1.roll();
		dice2.roll();
		dice3.roll();
			
		int diceFaceOne = dice1.topFace();
		int diceFaceTwo = dice2.topFace();
		int diceFaceThree = dice3.topFace();
		
		int diceTotal = diceFaceOne + diceFaceTwo + diceFaceThree;
		double betReturn;
		
		
		if(betType.equals("triple"))
		{
			if(diceFaceOne == diceFaceTwo && diceFaceOne==diceFaceThree && diceFaceOne != 1 && diceFaceTwo != 6)
			{
				betReturn = TRIPLE_BET_RETURN * betAmount;
				System.out.println("Winner ,you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree);
				wallet.put(betReturn);
			}
			else
			{
				System.out.println("Loser, you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree);
				
			}	
		}
		if(betType.equals("field"))
		{
			if(diceTotal < 8 || diceTotal > 11)
			{
				betReturn = DEFAULT_BET_RETURN * betAmount;
				System.out.println("Winner, you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree + " giving a total of " 
							+ diceTotal);
				wallet.put(betReturn);
			}
			else
			{
				System.out.println("Loser, You rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree + " giving a total of " 
						+ diceTotal);	
			}
		}
		if(betType.equals("high"))
		{
			if(diceTotal <= 10 || (diceFaceOne == diceFaceTwo && diceFaceOne == diceFaceThree && 
					diceFaceOne >= 4 && diceFaceTwo >= 4 && diceFaceThree >= 4))
			{
				System.out.println("Loser, you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree + " giving a total of " 
						+ diceTotal);	
			}
			else
			{
				betReturn = DEFAULT_BET_RETURN * betAmount;
				System.out.println("Winner, you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree + " giving a total of " 
							+ diceTotal);
				wallet.put(betReturn);
				
			}
		}
		if(betType.equals("low"))
		{
			if(diceTotal >=11 || (diceFaceOne == diceFaceTwo && diceFaceOne == diceFaceThree && 
					diceFaceOne <=3 && diceFaceTwo <= 3 && diceFaceThree <=3))
			{
				System.out.println("Loser, you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree + " giving a total of " 
						+ diceTotal);	
			}
			else
			{
				betReturn = DEFAULT_BET_RETURN * betAmount;
				System.out.println("Winner, you rolled: " + diceFaceOne + " " + diceFaceTwo + " " + diceFaceThree + " giving a total of " 
							+ diceTotal);
				wallet.put(betReturn);
				
			}	
		}
	}
}
