package com.terraformersmc.terraform.shapes.impl.validator;

import com.terraformersmc.terraform.shapes.api.validator.AllMeetValidator;
import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.IWorldReader;

/**
 * @author <Wtoll> Will Toll on 2020-06-07
 * @project Shapes
 */
public class AirValidator extends AllMeetValidator {

    private final IWorldReader IWorldReader;
    private final IWorldGenerationBaseReader IWorldGenerationBaseReader;

    public AirValidator(IWorldReader world) {
        this.IWorldReader = world;
        this.IWorldGenerationBaseReader = null;
    }

    public AirValidator(IWorldGenerationBaseReader world) {
        this.IWorldReader = null;
        this.IWorldGenerationBaseReader = world;
    }

    public static AirValidator of(IWorldReader world) {
        return new AirValidator(world);
    }

    public static AirValidator of(IWorldGenerationBaseReader world) {
        return new AirValidator(world);
    }

    @Override
    public boolean test(Position position) {
        if (IWorldReader != null) {
            return IWorldReader.isEmptyBlock(position.toBlockPos());
        } else if (IWorldGenerationBaseReader != null) {
            return IWorldGenerationBaseReader.isStateAtPosition(position.toBlockPos(), (state) -> state.isAir());
        } else {
            return false;
        }
    }
}
