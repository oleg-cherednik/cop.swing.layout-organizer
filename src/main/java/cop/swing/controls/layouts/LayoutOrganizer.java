package cop.swing.controls.layouts;

import cop.swing.panels.CreateFactory;

import java.awt.Component;
import java.awt.Container;

/**
 * Layout manager for the component. Main idea for this is ther're some gui components {@link Component} and one
 * parent panel {@link Container} and a layout template. And we want to organize given components on the given parent
 * panel according to this template. So, you don't know what, don't know where but know what.
 *
 * @author Oleg Cherednik
 * @since 01.10.2010
 */
public interface LayoutOrganizer {
    void update(Container container);

    CreateFactory<Component> modifyNode(CreateFactory<Component> node);
}
