package entities;

import keyboardDependens.KeyInputHandler;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static entities.Map.HEIGHT;
import static entities.Map.WIDTH;

public class Player {
    public static Sprite hero;
    private static Integer x = HEIGHT / 3;
    private static Integer y = WIDTH / 3;

    public Player() {
        hero = getSprite("assets\\test_hero.png");
    }

    public void drowing(Graphics g) {
        hero.draw(g, x, y);
    }

    public void moving(KeyInputHandler keyControl) {
        float h = HEIGHT - hero.getHeight();
        float w = WIDTH - hero.getWidth();

        if (keyControl.isLeftPressed()) {
            if (x > 0 && x <= h) {
                x--;
            }
        }
        if (keyControl.isRightPressed()) {
            if (x >= 0 && x < h) {
                x++;
            }
        }
        if (keyControl.isUpPressed()) {
            if (y > 0 && y <= w) {
                y--;
            }
        }
        if (keyControl.isDownPressed()) {
            if (y >= 0 && y < w) {
                y++;
            }
        }

        System.out.println(x);
        System.out.println(y);
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
