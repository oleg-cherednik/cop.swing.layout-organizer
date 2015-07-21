package cop.swing.controls.layouts;

import javax.swing.Box;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 30.11.2012
 */
public abstract class AbstractLayoutOrganizer implements LayoutOrganizer {
    protected abstract GridBagConstraints createConstraints();

    protected abstract void add(Container container, Component component, GridBagConstraints gbc);

    protected List<Component> addAlignmentGlue(List<Component> components) {
        return components;
    }

    // ========== LayoutOrganizer ==========

    @Override
    public void update(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createConstraints();

        List<Component> components = new ArrayList<>(Arrays.asList(container.getComponents()));
        container.removeAll();

        for (Component component : addAlignmentGlue(components))
            add(container, component, gbc);
    }

    // ========== static ==========

    protected static List<Component> removeLocalGlues(List<Component> components) {
        Iterator<Component> it = components.iterator();

        while (it.hasNext()) {
            if (it.next() instanceof Glue)
                it.remove();
        }

        return components;
    }

    // ========== Glue ==========

    protected static final class Glue extends Box.Filler {
        public static Glue createHorizontal() {
            return new Glue(Short.MAX_VALUE, 0);
        }

        public static Glue createVertical() {
            return new Glue(0, Short.MAX_VALUE);
        }

        private Glue(int width, int height) {
            super(new Dimension(0, 0), new Dimension(0, 0), new Dimension(width, height));
        }
    }
}
