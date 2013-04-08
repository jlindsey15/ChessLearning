import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class ChessBoard {
	//The tiles array
	public static ChessTile[][] tiles;
	public static ChessPiece[][] pieces;

	/**
	 * Initializes the chess board and the array of the tiles
	 */
	public static void Initialize() {
		//Allocate the array of tiles
		tiles = new ChessTile[8][8];
		pieces = new ChessPiece[8][8];
		InitializeChessPieces();
	}

	private static void InitializeChessPieces() {
		for (int i = 0; i < 8; i++) {
			new Pawn(i, 1, true); //creates white pawns - near side
			new Pawn(i, 6, false); //creates black pawns - far side
		}
		//white rooks:
		new Rook(0, 0, true);
		new Rook(7, 0, true);
		//black rooks:
		new Rook(0, 7, false);
		new Rook(7, 7, false);
		//white knights:
		new Knight(1, 0, true);
		new Knight(6, 0, true);
		//black knights:
		new Knight(1, 7, false);
		new Knight(6, 7, false);
		//white bishops:
		new Bishop(2, 0, true);
		new Bishop(5, 0, true);
		//black bishops:
		new Bishop(2, 7, false);
		new Bishop(5, 7, false);
		//white queen:
		new Queen(3, 0, true);
		//black queen:
		new Queen(3, 7, false);
		//white king:
		new King(4, 0, true);
		//black king:
		new King(4, 7, false);
	}

	/**
	 * Generates the initial chess board layout
	 *
	 * @param width - The width of the chess board in pixels
	 * @param height - The height of the chess board in pixels
	 * @param firstTileColor - The first tile color for the chess board
	 * @param secondTileColor - The second tile color for the chess board
	 * @return - The content panel holding the chess board view
	 */
	public static JPanel GenerateChessBoard(int width, int height, Color firstTileColor, Color secondTileColor) {
		JPanel contentPanel = new JPanel();
		contentPanel.setSize(width, height);
		contentPanel.setPreferredSize(new Dimension(width, height));

		//Create the grid layout manager to make tile placement easier
		GridLayout layout = new GridLayout(0, 8);
		layout.setHgap(2);
		layout.setVgap(2);

		contentPanel.setLayout(layout);

		//Set the tile size
		int gridSize = (int)(height/8);

		//Make all the tiles
		for (int y = 0; y < 8; ++y) {
			for (int x = 0; x < 8; ++x) {
				tiles[x][y] = new ChessTile(gridSize, gridSize);

				boolean firstColor = ((x % 2 != 0 && y % 2 == 0) || (x % 2 == 0 && y % 2 != 0));
				boolean secondColor = ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0));

				System.out.println("Coordinate: (" + x + ", " + y + ") First Color: " + firstColor + " Second Color: " + secondColor);

				//Check to see the position of the tile and determine the color
				if (x % 2 != 0 && y % 2 == 0) tiles[x][y].SetColor(firstTileColor);
				else if (x % 2 == 0 && y % 2 != 0) tiles[x][y].SetColor(firstTileColor);
				else if (x % 2 == 0 && y % 2 == 0) tiles[x][y].SetColor(secondTileColor);
				else if (x % 2 != 0 && y % 2 != 0) tiles[x][y].SetColor(secondTileColor);

				//Add the tile to the panel
				if (pieces[x][y] != null) {
					tiles[x][y].setOccupant(pieces[x][y]);
					pieces[x][y].LoadImage();
				}
				contentPanel.add(tiles[x][y].getPanel());
			}
		}

		return contentPanel;
	}

	/**
	 * Sets the chess piece for the position.
	 *
	 * @param x - The x coordinate for the chess piece (board coordinates 1 - 8)
	 * @param y - The y coordinate for the chess piece (board coordinates 1 - 8)
	 * @param piece - The piece to set to the position
	 */
	public static void setChessPiece(int x, int y, ChessPiece piece) {
		//Set the occupant for that position
		pieces[x][y] = piece;
	}

	public static void removeChessPiece(int x, int y) {
		pieces[x][y] = null;
	}

	public static void removeChessPiece(ChessPiece piece) {
		pieces[piece.getColumn()][piece.getRow()] = null;
	}

	public static ChessPiece[][] getBoard() {
		return pieces;
	}

	public static boolean isOccupied(int column, int row) {
		return pieces[column][row] != null;
	}
	public static void move(ChessPiece piece, Position pos) {
		removeChessPiece(piece.getColumn(), piece.getRow());
		setChessPiece(pos.column, pos.row, piece);
		piece.setPosition(pos);

	}

	public static void setBoard(ChessPiece[][] board) {
		pieces = board;
	}

}