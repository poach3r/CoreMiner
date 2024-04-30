package Library.Items;

import Game.Items.ItemType;

import java.io.File;
import java.util.HashMap;

public class GenericItem {
    private final HashMap<GenericItem, Integer> requiredItems;
    private final File asset;
    private final int value;
    private final String name;
    private ItemType itemType;

    public GenericItem(String name, File asset, int value) {
        this.name = name;
        this.asset = asset;
        this.value = value;
        itemType = ItemType.RESOURCE;
        requiredItems = new HashMap<>();
    }

    public GenericItem(String name, File asset, int value, ItemType itemType) {
        this.name = name;
        this.asset = asset;
        this.value = value;
        this.itemType = itemType;
        requiredItems = new HashMap<>();
    }

    public File getAsset() {
        return asset;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void addRequiredItem(GenericItem item, int count) {
        requiredItems.put(item, count);
    }

    public HashMap<GenericItem, Integer> getRecipe() {
        return requiredItems;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public GenericItem toGenericItem() {
        return (GenericItem) this;
    }
}
