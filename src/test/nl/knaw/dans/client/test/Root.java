package nl.knaw.dans.client.test;

import org.w3c.dom.Element;

public class Root
{
    private String normalElement;
    private MDRecord mdRecord = new MDRecord();

    public String getNormalElement()
    {
        return normalElement;
    }

    public void setNormalElement(String normalElement)
    {
        this.normalElement = normalElement;
    }

    public MDRecord getMdRecord()
    {
        return mdRecord;
    }


    public void setMdRecord(MDRecord mdRecord)
    {
        this.mdRecord = mdRecord;
    }


    public static class MDRecord
    {
        private Element anyElement;

        public Element getAnyElement()
        {
            return anyElement;
        }

        public void setAnyElement(Element anyElement)
        {
            this.anyElement = anyElement;
        }
    }

}
