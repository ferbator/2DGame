package entities;

import keyboardDependens.KeyInputHandler;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Map {
    public static Sprite map;
    private static Integer x = 0;
    private static Integer y = 0;

    public static Integer WIDTH = 600;
    public static Integer HEIGHT = 600;

    public Map() {
        map = getSprite("assets\\map.png");
    }

    public void drowing(Graphics g) {
        map.draw(g, x, y);
    }

    public Boolean moving(KeyInputHandler keyControl) {
        float h = map.getHeight() - HEIGHT;
        float w = map.getWidth() - WIDTH;

        if (keyControl.isLeftPressed()) {
            if (x >= -h && x < 0) {
                x++;
            }
        }
        if (keyControl.isRightPressed()) {
            if (x > -h && x <= 0) {
                x--;
            }
        }
        if (keyControl.isUpPressed()) {
            if (y >= -w && y < 0) {
                y++;
            }
        }
        if (keyControl.isDownPressed()) {
            if (y > -w && y <= 0) {
                y--;
            }
        }

        System.out.println(x);
        System.out.println(y);
        return true;
    }

    private Sprite getSprite(String path) {
        BufferedImage sourceImage = null;
        try {
            File file = new File(path);
            sourceImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Sprite(Toolkit.getDefaultToolkit().createImage(Objects.requireNonNull(sourceImage).getSource()));
    }
}
