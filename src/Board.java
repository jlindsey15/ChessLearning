
public class Board {
	private ChessPiece[][] daBoard = new ChessPiece[8][8]; //2D array representing the chessboard
	public Board() { //creates a new chess board with all pieces in their starting positions
		for (int i = 0; i < 8; i++) {
			new Pawn(this, i, 1, true); //creates white pawns - near side
			new Pawn(this, i, 6, false); //creates black pawns - far side
		}
		//white rooks:
		new Rook(this, 0, 0, true);
		new Rook(this, 7, 0, true);
		//black rooks:
		new Rook(this, 0, 7, false);
		new Rook(this, 7, 7, false);
		//white knights:
		new Knight(this, 1, 0, true);
		new Knight(this, 6, 0, true);
		//black knights:
		new Knight(this, 1, 7, false);
		new Knight(this, 6, 7, false);
		//white bishops:
		new Bishop(this, 2, 0, true);
		new Bishop(this, 5, 0, true);
		//black bishops:
		new Bishop(this, 2, 7, false);
		new Bishop(this, 5, 7, false);
		//white queen:
		new Queen(this, 3, 0, true);
		//black queen:
		new Queen(this, 3, 7, false);
		//white king:
		new King(this, 4, 0, true);
		//black king:
		new King(this, 4, 7, false);
	}
	public void add(ChessPiece piece) { //adds a Piece - the location is contained within the piece object
		daBoard[piece.getColumn()][piece.getRow()] = piece;
	}
	public void remove(ChessPiece piece) { //removes a Piece
		daBoard[piece.getColumn()][piece.getRow()] = null;
	}
	public void remove(int column, int row) { //removes a Piece
		daBoard[column][row] = null;
	}
	public boolean isOccupied(int column, int row) { //checks whether a given square is occupied or not
		if (daBoard[column][row] == null) {
			return false;
		}
		else {
			return true;
		}
	}
	public ChessPiece[][] getBoard() {
		return daBoard;
	}
	
	


}
