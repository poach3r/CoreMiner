package Library.Entities;

import Game.Graphics.Foreground;

import java.util.ArrayList;

/** Default controller for hostile entities
 * @author poacher
 */
public class HostileEntityController extends GenericEntityController {
    private GenericEntity target;
    private Foreground fg;
    private boolean entityRemoved;

    // if we dont store the removed entities in a separate list and simply remove them from the main entities list we get a comodification error
    private ArrayList<GenericEntity> entitiesToRemove;

    public HostileEntityController(GenericEntity target, Foreground fg) {
        super();
        this.target = target;
        this.fg = fg;
        entityRemoved = false;
        entitiesToRemove = new ArrayList<>();
    }

    public void setTarget(GenericEntity target) {
        this.target = target;
    }

    @Override
    public void move() {
        for (GenericEntity entity : this.getEntities()) {
            if (entity.getTimeSinceLastMove() == entity.getSpeed()) {
                entity.setTimeSinceLastMove(0);
                if (target.getX() > entity.getX()) {
                    if (!getMap().getTiles()[entity.getY()][entity.getX() + 1].hasCollision()) {
                        entity.setX(entity.getX() + 1);
                        fg.promptUpdate();
                    }
                }

                else if (target.getX() < entity.getX()) {
                    if (!getMap().getTiles()[entity.getY()][entity.getX() - 1].hasCollision()) {
                        entity.setX(entity.getX() - 1);
                        fg.promptUpdate();
                    }
                }

                else if (target.getY() > entity.getY()) {
                    if (!getMap().getTiles()[entity.getY() + 1][entity.getX()].hasCollision()) {
                        entity.setY(entity.getY() + 1);
                        fg.promptUpdate();
                    }
                }

                else if (target.getY() < entity.getY()) {
                    if (!getMap().getTiles()[entity.getY() - 1][entity.getX()].hasCollision()) {
                        entity.setY(entity.getY() - 1);
                        fg.promptUpdate();
                    }
                }
            }

            else
                entity.setTimeSinceLastMove(entity.getTimeSinceLastMove() + 1);
        }
    }

    @Override
    public void miscLogic() {
        entitiesToRemove = new ArrayList<>(entities);
        for(GenericEntity entity : entities) {
            if (getMap().getTiles()[entity.getY()][entity.getX()].getName().equals("void") || entity.getHp() < 1) {
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
