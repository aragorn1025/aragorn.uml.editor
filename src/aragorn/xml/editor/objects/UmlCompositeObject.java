package aragorn.xml.editor.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import aragorn.math.geometry.Coordinate2D;

public class UmlCompositeObject extends UmlBasicObject {

	private UmlBasicObject[] sub_objects;

	/** Do shallow copy for copy UML objects. */
	public UmlCompositeObject(ArrayList<UmlBasicObject> sub_objects) {
		super();
		if (sub_objects == null)
			throw new NullPointerException("The sub-objects should not be null.");
		if (sub_objects.size() == 0)
			throw new NullPointerException("The sub-objects should not be nothing in.");
		this.sub_objects = new UmlBasicObject[sub_objects.size()];
		for (int i = 0; i < this.sub_objects.length; i++) {
			this.sub_objects[i] = sub_objects.get(i);
		}
		Arrays.sort(this.sub_objects); // TODO

		double x_min = this.sub_objects[0].getBounds().getMinX();
		double x_max = this.sub_objects[0].getBounds().getMaxX();
		double y_min = this.sub_objects[0].getBounds().getMinY();
		double y_max = this.sub_objects[0].getBounds().getMaxY();
		int depth_max = this.sub_objects[0].getDepth();
		for (int i = 1; i < this.sub_objects.length; i++) {
			x_min = Math.min(x_min, this.sub_objects[i].getBounds().getMinX());
			x_max = Math.max(x_max, this.sub_objects[i].getBounds().getMaxX());
			y_min = Math.min(y_min, this.sub_objects[i].getBounds().getMinY());
			y_max = Math.max(y_max, this.sub_objects[i].getBounds().getMaxY());
			depth_max = Math.max(depth_max, this.sub_objects[i].getDepth());
		}
		setLocation(x_min, y_min);
		setSize(x_max - x_min, y_max - y_min);
		setDepth(depth_max);
	}

	@Override
	public void drawBackground(Graphics g, Coordinate2D c) {
		// Do nothing.
	}

	@Override
	protected void drawBody(Graphics g, Coordinate2D c) {
		Arrays.sort(sub_objects);
		for (UmlBasicObject sub_object : sub_objects) {
			g.setColor(Color.BLACK);
			sub_object.drawBackground(g, null);
			g.setColor(Color.WHITE);
			sub_object.draw(g, null);
		}
	}

	public UmlBasicObject[] getSubObjects() {
		return sub_objects;
	}

	@Override
	protected boolean isSelected() {
		return sub_objects[0].isSelected();
	}

	@Override
	protected boolean isSurround(Point2D.Double point) {
		for (UmlBasicObject sub_object : sub_objects) {
			if (sub_object.isSurround(point)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUngroupable() {
		return true;
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(false);
		for (UmlBasicObject sub_object : sub_objects) {
			sub_object.setSelected(selected);
		}
	}
}