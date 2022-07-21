package gameConfig;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GamePlay extends BasicGameState {

    static int WIDTH = 500;
    static int HEIGHT = 500;

    private TiledMap map;
    private Rectangle square;
    private SpriteSheet hero;
    private Hero player;
    private Camera camera;
    private float x = 140f;
    private float y = 300f;

    private float mapX = 140f;
    private float mapY = 300f;

    private float speed = 0.1f;
    private boolean[][] blocked;
    private int mapHeight;
    private int mapWidth;
    private int tileWidth = 32;
    private int tileHeight = 32;
    private static final int spriteWidth = 50;
    private static final int spriteHeight = 50;

    private static final int numberOfTilesInRow = 30;
    private static final int numberOfTilesInColumn = 30;
    private static final int numberOfLayers = 2;

    public GamePlay(int stateId) {
    }

    private void initializeBlocked() {
        for (int l = 0; l < numberOfLayers; l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < numberOfTilesInColumn; c++) {
                    for (int r = 0; r < numberOfTilesInRow; r++) {
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
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
//        square = new Rectangle((int) x, (int) y, 32, 32);
//        map = new TiledMap("entities/map1.tmx");
//        blocked = new boolean[numberOfTilesInRow][numberOfTilesInColumn];
//        initializeBlocked();

        map = new TiledMap("entities/map1.tmx");
//        blocked = new boolean[numberOfTilesInRow][numberOfTilesInColumn];
//        initializeBlocked();
        mapWidth = map.getWidth() * map.getTileWidth();
        mapHeight = map.getHeight() * map.getTileHeight();
        tileHeight = map.getTileHeight();
        tileWidth = map.getTileWidth();
        player = new Hero(tileWidth * 4, tileHeight * 4, 32, 32, new Image("assets/hero_down_1.png"));
        camera = new Camera(map, mapWidth, mapHeight);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

//        FactoryWU.frame.GP.cam.locationX = (int) (-FactoryWU.world.player.getXInt()+((this.getWidth()/cam.zoomLevel)/2)-(10*cam.zoomLevel));
//        FactoryWU.frame.GP.cam.locationY = (int) (-FactoryWU.world.player.getYInt()+((this.getHeight()/cam.zoomLevel)/2)-(10*cam.zoomLevel));

//        map.render(0, 0);
//        graphics.drawString("Howdy! " + "X:" + x + "  Y:" + y + "\n" + " V:" + speed, 500, 50);
//        graphics.fill(square);

        camera.translate(graphics, player);
        map.render(0, 0);
        player.render();
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        return blocked[xBlock][yBlock];
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        player.update(gameContainer, mapWidth, mapHeight, i, tileWidth, tileHeight);
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

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            stateBasedGame.enterState(0);
        }

    }
}