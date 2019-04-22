package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;

public class UmlUseCase extends UmlBasicObject {

	public static final Paintable BUTTON_ICON = new UmlUseCase(0, 1, 8, 6, 0); // TODO depth should be checked

	public static final String NAME = "use case";

	/** The default size of the object. It is strongly recommend to set the width and height as the multiple of 2. */
	private final static Dimension DEFAULT_SIZE = new Dimension(84, 48);

	private UmlUseCase(double x, double y, double width, double height, int depth) {
		super(x, y, width, height, depth);
		setConnectionPortIconHorizontalGap(width / 2 * (1 - Math.sqrt(1 - Math.pow(getConnectionPortLength() / height, 2))));
		setConnectionPortIconVerticalGap(height / 2 * (1 - Math.sqrt(1 - Math.pow(getConnectionPortLength() / width, 2))));
	}

	public UmlUseCase(Point point, int depth) {
		this(point.getX(), point.getY(), UmlUseCase.DEFAULT_SIZE.getWidth(), UmlUseCase.DEFAULT_SIZE.getHeight(), depth);
	}

	@Override
	public void drawBackground(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		Paintable.fillOval(g, c, new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	protected void drawBody(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		Paintable.drawOval(g, c, new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	protected boolean isSurround(Point2D.Double point) {
		Rectangle2D.Double bounds = getBounds();
		Oval icon = new Oval(new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()), bounds.getWidth(), bounds.getHeight());
		return icon.isSurround(point);
	}
}