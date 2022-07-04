package gameConfig;

import keyboardDependens.KeyInputHandler;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.Objects;


public class Game extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Boolean running;

    public static Integer WIDTH = 500;
    public static Integer HEIGHT = 500;
    public static String NAME = "Test";

    public static Sprite hero;
    private KeyInputHandler keyInputHandler;

    private static Integer x = 0;
    private static Integer y = 0;

    public void start() {
        running = true;
        keyInputHandler = new KeyInputHandler();
        new Thread(this).start();
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();
        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            render();
            update(delta);
        }
    }

    public void init() {
        addKeyListener(keyInputHandler);
        hero = getSprite("assets\\test_hero.png");
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        hero.draw(g, x, y);
        g.dispose();
        bs.show();
    }


    public void update(long delta) {
        if (keyInputHandler.isLeftPressed()) {
            x--;
        }
        if (keyInputHandler.isRightPressed()) {
            x++;
        }
        if (keyInputHandler.isUpPressed()) {
            y--;
        }
        if (keyInputHandler.isDownPressed()) {
            y++;
        }

        System.out.println(x);
        System.out.println(y);
    }

    public Sprite getSprite(String path) {
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