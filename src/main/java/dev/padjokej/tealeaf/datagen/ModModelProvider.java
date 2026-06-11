package dev.padjokej.tealeaf.datagen;

import dev.padjokej.tealeaf.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Block model for teapot is already in class
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TEA_CUP);

        itemModelGenerator.register(ModItems.ACACIA_TEA);
        itemModelGenerator.register(ModItems.BIRCH_TEA);
        itemModelGenerator.register(ModItems.DARK_OAK_TEA);
        itemModelGenerator.register(ModItems.JUNGLE_TEA);
        itemModelGenerator.register(ModItems.MANGROVE_TEA);
        itemModelGenerator.register(ModItems.OAK_TEA);
        itemModelGenerator.register(ModItems.PALE_OAK_TEA);
        itemModelGenerator.register(ModItems.SPRUCE_TEA);

        itemModelGenerator.register(ModItems.ACACIA_TEALEAF);
        itemModelGenerator.register(ModItems.BIRCH_TEALEAF);
        itemModelGenerator.register(ModItems.DARK_OAK_TEALEAF);
        itemModelGenerator.register(ModItems.JUNGLE_TEALEAF);
        itemModelGenerator.register(ModItems.MANGROVE_TEALEAF);
        itemModelGenerator.register(ModItems.OAK_TEALEAF);
        itemModelGenerator.register(ModItems.PALE_OAK_TEALEAF);
        itemModelGenerator.register(ModItems.SPRUCE_TEALEAF);
    }
}
