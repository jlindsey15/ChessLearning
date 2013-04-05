import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class ChessPiece {
	//The content panel
	protected JPanel panel;
	//The image view for the chess piece
	protected ChessImageView imageView;
	
	/**
	 * Constructor for the chess piece
	 * 
	 * @param filename - The filename of the image to display
	 */
	public ChessPiece(String filename) {
		this.setImage(filename);
	}
	
	/**
	 * Sets the image for the imageview for the piece
	 * 
	 * @param filename - The filename of the image to display
	 */
	public void setImage(String filename) {
		this.imageView = new ChessImageView(filename);
		this.panel.removeAll();
		this.panel.add(this.imageView);
	}
	
	/**
	 * Returns the content panel holding the imageview
	 * 
	 * @return the content panel
	 */
	public JPanel getPanel() {
		return this.panel;
	}
	
	private Board myBoard; //the board the piece belongs to
	private Position position;
	public boolean isOnWhiteTeam; //which team you're on
	public ChessPiece() { //default constructor
		;
	}
	public ChessPiece(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		position = new Position(theColumn, theRow);
		isOnWhiteTeam = whiteTeam;
		myBoard = board;
		myBoard.add(this); //constructor adds the piece to the chessboard
	}
	public int getRow() { //obvi
		return position.row;
	}
	public int getColumn() { //obvi
		return position.column;
	}
	public ArrayList<Position> getPositionsInDirection (int horiz, int vert) { 
		//vert and horiz should be either -1, 0, or 1. For example (1, -1) would be diagonal down right since you increase the column number and decrease the row number
																			
		ArrayList<Position> returned = new ArrayList<Position>();
		boolean stop = false;
		Position currentPosition = position;
		while (!stop) {
			currentPosition = new Position(currentPosition.column + horiz, currentPosition.row + vert);
			if (currentPosition.isValid()) {
				returned.add(currentPosition);
			}
			else {
				stop = true;
			}
		}
		return returned;
	}
	
	public ArrayList<Position> removeFriendlyFire(ArrayList<Position> positions) {
		ArrayList<Position> toBeRemoved = new ArrayList<Position>();
		for (Position pos : positions) {
			if (myBoard.getBoard()[pos.column][pos.row] == null) {
				;
			}
			
			else if (myBoard.getBoard()[pos.column][pos.row].isOnWhiteTeam == isOnWhiteTeam ) {
				System.out.println("removing ff");
				toBeRemoved.add(pos);
			}
			
		}
		for (Position posit : toBeRemoved) {
			positions.remove(posit);
		}
		return positions;
	}
	
	public ArrayList<ArrayList<Position>> ignoreAfterObstruction(ArrayList<ArrayList<Position>> meta) {
		for (ArrayList<Position> line : meta) {
			ArrayList<Position> toBeRemoved = new ArrayList<Position>();
			boolean startIgnoring = false;
			for (int i = 0; i < line.size(); i++) { //
				if (startIgnoring) {
					toBeRemoved.add(line.get(i));
				}
				else {
					if (myBoard.isOccupied(line.get(i).column,  line.get(i).row)) {
						startIgnoring = true;
					}
				}
			}
			for (Position removeThis : toBeRemoved) {
				line.remove(removeThis);
			}
			
		}
		return meta;
	}
	public abstract ArrayList<Position> possibleMoves();
}
