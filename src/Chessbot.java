import java.util.ArrayList;


public class Chessbot { //main class
	public static void main(String args[]) {
		ChessApplication.InitializeChessApplication(640, 480);
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
		ArrayList<Position> inaLine = board.getBoard()[6][1].getPositionsInDirection(0, 1);
		for (Position pos : inaLine) {
			System.out.println(pos.column + " " + pos.row);
		}
	}
}