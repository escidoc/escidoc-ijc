/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.interfaces.AdminHandlerClientInterface;
import de.escidoc.core.client.interfaces.AggregationDefinitionHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.client.interfaces.IngestHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.client.interfaces.PolicyDecisionPointHandlerClientInterface;
import de.escidoc.core.client.interfaces.PreprocessingHandlerClientInterface;
import de.escidoc.core.client.interfaces.ReportDefinitionHandlerClientInterface;
import de.escidoc.core.client.interfaces.ReportHandlerClientInterface;
import de.escidoc.core.client.interfaces.RoleHandlerClientInterface;
import de.escidoc.core.client.interfaces.ScopeHandlerClientInterface;
import de.escidoc.core.client.interfaces.SearchHandlerClientInterface;
import de.escidoc.core.client.interfaces.StagingHandlerClientInterface;
import de.escidoc.core.client.interfaces.StatisticDataHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserManagementWrapperClientInterface;

/**
 * This factory supports mapping and storing of {@link AbstractHandlerClient}
 * instances. For each serviceAddress, there will exist one and only one
 * {@link AbstractHandlerClient} instance of each type.
 * 
 * @author MVO
 * 
 */
public final class HandlerClientFactory {

    private static final Map<URL, Map<Class<?>, AbstractHandlerClient<?>>> serviceMap =
        new HashMap<URL, Map<Class<?>, AbstractHandlerClient<?>>>();

    /**
     * 
     */
    private HandlerClientFactory() {
    }

    /**
     * @param <T>
     * @param serviceAddress
     * @param type
     * @return
     */
    private static final <T extends AbstractHandlerClient<?>> T manageHandler(final T type) {

        checkNotNull(type);

        Map<Class<?>, AbstractHandlerClient<?>> clients = serviceMap.get(type.getServiceAddress());
        if (clients == null) {
            clients = new HashMap<Class<?>, AbstractHandlerClient<?>>();
            serviceMap.put(type.getServiceAddress(), clients);
        }

        if (!clients.containsKey(type.getClass())) {
            clients.put(type.getClass(), type);
        }

        return type;
    }

    /**
     * @param serviceAddress
     * @return
     */
    public static final AdminHandlerClientInterface getAdminHandlerClient(final URL serviceAddress) {
        return manageHandler(new AdminHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final AggregationDefinitionHandlerClientInterface getAggregationDefinitionHandlerClient(
        final URL serviceAddress) {
        return manageHandler(new AggregationDefinitionHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ContainerHandlerClientInterface getContainerHandlerClient(final URL serviceAddress) {
        return manageHandler(new ContainerHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ContentModelHandlerClientInterface getContentModelHandlerClient(final URL serviceAddress) {
        return manageHandler(new ContentModelHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ContentRelationHandlerClientInterface getContentRelationHandlerClient(final URL serviceAddress) {
        return manageHandler(new ContentRelationHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ContextHandlerClientInterface getContextHandlerClient(final URL serviceAddress) {
        return manageHandler(new ContextHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final IngestHandlerClientInterface getIngestHandlerClient(final URL serviceAddress) {
        return manageHandler(new IngestHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ItemHandlerClientInterface getItemHandlerClient(final URL serviceAddress) {
        return manageHandler(new ItemHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final OrganizationalUnitHandlerClientInterface getOrganizationalUnitHandlerClient(
        final URL serviceAddress) {
        return manageHandler(new OrganizationalUnitHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final PolicyDecisionPointHandlerClientInterface getPolicyDecisionPointHandlerClient(
        final URL serviceAddress) {
        return manageHandler(new PolicyDecisionPointHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final PreprocessingHandlerClientInterface getPreprocessingHandlerClient(final URL serviceAddress) {
        return manageHandler(new PreprocessingHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ReportDefinitionHandlerClientInterface getReportDefinitionHandlerClient(final URL serviceAddress) {
        return manageHandler(new ReportDefinitionHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ReportHandlerClientInterface getReportHandlerClient(final URL serviceAddress) {
        return manageHandler(new ReportHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final RoleHandlerClientInterface getRoleHandlerClient(final URL serviceAddress) {
        return manageHandler(new RoleHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final ScopeHandlerClientInterface getScopeHandlerClient(final URL serviceAddress) {
        return manageHandler(new ScopeHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final SearchHandlerClientInterface getSearchHandlerClient(final URL serviceAddress) {
        return manageHandler(new SearchHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final StagingHandlerClientInterface getStagingHandlerClient(final URL serviceAddress) {
        return manageHandler(new StagingHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final StatisticDataHandlerClientInterface getStatisticDataHandlerClient(final URL serviceAddress) {
        return manageHandler(new StatisticDataHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final UserAccountHandlerClientInterface getUserAccountHandlerClient(final URL serviceAddress) {
        return manageHandler(new UserAccountHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final UserGroupHandlerClientInterface getUserGroupHandlerClient(final URL serviceAddress) {
        return manageHandler(new UserGroupHandlerClient(serviceAddress));
    }

    /**
     * 
     * @return
     */
    public static final UserManagementWrapperClientInterface getUserManagementWrapperClient(final URL serviceAddress) {
        return manageHandler(new UserManagementWrapperClient(serviceAddress));
    }
}