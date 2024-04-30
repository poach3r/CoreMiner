package Game;

import Game.Biomes.StageOneBiomes.StageOneBiomes;
import Game.Biomes.StageThreeBiomes.StageThreeBiomes;
import Game.Biomes.StageThreeBiomes.UndergroundForest;
import Game.Biomes.StageTwoBiomes.StageTwoBiomes;
import Game.Entities.Player.Player;
import Game.Entities.Player.PlayerController;
import Game.Graphics.CraftingUI;
import Game.Graphics.Foreground;
import Game.Items.ItemIndex;
import Game.Logic.Crafter;
import Library.Entities.GenericEntity;
import Library.Entities.HostileEntityController;
import Library.Entities.PassiveEntityController;
import Library.Audio.AudioPlayer;
import Library.Misc.GameLoop;

public class Main {
    private static Player player;
    private static Crafter crafter;
    private static AudioPlayer audioPlayer;

    private static Library.Graphics.Frame frame;
    private static Foreground fg;
    private static Game.Graphics.Background bg;
    private static Library.Graphics.ContentPanel cpnl;
    private static Game.Graphics.Overlay overlay;
    private static CraftingUI craftingUI;

    private static PlayerController playerController;
    private static HostileEntityController hostileEntityController;
    private static PassiveEntityController passiveEntityController;

    private static Library.Map.Map map;

    private static GameLoop gl;

    private static int stage;

    public Main() {
        stage = 0;

        player = new Player();
        crafter = new Crafter(player);
        audioPlayer = new AudioPlayer();

        frame = new Library.Graphics.Frame();
        fg = new Foreground(frame);
        bg = new Game.Graphics.Background(frame);
        cpnl = new Library.Graphics.ContentPanel();
        overlay = new Game.Graphics.Overlay(player);
        craftingUI = new CraftingUI(frame, crafter, player);

        playerController = new PlayerController(player, fg, crafter, frame, audioPlayer);
        hostileEntityController = new HostileEntityController(player, fg);
        passiveEntityController = new PassiveEntityController(fg);
        map = new StageOneBiomes().generate();
        gl = new GameLoop(
                new Runnable() {
                    @Override
                    public void run() {
                        playerController.miscLogic();
                        playerController.move();

                        passiveEntityController.move();

                        hostileEntityController.miscLogic();
                        hostileEntityController.move();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        bg.reload(map);
                        fg.load();
                        overlay.reload();
                        craftingUI.displayInv();
                    }
                });
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        new Main();

        loadNewMap();
        fg.add(player);
        cpnl.add(fg);
        cpnl.add(overlay);
        cpnl.add(bg);

        frame.add(cpnl);
        frame.addMouseListener(playerController);
        frame.addKeyListener(playerController);
        frame.pack();

        toggleCrafting();

        gl.run();
    }

    public static void loadNewMap() {
        playerController.setMap(map);
        passiveEntityController.setMap(map);
        hostileEntityController.setMap(map);

        bg.setMap(map);
        bg.load();

        fg.promptUpdate();
        System.out.println("loading map");
    }

    public static void updateMap(Library.Map.Map m) {
        map = m;

        playerController.setMap(m);
        passiveEntityController.setMap(m);
        hostileEntityController.setMap(m);

        bg.promptUpdate();
        fg.promptUpdate();

        System.out.println("updating map");
    }

    public static void progress() {
        stage += 1;
//        switch(stage) {
//            case 2, 3, 4, 5, 6 -> updateMap(new StageTwoBiomes().generate());
//            case 7, 8, 9, 10, 11 -> updateMap(new StageThreeBiomes().generate());
//        }

        if(stage < 7)
            updateMap(new StageTwoBiomes().generate());
        else
            updateMap(new StageThreeBiomes().generate());
    }

    // 1 = passive 2 = hostile, temp solution
    public static void summonEntity(GenericEntity entity, int hostility) {
        if(hostility == 1) {
            if(passiveEntityController != null) {
                passiveEntityController.addEntity(entity);
                fg.add(entity);
            }
        }

        else if(hostility == 2) {
            if (hostileEntityController != null) {
                hostileEntityController.addEntity(entity);
                fg.add(entity);
            }
        }

        playerController.setFg(fg);
    }

    public static void removeAllEntitiesButPlayer() {
        fg.removeAllBut(player);
        hostileEntityController.removeAll();
        passiveEntityController.removeAll();
        System.out.println("removing non player entities");
    }

    public static void restart() {
        fg.removeAllEntities();

        player = new Player();
        fg.add(player);
        hostileEntityController.setTarget(player);

        map = new StageOneBiomes().generate();
        loadNewMap();

        frame.removeKeyListener(playerController);
        frame.removeMouseListener(playerController);

        playerController.setPlayer(player);
        frame.addKeyListener(playerController);
        frame.addMouseListener(playerController);

        overlay.setPlayer(player);

        craftingUI.setPlayer(player);
        crafter.setPlayer(player);
        stage = 0;
    }

    public static void toggleCrafting() {
        if(craftingUI.isOpened()) {
            craftingUI.setOpenStatus(false);
            cpnl.remove(craftingUI);
        }

        else {
            craftingUI.setOpenStatus(true);
            cpnl.add(craftingUI, 0);
            craftingUI.displayInv();
            craftingUI.displayRecipes();
        }

        cpnl.revalidate();
        cpnl.repaint();
        System.out.println(craftingUI.isOpened());
    }
}