package cop.swing.controls;

import javax.validation.constraints.NotNull;

/**
 * Interface describes an enumeration (or any other object), that can be stored in db by its id. It's important not to
 * have direct link between enumeration id and enumeration name, because enumeration name can be changed in code within
 * refactoring.
 *
 * @author Oleg Cherednik
 * @since 31.01.2012
 */
public interface KeyId {
    /** Returns id */
    @NotNull
    String getId();
}
