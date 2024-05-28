package Game;

import Game.Biomes.StageOneBiomes.StageOneBiomes;
import Game.Entities.Player.Player;
import Game.Entities.Player.PlayerController;
import Game.Entities.Projectile.ProjectileController;
import Library.Audio.AudioPlayer;
import Library.Entities.GenericEntity;
import Library.Entities.HostileEntityController;
import Library.Entities.PassiveEntityController;
import Library.Graphics.Renderer;
import Library.Map.Map;
import Library.Map.World;
import Library.Misc.GameLoop;

public class Main {
    private static Player player;
    private static AudioPlayer audioPlayer;

    private static Library.Graphics.Frame frame;
    private static Renderer renderer;
    private static Library.Graphics.ContentPanel cpnl;
    private static Game.Graphics.Overlay overlay;

    private static PlayerController playerController;
    private static HostileEntityController hostileEntityController;
    private static PassiveEntityController passiveEntityController;
    private static ProjectileController projectileController;

    private static World world;

    private static GameLoop gl;

    public Main() {
        world = new World();
        genWorld();

        player = new Player();
        audioPlayer = new AudioPlayer();

        frame = new Library.Graphics.Frame();
        renderer = new Renderer(frame, 16);
        cpnl = new Library.Graphics.ContentPanel();
        overlay = new Game.Graphics.Overlay(player);

        playerController = new PlayerController(player, renderer, frame, audioPlayer, world);

        hostileEntityController = new HostileEntityController(player, renderer);
        hostileEntityController.setOnDeathAction(entity -> {
            world.getCurrentMap().removeEntity(entity);
            renderer.promptFgUpdate();
        });

        passiveEntityController = new PassiveEntityController(renderer);
        passiveEntityController.setOnDeathAction(entity -> {
            world.getCurrentMap().removeEntity(entity);
            renderer.promptFgUpdate();
        });

        projectileController = new ProjectileController(renderer);

        gl = new GameLoop(
                () -> {
                    playerController.miscLogic();
                    playerController.move();

                    passiveEntityController.move();
                    passiveEntityController.miscLogic();

                    hostileEntityController.miscLogic();
                    hostileEntityController.move();
                },
                () -> {
                    renderer.load();
                });
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        new Main();

        world.getCurrentMap().addEntity(player);
        cpnl.add(renderer);

        frame.add(cpnl);
        frame.addMouseListener(playerController);
        frame.addKeyListener(playerController);

        frame.revalidate();
        frame.repaint();

        renderer.addCustomRendering(overlay.getRenderingCode());
        renderer.addCustomRendering(playerController.getToolRenderingCode());
        renderer.promptCustomUpdate();

        loadNewMap();

        renderer.promptCustomUpdate();

        gl.run();
    }

    public static void p() {
        renderer.promptCustomUpdate();
    }

    public static void genWorld() {
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                world.setMap(n, i, new StageOneBiomes().generate());
            }
        }
        world.setCurrentMap(1, 1);
    }

    public static void loadNewMap() {
        System.out.println(world.getCurrentMap().getEntities());
        for (GenericEntity entity : world.getCurrentMap().getEntities()) {
            if (entity.getHostility() == 0)
                passiveEntityController.addEntity(entity);
            else if (entity.getHostility() == 1)
                hostileEntityController.addEntity(entity);
        }

        if (!world.getCurrentMap().getEntities().contains(player))
            world.getCurrentMap().addEntity(player);

        playerController.setMap(world.getCurrentMap());
        passiveEntityController.setMap(world.getCurrentMap());
        hostileEntityController.setMap(world.getCurrentMap());

        renderer.promptBgUpdate(world.getCurrentMap());
        renderer.promptFgUpdate();

        System.out.println("loading map");
    }

    public static void removeAllEntitiesButPlayer() {
        hostileEntityController.removeAll();
        passiveEntityController.removeAll();
        projectileController.removeAll();
        renderer.promptFgUpdate();
    }

    public static void restart() {
        player = new Player();
        hostileEntityController.setTarget(player);

        genWorld();
        loadNewMap();

        frame.removeKeyListener(playerController);
        frame.removeMouseListener(playerController);

        playerController.setPlayer(player);
        frame.addKeyListener(playerController);
        frame.addMouseListener(playerController);

        overlay.setPlayer(player);

        renderer.promptCustomUpdate();
        renderer.promptFgUpdate();
    }

    public static void addEntity(GenericEntity entity) {
        if (entity.getHostility() == 0)
            passiveEntityController.addEntity(entity);
        else if (entity.getHostility() == 1)
            hostileEntityController.addEntity(entity);

        renderer.promptFgUpdate();
    }
}