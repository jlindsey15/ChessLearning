import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class ChessBoard {
	//The tiles array
	public static ChessTile[][] tiles;
	
	/**
	 * Initializes the chess board and the array of the tiles
	 */
	public static void Initialize() {
		//Allocate the array of tiles
		tiles = new ChessTile[8][8];
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
				tiles[y][x] = new ChessTile(gridSize, gridSize);
					
				//Check to see the position of the tile and determine the color
				if (x % 2 != 0 && y % 2 == 0) tiles[y][x].SetColor(firstTileColor);
				else if (x % 2 == 0 && y % 2 != 0) tiles[y][x].SetColor(firstTileColor);
				else if (x % 2 == 0 && y % 2 == 0) tiles[y][x].SetColor(secondTileColor);
				else if (x % 2 != 0 && y % 2 != 0) tiles[y][x].SetColor(secondTileColor);
							
				//Add the tile to the panel
				contentPanel.add(tiles[y][x].getPanel());
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
		tiles[y][x].setOccupant(piece);
	}
}