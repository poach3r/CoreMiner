package Game.Biomes;

import Library.Map.Map;
import Library.Map.Tile;

import java.util.HashMap;
import java.util.Random;

public class GenericBiome extends Map {
    private int[][] tileVals;
    private Random rand;
    private HashMap<Integer, Tile> tiles;
    private Runnable extraGeneration;

    public GenericBiome() {
        super();
        tileVals = new int[16][16];
        rand = new Random();
        tiles = new HashMap<Integer, Tile>();
        extraGeneration = null;
    }

    public void setTile(Integer index, Tile tile) {
        tiles.put(index, tile);
    }

    public void setTiles(HashMap<Integer, Tile> hm) {
        this.tiles = hm;
    }

    public void setExtraGeneration(Runnable extraGeneration) {
        this.extraGeneration = extraGeneration;
    }

    public Map generate() {
        int principleVal = rand.nextInt(2) + 1;
        for(int i = 0; i < 16; i++) { // column
            for(int n = 0; n < 16; n++) { // row
                int tile = tileVals[i][n];

                // filter grown tiles
                if(tile == 0) {
                    tile = rand.nextInt(5) + principleVal; // calculate extremity of tile
                    if (tile == 0)
                        tile = principleVal;
                }

                if(tiles.get(tile).grows()) {
                    if (i < 15 && n < 15 && i > 0 && n > 0) // growth
                        if (rand.nextBoolean()) {
                            tileVals[i][n + 1] = tile;
                            tileVals[i][n - 1] = tile;
                            tileVals[i + 1][n] = tile;
                            tileVals[i - 1][n] = tile;
                        }
                }

                this.setTile(tiles.get(tile), i, n);
            }
        }

        if(extraGeneration != null)
            extraGeneration.run();

        return this;
    }
}
