package dev.padjokej.tealeaf.entity;

import dev.padjokej.tealeaf.registry.TagsRegistry;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeapotBlockEntity extends BlockEntity {

    protected final PropertyDelegate propertyDelegate;
    private int level;
    public int teaType;
    private int boilTimer;
    private int maxBoilTimer = 200;
    public int teaResult;
    public int hasWater;

    public TeapotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEAPOT, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TeapotBlockEntity.this.level;
                    case 1 -> TeapotBlockEntity.this.teaType;
                    case 2 -> TeapotBlockEntity.this.boilTimer;
                    case 3 -> TeapotBlockEntity.this.maxBoilTimer;
                    case 4 -> TeapotBlockEntity.this.teaResult;
                    case 5 -> TeapotBlockEntity.this.hasWater;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TeapotBlockEntity.this.level = value;
                    case 1 -> TeapotBlockEntity.this.teaType = value;
                    case 2 -> TeapotBlockEntity.this.boilTimer = value;
                    case 3 -> TeapotBlockEntity.this.maxBoilTimer = value;
                    case 4 -> TeapotBlockEntity.this.teaResult = value;
                    case 5 -> TeapotBlockEntity.this.hasWater = value;
                }
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("teapot.level", level);
        nbt.putInt("teapot.teaType", teaType);
        nbt.putInt("teapot.boilTimer", boilTimer);
        nbt.putInt("teapot.teaResult", teaResult);
        nbt.putInt("teapot.hasWater", hasWater);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        level = nbt.getInt("teapot.level").orElse(0);
        teaType = nbt.getInt("teapot.teaType").orElse(0);
        boilTimer = nbt.getInt("teapot.boilTimer").orElse(0);
        teaResult = nbt.getInt("teapot.teaResult").orElse(0);
        hasWater = nbt.getInt("teapot.hasWater").orElse(0);
    }

    public void addWater() {
        hasWater = 1;
    }

    public void removeWater() {
        hasWater = 0;
    }

    public void addTealeaf(int type) {
        teaType = type;
    }

    public static boolean isHotBlock(World world, BlockPos pos) {
        BlockState blockStateBelow = world.getBlockState(pos.down());

        Block blockBelow = blockStateBelow.getBlock();

        if (blockStateBelow.isIn(TagsRegistry.HEAT_SOURCES)) {
            if (blockStateBelow.contains(Properties.LIT))
                return blockStateBelow.get(Properties.LIT);
            return true;
        }
        return blockBelow instanceof AbstractFurnaceBlock && blockStateBelow.get(AbstractFurnaceBlock.LIT);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TeapotBlockEntity entity) {
        if (!world.isClient) {
            if (entity.hasWater == 0) {
                entity.resetProgress();
                markDirty(world, pos, state);
                return;
            }

            if (entity.teaType > 0 && isHotBlock(world, pos)) {
                entity.boilTimer++;
                markDirty(world, pos, state);
                var part = ParticleTypes.SMOKE;
                if (entity.boilTimer >= entity.maxBoilTimer) {
                    entity.makeTea(entity.teaType);
                    part = ParticleTypes.CLOUD;
                } else if (world instanceof ServerWorld) {
                    world.playSound(null, pos, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }

                if (world instanceof ServerWorld serverWorld) {
                    var oP = state.get(Properties.HORIZONTAL_FACING);

                    var particlePos = Vec3d.ofCenter(pos).add(
                            new Vec3d(oP.getOffsetX(), oP.getOffsetY(), oP.getOffsetZ())
                                    .multiply(-0.5));

                    serverWorld.spawnParticles(part, particlePos.getX(), particlePos.getY() + 0.4, particlePos.getZ(), 1, 0, .2, 0, 0);
                }
            } else {
                entity.resetProgress();
                markDirty(world, pos, state);
            }
        }
    }

    private void makeTea(int type) {
        this.teaResult = type;
    }

    private void resetProgress() {
        this.boilTimer = 0;
    }

}
