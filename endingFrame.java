import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class endingFrame extends JFrame {

	private JPanel contentPane;
	minesweeperGUI mineGUI = new minesweeperGUI();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					endingFrame frame = new endingFrame();
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
	public endingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(217, 208, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// difficulty changer
		String[] difficultyLevels = {" ", "Normal", "Hard" };
		JComboBox comboBox = new JComboBox(difficultyLevels);
		comboBox.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				menuFrame menu = new menuFrame();
				if (comboBox.getSelectedItem() == difficultyLevels[1]) {
					menu.boardSize = 10;
					menu.frame.setVisible(false);
				} else if (comboBox.getSelectedItem() == difficultyLevels[2]) {
					menu.boardSize = 25;
					menu.frame.setVisible(false);
				} else
					menu.boardSize = 10;
					menu.frame.setVisible(false);

			}
		});
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboBox.setBounds(242, 206, 198, 71);
		contentPane.add(comboBox);

		JLabel difficultyLabel = new JLabel("Difficulty:");
		difficultyLabel.setForeground(Color.DARK_GRAY);
		difficultyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		difficultyLabel.setBounds(113, 226, 117, 29);
		contentPane.add(difficultyLabel);

		// open minesweeper frame
		JButton restart = new JButton("RESTART");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mineGUI.main(null);
			}
		});
		restart.setForeground(new Color(165, 42, 42));
		restart.setBackground(Color.LIGHT_GRAY);
		restart.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		restart.setBounds(125, 289, 277, 91);
		contentPane.add(restart);

	}
}
