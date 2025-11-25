package dev.padjokej.tealeaf.entity;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<TeapotBlockEntity> TEAPOT;

    public static void registerBlockEntities()
    {
        TEAPOT = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(TeaLeaf.MOD_ID, "teapot"),
                FabricBlockEntityTypeBuilder.create(TeapotBlockEntity::new,
                        ModBlocks.TEAPOT_BLOCK).build(null));
    }
}
