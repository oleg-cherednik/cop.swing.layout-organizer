package cop.swing.controls.layouts;

import cop.swing.controls.Node;

import javax.swing.Box;
import java.awt.Component;

/**
 * @author Oleg Cherednik
 * @since 15.11.2012
 */
public enum LayoutNode implements Node {
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

    public String getText(Component obj) {
        return null;
    }

    public void setText(Component obj, String text) {
    }

    public void doAction(Component obj) {
    }

    // ========== CreateFactory ==========

    @Override
    public Component create() {
        return null;
    }

    // ========== KeyId ==========

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean check(String id) {
        return false;
    }
}
