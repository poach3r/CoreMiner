package Game.Biomes.StageThreeBiomes;

import Game.Biomes.StageTwoBiomes.StonyCave;
import Library.Map.Map;

import java.util.Random;

public class StageThreeBiomes {
    public StageThreeBiomes() {}

    public Map generate() {
        switch(new Random().nextInt(3)) {
            case 0 -> { return new UndergroundForest().generate(); }
            case 1, 2 -> { return new StonyCave().generate(); }
        }
        return new Map();
    }
}
