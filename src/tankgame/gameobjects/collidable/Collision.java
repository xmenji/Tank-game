package tankgame.gameobjects.collidable;

import tankgame.gameobjects.GameObject;

public interface Collision {
    void checkCollision(GameObject obj);
    void takeHit();
}
