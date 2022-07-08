package gameConfig;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class BasicGame extends BasicGameState {
    private TiledMap map;
    private Rectangle square;
    private SpriteSheet hero;
    private float x = 140f;
    private float y = 300f;
    private float speed = 0.1f;
    private boolean[][] blocked;

    private static final int TILEWIDTH = 32;
    private static final int TILEHEIGHT = 32;
    private static final int NUMBEROFTILESINAROW = 30;
    private static final int NUMBEROFTILESINACOLUMN = 30;
    private static final int NUMBEROFLAYERS = 2;

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < NUMBEROFTILESINACOLUMN; c++) {
                    for (int r = 0; r < NUMBEROFTILESINAROW; r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getID() {
        return 1100;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        square = new Rectangle((int) x, (int) y, 50, 50);
        map = new TiledMap("entities/map1.tmx");
        blocked = new boolean[NUMBEROFTILESINAROW][NUMBEROFTILESINACOLUMN];
        initializeBlocked();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(0, 0);
        graphics.setColor(Color.black);
        graphics.drawString("Howdy! " + "X:" + x + "  Y:" + y + "\n" + " V:" + speed, 500, 50);
        graphics.fill(square);
        graphics.setBackground(Color.white);
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / TILEWIDTH;
        int yBlock = (int) y / TILEHEIGHT;
        return blocked[xBlock][yBlock];
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            if (!isBlocked(x - i * speed, y + 1) && !isBlocked(x - i * speed, y + TILEHEIGHT - 1)) {
                x -= speed * i;
                square.setX((int) x);
            }
        }
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            if (!isBlocked(x + TILEWIDTH + i * speed, y + TILEHEIGHT - 1) && !isBlocked(x + TILEWIDTH + i * speed, y + 1)) {
                x += speed * i;
                square.setX((int) x);
            }
        }
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            if (!isBlocked(x + TILEWIDTH - 1, y - i * speed) && !isBlocked(x + 1, y - i * speed)) {
                y -= speed * i;
                square.setY((int) y);
            }
        }
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            if (!isBlocked(x + TILEWIDTH - 1, y + TILEHEIGHT + i * speed) && !isBlocked(x + 1, y + TILEHEIGHT + i * speed)) {
                y += speed * i;
                square.setY((int) y);
            }
        }
        if (input.isKeyDown(Input.KEY_LSHIFT)) speed = 0.8f;
        else speed = 0.4f;

    }
}