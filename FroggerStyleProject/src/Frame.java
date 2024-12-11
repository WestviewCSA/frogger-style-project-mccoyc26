import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	public static boolean debugging = true;
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	
	//score
	public int score = 0;
	public int theirscore = 0;
	public int lives = 5;
	
	Font newFont = new Font("Courier", Font.BOLD, 50);
	//SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
	//Music soundBang = new Music("bang.wav", false);
	//Music soundHaha = new Music("haha.wav", false);
	SimpleAudioPlayer music = new SimpleAudioPlayer("sound.wav", false);
	
	Sprite2 trollFace = new Sprite2();
	road road = new road();
	grass grass = new grass();
	gameOver game = new gameOver(0,0);
	win winner = new win(0,0);
	water water = new water(0, 0);
	road3 road3 = new road3(0,0);
	grass2 grass3 = new grass2(0, 620);
	
	thoseWhoKnow[] row1 = new thoseWhoKnow [5];
	ArrayList<thoseWhoKnow> row1List = new ArrayList<thoseWhoKnow>();
	
	ArrayList<lives> life = new ArrayList<lives>();
	
	mango[] row2 = new mango [10];
	log[] row3 = new log[4];
	log2[] row4 = new log2[4];
	those[] row5 = new those[1];
	//frame width/height
	int width = 700;
	int height = 900;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		boolean c = false;
		
		water.paint(g);
		road.paint(g);
		grass.paint(g);
		
		road3.paint(g);
		grass3.paint(g);
		
		for(log obj : row3) {
			obj.paint(g);
		}
		
		for(log2 obj : row4) {
			obj.paint(g);
		}
		
		trollFace.paint(g);
		
		//row1 obj paint on the screen
		for(thoseWhoKnow obj : row1) {
			obj.paint(g);
		}
		
		for(thoseWhoKnow obj : row1List) {
			
		}
		
		
		for(mango obj : row2) {
			obj.paint(g);
		}
		
		for(those obj : row5) {
			obj.paint(g);
		}
		
		//score
		g.setFont(newFont);
		g.setColor(Color.darkGray);
		g.drawString("Score: " + score, 20, 90);
		g.setColor(Color.orange);
		g.drawString("Lives: " + lives, 20, 40);
		
		
		if(trollFace.getY() < 43) {
			score += 10;
			trollFace.move(4);
		}
		
		
		
		//collision detection
		//for each thn obj in row1
		for(mango obj : row2) {
			if(obj.collided(trollFace)) {
				trollFace.move(4);
				
				
				lives--;
				if(life.size() > 0) {
					life.remove(life.size()-1);
				}
			}
		}
		
		for(thoseWhoKnow obj : row1) {
			if(obj.collided(trollFace)) {
				trollFace.move(4);
				lives--;
				//when lives is still greater than zero contact with an object removes one life
				if(life.size() > 0) {
					life.remove(life.size()-1);
				}
			}
		}
		
		
		
		
		
		for(log obj : row3) {
			if(obj.collided(trollFace)) {
				c = true;
				trollFace.move(5);
			}
		}
		
		
		for(log2 obj : row4) {
			if(obj.collided(trollFace)) {
				c = true;
				trollFace.move(7);
			}
		}
		
		if(trollFace.getY() < 291 && trollFace.getY() >= 100) {
			if(c == false) {
				trollFace.move(4);
				lives--;
			}
		}
		
		for(those obj : row5) {
			if(obj.collided(trollFace)) {
				music.play(); //contact with this object plays a sound
				
				trollFace.move(4);
				score -= 5;
				lives --;
				if(life.size() > 0) {
					life.remove(life.size()-1);
				}
				System.out.println("points stolen!!");
				
			}
		}
		
		if(lives <= 0) {
			game.paint(g); //paints game over screen
		}
		
		if(score >= 30) {
			winner.paint(g); //paints win screen
		}
		
		
		
		
		
		
		
	}
	
	
	
		
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Troll Face");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();

		//setup any array here - create objects here
		
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new thoseWhoKnow(i*200,450);
		}
		
		//pracice row for ArraList
		for(int i = 0; i < 10; i++) {
			this.row1List.add(new thoseWhoKnow(i*200, 450));
		}
		
		for(int i = 0; i < 5; i++) {
			this.life.add(new lives(i*40, 5));
		}
		
		for(int i = 0; i < row2.length; i++) {
			row2[i] = new mango(i*151, 550);
		}
		
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new log(i*400, 250);
		}
		
		for(int i = 0; i < row4.length; i++) {
			row4[i] = new log2(i*400, 140);
		}
		
		for(int i = 0; i < row5.length; i++) {
			row5[i] = new those(i*100, 0);
		}
		
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode()==87) {
			//up
			trollFace.move(0);
		}else if(arg0.getKeyCode()==83) {
			//down
			trollFace.move(1);
		}else if(arg0.getKeyCode()==65) {
			trollFace.move(2);
		}else if(arg0.getKeyCode()==68) {
			trollFace.move(3);
		}else if(arg0.getKeyCode()==82) { //pressing "r" resets the character position and live to 5 and points to 0
			lives = 5;
			
			score = 0;
			trollFace.move(4);
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==87) {
			//up
			trollFace.move(6);
		}else if(arg0.getKeyCode()==83) {
			//down
			trollFace.move(6);
		}else if(arg0.getKeyCode()==65) { //movement
			trollFace.move(6);
		}else if(arg0.getKeyCode()==68) {
			trollFace.move(6);
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
