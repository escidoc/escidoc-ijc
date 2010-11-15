/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.AggregationDefinitionHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;
import de.escidoc.core.resources.sm.ad.AggregationTable;
import de.escidoc.core.resources.sm.ad.Field;
import de.escidoc.core.resources.sm.ad.Index;
import de.escidoc.core.resources.sm.ad.InfoField;
import de.escidoc.core.resources.sm.ad.InfoFieldType;
import de.escidoc.core.resources.sm.ad.StatisticData;
import de.escidoc.core.resources.sm.ad.StatisticTable;
import de.escidoc.core.resources.sm.ad.TimeReductionField;
import de.escidoc.core.resources.sm.ad.TimeReductionFieldType;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.resources.sm.scope.ScopeType;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionHandlerClientTest
    extends AbstractParameterizedTestBase {

    private Authentication auth;

    private AggregationDefinitionHandlerClient adhc;

    private String scopeId;

    private static final String FIELD_INFO_NAME = "info_field_01";

    private static final String FIELD_TIME_NAME = "time_field_01";

    private static final String XPATH_INFO = "";

    private static final String IDX_NAME = "idx_01_02";

    private static final String FEED = "statistics-data";

    public AggregationDefinitionHandlerClientTest(
        final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        adhc = new AggregationDefinitionHandlerClient(auth.getServiceAddress());
        adhc.setHandle(auth.getHandle());
        adhc.setTransport(transport);

        // prepare scope
        scopeId = createScope(ScopeType.admin);
        if (scopeId == null)
            fail("Unable to initialze test case. ScopeId is null.");
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate01() throws Exception {

        final String AD_NAME = "ad_test_" + System.currentTimeMillis();
        final String AD_TABLE_NAME = "ad_test_table";

        AggregationDefinition ad = defineValidAD(AD_NAME, AD_TABLE_NAME);

        AggregationDefinition adCreated = adhc.create(ad);

        assertNotNull("Objid is null.", adCreated.getObjid());
        assertEquals("Name is not equals.", ad.getName(), adCreated.getName());
        assertEquals("", ad.getScope(), adCreated.getScope());

        assertNotNull("StatisticData is null.", adCreated.getStatisticData());
        assertNotNull("StatisticTable is null.", adCreated
            .getStatisticData().getStatisticTable());
        // assertNotNull("",
        // ad.getStatisticData().getStatisticTable().getXPath());
        assertNotNull("AggregationTables is null.",
            adCreated.getAggregationTables());
        assertTrue("AggregationTables size is not 1.", adCreated
            .getAggregationTables().size() == 1);

        for (AggregationTable table : adCreated.getAggregationTables()) {
            assertNotNull("Table name should not be null.", table.getName());
            assertTrue("Incorrect table name.",
                table.getName().endsWith(AD_TABLE_NAME));

            assertNotNull("Fields is null.", table.getFields());
            assertTrue("Fields size is not 2.", table.getFields().size() == 2);

            for (Field fieldEntry : table.getFields()) {
                switch (fieldEntry.getType()) {
                    case InfoField: {
                        InfoField field = (InfoField) fieldEntry;

                        assertEquals("Incorrect field name.", FIELD_INFO_NAME,
                            field.getName());
                        assertEquals("Incorrect feed.", FEED, field.getFeed());
                        assertTrue("Wrong field type.",
                            InfoFieldType.text == field.getInfoFieldType());
                        assertEquals("Incorrect xPath.", XPATH_INFO,
                            field.getXPath());
                        break;
                    }
                    case TimeReductionField: {
                        TimeReductionField field =
                            (TimeReductionField) fieldEntry;

                        assertEquals("Incorrect field name.", FIELD_TIME_NAME,
                            field.getName());
                        assertEquals("Incorrect feed.", FEED, field.getFeed());
                        assertTrue("Wrong field type.",
                            TimeReductionFieldType.day == field.getReduceTo());
                        break;
                    }
                }
            }
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreateDuplicates() throws Exception {
        final String AD_NAME = "duplicate_ad_name";
        final String AD_TABLE_NAME = "duplicate_table_name";

        AggregationDefinition ad01 = defineValidAD(AD_NAME, AD_TABLE_NAME);
        AggregationDefinition ad02 = defineValidAD(AD_NAME, AD_TABLE_NAME);

        /*
         * Table names will be changed by the core in order to guarantee, that
         * duplicates do not exist.
         */
        AggregationDefinition createdAD01 = adhc.create(ad01);
        AggregationDefinition createdAD02 = adhc.create(ad02);

        // check AD01
        assertNotNull("AggregationTables must not be null.",
            createdAD01.getAggregationTables());
        assertFalse("No tables found in AggregationDefinition.", createdAD01
            .getAggregationTables().isEmpty());

        AggregationTable t01 =
            createdAD01.getAggregationTables().iterator().next();

        assertNotNull("", t01.getName());
        assertFalse(
            "Supplied table name should not be equals to the returned table name!",
            t01.getName().equals(AD_TABLE_NAME));

        // check AD02
        assertNotNull("AggregationTables must not be null.",
            createdAD02.getAggregationTables());
        assertFalse("No tables found in AggregationDefinition.", createdAD02
            .getAggregationTables().isEmpty());

        AggregationTable t02 =
            createdAD02.getAggregationTables().iterator().next();

        assertNotNull("", t02.getName());
        assertFalse(
            "Supplied table name should not be equals to the returned table name!",
            t02.getName().equals(AD_TABLE_NAME));

        // check AD01 vs AD02
        assertFalse(
            "Table names of both AggregationDefinition objects should niot be equals.",
            t01.getName().equals(t02.getName()));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDelete01() throws Exception {
        AggregationDefinition ad =
            defineValidAD("ad_test_" + System.currentTimeMillis(), "table_name");
        AggregationDefinition createdAd = adhc.create(ad);

        assertNotNull("Objid should not be null.", createdAd.getObjid());

        adhc.delete(createdAd.getObjid());
    }

    /**
     * Creates a valid AD Object with only one table.
     * 
     * @return
     */
    private final AggregationDefinition defineValidAD(
        final String name, final String tableName) {
        return defineValidAD(name, tableName, scopeId);
    }

    /**
     * 
     * @param name
     * @param tableName
     * @param scopeId
     * @return
     */
    public static final AggregationDefinition defineValidAD(
        final String name, final String tableName, final String scopeId) {

        StatisticData statData = new StatisticData(new StatisticTable());
        AggregationDefinition ad =
            new AggregationDefinition(name, new ScopeRef(scopeId), statData);
        AggregationTable at = new AggregationTable(tableName);
        at.getFields()
            .add(
                new InfoField(FIELD_INFO_NAME, FEED, InfoFieldType.text,
                    XPATH_INFO));
        at.getFields().add(
            new TimeReductionField(FIELD_TIME_NAME, FEED,
                TimeReductionFieldType.day));
        at.getIndexes().add(
            new Index(IDX_NAME, Arrays.asList(new String[] { FIELD_INFO_NAME,
                FIELD_TIME_NAME })));

        ad.getAggregationTables().add(at);
        return ad;
    }

    /**
     * 
     * @param type
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String createScope(final ScopeType type) throws EscidocException,
        InternalClientException, TransportException {
        Scope scope =
            new Scope("AdminScope @" + System.currentTimeMillis(), type);

        ScopeHandlerClient shc =
            new ScopeHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());
        shc.setTransport(transport);

        return shc.create(scope).getObjid();
    }
}
