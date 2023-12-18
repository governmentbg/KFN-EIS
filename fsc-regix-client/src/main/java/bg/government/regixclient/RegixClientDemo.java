package bg.government.regixclient;


import bg.government.regixclient.grao.GraoOperation;
import bg.government.regixclient.grao.pna.ObjectFactory;
import bg.government.regixclient.grao.pna.PermanentAddressRequestType;

import java.lang.reflect.Field;
import java.security.Security;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class RegixClientDemo {

    public static void main(String[] args) throws Exception {

        String keystorePassword = args[0];

        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        ObjectFactory objectFactory = new ObjectFactory();
        PermanentAddressRequestType personDataRequest = objectFactory.createPermanentAddressRequestType();
        personDataRequest.setEGN("8506258485");
        GregorianCalendar c = GregorianCalendar.from((LocalDate.parse("2022-06-22")).atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        personDataRequest.setSearchDate(date2);

        RegixClient client = RegixClient.createTestClient(
                RegixClientDemo.class.getResourceAsStream("/regix.keystore"),
                keystorePassword.toCharArray());
        ServiceRequestData requestData = RegixClient.createRequestData(GraoOperation.PERMANENT_ADDRESS_SEARCH, objectFactory.createPermanentAddressRequest(personDataRequest));

        CallContext ctx = new CallContext();
        ctx.setServiceURI("https://regix-service-test.egov.bg/regix/RegiXEntryPoint.svc");
        ctx.setServiceType("За проверовъчна дейност");
        ctx.setEmployeeIdentifier("SYSTEM");
        ctx.setEmployeeNames("СВИП Система за вторична интеграция и правоприлагане");
        ctx.setEmployeeAditionalIdentifier("");
        ctx.setEmployeePosition("СВИП Система за вторична интеграция и правоприлагане");
        ctx.setAdministrationOId("2.16.100.1.1.1.1.15");
        ctx.setAdministrationName("Нацонално тол управление към Агенция \"Пътна инфраструктура\"");
        ctx.setLawReason("чл. 167а, ал. 6 от ЗДвП, във връзка с чл. 189е, ал. 12 и чл. 189ж, ал. 1 от ЗДвП");
        ctx.setRemark("няма");

        requestData.setCallContext(ctx);

        ServiceResultData serviceResultData = client.executeSynchronous(requestData);

        System.out.println(JAXBIntrospector.getValue(serviceResultData.getData().getResponse().getAny()));
    }

    @SuppressWarnings("unused")
    private static void setupTLS() throws Exception {
        // strong security is enabled by default for Java 12

        // enable strong security BEFORE Java 12
        Security.setProperty("crypto.policy", "unlimited");

        // enable strong security BEFORE Java 9
        Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
        field.setAccessible(true);
        field.set(null, java.lang.Boolean.FALSE);
    }
}
