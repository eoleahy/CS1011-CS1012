
public class Connect4Grid2DArray implements Connect4Grid {

	char[][] grid= new char[BOARD_HEIGHT][BOARD_WIDTH];
	public int lastRow;
	public int lastColumn;
	
	public void emptyGrid()
	{
		for (int column = 0; column < BOARD_WIDTH; column++) {
			for (int row = 0; row < BOARD_HEIGHT; row++) {
				grid[row][column] = BLANK;
			}
		}
	}

	public String toString()
	{
		
		int totalBoardHeight = 8;
		int row =0;
		int column=0;
		int i =0;
		
		String gridString="";	
		String gridCharacter;

		for (int rowNumber = 0; rowNumber < totalBoardHeight; rowNumber++)
		{
			for (int columnNumber = 0; columnNumber < totalBoardHeight; columnNumber++)
			{
				if (rowNumber < BOARD_HEIGHT)
				{
					gridCharacter ="|";
					gridString += gridCharacter;
				}
				
				if(columnNumber<BOARD_WIDTH && rowNumber<BOARD_HEIGHT )
				{	
					gridCharacter = Character.toString(grid[row][column]);
					gridString += gridCharacter;
					column++;
					if(column==BOARD_WIDTH)
					{
						row++;
						column=0;
					}
					if(row==6)
						row--;
					if(column==7)
						column--;
				}
				

				if (rowNumber == BOARD_HEIGHT && columnNumber < BOARD_WIDTH)
				{
					gridCharacter = "--";
					gridString += gridCharacter;
				}
				if (rowNumber == BOARD_WIDTH && columnNumber < BOARD_WIDTH)
				{
					gridCharacter = " " +Integer.toString(i+1) ;
					i++;
					gridString +=gridCharacter;
				}
			}
			gridCharacter = "\n";
			gridString += gridCharacter;
		}
		return gridString;
	}
	
	public boolean isValidColumn(int column)
	{	
		column -=1;
		if(column >=0 && column < 7){
			if(!isColumnFull(column))
				return true;
			else
				return false;
		}	
		else
			return false;
	}
	public boolean isColumnFull(int column)
	{	
		for(int row=0;row<BOARD_HEIGHT; row++)
		{		
			if(grid[row][column] == BLANK)
				return false;	
		}	
		return true;
	}

	public void dropPiece(ConnectPlayer player, int dropColumn){
		dropColumn-=1;
		int lowestRow=5;
		boolean foundFreeRow = false;
		while(!foundFreeRow)
		{
			 if(grid[lowestRow][dropColumn] == BLANK)
				foundFreeRow = true;
			 else if(grid[lowestRow][dropColumn] != BLANK)
			 	lowestRow--;				 
		}
		grid[lowestRow][dropColumn] = player.playerPiece;		
		lastRow = lowestRow;
		lastColumn = dropColumn;
	}
	
	public boolean didLastPieceConnect4()
	{
		char currentPiece = grid[lastRow][lastColumn];
		
		//Down
		int winningPieces=0;
		for(int n=0; n<BOARD_HEIGHT;n++)	
		{
			if(grid[n][lastColumn]==currentPiece)
				winningPieces++;
			else
				winningPieces=0;
		}
		if(winningPieces>=PIECES_IN_A_ROW_TO_WIN)
			return true;	
		 winningPieces=0;
		//Across
		for(int n=0; n<BOARD_WIDTH;n++)
		{
			if(grid[lastRow][n]==currentPiece)
				winningPieces++;
			else
				winningPieces=0;
		}
		if(winningPieces>=PIECES_IN_A_ROW_TO_WIN)
			return true;
		//Diagonal
		
		
		return false;
	}
	
	public boolean isGridFull()
	{
		for(int row=0; row<BOARD_HEIGHT; row++)
		{
			for( int column=0; column<BOARD_WIDTH; column++)
			{
				if(grid[row][column] == BLANK)
					return false;
			}
		}
		System.out.println("The board is full");
		return true;
	}
}
