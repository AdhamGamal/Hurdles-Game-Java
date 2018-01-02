package models;

import java.awt.Image;
import java.awt.event.KeyEvent;
import static ui.Hurdles_Game.getRandomPosition;
import ui.KeyInput;

public final class Block extends Object2D {

    private final Player player;

    public Block(Image img, int x, int groundY, int dx, Player player) {
        setImg(img);
        setWidth(img.getWidth(null));
        setHeight(img.getHeight(null));
        setX(x);
        setY(groundY - getHeight());
        setDx(0);
        setStoreDx(dx);
        this.player = player;
    }

    @Override
    public void updateObjectCoordinates() {
        //the first space the player will press will make the game began        
        if (KeyInput.keyIs(KeyEvent.VK_SPACE)) {
            //make background move left
            setDx(getStoreDx());
        }
        //  +*+
        //  *** +*  
        //  +*+ **
        if ((getBack() >= player.getBack() && getBack() <= player.getFront() && getUp() >= player.getUp() && getUp() <= player.getBottom())
                //  +*+
                //  *** *+  
                //  +*+ **
                || (getFront() >= player.getBack() && getFront() <= player.getFront() && getUp() >= player.getUp() && getUp() <= player.getBottom())) {
            player.setDead(true);
        }
        setX(getX() - getDx());
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
}
