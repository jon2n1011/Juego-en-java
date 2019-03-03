package hxhthefirsttreasure;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * <h2>This class is the representation of a Sprite in a Pixel Field.</h2> It
 * measures as a rectangle, has collision functions. Every class in a Pixel
 * Field must extend from this class
 * 
 * @see <a href="https://gitlab.com/malbareda/GraphicBoard">Git Repository</a>
 * @version 0.7
 * @author
 *         <h1>Marc Albareda</h1> <img src=
 *         "https://yt3.ggpht.com/a-/AAuE7mAEQPhIx5GSxS4TWhnD_TltoW2pMDvvuveiOg=s288-mo-c-c0xffffffff-rj-k-no"
 *         alt="Smiley face" height="1200" width="1200">
 *
 * 
 */
public class Sprite {

	/**
	 * Margin to detect collisions. This will mark the horizontal range where a
	 * top-down collision will not be detected
	 */
	public int MARGIN = 5;
	
	/**
	 * Depth to detect collisions. This will mark the depth of the rectangle for the top/bottom/lateral rectangles
	 */
	public int DEPTH = 20;

	/**
	 * the name of the sprite
	 */
	public String name;

	/**
	 * The horizontal position in the field of the upper-left pixel in the sprite
	 */
	public int x1;
	/**
	 * The vertical position in the field of the upper-left pixel in the sprite
	 */
	public int y1;

	/**
	 * The horizontal position in the field of the bottom-right pixel in the sprite
	 */
	public int x2;
	/**
	 * The vertical position in the field of the bottom-right pixel in the sprite
	 */
	public int y2;

	/**
	 * The rotating angle in degrees
	 */
	public double angle;

	/**
	 * The path to the image to that Sprite
	 */
	public String path;

	/**
	 * Specifies whether this Sprite is solid. Non-solid sprites will not collide.
	 */
	public boolean solid = true;
	/**
	 * Specifies whether this sprite is terrain. Terrains are immobile objects which
	 * can be collided and standed on.
	 */
	public boolean terrain;
	/**
	 * Specifies whether this sprite is unscrollable. Unscrollables won't be scrolled ever. Used for HUD or fixed objects
	 */
	public boolean unscrollable;
	/**
	 * Specifies whether this sprite is text. Text won't use an image path and will
	 * print the String specified as path.
	 */
	public boolean text;
	/**
	 * Font for the text if the Sprite is used as text
	 */
	public Font font = new Font("SansSerif", Font.PLAIN, 16);
	/**
	 * Color for the text if the Sprite is used as text
	 */
	public int textColor = 0x000000;
	/**
	 * image object for the sprite
	 */
	public Image img;
	/**
	 * checks if an img Array should be used instead of a single image
	 */
	public boolean useImgArray = false;
	/**
	 * position of the image on the img array that should be accessed currently
	 */
	public int currentImg = 0;
	/**
	 * array of images, needed if a single sprite has several images.
	 */
	public Image[] imgArray;
	/**
	 * marks this Sprite to be deleted.
	 */
	public boolean delete;
	

	public Sprite(String name, int x1, int y1, int x2, int y2, String path) {
		super();
		this.name = name;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.path = path;
		img = new ImageIcon((this.path)).getImage();
		useImgArray = false;
		this.angle = 0;
	}

	public Sprite(String name, int x1, int y1, int x2, int y2, String[] path) {
		super();
		this.name = name;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		imgArray = new Image[path.length];
		for (int i = 0; i < path.length; i++) {
			imgArray[i] = new ImageIcon((path[i])).getImage();
		}
		useImgArray = true;
		this.angle = 0;
	}

	public Sprite(String name, int x1, int y1, int x2, int y2, double angle, String path) {
		super();
		this.name = name;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.path = path;
		img = new ImageIcon((this.path)).getImage();
		useImgArray = false;
		this.angle = angle;
	}

	public Sprite(String name, int x1, int y1, int x2, int y2, double angle, String[] path) {
		super();
		this.name = name;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		imgArray = new Image[path.length];
		for (int i = 0; i < path.length; i++) {
			imgArray[i] = new ImageIcon((path[i])).getImage();
		}
		useImgArray = true;
		this.angle = angle;
	}

	/**
	 * changes the image
	 * 
	 * @param path of the new image
	 */
	public void changeImage(String path) {
		// TODO eventualment anira al setPath quan encapsuli tot...
		this.path = path;
		img = new ImageIcon((this.path)).getImage();
	}

