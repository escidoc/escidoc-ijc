package de.escidoc.core.resources.sb.wrapper.explain;

import gov.loc.www.zing.srw.RecordType;

public class MyRecordExplainType {

    private RecordType record;

    private MyStringFragmentExplain stringFragment;

    public MyRecordExplainType(final RecordType record) {
        this.record = record;
    }

    public RecordType getRecord() {
        return record;
    }

    public java.lang.String getRecordSchema() {
        return record.getRecordSchema();
    }

    public java.lang.String getRecordPacking() {
        return record.getRecordPacking();
    }

    public MyStringFragmentExplain getStringFragment() {
        return new MyStringFragmentExplain(record.getRecordData());
    }

    public void setStringFragment(final MyStringFragmentExplain stringFragment) {
        this.stringFragment = stringFragment;
    }

    public org.apache.axis.types.PositiveInteger getRecordPosition() {
        return record.getRecordPosition();
    }

    public gov.loc.www.zing.srw.ExtraDataType getExtraRecordData() {
        return record.getExtraRecordData();
    }

}
