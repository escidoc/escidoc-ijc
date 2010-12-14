INSERT INTO sm.scopes (id, xml_data) VALUES ('escidoc:scope3','<?xml version="1.0" encoding="UTF-8"?>
<scope xmlns="http://www.escidoc.de/schemas/scope/0.3" objid="escidoc:scope3">
	<name>Test Scope for JUnit</name>
	<type>normal</type>
</scope>');

INSERT INTO sm.scopes (id, xml_data) VALUES ('escidoc:scope4','<?xml version="1.0" encoding="UTF-8"?>
<scope xmlns="http://www.escidoc.de/schemas/scope/0.3" objid="escidoc:scope4">
    <name>Test Scope1 for JUnit</name>
    <type>normal</type>
</scope>');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata1', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>
        1980-01-28T12:00:00
        </datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>
        createItem
        </stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>
        Session1
        </stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata2', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-01-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>retrieveItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session1</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata3', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-01-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>retrieveItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata4', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-02-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>createItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata5', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-02-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>homepage</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata6', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-03-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>createItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session1</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata7', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>
        1980-01-28T12:00:00
        </datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>
        createItem
        </stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>
        Session1
        </stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-02 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata8', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-01-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>retrieveItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session1</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-02 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata9', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-01-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>retrieveItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-02 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata10', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-02-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>createItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-02 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata11', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-02-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>homepage</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-02 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata12', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-03-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>createItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session1</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2000-01-02 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata13', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>
        1980-01-28T12:00:00
        </datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>
        createItem
        </stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>
        Session1
        </stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2009-02-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata14', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-01-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>retrieveItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session1</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2009-02-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata15', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-01-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>retrieveItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2009-02-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata16', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-02-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>createItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2009-02-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata17', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-02-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>homepage</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session2</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2009-02-01 08:00:00');

INSERT INTO sm.statistic_data (id, xml_data, scope_id, timemarker) 
VALUES
('escidoc:statisticdata18', '<?xml version="1.0" encoding="UTF-8"?>
<statistic-record>
    <scope objid="escidoc:scope4"/>
    <parameter name="reporttest">
        <stringvalue>marker for reporttest</stringvalue>
    </parameter>
    <parameter name="time">
        <datevalue>1980-03-28T12:00:00</datevalue>
    </parameter>
    <parameter name="page">
        <stringvalue>createItem</stringvalue>
    </parameter>
    <parameter name="session_id">
        <stringvalue>Session1</stringvalue>
    </parameter>
</statistic-record>', 'escidoc:scope4', '2009-02-01 08:00:00');

