package de.escidoc.core.resources.interfaces.item;

public interface ComponentPropertiesInterface {

    String getContentCategory();

    void setContentCategory(final String contentCategory);

    String getFileName();

    void setFileName(final String fileName);

    String getMimeType();

    void setMimeType(final String mimeType);

    void setValidStatus(final String validStatus);

    String getValidStatus();

    String getVisibility();

    void setVisibility(final String visibility);
}
