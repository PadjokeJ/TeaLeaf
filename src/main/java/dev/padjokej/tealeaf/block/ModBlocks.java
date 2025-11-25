package dev.padjokej.tealeaf.block;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.block.teapot.TeaPotBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TEAPOT_BLOCK = registerBlock("teapot",
            new TeaPotBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                    .strength(1f).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TeaLeaf.MOD_ID, name), block);
    }
    private static Item registerBlockItems(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(TeaLeaf.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerModBlocks()
    {
        TeaLeaf.LOGGER.debug("Registering mod blocks for " + TeaLeaf.MOD_ID);
    }
}
