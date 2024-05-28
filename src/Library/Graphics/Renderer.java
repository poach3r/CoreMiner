package Library.Graphics;

import Library.Entities.GenericEntity;
import Library.Map.Map;
import Library.Map.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Renders a map.
 * @author poacher
 */
public class Renderer extends JPanel {
    protected Map map;
    protected JFrame frame;
    private final int gridSize;

    private boolean shouldUpdateFg;
    private boolean shouldUpdateBg;
    private boolean shouldUpdateCustom;

    private final BufferedImage bg;
    private final BufferedImage fg;
    private final BufferedImage custom;
    private final BufferedImage finalImg;

    private final HashMap<int[], Tile> litTiles;

    private final ArrayList<BiConsumer<Graphics2D, JPanel>> customRendering; // used to render permanent elements such as the hud

    private BiConsumer<Graphics2D, JPanel> oneTimeRender; // used to render one time things such as a particle effect

    public Renderer(JFrame frame, int gridSize) {
        this.frame = frame;
        this.gridSize = gridSize;

        shouldUpdateBg = false;
        shouldUpdateFg = false;
        shouldUpdateCustom = false;

        fg = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        bg = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        custom = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        finalImg = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);

        litTiles = new HashMap<>();

        customRendering = new ArrayList<>();
        oneTimeRender = null;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = new Map(map);
    }

    public void addCustomRendering(BiConsumer<Graphics2D, JPanel> customRendering) {
        this.customRendering.add(customRendering);
    }

    /**
     * Reloads components when they need to be updated.
     */
    public void load() {
        if (shouldUpdateFg || shouldUpdateBg || shouldUpdateCustom || oneTimeRender != null)
            paint(getGraphics());
    }

    public void promptBgUpdate(Map mapToLoad) {
        map = mapToLoad;
        shouldUpdateBg = true;
    }

    public void promptBgUpdate() {
        shouldUpdateBg = true;
    }

    public void promptFgUpdate() {
        shouldUpdateFg = true;
    }

    public void promptCustomUpdate() {
        shouldUpdateCustom = true;
    }

    /**
     * Outputs finalGraphics as sc.png
     */
    public void screenshot() {
        try {
            ImageIO.write(finalImg, "png", new File("sc.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Render something to finalGraphics once before disposing of it.
     * @param oneTimeRender The code to run once.
     */
    public void renderOnce(BiConsumer<Graphics2D, JPanel> oneTimeRender) {
        this.oneTimeRender = oneTimeRender;
    }

    @Override
    public void paint(Graphics g) {
        if(g == null)
            return;

        Tile tile = null;
        int x = 0;
        int y = 0;
        Graphics2D finalGraphics = (Graphics2D) finalImg.getGraphics();

        if(shouldUpdateBg) {
            shouldUpdateBg = false;
            Graphics2D imgGraphics = (Graphics2D) bg.getGraphics();

            for (int i = 0; i < gridSize; i++) {
                for (int n = 0; n < gridSize; n++) {
                    tile = map.getTiles()[i][n];
                    x = n * tile.getTexture().getImage().getWidth();
                    y = i * tile.getTexture().getImage().getHeight();
                    imgGraphics.drawImage(tile.getTexture().getImage(), x, y, this);

                    if (tile.getLightLevel() > 0)
                        litTiles.put(new int[]{x, y}, tile);
                }
            }

            litTiles.forEach((key,value) -> {
                imgGraphics.setColor(CustomColors.FIRELIGHT);
                for(int i = 0; i < value.getLightLevel(); i++) {
                    imgGraphics.fillOval(key[0] - (i), key[1] - (i), value.getTexture().getImage().getWidth() + (i * 2), value.getTexture().getImage().getHeight() + (i * 2));
                }
            });

            litTiles.clear();
            imgGraphics.dispose();
        }

        if(shouldUpdateFg) {
            shouldUpdateFg = false;
            Graphics2D fG = (Graphics2D) fg.getGraphics();

            fG.setBackground(CustomColors.TRANSPARENT);
            fG.clearRect(0, 0, 1024, 1024);

            for (GenericEntity entity : map.getEntities()) {
                x = entity.getX();
                y = entity.getY();

                fG.setColor(CustomColors.SHADOW);
                for (int i = 0; i < 12; i++) {
                    fG.fillOval(x - (i) - 14, y - (i) - 14, entity.getTexture().getImage().getWidth() + (i * 2) + 28, entity.getTexture().getImage().getHeight() + (i * 2) + 28);
                }

                fG.drawImage(entity.getTexture().getImage(), x, y, this);
                entity.getRenderingCode().accept(fG, this);
            }

            fG.dispose();
        }

        finalGraphics.drawImage(bg, 0, 0, this);
        finalGraphics.drawImage(fg, 0, 0, this);

        if(shouldUpdateCustom) {
            shouldUpdateCustom = false;
            Graphics2D customGraphics = (Graphics2D) custom.getGraphics();
            customGraphics.setBackground(CustomColors.TRANSPARENT);
            customGraphics.clearRect(0, 0, 1024, 1024);

            customRendering.forEach(e -> e.accept(customGraphics, this));
            customGraphics.dispose();
        }

        if(oneTimeRender != null) {
            oneTimeRender.accept(finalGraphics, this);
            oneTimeRender = null;
        }

        finalGraphics.drawImage(custom, 0, 0, this);
        g.drawImage(finalImg, ((frame.getWidth() - 1024) / 2), (frame.getHeight() - 1024) / 2, this);

        finalGraphics.dispose();
        g.dispose();
    }
}
