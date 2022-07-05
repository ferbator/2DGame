import gameConfig.BasicGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    public Main(String name) {
        super(name);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new Main("Game Title"));
        agc.setDisplayMode(600, 600, false);
        agc.setAlwaysRender(true);
        agc.start();
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new BasicGame());
    }
}
