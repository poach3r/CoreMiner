package Library.Map;


import Library.Entities.GenericEntity;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** todo
 * @author poacher
 */
public class Map implements Serializable {
    private final Tile[][] tiles;
    private final Rectangle[][] tileHitboxes;
    private final ArrayList<GenericEntity> entities;

    public Map() {
        tiles = new Tile[16][16];
        tileHitboxes = new Rectangle[16][16];
        entities = new ArrayList<>();
    }

    public Map(Map map) {
        tiles = new Tile[map.getTiles().length][map.getTiles()[0].length];
        tileHitboxes = new Rectangle[map.getTiles().length][map.getTiles()[0].length];
        for (int i = 0; i < map.getTiles().length; i++) {
            for (int n = 0; n < map.getTiles()[i].length; n++) {
                tiles[i][n] = new Tile(map.getTiles()[i][n]);
                tileHitboxes[i][n] = new Rectangle(i * 64, n * 64, tiles[i][n].getTexture().getImage().getWidth(), tiles[i][n].getTexture().getImage().getWidth());
            }
        }
        entities = new ArrayList<>();
    }

    public void addEntity(GenericEntity entity) {
        entities.add(entity);
    }

    public ArrayList<GenericEntity> getEntities() {
        return entities;
    }

    public void removeEntity(GenericEntity entity) {
        entities.remove(entity);
    }

    public void removeEntity(int index) {
        entities.remove(index);
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void setTile(Tile tile, int row, int column) {
        this.tiles[row][column] = tile;
        this.tileHitboxes[row][column] = new Rectangle(column * 64, row * 64, 64, 64);
    }

    /**
     * Checks all tiles an entity is colliding with for collision.
     * @param entity The entity to check with
     * @return Whether the entity is colliding with any tiles or not.
     */
    public boolean entityCollidesWithTerrain(GenericEntity entity) {
        return getTilesAtEntity(entity).stream().anyMatch(Tile::hasCollision);
    }

    /**
     * Gets all tiles the entity is colliding with by checking in a 3x3 radius of the entity
     * @param entity The entity to check with
     * @return The tiles the entity is at.
     */
    public List<Tile> getTilesAtEntity(GenericEntity entity) {
        ArrayList<Tile> collidingTiles = new ArrayList<>();
        int x = (entity.getX() / 64) - 1;
        int y = (entity.getY() / 64) - 1;

        for(int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++) {
                if(x + b < 0 || y + a < 0 || x + b > 15 || y + a > 15)
                    continue;

                if(entity.getHitbox().intersects(tileHitboxes[y + a][x + b]))
                    collidingTiles.add(tiles[y + a][x + b]);
            }
        }

        return collidingTiles;
    }

    /**
     * Checks if any entities are colliding with another and returns an optional list of them
     *
     * @param collider The entity to check against
     * @return 0 if the entity doesn't exist or does no damage, otherwise the damage the entity deals
     */
    public Optional<List<GenericEntity>> getEntitiesCollidingWithOtherEntity(GenericEntity collider) {
        List<GenericEntity> possibleEntities = entities
                .stream()
                .filter(e -> e.getHitbox().intersects(collider.getHitbox()) && !e.equals(collider))
                .toList();

        if (!possibleEntities.isEmpty())
            return Optional.of(possibleEntities);

        return Optional.empty();
    }

    /**
     * Checks if any entities are colliding with a hitbox and returns an optional list of them
     *
     * @param collider The entity to check against
     * @return 0 if the entity doesn't exist or does no damage, otherwise the damage the entity deals
     */
    public Optional<List<GenericEntity>> getEntitiesCollidingWithHitbox(Rectangle collider) {
        List<GenericEntity> possibleEntities = entities
                .stream()
                .filter(e -> e.getHitbox().intersects(collider))
                .toList();

        if (!possibleEntities.isEmpty())
            return Optional.of(possibleEntities);

        return Optional.empty();
    }

    /**
     * Checks if any entities are at a given tile.
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @return An optional list of all the entities at the specified tile.
     */
    public Optional<List<GenericEntity>> getEntitiesAtTile(int x, int y) {
        return Optional.of(entities.stream().filter(e -> e.getHitbox().intersects(tileHitboxes[y][x])).toList());
    }
}
