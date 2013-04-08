import java.awt.Color;

import javax.swing.JPanel;


public class ChessTile {
	private JPanel panel;
	
	private int width;
	private int height;
	
	private ChessPiece occupant = null;
	
	/**
	 * Constructor for the tile piece, sets the initial width and the height of the tile
	 * 
	 * @param width - The width of the tile
	 * @param height - The height of the tile
	 */
	public ChessTile(int width, int height) {
		this.width = width;
		this.height = height;
		
		panel = new JPanel();
		panel.setSize(this.width, this.height);
	}
	
	/**
	 * Sets the size of the tile using the parameters,
	 * changes the panel size and the stored variables size
	 * 
	 * @param width - The new width of the tile
	 * @param height - The new height of the tile
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		
		panel.setSize(this.width, this.height);
	}
	
	/**
	 * Gets the content panel for the tile
	 * 
	 * @return {@link #panel}
	 */
	public JPanel getPanel() {
		if (occupant != null) this.panel.add(occupant.getPanel());
	
		return panel;
	}
	
	/**
	 * Sets the occupant to this tilepiece
	 * 
	 * @param piece - The piece to set the tile too
	 */
	public void setOccupant(ChessPiece piece) {		
		this.occupant = piece;
	}
	
	public ChessPiece getOccupant() {
		return this.occupant;
	}
	
	public void removeOccupant() {
		this.occupant = null;
	}
	
	public void SetColor(Color color) {
		panel.setBackground(color);
	}
	
	public boolean isOccupied() {
		return occupant == null;
	}
	
	public void add(JPanel newPanel) {
		this.panel.add(newPanel);
	}
}
