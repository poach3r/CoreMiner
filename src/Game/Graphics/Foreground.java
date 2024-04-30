package Game.Graphics;

import Library.Entities.GenericEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Extends JPanel and is used for rendering entities.
 *
 * @author poacher
 */

public class Foreground extends JPanel {
    private ArrayList<Library.Entities.GenericEntity> entities;
    private final JFrame frame;
    private boolean shouldUpdate;

    public Foreground(JFrame frame) {
        super();
        this.frame = frame;
        entities = new ArrayList<>();

        this.setOpaque(false);
        this.setLayout(new GridLayout(16, 16, 0, 0));
        for (int i = 0; i < 16; i++) { // row
            for (int n = 0; n < 16; n++) { // column
                this.add(new JLabel(""));
            }
        }

        this.setVisible(true);
    }

    public void add(Library.Entities.GenericEntity entity) {
        entities.add(entity);
    }

    /**
     * Loads an entity whenever a change in position is detected
     * @author poacher
     */
    public void load() {
        if(!shouldUpdate)
            return;

        shouldUpdate = false;
        try {
            for (int i = 0; i < 15; i++) { // HACK this needs to be 15, if it isnt then pos will be out of the bounds of the component array
                for (int n = 0; n < 16; n++) {
                    int pos = n * 16 + i;
                    Optional<GenericEntity> possibleEntity = getEntityAtTile(i, n);

                    if (this.getComponent(pos).getName() != null) {
                        // if entity doesnt actually exist
                        if (possibleEntity.isEmpty()) {
                            remove(pos);
                            add(new JLabel(""), pos);
                            continue;
                        }
                    }

                    // if entity should exist
                    if (possibleEntity.isPresent()) {
                        this.remove(pos);

                        JLabel entityLabel = new JLabel(new ImageIcon(ImageIO.read(possibleEntity.get().getAsset())));
                        entityLabel.setName("entity");
                        this.add(entityLabel, pos);

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.revalidate();
        this.repaint();
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
     * Removes all entities.
     */
    public void removeAllEntities() {
        entities = new ArrayList<>();
    }

    public void promptUpdate() {
        shouldUpdate = true;
    }


    // Both of these are used to force the panel into a square.
    @Override
    public void setSize(int width, int height) {
        int size = Math.min(width, height);
        super.setSize(size, size);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        int minWidth = (int) getMinimumSize().getWidth();
        int minHeight = (int) getMinimumSize().getHeight();
        int size = Math.min(minWidth, minHeight);
        super.setBounds((frame.getWidth() / 2) - (size / 2), (frame.getHeight() / 2) - (size / 2), size, size);
    }
}
