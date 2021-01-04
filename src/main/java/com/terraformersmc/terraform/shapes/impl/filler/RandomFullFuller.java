package com.terraformersmc.terraform.shapes.impl.filler;

import com.terraformersmc.terraform.shapes.api.Filler;
import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.block.BlockState;
import net.minecraft.world.IWorldWriter;

import java.util.List;
import java.util.Random;

/**
 * @author <LudoCrypt> LudoCrypt on 2021-01-04
 * @project Shapes
 */
public class RandomFullFuller implements Filler {

    private final IWorldWriter world;
    private final List<BlockState> state;
    private final Random random;
    private final int flags;

    public RandomFullFuller(IWorldWriter world, List<BlockState> state, int flags, Random random) {
        this.world = world;
        this.state = state;
        this.flags = flags;
        this.random = random;
    }

    public RandomFullFuller(IWorldWriter world, List<BlockState> state, Random random) {
        this(world, state, 3, random);
    }

    public static RandomFullFuller of(IWorldWriter world, List<BlockState> state, int flags, Random random) {
        return new RandomFullFuller(world, state, flags, random);
    }

    public static RandomFullFuller of(IWorldWriter world, List<BlockState> state, Random random) {
        return new RandomFullFuller(world, state, random);
    }

    @Override
    public void accept(Position position) {
        world.setBlockState(position.toBlockPos(), this.state.get(random.nextInt(this.state.size())), this.flags);
    }
}
