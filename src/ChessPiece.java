import javax.swing.JPanel;

public class ChessPiece {
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
}