	protected Shape getRect() {
		Rectangle myRect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), (x1 + x2) / 2, (y1 + y2) / 2);
		Shape rotatedRect = at.createTransformedShape(myRect);

		return rotatedRect;
	}

	protected Rectangle getNonRotatingRect() {
		return new Rectangle(x1, y1, x2 - x1, y2 - y1);
	}

	protected Rectangle getBottomRect() {
		return new Rectangle(x1 + MARGIN, y2 - DEPTH, x2 - x1 - MARGIN * 2, DEPTH);
	}

	protected Rectangle getTopRect() {
		return new Rectangle(x1 + MARGIN, y1, x2 - x1 - MARGIN * 2, DEPTH);
	}

	protected Rectangle getLeftRect() {
		return new Rectangle(x1, y1 + MARGIN, DEPTH, y2 - y1 - MARGIN * 2);
	}

	protected Rectangle getRightRect() {
		return new Rectangle(x2 - DEPTH, y1 + DEPTH, MARGIN, y2 - y1 - MARGIN * 2);
	}

	/**
	 * Returns the all the Sprites in the provided list that collide with this
	 * Sprite
	 * 
	 * @param others A list of Sprites or classes that extends Sprite
	 * @return A list with all the sprites in the sprites list in the List that
	 *         collides. <br>
	 *         Returns an Empty list if there is no collision with any Sprite
	 */
	public ArrayList<Sprite> collidesWithList(ArrayList<? extends Sprite> others) {
		ArrayList<Sprite> list = new ArrayList<>();
		for (Sprite s : others) {
			if (this.collidesWith(s))
				list.add(s);
		}
		return list;

	}

	/**
	 * Checks if this sprite collides with the provided sprite. It won't collide against non-solid sprites, or sprites marked as deleted
	 * 
	 * @param other The other sprite
	 */
	public boolean collidesWith(Sprite other) {
		Area areaA = new Area(this.getRect());
		areaA.intersect(new Area(other.getRect()));
		return (!areaA.isEmpty()&&!this.equals(other)&&!this.delete&&!other.delete&&other.solid&&this.solid);
	}
	
	/**
	 * Checks if this sprite collides with the point in coordinates x,y	 * 
	 * @param other The other sprite
	 */
	public boolean collidesWithPoint(int x, int y) {
		return this.getRect().contains(x, y);
	}

	/**
	 * Returns the first Sprite in the provided filed that collides with this Sprite
	 * 
	 * @param f A Pixel Field
	 * @return The first Sprite in the sprites list in the Pixel Field that
	 *         collides. <br>
	 *         The Pixel Field is ordered by insertion in the code <br>
	 *         Returns null if there is no collision with any Sprite
	 */
	public Sprite firstCollidesWithField(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (this.collidesWith(f.sprites.get(i))) {
				// System.out.println(f.sprites.get(i));
				return f.sprites.get(i);
			}

		}
		return null;
	}

	/**
	 * Returns the all the Sprites in the provided filed that collide with this
	 * Sprite
	 * 
	 * @param f A Pixel Field
	 * @return A list with all the sprites in the sprites list in the Pixel Field
	 *         that collides. <br>
	 *         The Pixel Field is ordered by insertion in the code <br>
	 *         Returns an Empty list if there is no collision with any Sprite
	 */
	public ArrayList<Sprite> collidesWithField(Field f) {
		ArrayList<Sprite> list = new ArrayList<>();
		for (int i = 0; i < f.sprites.size(); i++) {
			if (this.collidesWith(f.sprites.get(i)))
				list.add(f.sprites.get(i));
		}
		return list;
	}

	/**
	 * Returns the first Sprite in the provided list that collides with this Sprite
	 * 
	 * @param others A list of Sprites or classes that extends Sprite
	 * @return The first Sprite in the llist that collides <br>
	 *         Returns null if there is no collision with any Sprite
	 */
	public Sprite firstCollidesWithList(ArrayList<? extends Sprite> others) {
		for (Sprite s : others) {
			if (this.collidesWith(s))
				return s;
		}
		return null;

	}

	/**
	 * Returns collision type
	 * 
	 * @param other The second sprite
	 * @return A String depending on the collision type <br>
	 *         c: Contains. The other sprite is inside this <br>
	 *         i: Inside. This sprite is inside the other <br>
	 *         l: left. There is a collision between the left side of this sprite
	 *         and any part of the other sprite <br>
	 *         r: right. There is a collision between the right side of this sprite
	 *         and any part of the other sprite <br>
	 *         u: up. There is a collision between the upper side of this sprite and
	 *         any part of the other sprite <br>
	 *         d: down. There is a collision between the bottom side of this sprite
	 *         and any part of the other sprite <br>
	 *         o: other. There is another type of collision between the two sprites.
	 *         They are colliding, but in an unexpected way <br>
	 *         n: none. There is no collision between the two sprites, or the other
	 *         sprite is not solid. <br>
	 *         The string may contain several chars. for instance, "lu" would mean
	 *         there is both a left and an upper collision.
	 */
	public String getCollisionType(Sprite other) {
		if ((!this.getRect().intersects(other.getNonRotatingRect())) || !other.solid)
			return "n";
		else {
			String ret = "";
			if (this.getRect().contains(other.getNonRotatingRect()))
				ret += 'c';
			if (other.getRect().contains(this.getNonRotatingRect()))
				ret += 'i';
			if (this.getLeftRect().intersects(other.getNonRotatingRect()))
				ret += 'l';
			if (this.getRightRect().intersects(other.getNonRotatingRect()))
				ret += 'r';
			if (this.getTopRect().intersects(other.getNonRotatingRect()))
				ret += 'u';
			if (this.getBottomRect().intersects(other.getNonRotatingRect()))
				ret += 'd';
			if (ret.isEmpty())
				return "o";
			else
				return ret;
		}
	}

	/**
	 * Checks if this sprite has the other sprite just below
	 */
	public boolean stepsOn(Sprite other) {
		return (this.collidesWith(other)&&this.getBottomRect().intersects(other.getTopRect())) ? true : false;
	}

	/**
	 * Checks if this sprite has the other sprite just above
	 */
	public boolean headOn(Sprite other) {
		return (this.collidesWith(other)&&this.getTopRect().intersects(other.getBottomRect())) ? true : false;
	}

	/**
	 * Checks if this sprite has the other sprite just on its left
	 */
	public boolean leftOn(Sprite other) {
		return (this.collidesWith(other)&&this.getLeftRect().intersects(other.getRightRect())) ? true : false;
	}

	/**
	 * Checks if this sprite has the other sprite just on its right
	 */
	public boolean rightOn(Sprite other) {
		return (this.collidesWith(other)&&this.getRightRect().intersects(other.getLeftRect())) ? true : false;
	}

	/**
	 * Checks if this sprite is colliding with terrain below
	 */
	public boolean isGrounded(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (f.sprites.get(i).terrain && this.stepsOn(f.sprites.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * Checks if this sprite is colliding with terrain above
	 */
	public boolean isOnCeiling(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (f.sprites.get(i).terrain && this.headOn(f.sprites.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * Checks if this sprite is colliding with terrain on its sides
	 */
	public boolean isOnColumn(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (f.sprites.get(i).terrain && this.leftOn(f.sprites.get(i)))
				return true;
			if (f.sprites.get(i).terrain && this.rightOn(f.sprites.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * Checks if this sprite is stuck on terrain
	 */
	public boolean isStuckOnTerrain(Field f) {
		return (isGrounded(f) || isOnCeiling(f) || isOnColumn(f));
	}

	/**
	 * Returns this sprite to ground level if it gets stuck into a terrain below it
	 * <br>
	 * Useful for controlling landings<br>
	 * 
	 * @return true if it had succesfully unstucked the character, false if it
	 *         wasn't stuck
	 */
	public boolean getGrounded(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (f.sprites.get(i).terrain && this.stepsOn(f.sprites.get(i))) {
				int dist = (y2 - f.sprites.get(i).y1) - 1;
				y2 -= dist;
				y1 -= dist;
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns this sprite to the appropiate position if it gets stuck into a
	 * terrain above it <br>
	 * Useful for controlling jumps<br>
	 * 
	 * @return true if it had succesfully unstucked the character, false if it
	 *         wasn't stuck
	 */
	public boolean getCeiling(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (f.sprites.get(i).terrain && this.headOn(f.sprites.get(i))) {
				int dist = (f.sprites.get(i).y2 - y1) + 1;
				y2 += dist;
				y1 += dist;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns this sprite to the appropiate position if it gets stuck into a
	 * terrain on its sides <br>
	 * Useful for controlling dashes<br>
	 * 
	 * @return true if it had succesfully unstucked the character, false if it
	 *         wasn't stuck
	 */
	public boolean getSided(Field f) {
		for (int i = 0; i < f.sprites.size(); i++) {
			if (f.sprites.get(i).terrain && this.rightOn(f.sprites.get(i))) {
				int dist = (x2 - f.sprites.get(i).x1) - 1;
				x2 -= dist;
				x1 -= dist;
				return true;
			} else if (f.sprites.get(i).terrain && this.leftOn(f.sprites.get(i))) {
				int dist = (f.sprites.get(i).x2 - x1) + 1;
				x2 += dist;
				x1 += dist;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns this sprite to the appropiate position if it gets stuck into a
	 * terrain <br>
	 * 
	 * @return true if it had succesfully unstucked the character, false if it
	 *         wasn't stuck
	 */
	public boolean deattach(Field f) {
		return (getGrounded(f) || getCeiling(f) || getSided(f));
	}

	/**
	 * marks this sprite to be deleted. Sprites marked for deletion won't collide or be drawn. 
	 */
	public void delete() {
		delete = true;
	}

}
