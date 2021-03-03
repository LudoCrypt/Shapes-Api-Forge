package com.terraformersmc.terraform.shapes.impl.layer.transform;

import java.util.function.Predicate;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.api.layer.Layer;

public class BendLayer implements Layer {

	private final double arc;
	private final double height;

	public BendLayer(double arc, double height) {
		this.arc = arc;
		this.height = height;
	}

	public static BendLayer of(double arc, double height) {
		return new BendLayer(arc, height);
	}

	@Override
	public Position modifyMax(Shape shape) {
		Position pos = shape.max();
		double dist = getDist(pos);
		pos.setX(pos.getX() + dist + height);
		pos.setY(pos.getY() + dist + height);
		pos.setZ(pos.getZ() + dist + height);
		return pos;
	}

	@Override
	public Position modifyMin(Shape shape) {
		Position pos = shape.min();
		double dist = getDist(pos);
		pos.setX(pos.getX() - dist + height);
		pos.setY(pos.getY() - dist + height);
		pos.setZ(pos.getZ() - dist + height);
		return pos;
	}

	@Override
	public Predicate<Position> modifyEquation(Shape shape) {
		return (pos) -> {
			double dist = getDist(pos);
			return new RotateLayer(Quaternion.of(0, 0, arc * (dist / height), true)).modifyEquation(shape).test(pos);
		};
	}

	private static double getDist(Position pos) {
		return Math.sqrt(pos.getX() * pos.getX() + pos.getY() + pos.getY() + pos.getZ() + pos.getZ());
	}

}
