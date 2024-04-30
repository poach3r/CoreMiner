package Game.Biomes.StageThreeBiomes;

import Game.Biomes.GenericBiome;
import Game.Entities.Hostile.MushyZombie;
import Game.Entities.Hostile.Zombie;
import Game.Structures.GraniteClump;
import Game.Structures.Mineshaft;
import Game.Tiles.TileIndex;

import java.util.Random;

public class UndergroundForest extends GenericBiome {
    public UndergroundForest() {
        setTile(1, TileIndex.stone);
        setTile(2, TileIndex.stone);
        setTile(3, TileIndex.andesite);
        setTile(4, TileIndex.stoneShroom);
        setTile(5, TileIndex.log);
        setTile(6, TileIndex.stoneShroom);
        setTile(7, TileIndex.log);
        setTile(8, TileIndex.log);
        setTile(9, TileIndex.log);

        setExtraGeneration(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();

                Game.Main.summonEntity(new MushyZombie(r.nextInt(16), r.nextInt(16)), 2);

                if(r.nextBoolean())
                    Game.Main.summonEntity(new MushyZombie(r.nextInt(16), r.nextInt(16)), 2);

                if(r.nextBoolean())
                    Game.Main.summonEntity(new Zombie(r.nextInt(16), r.nextInt(16)), 2);
            }
        });
    }
}
