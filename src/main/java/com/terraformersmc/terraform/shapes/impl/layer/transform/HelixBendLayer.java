package com.terraformersmc.terraform.shapes.impl.layer.transform;

import java.util.function.Predicate;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.api.layer.Layer;

public class HelixBendLayer implements Layer {

	private final double height;
	private final double radius;

	public HelixBendLayer(double height, double radius) {
		this.height = height;
		this.radius = radius;
	}

	public static HelixBendLayer of(double height, double radius) {
		return new HelixBendLayer(height, radius);
	}

	@Override
	public Position modifyMax(Shape shape) {
		Position max = shape.max();

		max.setX(max.getX() + radius);
		max.setZ(max.getZ() + radius);

		return max;
	}

	@Override
	public Position modifyMin(Shape shape) {
		Position min = shape.min();

		min.setX(min.getX() - radius);
		min.setZ(min.getZ() - radius);

		return min;
	}

	@Override
	public Predicate<Position> modifyEquation(Shape shape) {
		return (pos) -> {

			double turn = getTurn(pos);

			pos.setX(pos.getX() + (Math.cos(Math.toRadians(turn)) * radius));
			pos.setZ(pos.getZ() + (Math.sin(Math.toRadians(turn)) * radius));

			return shape.equation().test(pos);
		};
	}

	protected double getTurn(Position pos) {
		double turn = ((pos.getY() % height) / height) * 360;
		return turn;
	}

}
