package Library.Entities;

import Game.Graphics.Foreground;

import java.util.ArrayList;
import java.util.Random;

/** Default controller for passive entities
 * @author poacher
 */
public class PassiveEntityController extends GenericEntityController {
    private Random movementRandomizer;
    private Foreground fg;
    private boolean entityRemoved;

    public PassiveEntityController(Foreground fg) {
        super();
        movementRandomizer = new Random();
        this.fg = fg;
        entityRemoved = false;
    }

    @Override
    public void move() {
        ArrayList<GenericEntity> temp = new ArrayList<>(entities);
        for (GenericEntity entity : this.getEntities()) {
            if(entity.getTimeSinceLastMove() == entity.getSpeed()) {
                entity.setTimeSinceLastMove(0);
                if(getMap().getTiles()[entity.getY()][entity.getX()].getName().equals("void") || entity.getHp() < 1) {
                    temp.remove(entity);
                    fg.remove(entity);
                    //fg.load();
                    entityRemoved = true;
                    continue;
                }

                switch(movementRandomizer.nextInt(4)) {
                    case 0 -> {
                        if(entity.getX() < 15)
                            if(!getMap().getTiles()[entity.getY()][entity.getX() + 1].hasCollision()) {
                                entity.setX(entity.getX() + 1);
                                fg.promptUpdate();
                            }
                    }
                    case 1 -> {
                        if(entity.getX() > 0)
                            if(!getMap().getTiles()[entity.getY()][entity.getX() - 1].hasCollision()) {
                                entity.setX(entity.getX() - 1);
                                fg.promptUpdate();
                            }
                    }
                    case 2 -> {
                        if(entity.getY() < 15)
                            if(!getMap().getTiles()[entity.getY() + 1][entity.getX()].hasCollision()) {
                                entity.setY(entity.getY() + 1);
                                fg.promptUpdate();
                            }
                    }
                    case 3 -> {
                        if(entity.getY() > 0)
                            if(!getMap().getTiles()[entity.getY() - 1][entity.getX()].hasCollision()) {
                                entity.setY(entity.getY() - 1);
                                fg.promptUpdate();
                            }
                    }
                }
            }

            else
                entity.setTimeSinceLastMove(entity.getTimeSinceLastMove() + 1);
        }

        if(entityRemoved)
            entities = temp;
    }

    @Override
    public void miscLogic() {}
}
