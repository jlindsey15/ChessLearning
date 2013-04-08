import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class ChessPiece {
	//The content panel
	protected JPanel panel;
	//The image view for the chess piece
	protected ChessImageView imageView;
	
	protected String imageFilename = "";
	
	/**
	 * Constructor for the chess piece
	 * 
	 * @param filename - The filename of the image to display
	 */
	public ChessPiece(String filename) {
		this.panel = new JPanel();
		this.setImage(filename);
	}
	
	public void LoadImage() {
		setImage(this.imageFilename);
	}
	
	/**
	 * Sets the image for the imageview for the piece
	 * 
	 * @param filename - The filename of the image to display
	 */
	public void setImage(String filename) {
		this.imageView = new ChessImageView(filename);
		this.panel.removeAll();
		this.panel.setPreferredSize(new Dimension(80, 80));
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
	
	private Position position;
	public boolean isOnWhiteTeam; //which team you're on
	public ChessPiece() { //default constructor
		;
	}
	public ChessPiece(int theColumn, int theRow, boolean whiteTeam) { //constructor
		position = new Position(theColumn, theRow);
		isOnWhiteTeam = whiteTeam;		
		this.panel = new JPanel();
		ChessBoard.setChessPiece(position.column, position.row, this);
	}
	public ChessPiece(int theColumn, int theRow, boolean whiteTeam, String filename) { //constructor
		position = new Position(theColumn, theRow);
		isOnWhiteTeam = whiteTeam;
		ChessBoard.setChessPiece(position.column, position.row, this);
		this.panel = new JPanel();
		this.imageFilename = filename;
	}
	public int getRow() { //obvi
		return position.row;
	}
	public int getColumn() { //obvi
		return position.column;
	}
	public void setPosition(Position pos) {
		position = pos;
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
	
	public ArrayList<Position> removeInvalid(ArrayList<Position> positions) {
		ArrayList<Position> toBeRemoved = new ArrayList<Position>();
		for (Position pos : positions) {
			if (!pos.isValid()) {
				toBeRemoved.add(pos);
			}
			else if (ChessBoard.getBoard()[pos.column][pos.row] == null) {
				;
			}
			
			else if (((ChessPiece)ChessBoard.getBoard()[pos.column][pos.row]).isOnWhiteTeam == isOnWhiteTeam ) {
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
					if (ChessBoard.isOccupied(line.get(i).column,  line.get(i).row)) {
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
