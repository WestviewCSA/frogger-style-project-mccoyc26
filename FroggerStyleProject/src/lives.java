import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class lives{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.5;		//change to scale image
	double scaleHeight = 0.5; 		//change to scale image

	public lives() {
		forward 	= getImage("/imgs/"+"those-who-know_-pixilart.png"); //load the image for Tree
		
		//alter these
		width = 82;
		height = 84;
		x = -width;
		y = 500;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}

	
public boolean collided(Sprite2 character) {
		
		//represent every object as a rect
		//then check if they are intersecting
		Rectangle main = new Rectangle(
			character.getX(),
			character.getY(),
			character.getWidth(),
			character.getHeight()
			);
			
		
		Rectangle trollFace = new Rectangle(x, y, width, height);
		
		//user built-in method to check intersection
		return main.intersects(trollFace);
	}

	
	
	
	public lives(int x, int y) {
		
		this();
		this.x = x;
		this.y = y;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		//infinite scrolling - teleport
		if(x > 800) {
			x = -200;
		}
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		//hitbox
		if(Frame.debugging) {
			g.setColor(Color.green);
			
		}
			

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = lives.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
