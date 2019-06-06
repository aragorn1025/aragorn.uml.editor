package aragorn.uml.editor.gui.mode;

import java.awt.event.MouseEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.line.CompositionLine;

public class CompositionLineMode extends UmlMode {

	public CompositionLineMode(UmlCanvas parent) {
		super(parent);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		defaultMousePressed(event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		defaultMouseReleased(event);
		if (!isUmlConnectLineShouldBeSet())
			return;
		getParent().addUmlConnectionLine(new CompositionLine(getStartingObject(), getStartingConnectionPort(), getEndingObject(), getEndingConnectionPort()));
	}
}