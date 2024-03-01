package io.itch.padjokej.tealeaf.item;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import io.itch.padjokej.tealeaf.item.ModItemGroup;
import io.itch.padjokej.tealeaf.item.DrinkableItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
public class ModItems
{
    public static final Item SPRUCE_TEALEAF = registerItem("spruce_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item OAK_TEALEAF = registerItem("oak_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item ACACIA_TEALEAF = registerItem("acacia_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item JUNGLE_TEALEAF = registerItem("jungle_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item BIRCH_TEALEAF = registerItem("birch_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item DARK_OAK_TEALEAF = registerItem("dark_oak_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item MANGROVE_OAK_TEALEAF = registerItem("mangrove_tea_leaf",
            new Item(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item SPRUCE_TEA = registerItem("spruce_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item OAK_TEA = registerItem("oak_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item ACACIA_TEA = registerItem("acacia_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item JUNGLE_TEA = registerItem("jungle_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item BIRCH_TEA = registerItem("birch_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item DARK_OAK_TEA = registerItem("dark_oak_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    public static final Item MANGROVE_OAK_TEA = registerItem("mangrove_tea",
            new DrinkableItem(new FabricItemSettings().group(ModItemGroup.TEALEAF)));
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(TeaLeaf.MOD_ID,name), item);
    }
    public static void registerModItems()
    {
        TeaLeaf.LOGGER.debug("Registering Mod Items for TeaLeaf!");
    }
}
