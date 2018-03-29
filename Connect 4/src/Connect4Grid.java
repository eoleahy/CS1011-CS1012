
interface Connect4Grid {
	
	public static final int BOARD_WIDTH = 7;
	public static final int BOARD_HEIGHT = 6;
	public static final char BLANK = ' ';
	public static final char PLAYER1_PIECE = '+';
	public static final char PLAYER2_PIECE = 'O';
	public static final int PIECES_IN_A_ROW_TO_WIN = 4;

	public void emptyGrid();
		
	public String toString();
	
	public boolean isValidColumn(int column);
	
	public boolean isColumnFull(int column);

	public void dropPiece(ConnectPlayer player, int column);
	
	public boolean didLastPieceConnect4();
	
	public boolean isGridFull();
}
