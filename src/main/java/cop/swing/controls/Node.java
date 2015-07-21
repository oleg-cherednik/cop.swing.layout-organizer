package cop.swing.controls;

import cop.swing.panels.CreateFactory;

import java.awt.Component;

/**
 * This interface is used to define visual components. The set of this components can be applied to some container,
 * e.g. {@link NodePanel}
 *
 * @author Oleg Cherednik
 * @see NodePanel
 * @since 18.07.2015
 */
public interface Node extends CreateFactory<Component>, KeyId {
    boolean check(String id);
}
