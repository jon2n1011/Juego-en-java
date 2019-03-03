package hxhthefirsttreasure;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Window representation of the GUI interface. Manages every part of the GUI
 * which is not the board or field proper.
 * 
 * @author Marc Albareda
 * @version 1.42
 */
public class Window extends JFrame {

	/*
	 * TODO Posar  opci� de carregarli un jPanel de pantalla
	 * d'intro?
	 */

	/**
	 * Board used
	 */
	private Board board;
	/**
	 * Second board if a double board setup is used
	 */
	private Board board2;
	/**
	 * Pixel Field used
	 */
	private Field field;
	/**
	 * Horizontal size of the window
	 */
	private int sizeX=700;
	/**
	 * Vertical size of the window
	 */
	private int sizeY=400;
	/**
	 * Checks whether to use a double board setup
	 */
	private boolean secondBoard = false;
	/**
	 * Checks whether labels should appear at the right of the screen
	 */
	private boolean actLabels = false;
	/**
	 * Checks whether the debug label, a label with mouse and keyboard input, should appear
	 */
	private boolean debugLabel = false;
	/**
	 * Content of the labels
	 */
	private String[] labels = { "" };
	/**
	 * Panel containing all the labels
	 */
	private JPanel labelpanel = new JPanel(new GridLayout(0, 1, 5, 5));
	/**
	 * tpanel used as container for both boards if double board mode is activated
	 */
	private JPanel tpanel = new JPanel(new GridLayout(0, 2, 5, 5));
	/**
	 *  Last keyboard char pressed, even if it's not currently pressed
	 */
	private char keyPressed; 
	/**
	 *  Last keyboard char pressed, resets every time on check
	 *  @deprecated
	 */
	private char currentKeyPressed; 
	/**
	 * set with every key CURRENTLY pressed.
	 */
	private Set<Character> pressedKeys = new HashSet<Character>(); 

	/**
	 * music clip to be played
	 */
	Clip musicClip;
	/**
	 * SFX clip to be played
	 */
	Clip sfxClip;
	/**
	 * audio player
	 */
	AudioInputStream audioInputStream;

	public Window(Board t) {
		board = t;
		init();
	}

	public Window(Board t, Board t2) {
		board = t;
		board2 = t2;
		secondBoard = true;
		init();
	}

	public Window(Field f) {
		field = f;
		initField();
	}

	/**
	 * Initializes the window on MatrixBoard mode
	 */
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (secondBoard) {
			tpanel.add(board);
			tpanel.add(board2);
			add(tpanel);
		} else
			add(board);

		if (actLabels) {
			for (String s : labels) {
				JLabel etiq = new JLabel(s);
				labelpanel.add(etiq);
			}
		}
		add(labelpanel, BorderLayout.LINE_END);
		labelpanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		setSize(sizeX, sizeY); 
		setLocation(100, 100);
		setVisible(true);
		setResizable(true);
		board.addComponentListener(board.cl);

