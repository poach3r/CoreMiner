package Library.Graphics;

import Library.Entities.GenericEntity;
import Library.Map.Map;
import Library.Map.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Renderer extends JPanel {
    private final int gridSize;
    protected Map map;
    protected JFrame frame;
    private boolean shouldUpdate;
    private ArrayList<GenericEntity> entities;
    private final BufferedImage img;

    public Renderer(JFrame frame, int gridSize) {
        this.frame = frame;
        shouldUpdate = false;
        entities = new ArrayList<>();
        img = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        this.gridSize = gridSize;
    }

    public Library.Map.Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = new Map(map);
    }

    public void setTile(Tile tile, int row, int column) {
        map.setTile(tile, row, column);
    }

    public void add(Library.Entities.GenericEntity entity) {
        entities.add(entity);
    }

    public void load() {
        paint(getGraphics());
    }

    public void promptUpdate(Map mapToLoad) {
        map = mapToLoad;
        shouldUpdate = true;
    }

    public void promptUpdate() {
        shouldUpdate = true;
    }

    /**
     * Removes all entities but the one specified.
     *
     * @param avoidedEntity The entity to not be removed.
     */
    public void removeAllBut(GenericEntity avoidedEntity) {
        ArrayList<GenericEntity> tempEntities = new ArrayList<>(entities);
        for (GenericEntity entity : entities) {
            if (!entity.equals(avoidedEntity)) {
                tempEntities.remove(entity);
            }
        }
        entities = tempEntities;
    }

    public void remove(GenericEntity entity) {
        entities.remove(entity);
    }

    /**
     * Returns the entity at a specified tile.
     *
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @return A nullable optional of the entity at the tile if it exists.
     */
    public Optional<GenericEntity> getEntityAtTile(int x, int y) {
        for (GenericEntity entity : entities) {
            if (entity.getX() == x && entity.getY() == y) {
                return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    /**
     * Returns the entity at the specified tile as long as it isn't the specified entity.
     *
     * @param x             The x coordinate of the tile.
     * @param y             The y coordinate of the tile.
     * @param avoidedEntity The entity to avoid
     * @return A nullable optional of the entity at the tile if it exists and is not the avoided entity.
     */
    public Optional<GenericEntity> getEntityAtTileButAvoidEntity(int x, int y, GenericEntity avoidedEntity) {
        for (GenericEntity entity : entities) {
            if (entity.getX() == x && entity.getY() == y && !entity.equals(avoidedEntity)) {
                return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    /**
     * Checks if one entity is colliding with another and returns the damage dealt
     *
     * @param collider The entity to check against
     * @return 0 if the entity doesn't exist or does no damage, otherwise the damage the entity deals
     */
    public int entityIsColliding(GenericEntity collider) {
        List<GenericEntity> possibleEntities = entities.stream()
                .filter(e -> e.getHitbox().intersects(collider.getHitbox()) && !e.equals(collider)).toList();

        if (!possibleEntities.isEmpty())
            return possibleEntities.get(0).getDmg();

        return 0;
    }

    /**
     * Removes all entities.
     */
    public void removeAllEntities() {
        entities = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        Tile tile = null;
        int x = 0;
        int y = 0;
        Graphics imgGraphics = img.getGraphics();

        if (shouldUpdate) {
            shouldUpdate = false;
            // TODO this could be optimized by only re-drawing tiles which have changed however that leads to a lot of issues
            for (int i = 0; i < gridSize; i++) {
                for (int n = 0; n < gridSize; n++) {
                    tile = map.getTiles()[i][n];
                    x = n * tile.getTexture().getImage().getWidth();
                    y = i * tile.getTexture().getImage().getHeight();
                    imgGraphics.drawImage(tile.getTexture().getImage(), x, y, this);
                }
            }

            for (GenericEntity entity : entities) {
                x = entity.getX();
                y = entity.getY();
                imgGraphics.drawImage(entity.getTexture().getImage(), x, y, this);
            }
        }

        g.drawImage(img, ((frame.getWidth() - 1024) / 2), ((frame.getHeight() - 1024) / 2), this);
        imgGraphics.dispose();
        g.dispose();
    }
}
