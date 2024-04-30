package Game.Biomes.StageTwoBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.Zombie;
import Game.Structures.Mineshaft;
import Game.Tiles.*;
import Library.Map.Tile;

import java.util.HashMap;
import java.util.Random;

public class CopperRichCave extends GenericBiome {
    public CopperRichCave() {
        setTile(1, TileIndex.granite);
        setTile(2, TileIndex.granite);
        setTile(3, TileIndex.stone);
        setTile(4, TileIndex.stone);
        setTile(5, TileIndex.copper);
        setTile(6, TileIndex.andesite);
        setTile(7, TileIndex.copper);
        setTile(8, TileIndex.dirt);
        setTile(9, TileIndex.copper);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                if(r.nextInt(20) == 1) {
                    placeStructure(new Mineshaft(), r.nextInt(16), r.nextInt(16));
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);
                }

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);
            }
        });
    }
}
