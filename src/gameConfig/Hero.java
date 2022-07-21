package gameConfig;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Hero {

    protected Vector2f position;
    protected Rectangle rectangle;
    protected Image image;

    public Hero(float x, float y, int width, int height, Image image) {
        position = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, width, height);
        this.image = image;
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, int tileWidth, int tileHeight) {
        Vector2f trans = new Vector2f(0, 0);
        Input input = gc.getInput();

        //Näillä toiminnoilla mahdollistetaan sankarin liikkuminen kartalla.
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            trans.y = -0.5f * delta;
        } else if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            trans.y = 0.5f * delta;
        } else if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            trans.x = 0.5f * delta;
        } else if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            trans.x = -0.5f * delta;
        }

        if (trans.x != 0 && trans.y != 0) {
            trans.set(trans.x / 1.5f, trans.y / 1.5f);
        }

        // Tarkistuksella varmistetaan, että sankari pysyy kartan sisällä.
        if (position.x + trans.x > tileWidth && position.x + trans.x < (mapWidth - (2*tileWidth))) {
            position.x += trans.x;
        }

        // Tarkistuksella varmistetaan, että sankari pysyy kartan sisällä.
        if (position.y + trans.y > tileHeight && position.y + trans.y < (mapHeight - (4*tileHeight))) {
            position.y += trans.y;
        }
    }

    //Renderöidään sankarin kuva peliin. Tämä tehdään aina updaten jälkeen.
    public void render() {
        image.draw(position.x, position.y);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}