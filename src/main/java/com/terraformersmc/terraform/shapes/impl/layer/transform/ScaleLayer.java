package com.terraformersmc.terraform.shapes.impl.layer.transform;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.layer.TransformationLayer;

/**
 * @author <LudoCrypt> LudoCrypt on 2021-01-04
 * @project Shapes
 */
public class ScaleLayer extends TransformationLayer {

    private final double Xscale;
    private final double Yscale;
    private final double Zscale;

    public ScaleLayer(double Xscale, double Yscale, double Zscale) {
        this.Xscale = Xscale;
        this.Yscale = Yscale;
        this.Zscale = Zscale;
    }

    public static ScaleLayer of(double Xscale, double Yscale, double Zscale) {
        return new ScaleLayer(Xscale, Yscale, Zscale);
    }

    public static ScaleLayer of(double scale) {
        return new ScaleLayer(scale, scale, scale);
    }

    @Override
    public Position transform(Position pos) {
        pos.setX(pos.getX() * this.Xscale);
        pos.setY(pos.getY() * this.Yscale);
        pos.setZ(pos.getZ() * this.Zscale);
        return pos;
    }

    @Override
    public Position inverseTransform(Position pos) {
        pos.setX(pos.getX() / this.Xscale);
        pos.setY(pos.getY() / this.Yscale);
        pos.setZ(pos.getZ() / this.Zscale);
        return pos;
    }
}
