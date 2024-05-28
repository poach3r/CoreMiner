package Game.Entities.Projectile;

import Library.Entities.GenericEntity;
import Library.Entities.GenericEntityController;
import Library.Graphics.Renderer;
import Library.Map.Tile;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ProjectileController extends GenericEntityController {
    private Renderer renderer;
    private boolean entityRemoved;
    private Consumer<GenericEntity> onDeath;
    private ArrayList<GenericEntity> entitiesToRemove;
    private ArrayList<Projectile> projectiles;

    public ProjectileController(Renderer renderer) {
        super();
        this.renderer = renderer;
        entityRemoved = false;
        entitiesToRemove = null;
        onDeath = null;
        projectiles = new ArrayList<>();
    }

    public void setOnDeathAction(Consumer<GenericEntity> onDeathAction) {
        onDeath = onDeathAction;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    @Override
    public void move() {
//        for (Projectile entity : projectiles) {
//            if (entity.getEndX() > entity.getX()) {
//                for (int i = 0; i < entity.getSpeed(); i++) {
//                    if (entity.getX() + 1 >= 1024 - entity.getTexture().getImage().getWidth())
//                        break;
//
//                    if (getMap().getTileAtPos(entity.getX() + 1, entity.getY()).stream().anyMatch(Tile::hasCollision))
//                        break;
//
//                    entity.setX(entity.getX() + 1);
//                    renderer.promptUpdate();
//                }
//            }
//
//            if (entity.getEndX() < entity.getX()) {
//                for (int i = 0; i < entity.getSpeed(); i++) {
//                    if (entity.getX() - 1 <= 0)
//                        break;
//
//                    if (getMap().getTileAtPos(entity.getX() - 1, entity.getY()).stream().anyMatch(Tile::hasCollision))
//                        break;
//
//                    entity.setX(entity.getX() - 1);
//                    renderer.promptUpdate();
//                }
//            }
//
//            if (entity.getEndY() > entity.getY()) {
//                for (int i = 0; i < entity.getSpeed(); i++) {
//                    if (entity.getY() + 1 >= 1024 - entity.getTexture().getImage().getWidth())
//                        break;
//
//                    if (getMap().getTileAtPos(entity.getX(), entity.getY() + 1).stream().anyMatch(Tile::hasCollision))
//                        break;
//
//                    entity.setY(entity.getY() + 1);
//                    renderer.promptUpdate();
//                }
//            }
//
//            if (entity.getEndY() < entity.getY()) {
//                for (int i = 0; i < entity.getSpeed(); i++) {
//                    if (entity.getY() - 1 <= 0)
//                        break;
//
//                    if (getMap().getTileAtPos(entity.getX(), entity.getY() - 1).stream().anyMatch(Tile::hasCollision))
//                        break;
//
//                    entity.setY(entity.getY() - 1);
//                    renderer.promptUpdate();
//                }
//            }
//        }
    }

    @Override
    public void miscLogic() {

    }
}
