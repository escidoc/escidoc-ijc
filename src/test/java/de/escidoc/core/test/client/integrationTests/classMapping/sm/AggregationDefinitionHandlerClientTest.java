/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchResult;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;
import de.escidoc.core.resources.sm.ad.AggregationTable;
import de.escidoc.core.resources.sm.ad.CountCumulationField;
import de.escidoc.core.resources.sm.ad.DifferenceCumulationField;
import de.escidoc.core.resources.sm.ad.Field;
import de.escidoc.core.resources.sm.ad.FieldType;
import de.escidoc.core.resources.sm.ad.Index;
import de.escidoc.core.resources.sm.ad.InfoField;
import de.escidoc.core.resources.sm.ad.InfoFieldType;
import de.escidoc.core.resources.sm.ad.StatisticData;
import de.escidoc.core.resources.sm.ad.StatisticTable;
import de.escidoc.core.resources.sm.ad.TimeReductionField;
import de.escidoc.core.resources.sm.ad.TimeReductionFieldType;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.resources.sm.scope.ScopeType;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionHandlerClientTest {

    private Authentication auth;

    private AggregationDefinitionHandlerClient adhc;

    private String scopeId;

    private static final String FIELD_INFO_PAGE = "page";

    private static final String FIELD_TIME_MONTH = "month";

    private static final String FIELD_TIME_YEAR = "year";

    private static final String FIELD_CUMUL_REQUESTS = "requests";

    private static final String FIELD_DIFF_SESSIONS = "sessions";

    private static final String FIELD_INFO_PAGE_XPATH =
        "//parameter[@name=\"page\"]/stringvalue";

    private static final String FIELD_DIFF_SESSIONS_XPATH =
        "//parameter[@name=\"session_id\"]/stringvalue";

    private static final String IDX_NAME = "time_idx";

    private static final String FEED = "statistics-data";

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        adhc = new AggregationDefinitionHandlerClient(auth.getServiceAddress());
        adhc.setHandle(auth.getHandle());

        // prepare scope
        scopeId = createScope(ScopeType.ADMIN);
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
    public void testMarshallingForCdata01() throws Exception {
        InputStream in = Template.load("/soap/sm/ad/ad-01.xml");
        AggregationDefinition ad =
            MarshallerFactory
                .getInstance(TransportProtocol.SOAP)
                .getMarshaller(AggregationDefinition.class)
                .unmarshalDocument(in);
        String xml =
            MarshallerFactory
                .getInstance(TransportProtocol.SOAP)
                .getMarshaller(AggregationDefinition.class).marshalDocument(ad);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate01() throws Exception {

        final String AD_NAME = "Page Statistics for PubMan";
        final String AD_TABLE_NAME = "Report_Test";

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
            assertTrue("Fields size is not 2.", table.getFields().size() == 5);

            Iterator<Field> it = table.getFields().iterator();
            Field fieldEntry = it.next();
            // info field
            assertTrue(fieldEntry.getType() == FieldType.INFO);
            InfoField field = (InfoField) fieldEntry;

            assertEquals("Incorrect field name.", FIELD_INFO_PAGE,
                field.getName());
            assertEquals("Incorrect feed.", FEED, field.getFeed());
            assertTrue("Wrong field type.",
                InfoFieldType.TEXT == field.getInfoFieldType());
            assertEquals("Incorrect xPath.", FIELD_INFO_PAGE_XPATH,
                field.getXPath());

            fieldEntry = it.next();
            // time reduction field
            assertTrue(fieldEntry.getType() == FieldType.TIME_REDUCTION);
            TimeReductionField timeField = (TimeReductionField) fieldEntry;

            assertEquals("Incorrect field name.", FIELD_TIME_MONTH,
                timeField.getName());
            assertEquals("Incorrect feed.", FEED, timeField.getFeed());
            assertTrue("Wrong field type.",
                TimeReductionFieldType.MONTH == timeField.getReduceTo());

            fieldEntry = it.next();
            // time reduction field
            assertTrue(fieldEntry.getType() == FieldType.TIME_REDUCTION);
            TimeReductionField timeField2 = (TimeReductionField) fieldEntry;

            assertEquals("Incorrect field name.", FIELD_TIME_YEAR,
                timeField2.getName());
            assertEquals("Incorrect feed.", FEED, timeField2.getFeed());
            assertTrue("Wrong field type.",
                TimeReductionFieldType.YEAR == timeField2.getReduceTo());

            fieldEntry = it.next();
            // count cumulation field
            assertTrue(fieldEntry.getType() == FieldType.COUNT_CUMULATION);
            CountCumulationField countField = (CountCumulationField) fieldEntry;

            assertEquals("Incorrect field name.", FIELD_CUMUL_REQUESTS,
                countField.getName());

            fieldEntry = it.next();
            // time reduction field
            assertTrue(fieldEntry.getType() == FieldType.DIFFERENCE_CUMULATION);
            DifferenceCumulationField diffField =
                (DifferenceCumulationField) fieldEntry;

            assertEquals("Incorrect field name.", FIELD_DIFF_SESSIONS,
                diffField.getName());
            assertEquals("Incorrect feed.", FEED, diffField.getFeed());
            assertEquals("Incorrect xPath.", FIELD_DIFF_SESSIONS_XPATH,
                diffField.getXPath());
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
     * 
     * @throws Exception
     */
    @Test
    public void testExplain() throws Exception {
        AggregationDefinition ad =
            defineValidAD("ad_test_" + System.currentTimeMillis(), "table_name");
        AggregationDefinition createdAd = adhc.create(ad);

        assertNotNull("Objid should not be null.", createdAd.getObjid());

        ExplainRequestType request = new ExplainRequestType();
        ExplainResponse response = adhc.retrieveAggregationDefinitions(request);
        Explain explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertNotNull("No index definitions found", explain.getIndexInfo());
        assertNotNull("No index definitions found", explain
            .getIndexInfo().getIndexes());
        assertTrue("No index definitions found", explain
            .getIndexInfo().getIndexes().size() > 0);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFilter() throws Exception {
        AggregationDefinition ad =
            defineValidAD("ad_test_" + System.currentTimeMillis(), "table_name");
        AggregationDefinition createdAd = adhc.create(ad);

        assertNotNull("Objid should not be null.", createdAd.getObjid());

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery("\"http://purl.org/dc/elements/1.1/identifier\"="
            + createdAd.getObjid());
        SearchRetrieveResponse response =
            adhc.retrieveAggregationDefinitions(request);

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("Wrong number of matching records",
            response.getNumberOfMatchingRecords() >= 1);
        assertTrue("Wrong number of resulting records",
            response.getNumberOfResultingRecords() >= 1);
        assertEquals("Wrong record position", 1, response
            .getRecords().iterator().next().getRecordPosition().intValue());

        Collection<String> ids =
            new ArrayList<String>(response.getNumberOfResultingRecords());
        for (SearchResultRecord record : response.getRecords()) {
            SearchResult recordData = record.getRecordData();

            if (recordData.getContent() instanceof AggregationDefinition) {

                AggregationDefinition data =
                    (AggregationDefinition) recordData.getContent();
                if (data != null)
                    ids.add(data.getObjid());
            }
        }
        assertTrue("Created AggregationDefinition missing in list",
            ids.contains(createdAd.getObjid()));
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

        at.getFields().add(
            new InfoField(FIELD_INFO_PAGE, FEED, InfoFieldType.TEXT,
                FIELD_INFO_PAGE_XPATH));

        at.getFields().add(
            new TimeReductionField(FIELD_TIME_MONTH, FEED,
                TimeReductionFieldType.MONTH));

        at.getFields().add(
            new TimeReductionField(FIELD_TIME_YEAR, FEED,
                TimeReductionFieldType.YEAR));

        at.getFields().add(new CountCumulationField(FIELD_CUMUL_REQUESTS));

        at.getFields().add(
            new DifferenceCumulationField(FIELD_DIFF_SESSIONS, FEED,
                FIELD_DIFF_SESSIONS_XPATH));

        at.getIndexes().add(
            new Index(IDX_NAME, Arrays.asList(new String[] { FIELD_TIME_MONTH,
                FIELD_TIME_YEAR })));

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

        return shc.create(scope).getObjid();
    }
}
