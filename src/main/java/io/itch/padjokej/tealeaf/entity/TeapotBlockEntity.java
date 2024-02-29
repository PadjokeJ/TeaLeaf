package io.itch.padjokej.tealeaf.entity;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
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
    }
    public void readNbt (NbtCompound nbt)
    {
        super.readNbt(nbt);
        level = nbt.getInt("teapot.level");
        teaType = nbt.getInt("teapot.teaType");
        boilTimer = nbt.getInt("teapot.boilTimer");
        teaResult = nbt.getInt("teapot.teaResult");
    }
    public static void tick(World world, BlockPos pos, BlockState state, TeapotBlockEntity entity)
    {
        if(world.isClient())
        {
            return;
        }
        if(entity.teaType > 0)
        {
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
