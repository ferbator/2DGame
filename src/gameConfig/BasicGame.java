package gameConfig;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BasicGame extends StateBasedGame {
    public static final String gameName = "Test";
    public static final int playId = 1;
    public static final int menuId = 0;

    public BasicGame(String gameName) {
        super(gameName);
        this.addState(new GameMenu(menuId));
        this.addState(new GamePlay(playId));
        this.enterState(menuId);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new BasicGame(gameName));
        //agc.setDisplayMode(960, 960, false);
        agc.setDisplayMode(500, 500, false);
        agc.setAlwaysRender(true);
        agc.setUpdateOnlyWhenVisible(true);
        agc.start();
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

    }
}
