package aragorn.xml.editor.objects;

import java.awt.Point;
import java.awt.geom.Point2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;
import aragorn.util.MathVector2D;

public class UmlGeneralizationLine extends UmlConnectionLine {

	public static final Paintable BUTTON_ICON = new Polyline2D(new Point2D.Double(8, 4), new Point2D.Double(2, 4), new Point2D.Double(2, 2), new Point2D.Double(0, 4),
			new Point2D.Double(2, 6), new Point2D.Double(2, 4));

	public static final String NAME = "generalization line";

	protected UmlGeneralizationLine(Point starting_point, Point ending_point, UmlConnectionPort starting_connection_port, UmlConnectionPort ending_connection_port) {
		super(starting_point, ending_point, starting_connection_port, ending_connection_port);
	}

	@Override
	protected Paintable getEndArrow(MathVector2D parallel_vector, MathVector2D normal_vector) {
		MathVector2D negative_parallel_vector = parallel_vector.getNegative();
		MathVector2D negative_parallel_vector_quarter = negative_parallel_vector.getScalarMultiply(0.25);
		MathVector2D normal_vector_half = normal_vector.getScalarMultiply(0.5);

		Polyline2D val = new Polyline2D();
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector));
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_quarter));
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_quarter, normal_vector_half));
		val.addPoint(getEndingPoint());
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_quarter, normal_vector_half.getNegative()));
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_quarter));
		return val;
	}
}