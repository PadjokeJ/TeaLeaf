package io.itch.padjokej.tealeaf.block.teapot;

import io.itch.padjokej.tealeaf.TeaLeaf;
import io.itch.padjokej.tealeaf.entity.ModBlockEntities;
import io.itch.padjokej.tealeaf.entity.TeapotBlockEntity;
import io.itch.padjokej.tealeaf.item.ModItems;
import io.itch.padjokej.tealeaf.registry.TagsRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class TeaPotBlock extends BlockWithEntity implements BlockEntityProvider
{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public TeaPotBlock(Settings settings)
    {
        super(settings);
    }



    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    /* BLOCK ENTITY */
    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (!world.isClient)
        {
            if(world.getBlockEntity(pos) instanceof TeapotBlockEntity teapotBlockEntity)
            {
                player.sendMessage(Text.of(String.valueOf(teapotBlockEntity.hasWater)));
                if(player.getStackInHand(hand).getItem() == Items.WATER_BUCKET && teapotBlockEntity.hasWater == 0)
                {
                    player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(Items.BUCKET)));
                    teapotBlockEntity.addWater();

                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ActionResult.SUCCESS;
                }
                if(player.getStackInHand(hand).getItem() == Items.BUCKET && teapotBlockEntity.hasWater == 1)
                {
                    player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(Items.WATER_BUCKET)));
                    teapotBlockEntity.removeWater();

                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ActionResult.SUCCESS;
                }
                if(player.getStackInHand(hand).getItem() == ModItems.TEA_CUP && teapotBlockEntity.teaResult > 0)
                {
                    switch (teapotBlockEntity.teaResult)
                    {
                        case 1: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.ACACIA_TEA)));
                            break;
                        case 2: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.BIRCH_TEA)));
                            break;
                        case 3: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.DARK_OAK_TEA)));
                            break;
                        case 4: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.JUNGLE_TEA)));
                            break;
                        case 5: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.MANGROVE_OAK_TEA)));
                            break;
                        case 6: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.OAK_TEA)));
                            break;
                        case 7: player.setStackInHand(hand, ItemUsage.exchangeStack(player.getStackInHand(hand), player, new ItemStack(ModItems.SPRUCE_TEA)));
                            break;
                    }

                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);

                    teapotBlockEntity.removeWater();
                    teapotBlockEntity.teaResult = 0;
                    teapotBlockEntity.teaType = 0;
                }
                boolean hasLeafItem = false;
                switch (player.getStackInHand(hand).getItem().toString())
                {
                    case "acacia_tea_leaf": teapotBlockEntity.addTealeaf(1);
                        hasLeafItem = true;
                        break;
                    case "birch_tea_leaf": teapotBlockEntity.addTealeaf(2);
                        hasLeafItem = true;
                        break;
                    case "dark_oak_tea_leaf": teapotBlockEntity.addTealeaf(3);
                        hasLeafItem = true;
                        break;
                    case "jungle_tea_leaf": teapotBlockEntity.addTealeaf(4);
                        hasLeafItem = true;
                        break;
                    case "mangrove_tea_leaf": teapotBlockEntity.addTealeaf(5);
                        hasLeafItem = true;
                        break;
                    case "oak_tea_leaf": teapotBlockEntity.addTealeaf(6);
                        hasLeafItem = true;
                        break;
                    case "spruce_tea_leaf": teapotBlockEntity.addTealeaf(7);
                        hasLeafItem = true;
                        break;
                }
                if (!player.getAbilities().creativeMode && hasLeafItem)
                {
                    player.getStackInHand(hand).decrement(1);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new TeapotBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return checkType(type, ModBlockEntities.TEAPOT, TeapotBlockEntity::tick);
    }

}
