/*
 Qualifies a user as having logged in
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.qualifiers;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author markito
 */
@Qualifier
@Retention(RUNTIME)
@Target({
    METHOD,
    FIELD,
    PARAMETER,
    TYPE
})
public @interface LoggedIn {
}
