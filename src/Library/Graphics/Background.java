package Library.Graphics;

import Library.Map.Tile;

import javax.swing.*;
import java.awt.*;

public abstract class Background extends JPanel {
    protected Library.Map.Map map;
    protected JFrame frame;

    public Background(JFrame frame) {
        this.frame = frame;
        this.setLayout(new GridLayout(16, 16, 0, 0));
        this.setBackground(Color.BLACK);
    }

    public abstract void load();

    public Library.Map.Map getMap() {
        return map;
    }

    public void setMap(Library.Map.Map map) {
        this.map = map;
    }

    public void setTile(Tile tile, int row, int column) {
        map.setTile(tile, row, column);
    }
}
