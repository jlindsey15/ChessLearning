import java.util.ArrayList;

public class ChessGame {
	public static Player player1;
	public static Player player2;
	public static Player currentPlayer;
	public static Player otherPlayer;
	
	private static int moveCount = 0;
	
	public static void InitializeGame() {
		player1 = new Player(true);
		player2 = new Player(false);
		
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		currentPlayer = player1;
		otherPlayer = player2;
	}
	
	public static void UpdateGame() {
		System.out.println("test ccccity " + ChessBoard.tiles[7][7].getOccupant());
		currentPlayer = player2;
		otherPlayer = player1;
		if (otherPlayer.hasStaleMate()) {
			System.out.println("Stalemate - Moves made to tie  game: " + moveCount);
			moveCount++;
			ChessApplication.UpdateDisplay();
			return;
		}
		if (otherPlayer.hasWon()) {
			System.out.println("Someone has one: " + moveCount);
			moveCount++;
			ChessApplication.UpdateDisplay();
			return;
		}
		moveCount++;
		ChessApplication.UpdateDisplay();
		UpdateAI(currentPlayer.getMyTeam());
		
		currentPlayer = player1;
		otherPlayer = player2;
		
	}
	
	private static void UpdateAI(ArrayList<ChessPiece> pieces) {
		int depth = 2; //must be >=1, if depth is less than 1 it just acts like it equals 1
		//ChessPiece bestPiece = null;
		//Position bestMove = null;
		int startColumn = -1;
		int startRow = -1;
		int endColumn = -1;
		int endRow = -1;
		int max = Integer.MIN_VALUE; //so that it'll definitely be replaced with a real value
	    for (ChessPiece piece : currentPlayer.getMyTeam())  {
	    	for (Position pos : piece.removeDangerMoves(piece.possibleMoves())) {
	    		int oldColumn = piece.getColumn();
	    		int oldRow = piece.getRow();
	    		ChessPiece oldOccupant = ChessBoard.getBoard()[pos.column][pos.row];
	    		ChessBoard.move(piece,  pos);
	    		Player temp = currentPlayer;
	    		otherPlayer = currentPlayer;
	    		currentPlayer = temp;
	    		int score = -negamax(depth -1);
	    		temp = currentPlayer;
	    		otherPlayer = currentPlayer;
	    		currentPlayer = temp;
	    		ChessBoard.move(piece,  new Position(oldColumn, oldRow));
	    		ChessBoard.setChessPiece(pos.column,  pos.row,  oldOccupant);
	        if( score > max ) {
	            max = score;
	        	startColumn = piece.getColumn();
	        	startRow = piece.getRow();
	        	endColumn = pos.column;
	        	endRow = pos.row;
	    	}
	    	}
	        
	    }
	    currentPlayer.makeMove(ChessBoard.getBoard()[startColumn][startRow], new Position(endColumn, endRow));
	    ChessPiece currentlySelectedPiece = ChessBoard.getBoard()[endColumn][endRow];
	    System.out.println("piece is " + currentlySelectedPiece);
	    if (currentlySelectedPiece instanceof Pawn) {
	    	System.out.println("mehchange");
			if ((currentlySelectedPiece.isOnWhiteTeam && endRow == 7) || (!currentlySelectedPiece.isOnWhiteTeam && endRow == 0)) {
				System.out.println("CHANGE");
				//ChessBoard.removeChessPiece(column, row);
				Queen queen = new Queen(endColumn, endRow, currentlySelectedPiece.isOnWhiteTeam);
				queen.player = ChessGame.currentPlayer;
				queen.LoadImage();
				ChessBoard.setChessPiece(endColumn,  endRow,  queen);
				System.out.println("test city " + ChessBoard.tiles[endColumn][endRow].getOccupant());

			}
		}
	}
		
		/*ChessPiece randomPiece;
		ArrayList<Position>possibleMoves = new ArrayList<Position>();
		Position randomMove;
		
		while (true) { //keeps randomly selecting pieces until one can move - then randomly selects its move
		
			double random = (double) Math.random();
			randomPiece = pieces.get((int)Math.floor(random * pieces.size()));

			possibleMoves = randomPiece.possibleMoves();
			possibleMoves = randomPiece.removeDangerMoves(possibleMoves);
			if (possibleMoves.size() > 0) {
				random = (int) Math.random();
				randomMove = possibleMoves.get((int)Math.floor(random * possibleMoves.size()));
				player2.makeMove(randomPiece, randomMove);
				randomPiece.setPosition(new Position(randomMove.column, randomMove.row));
				break;
			}
		}*/
		
	
	public static int negamax(int depth) {
		System.out.println("new negamax: " + depth);
		if (depth <= 0) {
			System.out.println("returning");
			return currentPlayer.evaluateBoard(ChessBoard.getBoard(), currentPlayer);
		}
		int max = Integer.MIN_VALUE; //so that it'll definitely be replaced with a real value
	    for (ChessPiece piece : currentPlayer.getMyTeam())  {
	    	for (Position pos : piece.removeDangerMoves(piece.possibleMoves())) {
	    		int oldColumn = piece.getColumn();
	    		int oldRow = piece.getRow();
	    		ChessPiece oldOccupant = ChessBoard.getBoard()[pos.column][pos.row];
	    		ChessBoard.move(piece,  pos);
	    		Player temp = currentPlayer;
	    		otherPlayer = currentPlayer;
	    		currentPlayer = temp;
	    		int score = -negamax(depth - 1);
	    		temp = currentPlayer;
	    		otherPlayer = currentPlayer;
	    		currentPlayer = temp;
	    		ChessBoard.move(piece,  new Position(oldColumn, oldRow));
	    		ChessBoard.setChessPiece(pos.column,  pos.row,  oldOccupant);
	        if( score > max )
	            max = score;
	    	}
	        
	    }
	    System.out.println("legitreturning");
	    return max;
	}
}
