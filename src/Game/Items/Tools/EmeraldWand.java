package Game.Items.Tools;

import Game.Entities.Player.Player;
import Game.Entities.Player.PlayerController;
import Game.Graphics.TextureIndex;
import Game.Main;
import Game.Tiles.TileIndex;
import Library.Entities.GenericEntity;
import Library.Graphics.Renderer;
import Library.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.List;

public class EmeraldWand extends GenericTool {
    public EmeraldWand() {
        super(7, "Emerald Wand", TextureIndex.emeraldWand, 20, 0, 192, 2, 0);
    }

    @Override
    public void logic(Player player, PlayerController playerController, Renderer renderer) {
        if(player.getHitbox().contains(player.getMouseX(), player.getMouseY()))
            return;

        playerController.getMap().setTile(TileIndex.log, player.getMouseY() / 64, player.getMouseX() / 64);

        renderer.promptBgUpdate();
        renderer.promptCustomUpdate();
    }

    @Override
    public void renderering(Player player, Graphics2D g, JPanel j) {
        double angle = (Math.atan2((player.getY() + 32) - player.getMouseY(), (player.getX() + 32) - player.getMouseX()));

        AffineTransform tx = AffineTransform.getRotateInstance(angle, 32, 32);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // draw staff
        g.drawImage(op.filter(getTexture().getImage(), null), player.getX(), player.getY(), null);

        // draw hitbox
        g.setColor(Color.GREEN);
        g.fillRect(player.getMouseX() - 16, player.getMouseY() - 16, 32, 32);
    }
}
