package de.escidoc.core.resources.sb.wrapper.explain;

import gov.loc.www.zing.srw.RecordType;
import de.escidoc.core.resources.sb.wrapper.search.MyStringFragmentSearch;

public class MyRecordExplainType {
private RecordType record;

private MyStringFragmentExplain stringFragment;


public MyRecordExplainType(RecordType record) {
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

public void setStringFragment(MyStringFragmentExplain stringFragment) {
    this.stringFragment = stringFragment;
}



public org.apache.axis.types.PositiveInteger getRecordPosition() {
    return record.getRecordPosition();
}

public gov.loc.www.zing.srw.ExtraDataType getExtraRecordData() {
    return record.getExtraRecordData();
}

}
