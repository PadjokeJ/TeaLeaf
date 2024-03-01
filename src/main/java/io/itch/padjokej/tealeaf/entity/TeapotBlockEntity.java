package io.itch.padjokej.tealeaf.entity;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeapotBlockEntity extends BlockEntity {

    protected final PropertyDelegate propertyDelegate;
    private int level;
    private int teaType;
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



    protected void writeNbt (NbtCompound nbt)
    {
        super.writeNbt(nbt);
        nbt.putInt("teapot.level", level);
        nbt.putInt("teapot.teaType", teaType);
        nbt.putInt("teapot.boilTimer", boilTimer);
        nbt.putInt("teapot.teaResult", teaResult);
        nbt.putInt("teapot.hasWater", hasWater);
    }
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
    public void addTealeaf (Item item)
    {
        if(item.getName() == Text.of("acacia_tealeaf"))
        {
            teaType = 1;
        }
        if(item.getName() == Text.of("birch_tealeaf"))
        {
            teaType = 2;
        }
        if(item.getName() == Text.of("dark_oak_tealeaf"))
        {
            teaType = 3;
        }
        if(item.getName() == Text.of("jungle_tealeaf"))
        {
            teaType = 4;
        }
        if(item.getName() == Text.of("mangrove_tealeaf"))
        {
            teaType = 5;
        }
        if(item.getName() == Text.of("oak_tealeaf"))
        {
            teaType = 6;
        }
        if(item.getName() == Text.of("spruce_tealeaf"))
        {
            teaType = 7;
        }

    }

    public static void tick(World world, BlockPos pos, BlockState state, TeapotBlockEntity entity)
    {
        if(world.isClient())
        {
            if(entity.hasWater == 0)
            {
                return;
            }
            if(entity.teaType > 0) {
                if (entity.boilTimer >= entity.maxBoilTimer)
                {
                    return;
                }
                world.addParticle(ParticleTypes.SMOKE, (double)pos.getX(), (double)pos.getY() + 0.4, (double)pos.getZ(), 0.0, 0.005, 0.0);
            }
            return;
        }
        if(entity.hasWater == 0)
        {
            entity.resetProgress();
            markDirty(world, pos, state);
            return;
        }
        if(entity.teaType > 0)
        {
            /*world.addParticle(ParticleTypes.SMOKE, (double)pos.getX(), (double)pos.getY() + 0.4, (double)pos.getZ(), 0.0, 0.005, 0.0);*/
            entity.boilTimer++;
            markDirty(world, pos, state);
            if(entity.boilTimer >= entity.maxBoilTimer)
            {
                entity.makeTea(entity.teaType);
            }

        }else
        {
            entity.resetProgress();
            markDirty(world, pos, state);
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
