/**
 * 
 */
package de.escidoc.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes which are annotate with this annotation do have to comply to some
 * general rules in order to work best with JiBX bindings. This annotation can
 * be used on classes and constructors and should be used on private
 * constructors only as explained in paragraph 2.
 * 
 * <ol>
 * <li>Collections<br/>
 * <br/>
 * If a class is using a Collection field, the getter method has to implement
 * lazy loading:<br/>
 * <br/>
 * 
 * The following part of a collection binding definition has some side-effects.<br/>
 * <br/>
 * 
 * <code>&lt;collection name="tagName" field="classField"
 * [...]&gt;[...]&lt;/collection&gt;</code><br/>
 * <br/>
 * 
 * Those side-effects are:<br/>
 * <ul>
 * <li>Unmarshalling:<br/>
 * If the collection is empty, the corresponding field will be set to
 * <code>null</code> by JiBX. This can result in an invalid state for the
 * object. However, if you are using get- and set-method declarations instead of
 * a field declaration in your binding, the setter method of the object may
 * check the value, ignoring null-values for example - if this behavior is
 * valid.</li>
 * <li>Marshalling:<br/>
 * If the collection is empty or <code>null</code> at the object, the tag will
 * not be written to the resulting XML.</li>
 * </ul>
 * <br/>
 * The following part of a collection binding definition could be used
 * alternatively.<br/>
 * <br/>
 * <code>&lt;structure name="tagName"&gt;<br/>&lt;collection field="classField"
 * [...]&gt;[...]&lt;/collection&gt;<br/>&lt;/structure&gt;</code><br/>
 * <br/>
 * The side-effects of this binding are:<br/>
 * <ul>
 * <li>Unmarshalling:<br/>
 * If the collection is empty, the corresponding field will be <b>not</b> set to
 * <code>null</code> by JiBX. The existing Collection object will stay remain.</li>
 * <li>Marshalling:<br/>
 * If the Collection is empty or <code>null</code>, the tag will be written to
 * the resulting XML because it got declared as an enclosing structure element.
 * If the schema for the resulting XML does not allow empty list elements, this
 * binding is not an option.</li>
 * </ul>
 * </li>
 * <li>Constructors<br/>
 * <br/>
 * JiBX needs a default constructor in each class, which is being used within a
 * binding definition, alternatively it will look for a constructor for the type
 * java.lang.String.<br/>
 * If the Object should not be instantiated with a default constructor, because
 * this will be an invalid state for the object, the object should implement a
 * private default constructor annotated with
 * <code>@SuppressWarnings("unused")</code><br/>
 * <br/>
 * There exist two other possibilities in order to avoid implementing default
 * constructors:<br/>
 * <br/>
 * <ul>
 * <li>Factory methods:<br/>
 * If the object has to be instantiated with valid arguments, a factory method
 * may not be able to do this, because those arguments may be found within the
 * mapping element of the object, which need to be instantiated. You will not be
 * able to access the sub elements in the factory method, without leaving the
 * Marshaller in an invalid state.</li>
 * <li>Attribute <code>add-constructors</code> of the binding element:<br/>
 * The added default constructor seems to be public and is therefore no option
 * as well, because the object may be used outside of this API. A user of this
 * API should not be able to instantiate objects with invalid states.</li>
 * </ol>
 * 
 * @author MVO
 */
@Target({ ElementType.TYPE, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface JiBX {

}
