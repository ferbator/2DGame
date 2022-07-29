package gameConfig;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMenu extends BasicGameState {

    private int stateId;
    private Rectangle exit;
    private Rectangle play;
    private Rectangle cursor;

    public GameMenu(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        exit = new Rectangle(20, 80, 100, 50);
        play = new Rectangle(20, 150, 100, 50);
        cursor = new Rectangle(20, 80, 102, 52);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(Color.black);
        graphics.setColor(Color.white);
        graphics.drawString("Menu", 20, 50);
        graphics.fill(exit);
        graphics.fill(play);
        graphics.setColor(Color.black);
        graphics.drawString("Exit", 20, 80);
        graphics.drawString("Play", 20, 150);
        graphics.setColor(Color.orange);
        graphics.draw(cursor);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            cursor.setY(80);
        }
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            cursor.setY(150);
        }
        if (cursor.getY() == 150 && input.isKeyDown(Input.KEY_ENTER)) {
            stateBasedGame.enterState(1);
        }
        if (cursor.getY() == 80 && input.isKeyDown(Input.KEY_ENTER)) {
            System.exit(0);
        }
    }
}
