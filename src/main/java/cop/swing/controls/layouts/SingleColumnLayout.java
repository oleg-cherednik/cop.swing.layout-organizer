package cop.swing.controls.layouts;

import cop.swing.panels.CreateFactory;

import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

/**
 * This manager organizes all given components into one single column.
 *
 * @author Oleg Cherednik
 * @since 15.11.2012
 */
public class SingleColumnLayout extends AbstractLayoutOrganizer {
    private int space;
    private int alignment = GridBagConstraints.CENTER;

    public void setSpace(int space) {
        this.space = space;
    }

    /**
     * <ul>
     * Available constants:
     * <li>{@link SwingConstants#CENTER} - default</li>
     * <li>{@link SwingConstants#NORTH}</li>
     * <li>{@link SwingConstants#SOUTH}</li>
     * <li>{@link SwingConstants#LEADING}, equals with {@link SwingConstants#NORTH}</li>
     * <li>{@link SwingConstants#TRAILING}, equals with {@link SwingConstants#SOUTH}</li>
     * </ul>
     */
    public void setAlignment(int alignment) {
        if (alignment == SwingConstants.CENTER)
            this.alignment = GridBagConstraints.CENTER;
        else if (alignment == SwingConstants.NORTH || alignment == SwingConstants.LEADING)
            this.alignment = GridBagConstraints.NORTH;
        else if (alignment == SwingConstants.SOUTH || alignment == SwingConstants.TRAILING)
            this.alignment = GridBagConstraints.SOUTH;
    }

    // ========== LayoutOrganizer ==========

    @Override
    public CreateFactory<Component> modifyNode(CreateFactory<Component> node) {
        if (node == LayoutNode.GLUE || node == LayoutNode.H_GLUE || node == LayoutNode.V_GLUE)
            return LayoutNode.V_GLUE;
        return node;
    }

    // ========== AbstractLayoutOrganizer ==========

    @Override
    protected List<Component> addAlignmentGlue(List<Component> components) {
        if (alignment == GridBagConstraints.NORTH) {
            removeLocalGlues(components);
            components.add(Glue.createVertical());
        } else if (alignment == GridBagConstraints.SOUTH) {
            removeLocalGlues(components);

            List<Component> tmp = new ArrayList<>(components);
            components.clear();
            components.add(Glue.createVertical());
            components.addAll(tmp);
        } else if (alignment == GridBagConstraints.CENTER)
            removeLocalGlues(components);

        return components;
    }

    @Override
    protected void add(Container container, Component component, GridBagConstraints gbc) {
        gbc.weighty = component instanceof Box.Filler || component instanceof Box ? 1 : 0;
        container.add(component, gbc);

        if (gbc.insets.top != space)
            gbc.insets.top = space;
    }

    @Override
    protected GridBagConstraints createConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;

        return gbc;
    }
}
