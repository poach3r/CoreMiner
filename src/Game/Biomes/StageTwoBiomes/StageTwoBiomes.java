package Game.Biomes.StageTwoBiomes;

import Library.Map.Map;

import java.util.Random;

public class StageTwoBiomes {
    public StageTwoBiomes() {}

    public Map generate() {
        switch(new Random().nextInt(5)) {
            case 0 -> { return new CopperRichCave().generate(); }
            case 1, 2 -> { return new GraniteCave().generate(); }
            case 3, 4 -> { return new StonyCave().generate(); }
        }
        return new Map();
    }
}
