/**
 * 
 */
package de.escidoc.core.resources.sb.search.records.resolver;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.sb.search.records.RecordMetaData;
import de.escidoc.core.resources.sb.search.records.ResourceRecord;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;
import de.escidoc.core.resources.sm.report.ReportDefinition;
import de.escidoc.core.resources.sm.scope.Scope;

/**
 * @author MVO
 * 
 */
public class ResourceRecordResolver
    extends RecordResolver<ResourceRecord<?>, ResourceType> {

    /**
     * Initialization of the ResourceRecordResolver, which maps Resource Objects
     * to the corresponding Record Implementation. Only root resources are
     * mapped, because there exist only filter for these resources.
     */
    public ResourceRecordResolver() {
        getResolvableRecordDefinitions().put(
            new TagEntry("aggregation-definition"),
            ResourceType.AggregationDefinition);
        getResolvableRecordDefinitions().put(new TagEntry("container"),
            ResourceType.Container);
        getResolvableRecordDefinitions().put(new TagEntry("content-model"),
            ResourceType.ContentModel);
        getResolvableRecordDefinitions().put(new TagEntry("content-relation"),
            ResourceType.ContentRelation);
        getResolvableRecordDefinitions().put(new TagEntry("context"),
            ResourceType.Context);
        getResolvableRecordDefinitions().put(new TagEntry("grant"),
            ResourceType.Grant);
        getResolvableRecordDefinitions().put(new TagEntry("item"),
            ResourceType.Item);
        getResolvableRecordDefinitions().put(
            new TagEntry("organizational-unit"),
            ResourceType.OrganizationalUnit);
        getResolvableRecordDefinitions().put(new TagEntry("report-definition"),
            ResourceType.ReportDefinition);
        getResolvableRecordDefinitions().put(new TagEntry("role"),
            ResourceType.Role);
        getResolvableRecordDefinitions().put(new TagEntry("scope"),
            ResourceType.Scope);
        getResolvableRecordDefinitions().put(new TagEntry("user-account"),
            ResourceType.UserAccount);
        setIgnoreNS(true);
    }

    /**
     * 
     */
    @Override
    public ResourceRecord<?> getRecordInstance(
        final ResourceType type, final RecordMetaData data) {

        switch (type) {
            case AggregationDefinition: {
                return ResourceRecord.createResourceRecord(
                    AggregationDefinition.class, data);
            }
            case Container: {
                return ResourceRecord.createResourceRecord(Container.class,
                    data);
            }
            case ContentModel: {
                return ResourceRecord.createResourceRecord(ContentModel.class,
                    data);
            }
            case ContentRelation: {
                return ResourceRecord.createResourceRecord(
                    ContentRelation.class, data);
            }
            case Context: {
                return ResourceRecord.createResourceRecord(Context.class, data);
            }
            case Grant: {
                return ResourceRecord.createResourceRecord(Grant.class, data);
            }
            case Item: {
                return ResourceRecord.createResourceRecord(Item.class, data);
            }
            case OrganizationalUnit: {
                return ResourceRecord.createResourceRecord(
                    OrganizationalUnit.class, data);
            }
            case ReportDefinition: {
                return ResourceRecord.createResourceRecord(
                    ReportDefinition.class, data);
            }
            case Role: {
                return ResourceRecord.createResourceRecord(Role.class, data);
            }
            case Scope: {
                return ResourceRecord.createResourceRecord(Scope.class, data);
            }
            case UserAccount: {
                return ResourceRecord.createResourceRecord(UserAccount.class,
                    data);
            }
        }
        return null;
    }
}
