package cop.swing.controls.layouts;

import cop.swing.panels.CreateFactory;

import javax.swing.Box;
import java.awt.Component;

/**
 * @author Oleg Cherednik
 * @since 15.11.2012
 */
public enum LayoutNode implements CreateFactory<Component> {
	GLUE,
	V_GLUE {
		@Override
		public Component create() {
			return Box.createVerticalGlue();
		}
	},
	H_GLUE {
		@Override
		public Component create() {
			return Box.createHorizontalGlue();
		}
	};

	// ========== CreateFactory ==========

	@Override
	public Component create() {
		return null;
	}
}
