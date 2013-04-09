import java.util.ArrayList;


public class Player {
	public boolean isOnWhiteTeam;
	public Player opponent;
	public Player() { //default constructor
		;
	}
	public Player(boolean whiteTeam) {
		isOnWhiteTeam = whiteTeam;
		for (ChessPiece piece : getMyTeam()) {
			piece.player = this;
		}


	}
	public void setOpponent(Player opp) {
		opponent = opp;
	}
	public void makeMove(ChessPiece piece, Position pos) {
		if (piece.isOnWhiteTeam == isOnWhiteTeam) {
			ChessBoard.move(piece, pos);
			if (piece instanceof Pawn) {
				((Pawn) piece).hasMoved = true;
			}
		}

	}

	public boolean hasWon() { //checks if the player has won the game
		if (!opponentIsInCheck()) { //opponent must currently be in check to achieve checkmate (but not for stalemate...)
			return false;
		}
		else {
			for (ChessPiece oppPiece : opponent.getMyTeam()) {
				for (Position movePosition : oppPiece.possibleMoves()) { //simmulate all possible opponent moves
					//used to restore the board back to original state after "preview" of opponent move
					ChessPiece[][] oldBoard = new ChessPiece[8][8];

					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							oldBoard[i][j] = ChessBoard.getBoard()[i][j];
						}
					}
					int oldColumn = oppPiece.getColumn();
					int oldRow = oppPiece.getRow();
					ChessBoard.move(oppPiece,  movePosition);
					if (!opponentIsInCheck()) {//this means there's a way for the opponent to get out of check, so the game's not over
						System.out.println("Not mate!");
						ChessBoard.setBoard(oldBoard);
						oppPiece.setPosition(new Position(oldColumn, oldRow));
						return false; 

					}
					else {
						ChessBoard.setBoard(oldBoard);
						oppPiece.setPosition(new Position(oldColumn, oldRow));//restores board back to old state before simulation
					}

				}
			}
		}
		return true; //if there's no escape, return true
	}

	public boolean hasStaleMate() { //TODO: other stalemate rules (like 3 same in a row, 50 move rule, etc.)
		//checks if the player has won the game 

		if (opponentIsInCheck()) { //opponent must NOT currently be in check to achieve STALEMATE
			return false;
		}
		else {
			for (ChessPiece oppPiece : opponent.getMyTeam()) {
				for (Position movePosition : oppPiece.possibleMoves()) { //simmulate all possible opponent moves
					//used to restore the board back to original state after "preview" of opponent move
					ChessPiece[][] oldBoard = new ChessPiece[8][8];

					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							oldBoard[i][j] = ChessBoard.getBoard()[i][j];
						}
					}
					int oldColumn = oppPiece.getColumn();
					int oldRow = oppPiece.getRow();
					ChessBoard.move(oppPiece, movePosition);
					if (!opponentIsInCheck()) {//this means there's a way for the opponent to not move into check, so the game's not a stale mate
						ChessBoard.setBoard(oldBoard);
						oppPiece.setPosition(new Position(oldColumn, oldRow));
						return false; 

					}
					else {
						ChessBoard.setBoard(oldBoard);
						oppPiece.setPosition(new Position(oldColumn, oldRow));//restores board back to old state before simulation
					}

				}
			}
		}
		return true; //if there's no escape, return true
	}

	public boolean opponentIsInCheck() { //checks whether you could attack the opponent's king
		ChessPiece[][] oldBoard = new ChessPiece[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				oldBoard[i][j] = ChessBoard.getBoard()[i][j];
			}
		}
		for (ChessPiece piece : getMyTeam()) {
			
			for (Position movePosition : piece.possibleMoves()) {
				int oldColumn = piece.getColumn();
				int oldRow = piece.getRow();
				ChessBoard.move(piece, movePosition);
				
				if (opponent.getKing() == null) { //if one of your pieces could attack opponent's King
					ChessBoard.setBoard(oldBoard);
					piece.setPosition(new Position(oldColumn, oldRow));
					return true;
				}
				piece.setPosition(new Position(oldColumn, oldRow));
			}
		}
		ChessBoard.setBoard(oldBoard);
		return false; //if no possible ways to attack opponent king, then he is not in check
	}

	public ArrayList<ChessPiece> getMyTeam() {
		ArrayList<ChessPiece> returned = new ArrayList<ChessPiece>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (ChessBoard.getBoard()[i][j] == null) {
					;
				}
				else {
					if (ChessBoard.getBoard()[i][j].isOnWhiteTeam == isOnWhiteTeam) {
						returned.add(ChessBoard.getBoard()[i][j]);
					}
				}
			}
		}
		return returned;
	}
	public King getKing() {
		for (ChessPiece piece : getMyTeam()) {
			if (piece instanceof King) {
				return (King) piece;
			}
		}
		return null; //should never get here
	}


}
