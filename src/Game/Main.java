package Game;

import Game.Biomes.StageOneBiomes.StageOneBiomes;
import Game.Biomes.StageOneBiomes.Void;
import Game.Biomes.StageThreeBiomes.StageThreeBiomes;
import Game.Biomes.StageTwoBiomes.StageTwoBiomes;
import Game.Entities.Player.Player;
import Game.Entities.Player.PlayerController;
import Game.Graphics.CraftingUI;
import Game.Logic.Crafter;
import Library.Entities.GenericEntity;
import Library.Entities.HostileEntityController;
import Library.Entities.PassiveEntityController;
import Library.Audio.AudioPlayer;
import Library.Graphics.Renderer;
import Library.Map.Map;
import Library.Misc.GameLoop;

public class Main {
    private static Player player;
    private static Crafter crafter;
    private static AudioPlayer audioPlayer;

    private static Library.Graphics.Frame frame;
    private static Renderer renderer;
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
        renderer = new Renderer(frame, 16);
        cpnl = new Library.Graphics.ContentPanel();
        overlay = new Game.Graphics.Overlay(player);
        craftingUI = new CraftingUI(frame, crafter, player);

        playerController = new PlayerController(player, renderer, frame, audioPlayer);
        hostileEntityController = new HostileEntityController(player, renderer);
        passiveEntityController = new PassiveEntityController(renderer);
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
                        renderer.load();
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

        renderer.add(player);
        cpnl.add(overlay);
        cpnl.add(renderer);

        frame.add(cpnl);
        frame.addMouseListener(playerController);
        frame.addKeyListener(playerController);

        renderer.setMap(new Void().generate());

        loadNewMap();

        toggleCrafting();

        gl.run();
    }

    public static void loadNewMap() {
        playerController.setMap(map);
        passiveEntityController.setMap(map);
        hostileEntityController.setMap(map);

        renderer.promptUpdate(map);

        System.out.println("loading map");
    }

    public static void updateMap(Map m) {
        map = m;

        playerController.setMap(m);
        passiveEntityController.setMap(m);
        hostileEntityController.setMap(m);

        renderer.promptUpdate(m);

        System.out.println("updating map");
    }

    public static void progress() {
        removeAllEntitiesButPlayer();
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
                renderer.add(entity);
                renderer.promptUpdate();
            }
        }

        else if(hostility == 2) {
            if (hostileEntityController != null) {
                hostileEntityController.addEntity(entity);
                renderer.add(entity);
                renderer.promptUpdate();
            }
        }
    }

    public static void removeAllEntitiesButPlayer() {
        renderer.removeAllBut(player);
        hostileEntityController.removeAll();
        passiveEntityController.removeAll();
        renderer.promptUpdate();
        System.out.println("removing non player entities");
    }

    public static void restart() {
        renderer.removeAllEntities();

        player = new Player();
        renderer.add(player);
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
    }
}