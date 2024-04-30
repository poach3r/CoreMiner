package Game.Biomes.StageTwoBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.Zombie;
import Game.Structures.Mineshaft;
import Game.Tiles.*;
import Library.Map.Tile;

import java.util.HashMap;
import java.util.Random;

public class GraniteCave extends GenericBiome {
    public GraniteCave() {
        setTile(1, TileIndex.stone);
        setTile(2, TileIndex.stone);
        setTile(3, TileIndex.granite);
        setTile(4, TileIndex.granite);
        setTile(5, TileIndex.granite);
        setTile(6, TileIndex.dirt);
        setTile(7, TileIndex.copper);
        setTile(8, TileIndex.copper);
        setTile(9, TileIndex.copper);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                if(r.nextInt(10) == 1) {
                    placeStructure(new Mineshaft(), r.nextInt(16), r.nextInt(16));
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);
                }

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);
            }
        });
    }
}
