package com.terraformersmc.terraform.shapes.impl.filler;

import com.terraformersmc.terraform.shapes.api.Filler;
import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.block.BlockState;
import net.minecraft.world.IWorldWriter;

import java.util.Random;

/**
 * @author <Wtoll> Will Toll on 2020-06-16
 * @project Shapes
 */
public class RandomSimpleFiller implements Filler {

    private final IWorldWriter world;
    private final BlockState state;
    private final int flags;
    private final Random random;
    private final float probability;

    public RandomSimpleFiller(IWorldWriter world, BlockState state, int flags, Random random, float probability) {
        this.world = world;
        this.state = state;
        this.flags = flags;
        this.random = random;
        this.probability = probability;
    }

    public RandomSimpleFiller(IWorldWriter world, BlockState state, Random random, float probability) {
        this(world, state, 3, random, probability);
    }

    public static RandomSimpleFiller of(IWorldWriter world, BlockState state, int flags, Random random, float proability) {
        return new RandomSimpleFiller(world, state, flags, random, proability);
    }

    public static RandomSimpleFiller of(IWorldWriter world, BlockState state, Random random, float proability) {
        return new RandomSimpleFiller(world, state, random, proability);
    }

    @Override
    public void accept(Position position) {
        if (this.random.nextFloat() < this.probability) {
            world.setBlockState(position.toBlockPos(), this.state, this.flags);
        }
    }
}
