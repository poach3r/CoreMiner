package Game.Biomes.StageTwoBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.Zombie;
import Game.Structures.GraniteClump;
import Game.Structures.Mineshaft;
import Game.Tiles.*;
import Library.Map.Tile;

import java.util.HashMap;
import java.util.Random;

public class StonyCave extends GenericBiome {
    public StonyCave() {
        setTile(1, TileIndex.stone);
        setTile(2, TileIndex.stone);
        setTile(3, TileIndex.stone);
        setTile(4, TileIndex.andesite);
        setTile(5, TileIndex.andesite);
        setTile(6, TileIndex.iron);
        setTile(7, TileIndex.dirt);
        setTile(8, TileIndex.dirt);
        setTile(9, TileIndex.copper);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                if(r.nextInt(10) == 1)
                    placeStructure(new GraniteClump(), r.nextInt(16), r.nextInt(16));

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
