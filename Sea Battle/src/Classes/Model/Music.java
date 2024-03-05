import java.io.Serializable;

public class Music implements Serializable {
    private boolean game;

    public boolean getGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    Music() {
    }

    Music(boolean game) {
        this.game = game;
    }

}
