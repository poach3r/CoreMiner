package Library.Entities;

import Library.Graphics.Renderer;
import Library.Map.Tile;

import java.util.ArrayList;
import java.util.Random;

/**
 * Default controller for passive entities
 *
 * @author poacher
 */
public class PassiveEntityController extends GenericEntityController {
    private final Random movementRandomizer;
    private final Renderer renderer;
    private boolean entityRemoved;
    private ArrayList<GenericEntity> entitiesToRemove;

    public PassiveEntityController(Renderer fg) {
        super();
        movementRandomizer = new Random();
        this.renderer = fg;
        entityRemoved = false;
        entitiesToRemove = null;
    }

    @Override
    public void move() {
        for (GenericEntity entity : this.getEntities()) {
            switch (movementRandomizer.nextInt(4)) {
                case 0 -> {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getX() >= 1024 - entity.getTexture().getImage().getWidth() - 1)
                            break;

                        if (getMap().getTileAtPos(entity.getX() + 1, entity.getY()).stream().anyMatch(Tile::hasCollision))
                            break;

                        entity.setX(entity.getX() + 1);
                        renderer.promptUpdate();
                    }
                }
                case 1 -> {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getX() <= 0)
                            break;

                        if (getMap().getTileAtPos(entity.getX() - 1, entity.getY()).stream().anyMatch(Tile::hasCollision))
                            break;

                        entity.setX(entity.getX() - 1);
                        renderer.promptUpdate();
                    }
                }
                case 2 -> {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getY() >= 1024 - entity.getTexture().getImage().getWidth() - 1)
                            break;

                        if (getMap().getTileAtPos(entity.getX() , entity.getY() + 1).stream().anyMatch(Tile::hasCollision))
                            break;

                        entity.setY(entity.getY() + 1);
                        renderer.promptUpdate();
                    }
                }
                case 3 -> {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getY() <= 0)
                            break;

                        if (getMap().getTileAtPos(entity.getX(), entity.getY() - 1).stream().anyMatch(Tile::hasCollision))
                            break;

                        entity.setY(entity.getY() - 1);
                        renderer.promptUpdate();
                    }
                }
            }
        }

    }

    @Override
    public void miscLogic() {
        entitiesToRemove = new ArrayList<>(entities);
        for (GenericEntity entity : entities) {
            if (getMap().getTiles()[entity.getY()][entity.getX()].getId() == 0 || entity.getHp() < 1) {
                entitiesToRemove.remove(entity);
                entityRemoved = true;
                renderer.remove(entity);
                renderer.promptUpdate();
            }
        }

        if (entityRemoved)
            entities = entitiesToRemove;
    }
}
