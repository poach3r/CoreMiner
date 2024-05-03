package Library.Entities;

import Library.Graphics.Renderer;
import Library.Map.Tile;

import java.util.ArrayList;

/** Default controller for hostile entities
 * @author poacher
 */
public class HostileEntityController extends GenericEntityController {
    private GenericEntity target;
    private Renderer fg;
    private boolean entityRemoved;

    // if we dont store the removed entities in a separate list and simply remove them from the main entities list we get a comodification error
    private ArrayList<GenericEntity> entitiesToRemove;

    public HostileEntityController(GenericEntity target, Renderer fg) {
        super();
        this.target = target;
        this.fg = fg;
        entityRemoved = false;
        entitiesToRemove = null;
    }

    public void setTarget(GenericEntity target) {
        this.target = target;
    }

    @Override
    public void move() {
        for (GenericEntity entity : this.getEntities()) {
            if (target.getX() > entity.getX()) {
                for (int i = 0; i < entity.getSpeed(); i++) {
                    if (entity.getX() + 1 >= 1024 - entity.getTexture().getImage().getWidth())
                        break;

                    if (getMap().getTileAtPos(entity.getX() + 1, entity.getY()).stream().anyMatch(Tile::hasCollision))
                        break;

                    entity.setX(entity.getX() + 1);
                    fg.promptUpdate();
                }
            }

            if (target.getX() < entity.getX()) {
                for (int i = 0; i < entity.getSpeed(); i++) {
                    if (entity.getX() - 1 <= 0)
                        break;

                    if (getMap().getTileAtPos(entity.getX() - 1, entity.getY()).stream().anyMatch(Tile::hasCollision))
                        break;

                    entity.setX(entity.getX() - 1);
                    fg.promptUpdate();
                }
            }

            if (target.getY() > entity.getY()) {
                for (int i = 0; i < entity.getSpeed(); i++) {
                    if (entity.getY() + 1 >= 1024 - entity.getTexture().getImage().getWidth())
                        break;

                    if (getMap().getTileAtPos(entity.getX(), entity.getY() + 1).stream().anyMatch(Tile::hasCollision))
                        break;

                    entity.setY(entity.getY() + 1);
                    fg.promptUpdate();
                }
            }

            if (target.getY() < entity.getY()) {
                for (int i = 0; i < entity.getSpeed(); i++) {
                    if (entity.getY() - 1 <= 0)
                        break;

                    if (getMap().getTileAtPos(entity.getX(), entity.getY() - 1).stream().anyMatch(Tile::hasCollision))
                        break;

                    entity.setY(entity.getY() - 1);
                    fg.promptUpdate();
                }
            }
        }
    }

    @Override
    public void miscLogic() {
        entitiesToRemove = new ArrayList<>(entities);
        for(GenericEntity entity : entities) {
            if(getMap().getTileAtPos(entity.getX(), entity.getY()).stream().anyMatch(e -> e.getId() == 0)) {
                entityRemoved = true;
                entitiesToRemove.remove(entity);
                fg.remove(entity);
                fg.promptUpdate();
            }
        }

        if(entityRemoved) {
            entities = new ArrayList<>(entitiesToRemove);
        }
    }
}
