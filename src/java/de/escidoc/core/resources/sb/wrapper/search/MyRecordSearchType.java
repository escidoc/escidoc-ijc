package de.escidoc.core.resources.sb.wrapper.search;

import gov.loc.www.zing.srw.RecordType;
import de.escidoc.core.resources.sb.wrapper.search.MyStringFragmentSearch;

public class MyRecordSearchType {

    private RecordType record;

    private MyStringFragmentSearch stringFragment;

    public MyRecordSearchType(RecordType record) {
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

    public MyStringFragmentSearch getStringFragment() {
        return new MyStringFragmentSearch(record.getRecordData());
    }

    public void setStringFragment(MyStringFragmentSearch stringFragment) {
        this.stringFragment = stringFragment;
    }

    public org.apache.axis.types.PositiveInteger getRecordPosition() {
        return record.getRecordPosition();
    }

    public gov.loc.www.zing.srw.ExtraDataType getExtraRecordData() {
        return record.getExtraRecordData();
    }

}
