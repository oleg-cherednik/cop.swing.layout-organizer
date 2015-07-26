package cop.swing.controls.layouts;

import cop.swing.panels.CreateFactory;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

/**
 * This manager organizes all given components into one single line.
 *
 * @author Oleg Cherednik
 * @since 01.10.2010
 */
public class SingleRowLayout extends AbstractLayoutOrganizer {
    private int space;
    private int alignment = GridBagConstraints.CENTER;

    public void setSpace(int space) {
        this.space = space;
    }

    /**
     * <ul>
     * Available constants:
     * <li>{@link SwingConstants#CENTER} - default</li>
     * <li>{@link SwingConstants#WEST}</li>
     * <li>{@link SwingConstants#EAST}</li>
     * <li>{@link SwingConstants#LEADING}, equals with {@link SwingConstants#WEST}</li>
     * <li>{@link SwingConstants#TRAILING}, equals with {@link SwingConstants#EAST}</li>
     * </ul>
     */
    public void setAlignment(int alignment) {
        if (alignment == SwingConstants.CENTER)
            this.alignment = GridBagConstraints.CENTER;
        else if (alignment == SwingConstants.WEST || alignment == SwingConstants.LEADING)
            this.alignment = GridBagConstraints.WEST;
        else if (alignment == SwingConstants.EAST || alignment == SwingConstants.TRAILING)
            this.alignment = GridBagConstraints.EAST;
    }

    // ========== LayoutOrganizer ==========

    @Override
    public CreateFactory<Component> modifyNode(CreateFactory<Component> node) {
        if (node == LayoutNode.GLUE || node == LayoutNode.H_GLUE || node == LayoutNode.V_GLUE)
            return LayoutNode.H_GLUE;
        return node;
    }

    // ========== AbstractLayoutOrganizer ==========

    @Override
    protected List<Component> addAlignmentGlue(List<Component> components) {
        if (alignment == GridBagConstraints.WEST) {
            removeLocalGlues(components);
            components.add(Glue.createHorizontal());
        } else if (alignment == GridBagConstraints.EAST) {
            removeLocalGlues(components);

            List<Component> tmp = new ArrayList<>(components);
            components.clear();
            components.add(Glue.createHorizontal());
            components.addAll(tmp);
        } else if (alignment == GridBagConstraints.CENTER)
            removeLocalGlues(components);

        return components;
    }

    @Override
    protected void add(Container container, Component component, GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0;

        if (component instanceof Box.Filler || component instanceof Box)
            gbc.weightx = 1;
        if (component instanceof JLabel || (component instanceof JTextField && ((JTextField)component).getColumns() == 0)) {
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
        }

        container.add(component, gbc);

        if (gbc.insets.left != space)
            gbc.insets.left = space;
    }

    @Override
    protected GridBagConstraints createConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;

        return gbc;
    }
}

