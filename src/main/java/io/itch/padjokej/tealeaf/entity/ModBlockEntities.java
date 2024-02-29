package io.itch.padjokej.tealeaf.entity;

import io.itch.padjokej.tealeaf.TeaLeaf;
import io.itch.padjokej.tealeaf.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<TeapotBlockEntity> TEAPOT;

    public static void registerBlockEntities()
    {
        TEAPOT = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(TeaLeaf.MOD_ID, "teapot"),
                FabricBlockEntityTypeBuilder.create(TeapotBlockEntity::new,
                        ModBlocks.TEAPOT_BLOCK).build(null));
    }
}
