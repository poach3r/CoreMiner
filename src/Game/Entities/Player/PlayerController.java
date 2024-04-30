package Game.Entities.Player;

import Game.Audio.SoundIndex;
import Game.Graphics.CraftingUI;
import Game.Logic.Crafter;
import Game.Main;
import Game.Tiles.TileIndex;
import Library.Audio.AudioPlayer;
import Library.Entities.GenericEntity;
import Game.Graphics.Foreground;
import Library.Items.GenericItem;
import Library.Map.Map;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Optional;

/** A heavily modified generic entity controller which handles our keyboard input via the KeyListener interface and then moves the player accordingly.
 * @author poacher
 */
public class PlayerController extends Library.Entities.GenericEntityController implements KeyListener, MouseListener {

    /* In order to get smooth movement, we must only move the player when a key is held down and not whenever we receive a keyboard input
     * as keyboards will send repeated inputs while a key is held down therefore, we add all currently pressed keys to this List
     * which is then parsed in move()
     */
    ArrayList<Integer> heldKeys;

    private Player player;
    private Foreground fg;
    private Crafter crafter;
    private JFrame frame;
    private AudioPlayer audioPlayer;

    public PlayerController(Player player, Foreground fg, Crafter crafter, JFrame frame, AudioPlayer audioPlayer) {
        super();
        heldKeys = new ArrayList<>();
        this.player = player;
        this.fg = fg;
        this.crafter = crafter;
        this.frame = frame;
        this.audioPlayer = audioPlayer;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setFg(Foreground fg) {
        this.fg = fg;
    }

    // We can ignore this
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /** Handles key presses by adding them to heldKeys as long as the key is not already contained within heldKeys.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R) {
            Main.restart();
        }

        else if(e.getKeyCode() == KeyEvent.VK_E) {
            Main.toggleCrafting();
        }

        else if(e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(1);
        }

        else if(!heldKeys.contains(e.getKeyCode()))
            heldKeys.add(e.getKeyCode());
    }

    /** Handles key releases by removing them from heldKeys.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        heldKeys.remove((Integer) e.getKeyCode());
    }

    /** Handles all keybindings based on the contents of heldKeys.
     */
    @Override
    public void move() {
        player.setTimeSinceLastMine(player.getTimeSinceLastMine() + 1);

        // movement keybinds
        if(player.getTimeSinceLastMove() >= player.getSpeed()) {
            player.setTimeSinceLastMove(0);
            if (heldKeys.contains(KeyEvent.VK_W))
                if (player.getY() > 0)
                    if (!getMap().getTiles()[player.getY() - 1][player.getX()].hasCollision()) {
                        player.setY(player.getY() - 1);
                        //audioPlayer.play(getMap().getTiles()[player.getY() - 1][player.getX()].getStepSound());
                        fg.promptUpdate();
                    }

            if (heldKeys.contains(KeyEvent.VK_S))
                if (player.getY() < 15)
                    if (!getMap().getTiles()[player.getY() + 1][player.getX()].hasCollision()) {
                        player.setY(player.getY() + 1);
                        //audioPlayer.play(getMap().getTiles()[player.getY() + 1][player.getX()].getStepSound());
                        fg.promptUpdate();
                    }

            if (heldKeys.contains(KeyEvent.VK_A))
                if (player.getX() > 0)
                    if (!getMap().getTiles()[player.getY()][player.getX() - 1].hasCollision()) {
                        player.setX(player.getX() - 1);
                        //audioPlayer.play(getMap().getTiles()[player.getY()][player.getX() - 1].getStepSound());
                        fg.promptUpdate();
                    }

            if (heldKeys.contains(KeyEvent.VK_D))
                if (player.getX() < 15)
                    if (!getMap().getTiles()[player.getY()][player.getX() + 1].hasCollision()) {
                        player.setX(player.getX() + 1);
                        //audioPlayer.play(getMap().getTiles()[player.getY()][player.getX() + 1].getStepSound());
                        fg.promptUpdate();
                    }
        }

        else
            player.setTimeSinceLastMove(player.getTimeSinceLastMove() + 1);
    }

    /** Misc player logic such as calculating death and collision.
     */
    public void miscLogic() {
        if(getMap().getTiles()[player.getY()][player.getX()].getName().equals("void")) {
            Game.Main.removeAllEntitiesButPlayer();
            Game.Main.progress();
        }

        // Entity collision
        if(player.getTimeSinceHit() == player.getITime()) { // If the player is not invincible
            Optional<GenericEntity> possibleCollider = fg.getEntityAtTileButAvoidEntity(player.getX(), player.getY(), player);
            possibleCollider.ifPresent(entity -> { // If the player is colliding with an entity.
                player.setHp(player.getHp() - entity.getDmg());
                player.setTimeSinceHit(0);
            });
        }

        else {
            player.setTimeSinceHit(player.getTimeSinceHit() + 1);
        }

        if(player.getHp() <= 0) {
            Main.restart();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = ((e.getX() - ((frame.getWidth() - fg.getWidth())) / 2) / 64);
        int y = ((e.getY() - ((frame.getHeight() - fg.getHeight())) / 2) / 64);

        if(x < 0 || y < 0 || x > 15 || y > 15)
            return;

        if(player.getX() > x + player.getTool().getRadius() || player.getX() < x - player.getTool().getRadius()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too far");
            System.out.println(player.getX());
            System.out.println(x + player.getTool().getRadius());
            System.out.println(x - player.getTool().getRadius());
            return;
        }

        if(player.getY() > y + player.getTool().getRadius() || player.getY() < y - player.getTool().getRadius()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too far");
            System.out.println(player.getY());
            System.out.println(y + player.getTool().getRadius());
            System.out.println(y - player.getTool().getRadius());
            return;
        }

        if(player.getTimeSinceLastMine() < player.getTool().getSpeed()) {
            audioPlayer.play(SoundIndex.digFail);
            System.out.println("too fast");
            return;
        }

        player.setTimeSinceLastMine(0);

        for(GenericItem item : getMap().getTiles()[y][x].getResources())
            player.addItem(item);

        Map m = new Map(getMap());
        m.setTile(TileIndex.Void, y, x);
        Main.updateMap(m);

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
