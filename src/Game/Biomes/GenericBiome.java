package Game.Biomes;

import Library.Map.Map;
import Library.Map.Structure;
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

    public void placeStructure(Structure structure, int row, int column) {
        for (int i = 0; i < structure.getTiles().length; i++) {
            for (int n = 0; n < structure.getTiles().length; n++) {
                if(i + row > 15)
                    continue;
                if(n + column > 15)
                    continue;

                setTile(structure.getTiles()[n][i], i + row, n + column);
            }
        }
    }
}