import java.util.ArrayList;


public class Chessbot { //main class
	public static void main(String args[]) {
		ChessBoard.Initialize();
		Player player1 = new Player(true);
		Player player2 = new Player (false);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		/*player2.makeMove(ChessBoard.getBoard()[0][7], new Position(4, 4));
		player1.makeMove(player1.getKing(), new Position(6, 4));
		
		//player2.makeMove(ChessBoard.getBoard()[7][7], new Position(4, 3));
		ChessBoard.removeChessPiece(4, 1);
		ChessApplication.InitializeChessApplication(800, 800);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("has won? " +player2.hasWon());
		System.out.println("check? " + player2.opponentIsInCheck());
		System.out.println("hey " +player1.getKing().getColumn() + " " + player1.getKing().getRow() + " " + (player1.getKing() instanceof King));
		System.out.println("Pawn");
		for (Position pos : ChessBoard.getBoard()[7][6].possibleMoves()) {
			
			System.out.println(pos.column + " " + pos.row);
		}
		for (Position move : player1.getKing().removeDangerMoves(player1.getKing().possibleMoves())) {
			System.out.println(move.column + " " + move.row);
		}
		int x = 3;
		while (x==3) {
			;
		}
		*/
		
		

		System.out.println("-1");
		double random;
		ChessPiece randomPiece;
		Position randomMove;
		ArrayList<Position> possibleMoves;
		
		
		
		int moves = 0;

		Player currentPlayer = player1;
		Player otherPlayer = player2;
		while (!(otherPlayer.hasWon() || otherPlayer.hasStaleMate())) { //stops when game is over
			moves++;
			
			System.out.println("new move");


			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {


					try {
						System.out.print(ChessBoard.getBoard()[i][j].getClass());
					}
					catch (NullPointerException e) {
						System.out.print("empty");
					}

				}
				System.out.println();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			ChessApplication.InitializeChessApplication(800, 800);
			
			try {
				Thread.sleep(0);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<ChessPiece> currentPlayers = currentPlayer.getMyTeam();

			
			while (true) { //keeps randomly selecting pieces until one can move - then randomly selects its move
				
				random = Math.random();
				randomPiece = currentPlayers.get((int)Math.floor(random * currentPlayers.size()));
				int oldColumn = randomPiece.getColumn();
				int oldRow = randomPiece.getRow();

				possibleMoves = randomPiece.possibleMoves();
				possibleMoves = randomPiece.removeDangerMoves(possibleMoves);
				if (possibleMoves.size() > 0) {
					random = Math.random();
					randomMove = possibleMoves.get((int)Math.floor(random * possibleMoves.size()));
					currentPlayer.makeMove(randomPiece, randomMove);
					randomPiece.setPosition(new Position(randomMove.column, randomMove.row));
					break;
				}



			}
			Player temp = otherPlayer;
			otherPlayer = currentPlayer;
			currentPlayer = temp;



		}
		ChessApplication.InitializeChessApplication(800, 800);
		System.out.println(otherPlayer);

	}
}