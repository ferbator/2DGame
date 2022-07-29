package gameConfig;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import static gameConfig.BasicGame.windowWidth;
import static gameConfig.BasicGame.windowHeight;
import static gameConfig.GamePlay.*;

public class Camera {

    private int x, y;
    private int mapWidth, mapHeight;
    public Rectangle viewPort;
    private TiledMap map;
    private boolean[][] blocked;

    public Camera(TiledMap map, int mapWidth, int mapHeight) {
        this.x = 100;
        this.y = 100;
        this.viewPort = new Rectangle(x, y, windowWidth, windowHeight);
        this.map = map;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    private void initializeBlocked() {
        for (int l = 0; l < numberOfLayers; l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = x / 30; c < (int) ((viewPort.getWidth()) / 30) - x / 30 + 1; c++) {
                    for (int r = y / 30; r < (int) ((viewPort.getHeight()) / 30) - y / 30 + 1; r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }

    public boolean[][] translate(Graphics graphics, Hero hero) {
        if (hero.getX() - (float) windowWidth / 2 <= 0) {
            x = 0;
        } else if (hero.getX() + (float) windowWidth / 2 >= mapWidth) {
            x = -mapWidth + windowWidth;
        } else {
            x = (int) -hero.getX() + windowWidth / 2;
        }

        if (hero.getY() - (float) windowHeight / 2 <= 0) {
            y = 0;
        } else if (hero.getY() + (float) windowHeight / 2 >= mapHeight) {
            y = -mapHeight + windowHeight;
        } else {
            y = (int) -hero.getY() + windowHeight / 2;
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
        //map.render(0, 0);
        int tmpXStart = (int) (viewPort.getX() / tileWidth);
        int tmpYStart = (int) (viewPort.getY() / tileHeight);
        int tmpXFinish = (int) ((viewPort.getWidth()+1) / tileWidth) - tmpXStart + 2;
        int tmpYFinish = (int) ((viewPort.getHeight()+1) / tileHeight) - tmpYStart + 2;
        map.render(tmpXStart, tmpYStart, 0, 0, tmpXFinish, tmpYFinish);
        //initializeBlocked();
    }
}