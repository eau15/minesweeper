import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;

public class menuFrame {

	// global variables
	public JFrame frame;
	private JPanel contentPane;
	static int boardSize = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuFrame window = new menuFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public menuFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 540, 540);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.setBounds(42, 57, 310, 113);
		contentPane.add(panel);
		
		// difficulty level changer
		String[] difficultyLevels = {" ", "Normal", "Hard" };
		JComboBox comboBox = new JComboBox(difficultyLevels);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == difficultyLevels[1]) {
					boardSize = 10;
				} else if (comboBox.getSelectedItem() == difficultyLevels[2]) {
					boardSize = 25;
				} else
					boardSize = 10;

			}
		});
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboBox.setBounds(242, 206, 198, 71);
		contentPane.add(comboBox);

		JLabel difficultyLabel = new JLabel("Difficulty:");
		difficultyLabel.setForeground(Color.DARK_GRAY);
		difficultyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		difficultyLabel.setBounds(135, 226, 117, 29);
		contentPane.add(difficultyLabel);

		// move to minesweeper frame to start game
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				minesweeperGUI mineGUI = new minesweeperGUI();
				mineGUI.main(null);
			}
		});
		start.setForeground(new Color(165, 42, 42));
		start.setBackground(Color.LIGHT_GRAY);
		start.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		start.setBounds(135, 289, 284, 81);
		contentPane.add(start);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 189, 528, 29);
		contentPane.add(separator);

		JLabel title = new JLabel("MINESWEEPER");
		title.setBounds(6, 57, 364, 113);
		panel.add(title);
		title.setForeground(Color.DARK_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Andale Mono", Font.PLAIN, 45));

		JLabel logo = new JLabel("");
		logo.setBounds(385, 67, 101, 97);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.CENTER);
		URL url = menuFrame.class.getResource("minesweeperLogo.png");
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		logo.setIcon(new ImageIcon(image));
		contentPane.add(logo);

		frame.setVisible(true);
	}

	// get the board size
	public int getSize() {
		frame.setVisible(false);
		return boardSize;
	}

}
