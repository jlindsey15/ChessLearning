import java.util.ArrayList;


public class Chessbot { //main class
	public static void main(String args[]) {
		ChessApplication.InitializeChessApplication(800, 800);
		Board board = new Board();
		//tests board creation:
		for (int i = 7; i >=0; i--) {
			for (int j = 0; j < 8; j++) {
				if (board.getBoard()[j][i] == null) {
					System.out.print("empty");
				}
				else {
					System.out.print("full");
				}
				
			}
			System.out.println();
		}
		//tests the getPositionsInDirection method
		board.remove(3, 1);
		board.remove(1,1);
		board.remove(5, 0);
		
		ArrayList<Position> inaLine = board.getBoard()[6][1].getPositionsInDirection(0, 1);
		//Used to test the piece movement rules:
		for (Position pos : inaLine) {
			System.out.println(pos.column + " " + pos.row);
		}
		inaLine = board.getBoard()[4][0].possibleMoves();
		System.out.println("hey: " + (board.getBoard()[4][0] instanceof King));
		for (Position pos : inaLine) {
			System.out.println(pos.column + " " + pos.row + " " + board.isOccupied(pos.column,  pos.row));
		}
	}
}