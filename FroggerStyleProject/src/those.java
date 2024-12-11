import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class those{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2;		//change to scale image
	double scaleHeight = 1; 		//change to scale image

	public those() {
		forward 	= getImage("/imgs/"+"t-h-n-evil3.png"); //load the image for Tree
		
		//alter these
		width = 86;
		height = 94;
		x = -width;
		y = 500;
		vx = 4;
		vy = 4;
		
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

	
	
	
	public those(int x, int y) {
		
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
		if(x > 536) {
			vx = -vx;
		}else if(x < 0) {
			vx = -vx;
		}else if(y < 0) {
			vy = -vy;
		}else if(y > 785) {
			vy = -vy;
		}
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		//hitbox
		if(Frame.debugging) {
			g.setColor(Color.red);
			
		}
		
			

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = those.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
