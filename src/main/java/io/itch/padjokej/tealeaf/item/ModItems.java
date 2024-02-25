package io.itch.padjokej.tealeaf.item;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
public class ModItems
{
    public static final Item SPRUCE_TEALEAF = registerItem("spruce_tea_leaf",
            new Item(new FabricItemSettings().group(ItemGroup.BREWING)));
    public static final Item OAK_TEALEAF = registerItem("oak_tea_leaf",
            new Item(new FabricItemSettings().group(ItemGroup.BREWING)));
    public static final Item ACACIA_TEALEAF = registerItem("acacia_tea_leaf",
            new Item(new FabricItemSettings().group(ItemGroup.BREWING)));
    public static final Item BIRCH_TEALEAF = registerItem("birch_tea_leaf",
            new Item(new FabricItemSettings().group(ItemGroup.BREWING)));
    public static final Item DARK_OAK_TEALEAF = registerItem("dark_oak_tea_leaf",
            new Item(new FabricItemSettings().group(ItemGroup.BREWING)));
    public static final Item MANGROVE_OAK_TEALEAF = registerItem("mangrove_oak_tea_leaf",
            new Item(new FabricItemSettings().group(ItemGroup.BREWING)));
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(TeaLeaf.MOD_ID,name), item);
    }
    public static void registerModItems()
    {
        TeaLeaf.LOGGER.debug("Registering Mod Items for TeaLeaf!");
    }
}
