import java.util.ArrayList;


public class Player {
	private static final int PAWN_WEIGHT = 1;
	private static final int ROOK_WEIGHT = 5;
	private static final int BISHOP_WEIGHT = 3;
	private static final int KNIGHT_WEIGHT = 3;
	private static final int QUEEN_WEIGHT = 9;
	private static final int KING_WEIGHT = 200000000;
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
					ChessPiece oldOccupant = ChessBoard.getBoard()[movePosition.column][movePosition.row];
					ChessBoard.move(oppPiece, movePosition);
					if (!opponentIsInCheck()) {//this means there's a way for the opponent to not move into check, so the game's not a stale mate
						ChessBoard.move(oppPiece,  new Position(oldColumn, oldRow));
						ChessBoard.setChessPiece(movePosition.column,  movePosition.row,  oldOccupant);
						//ChessBoard.setBoard(oldBoard);
						//oppPiece.setPosition(new Position(oldColumn, oldRow));
						return false; 

					}
					else {
						ChessBoard.move(oppPiece,  new Position(oldColumn, oldRow));
						ChessBoard.setChessPiece(movePosition.column,  movePosition.row,  oldOccupant);
						//ChessBoard.setBoard(oldBoard);
						//oppPiece.setPosition(new Position(oldColumn, oldRow));
						//ChessBoard.setBoard(oldBoard);
						//oppPiece.setPosition(new Position(oldColumn, oldRow));//restores board back to old state before simulation
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
					ChessPiece oldOccupant = ChessBoard.getBoard()[movePosition.column][movePosition.row];
					ChessBoard.move(oppPiece, movePosition);
					if (!opponentIsInCheck()) {//this means there's a way for the opponent to not move into check, so the game's not a stale mate
						ChessBoard.move(oppPiece,  new Position(oldColumn, oldRow));
						ChessBoard.setChessPiece(movePosition.column,  movePosition.row,  oldOccupant);
						//oppPiece.setPosition(new Position(oldColumn, oldRow));
						return false; 

					}
					else {
						ChessBoard.move(oppPiece,  new Position(oldColumn, oldRow));
						ChessBoard.setChessPiece(movePosition.column,  movePosition.row,  oldOccupant);
						//oppPiece.setPosition(new Position(oldColumn, oldRow));
						//oppPiece.setPosition(new Position(oldColumn, oldRow));//restores board back to old state before simulation
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
				ChessPiece oldOccupant = ChessBoard.getBoard()[movePosition.column][movePosition.row];
				ChessBoard.move(piece, movePosition);

				if (opponent.getKing() == null) { //if one of your pieces could attack opponent's King
					ChessBoard.move(piece, new Position(oldColumn, oldRow));
					ChessBoard.setChessPiece(movePosition.column,  movePosition.row,  oldOccupant);
					piece.setPosition(new Position(oldColumn, oldRow));
					return true;
				}
				ChessBoard.move(piece, new Position(oldColumn, oldRow));
				ChessBoard.setChessPiece(movePosition.column,  movePosition.row,  oldOccupant);
				//piece.setPosition(new Position(oldColumn, oldRow));
			}
		}

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

	public static int evaluateBoard(ChessPiece board[][], Player player) {
		int rank = 0;

		ArrayList<Pawn> compPawns = new ArrayList<Pawn>();
		ArrayList<Pawn> playerPawns = new ArrayList<Pawn>();

		ArrayList<Bishop> compBishops = new ArrayList<Bishop>();
		ArrayList<Bishop> playerBishops = new ArrayList<Bishop>();

		ArrayList<Knight> compKnights = new ArrayList<Knight>();
		ArrayList<Knight> playerKnights = new ArrayList<Knight>();

		ArrayList<Rook> compRooks = new ArrayList<Rook>();
		ArrayList<Rook> playerRooks = new ArrayList<Rook>();

		int playerQueenCount = 0;
		int compQueenCount = 0;

		int playerKingCount = 0;
		int compKingCount = 0;

		for (ChessPiece piece : player.opponent.getMyTeam()) {
			if (piece instanceof Pawn) playerPawns.add((Pawn) piece);
			if (piece instanceof Bishop) playerBishops.add((Bishop) piece);
			if (piece instanceof Knight) playerKnights.add((Knight) piece);
			if (piece instanceof Rook) playerRooks.add((Rook) piece);
			if (piece instanceof Queen) playerQueenCount = 1;
			if (piece instanceof King) playerKingCount = 1;
		}

		for (ChessPiece piece : player.getMyTeam()) {
			if (piece instanceof Pawn) compPawns.add((Pawn) piece);
			if (piece instanceof Bishop) compBishops.add((Bishop) piece);
			if (piece instanceof Knight) compKnights.add((Knight) piece);
			if (piece instanceof Rook) compRooks.add((Rook) piece);
			if (piece instanceof Queen) compQueenCount = 1;
			if (piece instanceof King) compKingCount = 1;
		}

		rank = KING_WEIGHT * (compKingCount - playerKingCount)
				+ QUEEN_WEIGHT * (compQueenCount - playerQueenCount)
				+ ROOK_WEIGHT * (compRooks.size() - playerRooks.size())
				+ BISHOP_WEIGHT * (compBishops.size() - playerBishops.size())
				+ KNIGHT_WEIGHT * (compKnights.size() - playerKnights.size())
				+ PAWN_WEIGHT * (compPawns.size() - playerPawns.size());

		//System.out.println("rank: " + rank);
		return rank;

	}


}
