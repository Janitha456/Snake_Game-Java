import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMain window = new GameMain();
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
	public GameMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Snake Game");
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 608, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); 
		
		
		// Instructions Label
		
		JLabel lblInstructions = new JLabel("Instructions ;\r\n");
		lblInstructions.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 18));
		lblInstructions.setForeground(new Color(255, 255, 255));
		lblInstructions.setBounds(35, 317, 113, 25);
		frame.getContentPane().add(lblInstructions);
		
		
		// PLAY button features and functionalities
		
		JButton btnPlay = new JButton("PLAY");
		
		btnPlay.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame frame1 = new GameFrame();
				frame.dispose();     // Close the Title screen 
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlay.setBackground(new Color(128, 255, 255));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnPlay.setBackground(new Color(0, 255, 128));
			}
		});
		btnPlay.setBackground(new Color(0, 255, 128));
		btnPlay.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 50));
		btnPlay.setBounds(192, 207, 200, 78);
		frame.getContentPane().add(btnPlay);
		
		
		// Instruction 1 Label
		
		JLabel lblInstruction_1 = new JLabel("# Use arrow keys to control the snake.");
		lblInstruction_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblInstruction_1.setForeground(new Color(128, 128, 0));
		lblInstruction_1.setBounds(10, 353, 272, 25);
		frame.getContentPane().add(lblInstruction_1);
		
		
		// Instruction 2 Label
		
		JLabel lblInstruction_2 = new JLabel("# Press \"Space\" to pause the game.");
		lblInstruction_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblInstruction_2.setForeground(new Color(128, 128, 0));
		lblInstruction_2.setBounds(10, 377, 259, 25);
		frame.getContentPane().add(lblInstruction_2);
		
		
		// SNAKE Title Label
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setIcon(new ImageIcon(this.getClass().getResource("/Snaketext.JPG")));
		lblTitle.setBounds(57, 39, 474, 135);
		frame.getContentPane().add(lblTitle);
		
		
		// Snake Image Label
		
		JLabel lblSnake = new JLabel("Snake");
		lblSnake.setIcon(new ImageIcon(this.getClass().getResource("/snakep.png")));
		lblSnake.setBounds(301, 200, 281, 358);
		frame.getContentPane().add(lblSnake);
		
		
		// Icon Image Label (at bottom left corner)
		
		JLabel lblIcon = new JLabel("Icon");
		lblIcon.setIcon(new ImageIcon(this.getClass().getResource("/Icon.JPG")));
		lblIcon.setBounds(21, 429, 101, 129);
		frame.getContentPane().add(lblIcon);
		
		
		// EXIT button features & functionalities
		
		JButton btnExit = new JButton("EXIT");
		
		btnExit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(new Color(253, 64, 36));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(new Color(17, 174, 167));
			}
		});
		btnExit.setBackground(new Color(17, 174, 167));
		btnExit.setFont(new Font("Maiandra GD", Font.BOLD, 27));
		btnExit.setBounds(235, 470, 101, 37);
		frame.getContentPane().add(btnExit);
	}
}
