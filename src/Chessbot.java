import java.util.ArrayList;


public class Chessbot { //main class
	public static void main(String args[]) {
		ChessBoard.Initialize();
		ChessApplication.InitializeChessApplication(800, 800);

		System.out.println("-1");
		double random;
		ChessPiece randomPiece;
		Position randomMove;
		ArrayList<Position> possibleMoves;
		Player player1 = new Player(true);
		Player player2 = new Player (false);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		int moves = 0;

		Player currentPlayer = player1;
		Player otherPlayer = player2;
		while (!(otherPlayer.hasWon() || otherPlayer.hasStaleMate())) {
			ChessApplication.InitializeChessApplication(800, 800);
			ChessPiece[][] oldBoard = new ChessPiece[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					oldBoard[i][j] = ChessBoard.getBoard()[i][j];


				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			moves++;
			ArrayList<ChessPiece> currentPlayers = currentPlayer.getMyTeam();
			System.out.println("test1");



			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {


					try {
						System.out.print(oldBoard[i][j].getClass());
					}
					catch (NullPointerException e) {
						System.out.print("empty");
					}

				}
				System.out.println();
			}
			

			
			while (true) {
				random = Math.random();
				randomPiece = currentPlayers.get((int)Math.floor(random * currentPlayers.size()));

				possibleMoves = randomPiece.possibleMoves();
				System.out.println(randomPiece + " " + possibleMoves.size());
				if (possibleMoves.size() > 0) {
					random = Math.random();
					randomMove = possibleMoves.get((int)Math.floor(random * possibleMoves.size()));
					currentPlayer.makeMove(randomPiece, randomMove);
					break;
				}
				//System.out.println("sup");



			}
			System.out.println("hey");
			System.out.println("endmove " + moves + " " + currentPlayer.getKing() + " " + otherPlayer.getKing());
			Player temp = otherPlayer;
			otherPlayer = currentPlayer;
			currentPlayer = temp;



		}
		System.out.println(otherPlayer);

	}
}