package Game.Entities.Player;

import Game.Audio.SoundIndex;
import Game.Main;
import Game.Tiles.TileIndex;
import Library.Audio.AudioPlayer;
import Library.Entities.GenericEntityController;
import Library.Graphics.Renderer;
import Library.Items.GenericItem;
import Library.Map.Map;
import Library.Map.Tile;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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

    public PlayerController(Player player, Renderer renderer, JFrame frame, AudioPlayer audioPlayer) {
        super();
        heldKeys = new ArrayList<>();
        this.player = player;
        this.renderer = renderer;
        this.frame = frame;
        this.audioPlayer = audioPlayer;
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
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            Main.toggleCrafting();
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
                if (player.getY() - 1 <= 0)
                    break;

                if (getMap().getTileAtPos(player.getX(), player.getY() - 1).stream().anyMatch(Tile::hasCollision))
                    break;

                player.setY(player.getY() - 1);
                //audioPlayer.play(getMap().getTiles()[player.getY() - 1][player.getX()].getStepSound());
                renderer.promptUpdate();
            }

        if (heldKeys.contains(KeyEvent.VK_S))
            for (int i = 0; i < player.getSpeed(); i++) {
                if (player.getY() + 1 >= 1024 - player.getTexture().getImage().getHeight())
                    break;

                if (getMap().getTileAtPos(player.getX(), player.getY() + 1).stream().anyMatch(Tile::hasCollision))
                    break;

                player.setY(player.getY() + 1);
                renderer.promptUpdate();
            }

        if (heldKeys.contains(KeyEvent.VK_A))
            for (int i = 0; i < player.getSpeed(); i++) {
                if (player.getX() + 1 <= 0)
                    break;

                if (getMap().getTileAtPos(player.getX() - 1, player.getY()).stream().anyMatch(Tile::hasCollision))
                    break;


                player.setX(player.getX() - 1);
                renderer.promptUpdate();
            }

        if (heldKeys.contains(KeyEvent.VK_D))
            for (int i = 0; i < player.getSpeed(); i++) {
                if (player.getX() + 1 >= 1024 - player.getTexture().getImage().getWidth())
                    break;

                if (getMap().getTileAtPos(player.getX() + 1, player.getY()).stream().anyMatch(Tile::hasCollision))
                    break;

                player.setX(player.getX() + 1);
                renderer.promptUpdate();

            }
    }

    /**
     * Misc player logic such as calculating death and collision.
     */
    public void miscLogic() {
        player.setTimeSinceLastMine(player.getTimeSinceLastMine() + 1);

        if (getMap().getTileAtPos(player.getX(), player.getY()).stream().anyMatch(e -> e.getId() == 0))
            Main.progress();

        // Entity collision
        if (player.getTimeSinceHit() == player.getITime()) { // If the player is not invincible
            int possibleDamage = renderer.entityIsColliding(player);
            if (possibleDamage != 0) {
                player.setHp(player.getHp() - possibleDamage);
                player.setTimeSinceHit(0);
            }

        } else {
            player.setTimeSinceHit(player.getTimeSinceHit() + 1);
        }

        if (player.getHp() <= 0) {
            Main.restart();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = ((e.getX() - ((frame.getWidth() - 1024)) / 2) / 64);
        int y = ((e.getY() - ((frame.getHeight() - 1024)) / 2) / 64);

        if (x < 0 || y < 0 || x > 15 || y > 15)
            return;

        if (player.getX() / 64 > x + player.getTool().getRadius() || player.getX() / 64 < x - player.getTool().getRadius()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too far");
            System.out.println(player.getX());
            System.out.println(x + player.getTool().getRadius());
            System.out.println(x - player.getTool().getRadius());
            return;
        }

        if (player.getY() / 64 > y + player.getTool().getRadius() || player.getY() / 64 < y - player.getTool().getRadius()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too far");
            System.out.println(player.getY());
            System.out.println(y + player.getTool().getRadius());
            System.out.println(y - player.getTool().getRadius());
            return;
        }

        if (player.getTimeSinceLastMine() < player.getTool().getSpeed()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too fast");
            return;
        }

        player.setTimeSinceLastMine(0);

        for (GenericItem item : getMap().getTiles()[y][x].getResources())
            player.addItem(item);

        Map m = new Map(getMap());
        m.setTile(TileIndex.Void, y, x);
        Main.updateMap(m);
        System.out.println("mine");

        audioPlayer.play(SoundIndex.dig1);

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
