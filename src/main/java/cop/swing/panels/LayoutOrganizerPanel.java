package cop.swing.panels;

import cop.swing.controls.layouts.LayoutOrganizer;
import cop.swing.controls.layouts.SingleColumnLayout;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Collection;

/**
 * This panel is just {@link JPanel} decorator with {@link LayoutOrganizer} and after add components they will be
 * automatically reorganized according to the current {@link LayoutOrganizer}.
 *
 * @author Oleg Cherednik
 * @since 23.11.2012
 */
public class LayoutOrganizerPanel extends JPanel {
    private int preferredWidth;
    protected LayoutOrganizer layoutOrganizer;

    public LayoutOrganizerPanel() {
        this(null);
    }

    public LayoutOrganizerPanel(LayoutOrganizer layoutOrganizer) {
        this.layoutOrganizer = layoutOrganizer != null ? layoutOrganizer : new SingleColumnLayout();
    }

    public void setLayoutOrganizer(LayoutOrganizer layoutOrganizer) {
        if (this.layoutOrganizer == layoutOrganizer || layoutOrganizer == null)
            return;

        this.layoutOrganizer = layoutOrganizer;
        updateUI();
    }

    public LayoutOrganizer getLayoutOrganizer() {
        return layoutOrganizer;
    }

    public void addComp(Component... components) {
        if (ArrayUtils.isNotEmpty(components))
            addComp(Arrays.asList(components));
    }

    public void addComp(Collection<? extends Component> components) {
        if (CollectionUtils.isEmpty(components))
            return;

        for (Component comp : components)
            super.add(comp);

        updateUI();
    }

    /**
     * Returns current given component position in the current container or <t>-1</t> if it's not found or given component is {@code null}.
     *
     * @param component existed component
     * @return given component position for current container
     */
    public int getComponentPosition(Component component) {
        return ArrayUtils.indexOf(getComponents(), component);
    }

    public void setComp(Collection<? extends Component> components) {
        removeAll();

        if (CollectionUtils.isNotEmpty(components))
            for (Component comp : components)
                super.add(comp);

        layoutOrganizer.update(this);
    }

    public void addCompFirst(Component comp) {
        super.add(comp, 0);
        layoutOrganizer.update(this);
    }

    public void removeLast() {
        int total = getComponentCount();

        if (total > 0) {
            remove(total - 1);
            updateUI();
        }
    }

    public void setPreferredWidth(int width) {
        preferredWidth = width;
    }

    private Component findCompAt(int x, int y, boolean ignoreEnabled) {
        synchronized (getTreeLock()) {
            if (isVisible())
                return findCompAtImpl(x, y, ignoreEnabled);
        }
        return null;
    }

    private Component findCompAtImpl(int x, int y, boolean ignoreEnabled) {
        if (!Thread.holdsLock(getTreeLock()))
            throw new IllegalStateException("This function should be called while holding treeLock");

        if (!(contains(x, y) && isVisible() && (ignoreEnabled || isEnabled())))
            return null;

        for (Component comp : getComponents())
            if (comp != null && comp.contains(x - comp.getX(), y - comp.getY()))
                return comp;

        return this;
    }

    // ========== Component ==========

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();

        if (preferredWidth > 0 && !isPreferredSizeSet())
            size.width = preferredWidth;

        return size;
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    // ========== JComponent ==========

    @Override
    public void updateUI() {
        if (layoutOrganizer != null)
            layoutOrganizer.update(this);
        super.updateUI();
    }

    // ========== Container ==========

    @Override
    public Component findComponentAt(int x, int y) {
        return findCompAt(x, y, true);
    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
        layoutOrganizer.update(this);
    }

    @Override
    public void remove(int index) {
        super.remove(index);
        layoutOrganizer.update(this);
    }

    @Override
    @Deprecated
    public final void add(Component comp, Object constraints) {
        super.add(comp, constraints);
    }

    @Override
    @Deprecated
    public Component add(Component comp) {
        return super.add(comp);
    }

    @Override
    @Deprecated
    public Component add(Component comp, int index) {
        return super.add(comp, index);
    }

    // ========== Object ==========

    @Override
    public String toString() {
        return "components: " + getComponentCount();
    }
}
