import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class minesweeperGUI extends JFrame {

	// global variables
	private JPanel contentPane;
	JButton[][] buttons;
	minePlacement b = new minePlacement();
	char[][] board = b.createBoard();
	int count = 0;
	int winorlose = 0;
	int flagsPlaced = 0;
	menuFrame m = new menuFrame();
	int size = m.getSize();
	JLabel flagCount = new JLabel("");
	JToggleButton flagMode = new JToggleButton("FLAGS");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					minesweeperGUI frame = new minesweeperGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public minesweeperGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 540);
		if (size == 25)
			setBounds(100, 100, 850, 1000);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		flagCounter();
		displayButtons();
	}

	/*
	 * generates grid of buttons and tool bar buttons
	 */
	public void displayButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(176, 196, 222));
		buttonPanel.setBounds(42, 53, 460, 460);
		if (size == 25)
			buttonPanel.setBounds(42, 53, 760, 760);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		buttons = new JButton[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j] = new JButton();
				if (size == 10) {
					buttons[i][j].setPreferredSize(new Dimension(45, 45));
				} else {
					buttons[i][j].setPreferredSize(new Dimension(30, 30));
				}
				buttons[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						actionListener(e);
					}
				});

				buttonPanel.add(buttons[i][j]);
			}
		}
		contentPane.add(buttonPanel);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(6, 5, 528, 49);
		contentPane.add(toolBar);

		JButton reset = new JButton("RESET");
		reset.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object[] options = { "OK", "CANCEL" };
				int resetDialog = JOptionPane.showOptionDialog(contentPane, "Would you like reset the game? ", "RESET",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

				if (resetDialog == 0) {
					dispose();
					main(null);
				}
			}
		});
		toolBar.add(reset);

		JButton menu = new JButton("MENU");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "OK", "CANCEL" };
				int resetDialog = JOptionPane.showOptionDialog(contentPane, "Would you like go to the menu? ", "MENU",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

				if (resetDialog == 0) {
					dispose();
					menuFrame menuFrame = new menuFrame();
					menuFrame.main(null);
				}
			}
		});
		menu.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		toolBar.add(menu);

		JButton helpButton = new JButton("?");
		helpButton.setToolTipText(
				"Click on the grid to play. \nNumbers are how many bombs are adjacent. \nIf a bomb is clicked, you lose.");
		helpButton.setEnabled(false);
		helpButton.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		toolBar.add(helpButton);

		JLabel filler = new JLabel("                       ");
		filler.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		toolBar.add(filler);
		toolBar.add(flagCount);

		JLabel flagImage = new JLabel("");
		flagImage.setHorizontalAlignment(SwingConstants.CENTER);
		flagImage.setVerticalAlignment(SwingConstants.CENTER);
		URL url = minesweeperGUI.class.getResource("flag.png");
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		flagImage.setIcon(new ImageIcon(image));
		toolBar.add(flagImage);

		flagMode.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		flagMode.setToolTipText("Toggle to change to Flag mode");
		toolBar.add(flagMode);

	}

	// action listener for buttons on the grid
	public void actionListener(MouseEvent e) {
		// if placing flags
		if (flagMode.isSelected()) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (e.getSource() == buttons[i][j]) {

						if (buttons[i][j].getIcon() == null) {

							buttons[i][j].setHorizontalAlignment(SwingConstants.CENTER);
							buttons[i][j].setVerticalAlignment(SwingConstants.CENTER);
							URL url = minesweeperGUI.class.getResource("flag.png");
							ImageIcon icon = new ImageIcon(url);
							Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
							buttons[i][j].setIcon(new ImageIcon(image));
							flagsPlaced--;

						} else {

							buttons[i][j].setIcon(null);
							flagsPlaced++;

						}
						if (flagsPlaced < 0) {

							buttons[i][j].setIcon(null);

						}
					}

				}
			}
			flagCounterContinuous(); // updates the flag counter
		}
		// to reveal the buttons
		else {
			count++;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (e.getSource() == buttons[i][j]) {
						if (count == 1) {
							checkFirstClick(i, j);
						}
						if (board[i][j] == '*') {
							String mine = "" + board[i][j];
							buttons[i][j].setText(mine);
							winorlose = -1;
							if (hasFlag(i, j) == false) {
								showBombs();
								timer();
							}

						} else if (board[i][j] == '0') {
							tilePressed(i, j);
						} else {
							tilePressed(i, j);
						}
						checkUnclicked();

					}
				}
			}
		}
	}
	// first click will always open up blank spots
	private void checkFirstClick(int i, int j) {
		while (board[i][j] != '0') {
			board = b.createBoard();
		}
	}
	// the # of surrounding mines will show on the button clicked
	private void tilePressed(int i, int j) {
		if (board[i][j] == '0') {
			board[i][j] = ' ';

			for (int x = (i - 1); x <= (i + 1); x++) {
				for (int y = (j - 1); y <= (j + 1); y++) {
					if (x >= 0 && y >= 0 && x < size && y < size) {
						buttons[x][y].setOpaque(true);
						buttons[x][y].setEnabled(false);
						buttons[x][y].setBackground(new Color(196, 196, 196));
						String number = "" + board[x][y];
						buttons[x][y].setText(number);

						if (board[x][y] == '0')
							tilePressed(x, y);
						if (x == i + 1)
							tilePressed(x, y);
						if (y == i + 1)
							tilePressed(x, y);
					}
				}

			}

		}
		showNumber(i, j);

	}
	
	// if the button already has a flag, returns true
	private boolean hasFlag(int i, int j) {
		if (buttons[i][j].getIcon() != null)
			return true;
		else {
			return false;
		}

	}
	
	// # of surrounding mines will show on the selected button
	private void showNumber(int i, int j) {
		buttons[i][j].setOpaque(true);
		buttons[i][j].setEnabled(false);

		if (board[i][j] == '*')
			buttons[i][j].setBackground(Color.red);
		else
			buttons[i][j].setBackground(new Color(196, 196, 196));
		String number = "" + board[i][j];
		buttons[i][j].setText(number);
	}

	// check if user has opened all the buttons except for the mines
	private void checkUnclicked() {
		int buttonsEnabled = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (buttons[x][y].isEnabled()) {
					buttonsEnabled++;
					if (board[x][y] == '*') {
						buttonsEnabled--;
						continue;
					}
				}
			}
		}
		if (buttonsEnabled == 0) {
			winorlose = 1;
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
					buttons[x][y].setBackground(Color.green);
				}
			}
			timer(); 
		}
	}

	// if user clicks a mine, all mines will be revealed
	private void showBombs() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == '*') {
					showNumber(x, y);
				}
			}
		}
	}

	// for users to be able to see where the bombs are before going to end frame
	private void timer() {
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				end();
			}
		}, 1500);
	}

	// once the game is over, the ending frame will be visible
	private void end() {
		dispose();
		endingFrame end = new endingFrame();

		Panel panel = new Panel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.setBounds(91, 57, 364, 113);

		JLabel gameResults = new JLabel();
		if (winorlose == 1) {
			gameResults.setText("You Won!");
		} else if (winorlose == -1) {
			gameResults.setText("You Lose!");
		}
		gameResults.setForeground(Color.DARK_GRAY);
		gameResults.setHorizontalAlignment(SwingConstants.CENTER);
		gameResults.setFont(new Font("Andale Mono", Font.PLAIN, 40));
		gameResults.setBounds(55, 58, 364, 113);
		panel.add(gameResults);
		end.setVisible(true);

		end.getContentPane().add(panel);
	}

	// # of flags depending on the difficulty
	private void flagCounter() {
		flagCount.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		if (size == 10) {
			flagCount.setText("10");
			flagsPlaced = 10;
		} else {
			flagCount.setText("80");
			flagsPlaced = 80;
		}

	}
	// updates flag counter
	private void flagCounterContinuous() {
		if (flagsPlaced < 0) {
			flagsPlaced = 0;
		}
		flagCount.setText("" + flagsPlaced);

	}
}
