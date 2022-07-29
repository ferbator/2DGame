package gameConfig;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import static gameConfig.Camera.blocked;

public class GamePlay extends BasicGameState {

    private final int stateId;

    private TiledMap map;
    private Rectangle square;

    private Hero player;
    private Camera camera;

    private SpriteSheet hero;
    private static final int spriteWidth = 50;
    private static final int spriteHeight = 50;

    private float x = 140f;
    private float y = 300f;

    private float speed = 0.1f;
    //public boolean[][] blocked;
    private static int mapHeight;
    private static int mapWidth;
    public static int tileWidth;
    public static int tileHeight;
    public static int numberOfLayers;
    public static final int numberOfTilesInRow = 30;
    public static final int numberOfTilesInColumn = 30;

    public GamePlay(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return stateId;
    }

//    private void initializeBlocked() {
//        for (int l = 0; l < numberOfLayers; l++) {
//            String layerValue = map.getLayerProperty(l, "blocked", "false");
//            if (layerValue.equals("true")) {
//                for (int c = 0; c < numberOfTilesInColumn; c++) {
//                    for (int r = 0; r < numberOfTilesInRow; r++) {
//                        if (map.getTileId(c, r, l) != 0) {
//                            blocked[c][r] = true;
//                        }
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new TiledMap("assets/map1.tmx");
//        blocked = new boolean[numberOfTilesInRow][numberOfTilesInColumn];
//        initializeBlocked();
        mapWidth = map.getWidth() * map.getTileWidth();
        mapHeight = map.getHeight() * map.getTileHeight();
        tileHeight = map.getTileHeight();
        tileWidth = map.getTileWidth();
        numberOfLayers = map.getLayerCount();
        player = new Hero(tileWidth * 10, tileHeight * 2, 32, 32);
        camera = new Camera(map, gameContainer, mapWidth, mapHeight);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(Color.white);
        graphics.setColor(Color.white);
        camera.translate(graphics, player);
        graphics.fill(new Rectangle(tileWidth * 11 - 1, tileHeight * 5 - 1, 32, 32));
        graphics.fill(new Rectangle(tileWidth * 10 - 1, tileHeight * 10 - 1, 32, 32));
        player.render();
    }

//    private boolean isBlocked(float x, float y) {
//        int xBlock = (int) x / tileWidth;
//        int yBlock = (int) y / tileHeight;
//        return blocked[xBlock][yBlock];
//    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        player.update(gameContainer, mapWidth, mapHeight, i, tileWidth, tileHeight, blocked);
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            stateBasedGame.enterState(0);
        }
    }
}