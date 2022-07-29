package gameConfig;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import static gameConfig.GamePlay.tileHeight;
import static gameConfig.GamePlay.tileWidth;

public class Hero {

    private float x = 0f;
    private float y = 0f;
    private float speed = 0.1f;
    protected Vector2f position;
    protected Rectangle rectangle;
    protected Image image;
    private boolean[][] blocked;

    public Hero(float x, float y, int width, int height, Image image) {
        position = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, width, height);
        this.image = image;
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        return blocked[xBlock][yBlock];
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, int tileWidth, int tileHeight, boolean[][] blocked) {

        //this.blocked = blocked;

        Vector2f trans = new Vector2f(0, 0);
        Input input = gc.getInput();

//        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
//            if (!isBlocked(x - i * speed, y + 1) && !isBlocked(x - i * speed, y + tileHeight - 1)) {
//                x -= speed * i;
//                square.setX((int) x);
//            }
//        }
//        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
//            if (!isBlocked(x + tileWidth + i * speed, y + tileHeight - 1) && !isBlocked(x + tileWidth + i * speed, y + 1)) {
//                x += speed * i;
//                square.setX((int) x);
//            }
//        }
//        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
//            if (!isBlocked(x + tileWidth - 1, y - i * speed) && !isBlocked(x + 1, y - i * speed)) {
//                y -= speed * i;
//                square.setY((int) y);
//            }
//        }
//        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
//            if (!isBlocked(x + tileWidth - 1, y + tileHeight + i * speed) && !isBlocked(x + 1, y + tileHeight + i * speed)) {
//                y += speed * i;
//                square.setY((int) y);
//            }
//        }
//        if (input.isKeyDown(Input.KEY_LSHIFT)) speed = 0.8f;
//        else speed = 0.4f;


        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            trans.y = -speed * delta;
        }
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            trans.y = speed * delta;
        }
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            trans.x = speed * delta;
        }
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            trans.x = -speed * delta;
        }
        if (input.isKeyDown(Input.KEY_LSHIFT)) speed = 0.5f;
        else speed = 0.1f;

//        if (trans.x != 0 && trans.y != 0) {
//            trans.set(trans.x / 1.5f, trans.y / 1.5f);
//        }

//        if (position.x + trans.x > tileWidth
//                && position.x + trans.x < (mapWidth - (2 * tileWidth))) {
//            position.x += trans.x;
//        }
//
//        if (position.y + trans.y > tileHeight
//                && position.y + trans.y < (mapHeight - (4 * tileHeight))) {
//            position.y += trans.y;
//        }

        position.x += trans.x;
        position.y += trans.y;
    }

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