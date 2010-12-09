package de.escidoc.core.resources.sb.search.records.resolver;

import de.escidoc.core.resources.sb.RecordMetaData;
import de.escidoc.core.resources.sb.search.records.SearchResultRecordRecord;

public class SearchResultRecordRecordResolver
    extends RecordResolver<SearchResultRecordRecord, Object> {

    /**
     * 
     */
    public SearchResultRecordRecordResolver() {
        getResolvableRecordDefinitions().put(
            new TagEntry("search-result-record"), "");
        setIgnoreNS(true);
    }

    @Override
    public SearchResultRecordRecord getRecordInstance(
        final Object value, final RecordMetaData data) {
        // no dependency to the resulting map value needed here
        return new SearchResultRecordRecord(data);
    }

}
