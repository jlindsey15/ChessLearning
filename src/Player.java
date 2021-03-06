import java.util.ArrayList;


public class Player {
	private static final int PAWN_WEIGHT = 100;
	private static final int ROOK_WEIGHT = 500;
	private static final int BISHOP_WEIGHT = 330;
	private static final int KNIGHT_WEIGHT = 320;
	private static final int QUEEN_WEIGHT = 900;
	private static final int KING_WEIGHT = 200000000;
	private static final int ROOK_ALONE_BONUS = 5;
	private static final int PAWN_ISOLATION_PENALTY = -3;
	private static final int PAWN_BACKWARD_PENALTY = -5;
	private static final int PAWN_DOUBLED_PENALTY = -4;

	public boolean isOnWhiteTeam;
	public Player opponent;
	public Player() { //default constructor
		;
	}
	public  ArrayList<Rook> myRooks = new ArrayList<Rook>();
	public  ArrayList<Pawn> myPawns = new ArrayList<Pawn>();
	public  ArrayList<Bishop> myBishops = new ArrayList<Bishop>();
	public  ArrayList<Queen> myQueens = new ArrayList<Queen>();
	public  ArrayList<King> myKings = new ArrayList<King>();
	public ArrayList<Knight> myKnights = new ArrayList<Knight>();
	public Player(boolean whiteTeam) {
		isOnWhiteTeam = whiteTeam;
		for (ChessPiece piece : getMyTeam()) {
			if (piece instanceof Pawn) myPawns.add((Pawn)piece);
			if (piece instanceof Rook) myRooks.add((Rook)piece);
			if (piece instanceof Bishop) myBishops.add((Bishop)piece);
			if (piece instanceof Knight) myKnights.add((Knight)piece);
			if (piece instanceof King) myKings.add((King)piece);
			if (piece instanceof Queen) myQueens.add((Queen)piece);
			piece.player = this;
		}


	}
	public void refreshArrayLists() {
		myPawns.clear();
		myRooks.clear();
		myBishops.clear();
		myKnights.clear();
		myQueens.clear();
		myKings.clear();
		for (ChessPiece piece : getMyTeam()) {
			if (piece instanceof Pawn) myPawns.add((Pawn)piece);
			if (piece instanceof Rook) myRooks.add((Rook)piece);
			if (piece instanceof Bishop) myBishops.add((Bishop)piece);
			if (piece instanceof Knight) myKnights.add((Knight)piece);
			if (piece instanceof King) myKings.add((King)piece);
			if (piece instanceof Queen) myQueens.add((Queen)piece);
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
	public void restore(ChessPiece piece) {
		ChessPiece thing = piece;
		if (thing instanceof Pawn) myPawns.add((Pawn)thing);
		else if (thing instanceof Knight) myKnights.add((Knight)thing);
		else if (thing instanceof Rook) myRooks.add((Rook)thing);
		else if (thing instanceof Queen) myQueens.add((Queen)thing);
		else if (thing instanceof King) myKings.add((King)thing);
		else if (thing instanceof Bishop) myBishops.add((Bishop)thing);

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

				if (opponent.myKings.size() == 0) { //if one of your pieces could attack opponent's King
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
		return myKings.get(0);
	}

	public static int evaluateMaterial(ChessPiece board[][], Player player) {
		int rank = 0;

		rank = KING_WEIGHT * (player.myKings.size() - player.opponent.myKings.size())
				+ QUEEN_WEIGHT * (player.myQueens.size() - player.opponent.myQueens.size())
				+ ROOK_WEIGHT * (player.myRooks.size() - player.opponent.myRooks.size())
				+ BISHOP_WEIGHT * (player.myBishops.size() - player.opponent.myBishops.size())
				+ KNIGHT_WEIGHT * (player.myKnights.size() - player.opponent.myKnights.size())
				+ PAWN_WEIGHT * (player.myPawns.size() - player.opponent.myPawns.size());

		System.out.println("rank: " + rank + " queensize " + (player.myQueens.size()) + " opponent queensize " + player.opponent.myQueens.size());
		return rank;

	}
	public static int evaluateMobility(ChessPiece board[][], Player player) {
		int counter = 0;
		for (ChessPiece piece : player.getMyTeam()) {
			ArrayList<Position> moves = piece.removeDangerMoves(piece.possibleMoves());
			counter += moves.size();
		}
		for (ChessPiece piece : player.opponent.getMyTeam()) {
			ArrayList<Position> moves = piece.removeDangerMoves(piece.possibleMoves());
			counter -= moves.size();
		}
		return counter;
	}

	public static int evaluateRookBonus(ChessPiece board[][], Player player) {
		int count = 0;

		int aloneBonus = ROOK_ALONE_BONUS;
		for (Rook rook : player.myRooks) {
			int column = rook.getColumn();
			for (int i = 0; i < 8; i ++) {
				if (ChessBoard.getBoard()[column][i] != null) {
					aloneBonus = 0;
				}
			}
			count += aloneBonus;
		}
		System.out.println("Rook bonus " + count);
		return count;

	}

	public static int evaluatePawnBonus(ChessPiece board[][], Player player) { //penalties for isolated or "backwards" pawns
		int count = 0;



		for (Pawn pawn : player.myPawns) {
			int column = pawn.getColumn();
			int row = pawn.getRow();
			int left = column - 1;
			int right = column + 1;
			int bonus;
			int doubleBonus;

			if (0 <= left && left < 8) {
				bonus = PAWN_ISOLATION_PENALTY;
				doubleBonus = PAWN_BACKWARD_PENALTY;

				for (Pawn otherPawn : player.myPawns) {
					if (otherPawn.getColumn() == left) {
						if (otherPawn != pawn) {
							bonus = 0;
							if (player.isOnWhiteTeam) {
								if (otherPawn.getColumn() == left && otherPawn.getRow()<= row) {
									doubleBonus = 0;
								}
							}
						}
					}
				}
				count += bonus;
				count += doubleBonus;
			}

			if (0 <= right && right < 8) {
				bonus = PAWN_ISOLATION_PENALTY;
				doubleBonus = PAWN_BACKWARD_PENALTY;
				for (Pawn otherPawn : player.myPawns) {
					if (otherPawn.getColumn() == left) {
						if (otherPawn != pawn) {
							bonus = 0;
							if (player.isOnWhiteTeam) {
								if (otherPawn.getColumn() == left && otherPawn.getRow()<= row) {
									doubleBonus = 0;
								}
							}
						}
					}
				}
				count += bonus;
				count += doubleBonus;
			}
			bonus = 0;
			for (Pawn otherPawn : player.myPawns) {
				if (otherPawn.getColumn() == column) {
					if (otherPawn != pawn) {
						bonus = PAWN_DOUBLED_PENALTY;
					}
				}
			}
			count += bonus;


		}
		for (Pawn pawn : player.opponent.myPawns) {
			int column = pawn.getColumn();
			int row = pawn.getRow();
			int left = column - 1;
			int right = column + 1;
			int bonus;
			int doubleBonus;

			if (0 <= left && left < 8) {
				bonus = PAWN_ISOLATION_PENALTY;
				doubleBonus = PAWN_BACKWARD_PENALTY;

				for (Pawn otherPawn : player.opponent.myPawns) {
					if (otherPawn.getColumn() == left) {
						if (otherPawn != pawn) {
							bonus = 0;
							if (player.opponent.isOnWhiteTeam) {
								if (otherPawn.getColumn() == left && otherPawn.getRow()<= row) {
									doubleBonus = 0;
								}
							}
						}
					}
				}
				count -= bonus;
				count -= doubleBonus;
			}

			if (0 <= right && right < 8) {
				bonus = PAWN_ISOLATION_PENALTY;
				doubleBonus = PAWN_BACKWARD_PENALTY;
				for (Pawn otherPawn : player.myPawns) {
					if (otherPawn.getColumn() == left) {
						if (otherPawn != pawn) {
							bonus = 0;
							if (player.opponent.isOnWhiteTeam) {
								if (otherPawn.getColumn() == left && otherPawn.getRow()<= row) {
									doubleBonus = 0;
								}
							}
						}
					}
				}
				count -= bonus;
				count -= doubleBonus;
			}
			bonus = 0;
			for (Pawn otherPawn : player.opponent.myPawns) {
				if (otherPawn.getColumn() == column) {
					if (otherPawn != pawn) {
						bonus = PAWN_DOUBLED_PENALTY;
					}
				}
			}
			count -= bonus;


		}
		System.out.println("PAWN BONUS " + count);
		return count;
	}

	public static int evaluatePieceSquare(ChessPiece board[][], Player player) {
		int count = 0;
		if (!player.isOnWhiteTeam) {
			for (Pawn pawn : player.myPawns) {
				count += PieceSquare.blackPawn[pawn.getRow()][pawn.getColumn()];
			}
			for (Bishop bishop : player.myBishops) {
				count += PieceSquare.blackBishop[bishop.getRow()][bishop.getColumn()];
			}
			for (Knight knight : player.myKnights) {
				count += PieceSquare.blackKnight[knight.getRow()][knight.getColumn()];
			}
			for (Rook rook : player.myRooks) {
				count += PieceSquare.blackRook[rook.getRow()][rook.getColumn()];
			}
			for (Queen queen : player.myQueens) {
				count += PieceSquare.blackQueen[queen.getRow()][queen.getColumn()];
			}
			for (King king : player.myKings) {
				count += PieceSquare.blackKingMiddle[king.getRow()][king.getColumn()]; //TODO: opening to middle to endgame interpolation
			}
			//***********************************************************************************
			for (Pawn pawn : player.opponent.myPawns) {
				count -= PieceSquare.blackPawn[7 - pawn.getRow() ][7 - pawn.getColumn()];
			}
			for (Bishop bishop : player.opponent.myBishops) {
				count -= PieceSquare.blackBishop[7 - bishop.getRow()][7 - bishop.getColumn()];
			}
			for (Knight knight : player.opponent.myKnights) {
				count -= PieceSquare.blackKnight[7 - knight.getRow()][7 - knight.getColumn()];
			}
			for (Rook rook : player.opponent.myRooks) {
				count -= PieceSquare.blackRook[7 - rook.getRow()][7 - rook.getColumn()];
			}
			for (Queen queen : player.opponent.myQueens) {
				count -= PieceSquare.blackQueen[7 - queen.getRow()][7 - queen.getColumn()];
			}
			for (King king : player.opponent.myKings) {
				count -= PieceSquare.blackKingMiddle[7 - king.getRow()][7 - king.getColumn()]; //TODO: opening to middle to endgame interpolation
			}
		}
		else {
			for (Pawn pawn : player.myPawns) {
				count += PieceSquare.blackPawn[7 - pawn.getRow()][7 - pawn.getColumn()];
			}
			for (Bishop bishop : player.myBishops) {
				count += PieceSquare.blackBishop[7 - bishop.getRow()][7 - bishop.getColumn()];
			}
			for (Knight knight : player.myKnights) {
				count += PieceSquare.blackKnight[7 - knight.getRow()][7 - knight.getColumn()];
			}
			for (Rook rook : player.myRooks) {
				count += PieceSquare.blackRook[7 - rook.getRow()][7 - rook.getColumn()];
			}
			for (Queen queen : player.myQueens) {
				count += PieceSquare.blackQueen[7 - queen.getRow()][7 - queen.getColumn()];
			}
			for (King king : player.myKings) {
				count += PieceSquare.blackKingMiddle[7 - king.getRow()][7 - king.getColumn()]; //TODO: opening to middle to endgame interpolation
			}
			//***********************************************************************************
			for (Pawn pawn : player.opponent.myPawns) {
				count -= PieceSquare.blackPawn[pawn.getRow() ][pawn.getColumn()];
			}
			for (Bishop bishop : player.opponent.myBishops) {
				count -= PieceSquare.blackBishop[bishop.getRow()][bishop.getColumn()];
			}
			for (Knight knight : player.opponent.myKnights) {
				count -= PieceSquare.blackKnight[knight.getRow()][knight.getColumn()];
			}
			for (Rook rook : player.opponent.myRooks) {
				count -= PieceSquare.blackRook[rook.getRow()][rook.getColumn()];
			}
			for (Queen queen : player.opponent.myQueens) {
				count -= PieceSquare.blackQueen[queen.getRow()][queen.getColumn()];
			}
			for (King king : player.opponent.myKings) {
				count -= PieceSquare.blackKingMiddle[king.getRow()][king.getColumn()]; //TODO: opening to middle to endgame interpolation
			}
		}

		//*****************************************************************88

		//**********************************************************************************

		
		return count;
	}

	public static int evaluateBoard(ChessPiece board[][], Player player) {
		return evaluateMaterial(board, player) + evaluateRookBonus(board, player) + evaluatePawnBonus(board, player) + evaluatePieceSquare(board, player);
	}


}

