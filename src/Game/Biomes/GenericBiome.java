package Game.Biomes;

import Library.Map.Map;
import Library.Map.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GenericBiome extends Map {
    private Random rand;
    private HashMap<Integer, Tile> tiles;
    private Runnable extraGeneration;
    private NoiseGenerator ng;
    private Tile[][] ores;
    private int oreRarity; // higher number = more rare

    public GenericBiome() {
        super();
        rand = new Random();
        tiles = new HashMap<>();
        ng = new NoiseGenerator();
        ores = new Tile[2][2];
        extraGeneration = null;
        oreRarity = 1;
    }

    public void putTile(int i, Tile tile) {
        tiles.put(i, tile);
    }

    public void setExtraGeneration(Runnable extraGeneration) {
        this.extraGeneration = extraGeneration;
    }

    public void addOre(int index, Tile oreTile, Tile containerTile) {
        ores[index][0] = oreTile;
        ores[index][1] = containerTile;
    }

    public void setOreRarity(int oreRarity) {
        this.oreRarity = oreRarity;
    }

    /**
     * We are using a perlin noise algorithm for generating terrain, after we do this, we randomly decide whether each
     * tile should hold an ore or not.
     * @return The newly generate map
     */
    public Map generate() {
        int tile = 0;
        for(int i = 0; i < 16; i++) { // column
            for(int n = 0; n < 16; n++) { // row
                tile = (int) Math.round(ng.noise(i, n) * 10);
                setTile(tiles.getOrDefault(tile, tiles.get(0)), i, n);
            }
        }

        tile = 0;
        for(int i = 0; i < 16; i++) { // column
            for(int n = 0; n < 16; n++) { // row
                tile = rand.nextInt(oreRarity);

                if(tile == 0) {
                    if(rand.nextBoolean()) {
                        if(ores[0][1] == null || ores[0][0] == null)
                            continue;

                        if (getTiles()[i][n].getName().equals(ores[0][1].getName()))
                            setTile(ores[0][0], i, n);
                    }

                    else {
                        if(ores[1][1] == null || ores[1][0] == null)
                            continue;

                        if (getTiles()[i][n].getName().equals(ores[1][1].getName()))
                            setTile(ores[1][0], i, n);
                    }
                }
            }
        }

        if(extraGeneration != null)
            extraGeneration.run();

        return this;
    }
}

//package Game.Biomes;
//
//import Library.Map.Map;
//import Library.Map.Tile;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Random;
//
//public class GenericBiome extends Map {
//    private int[][] tileVals;
//    private Random rand;
//    private HashMap<Integer, Tile> tiles;
//    private Runnable extraGeneration;
//    private NoiseGenerator ng;
//
//    public GenericBiome() {
//        super();
//        tileVals = new int[16][16];
//        rand = new Random();
//        tiles = new HashMap<>();
//        ng = new NoiseGenerator();
//        extraGeneration = null;
//    }
//
//    public void setTile(int i, Tile tile) {
//        tiles.put(i, tile);
//    }
//
//    public void setExtraGeneration(Runnable extraGeneration) {
//        this.extraGeneration = extraGeneration;
//    }
//
//    /**
//     * Firstly, we randomly generate a "principle value" that decides the dominant tile type, then we filter out the tile if it was grown,
//     * then decide whether the tile should grow or not
//     * @return The newly generate map
//     */
//    public Map generate() {
//        int principleVal = rand.nextInt(2) + 1;
//        int tile = 0;
//        for(int i = 0; i < 16; i++) { // column
//            for(int n = 0; n < 16; n++) { // row
//                tile = tileVals[i][n];
//
//                // filter grown tiles
//                if(tile == 0) {
//                    tile = rand.nextInt(5) + principleVal; // calculate extremity of tile
//                    if (tile == 0)
//                        tile = principleVal;
//                }
//
//                if(tiles.get(tile).grows()) {
//                    if (i < 15 && n < 15 && i > 0 && n > 0) // growth
//                        if (rand.nextBoolean()) {
//                            tileVals[i][n + 1] = tile;
//                            tileVals[i][n - 1] = tile;
//                            tileVals[i + 1][n] = tile;
//                            tileVals[i - 1][n] = tile;
//                        }
//                }
//
//                super.setTile(tiles.get(tile), i, n);
//            }
//        }
//
//        if(extraGeneration != null)
//            extraGeneration.run();
//
//        return this;
//    }
//}
