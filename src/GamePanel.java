
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int screenWidth = 600;
	static final int ScreenHeight = 600;
	
	static final int unitArea = 20;
	static final int gameUnits = (screenWidth * ScreenHeight)/unitArea;
	
	static final int delay = 100;
	
	final int x[] = new int[gameUnits]; // holds all of the x coordinates of bodyparts of the snake
	final int y[] = new int[gameUnits]; // holds all of the y coordinates of bodyparts of the snake
	
	int bodyParts = 3;    // Initial amount of the bodyparts of the snake
	int foodEaten;       
	int foodX;       // x coordinate of the food
	int foodY;       // y coordinate of the food
	
	char direction = 'R';   // Initial direction(Right) of the snake
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(screenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new myKeyAdapter());
		startGame();
	}
	
	// Start the game
	public void startGame() {
		newFood();             // Create a new food on the screen
		running = true;
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
		
			// Create Food
			g.setColor(Color.red);
			g.fillOval(foodX, foodY, unitArea, unitArea);
			
			// Create Snake
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0 ) {					
					// Head of the snake
					g.setColor(Color.green);
					g.fillOval(x[i], y[i], unitArea, unitArea);
				}
				else {
					// Body of the snake
					g.setColor(Color.yellow);
					g.fillOval(x[i], y[i], unitArea, unitArea);
				}
			}
			
			// Display Score
			g.setColor(Color.cyan);
			g.setFont(new Font("Segoe UI", Font.PLAIN, 35));			
			g.drawString("SCORE: " + foodEaten, 430, 40);
		}
		else {
			gameOver(g);
		}
	}
	
	// This method generate the coordinates of a new food randomly.
	
	public void newFood() {
		foodX = random.nextInt((int)(screenWidth/unitArea)) * unitArea;
		foodY = random.nextInt((int)(ScreenHeight/unitArea)) * unitArea;
	}
	
	// Move the snake	
	public void move() {
		for(int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];   // Shifting all the x coordinates in this array by one spot
			y[i] = y[i-1];   // Shifting all the y coordinates in this array by one spot
		}
		
		// Change the direction of the snake where the snake is headed
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - unitArea;
			break;
			
		case 'D':
			y[0] = y[0] + unitArea;
			break;
			
		case 'L':
			x[0] = x[0] - unitArea;
			break;
			
		case 'R':
			x[0] = x[0] + unitArea;
			break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
	}
	
	// Assign the events for the keys
	
	public class myKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
						
			switch(e.getKeyCode()) {
			
			// Assign the directions for the arrow keys
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
				
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
				
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;

			// Restart the game, when its over
			case KeyEvent.VK_ENTER:         
				if(running == false) {					
					GameFrame frame1 = new GameFrame();			
				}
				break;
				
			// Pause the game	
			case KeyEvent.VK_SPACE:      
				if (timer.isRunning()) {
				    timer.stop();
				}
				else {
				    timer.start();
				}
				break;
			}
		}
		
	}
	
	// Check if the snake hits a food. 
	
	public void checkFood() {
		if((x[0] == foodX) && (y[0] == foodY)) {
			bodyParts++;
			foodEaten++;
			newFood();
		}
	}
	
	public void checkCollisions() {
		
		// Check if the head touches the body of the snake
		for(int i = bodyParts; i>0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		
		// Check if the head touches the left border
		if(x[0] < 0) {
			running = false;
		}
		
		//Check if the head touches the right border
		if(x[0] > screenWidth) {
			running = false;
		}
		
		//Check if the head touches the top border
		if(y[0] < 0) {
			running = false;
		}
		
		//Check if the head touches the bottom border
		if(y[0] > ScreenHeight) {
			running = false;
		}
		
		// If running is false stop the timer
		if(!running) {
			timer.stop();
		}
		
	}
		
	public void gameOver(Graphics g) {
		
		// Display score
		g.setColor(Color.orange);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		g.drawString("SCORE : " + foodEaten, 200, 200);
				
		// Display Game Over Text
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman", Font.BOLD, 75));
		g.drawString("GAME OVER", 70, 300);				
		
		// Display Restart message
		g.setColor(Color.green);
        g.setFont(new Font("Candara", Font.PLAIN, 35));
		g.drawString("Press Enter to Restart", 130, 370);
	}
}
