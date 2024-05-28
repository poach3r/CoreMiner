package Library.Entities;

import Library.Graphics.Renderer;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Default controller for passive entities
 *
 * @author poacher
 */
public class PassiveEntityController extends GenericEntityController {
    private final Renderer renderer;
    private boolean entityRemoved;
    private Consumer<GenericEntity> onDeath;
    private final Random rand;

    // if we dont store the removed entities in a separate list and simply remove them from the main entities list we get a comodification error
    private ArrayList<GenericEntity> entitiesToKeep;

    public PassiveEntityController(Renderer renderer) {
        super();
        this.renderer = renderer;
        entityRemoved = false;
        entitiesToKeep = null;
        onDeath = null;
        rand = new Random();
    }

    public void setOnDeathAction(Consumer<GenericEntity> onDeathAction) {
        onDeath = onDeathAction;
    }

    @Override
    public void move() {
        for (GenericEntity entity : this.getEntities()) {
            if (rand.nextBoolean() && rand.nextBoolean()) {
                int r = rand.nextInt(4);
                if (r == 0) {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getX() >= 1024 - entity.getTexture().getImage().getWidth())
                            break;

                        entity.setX(entity.getX() + 1);
                        if (getMap().entityCollidesWithTerrain(entity)) {
                            entity.setX(entity.getX() - 1);
                            break;
                        }
                    }
                } else if (r == 1) {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getX() - 1 <= 0)
                            break;

                        entity.setX(entity.getX() - 1);
                        if (getMap().entityCollidesWithTerrain(entity)) {
                            entity.setX(entity.getX() + 1);
                            break;
                        }
                    }
                } else if (r == 2) {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getY() + 1 >= 1024 - entity.getTexture().getImage().getWidth() - 1)
                            break;

                        entity.setY(entity.getY() + 1);
                        if (getMap().entityCollidesWithTerrain(entity)) {
                            entity.setY(entity.getY() - 1);
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < entity.getSpeed(); i++) {
                        if (entity.getY() - 1 <= 0)
                            break;

                        entity.setY(entity.getY() - 1);
                        if (getMap().entityCollidesWithTerrain(entity)) {
                            entity.setY(entity.getY() + 1);
                            break;
                        }
                    }
                }
            }

            renderer.promptFgUpdate();
        }
    }

    @Override
    public void miscLogic() {
        entitiesToKeep = new ArrayList<>(entities);
        for (GenericEntity entity : entities) {
            if (entity.getHp() <= 0) {
                if (onDeath != null) {
                    entityRemoved = true;
                    entitiesToKeep.remove(entity);
                    onDeath.accept(entity);
                }
            }

            if (entity.getTimeSinceHit() < entity.getITime())
                entity.setTimeSinceHit(entity.getTimeSinceHit() + 1);
        }

        if (entityRemoved) {
            entities = new ArrayList<>(entitiesToKeep);
        }
    }
}
