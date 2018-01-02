package models;

import java.awt.Image;
import java.awt.event.KeyEvent;
import ui.KeyInput;

public final class Background extends Object2D {

    public Background(Image img, int y, int dx) {
        setImg(img);
        setWidth(img.getWidth(null));
        setHeight(img.getHeight(null));
        setX(0);
        setY(y);
        setDx(0);
        setStoreDx(dx);
    }

    @Override
    public void updateObjectCoordinates() {
        //the first space the player will press will make the game began
        if (KeyInput.keyIs(KeyEvent.VK_SPACE)) {
            //make background move left
            setDx(getStoreDx());
        }
        setX(getX() - getDx());
        //repeat the background and border
        if (getX() <= -(getImg().getWidth(null) / 2)) {
            setX(0);
        }
    }
}