		addKeyListener(ka);

	}

	/**
	 * Initializes the window on PixelField mode
	 */
	private void initField() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(sizeX, sizeY);
		setLocationRelativeTo(null);
		setResizable(true);
		add(field);
		

		if (actLabels) {
			for (String s : labels) {
				JLabel etiq = new JLabel(s);
				labelpanel.add(etiq);
			}
		}
		add(labelpanel, BorderLayout.LINE_END);
		labelpanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		setVisible(true);
		field.addComponentListener(field.cl);

		addKeyListener(ka);

	}

	/**
	 * renews the window in case the label changes.
	 */
	private void renew() {
		labelpanel.removeAll();
		if (actLabels) {
			for (String s : labels) {
				JLabel etiq = new JLabel(s);
				labelpanel.add(etiq);
			}

		}
		if (debugLabel) {
			JLabel debug = new JLabel("Ultima tecla premuda " + keyPressed + " Tecla actual   " + currentKeyPressed);
			JLabel debug2 = new JLabel("Llista de tecles premudes actualment" + pressedKeys);
			labelpanel.add(debug);
			labelpanel.add(debug2);
			JLabel debugm = new JLabel(
					"Ultima casella premuda amb el ratoli " + board.getMouseRow() + ", " + board.getMouseCol());
			JLabel debugmr = new JLabel(
					"Ultima casella premuda amb el ratoli dret" + board.getRightMouseRow() + ", " + board.getRightMouseCol());
			labelpanel.add(debugm);
			labelpanel.add(debugmr);

		}
		labelpanel.repaint();
		labelpanel.revalidate();
		// every cell in board is reset as a side effect.

	}
	
	/**
	 * Change Size of the window
	 * @param x: Width
	 * @param y: Height
	 */
	public void changeSize(int x, int y) {
		this.sizeX=x;
		this.sizeY=y;
		this.setSize(sizeX, sizeY);
	}
	
	
	/**
	 * Change Size of the window
	 * @param x: Width
	 * @param y: Height
	 */
	public void scroll(int x, int y) {
		this.sizeX=x;
		this.sizeY=y;
		this.setSize(sizeX, sizeY);
	}

	/**
	 * Stops all music
	 */
	public void stopMusic() {

		try {
			musicClip.stop();
			musicClip.close();
			System.out.println("music stopped");

		} catch (Exception e) {
			System.out.println("Error trying to play sound ");
		}
	}

	/**
	 * resets AudioStream so songs can be changed
	 */
	private void resetAudioStream(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        musicClip = AudioSystem.getClip(); 
		musicClip.open(audioInputStream);
	}

	/**
	 * Plays a music song continously. Stops the current music if there was one
	 * @param Path to a compatible files. .wav and .au are compatible files. .ogg perhaps
	 */
	public void playMusic(String filePath) {

		System.out.println("playing music " + filePath);
		try {
			if(musicClip!=null && musicClip.isOpen())stopMusic();
			resetAudioStream(filePath);

			musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			musicClip.setFramePosition(0);
			musicClip.start();
		} catch (Exception e) {
			System.out.println("Error trying to play sound " + filePath);
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays a music song just once. Stops the current music if there was one
	 * @param Path to a compatible files. .wav and .au are compatible files. .ogg perhaps
	 */
	public void playMusicOnce(String filePath) {

		System.out.println("playing music " + filePath);
		try {
			if(musicClip!=null && musicClip.isOpen())stopMusic();
			resetAudioStream(filePath);

			musicClip.loop(0);
			musicClip.setFramePosition(0);
			musicClip.start();
		} catch (Exception e) {
			System.out.println("Error trying to play sound " + filePath);
			e.printStackTrace();
		}
	}

	/**
	 * Plays a sfx sound once. SFX goes in a different channel than music, so it can be played at the same time as music. Several SFX can sound at once
	 * @param Path to a compatible files. .wav and .au are compatible files. .ogg perhaps
	 */
	public void playSFX(String filePath) {

		System.out.println("playing sfx" + filePath);
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

			// create clip reference
			sfxClip = AudioSystem.getClip();

			// open audioInputStream to the clip
			sfxClip.open(audioInputStream);
			// will not loop
			sfxClip.loop(0);

		} catch (Exception e) {
			System.out.println("Error trying to play sound " + filePath);
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the window. Ends execution of the window options including music
	 */
	public void close() {
		this.close();
	}
	
	public boolean isActLabels() {
		return actLabels;
	}

	/**
	 * activates labels. Labels will appear as text to the right of the screen. Use it for info or debugging
	 */
	public void setActLabels(boolean actetiquetes) {
		this.actLabels = actetiquetes;
		if (!actetiquetes)
			renew();
	}

	public boolean isDebugLabel() {
		return debugLabel;
	}

	/**
	 * activates debug label. A label with keyboard and mouse input info
	 */
	public void setDebugLabel(boolean etiquetadebug) {
		this.debugLabel = etiquetadebug;
		renew();
	}

	/**
	 * gets all the labels as a String array
	 */
	public String[] getLabels() {
		return labels;
	}

	/**
	 * sets the labels. Each String on the array will be one line. Lines will be automatically adjusted to height.
	 */
	public void setLabels(String[] etiquetes) {
		this.labels = etiquetes;
		renew();
	}

	/**
	 * Returns the last key pressed, even if it's not being pressed currently. Used for Pac-man style games (direction is kept until changed)
	 */
	public char getKeyPressed() {
		return keyPressed;
	}
	
	/**
	 * Returns the current key pressed. Works correctly only if there is only one key pressed. For multiple keys use getPressedKeys
	 * @return The current pressed  key. The last pressed if there are multiple. 0 (null character, not '0') if there is none
	 * @deprecated
	 */
	public char getCurrentKeyPressed() {
		if(pressedKeys.toArray().length==0) {
			return 0;
		}else return (char) pressedKeys.toArray()[0];
	}

	/**
	 * Returns a set of Character with all the currently pressed chars. Check whether your character is included with contains
	 */
	public Set<Character> getPressedKeys() {
		return pressedKeys;
	}

	/**
	 * Keyboard integration. Detects pressed keys and returns them as a set.
	 */
	private KeyAdapter ka = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			pressedKeys.add(e.getKeyChar());
			char char1 = e.getKeyChar();
			keyPressed = char1;
			currentKeyPressed = char1;
			if (debugLabel)
				renew();
		}

		@Override
		public synchronized void keyReleased(KeyEvent e) {
			pressedKeys.remove(e.getKeyChar());
			currentKeyPressed = '0';
			if (debugLabel)
				renew();
		}

	};

}
