package hxhthefirsttreasure;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Graphical User Interface that converts the provided Sprites to a graphical representation as a Pixel Field.
 * This class also provides methods to manipulate that board and set how the
 * graphics are displayed. 
 * 
 * @author Marc Albareda
 * @version 0.3
 *
 */
public class Field extends JPanel {

	ArrayList<Sprite> sprites = new ArrayList<>();
	private boolean recentDraw = false;
	/**
	 * Path to Background. Null for no background.
	 */
	public String background = null;
	
	/**
	 * Horizontal scroll applied
	 */
	private int scrollx=0;
	/**
	 * Vertical scroll applied
	 */
	private int scrolly=0;
	/**
	 * X of last mouse click
	 */
	private int mouseX = -1;
	/**
	 * y of last mouse click
	 */
	private int mouseY = -1;
	/**
	 * X of last mouse click. Will reset after each check.
	 */
	private int currentMouseX = -1;
	/**
	 * Y of last mouse click. Will reset after each check.
	 */
	private int currentMouseY = -1;
	/**
	 * X of last mouse click
	 */
	private int rmouseX = -1;
	/**
	 * Y of last mouse click
	 */
	private int rmouseY = -1;
	/**
	 * X of last mouse click. Will reset after each check.
	 */
	private int rcurrentMouseX = -1;
	/**
	 * Y of last mouse click. Will reset after each check.
	 */
	private int rcurrentMouseY = -1;
	
	

	/**
	 * Event handler every time mouse is clicked.
	 */
	private MouseListener ml = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			int button = e.getButton();
			Point p = e.getPoint();
			if (button == 1) {
				mouseX = p.x;
				mouseY = p.y;
				currentMouseX = p.x;
				currentMouseY = p.y;
			} else if (button == 3) {
				rmouseX = p.x;
				rmouseY = p.y;
				rcurrentMouseX = p.x;
				rcurrentMouseY = p.y;
			}
			

		}

	};
	/**
	 * Redraws field if window size is changed.
	 */
	public ComponentListener cl = new ComponentAdapter() {
		public void componentResized(ComponentEvent e) {
			// TODO
			repaint();
		}
	};

	public Field() {
		// setFocusable(true);
		addMouseListener(ml);

	}

	/**
	 * Adds an ArrayList to the sprite list to be drawn
	 */
	public void add(ArrayList<? extends Sprite> newSprites) {
		if (recentDraw) {
			clear();
			recentDraw = false;
		}
		sprites.addAll(newSprites);
	}

	/**
	 * Adds a Sprite to the sprite list to be drawn
	 */
	public void add(Sprite newSprites) {
		if (recentDraw) {
			clear();
			recentDraw = false;
		}
		sprites.add(newSprites);
	}

	/**
	 * Clears the sprite list
	 */
	public void clear() {
		sprites.clear();
	}

	/**
	 * Draws the current sprite list, if "add" methods have been used before.
	 */
	public void draw() {
		// TODO
		recentDraw = true;
		repaint();
	}

	/**
	 * Draws the provided sprite list, and nothing else
	 */
	public void draw(ArrayList<? extends Sprite> sprites2) {
		clear();
		sprites.addAll(sprites2);
		repaint();
	}

	/**
	 * Draws the provided Sprite, and nothing else
	 */
	public void draw(Sprite s2) {
		sprites.clear();
		sprites.add(s2);
		repaint();

	}
	
	/**
	 * Scrolls the whole field by the specified ammount
	 * @param x The ammount of pixels it should be horizontally scrolled (negative left, positive right)
	 * @param y The ammount of pixels it should be vertically scrolled (negative up, positive down)
	 */
	public void scroll(int x, int y) {
		scrollx+=x;
		scrolly+=y;
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		if (background != null) {
			Image img = null;
			try {
				img = new ImageIcon(background).getImage();
			} catch (Exception e) {
				System.out.println("Error on background img");
			}
			g2d.drawImage(img, (int) 0, (int) 0, (int) (getWidth()), (int) (getHeight()), 0, 0, img.getWidth(this),
					img.getHeight(this), this);

		}
		// System.out.println("painting "+sprites);
		for (int i = 0; i < sprites.size(); i++) {
			if(!sprites.get(i).delete)drawSprite(sprites.get(i), g2d);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private void drawSprite(Sprite sprite, Graphics2D g2d) {

		int x = sprite.x1;
		int y = sprite.y1;
		int width = sprite.x2 - sprite.x1;
		int height = sprite.y2 - sprite.y1;

		try {
			if (sprite.text) {
				// Color inside = new Color(colorlletres[value]);
				// g2d.setPaint(inside);
				// int padding = 5;
				Color inside = new Color(sprite.textColor);
				g2d.setPaint(inside);
				g2d.setFont(sprite.font);
				AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(sprite.angle), x+width/2, y+height/2);
			    g2d.setTransform(a);
				g2d.drawString(sprite.path, sprite.x1, sprite.y2);
				// On drawString the starting point is not the upper left, but the bottom left
			} else {

				Image img;
				if (sprite.useImgArray) {
					img = sprite.imgArray[sprite.currentImg];
				} else {
					img = sprite.img;
				}
				AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(sprite.angle), x+width/2, y+height/2);
			    g2d.setTransform(a);
			    if(!sprite.unscrollable) {
			    	x+=scrollx;
			    	y+=scrolly;
			    }
				g2d.drawImage(img, x, y, width, height, this);
			}
		} catch (Exception e) {
			System.out.println("Error on image " + sprite.path + " object: " + sprite.name);
			e.printStackTrace();
		}
	}
	
	/**
	 * get the X of the pixel last clicked. 
	 */
	public int getMouseX() {
		return mouseX;
	}
	/**
	 * get the Y of the pixel last clicked. 
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * get the X of the pixel last clicked. Returns -1 if no pixel was clicked since
	 * last check.
	 */
	public int getCurrentMouseX() {
		int tmp = currentMouseX;
		currentMouseX = -1;
		return tmp;
	}
	/**
	 * get the Y of the pixel last clicked. Returns -1 if no pixel was clicked
	 * since last check.
	 */
	public int getCurrentMouseY() {
		int tmp = currentMouseY;
		currentMouseY = -1;
		return tmp;
	}
	/**
	 * get the X of the pixel last clicked. 
	 */
	public int getRightMouseX() {
		return rmouseX;
	}
	/**
	 * get the Y of the pixel last clicked. 
	 */
	public int getRightMouseY() {
		return rmouseY;
	}

	/**
	 * get the X of the pixel last clicked. Returns -1 if no pixel was clicked since
	 * last check.
	 */
	public int getCurrentRightMouseX() {
		int tmp = rcurrentMouseX;
		rcurrentMouseX = -1;
		return tmp;
	}
	/**
	 * get the Y of the pixel last clicked. Returns -1 if no pixel was clicked
	 * since last check.
	 */
	public int getCurrentRightMouseY() {
		int tmp = rcurrentMouseY;
		rcurrentMouseY = -1;
		return tmp;
	}

}
