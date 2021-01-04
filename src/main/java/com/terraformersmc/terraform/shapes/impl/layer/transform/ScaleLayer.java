package com.terraformersmc.terraform.shapes.impl.layer.transform;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.layer.TransformationLayer;

/**
 * @author <LudoCrypt> Will Toll on 2021-01-04
 * @project Shapes
 */
public class ScaleLayer extends TransformationLayer {

    private final double scale;

    public ScaleLayer(double scale) {
        this.scale = scale;
    }

    public static ScaleLayer of(double scale) {
        return new ScaleLayer(scale);
    }

    @Override
    public Position transform(Position pos) {
        pos.setX(pos.getX() * this.scale);
        pos.setY(pos.getX() * this.scale);
        pos.setZ(pos.getX() * this.scale);
        return pos;
    }

    @Override
    public Position inverseTransform(Position pos) {
        pos.setX(pos.getX() / this.scale);
        pos.setY(pos.getX() / this.scale);
        pos.setZ(pos.getX() / this.scale);
        return pos;
    }
}
