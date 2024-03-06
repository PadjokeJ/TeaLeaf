package io.itch.padjokej.tealeaf.entity;

import io.itch.padjokej.tealeaf.TeaLeaf;
import io.itch.padjokej.tealeaf.registry.PacketsRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class TeapotBlockEntity extends BlockEntity {

    protected final PropertyDelegate propertyDelegate;
    private int level;
    public int teaType;
    private int boilTimer;
    private int maxBoilTimer = 100;
    private int teaResult;
    public int hasWater;
    public TeapotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEAPOT, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
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
    public void addTealeaf (String item)
    {
        switch (item)
        {
            case "acacia_tea_leaf": teaType = 1;
            break;
            case "birch_tea_leaf": teaType = 2;
            break;
            case "dark_oak_tea_leaf": teaType = 3;
            break;
            case "jungle_tea_leaf": teaType = 4;
            break;
            case "mangrove_tea_leaf": teaType = 5;
            break;
            case "oak_tea_leaf": teaType = 6;
            break;
            case "spruce_tea_leaf": teaType = 7;
            break;
        }

    }
    static PacketByteBuf sendParticlePacket(Double x, Double y, Double z)
    {
        var buf = PacketByteBufs.create();
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        return buf;
    }
    public static void tick(World world, BlockPos pos, BlockState state, TeapotBlockEntity entity)
    {
        if(world.isClient())
        {
            return;
        }
        if(!world.isClient) {
            if (entity.hasWater == 0)
            {
                entity.resetProgress();
                markDirty(world, pos, state);
                return;
            }

            if (entity.teaType > 0) {
                /*world.addParticle(ParticleTypes.SMOKE, (double)pos.getX(), (double)pos.getY() + 0.4, (double)pos.getZ(), 0.0, 0.005, 0.0);*/
                entity.boilTimer++;
                markDirty(world, pos, state);
                if (entity.boilTimer >= entity.maxBoilTimer) {
                    entity.makeTea(entity.teaType);
                }
                for (ServerPlayerEntity player : PlayerLookup.tracking(entity))
                {
                    ServerPlayNetworking.send(player, PacketsRegistry.BOIL_PARTICLE_PACKET, sendParticlePacket((double)pos.getX(), (double)pos.getY() + 0.4, (double)pos.getZ()));
                }

            } else {
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
