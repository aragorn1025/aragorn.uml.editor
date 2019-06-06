package aragorn.uml.editor.gui.mode;

import java.awt.event.MouseEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.basic.UseCase;

public class UseCaseMode extends UmlMode {

	public UseCaseMode(UmlCanvas parent) {
		super(parent);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		getParent().addUmlBasicObject(new UseCase(event.getPoint()));
	}
}