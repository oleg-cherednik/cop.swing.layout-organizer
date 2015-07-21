package cop.swing.panels;

import java.awt.Component;

/**
 * This interface describes simple creatable component.
 *
 * @author Oleg Cherednik
 * @since 18.07.2015
 */
public interface CreateFactory<T extends Component> {
    T create();
}
