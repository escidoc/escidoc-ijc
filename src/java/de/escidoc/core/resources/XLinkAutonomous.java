/**
 * 
 */
package de.escidoc.core.resources;

/**
 * @author MVO
 *
 */
public interface XLinkAutonomous {

    /**
     * XLink validation for JiBX.<br/><br/>
     * 
     * The client application, may only work with {@link #getObjid()} and
     * {@link #setObjid(String)} or an appropriate constructor for a
     * resource implementation. In order to work with such resource objects
     * correctly no matter which transport protocol is being used by the client
     * application, this method is being used by the JiBX binding to ensure,
     * that the XLink values do exist after a resource object got retrieved
     * from the infrastructure <b>and</b> when requesting operations for a
     * resource object on the infrastructure by using the REST protocol.<br/><br/>
     * Therefore this method is being called by the
     * <ul><li>SOAP binding as a <b>post-set</b> method for unmarshalling and</li>
     * <li>REST binding as a <b>pre-get</b> method for marshalling.</li></ul>
     */
    void genXLink();
}
