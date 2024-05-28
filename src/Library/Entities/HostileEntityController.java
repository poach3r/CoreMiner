package Library.Entities;

import Library.Graphics.Renderer;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Default controller for hostile entities
 *
 * @author poacher
 */
public class HostileEntityController extends GenericEntityController {
    private GenericEntity target;
    private final Renderer renderer;
    private boolean entityRemoved;
    private Consumer<GenericEntity> onDeath;

    // if we dont store the removed entities in a separate list and simply remove them from the main entities list we get a comodification error
    private ArrayList<GenericEntity> entitiesToKeep;

    public HostileEntityController(GenericEntity target, Renderer renderer) {
        super();
        this.target = target;
        this.renderer = renderer;
        entityRemoved = false;
        entitiesToKeep = null;
        onDeath = null;
    }

    public void setTarget(GenericEntity target) {
        this.target = target;
    }

    public void setOnDeathAction(Consumer<GenericEntity> onDeathAction) {
        onDeath = onDeathAction;
    }

    @Override
    public void move() {
        for (GenericEntity entity : this.getEntities()) {
            for (int i = 0; i < entity.getSpeed(); i++) {
                if (target.getX() > entity.getX()) {
                    if (entity.getX() >= 1024 - entity.getTexture().getImage().getWidth())
                        break;

                    entity.setX(entity.getX() + 1);
                    if (getMap().entityCollidesWithTerrain(entity)) {
                        entity.setX(entity.getX() - 1);
                        break;
                    }
                }

                if (target.getX() < entity.getX()) {
                    if (entity.getX() - 1 <= 0)
                        break;

                    entity.setX(entity.getX() - 1);
                    if (getMap().entityCollidesWithTerrain(entity)) {
                        entity.setX(entity.getX() + 1);
                        break;
                    }
                }

                if (target.getY() > entity.getY()) {
                    if (entity.getY() + 1 >= 1024 - entity.getTexture().getImage().getWidth() - 1)
                        break;

                    entity.setY(entity.getY() + 1);
                    if (getMap().entityCollidesWithTerrain(entity)) {
                        entity.setY(entity.getY() - 1);
                        break;
                    }
                }

                if (target.getY() < entity.getY()) {
                    if (entity.getY() - 1 <= 0)
                        break;

                    entity.setY(entity.getY() - 1);
                    if (getMap().entityCollidesWithTerrain(entity)) {
                        entity.setY(entity.getY() + 1);
                        break;
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

            if(entity.getTimeSinceHit() < entity.getITime())
                entity.setTimeSinceHit(entity.getTimeSinceHit() + 1);
        }

        if (entityRemoved) {
            entities = new ArrayList<>(entitiesToKeep);
        }
    }
}
