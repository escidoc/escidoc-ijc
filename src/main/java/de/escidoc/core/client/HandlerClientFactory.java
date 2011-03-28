/**
 * 
 */
package de.escidoc.core.client;

import java.net.URL;

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
import de.escidoc.core.client.interfaces.UserManagementWrapperClientInterface;

/**
 * @author MVO
 * 
 */
public final class HandlerClientFactory {

    private HandlerClientFactory() {
    }

    /**
     * 
     * @return
     */
    public static final AdminHandlerClientInterface getAdminHandlerClient() {
        return new AdminHandlerClient();
    }

    /**
     * @param serviceAddress
     * @return
     */
    public static final AdminHandlerClientInterface getAdminHandlerClient(
        final URL serviceAddress) {
        return new AdminHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final AggregationDefinitionHandlerClientInterface getAggregationDefinitionHandlerClient() {
        return new AggregationDefinitionHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final AggregationDefinitionHandlerClientInterface getAggregationDefinitionHandlerClient(
        final URL serviceAddress) {
        return new AggregationDefinitionHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ContainerHandlerClientInterface getContainerHandlerClient() {
        return new ContainerHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ContainerHandlerClientInterface getContainerHandlerClient(
        final URL serviceAddress) {
        return new ContainerHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ContentModelHandlerClientInterface getContentModelHandlerClient() {
        return new ContentModelHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ContentModelHandlerClientInterface getContentModelHandlerClient(
        final URL serviceAddress) {
        return new ContentModelHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ContentRelationHandlerClientInterface getContentRelationHandlerClient() {
        return new ContentRelationHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ContentRelationHandlerClientInterface getContentRelationHandlerClient(
        final URL serviceAddress) {
        return new ContentRelationHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ContextHandlerClientInterface getContextHandlerClient() {
        return new ContextHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ContextHandlerClientInterface getContextHandlerClient(
        final URL serviceAddress) {
        return new ContextHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final IngestHandlerClientInterface getIngestHandlerClient() {
        return new IngestHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final IngestHandlerClientInterface getIngestHandlerClient(
        final URL serviceAddress) {
        return new IngestHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ItemHandlerClientInterface getItemHandlerClient() {
        return new ItemHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ItemHandlerClientInterface getItemHandlerClient(
        final URL serviceAddress) {
        return new ItemHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final OrganizationalUnitHandlerClientInterface getOrganizationalUnitHandlerClient() {
        return new OrganizationalUnitHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final OrganizationalUnitHandlerClientInterface getOrganizationalUnitHandlerClient(
        final URL serviceAddress) {
        return new OrganizationalUnitHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final PolicyDecisionPointHandlerClientInterface getPolicyDecisionPointHandlerClient() {
        return new PolicyDecisionPointHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final PolicyDecisionPointHandlerClientInterface getPolicyDecisionPointHandlerClient(
        final URL serviceAddress) {
        return new PolicyDecisionPointHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final PreprocessingHandlerClientInterface getPreprocessingHandlerClient() {
        return new PreprocessingHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final PreprocessingHandlerClientInterface getPreprocessingHandlerClient(
        final URL serviceAddress) {
        return new PreprocessingHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ReportDefinitionHandlerClientInterface getReportDefinitionHandlerClient() {
        return new ReportDefinitionHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ReportDefinitionHandlerClientInterface getReportDefinitionHandlerClient(
        final URL serviceAddress) {
        return new ReportDefinitionHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ReportHandlerClientInterface getReportHandlerClient() {
        return new ReportHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ReportHandlerClientInterface getReportHandlerClient(
        final URL serviceAddress) {
        return new ReportHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final RoleHandlerClientInterface getRoleHandlerClient() {
        return new RoleHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final RoleHandlerClientInterface getRoleHandlerClient(
        final URL serviceAddress) {
        return new RoleHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final ScopeHandlerClientInterface getScopeHandlerClient() {
        return new ScopeHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final ScopeHandlerClientInterface getScopeHandlerClient(
        final URL serviceAddress) {
        return new ScopeHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final SearchHandlerClientInterface getSearchHandlerClient() {
        return new SearchHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final SearchHandlerClientInterface getSearchHandlerClient(
        final URL serviceAddress) {
        return new SearchHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final StagingHandlerClientInterface getStagingHandlerClient() {
        return new StagingHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final StagingHandlerClientInterface getStagingHandlerClient(
        final URL serviceAddress) {
        return new StagingHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final StatisticDataHandlerClientInterface getStatisticDataHandlerClient() {
        return new StatisticDataHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final StatisticDataHandlerClientInterface getStatisticDataHandlerClient(
        final URL serviceAddress) {
        return new StatisticDataHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final UserAccountHandlerClientInterface getUserAccountHandlerClient() {
        return new UserAccountHandlerClient();
    }

    /**
     * 
     * @return
     */
    public static final UserAccountHandlerClientInterface getUserAccountHandlerClient(
        final URL serviceAddress) {
        return new UserAccountHandlerClient(serviceAddress);
    }

    /**
     * 
     * @return
     */
    public static final UserManagementWrapperClientInterface getUserManagementWrapperClient() {
        return new UserManagementWrapperClient();
    }

    /**
     * 
     * @return
     */
    public static final UserManagementWrapperClientInterface getUserManagementWrapperClient(
        final URL serviceAddress) {
        return new UserManagementWrapperClient(serviceAddress);
    }
}