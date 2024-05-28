package Game.Items.Tools;

import Game.Entities.Player.Player;
import Game.Entities.Player.PlayerController;
import Game.Graphics.TextureIndex;
import Library.Graphics.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class RubyWand extends GenericTool {
    public RubyWand() {
        super(7, "Ruby Wand", TextureIndex.rubyWand, 20, 0, 320, 10, 1);
    }

    @Override
    public void logic(Player player, PlayerController playerController, Renderer renderer) {
        playerController.getMap().getEntitiesCollidingWithHitbox(new Rectangle(player.getMouseX() - 16, player.getMouseY() - 16, 32, 32))
                .ifPresent(entities -> entities.stream().filter(entity -> entity.getITime() == entity.getTimeSinceHit())
                        .forEach(entity ->  {
                            entity.setHp(entity.getHp() - player.getActiveTool().getDamage());
                            entity.setTimeSinceHit(0);
                        }));

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
        g.setColor(Color.RED);
        g.fillRect(player.getMouseX() - 16, player.getMouseY() - 16, 32, 32);
    }
}
