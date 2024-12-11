import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Sprite2{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.75;		//change to scale image
	double scaleHeight = 0.75; 		//change to scale image

	public Sprite2() {
		forward 	= getImage("/imgs/"+"troll-face-pixilart.png"); //load the image for Tree
		
		//alter these
		width = 60;
		height = 52;
		x = 700/2 - width/2;
		y = 770;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}

	
	
	
	public Sprite2(int x, int y) {
		
		this();
		this.x = x;
		this.y = y;
	}
	
	public void move(int dir) {
		
		switch(dir) {
		
		case 0: //hop up
			
			vy = -8;
			
			break;
		case 1: //hop down
			
			vy = 8;
			
			break;
		case 2: //hop left
		
			vx = -6;
			
			break;
		case 3: //hop right
			
			vx = 6;
			
			break;
		case 4: //reset
			
			x = 700/2 - width/2;
			y = 770;
			
			break;
		case 5: //log
			
			vx = 5;
			break;
		case 6: // stop
			vx = 0;
			vy = 0;
			break;
		case 7: //log2
			vx = -5;
		}
		
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		if(x > 700) {
			x = 700/2 - width/2;
			y = 770;
		}else if(x < -60) {
			x = 700/2 - width/2;
			y = 770;
		}else if(y < 40) {
			x = 700/2 - width/2;
			y = 770;
			System.out.println("you won!!");
			
			
		}else if(y > 900) {
			x = 700/2 - width/2;
			y = 770;
		}
			
		
		g2.drawImage(forward, tx, null);
		//hitbox
		if(Frame.debugging) {
			g.setColor(Color.blue);
			
		}
		
		

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite2.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
