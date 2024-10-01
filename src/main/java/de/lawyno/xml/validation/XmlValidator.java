package de.lawyno.xml.validation;


import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;


public class XmlValidator {

    private final String xsdPath;
    private final StreamSource xmlStreamSource;


    public XmlValidator(final String xsdPath, final String xml) {
        this(xsdPath, new StreamSource(new StringReader(xml)));
    }


    public XmlValidator(final String xsdPath, final StreamSource xmlStreamSource) {
        this.xsdPath = xsdPath;
        this.xmlStreamSource = xmlStreamSource;
    }


    public void validate() throws SAXException, IOException {
        final Validator validator = initValidator(xsdPath);
        validator.validate(xmlStreamSource);
    }


    private Validator initValidator(final String xsdPath) throws SAXException {
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        final URL url = System.class.getResource(xsdPath);
        final Schema schema = schemaFactory.newSchema(url);
        final Validator validator = schema.newValidator();
        validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        return validator;
    }

}
