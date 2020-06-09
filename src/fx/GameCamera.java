package fx;

import tankgame.GameWorld;
import tankgame.gameobjects.collidable.breakable.Tank;

public class GameCamera {

    private int xOffset, yOffset;
    private GameWorld game;

    public GameCamera(GameWorld game, int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.game = game;
    }

    public void followTank(Tank tank){
        xOffset = tank.getX() - game.getWidth() / 2;
        yOffset = tank.getY() - game.getWidth() / 2;
    }

    public void move(int x, int y){
        xOffset += x;
        yOffset += y;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
}
