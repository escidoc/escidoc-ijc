package de.escidoc.core.resources.om.item.component;

import java.io.IOException;

import de.escidoc.core.resources.om.item.Content;

public class ComponentContent extends Content{

private String base64EncodedContent;

public void setBase64EncodedContent(String base64EncodedContent) {
    this.base64EncodedContent = base64EncodedContent;
}


public String getBase64EncodedContent() {
    return base64EncodedContent;
}


public String encodeBinaryContent(byte [] inlineContent) {
    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    String encodedContent = encoder.encode(inlineContent);
    return encodedContent;
}

public byte [] decodeBinaryContent(String base64EncodedContent) throws IOException {
    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    byte [] decodedContent = decoder.decodeBuffer(base64EncodedContent);
    return decodedContent;
}
}
