package com.terraformersmc.terraform.shapes.impl.validator;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.validator.AllMeetValidator;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.IWorldGenerationBaseReader;

import java.util.Arrays;
import java.util.List;

/**
 * @author <Wtoll> Will Toll on 2020-06-25
 * @project Shapes
 */
public class SafelistValidator extends AllMeetValidator {

    private final List<BlockState> safeStates;
    private final IWorldGenerationBaseReader IWorldGenerationBaseReader;

    public SafelistValidator(IWorldGenerationBaseReader world, List<BlockState> safeStates) {
        this.safeStates = safeStates;
        this.IWorldGenerationBaseReader = world;
    }

    public SafelistValidator(IWorldGenerationBaseReader world, BlockState ...safeStates) {
        this(world, Arrays.asList(safeStates));
    }

    public static SafelistValidator of(IWorldGenerationBaseReader world, List<BlockState> safeStates) {
        return new SafelistValidator(world, safeStates);
    }

    public static SafelistValidator of(IWorldGenerationBaseReader world, BlockState ...safeStates) {
        return new SafelistValidator(world, safeStates);
    }

    @Override
    public boolean test(Position position) {
        return IWorldGenerationBaseReader.isStateAtPosition(position.toBlockPos(), (state) -> state.isAir() || safeStates.contains(state));
    }
}
