package cube;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import cube.Screen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int width = 512;
	public static int height = width;
	public static String title = "";
	private JFrame frame;
	private Thread thread;
	private boolean running = false;
	private Screen screen;
	public static int timePlayed = 0;
	private Input in;
	int tix = 0;
	Random random = new Random();
	static Game game;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		screen = new Screen(width, height, this);
		frame = new JFrame();
		in = new Input(screen);
		addMouseListener(in);
		addMouseWheelListener(in);
		addKeyListener(in);
	}

	public synchronized void start() {
		requestFocusInWindow();
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		frame.setTitle("");
		requestFocus();
		while (running) {
			timePlayed++;
			tick();
			render();
		}
		stop();
	}

	public void tick() {
		tix++;
		if (tix % 5 == 4)
			Cube.iterate();
		in.update(this);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.render();
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Cube.init();
		game = new Game();
		game.frame.setResizable(true);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}
