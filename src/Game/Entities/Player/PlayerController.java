package Game.Entities.Player;

import Game.Audio.SoundIndex;
import Game.Main;
import Library.Audio.AudioPlayer;
import Library.Entities.GenericEntity;
import Library.Entities.GenericEntityController;
import Library.Graphics.Renderer;
import Library.Map.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * A heavily modified generic entity controller which handles our keyboard input via the KeyListener interface and then moves the player accordingly.
 *
 * @author poacher
 */
public class PlayerController extends GenericEntityController implements KeyListener, MouseListener {

    private final JFrame frame;
    private final AudioPlayer audioPlayer;
    /* In order to get smooth movement, we must only move the player when a key is held down and not whenever we receive a keyboard input
     * as keyboards will send repeated inputs while a key is held down therefore, we add all currently pressed keys to this List
     * which is then parsed in move()
     */
    ArrayList<Integer> heldKeys;
    private Player player;
    private final Renderer renderer;
    private World world;
    private boolean toolShown;

    public PlayerController(Player player, Renderer renderer, JFrame frame, AudioPlayer audioPlayer, World world) {
        super();
        heldKeys = new ArrayList<>();
        this.player = player;
        this.renderer = renderer;
        this.frame = frame;
        this.audioPlayer = audioPlayer;
        this.world = world;
        toolShown = false;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // We can ignore this
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key presses by adding them to heldKeys as long as the key is not already contained within heldKeys.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            Main.restart();
        } else if(e.getKeyCode() == KeyEvent.VK_E) {
            player.iterateTool();
            renderer.promptFgUpdate();
            renderer.promptCustomUpdate();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(1);
        } else if (!heldKeys.contains(e.getKeyCode()))
            heldKeys.add(e.getKeyCode());
    }

    /**
     * Handles key releases by removing them from heldKeys.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        heldKeys.remove((Integer) e.getKeyCode());
    }

    /**
     * Handles all keybindings based on the contents of heldKeys.
     */
    @Override
    public void move() {
        if (heldKeys.contains(KeyEvent.VK_W))
            for (int i = 0; i < player.getSpeed(); i++) {
                player.setY(player.getY() - 2);

                if (player.getY() < 0) {
                    if(world.getCurrentMapIndex()[1] < 1) {
                        player.setY(player.getY() + 2);
                        break;
                    }

                    player.setY(1024);
                    Main.removeAllEntitiesButPlayer();
                    world.setCurrentMap(world.getCurrentMapIndex()[0], world.getCurrentMapIndex()[1] - 1);
                    Main.loadNewMap();
                }

                if (world.getCurrentMap().entityCollidesWithTerrain(player)) {
                    player.setY(player.getY() + 2);
                    break;
                }

                //audioPlayer.play(world.getCurrentMap().getTiles()[player.getY() - 1][player.getX()].getStepSound());
                renderer.promptFgUpdate();
            }

        if (heldKeys.contains(KeyEvent.VK_S))
            for (int i = 0; i < player.getSpeed(); i++) {
                player.setY(player.getY() + 2);

                if (player.getY() > 1024 - player.getTexture().getImage().getHeight()) {
                    if(world.getCurrentMapIndex()[1] > 1) {
                        player.setY(player.getY() - 2);
                        break;
                    }

                    player.setY(0);
                    Main.removeAllEntitiesButPlayer();
                    world.setCurrentMap(world.getCurrentMapIndex()[0], world.getCurrentMapIndex()[1] + 1);
                    Main.loadNewMap();
                }

                if (world.getCurrentMap().entityCollidesWithTerrain(player)) {
                    player.setY(player.getY() - 2);
                    break;
                }

                renderer.promptFgUpdate();
            }

        if (heldKeys.contains(KeyEvent.VK_A))
            for (int i = 0; i < player.getSpeed(); i++) {
                player.setX(player.getX() - 2);

                if (player.getX() < 0) {
                    if(world.getCurrentMapIndex()[0] < 1) {
                        player.setX(player.getX() + 2);
                        break;
                    }

                    player.setX(1024);
                    Main.removeAllEntitiesButPlayer();
                    world.setCurrentMap(world.getCurrentMapIndex()[0] - 1, world.getCurrentMapIndex()[1]);
                    Main.loadNewMap();
                    return;
                }

                if (world.getCurrentMap().entityCollidesWithTerrain(player)) {
                    player.setX(player.getX() + 2);
                    break;
                }

                renderer.promptFgUpdate();
            }

        if (heldKeys.contains(KeyEvent.VK_D))
            for (int i = 0; i < player.getSpeed(); i++) {
                player.setX(player.getX() + 2);

                if (player.getX() > 1024 - player.getTexture().getImage().getWidth()) {
                    if(world.getCurrentMapIndex()[0] > 1) {
                        player.setX(player.getX() - 2);
                        break;
                    }

                    System.out.println(world.getCurrentMapIndex()[0]);

                    player.setX(0);
                    Main.removeAllEntitiesButPlayer();
                    world.setCurrentMap(world.getCurrentMapIndex()[0] + 1, world.getCurrentMapIndex()[1]);
                    Main.loadNewMap();
                    return;
                }

                if (world.getCurrentMap().entityCollidesWithTerrain(player)) {
                    player.setX(player.getX() - 2);
                    break;
                }

                renderer.promptFgUpdate();
            }
    }

    /**
     * Misc player logic such as calculating death and collision.
     */
    public void miscLogic() {
        player.setTimeSinceLastToolUse(player.getTimeSinceLastToolUse() + 1);

        // Entity collision
        if (player.getTimeSinceHit() == player.getITime()) { // If the player is not invincible
            Optional<List<GenericEntity>> possibleCollidingEntity = world.getCurrentMap().getEntitiesCollidingWithOtherEntity(player);

            // took damage
            possibleCollidingEntity.ifPresent(e -> {
                player.setHp(player.getHp() - e.get(0).getDmg());
                player.setTimeSinceHit(0);
                renderer.promptCustomUpdate();
            });

        } else {
            player.setTimeSinceHit(player.getTimeSinceHit() + 1);
        }

        // kill the player
        if (player.getHp() <= 0) {
            Main.restart();
        }

        // execute the code for the tile the player is at
        getMap().getTilesAtEntity(player).forEach(e -> e.getOnStepCode().accept(player));

        // if tool usage is over
        if(toolShown && player.getActiveTool().getUseTime() <= player.getTimeSinceLastToolUse()) {
            toolShown = false;
            renderer.promptCustomUpdate();
        }

        // if tool usage is not over, continue rendering it so it keeps up with the player
        else if(toolShown) {
            player.getActiveTool().logic(player, this, renderer);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        player.setMouseX(e.getX() - ((frame.getWidth() - 1024)) / 2);
        player.setMouseY(e.getY() - ((frame.getHeight() - 1024)) / 2);

        if(!new Rectangle(
                player.getX() - (player.getActiveTool().getRadius() / 2),
                player.getY() - (player.getActiveTool().getRadius() / 2),
                player.getActiveTool().getRadius() + player.getTexture().getImage().getWidth(),
                player.getActiveTool().getRadius() + player.getTexture().getImage().getHeight()
        ).contains(player.getMouseX(), player.getMouseY())) {
            audioPlayer.play(SoundIndex.digFail);
            return;
        }

        if(player.getTimeSinceLastToolUse() < player.getActiveTool().getSpeed()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too fast");
            return;
        }

        player.setTimeSinceLastToolUse(0);

        toolShown = true;
        renderer.promptCustomUpdate();
    }

    public BiConsumer<Graphics2D, JPanel> getToolRenderingCode() {
        return (g, j) -> {
            if(!toolShown) {
                return;
            }

            player.getActiveTool().renderering(player, g, j);
        };
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
