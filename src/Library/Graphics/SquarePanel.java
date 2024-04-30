package Library.Graphics;

import javax.swing.*;

public class SquarePanel extends JPanel {
    private JFrame frame;
    public SquarePanel(JFrame frame) {
        this.frame = frame;
    }

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
