package gameConfig;

import entities.Map;
import entities.Player;
import keyboardDependens.KeyInputHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;


public class Game extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Boolean running;

    public static Integer WIDTH = 600;
    public static Integer HEIGHT = 600;
    public static String NAME = "Test";
    private Player player;
    private Map map;

    private KeyInputHandler keyInputHandler;

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
        player = new Player();
        map = new Map();
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
        map.drowing(g);
        player.drowing(g);
        g.dispose();
        bs.show();
    }


    public void update(long delta) {
        player.moving(keyInputHandler);
        map.moving(keyInputHandler);
    }
}