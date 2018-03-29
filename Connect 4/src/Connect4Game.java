import java.util.Scanner;
import java.util.Random;

public class Connect4Game {

	
	public static final int BOARD_WIDTH = 7;
	public static final int BOARD_HEIGHT = 6;
	public static final char BLANK = ' ';
	public static final char PLAYER1_PIECE = '+';
	public static final char PLAYER2_PIECE = 'O';

	public static final int PIECES_IN_A_ROW_TO_WIN = 4;

	public static void main(String[] args) {

		
		boolean running = true;
		int turnNumber;
		int drop;
		

		while (running) {
			boolean gameOn = true;
			System.out.println("Welcome to Connect 4 are your playing against another player?");
			Scanner scanner = new Scanner(System.in);
			String playerChoice = scanner.nextLine();
			boolean twoPlayers = false;
			boolean invalidOption = false;
			
			ConnectPlayer playerOne = new C4HumanPlayer(PLAYER1_PIECE);
			ConnectPlayer playerTwo = null;
			if (playerChoice.equalsIgnoreCase("No")) 
			{
				playerTwo = new C4RandomAIPlayer(PLAYER2_PIECE);
				System.out.println("AI player has been added.");
				twoPlayers = false;
			}
			else if (playerChoice.equalsIgnoreCase("Yes"))
			{
				playerTwo = new C4HumanPlayer(PLAYER2_PIECE);
				System.out.println("Human player has been added.");
				twoPlayers = true;
			} 
			else
			{
				System.out.println("Invalid, try again.");
				invalidOption = true;
			}
			if (!invalidOption)
			{
				Connect4Grid gameGrid = new Connect4Grid2DArray();
				gameGrid.emptyGrid();
				ConnectPlayer currentPlayer=playerOne;
				turnNumber = 0;
				
				while(gameOn) 
				{
					String gridString = gameGrid.toString();
					System.out.println(gridString);
					
					// Player 1's turn.
					if(turnNumber%2==0)
					{
						currentPlayer = playerOne;
						System.out.println("Player one: Where would you like to drop the piece?");
						drop = scanner.nextInt();
						if(gameGrid.isValidColumn(drop))
						{
							System.out.println("Valid move.");
							gameGrid.dropPiece(currentPlayer, drop);
							turnNumber++;
						}
						else
							System.out.println("Invalid move.");
					}
					// Player 2's / AI's turn.
					else if(turnNumber%2==1)
					{
						currentPlayer = playerTwo;
						if(twoPlayers)
						{
							System.out.println("Player two: Where would you like to drop the piece?");
							drop = scanner.nextInt();
							if(gameGrid.isValidColumn(drop))
							{
								System.out.println("Valid move.");
								gameGrid.dropPiece(currentPlayer, drop);
								turnNumber++;
							}
							else
								System.out.println("Invalid move.");
						}
						else if(!twoPlayers)
						{
							Random rnd = new Random();
							drop = rnd.nextInt(7+1);
					
							if(gameGrid.isValidColumn(drop))
							{
								gameGrid.dropPiece(currentPlayer, drop);
								System.out.println("The AI dropped piece at: " + (drop));
								turnNumber++;
							}
						}
					}
					
					if(gameGrid.didLastPieceConnect4())
					{
						gridString = gameGrid.toString();
						System.out.println(gridString);
						System.out.println("The winner was: " + (turnNumber%2==1 ? "Player One" : "Player Two"));
						gameOn = false;
					}
					if(gameGrid.isGridFull()){
						
						gridString = gameGrid.toString();
						System.out.println(gridString);
						System.out.println("No winner.");
						gameOn = false;
					}
				}
			}
		}
	}
}
