
package com.lakala.mini.dto.work;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lakala.work package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ImportSUUserResponse_QNAME = new QName("importUser", "importSUUserResponse");
    private final static QName _ImportSUUser_QNAME = new QName("importUser", "importSUUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lakala.work
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImportSUUserRequestType }
     * 
     */
    public ImportSUUserRequestType createImportSUUserRequestType() {
        return new ImportSUUserRequestType();
    }

    /**
     * Create an instance of {@link ImportSUUserResponseType }
     * 
     */
    public ImportSUUserResponseType createImportSUUserResponseType() {
        return new ImportSUUserResponseType();
    }

    /**
     * Create an instance of {@link Context }
     * 
     */
    public Context createContext() {
        return new Context();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportSUUserResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "importUser", name = "importSUUserResponse")
    public JAXBElement<ImportSUUserResponseType> createImportSUUserResponse(ImportSUUserResponseType value) {
        return new JAXBElement<ImportSUUserResponseType>(_ImportSUUserResponse_QNAME, ImportSUUserResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportSUUserRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "importUser", name = "importSUUser")
    public JAXBElement<ImportSUUserRequestType> createImportSUUser(ImportSUUserRequestType value) {
        return new JAXBElement<ImportSUUserRequestType>(_ImportSUUser_QNAME, ImportSUUserRequestType.class, null, value);
    }

}
