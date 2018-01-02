package models;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import ui.KeyInput;

public final class Player extends Object2D {

    private int yBeforeJump = -1;
    private boolean jump = false;
    private boolean endJump = true;
    private boolean dead = false;
    private int maxJumb = 100;
    private int groundLevel;

    public Player(Image img, int groundY, int dy) {
        setImg(img);
        setWidth(img.getWidth(null));
        setHeight(img.getHeight(null));
        setX(40);
        groundLevel = groundY;
        setY(groundY - getHeight());
        setDx(0);
        setDy(0);
        setStoreDy(dy);
    }

    @Override
    public void updateObjectCoordinates() {
        if (jump) {
            if (getY() <= yBeforeJump - maxJumb) {
                yBeforeJump = -1;
                endJump = false;
                jump = false;
                setDy(getStoreDy());
                setImg(new ImageIcon(getClass().getResource("/images/down.png")).getImage());
            } else {
                setDy(-getStoreDy());
                setImg(new ImageIcon(getClass().getResource("/images/up.png")).getImage());
            }
        }
        if (getFeet() > groundLevel) {
            setImg(new ImageIcon(getClass().getResource("/images/stand.png")).getImage());
            endJump = true;
            jump = false;
            setDy(0);
            setY(groundLevel - getHeight());
        }

        if (KeyInput.keyIs(KeyEvent.VK_SPACE)) {
            if (!jump && endJump) {
                jump = true;
                yBeforeJump = getY();
            }
        }
        setY(getY() + getDy());
    }

    public int getFeet() {
        return getY() + getHeight();
    }

    public int getBack() {
        return getX();
    }

    public int getFront() {
        return getX() + getWidth();
    }

    public int getUp() {
        return getY();
    }

    public int getBottom() {
        return getY() + getHeight();
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
