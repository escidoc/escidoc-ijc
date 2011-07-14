package de.escidoc.core.test.client.unitTests.marshalling.sm.aggregationDefinition;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.sm.ad.AggregationDefinition;
import de.escidoc.core.resources.sm.ad.Field;
import de.escidoc.core.resources.sm.ad.FieldType;
import de.escidoc.core.resources.sm.ad.InfoField;
import de.escidoc.core.resources.sm.ad.TimeReductionField;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class AggregationDefinitionMarshallerTest extends MarshallerTestBase<AggregationDefinition> {

    private static final String BASE = "sm/aggregationDefinition";

    private static final String XSD = "0.4";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public AggregationDefinitionMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(AggregationDefinition.class, BASE, XSD, "aggregation-definition_complete.xml");
    }

    @Override
    protected void validate(final AggregationDefinition obj) throws Exception {

        assertResource("/agg-def:aggregation-definition", obj);
        assertXPath("/agg-def:aggregation-definition/agg-def:name", obj.getName());
        assertResource("/agg-def:aggregation-definition/agg-def:scope", obj.getScope());

        final String path = "/agg-def:aggregation-definition/agg-def:aggregation-table[1]";
        assertNotNull(obj.getAggregationTables());
        assertNotNull(obj.getAggregationTables().get(0));
        assertXPath(path + "/agg-def:name", obj.getAggregationTables().get(0).getName());

        String fieldPath = path + "/agg-def:field[1]/agg-def:info-field";
        Field field = obj.getAggregationTables().get(0).getFields().get(0);
        assertTrue(field.getType().equals(FieldType.INFO));
        assertXPath(fieldPath + "/@feed", ((InfoField) field).getFeed());
        assertXPath(fieldPath + "/agg-def:name", ((InfoField) field).getName());
        assertEnum(fieldPath + "/agg-def:type", ((InfoField) field).getInfoFieldType());
        assertXPath(fieldPath + "/agg-def:xpath", ((InfoField) field).getXPath());

        fieldPath = path + "/agg-def:field[2]/agg-def:time-reduction-field";
        field = obj.getAggregationTables().get(0).getFields().get(1);
        assertTrue(field.getType().equals(FieldType.TIME_REDUCTION));
        assertXPath(fieldPath + "/@feed", ((TimeReductionField) field).getFeed());
        assertXPath(fieldPath + "/agg-def:name", ((TimeReductionField) field).getName());
        assertEnum(fieldPath + "/agg-def:reduce-to", ((TimeReductionField) field).getReduceTo());

        // TODO complete
    }

    @Override
    protected void testSubResources(final AggregationDefinition obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final AggregationDefinition obj) throws Exception {
        // TODO Auto-generated method stub
    }
}