package com.fsc.authorization.util;

import org.opensaml.core.xml.schema.XSAny;
import org.opensaml.core.xml.schema.impl.XSAnyBuilder;
import org.opensaml.saml.saml2.core.Extensions;
import org.opensaml.saml.saml2.core.impl.ExtensionsBuilder;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.List;

public class XmlObjectUtils {

    private static final String PREFIX = "egovbga";
    private static final String NAME_SPACE_URL = "urn:bg:egov:eauth:2.0:saml:ext";
    private static final String LOCAL_NAME_LEVEL_OF_ASSURANCE = "LevelOfAssurance";
    private static final String LOCAL_NAME_SERVICE = "Service";
    private static final String LOCAL_NAME_PROVIDER = "Provider";
    private static final String LOCAL_NAME_REQUESTED_SERVICE = "RequestedService";
    private static final String LOCAL_NAME_REQUESTED_ATTRIBUTES = "RequestedAttributes";
    private static final String LOCAL_NAME_REQUESTED_ATTRIBUTE = "RequestedAttribute";
    private static final String LOCAL_NAME_ATTRIBUTE_VALUE = "AttributeValue";
    private static final List<String> REQUESTED_ATTRIBUTES = Arrays.asList("latinName", "birthName", "email", "phone",
            "gender", "dateOfBirth", "placeOfBirth", "canonicalResidenceAddress");
    private static final String ATTRIBUTE_FRIENDLY_NAME = "FriendlyName";
    private static final String ATTRIBUTE_NAME = "Name";
    private static final String ATTRIBUTE_NAME_VALUE = "urn:egov:bg:eauth:2.0:attributes:%s";
    private static final String ATTRIBUTE_NAME_FORMAT_NAME = "NameFormat";
    private static final String ATTRIBUTE_NAME_FORMAT_VALUE = "urn:oasis:names:tc:saml2:2.0:attrname-format:uri";
    private static final String ATTRIBUTE_IS_REQUIRED_NAME = "isRequired";
    private static final String ATTRIBUTE_IS_REQUIRED_VALUE = "false";
    private static final String ATTRIBUTE_VALUE = "urn:egov:bg:eauth:2.0:attributes:%s";

    private XmlObjectUtils() {
    }

    public static Extensions buildExtensions(String oidService, String oidProvider, String levelOfAssurence) {

        // build RequestedServices
        XSAny requestedService = new XSAnyBuilder().buildObject(NAME_SPACE_URL,
                LOCAL_NAME_REQUESTED_SERVICE, PREFIX);

        requestedService.getUnknownXMLObjects().add(buildXsAny(LOCAL_NAME_SERVICE, oidService));
        requestedService.getUnknownXMLObjects().add(buildXsAny(LOCAL_NAME_PROVIDER, oidProvider));
        requestedService.getUnknownXMLObjects().add(buildXsAny(LOCAL_NAME_LEVEL_OF_ASSURANCE, levelOfAssurence));

        // build RequestedAttributes
        XSAny requestedAttributes = new XSAnyBuilder().buildObject(NAME_SPACE_URL,
                LOCAL_NAME_REQUESTED_ATTRIBUTES, PREFIX);

        buildAttributes(requestedAttributes);

        Extensions extensions = new ExtensionsBuilder().buildObject();
        extensions.getUnknownXMLObjects().add(requestedService);
        extensions.getUnknownXMLObjects().add(requestedAttributes);

        return extensions;
    }

    private static void buildAttributes(XSAny requestedAttributes) {

        for (String attributeName : REQUESTED_ATTRIBUTES) {
            XSAny attribute = buildAttribute(attributeName);
            requestedAttributes.getUnknownXMLObjects().add(attribute);
        }
    }

    public static XSAny buildAttribute(String attributeName) {

        XSAny attribute = new XSAnyBuilder().buildObject(NAME_SPACE_URL, LOCAL_NAME_REQUESTED_ATTRIBUTE, PREFIX);

        attribute.getUnknownAttributes().put(new QName(ATTRIBUTE_FRIENDLY_NAME), attributeName);
        attribute.getUnknownAttributes().put(new QName(ATTRIBUTE_NAME), String.format(ATTRIBUTE_NAME_VALUE,
                attributeName));
        attribute.getUnknownAttributes().put(new QName(ATTRIBUTE_NAME_FORMAT_NAME), ATTRIBUTE_NAME_FORMAT_VALUE);
        attribute.getUnknownAttributes().put(new QName(ATTRIBUTE_IS_REQUIRED_NAME), ATTRIBUTE_IS_REQUIRED_VALUE);

        XSAny attributeValue = buildXsAny(LOCAL_NAME_ATTRIBUTE_VALUE, String.format(ATTRIBUTE_VALUE, attributeName));

        attribute.getUnknownXMLObjects().add(attributeValue);

        return attribute;
    }

    public static XSAny buildXsAny(String localName, String content) {
        return buildXsAny(NAME_SPACE_URL, localName, PREFIX, content);
    }

    public static XSAny buildXsAny(String nameSpaceUrl, String localName, String prefix, String content) {

        XSAny xsAny = new XSAnyBuilder().buildObject(nameSpaceUrl, localName, prefix);
        xsAny.setTextContent(content);

        return xsAny;
    }
}