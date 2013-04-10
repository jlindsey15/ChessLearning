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
		UpdateAI(player2.getMyTeam());
		Player temp = currentPlayer;
		otherPlayer = currentPlayer;
		currentPlayer = temp;
		
	}
	
	private static void UpdateAI(ArrayList<ChessPiece> pieces) {		
		ChessPiece randomPiece;
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
		}
	}
}
