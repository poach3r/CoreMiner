package Game.Items;

import Library.Graphics.Texture;

import java.util.HashMap;

public class GenericItem {
    private final HashMap<GenericItem, Integer> requiredItems;
    private final Texture texture;
    private final int value;
    private final String name;
    private ItemType itemType;
    private final int id;

    public GenericItem(int id, String name, Texture texture, int value) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.value = value;
        itemType = ItemType.RESOURCE;
        requiredItems = new HashMap<>();
    }

    public GenericItem(int id, String name, Texture texture, int value, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.texture = texture;
        this.value = value;
        this.itemType = itemType;
        requiredItems = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Texture getTexture() {
        return texture;
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
