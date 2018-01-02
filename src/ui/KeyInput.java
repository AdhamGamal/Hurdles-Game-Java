package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private static boolean space;

    public KeyInput() {
        space = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key(e, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static boolean keyIs(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                return space;
            default:
                return false;
        }
    }

    public void key(KeyEvent e, boolean type) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                space = type;
                break;
        }
    }
}
