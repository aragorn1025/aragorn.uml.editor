package aragorn.uml.editor.gui.mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.util.MathVector2D;

public class SelectMode extends UmlMode {

	public SelectMode(UmlCanvas parent) {
		super(parent);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		getParent().clearSelectedUmlBasicObjects();
		for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
			if (getParent().getUmlBasicObject(i).isSurround(event.getPoint())) {
				getParent().addSelectedUmlBasicObjects(getParent().getUmlBasicObject(i));
			}
		}
		getParent().repaint();
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if (getMousePressedPoint() == null)
			throw new InternalError("Unknown error.");
		if (getStartingObject() != null) {
			Point2D.Double starting_point = new Point2D.Double(getMousePressedPoint().getX(), getMousePressedPoint().getY());
			Point2D.Double ending_point = new Point2D.Double(event.getPoint().getX(), event.getPoint().getY());
			Point2D.Double new_location = MathVector2D.add(getMousePressedObjectInitialLocation(), new MathVector2D(starting_point, ending_point));
			getStartingObject().setLocation(new_location.getX(), new_location.getY());
		} else {
			// set dragged block
			double min_x = Math.min(getMousePressedPoint().getX(), event.getPoint().getX());
			double min_y = Math.min(getMousePressedPoint().getY(), event.getPoint().getY());
			double max_x = Math.max(getMousePressedPoint().getX(), event.getPoint().getX());
			double max_y = Math.max(getMousePressedPoint().getY(), event.getPoint().getY());
			getParent().setDraggedBlock(new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y));
		}
		getParent().repaint();
	}

	@Override
	public void mousePressed(MouseEvent event) {
		defaultMousePressed(event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (getParent().getSelectedUmlObjectsNumber() == 1 && getParent().getSelectedUmlBasicObject(0).isSurround(event.getPoint())) {

		} else {
			getParent().setDraggedBlock(null);
			Point mouse_released_point = event.getPoint();
			if (mouse_released_point.equals(getMousePressedPoint())) {
				resetPressedReleased();
				return;
			}
			getParent().clearSelectedUmlBasicObjects();
			double min_x = Math.min(getMousePressedPoint().getX(), mouse_released_point.getX());
			double min_y = Math.min(getMousePressedPoint().getY(), mouse_released_point.getY());
			double max_x = Math.max(getMousePressedPoint().getX(), mouse_released_point.getX());
			double max_y = Math.max(getMousePressedPoint().getY(), mouse_released_point.getY());
			Rectangle2D.Double bounds = new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y);
			for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
				if (getParent().getUmlBasicObject(i).isSurroundedBy(bounds)) {
					getParent().addSelectedUmlBasicObjects(getParent().getUmlBasicObject(i));
				}
			}
			resetPressedReleased();
			getParent().repaint();
		}
	}
}