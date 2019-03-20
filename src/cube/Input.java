package cube;

import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import cube.Screen;

public class Input implements KeyListener, FocusListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static int mouseX = 0, mouseY = 0, mouseButton = 0;
	public static boolean space = false;
	public static boolean[] key = new boolean[256];
	public Screen screen;

	public Input(Screen screen) {
		this.screen = screen;
	}

	public static void update(Game display) {
		if (key[KeyEvent.VK_D])
			Screen.angle += .06;
		space = key[KeyEvent.VK_SPACE];
		if (key[KeyEvent.VK_A])
			Screen.angle -= .06;
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseButton = 0;
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length)
			key[keyCode] = true;
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length)
			key[keyCode] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
	}
}
