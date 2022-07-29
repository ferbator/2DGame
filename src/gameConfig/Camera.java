package gameConfig;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import java.util.Arrays;

import static gameConfig.GamePlay.*;

public class Camera {

    private int x, y;
    private int mapWidth, mapHeight;
    public Rectangle viewPort;
    private TiledMap map;
    public static boolean[][] blocked;
    private final GameContainer gameContainer;

    public Camera(TiledMap map, GameContainer gameContainer, int mapWidth, int mapHeight) {
        this.x = 100;
        this.y = 100;
        this.gameContainer = gameContainer;
        this.viewPort = new Rectangle(x, y, gameContainer.getWidth(), gameContainer.getHeight());
        this.map = map;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        blocked = new boolean[gameContainer.getWidth() % tileWidth][gameContainer.getHeight() % tileHeight];
    }

    //TODO rewrite initialize blocked

    private void initializeBlocked() {
        int tmpXStart = (int) (viewPort.getX() / tileWidth - 1) % 300;
        int tmpYStart = (int) (viewPort.getY() / tileHeight - 1);
        int tmpXFinish = (int) ((viewPort.getWidth()) / tileWidth) - tmpXStart - 1;
        int tmpYFinish = (int) ((viewPort.getHeight()) / tileHeight) - tmpYStart - 1;

        for (int l = 0; l < numberOfLayers; l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = Math.abs(tmpXStart); c < Math.abs(tmpXFinish) % 300; c++) {
                    for (int r = Math.abs(tmpYStart) % 300; r < Math.abs(tmpYFinish) % 300; r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }

    public boolean[][] translate(Graphics graphics, Hero hero) {
        if (hero.getX() - (float) gameContainer.getWidth() / 2 <= 0) {
            x = 0;
        } else if (hero.getX() + (float) gameContainer.getWidth() / 2 >= mapWidth) {
            x = -mapWidth + gameContainer.getWidth();
        } else {
            x = (int) -hero.getX() + gameContainer.getWidth() / 2;
        }

        if (hero.getY() - (float) gameContainer.getHeight() / 2 <= 0) {
            y = 0;
        } else if (hero.getY() + (float) gameContainer.getHeight() / 2 >= mapHeight) {
            y = -mapHeight + gameContainer.getHeight();
        } else {
            y = (int) -hero.getY() + gameContainer.getHeight() / 2;
        }

        graphics.setColor(Color.black);
        graphics.setBackground(Color.black);
        viewPort.setX(x);
        viewPort.setY(y);
        graphics.translate(viewPort.getX(), viewPort.getY());
        drawMap();
        graphics.drawString("X:" + hero.getX() + "\n" + "Y:" + hero.getY() + "\n", -x + 10, -y + 40);
        graphics.drawString("WorldX:" + x + "\n" + "WorldY:" + y + "\n", -x + 10, -y + 80);
        return blocked;
    }

    public void drawMap() {
//        map.render(0, 0);
        int tmpXStart = (int) (viewPort.getX() / tileWidth);
        int tmpYStart = (int) (viewPort.getY() / tileHeight);
        int tmpXFinish = (int) ((viewPort.getWidth()) / tileWidth) - tmpXStart + 2;
        int tmpYFinish = (int) ((viewPort.getHeight()) / tileHeight) - tmpYStart + 2;
        map.render(tmpXStart, tmpYStart, 0, 0, tmpXFinish, tmpYFinish);
        //initializeBlocked();
        //sdSystem.out.println(Arrays.deepToString(blocked));
    }
}