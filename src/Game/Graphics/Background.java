package Game.Graphics;

import Game.Tiles.TileIndex;
import Library.Map.Map;
import Library.Map.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * This class extends Library.Graphics.Background and handles all the rendering for our map as well as some components of mining
 * @author poacher
 */
public class Background extends Library.Graphics.Background {
    private boolean shouldUpdate;

    public Background(JFrame frame) {
        super(frame);
        shouldUpdate = false;
    }

    public void reload(Map loadingMap) {
        if(!shouldUpdate)
            return;

        for (int i = 0; i < map.getTiles().length; i++) {
            for (int n = 0; n < map.getTiles()[i].length; n++) {
                Tile tile = map.getTiles()[i][n];
                Tile loadingTile = loadingMap.getTiles()[i][n];
                if (!tile.getName().equals(loadingMap.getTiles()[i][n].getName())) {
                    try {
                        map.setTile(loadingMap.getTiles()[i][n], i, n);
                        remove(((i * map.getTiles()[i].length) + n));
                        JLabel newTile = new JLabel(new ImageIcon(ImageIO.read(loadingTile.getAsset())));
                        this.add(newTile, ((i * map.getTiles()[i].length) + n));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public void load() {
        removeAll();
        for (int i = 0; i < getMap().getTiles().length; i++) {
            for (int n = 0; n < map.getTiles()[i].length; n++) {
                Tile tile = map.getTiles()[i][n];
                try {
                    JLabel newTile = new JLabel(new ImageIcon(ImageIO.read(tile.getAsset())));
                    this.add(newTile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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
