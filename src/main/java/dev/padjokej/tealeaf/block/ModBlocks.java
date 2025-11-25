package dev.padjokej.tealeaf.block;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.block.teapot.TeaPotBlock;
import dev.padjokej.tealeaf.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TEAPOT_BLOCK = registerBlock("teapot",
            new TeaPotBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(1f).nonOpaque()), ModItemGroup.TEALEAF);
    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItems(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(TeaLeaf.MOD_ID, name), block);
    }
    private static Item registerBlockItems(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(TeaLeaf.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks()
    {
        TeaLeaf.LOGGER.debug("Registering mod blocks for " + TeaLeaf.MOD_ID);
    }
}
