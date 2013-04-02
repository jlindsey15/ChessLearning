
public class Board {
	private Piece[][] daBoard = new Piece[8][8]; //2D array representing the chessboard
	public Board() {
		for (int i = 0; i < 8; i++) {
			new Pawn(this, 1, i, false); //creates black pawns - far side
			new Pawn(this, 6, i, true); //creates white pawns - near side
		}
	}
	public void add(Piece piece) { //adds a Piece - the location is contained within the piece object
		daBoard[piece.getRow()][piece.getColumn()] = piece;
	}
	public void remove(Piece piece) { //removes a Piece
		daBoard[piece.getRow()][piece.getColumn()] = null;
	}
	public boolean isOccupied(int row, int column) { //checks whether a given square is occupied or not
		if (daBoard[row][column] == null) {
			return false;
		}
		else {
			return true;
		}
	}


}
