package Library.Graphics;

import javax.swing.*;

public class ContentPanel extends JPanel {
    public ContentPanel() {
        this.setLayout(new OverlayLayout(this));
        setOpaque(false);
        this.setVisible(true);
    }
}
