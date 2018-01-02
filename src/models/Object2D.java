package models;

import java.awt.Image;

public abstract class Object2D {

    private int x;
    private int y;
    private int width;
    private int height;
    private int dx;
    private int dy;
    private int storeDy;
    private int storeDx;
    private Image img;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getStoreDy() {
        return storeDy;
    }

    public int getStoreDx() {
        return storeDx;
    }

    public void setStoreDy(int storeDy) {
        this.storeDy = storeDy;
    }

    public void setStoreDx(int storeDx) {
        this.storeDx = storeDx;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public abstract void updateObjectCoordinates();
}
