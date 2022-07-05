package gameConfig;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class BasicGame extends BasicGameState {
    private int id = 1100;
    private Rectangle square;
    private Rectangle barrier;
    private SpriteSheet hero;
    private Boolean detectBarrier = false;
    private float x = 140;
    private float y = 300;
    private float speed = 0.1f;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
//        BufferedImage sourceImage = null;
//        File file = new File("assets\\test_hero.png");
//        try {
//            sourceImage = ImageIO.read(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        hero = new Sprite(Toolkit.getDefaultToolkit().createImage(Objects.requireNonNull(sourceImage).getSource()));

        //square = new Rectangle(GameContainer.getWidth()/2 - 20, GameContainer.getHeight()/2 - 20, 40, 40);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Howdy! " + "X:" + x + "  Y:" + y + "\n" + detectBarrier + "V:" + speed, 300, 10);
        graphics.setColor(Color.green);
        square = new Rectangle(x, y, 50, 50);
        barrier = new Rectangle(200, 300, 300, 100);
        graphics.fill(square);
        graphics.draw(barrier);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
            x -= speed * i;
        //square.setX(square.getX() - );
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
            x += speed * i;
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
            y -= speed * i;
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
            y += speed * i;

        if (square.intersects(barrier))
            detectBarrier = true;
        else
            detectBarrier = false;
        if (input.isKeyDown(Input.KEY_LSHIFT)) {
            speed = 0.8f;
        } else
            speed = 0.1f;


        if (square.intersects(barrier)) {
            /*X*/
            // left border
            if (x <= barrier.getX() - 2)
                x = barrier.getX() - square.getWidth() - 3;
            // right border
            if (x > barrier.getX() - 2 && x <= barrier.getX())
                x = barrier.getX() + 3;
            // left border
            if (x > barrier.getMaxX() - square.getWidth() && x <= barrier.getMaxX() - 2)
                x = barrier.getMaxX() - square.getWidth() - 3;
            // right border
            if (x > barrier.getMaxX() - 2 && x <= barrier.getMaxX())
                x = barrier.getMaxX() + 3;

            /*Y*/
            // left border
            if (y <= barrier.getY() - 2)
                y = barrier.getY() - square.getHeight() - 3;
            // right border
            if (y > barrier.getY() - 2 && y <= barrier.getY())
                y = barrier.getY() + 3;
            // left border
            if (y > barrier.getMaxY() - square.getHeight() && y <= barrier.getMaxY() - 2)
                y = barrier.getMaxY() - square.getHeight() - 3;
            // right border
            if (y > barrier.getMaxY() - 2 && y <= barrier.getMaxY())
                y = barrier.getMaxY() + 3;

        }
    }

}
