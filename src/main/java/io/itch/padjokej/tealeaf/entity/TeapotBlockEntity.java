package io.itch.padjokej.tealeaf.entity;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.*;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
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
    public TeapotBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TEAPOT, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            @Override
            public int get(int index)
            {
                switch (index)
                {
                    case 0: return TeapotBlockEntity.this.level;
                    case 1: return TeapotBlockEntity.this.teaType;
                    case 2: return TeapotBlockEntity.this.boilTimer;
                    case 3: return TeapotBlockEntity.this.maxBoilTimer;
                    case 4: return TeapotBlockEntity.this.teaResult;
                    case 5: return TeapotBlockEntity.this.hasWater;
                    default: return 0;
                }
            }

            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0: TeapotBlockEntity.this.level = value; break;
                    case 1: TeapotBlockEntity.this.teaType = value; break;
                    case 2: TeapotBlockEntity.this.boilTimer = value; break;
                    case 3: TeapotBlockEntity.this.maxBoilTimer = value; break;
                    case 4: TeapotBlockEntity.this.teaResult = value; break;
                    case 5: TeapotBlockEntity.this.hasWater = value; break;
                }
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }


    @Override
    protected void writeNbt (NbtCompound nbt)
    {
        super.writeNbt(nbt);
        nbt.putInt("teapot.level", level);
        nbt.putInt("teapot.teaType", teaType);
        nbt.putInt("teapot.boilTimer", boilTimer);
        nbt.putInt("teapot.teaResult", teaResult);
        nbt.putInt("teapot.hasWater", hasWater);
    }
    @Override
    public void readNbt (NbtCompound nbt)
    {
        super.readNbt(nbt);
        level = nbt.getInt("teapot.level");
        teaType = nbt.getInt("teapot.teaType");
        boilTimer = nbt.getInt("teapot.boilTimer");
        teaResult = nbt.getInt("teapot.teaResult");
        hasWater = nbt.getInt("teapot.hasWater");

    }
    public void addWater ()
    {
        hasWater = 1;
    }
    public void removeWater ()
    {
        hasWater = 0;
    }
    public void addTealeaf (int type)
    {
        teaType = type;
    }
    public static PacketByteBuf sendParticlePacket(double x, double y, double z)
    {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        return buf;
    }

    public static boolean isHotBlock(World world, BlockPos pos) {
        BlockState blockStateBelow = world.getBlockState(pos.down());

        Block blockBelow = blockStateBelow.getBlock();

        if (blockBelow.equals(Blocks.MAGMA_BLOCK))
            return true;
        if (blockBelow instanceof CampfireBlock && CampfireBlock.isLitCampfire(blockStateBelow))
            return true;
        if (blockBelow instanceof AbstractFurnaceBlock && blockStateBelow.get(AbstractFurnaceBlock.LIT))
            return true;

        return false;
    }

    public static void tick(World world, BlockPos pos, BlockState state, TeapotBlockEntity entity)
    {
        if(!world.isClient)
        {
            if (entity.hasWater == 0)
            {
                entity.resetProgress();
                markDirty(world, pos, state);
                return;
            }

            if (entity.teaType > 0 && isHotBlock(world, pos))
            {
                entity.boilTimer++;
                markDirty(world, pos, state);
                if (entity.boilTimer >= entity.maxBoilTimer)
                {
                    entity.makeTea(entity.teaType);
                    if (world instanceof ServerWorld serverWorld) {
                        var oP = state.get(Properties.HORIZONTAL_FACING).getUnitVector();
                        oP.scale(-0.5f);
                        var particlePos = Vec3d.ofCenter(pos).add(new Vec3d(oP.getX(), oP.getY(), oP.getZ()));

                        serverWorld.spawnParticles(ParticleTypes.CLOUD, particlePos.getX(), particlePos.getY() + 0.5, particlePos.getZ(), 1, 0, .4, 0, 0f);
                    }

                    return;
                }
                if(world instanceof ServerWorld serverWorld)
                {
                    world.playSound(null, pos, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f);

                    var oP = state.get(Properties.HORIZONTAL_FACING).getUnitVector();
                    oP.scale(-0.5f);
                    var particlePos = Vec3d.ofCenter(pos).add(new Vec3d(oP.getX(), oP.getY(), oP.getZ()));

                    serverWorld.spawnParticles(ParticleTypes.SMOKE, particlePos.getX(), particlePos.getY() + 0.4, particlePos.getZ(), 1, 0, .2, 0, 0);
                }
            } else
            {
                entity.resetProgress();
                markDirty(world, pos, state);
            }
        }
    }

    private void makeTea(int type)
    {
        this.teaResult = type;
    }

    private void resetProgress()
    {
        this.boilTimer = 0;
    }

}
