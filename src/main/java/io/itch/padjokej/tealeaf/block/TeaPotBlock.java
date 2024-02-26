package io.itch.padjokej.tealeaf.block;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import net.minecraft.inventory.Inventories;

public class TeaPotBlock extends BlockWithEntity implements InventoryProvider, Waterloggable
{
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return null;
    }
}
