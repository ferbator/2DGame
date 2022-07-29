package gameConfig;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import static gameConfig.Camera.blocked;
import static gameConfig.GamePlay.tileHeight;
import static gameConfig.GamePlay.tileWidth;

public class Hero {

    //    private float x = 0f;
//    private float y = 0f;
    private float speed = 0.1f;
    protected Vector2f position;
    protected Rectangle rectangle;
    protected Image image;
    public Animation avatar, movingUp, movingDown, movingLeft, movingRight;
    //private boolean[][] blocked;

    public Hero(float x, float y, int width, int height) throws SlickException {
        position = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, width, height);
        this.image = new Image("assets/hero_down_1.png");
        Image[] images = new Image[2];

        Image[] movementUp = {new Image("assets/hero/h_u_1.png"), new Image("assets/hero/h_u_2.png")};
        Image[] movementDown = {new Image("assets/hero/h_d_1.png"), new Image("assets/hero/h_d_2.png")};
        Image[] movementLeft = {new Image("assets/hero/h_l_1.png"), new Image("assets/hero/h_l_2.png")};
        Image[] movementRight = {new Image("assets/hero/h_r_1.png"), new Image("assets/hero/h_r_2.png")};
        int[] duration = {300, 300};

        movingUp = new Animation(movementUp, duration, false);
        movingDown = new Animation(movementDown, duration, false);
        movingLeft = new Animation(movementLeft, duration, false);
        movingRight = new Animation(movementRight, duration, false);

        avatar = movingDown;

    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        return blocked[xBlock % 350][yBlock % 350];
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

// TODO doing this code working

//        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
//            if (!isBlocked(position.x - delta * speed, position.y + 1)
//                    && !isBlocked(position.x - delta * speed, position.y + tileHeight - 1)) {
//                avatar = movingLeft;
//                trans.x = -speed * delta;
//            }
//        }
//        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
//            if (!isBlocked(position.x + tileWidth + delta * speed, position.y + tileHeight - 1)
//                    && !isBlocked(position.x + tileWidth + delta * speed, position.y + 1)) {
//                avatar = movingRight;
//                trans.x = speed * delta;
//            }
//        }
//        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
//            if (!isBlocked(position.x + tileWidth - 1, position.y - delta * speed)
//                    && !isBlocked(position.x + 1, position.y - delta * speed)) {
//                avatar = movingUp;
//                trans.y = -speed * delta;
//            }
//        }
//        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
//            if (!isBlocked(position.x + tileWidth - 1, position.y + tileHeight + delta * speed)
//                    && !isBlocked(position.x + 1, position.y + tileHeight + delta * speed)) {
//                avatar = movingDown;
//                trans.y = speed * delta;
//            }
//        }

        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            avatar = movingUp;
            avatar.update(delta);
            avatar.setSpeed(speed * 10);
            trans.y = -speed * delta;
        }
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            avatar = movingDown;
            avatar.update(delta);
            avatar.setSpeed(speed * 10);
            trans.y = speed * delta;

        }
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            avatar = movingRight;
            avatar.update(delta);
            avatar.setSpeed(speed * 10);
            trans.x = speed * delta;
        }
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            avatar = movingLeft;
            avatar.update(delta);
            avatar.setSpeed(speed * 10);
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
        avatar.draw(position.x, position.y);
        //image.draw(position.x, position.y);
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