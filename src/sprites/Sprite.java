package sprites;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import java.awt.*;

public class Sprite extends Shape {
    private Image image; //изображение

    public Sprite(Image image) {
        this.image = image;
    }

    @Override
    public Shape transform(Transform transform) {
        return null;
    }

    @Override
    protected void createPoints() {

    }

    public float getWidth() {
        return image.getWidth(null);
    }

    public float getHeight() {
        return image.getHeight(null);
    }

    public void draw(Graphics g,int x,int y) {
        g.drawImage(image,x,y,null);
    }
}
