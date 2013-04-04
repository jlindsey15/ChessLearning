import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessApplication {
	private static JFrame frame;

	/**
	 * Initializes the window. It also constructs the window and sets all the proper properties. It then adds
	 * the panel which contains all of the UI elements needed to construct the window
	 *
	 * @param width - The width of the window
	 * @param height - The height of the window
	 */
	public static void InitializeChessApplication(int width, int height) {
		frame = new JFrame("Chess Bot Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width, height);

		frame.getContentPane().add(CreateInterface(width, height));

		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creates the interface for the window, it constructs all of the UI elements into a hierarchy,
	 * which are all inside the panel object that is returned to the initialize method
	 *
	 * @param width - The width of the window
	 * @param height - The height of the window
	 * @return The panel which contains all of the UI elements
	 */
	private static JPanel CreateInterface(int width, int height) {
		JPanel panel = new JPanel();
		panel.setSize(width, height);
		panel.setPreferredSize(new Dimension(width, height));

		GridLayout layout = new GridLayout(0, 8);
		layout.setHgap(2);
		layout.setVgap(2);
		panel.setLayout(layout);

		CreateChessBoard(panel, width, height);

		return panel;
	}

	/**
	 * Creates the chess board. Uses the panel object passing in through the parameter to add a chess board too it.
	 * Creates a board with dynamically sized chess board tiles
	 *
	 * @param panel - The panel to add the tile too
	 * @param width - The width of the board in pixels
	 * @param height - The height of the board in pixels
	 */
	private static void CreateChessBoard(JPanel panel, int width, int height) {
		int gridSize = (int)(height/8);

		for (int y = 0; y < 8; ++y) {
			for (int x = 0; x < 8; ++x) {
				JPanel gridPanel = new JPanel();
				gridPanel.setSize(gridSize, gridSize);
				if (x % 2 == 0 && y % 2 == 0) gridPanel.setBackground(Color.CYAN);
				else if (x % 2 != 0 && y % 2 == 0) gridPanel.setBackground(Color.RED);
				else if (x % 2 == 0 && y % 2 != 0) gridPanel.setBackground(Color.RED);
				else gridPanel.setBackground(Color.CYAN);

				panel.add(gridPanel);
			}
		}
	}
}