package com.fsc.external.portal.controllers;

import bg.fsc.schemas.report.on.ReportMonthly;
import bg.fsc.schemas.report.on.ReportQuarterly;
import bg.fsc.schemas.report.on.ReportYearly;
import bg.fsc.schemas.report.on.b.ReportB;
import bg.fsc.schemas.report.on.b._11b.Report11B;
import bg.fsc.schemas.report.on.b._12b.Report12B;
import bg.fsc.schemas.report.on.b._13b.FundTypeRow;
import bg.fsc.schemas.report.on.b._13b.Report13B;
import bg.fsc.schemas.report.on.b._14b.Report14B;
import bg.fsc.schemas.report.on.b._15b.Report15B;
import bg.fsc.schemas.report.on.b._16b.Report16B;
import bg.fsc.schemas.report.on.b._18b.Report18B;
import bg.fsc.schemas.report.on.b._21b.Report21B;
import bg.fsc.schemas.report.on.b._22b.Report22B;
import bg.fsc.schemas.report.on.b._23b.Report23B;
import bg.fsc.schemas.report.on.b._24b.Report24B;
import bg.fsc.schemas.report.on.b._25b.Report25B;
import bg.fsc.schemas.report.on.b._31b.Report31B;
import bg.fsc.schemas.report.on.b._32b.Report32B;
import bg.fsc.schemas.report.on.b._33b.Report33B;
import bg.fsc.schemas.report.on.b._41b.Report41B;
import bg.fsc.schemas.report.on.b._42b.Report42B;
import bg.fsc.schemas.report.on.b._43b.Report43B;
import bg.fsc.schemas.report.on.distrib.funds.ReportDistribFundsRowType;
import bg.fsc.schemas.report.on.distrib.funds.ReportDistribFundsType;
import bg.fsc.schemas.report.on.distrib.insured.ReportDistribInsuredRowType;
import bg.fsc.schemas.report.on.distrib.insured.ReportDistribInsuredType;
import bg.fsc.schemas.report.on.distrib.paid.insured.ReportDistribPaidInsuredRowType;
import bg.fsc.schemas.report.on.distrib.paid.insured.ReportDistribPaidInsuredType;
import bg.fsc.schemas.report.on.invest.pension.reserve.a1.ReportInvestPensionReserveA1RowType;
import bg.fsc.schemas.report.on.invest.pension.reserve.a1.ReportInvestPensionReserveA1Type;
import bg.fsc.schemas.report.on.invest.pension.reserve.a2.ReportInvestPensionReserveA2RowType;
import bg.fsc.schemas.report.on.invest.pension.reserve.a2.ReportInvestPensionReserveA2Type;
import bg.fsc.schemas.report.on.invest.pension.reserve.a3.ReportInvestPensionReserveA3RowType;
import bg.fsc.schemas.report.on.invest.pension.reserve.a3.ReportInvestPensionReserveA3Type;
import bg.fsc.schemas.report.on.invest.pension.reserve.a4.ReportInvestPensionReserveA4RowType;
import bg.fsc.schemas.report.on.invest.pension.reserve.a4.ReportInvestPensionReserveA4Type;
import bg.fsc.schemas.report.on.master.ReportMasterType;
import bg.fsc.schemas.report.on.master.SignatureType;
import bg.fsc.schemas.report.on.p.ReportP;
import bg.fsc.schemas.report.on.p._10p.Report10P;
import bg.fsc.schemas.report.on.p._11p.Report11P;
import bg.fsc.schemas.report.on.p._12p.Report12P;
import bg.fsc.schemas.report.on.p._14p.Report14P;
import bg.fsc.schemas.report.on.p._15p.Report15P;
import bg.fsc.schemas.report.on.p._1p.FundType;
import bg.fsc.schemas.report.on.p._1p.Report1P;
import bg.fsc.schemas.report.on.p._2p.Report2P;
import bg.fsc.schemas.report.on.p._31p.Report31P;
import bg.fsc.schemas.report.on.p._32p.Report32P;
import bg.fsc.schemas.report.on.p._33p.Report33P;
import bg.fsc.schemas.report.on.p._34p.Report34P;
import bg.fsc.schemas.report.on.p._35p.Report35P;
import bg.fsc.schemas.report.on.p._3p.Report3P;
import bg.fsc.schemas.report.on.p._4p.Report4P;
import bg.fsc.schemas.report.on.p._5p.Report5P;
import bg.fsc.schemas.report.on.p._7p.Report7P;
import bg.fsc.schemas.report.on.p._9p.Report9P;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a1.ReportReserveGuaranteeGrossContrib1A1RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a1.ReportReserveGuaranteeGrossContrib1A1Type;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a2.ReportReserveGuaranteeGrossContrib1A2RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a2.ReportReserveGuaranteeGrossContrib1A2Type;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a3.ReportReserveGuaranteeGrossContrib1A3RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a3.ReportReserveGuaranteeGrossContrib1A3Type;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a4.ReportReserveGuaranteeGrossContrib1A4RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.gross.contrib1.a4.ReportReserveGuaranteeGrossContrib1A4Type;
import bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a1.ReportReserveGuaranteeMinYieldOwn1A1RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a2.ReportReserveGuaranteeMinYieldOwn1A2RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a3.ReportReserveGuaranteeMinYieldOwn1A3RowType;
import bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a4.ReportReserveGuaranteeMinYieldOwn1A4RowType;
import bg.fsc.schemas.report.on.riskvalue.instruments.ReportRiskValueInstrumentsRowType;
import bg.fsc.schemas.report.on.riskvalue.nonfulfilmentobligations.ReportRiskValueNonFulfilmentObligationsRowType;
import bg.fsc.schemas.report.types.CountryType;
import bg.fsc.schemas.report.types.CurrencyType;
import com.fsc.common.addon.service.mapping.mapper.on.CommonReportMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sql.DataSource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@RestController
@RequestMapping("/rest/generate/report")
@Tag(name = "Report generator API", description = "Created in favor of our beloved QA team. For internal use only. To be removed before "
                                                          + "official launch.")
public class ReportGeneratorController {

    private static final Logger log = LoggerFactory.getLogger(ReportGeneratorController.class);

    private final JdbcTemplate jdbcTemplate;

    public ReportGeneratorController(@Qualifier("migrationDataSource") DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Operation(summary = "Generate daily P report using random values")
    @GetMapping(value = "/daily/p/random", produces = MediaType.TEXT_XML_VALUE)
    public ReportP generatePReportRandom(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date
    ) throws DatatypeConfigurationException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReportP reportMonthly = new ReportP();
        populateObjectTree(ReportP.class, reportMonthly);
        ReportMasterType reportMasterType = generateReportMaster(date, date);
        reportMonthly.setReportMaster(reportMasterType);
        return reportMonthly;
    }

    @Operation(summary = "Generate daily P report using migrated real data")
    @GetMapping(value = "/daily/p/real", produces = MediaType.TEXT_XML_VALUE)
    public ReportP generatePReportReal(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date
    ) throws DatatypeConfigurationException {
        ReportP report = new ReportP();
        Report1P report1P = generateReport1P();
        LocalDate report1PDate = date.plusDays(1);
        report1P.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(report1PDate.toString()));
        report.setReport1P(report1P);
        report.setReport2P(generateReport2P());
        report.setReport3P(generateReport3P());
        report.setReport4P(generateReport4P());
        report.setReport5P(generateReport5P());
        // report.setReport6P(generateReport6P());
        report.setReport7P(generateReport7P());
        // report.setReport8P(generateReport8P());
        report.setReport9P(generateReport9P());
        report.setReport10P(generateReport10P());
        report.setReport11P(generateReport11P());
        report.setReport12P(generateReport12P());
        report.setReport14P(generateReport14P());
        report.setReport15P(generateReport15P());
        report.setReport31P(generateReport31P());
        report.setReport32P(generateReport32P());
        report.setReport33P(generateReport33P());
        report.setReport34P(generateReport34P());
        report.setReport35P(generateReport35P());

        ReportMasterType reportMasterType = generateReportMaster(date, date);
        report.setReportMaster(reportMasterType);
        return report;
    }

    @Operation(summary = "Generate daily B report using migrated real data")
    @GetMapping(value = "/daily/b/real", produces = MediaType.TEXT_XML_VALUE)
    public ReportB generateBReportReal(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date
    ) throws DatatypeConfigurationException {
        ReportB reportMonthly = new ReportB();

        reportMonthly.setReport11B(generateReport11B("175137918"));
        // reportMonthly.setReport12B(generateReport12B("175137918"));
        reportMonthly.setReport13B(generateReport13B("175137918"));
        // reportMonthly.setReport14B(generateReport14B("175137918"));
        reportMonthly.setReport15B(generateReport15B("175137918"));
        reportMonthly.setReport16B(generateReport16B("175137918"));
        reportMonthly.setReport18B(generateReport18B("175137918"));
        reportMonthly.setReport21B(generateReport21B("175137918"));
        // reportMonthly.setReport22B(generateReport22B("175137918"));
        // reportMonthly.setReport23B(generateReport23B("175137918"));
        reportMonthly.setReport24B(generateReport24B("175137918"));
        reportMonthly.setReport31B(generateReport31B("175137918"));
        reportMonthly.setReport32B(generateReport32B("175137918"));
        reportMonthly.setReport33B(generateReport33B("175137918"));
        reportMonthly.setReport41B(generateReport41B("175137918"));
        reportMonthly.setReport42B(generateReport42B("175137918"));
        reportMonthly.setReport43B(generateReport43B("175137918"));

        ReportMasterType reportMasterType = generateReportMaster(date, date);
        reportMonthly.setReportMaster(reportMasterType);
        return reportMonthly;
    }

    @Operation(summary = "Generate daily B report using random values")
    @GetMapping(value = "/daily/b/random", produces = MediaType.TEXT_XML_VALUE)
    public ReportB generateBReport(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date
    ) throws DatatypeConfigurationException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReportB report = new ReportB();

        bg.fsc.schemas.report.on.b._11b.Report11B report11B = new bg.fsc.schemas.report.on.b._11b.Report11B();
        bg.fsc.schemas.report.on.b._11b.PodType pod11B = new bg.fsc.schemas.report.on.b._11b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._11b.PodType.class, pod11B);
        report11B.getPods().add(pod11B);
        bg.fsc.schemas.report.on.b._12b.Report12B report12B = new bg.fsc.schemas.report.on.b._12b.Report12B();
        bg.fsc.schemas.report.on.b._12b.PodType pod12B = new bg.fsc.schemas.report.on.b._12b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._12b.PodType.class, pod12B);
        report12B.getPods().add(pod12B);
        bg.fsc.schemas.report.on.b._13b.Report13B report13B = new bg.fsc.schemas.report.on.b._13b.Report13B();
        bg.fsc.schemas.report.on.b._13b.PodType pod13B = new bg.fsc.schemas.report.on.b._13b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._13b.PodType.class, pod13B);
        report13B.getPods().add(pod13B);
        bg.fsc.schemas.report.on.b._14b.Report14B report14B = new bg.fsc.schemas.report.on.b._14b.Report14B();
        bg.fsc.schemas.report.on.b._14b.PodType pod14B = new bg.fsc.schemas.report.on.b._14b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._14b.PodType.class, pod14B);
        report14B.getPods().add(pod14B);
        bg.fsc.schemas.report.on.b._15b.Report15B report15B = new bg.fsc.schemas.report.on.b._15b.Report15B();
        bg.fsc.schemas.report.on.b._15b.PodType pod15B = new bg.fsc.schemas.report.on.b._15b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._15b.PodType.class, pod15B);
        report15B.getPods().add(pod15B);
        bg.fsc.schemas.report.on.b._16b.Report16B report16B = new bg.fsc.schemas.report.on.b._16b.Report16B();
        bg.fsc.schemas.report.on.b._16b.PodType pod16B = new bg.fsc.schemas.report.on.b._16b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._16b.PodType.class, pod16B);
        report16B.getPods().add(pod16B);
        bg.fsc.schemas.report.on.b._18b.Report18B report18B = new bg.fsc.schemas.report.on.b._18b.Report18B();
        bg.fsc.schemas.report.on.b._18b.PodType pod18B = new bg.fsc.schemas.report.on.b._18b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._18b.PodType.class, pod18B);
        report18B.getPods().add(pod18B);
        bg.fsc.schemas.report.on.b._21b.Report21B report21B = new bg.fsc.schemas.report.on.b._21b.Report21B();
        bg.fsc.schemas.report.on.b._21b.PodType pod21B = new bg.fsc.schemas.report.on.b._21b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._21b.PodType.class, pod21B);
        report21B.getPods().add(pod21B);
        bg.fsc.schemas.report.on.b._22b.Report22B report22B = new bg.fsc.schemas.report.on.b._22b.Report22B();
        bg.fsc.schemas.report.on.b._22b.PodType pod22B = new bg.fsc.schemas.report.on.b._22b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._22b.PodType.class, pod22B);
        report22B.getPods().add(pod22B);
        bg.fsc.schemas.report.on.b._23b.Report23B report23B = new bg.fsc.schemas.report.on.b._23b.Report23B();
        bg.fsc.schemas.report.on.b._23b.PodType pod23B = new bg.fsc.schemas.report.on.b._23b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._23b.PodType.class, pod23B);
        report23B.getPods().add(pod23B);
        bg.fsc.schemas.report.on.b._24b.Report24B report24B = new bg.fsc.schemas.report.on.b._24b.Report24B();
        bg.fsc.schemas.report.on.b._24b.PodType pod24B = new bg.fsc.schemas.report.on.b._24b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._24b.PodType.class, pod24B);
        report24B.getPods().add(pod24B);
        bg.fsc.schemas.report.on.b._25b.Report25B report25B = new bg.fsc.schemas.report.on.b._25b.Report25B();
        bg.fsc.schemas.report.on.b._25b.PodType pod25B = new bg.fsc.schemas.report.on.b._25b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._25b.PodType.class, pod25B);
        report25B.getPods().add(pod25B);
        bg.fsc.schemas.report.on.b._31b.Report31B report31B = new bg.fsc.schemas.report.on.b._31b.Report31B();
        bg.fsc.schemas.report.on.b._31b.PodType pod31B = new bg.fsc.schemas.report.on.b._31b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._31b.PodType.class, pod31B);
        report31B.getPods().add(pod31B);
        bg.fsc.schemas.report.on.b._32b.Report32B report32B = new bg.fsc.schemas.report.on.b._32b.Report32B();
        bg.fsc.schemas.report.on.b._32b.PodType pod32B = new bg.fsc.schemas.report.on.b._32b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._32b.PodType.class, pod32B);
        report32B.getPods().add(pod32B);
        bg.fsc.schemas.report.on.b._33b.Report33B report33B = new bg.fsc.schemas.report.on.b._33b.Report33B();
        bg.fsc.schemas.report.on.b._33b.PodType pod33B = new bg.fsc.schemas.report.on.b._33b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._33b.PodType.class, pod33B);
        report33B.getPods().add(pod33B);
        bg.fsc.schemas.report.on.b._41b.Report41B report41B = new bg.fsc.schemas.report.on.b._41b.Report41B();
        bg.fsc.schemas.report.on.b._41b.PodType pod41B = new bg.fsc.schemas.report.on.b._41b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._41b.PodType.class, pod41B);
        report41B.getPods().add(pod41B);
        bg.fsc.schemas.report.on.b._42b.Report42B report42B = new bg.fsc.schemas.report.on.b._42b.Report42B();
        bg.fsc.schemas.report.on.b._42b.PodType pod42B = new bg.fsc.schemas.report.on.b._42b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._42b.PodType.class, pod42B);
        report42B.getPods().add(pod42B);
        bg.fsc.schemas.report.on.b._43b.Report43B report43B = new bg.fsc.schemas.report.on.b._43b.Report43B();
        bg.fsc.schemas.report.on.b._43b.PodType pod43B = new bg.fsc.schemas.report.on.b._43b.PodType();
        populateObjectTree(bg.fsc.schemas.report.on.b._43b.PodType.class, pod43B);
        report43B.getPods().add(pod43B);

        report.setReport11B(report11B);
        report.setReport12B(report12B);
        report.setReport13B(report13B);
        report.setReport14B(report14B);
        report.setReport15B(report15B);
        report.setReport16B(report16B);
        report.setReport18B(report18B);
        report.setReport21B(report21B);
        report.setReport22B(report22B);
        report.setReport23B(report23B);
        report.setReport24B(report24B);
        report.setReport25B(report25B);
        report.setReport31B(report31B);
        report.setReport32B(report32B);
        report.setReport33B(report33B);
        report.setReport41B(report41B);
        report.setReport42B(report42B);
        report.setReport43B(report43B);

        ReportMasterType reportMasterType = generateReportMaster(date, date);
        report.setReportMaster(reportMasterType);
        return report;
    }

    @Operation(summary = "Generate monthly report")
    @GetMapping(value = "/monthly", produces = MediaType.TEXT_XML_VALUE)
    public ReportMonthly generateMonthlyReport(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate to
    ) throws DatatypeConfigurationException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReportMonthly reportMonthly = new ReportMonthly();
        populateObjectTree(ReportMonthly.class, reportMonthly);
        ReportMasterType reportMasterType = generateReportMaster(from, to);
        reportMonthly.setReportMaster(reportMasterType);
        return reportMonthly;
    }

    @Operation(summary = "Generate quarterly report")
    @GetMapping(value = "/quarterly", produces = MediaType.TEXT_XML_VALUE)
    public ReportQuarterly generateQuarterlyReport(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate to
    ) throws DatatypeConfigurationException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReportQuarterly report = new ReportQuarterly();
        populateObjectTree(ReportQuarterly.class, report);
        ReportMasterType reportMasterType = generateReportMaster(from, to);
        report.setReportMaster(reportMasterType);
        return report;
    }

    @Operation(summary = "Generate yearly report")
    @GetMapping(value = "/yearly", produces = MediaType.TEXT_XML_VALUE)
    public ReportYearly generateYearlyReport(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate to
    ) throws DatatypeConfigurationException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReportYearly report = new ReportYearly();
        populateObjectTree(ReportYearly.class, report);
        ReportMasterType reportMasterType = generateReportMaster(from, to);
        report.setReportMaster(reportMasterType);
        return report;
    }

    private void populateObjectTree(Class<?> cls, Object object) throws IllegalAccessException, InvocationTargetException,
                                                                                InstantiationException, DatatypeConfigurationException {
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().contains("Master")) {
                continue;
            }
            field.setAccessible(true);
            if (field.getType().isEnum()) {
                if (field.getType().isAssignableFrom(CountryType.class)) {
                    field.set(object, CountryType.BG);
                } else if (field.getType().isAssignableFrom(CurrencyType.class)) {
                    field.set(object, CurrencyType.BGN);
                }
            } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
                field.set(object, new BigDecimal(Math.random() * 10000000, MathContext.DECIMAL64));
            } else if (field.getType().isAssignableFrom(String.class)) {
                switch (field.getName()) {
                    case "currencyCode", "basicCurrencyCode" -> field.set(object, "BGN");
                    case "currencyCode2" -> field.set(object, "EUR");
                    case "countryId" -> field.set(object, "BG");
                    case "fundEik" -> field.set(object, "175137918");
                    default -> field.set(object, "String");
                }
            } else if (field.getType().isAssignableFrom(BigInteger.class)) {
                BigInteger fieldValue = generateBigIntegerValue(field.getName());
                field.set(object, fieldValue);
            } else if (field.getType().isAssignableFrom(XMLGregorianCalendar.class)) {
                field.set(object, DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.now().toString()));
            } else if (field.getType().isAssignableFrom(double.class)
                               || field.getType().isAssignableFrom(Double.class)) {
                field.set(object, Math.random() * 10000000);
            } else if (field.getType().isAssignableFrom(List.class)) {
                populateList(object, field);
            } else {
                Object fieldObject = field.getType().getConstructors()[0].newInstance();
                field.set(object, fieldObject);
                populateObjectTree(fieldObject.getClass(), fieldObject);
            }
            field.setAccessible(false);
        }
    }

    // Ама, че rрозно... но е "само за малко"
    private void populateList(
            Object object,
            Field field) throws IllegalAccessException, InvocationTargetException, InstantiationException, DatatypeConfigurationException {
        field.setAccessible(true);
        List<Object> objects = new ArrayList<>();
        if (object.getClass().isAssignableFrom(ReportDistribFundsType.class)) {
            ReportDistribFundsRowType row = new ReportDistribFundsRowType();
            populateObjectTree(ReportDistribFundsRowType.class, row);
            row.setGender(BigInteger.ONE);
            objects.add(row);
            ReportDistribFundsRowType row2 = new ReportDistribFundsRowType();
            populateObjectTree(ReportDistribFundsRowType.class, row2);
            row2.setGender(BigInteger.TWO);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportDistribInsuredType.class)) {
            ReportDistribInsuredRowType row = new ReportDistribInsuredRowType();
            populateObjectTree(ReportDistribInsuredRowType.class, row);
            row.setGender(BigInteger.ONE);
            objects.add(row);
            ReportDistribInsuredRowType row2 = new ReportDistribInsuredRowType();
            populateObjectTree(ReportDistribInsuredRowType.class, row2);
            row2.setGender(BigInteger.TWO);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportDistribPaidInsuredType.class)) {
            ReportDistribPaidInsuredRowType row = new ReportDistribPaidInsuredRowType();
            populateObjectTree(ReportDistribPaidInsuredRowType.class, row);
            row.setGender(BigInteger.ONE);
            objects.add(row);
            ReportDistribPaidInsuredRowType row2 = new ReportDistribPaidInsuredRowType();
            populateObjectTree(ReportDistribPaidInsuredRowType.class, row2);
            row2.setGender(BigInteger.TWO);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.riskvalue.instruments.FundType.class)) {
            ReportRiskValueInstrumentsRowType row = new ReportRiskValueInstrumentsRowType();
            populateObjectTree(ReportRiskValueInstrumentsRowType.class, row);
            objects.add(row);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.riskvalue.nonfulfilmentobligations.FundType.class)) {
            ReportRiskValueNonFulfilmentObligationsRowType row =
                    new ReportRiskValueNonFulfilmentObligationsRowType();
            populateObjectTree(ReportRiskValueNonFulfilmentObligationsRowType.class, row);
            objects.add(row);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._11b.FundType.class)) {
            bg.fsc.schemas.report.on.b._11b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._11b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._11b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._12b.FundType.class)) {
            bg.fsc.schemas.report.on.b._12b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._12b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._12b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._12b.FundType.class)) {
            bg.fsc.schemas.report.on.b._12b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._12b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._12b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._13b.FundType.class)) {
            bg.fsc.schemas.report.on.b._13b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._13b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._13b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._14b.FundType.class)) {
            bg.fsc.schemas.report.on.b._14b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._14b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._14b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._15b.FundType.class)) {
            bg.fsc.schemas.report.on.b._15b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._15b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._15b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._16b.FundType.class)) {
            bg.fsc.schemas.report.on.b._16b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._16b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._16b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._18b.FundType.class)) {
            bg.fsc.schemas.report.on.b._18b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._18b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._18b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._21b.FundType.class)) {
            bg.fsc.schemas.report.on.b._21b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._21b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._21b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._22b.FundType.class)) {
            bg.fsc.schemas.report.on.b._22b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._22b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._22b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._23b.FundType.class)) {
            bg.fsc.schemas.report.on.b._23b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._23b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._23b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._24b.FundType.class)) {
            bg.fsc.schemas.report.on.b._24b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._24b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._24b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._25b.FundType.class)) {
            bg.fsc.schemas.report.on.b._25b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._25b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._25b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._31b.FundType.class)) {
            bg.fsc.schemas.report.on.b._31b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._31b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._31b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._32b.FundType.class)) {
            bg.fsc.schemas.report.on.b._32b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._32b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._32b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._33b.FundType.class)) {
            bg.fsc.schemas.report.on.b._33b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._33b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._33b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._41b.FundType.class)) {
            bg.fsc.schemas.report.on.b._41b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._41b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._41b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._42b.FundType.class)) {
            bg.fsc.schemas.report.on.b._42b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._42b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._42b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
            bg.fsc.schemas.report.on.b._42b.FundTypeRow fundTypeRow1 = new bg.fsc.schemas.report.on.b._42b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._42b.FundTypeRow.class, fundTypeRow1);
            objects.add(fundTypeRow);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.b._43b.FundType.class)) {
            bg.fsc.schemas.report.on.b._43b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._43b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._43b.FundTypeRow.class, fundTypeRow);
            objects.add(fundTypeRow);
            bg.fsc.schemas.report.on.b._43b.FundTypeRow fundTypeRow1 = new bg.fsc.schemas.report.on.b._43b.FundTypeRow();
            populateObjectTree(bg.fsc.schemas.report.on.b._43b.FundTypeRow.class, fundTypeRow1);
            objects.add(fundTypeRow1);
        } else if (object.getClass().isAssignableFrom(ReportInvestPensionReserveA1Type.class)) {
            ReportInvestPensionReserveA1RowType row = new ReportInvestPensionReserveA1RowType();
            populateObjectTree(ReportInvestPensionReserveA1RowType.class, row);
            objects.add(row);
            ReportInvestPensionReserveA1RowType row2 = new ReportInvestPensionReserveA1RowType();
            populateObjectTree(ReportInvestPensionReserveA1RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportInvestPensionReserveA2Type.class)) {
            ReportInvestPensionReserveA2RowType row = new ReportInvestPensionReserveA2RowType();
            populateObjectTree(ReportInvestPensionReserveA2RowType.class, row);
            objects.add(row);
            ReportInvestPensionReserveA2RowType row2 = new ReportInvestPensionReserveA2RowType();
            populateObjectTree(ReportInvestPensionReserveA2RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportInvestPensionReserveA3Type.class)) {
            ReportInvestPensionReserveA3RowType row = new ReportInvestPensionReserveA3RowType();
            populateObjectTree(ReportInvestPensionReserveA3RowType.class, row);
            objects.add(row);
            ReportInvestPensionReserveA3RowType row2 = new ReportInvestPensionReserveA3RowType();
            populateObjectTree(ReportInvestPensionReserveA3RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportInvestPensionReserveA4Type.class)) {
            ReportInvestPensionReserveA4RowType row = new ReportInvestPensionReserveA4RowType();
            populateObjectTree(ReportInvestPensionReserveA4RowType.class, row);
            objects.add(row);
            ReportInvestPensionReserveA4RowType row2 = new ReportInvestPensionReserveA4RowType();
            populateObjectTree(ReportInvestPensionReserveA4RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportReserveGuaranteeGrossContrib1A1Type.class)) {
            ReportReserveGuaranteeGrossContrib1A1RowType row = new ReportReserveGuaranteeGrossContrib1A1RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A1RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeGrossContrib1A1RowType row2 = new ReportReserveGuaranteeGrossContrib1A1RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A1RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportReserveGuaranteeGrossContrib1A2Type.class)) {
            ReportReserveGuaranteeGrossContrib1A2RowType row = new ReportReserveGuaranteeGrossContrib1A2RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A2RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeGrossContrib1A2RowType row2 = new ReportReserveGuaranteeGrossContrib1A2RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A2RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportReserveGuaranteeGrossContrib1A3Type.class)) {
            ReportReserveGuaranteeGrossContrib1A3RowType row = new ReportReserveGuaranteeGrossContrib1A3RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A3RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeGrossContrib1A3RowType row2 = new ReportReserveGuaranteeGrossContrib1A3RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A3RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(ReportReserveGuaranteeGrossContrib1A4Type.class)) {
            ReportReserveGuaranteeGrossContrib1A4RowType row = new ReportReserveGuaranteeGrossContrib1A4RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A4RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeGrossContrib1A4RowType row2 = new ReportReserveGuaranteeGrossContrib1A4RowType();
            populateObjectTree(ReportReserveGuaranteeGrossContrib1A4RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a1.FundType.class)) {
            ReportReserveGuaranteeMinYieldOwn1A1RowType row = new ReportReserveGuaranteeMinYieldOwn1A1RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A1RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeMinYieldOwn1A1RowType row2 = new ReportReserveGuaranteeMinYieldOwn1A1RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A1RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a2.FundType.class)) {
            ReportReserveGuaranteeMinYieldOwn1A2RowType row = new ReportReserveGuaranteeMinYieldOwn1A2RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A2RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeMinYieldOwn1A2RowType row2 = new ReportReserveGuaranteeMinYieldOwn1A2RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A2RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a3.FundType.class)) {
            ReportReserveGuaranteeMinYieldOwn1A3RowType row = new ReportReserveGuaranteeMinYieldOwn1A3RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A3RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeMinYieldOwn1A3RowType row2 = new ReportReserveGuaranteeMinYieldOwn1A3RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A3RowType.class, row2);
            objects.add(row2);
        } else if (object.getClass().isAssignableFrom(bg.fsc.schemas.report.on.reserve.guarantee.min.yield.own1.a4.FundType.class)) {
            ReportReserveGuaranteeMinYieldOwn1A4RowType row = new ReportReserveGuaranteeMinYieldOwn1A4RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A4RowType.class, row);
            objects.add(row);
            ReportReserveGuaranteeMinYieldOwn1A4RowType row2 = new ReportReserveGuaranteeMinYieldOwn1A4RowType();
            populateObjectTree(ReportReserveGuaranteeMinYieldOwn1A4RowType.class, row2);
            objects.add(row2);
        }
        field.set(object, objects);
        field.setAccessible(false);
    }

    private BigInteger generateBigIntegerValue(String fieldName) {
        BigInteger value;
        Random random = new Random();
        switch (fieldName) {
            case "baseDays" -> {
                int[] intArray = {360, 365, 366};
                int idx = random.nextInt(intArray.length);
                value = BigInteger.valueOf(intArray[idx]);
            }
            case "typeMoney" -> {
                int[] intArray = {3, 4, 5};
                int idx = random.nextInt(intArray.length);
                value = BigInteger.valueOf(intArray[idx]);
            }
            case "typeShare", "basicAsset" -> value = BigInteger.valueOf(random.nextInt(82));
            case "typeShareBp" -> value = BigInteger.valueOf(random.nextInt(62 - 1) + 1);
            case "operationId" -> value = BigInteger.valueOf(random.nextInt(7 - 1) + 1);
            case "transactionId" -> value = BigInteger.valueOf(random.nextInt(15 - 1) + 1);
            case "typeRepo" -> value = BigInteger.valueOf(random.nextInt(2 - 1) + 1);
            case "indexMarket", "regulatedMarketIndex" -> value = BigInteger.valueOf(random.nextInt(90));
            case "evaluationMethod" -> value = BigInteger.valueOf(random.nextInt(14 - 1) + 1);
            case "source", "sourceId" -> value = BigInteger.valueOf(random.nextInt(9 - 1) + 1);
            case "typeFinInstrument" -> value = BigInteger.valueOf(random.nextInt(82));
            case "ratingAgency" -> value = BigInteger.valueOf(random.nextInt(4 - 1) + 1);
            case "creditRating", "rating", "ratingRegulatedMarket" -> value = BigInteger.valueOf(random.nextInt(64 - 1) + 1);
            case "tradeMarket", "regulatedMarket" -> value = BigInteger.valueOf(random.nextInt(58));
            case "debtObligationCode" -> value = BigInteger.valueOf(random.nextInt(8 - 1) + 1);
            case "optionsFutures" -> value = BigInteger.valueOf(random.nextInt(3 - 1) + 1);
            case "transactionFutures" -> value = BigInteger.valueOf(random.nextInt(5 - 1) + 1);
            case "transactionForwards" -> value = BigInteger.valueOf(random.nextInt(4 - 1) + 1);
            case "transactionSwap" -> value = BigInteger.valueOf(random.nextInt(8 - 1) + 1);
            case "purchaseSale" -> value = BigInteger.valueOf(random.nextInt(2 - 1) + 1);
            case "methodId" -> value = BigInteger.valueOf(random.nextInt(14 - 1) + 1);
            case "receivePayInterestRate" -> value = BigInteger.valueOf(random.nextInt(2 - 1) + 1);
            case "longShort" -> value = BigInteger.valueOf(random.nextInt(2 - 1) + 1);
            default -> value = new BigInteger(4, random);
        }
        return value;
    }

    private ReportMasterType generateReportMaster(LocalDate from, LocalDate to) throws DatatypeConfigurationException {
        ReportMasterType reportMasterType = new ReportMasterType();
        reportMasterType.setReportEik("12345");
        reportMasterType.setReportPeriodFrom(DatatypeFactory.newInstance().newXMLGregorianCalendar(from.toString()));
        reportMasterType.setReportPeriodTo(DatatypeFactory.newInstance().newXMLGregorianCalendar(to.toString()));
        reportMasterType.setReportPreparedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(from.toString()));
        SignatureType signatureType = new SignatureType();
        signatureType.setName("Ivan");
        signatureType.setSurname("Ivanov");
        reportMasterType.setReportPreparedBy(signatureType);
        reportMasterType.getReportRepresents().add(signatureType);
        return reportMasterType;
    }

    private Report12B generateReport12B(String podEik) {
        Report12B report = new Report12B();
        bg.fsc.schemas.report.on.b._12b.PodType pod = new bg.fsc.schemas.report.on.b._12b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._12b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._12b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._12b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._12b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._12b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._12b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);

        jdbcTemplate.query(
                "select FUND_ID,FUND_TYPE_ID,TYPE_SHARE,TRANSACTION_ID,INVESTMAN,INVESTMAN_CODE,EMITENT,ISIN,DATE_EMISSION,"
                        + "DATE_TRANSACTION,RATING,RATING_AGENCY,NUMBER_SHARE,CURRENCY_CODE,NOM_CURRENCY,NOM_LV,VALUE_BUY "
                        + "from PNL_REPORT_12B where bank_id ||'|'||  date_report = ( "
                        + "select bank_id ||'|'|| d from ( "
                        + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_12B where fund_id <> 16 "
                        + "group by bank_id, date_report "
                        + "order by 3 desc, 2 desc) where rownum = 1)",
                (rs) -> {
                    bg.fsc.schemas.report.on.b._12b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._12b.FundTypeRow();

                    fundTypeRow.setTypeShare(BigInteger.valueOf(rs.getLong(3)));
                    fundTypeRow.setTransactionId(BigInteger.valueOf(rs.getLong(4)));
                    fundTypeRow.setInvestman(rs.getString(5));
                    fundTypeRow.setInvestmanCode(rs.getString(6));
                    fundTypeRow.setEmitent(rs.getString(7));
                    fundTypeRow.setIsin(rs.getString(8));
                    fundTypeRow.setDateEmission(getXmlGregorianCalendar(rs.getDate(9)));
                    fundTypeRow.setDateTransaction(getXmlGregorianCalendar(rs.getDate(10)));
                    fundTypeRow.setRating(rs.getString(11));

                    fundTypeRow.setRatingAgency(BigInteger.valueOf(rs.getLong(12)));
                    if (fundTypeRow.getRatingAgency().equals(BigInteger.ZERO)) {
                        fundTypeRow.setRatingAgency(null);
                    }
                    fundTypeRow.setNumberShare(BigInteger.valueOf(rs.getLong(13)));
                    fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(14)));
                    fundTypeRow.setNomCurrency(rs.getDouble(15));
                    fundTypeRow.setNomLv(rs.getDouble(16));
                    fundTypeRow.setValueBuy(rs.getDouble(17));

                    CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                    switch (fundTypeId) {
                        case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                        case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                        case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                        case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                        case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                        case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                        default -> {
                        }
                    }
                });

        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report13B generateReport13B(String podEik) {
        Report13B report = new Report13B();
        bg.fsc.schemas.report.on.b._13b.PodType pod = new bg.fsc.schemas.report.on.b._13b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._13b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._13b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._13b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._13b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._13b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._13b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,OPERATION_ID,COMMERCIAL_BANK_DESCRIPTION,COMMERCIAL_BANK_CODE_2,N_CONTRACT_ANNEX,"
                    + "DATE_CONTRACT_ANNEX,DATE_INTEREST_BEGIN,DATE_INTEREST_END,CURRENCY_CODE,VALUE_CURRENCY,VALUE_LV from "
                    + "PNL_REPORT_13B "
                    + "where bank_id ||'|'||  date_report = ( "
                    + "select bank_id ||'|'|| d from ( "
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_13B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                FundTypeRow fundTypeRow = new FundTypeRow();

                fundTypeRow.setOperationId(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setCommercialBankDescription(rs.getString(4));
                fundTypeRow.setCommercialBankCode2(rs.getString(5));
                fundTypeRow.setNContractAnnex(rs.getString(6));
                fundTypeRow.setDateContractAnnex(getXmlGregorianCalendar(rs.getDate(7)));
                fundTypeRow.setDateInterestBegin(getXmlGregorianCalendar(rs.getDate(8)));
                fundTypeRow.setDateInterestEnd(getXmlGregorianCalendar(rs.getDate(9)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(10)));
                fundTypeRow.setValueCurrency(rs.getDouble(11));
                fundTypeRow.setValueLv(rs.getDouble(12));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report14B generateReport14B(String podEik) {
        Report14B report = new Report14B();
        bg.fsc.schemas.report.on.b._14b.PodType pod = new bg.fsc.schemas.report.on.b._14b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._14b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._14b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._14b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._14b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._14b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._14b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,NUMBER_ID,TRANSACTION_ID,INVESTMAN,INVESTMAN_CODE,DATE_OPTION_BEGIN,DATE_OPTION_END, "
                    + "BASE_ACTIV,NUMBER_CONTRACT,CURRENCY_CODE,VALUE_ACT,VOLUME_CONTRACT,BONUS_CURRENCY,BONUS_LV "
                    + "from PNL_REPORT_14B "
                    + "where bank_id ||'|'||  date_report = ( "
                    + "select bank_id ||'|'|| d from ( "
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_14B where "
                    + "fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._14b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._14b.FundTypeRow();
                fundTypeRow.setTransactionId(BigInteger.valueOf(rs.getLong(4)));
                fundTypeRow.setInvestman(rs.getString(5));
                fundTypeRow.setInvestmanCode(rs.getString(6));
                fundTypeRow.setDateOptionBegin(getXmlGregorianCalendar(rs.getDate(7)));
                fundTypeRow.setDateOptionEnd(getXmlGregorianCalendar(rs.getDate(8)));
                fundTypeRow.setBaseActiv(rs.getString(9));
                fundTypeRow.setNumberContract(BigInteger.valueOf(rs.getLong(10)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(11)));
                fundTypeRow.setValueAct(rs.getDouble(12));
                fundTypeRow.setVolumeContract(BigInteger.valueOf(rs.getLong(13)));
                fundTypeRow.setBonusCurrency(rs.getDouble(14));
                fundTypeRow.setBonusLv(rs.getDouble(15));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report15B generateReport15B(String podEik) {
        Report15B report = new Report15B();
        bg.fsc.schemas.report.on.b._15b.PodType pod = new bg.fsc.schemas.report.on.b._15b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._15b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._15b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._15b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._15b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._15b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._15b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query("select FUND_ID,FUND_TYPE_ID,TRANSACTION_ID,DATE_TRANSACTION,TRADE_SIDE,IMOT_DOC,IMOT_ADDRESS,IMOT_ID,"
                                   + "TOTAL_LV,COUNTRY_ID,CURRENCY_CODE,VALUE_BUY_CURRENCY from PNL_REPORT_15B where bank_id ||'|'||  "
                                   + "date_report = ( "
                                   + "select bank_id ||'|'|| d from ( "
                                   + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_15B where "
                                   + "fund_id <> 16 "
                                   + "group by bank_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._15b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._15b.FundTypeRow();
                fundTypeRow.setTransactionId(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setDateTransaction(getXmlGregorianCalendar(rs.getDate(4)));
                fundTypeRow.setTradeSide(rs.getString(5));
                fundTypeRow.setImotDoc(rs.getString(6));
                fundTypeRow.setImotAddress(rs.getString(7));
                fundTypeRow.setImotId(rs.getString(8));
                fundTypeRow.setTotalLv(rs.getDouble(9));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(10)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(11)));
                fundTypeRow.setValueBuyCurrency(rs.getDouble(12));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report16B generateReport16B(String podEik) {
        Report16B report = new Report16B();
        bg.fsc.schemas.report.on.b._16b.PodType pod = new bg.fsc.schemas.report.on.b._16b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._16b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._16b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._16b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._16b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._16b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._16b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,INCOME_DAY,EXPENSE_DAY,TOTAL_DAY,CURRENCY_CODE from PNL_REPORT_16B "
                    + "where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_16B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._16b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._16b.FundTypeRow();
                fundTypeRow.setIncomeDay(rs.getDouble(3));
                fundTypeRow.setExpenseDay(rs.getDouble(4));
                fundTypeRow.setTotalDay(rs.getDouble(5));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(6)));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report18B generateReport18B(String podEik) {
        Report18B report = new Report18B();
        bg.fsc.schemas.report.on.b._18b.PodType pod = new bg.fsc.schemas.report.on.b._18b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._18b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._18b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._18b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._18b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._18b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._18b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,TYPE_REPO,TYPE_SHARE_BP,CONTRACTOR,DATE_TRANSACTION,EMITENT,GARANT,MANAGEMENT_COMPANY,"
                    + "ISIN,NUMBER_SHARE,CURRENCY_CODE,NOM_CURRENCY,NOM_LV,DATE_REPO_TRANSACTION,VALUE_REPO_CURRENCY,VALUE_REPO_LV,"
                    + "VALUE_REPO_CURRENCY_OPPOSITE,VALUE_REPO_CURRENCY_LV,DATE_REPO_TRANSACTION_OPPOSITE from PNL_REPORT_18B "
                    + "where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_18B where fund_id <> 16 "
                    + "group by bank_id, date_report " + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._18b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._18b.FundTypeRow();
                fundTypeRow.setTypeRepo(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setTypeShareBp(BigInteger.valueOf(rs.getLong(4)));
                fundTypeRow.setContractor(rs.getString(5));
                fundTypeRow.setDateTransaction(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setEmitent(rs.getString(7));
                fundTypeRow.setGarant(rs.getString(8));
                fundTypeRow.setManagementCompany(rs.getString(9));
                fundTypeRow.setIsin(rs.getString(10));
                fundTypeRow.setNumberShare(rs.getDouble(11));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(12)));
                fundTypeRow.setNomCurrency(rs.getDouble(13));
                fundTypeRow.setNomLv(rs.getDouble(14));
                fundTypeRow.setDateRepoTransaction(getXmlGregorianCalendar(rs.getDate(15)));
                fundTypeRow.setValueRepoCurrency(rs.getDouble(16));
                fundTypeRow.setValueRepoLv(rs.getDouble(17));
                fundTypeRow.setValueRepoCurrencyOpposite(rs.getDouble(18));
                fundTypeRow.setValueRepoCurrencyLv(rs.getDouble(19));
                fundTypeRow.setDateRepoTransactionOpposite(getXmlGregorianCalendar(rs.getDate(20)));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report21B generateReport21B(String podEik) {
        Report21B report = new Report21B();
        bg.fsc.schemas.report.on.b._21b.PodType pod = new bg.fsc.schemas.report.on.b._21b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._21b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._21b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._21b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._21b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._21b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._21b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,TYPE_SHARE_BP,EMITENT,ISIN,DATE_EMISSION,NUMBER_CERTIFICATE,NUMBER_SHARE,CURRENCY_CODE,"
                    + "NOM_CURRENCY,NOM_LV,GARANT,MANAGEMENT_COMPANY,COUNTRY_ID,TRADE_MARKET,INDEX_MARKET,RATING,RATING_AGENCY,"
                    + "RATING_ID,RATING_AGENCY_ID from PNL_REPORT_21B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_21B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._21b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._21b.FundTypeRow();
                fundTypeRow.setTypeShareBp(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setEmitent(rs.getString(4));
                fundTypeRow.setIsin(rs.getString(5));
                fundTypeRow.setDateEmission(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setNumberCertificate(rs.getString(7));
                fundTypeRow.setNumberShare(rs.getDouble(8));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(9)));
                fundTypeRow.setNomCurrency(rs.getDouble(10));
                fundTypeRow.setNomLv(rs.getDouble(11));
                fundTypeRow.setGarant(rs.getString(12));
                fundTypeRow.setManagementCompany(rs.getString(13));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(14)));
                fundTypeRow.setTradeMarket(BigInteger.valueOf(rs.getLong(15)));
                fundTypeRow.setIndexMarket(BigInteger.valueOf(rs.getLong(16)));
                fundTypeRow.setRating(BigInteger.valueOf(rs.getLong(17)));
                if (fundTypeRow.getRating().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRating(null);
                }
                fundTypeRow.setRatingAgency(BigInteger.valueOf(rs.getLong(18)));
                if (fundTypeRow.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRatingAgency(null);
                }

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report22B generateReport22B(String podEik) {
        Report22B report = new Report22B();
        bg.fsc.schemas.report.on.b._22b.PodType pod = new bg.fsc.schemas.report.on.b._22b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._22b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._22b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._22b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._22b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._22b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._22b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,TYPE_SHARE,EMITENT,ISIN,DATE_EMISSION,RATING,RATING_AGENCY,INDX,NUMBER_CERTIFICATE,"
                    + "NUMBER_SHARE,CURRENCY_CODE,NOM_CURRENCY,NOM_LV from PNL_REPORT_22B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_22B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._22b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._22b.FundTypeRow();
                fundTypeRow.setTypeShareBp(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setEmitent(rs.getString(4));
                fundTypeRow.setIsin(rs.getString(5));
                fundTypeRow.setDateEmission(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setRating(BigInteger.valueOf(rs.getLong(7)));
                if (fundTypeRow.getRating().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRating(null);
                }
                fundTypeRow.setRatingAgency(BigInteger.valueOf(rs.getLong(8)));
                if (fundTypeRow.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRatingAgency(null);
                }
                fundTypeRow.setIndx(rs.getString(9));
                fundTypeRow.setNumberCertificate(rs.getString(10));
                fundTypeRow.setNumberShare(BigInteger.valueOf(rs.getLong(11)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(12)));
                fundTypeRow.setNomCurrency(rs.getDouble(13));
                fundTypeRow.setNomLv(rs.getDouble(14));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report23B generateReport23B(String podEik) {
        Report23B report = new Report23B();
        bg.fsc.schemas.report.on.b._23b.PodType pod = new bg.fsc.schemas.report.on.b._23b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._23b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._23b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._23b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._23b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._23b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._23b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,COMMERCIAL_BANK_DESCRIPTION,COMMERCIAL_BANK_CODE_2,N_CONTRACT_ANNEX,DATE_CONTRACT_ANNEX,"
                    + "DATE_INTEREST_BEGIN,DATE_INTEREST_END,CURRENCY_CODE,VALUE_CURRENCY,VALUE_LV,COUNTRY_ID,RATING,RATING_AGENCY,"
                    + "RATING_ID,RATING_AGENCY_ID from PNL_REPORT_23B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_23B where "
                    + "fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._23b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._23b.FundTypeRow();
                fundTypeRow.setCommercialBankDescription(rs.getString(3));
                fundTypeRow.setCommercialBankCode2(rs.getString(4));
                fundTypeRow.setNContractAnnex(rs.getString(5));
                fundTypeRow.setDateContractAnnex(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setDateInterestBegin(getXmlGregorianCalendar(rs.getDate(7)));
                fundTypeRow.setDateInterestEnd(getXmlGregorianCalendar(rs.getDate(8)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(9)));
                fundTypeRow.setValueCurrency(rs.getDouble(10));
                fundTypeRow.setValueLv(rs.getDouble(11));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(12)));
                fundTypeRow.setRating(BigInteger.valueOf(rs.getLong(13)));
                if (fundTypeRow.getRating().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRating(null);
                }
                fundTypeRow.setRatingAgency(BigInteger.valueOf(rs.getLong(14)));
                if (fundTypeRow.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRatingAgency(null);
                }

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report24B generateReport24B(String podEik) {
        Report24B report = new Report24B();
        bg.fsc.schemas.report.on.b._24b.PodType pod = new bg.fsc.schemas.report.on.b._24b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._24b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._24b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._24b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._24b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._24b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._24b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,NUMBER_ID,DATE_OPTION_BEGIN,DATE_OPTION_END,BASE_ACTIV,NUMBER_CONTRACT,CURRENCY_CODE,"
                    + "VALUE_ACT,VOLUME_CONTRACT,BONUS_CURRENCY,BONUS_LV from PNL_REPORT_24B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_24B where "
                    + "fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._24b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._24b.FundTypeRow();
                fundTypeRow.setDateOptionBegin(getXmlGregorianCalendar(rs.getDate(4)));
                fundTypeRow.setDateOptionEnd(getXmlGregorianCalendar(rs.getDate(5)));
                fundTypeRow.setBaseActiv(rs.getString(6));
                fundTypeRow.setNumberContract(BigInteger.valueOf(rs.getLong(7)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(8)));
                fundTypeRow.setValueAct(rs.getDouble(9));
                fundTypeRow.setVolumeContract(BigInteger.valueOf(rs.getLong(10)));
                fundTypeRow.setBonusCurrency(rs.getDouble(11));
                fundTypeRow.setBonusLv(rs.getDouble(12));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report25B generateReport25B(String podEik) {
        Report25B report = new Report25B();
        bg.fsc.schemas.report.on.b._25b.PodType pod = new bg.fsc.schemas.report.on.b._25b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._25b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._25b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._25b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._25b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._25b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._25b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,DATE_TRANSACTION,IMOT_DOC,IMOT_ADDRESS,TOTAL_LV,IMOT_DESCRIPTION,COUNTRY_ID,CURRENCY_CODE,"
                    + "VALUE_BUY_CURRENCY from PNL_REPORT_25B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_25B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._25b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._25b.FundTypeRow();
                fundTypeRow.setDateTransaction(getXmlGregorianCalendar(rs.getDate(3)));
                fundTypeRow.setImotDoc(rs.getString(4));
                fundTypeRow.setImotAddress(rs.getString(5));
                fundTypeRow.setTotalLv(rs.getDouble(6));
                fundTypeRow.setImotDescription(rs.getString(7));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(8)));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(9)));
                fundTypeRow.setValueBuyCurrency(rs.getDouble(10));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report31B generateReport31B(String podEik) {
        Report31B report = new Report31B();
        bg.fsc.schemas.report.on.b._31b.PodType pod = new bg.fsc.schemas.report.on.b._31b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._31b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._31b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._31b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._31b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._31b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._31b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,OPTIONS_FUTURES,TRANSACTION_FUTURES,COUNTRY_ID,TRADE_MARKET,BFB_NAME,DATE_CONTRACT,"
                    + "DATE_EXPIRY_CONTRACT,BASIC_ASSET,VALUE_BASIC_ASSET,VOLUME_CONTRACT,NUMBER_CONTRACT,SECURITY_DEPOSIT,"
                    + "CURRENCY_CODE,VALUE_CONTRACT_CURRENCY,VALUE_CONTRACT_LEVA from PNL_REPORT_31B "
                    + "where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_31B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._31b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._31b.FundTypeRow();
                fundTypeRow.setOptionsFutures(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setTransactionFutures(BigInteger.valueOf(rs.getLong(4)));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(5)));
                fundTypeRow.setTradeMarket(BigInteger.valueOf(rs.getLong(6)));
                fundTypeRow.setBfbName(rs.getString(7));
                fundTypeRow.setDateContract(getXmlGregorianCalendar(rs.getDate(8)));
                fundTypeRow.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(9)));
                fundTypeRow.setBasicAsset(rs.getString(10));
                fundTypeRow.setValueBasicAsset(rs.getDouble(11));
                fundTypeRow.setVolumeContract(rs.getDouble(12));
                fundTypeRow.setNumberContract(BigInteger.valueOf(rs.getLong(13)));
                fundTypeRow.setSecurityDeposit(rs.getDouble(14));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(15)));
                fundTypeRow.setValueContractCurrency(rs.getDouble(16));
                fundTypeRow.setValueContractLeva(rs.getDouble(17));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report32B generateReport32B(String podEik) {
        Report32B report = new Report32B();
        bg.fsc.schemas.report.on.b._32b.PodType pod = new bg.fsc.schemas.report.on.b._32b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._32b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._32b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._32b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._32b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._32b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._32b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,TRANSACTION_FORWARDS,COUNTRY_ID,CONTRAGENT,RATING,RATING_AGENCY,DATE_CONTRACT,"
                    + "DATE_EXPIRY_CONTRACT,CURRENCY_PAIR,BASIC_CURRENCY_CODE,RATE_FORWARD_CONTRACT,VOLUME_CONTRACT_CURRENCY,"
                    + "SECURITY_DEPOSIT,CURRENCY_CODE,VALUE_CONTRACT_CURRENCY,VALUE_CONTRACT_LEVA from PNL_REPORT_32B "
                    + "where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_32B where "
                    + "fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._32b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._32b.FundTypeRow();
                fundTypeRow.setTransactionForwards(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(4)));
                fundTypeRow.setContragent(rs.getString(5));
                fundTypeRow.setRating(BigInteger.valueOf(rs.getLong(6)));
                if (fundTypeRow.getRating().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRating(null);
                }
                fundTypeRow.setRatingAgency(BigInteger.valueOf(rs.getLong(7)));
                if (fundTypeRow.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRatingAgency(null);
                }
                fundTypeRow.setDateContract(getXmlGregorianCalendar(rs.getDate(8)));
                fundTypeRow.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(9)));
                fundTypeRow.setCurrencyPair(rs.getString(10));
                fundTypeRow.setBasicCurrencyCode(rs.getString(11));
                fundTypeRow.setRateForwardContract(rs.getDouble(12));
                fundTypeRow.setVolumeContractCurrency(rs.getDouble(13));
                fundTypeRow.setSecurityDeposit(rs.getDouble(14));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(15)));
                fundTypeRow.setValueContractCurrency(rs.getDouble(16));
                fundTypeRow.setValueContractLeva(rs.getDouble(17));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report33B generateReport33B(String podEik) {
        Report33B report = new Report33B();
        bg.fsc.schemas.report.on.b._33b.PodType pod = new bg.fsc.schemas.report.on.b._33b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._33b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._33b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._33b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._33b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._33b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._33b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,TRANSACTION_SWAP,COUNTRY_ID,CONTRAGENT,RATING,RATING_AGENCY,DATE_CONTRACT,"
                    + "DATE_EXPIRY_CONTRACT,BASIC_ASSET,NUMBER_PAYMENT_END_CONTRACT,PERIOD_PAYMENT,VOLUME_CONTRACT,FIXED_INTEREST_RATE,"
                    + "REFERE_INTEREST_RATE_FLOATING,SECURITY_DEPOSIT,CURRENCY_CODE,VALUE_CONTRACT_CURRENCY,VALUE_CONTRACT_LEVA "
                    + "from PNL_REPORT_33B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_33B where "
                    + "fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._33b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._33b.FundTypeRow();
                fundTypeRow.setTransactionSwap(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setCountry(CountryType.fromValue(rs.getString(4)));
                fundTypeRow.setContragent(rs.getString(5));
                fundTypeRow.setRating(BigInteger.valueOf(rs.getLong(6)));
                if (fundTypeRow.getRating().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRating(null);
                }
                fundTypeRow.setRatingAgency(BigInteger.valueOf(rs.getLong(7)));
                if (fundTypeRow.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundTypeRow.setRatingAgency(null);
                }
                fundTypeRow.setDateContract(getXmlGregorianCalendar(rs.getDate(8)));
                fundTypeRow.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(9)));
                fundTypeRow.setBasicAsset(rs.getString(10));
                fundTypeRow.setNumberPaymentEndContract(BigInteger.valueOf(rs.getLong(11)));
                fundTypeRow.setPeriodPayment(rs.getString(12));
                fundTypeRow.setVolumeContract(rs.getDouble(13));
                fundTypeRow.setFixedInterestRate(rs.getDouble(14));
                fundTypeRow.setRefereInterestRateFloating(rs.getString(15));
                fundTypeRow.setSecurityDeposit(rs.getDouble(16));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(17)));
                fundTypeRow.setValueContractCurrency(rs.getDouble(18));
                fundTypeRow.setValueContractLeva(rs.getDouble(19));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report41B generateReport41B(String podEik) {
        Report41B report = new Report41B();
        bg.fsc.schemas.report.on.b._41b.PodType pod = new bg.fsc.schemas.report.on.b._41b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._41b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._41b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._41b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._41b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._41b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._41b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,OPTIONS_FUTURES,LONG_SHORT,TRADE_MARKET,DATE_CONTRACT,DATE_EXPIRY_CONTRACT,BASIC_ASSET,"
                    + "VALUE_BASIC_ASSET,VOLUME_CONTRACT,NUMBER_CONTRACT,SECURITY_DEPOSIT from PNL_REPORT_41B "
                    + "where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_41B where "
                    + "fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._41b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._41b.FundTypeRow();
                fundTypeRow.setOptionsFutures(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setLongShort(BigInteger.valueOf(rs.getLong(4)));
                fundTypeRow.setTradeMarket(BigInteger.valueOf(rs.getLong(5)));
                fundTypeRow.setDateContract(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(7)));
                fundTypeRow.setBasicAsset(rs.getString(8));
                fundTypeRow.setValueBasicAsset(rs.getDouble(9));
                fundTypeRow.setVolumeContract(rs.getDouble(10));
                fundTypeRow.setNumberContract(BigInteger.valueOf(rs.getLong(11)));
                fundTypeRow.setSecurityDeposit(rs.getDouble(12));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report42B generateReport42B(String podEik) {
        Report42B report = new Report42B();
        bg.fsc.schemas.report.on.b._42b.PodType pod = new bg.fsc.schemas.report.on.b._42b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._42b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._42b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._42b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._42b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._42b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._42b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,LONG_SHORT,CONTRAGENT,DATE_CONTRACT,DATE_EXPIRY_CONTRACT,CURRENCY_PAIR,CURRENCY_CODE,"
                    + "RATE_FORWARD_CONTRACT,VOLUME_CONTRACT_CURRENCY,SECURITY_DEPOSIT from PNL_REPORT_42B "
                    + "where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_42B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._42b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._42b.FundTypeRow();
                fundTypeRow.setLongShort(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setContragent(rs.getString(4));
                fundTypeRow.setDateContract(getXmlGregorianCalendar(rs.getDate(5)));
                fundTypeRow.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setCurrencyPair(rs.getString(7));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(8)));
                fundTypeRow.setRateForwardContract(rs.getDouble(9));
                fundTypeRow.setVolumeContractCurrency(rs.getDouble(10));
                fundTypeRow.setSecurityDeposit(rs.getDouble(11));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report43B generateReport43B(String podEik) {
        Report43B report = new Report43B();
        bg.fsc.schemas.report.on.b._43b.PodType pod = new bg.fsc.schemas.report.on.b._43b.PodType();
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._43b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._43b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._43b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._43b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._43b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._43b.FundType());
        pod.setFundEik(podEik);
        report.getPods().add(pod);
        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,LONG_SHORT,CONTRAGENT,DATE_CONTRACT,DATE_EXPIRY_CONTRACT,BASIC_ASSET,"
                    + "NUMBER_PAYMENT_END_CONTRACT,PERIOD_PAYMENT,VOLUME_CONTRACT,FIXED_INTEREST_RATE,REFERE_INTEREST_RATE_FLOATING,"
                    + "SECURITY_DEPOSIT from PNL_REPORT_43B where bank_id ||'|'||  date_report = ("
                    + "select bank_id ||'|'|| d from ("
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_43B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.b._43b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._43b.FundTypeRow();
                fundTypeRow.setLongShort(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setContragent(rs.getString(4));
                fundTypeRow.setDateContract(getXmlGregorianCalendar(rs.getDate(5)));
                fundTypeRow.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(6)));
                fundTypeRow.setBasicAsset(rs.getString(7));
                fundTypeRow.setNumberPaymentEndContract(BigInteger.valueOf(rs.getLong(8)));
                fundTypeRow.setPeriodPayment(rs.getString(9));
                fundTypeRow.setVolumeContract(rs.getDouble(10));
                fundTypeRow.setFixedInterestRate(rs.getDouble(11));
                fundTypeRow.setRefereInterestRateFloating(rs.getString(12));
                fundTypeRow.setSecurityDeposit(rs.getDouble(13));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));
                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }
            });
        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }
        return report;
    }

    private Report11B generateReport11B(String fundEik) {
        Report11B report11B = new Report11B();
        bg.fsc.schemas.report.on.b._11b.PodType pod = new bg.fsc.schemas.report.on.b._11b.PodType();
        report11B.getPods().add(pod);
        pod.setFundDpf(new bg.fsc.schemas.report.on.b._11b.FundType());
        pod.setFundUpf(new bg.fsc.schemas.report.on.b._11b.FundType());
        pod.setFundPpf(new bg.fsc.schemas.report.on.b._11b.FundType());
        pod.setFundDpfps(new bg.fsc.schemas.report.on.b._11b.FundType());
        pod.setFundFipp(new bg.fsc.schemas.report.on.b._11b.FundType());
        pod.setFundFrp(new bg.fsc.schemas.report.on.b._11b.FundType());
        pod.setFundEik(fundEik);

        jdbcTemplate.query(
            "select FUND_ID,FUND_TYPE_ID,TYPE_SHARE_BP,TRANSACTION_ID,INVESTMAN,INVESTMAN_CODE,EMITENT,ISIN,DATE_EMISSION,"
                    + "DATE_TRANSACTION,NUMBER_SHARE,CURRENCY_CODE,NOM_CURRENCY,NOM_LV,VALUE_BUY,GARANT,MANAGEMENT_COMPANY,TRADE_MARKET,"
                    + "VALUE_BUY_CURRENCY from PNL_REPORT_11B where bank_id ||'|'||  date_report = ( "
                    + "select bank_id ||'|'|| d from ( "
                    + "select bank_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_11B where fund_id <> 16 "
                    + "group by bank_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)",
            (rs) -> {

                bg.fsc.schemas.report.on.b._11b.FundTypeRow fundTypeRow = new bg.fsc.schemas.report.on.b._11b.FundTypeRow();
                fundTypeRow.setTypeShareBp(BigInteger.valueOf(rs.getLong(3)));
                fundTypeRow.setTransactionId(BigInteger.valueOf(rs.getLong(4)));
                fundTypeRow.setInvestman(rs.getString(5));
                fundTypeRow.setInvestmanCode(rs.getString(6));
                fundTypeRow.setEmitent(rs.getString(7));
                fundTypeRow.setIsin(rs.getString(8));
                fundTypeRow.setDateEmission(getXmlGregorianCalendar(rs.getDate(9)));
                fundTypeRow.setDateTransaction(getXmlGregorianCalendar(rs.getDate(10)));
                fundTypeRow.setNumberShare(rs.getDouble(11));
                fundTypeRow.setCurrencyCode(CurrencyType.fromValue(rs.getString(12)));
                fundTypeRow.setNomCurrency(rs.getDouble(13));
                fundTypeRow.setNomLv(rs.getDouble(14));
                fundTypeRow.setValueBuy(rs.getDouble(15));
                fundTypeRow.setGarant(rs.getString(16));
                fundTypeRow.setManagementCompany(rs.getString(17));
                fundTypeRow.setTradeMarket(BigInteger.valueOf(rs.getLong(18)));
                fundTypeRow.setValueBuyCurrency(rs.getDouble(19));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(2));

                switch (fundTypeId) {
                    case FUNDDPF -> pod.getFundDpf().getRow().add(fundTypeRow);
                    case FUNDUPF -> pod.getFundUpf().getRow().add(fundTypeRow);
                    case FUNDPPF -> pod.getFundPpf().getRow().add(fundTypeRow);
                    case FUNDDPFPS -> pod.getFundDpfps().getRow().add(fundTypeRow);
                    case FUNDFIPP -> pod.getFundFipp().getRow().add(fundTypeRow);
                    case FUNDFRP -> pod.getFundFrp().getRow().add(fundTypeRow);
                    default -> {
                    }
                }

            });

        if (pod.getFundDpf().getRow().isEmpty()) {
            pod.setFundDpf(null);
        }
        if (pod.getFundUpf().getRow().isEmpty()) {
            pod.setFundUpf(null);
        }
        if (pod.getFundPpf().getRow().isEmpty()) {
            pod.setFundPpf(null);
        }
        if (pod.getFundDpfps().getRow().isEmpty()) {
            pod.setFundDpfps(null);
        }
        if (pod.getFundFipp().getRow().isEmpty()) {
            pod.setFundFipp(null);
        }
        if (pod.getFundFrp().getRow().isEmpty()) {
            pod.setFundFrp(null);
        }

        return report11B;
    }

    private Report1P generateReport1P() {
        Report1P report = new Report1P();

        jdbcTemplate.query(
            "select FUND_TYPE_ID,UNIT_VALUE_K1,NET_VALUE_K1,INCOME_TOTAL_K1,TAX_TOTAL_K1,UNIT_NET_NUMBER_K1,TAX_NO_K1,"
                    + "UNIT_NUMBER_NO_K1,EXPENSE_TOTAL_K1,UNIT_EXPENSE_NUMBER_K1,UNIT_NUMBER_CHANGE,UNIT_NUMBER_K1,UNIT_VALUE_K "
                    + "from PNL_REPORT_1P where fund_id ||'|'||  date_report = ("
                    + "select fund_id ||'|'|| d from ("
                    + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_1P where fund_id <> 16 "
                    + "group by fund_id, date_report "
                    + "order by 3 desc, 2 desc) where rownum = 1)",
            (rs) -> {
                FundType fundType = new FundType();
                fundType.setUnitValueK1(rs.getDouble(2));
                fundType.setNetValueK1(rs.getDouble(3));
                fundType.setIncomeTotalK1(rs.getDouble(4));
                fundType.setTaxTotalK1(rs.getDouble(5));
                fundType.setUnitNetNumberK1(rs.getDouble(6));
                fundType.setTaxNoK1(rs.getDouble(7));
                fundType.setUnitNumberNoK1(rs.getDouble(8));
                fundType.setExpenseTotalK1(rs.getDouble(9));
                fundType.setUnitExpenseNumberK1(rs.getDouble(10));
                fundType.setUnitNumberChange(rs.getDouble(11));
                fundType.setUnitNumberK1(rs.getDouble(12));
                fundType.setUnitValueK(rs.getDouble(13));


                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> {
                    }
                }
            });
        return report;
    }

    private Report2P generateReport2P() {
        Report2P report = new Report2P();
        jdbcTemplate.query("select FUND_TYPE_ID,INVESTMENTS,M_SETTLE_ACOUNTS,M_PAY_DESC,M_GET,M_GET_INVESTMENTS,M_GET_POD,M_GET_SHORT,"
                                   + "M_DUTY_SHORT,M_DUTY_PENSIONERS,M_DUTY_POD,ENTRY_TAX,PAYMENT_TAX,INVESTMENT_TAX,OTHER_TAX,OTHER,"
                                   + "OTHER_DUTY_INVESTMENTS,OTHER_SHORT_DUTY,DUTY_FIPP,DUTY_FRP,DUTY_TRANSFER from PNL_REPORT_2P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_2P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._2p.FundType fundType = new bg.fsc.schemas.report.on.p._2p.FundType();
                fundType.setInvestments(rs.getDouble(2));
                fundType.setMSettleAcounts(rs.getDouble(3));
                fundType.setMPayDesc(rs.getDouble(4));
                fundType.setMGet(rs.getDouble(5));
                fundType.setMGetInvestments(rs.getDouble(6));
                fundType.setMGetPod(rs.getDouble(7));
                fundType.setMGetShort(rs.getDouble(8));
                fundType.setMDutyShort(rs.getDouble(9));
                fundType.setMDutyPensioners(rs.getDouble(10));
                fundType.setMDutyPod(rs.getDouble(11));
                fundType.setEntryTax(rs.getDouble(12));
                fundType.setPaymentTax(rs.getDouble(13));
                fundType.setInvestmentTax(rs.getDouble(14));
                fundType.setOtherTax(rs.getDouble(15));
                fundType.setOther(rs.getDouble(16));
                fundType.setOtherDutyInvestments(rs.getDouble(17));
                fundType.setOtherShortDuty(rs.getDouble(18));
                fundType.setDutyFipp(rs.getDouble(19));
                fundType.setDutyFrp(rs.getDouble(20));
                fundType.setDutyTransfer(rs.getDouble(21));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report3P generateReport3P() {
        Report3P report = new Report3P();
        jdbcTemplate.query("select FUND_TYPE_ID,TYPE_SHARE,ISIN,EMITENT,BFB_NAME,CURRENCY_CODE,NOM_CURRENCY,NOM_LV,NOM_1000,SOURCE_ID,"
                                   + "METHOD_ID,DISC_PER,MARKET_VALUE_LV,RELATIVE_PART,COUNTRY_ID,RATING,RATING_AGENCY,TRADE_MARKET,"
                                   + "RATING_ID,RATING_AGENCY_ID,BULSTAT from PNL_REPORT_3P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_3P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._3p.FundType fundType = new bg.fsc.schemas.report.on.p._3p.FundType();
                fundType.setTypeShare(BigInteger.valueOf(rs.getLong(2)));
                fundType.setIsin(rs.getString(3));
                fundType.setEmitent(rs.getString(4));
                fundType.setBfbName(rs.getString(5));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(6)));
                fundType.setNomCurrency(rs.getDouble(7));
                fundType.setNomLv(rs.getDouble(8));
                fundType.setNom1000(rs.getDouble(9));
                fundType.setSourceId(BigInteger.valueOf(rs.getLong(10)));
                fundType.setMethodId(BigInteger.valueOf(rs.getLong(11)));
                fundType.setDiscPer(rs.getDouble(12));
                fundType.setMarketValueLv(rs.getDouble(13));
                fundType.setRelativePart(rs.getDouble(14));
                fundType.setCountry(CountryType.fromValue(rs.getString(15)));
                fundType.setRating(BigInteger.valueOf(rs.getLong(16)));
                if (fundType.getRating().equals(BigInteger.ZERO)) {
                    fundType.setRating(null);
                }
                fundType.setRatingAgency(BigInteger.valueOf(rs.getLong(17)));
                if (fundType.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundType.setRatingAgency(null);
                }
                fundType.setTradeMarket(BigInteger.valueOf(rs.getLong(18)));
                fundType.setBulstat(rs.getString(21));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report4P generateReport4P() {
        Report4P report = new Report4P();
        jdbcTemplate.query("select FUND_TYPE_ID,TYPE_SHARE,ISIN,EMITENT,BFB_NAME,NUMBER_EMITENT,NUMBER_SHARE,NOM_1,NOM_TOTAL,"
                                   + "ASSESSTMENT_1,SOURCE_ID,METHOD_ID,MARKET_VALUE_LV,RELATIVE_PART,RELATIVE_PART_EMITENT,COUNTRY_ID,"
                                   + "MANAGEMENT_COMPANY,INDEX_MARKET,CURRENCY_CODE,NOM_1_CURRENCY,NOM_1_CURRENCY_TOTAL,TRADE_MARKET,"
                                   + "BULSTAT from PNL_REPORT_4P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_4P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._4p.FundType fundType = new bg.fsc.schemas.report.on.p._4p.FundType();
                fundType.setTypeShare(BigInteger.valueOf(rs.getLong(2)));
                fundType.setIsin(rs.getString(3));
                fundType.setEmitent(rs.getString(4));
                fundType.setBfbName(rs.getString(5));
                fundType.setNumberEmitent(BigInteger.valueOf(rs.getLong(6)));
                fundType.setNumberShare(rs.getDouble(7));
                fundType.setNom1(rs.getDouble(8));
                fundType.setNomTotal(rs.getDouble(9));
                fundType.setAssesstment1(rs.getDouble(10));
                fundType.setSourceId(BigInteger.valueOf(rs.getLong(11)));
                fundType.setMethodId(BigInteger.valueOf(rs.getLong(12)));
                fundType.setMarketValueLv(rs.getDouble(13));
                fundType.setRelativePart(rs.getDouble(14));
                fundType.setRelativePartEmitent(rs.getDouble(15));
                fundType.setCountry(CountryType.fromValue(rs.getString(16)));
                fundType.setManagementCompany(rs.getString(17));
                fundType.setIndexMarket(BigInteger.valueOf(rs.getLong(18)));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(19)));
                fundType.setNom1Currency(rs.getDouble(20));
                fundType.setNom1CurrencyTotal(rs.getDouble(21));
                fundType.setTradeMarket(BigInteger.valueOf(rs.getLong(22)));
                fundType.setBulstat(rs.getString(23));


                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report5P generateReport5P() {
        Report5P report = new Report5P();
        jdbcTemplate.query("select FUND_TYPE_ID,COMMERCIAL_BANK_DESCRIPTION,COMMERCIAL_BANK_CODE,CONTRACT_ANNEX,DATE_CONTRACT_ANNEX,"
                                   + "CURRENCY_CODE,NOM_CURRENCY,NOM_LV,YEAR_INTEREST,DATE_INTEREST_BEGIN,DATE_INTEREST_END,INTEREST,TOTAL,"
                                   + "RELATIVE_PART,BASE_DAYS_REAL,BASE_DAYS,COUNTRY_ID,RATING,RATING_AGENCY,RATING_ID,RATING_AGENCY_ID,"
                                   + "BULSTAT from PNL_REPORT_5P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_5P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._5p.FundType fundType = new bg.fsc.schemas.report.on.p._5p.FundType();
                fundType.setCommercialBankDescription(rs.getString(2));
                fundType.setCommercialBankCode(rs.getString(3));
                fundType.setContractAnnex(rs.getString(4));
                fundType.setDateContractAnnex(getXmlGregorianCalendar(rs.getDate(5)));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(6)));
                fundType.setNomCurrency(rs.getDouble(7));
                fundType.setNomLv(rs.getDouble(8));
                fundType.setYearInterest(rs.getDouble(9));
                fundType.setDateInterestBegin(getXmlGregorianCalendar(rs.getDate(10)));
                fundType.setDateInterestEnd(getXmlGregorianCalendar(rs.getDate(11)));
                fundType.setInterest(rs.getDouble(12));
                fundType.setTotal(rs.getDouble(13));
                fundType.setRelativePart(rs.getDouble(14));
                fundType.setBaseDaysReal(BigInteger.valueOf(rs.getLong(15)));
                fundType.setBaseDays(BigInteger.valueOf(rs.getLong(16)));
                fundType.setCountry(CountryType.fromValue(rs.getString(17)));
                fundType.setRating(BigInteger.valueOf(rs.getLong(18)));
                if (fundType.getRating().equals(BigInteger.ZERO)) {
                    fundType.setRating(null);
                }
                fundType.setRatingAgency(BigInteger.valueOf(rs.getLong(19)));
                if (fundType.getRatingAgency().equals(BigInteger.ZERO)) {
                    fundType.setRatingAgency(null);
                }
                fundType.setBulstat(rs.getString(22));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report7P generateReport7P() {
        Report7P report = new Report7P();
        jdbcTemplate.query("select FUND_TYPE_ID,IMOT_DESCRIPTION,DATE_BUY,IMOT_DOC,IMOT_ADDRESS,VALUE_BUY,METHOD_ID,MARKET_VALUE_LV,"
                                   + "LAST_ASSESMENT,RELATIVE_PART,DATE_REPORT,COUNTRY_ID,CURRENCY_CODE,VALUE_BUY_CURRENCY from "
                                   + "PNL_REPORT_7P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_7P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._7p.FundType fundType = new bg.fsc.schemas.report.on.p._7p.FundType();
                fundType.setImotDescription(rs.getString(2));
                fundType.setDateBuy(getXmlGregorianCalendar(rs.getDate(3)));
                fundType.setImotDoc(rs.getString(4));
                fundType.setImotAddress(rs.getString(5));
                fundType.setValueBuy(rs.getDouble(6));
                fundType.setMethodId(BigInteger.valueOf(rs.getLong(7)));
                fundType.setMarketValueLv(rs.getDouble(8));
                fundType.setLastAssesment(getXmlGregorianCalendar(rs.getDate(9)));
                fundType.setRelativePart(rs.getDouble(10));
                fundType.setCountry(CountryType.fromValue(rs.getString(12)));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(13)));
                fundType.setValueBuyCurrency(rs.getDouble(14));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report9P generateReport9P() {
        Report9P report = new Report9P();
        jdbcTemplate.query("select FUND_TYPE_ID,TYPE_MONEY,INCOME_TOTAL,INCOME_TRANSFER,INTEREST_NSSI,INCOME_TRANSFER_ES,INCOME_RETURN,"
                                   + "INCOME_OTHER,INCOME_POD,INCOME_RGMD,INCOME_RGBV,INCOME_OTHER_POD,INCOME_INV,EXPENSE_PENSIONER,"
                                   + "EXPENSE_TOTAL,EXPENSE_TRANSFER,EXPENSE_TRANSFER_ES,EXPENSE_CHANGES,EXPENSE_OTHER,TAX_MMC,TAX_INV,"
                                   + "TAX_OTHER,PAID_INV,M_END,FIPP_LIFETIME_PENSION,FIPP_RECALC_PENSION,FIPP_WITHOUT_HEIRS,FRP_DEF_PAY,"
                                   + "FRP_RECALC_PAYMENTS,EXPENSE_HEIRS,CURRENCY_CODE,M_END_CURRENCY,M_END_LEVA from PNL_REPORT_9P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_9P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._9p.FundType fundType = new bg.fsc.schemas.report.on.p._9p.FundType();
                fundType.setTypeMoney(BigInteger.valueOf(rs.getLong(2)));
                fundType.setIncomeTotal(rs.getDouble(3));
                fundType.setIncomeTransfer(rs.getDouble(4));
                fundType.setInterestNssi(rs.getDouble(5));
                fundType.setIncomeTransferEs(rs.getDouble(6));
                fundType.setIncomeReturn(rs.getDouble(7));
                fundType.setIncomeOther(rs.getDouble(8));
                fundType.setIncomePod(rs.getDouble(9));
                fundType.setIncomeRgmd(rs.getDouble(10));
                fundType.setIncomeRgbv(rs.getDouble(11));
                fundType.setIncomeOtherPod(rs.getDouble(12));
                fundType.setIncomeInv(rs.getDouble(13));
                fundType.setExpensePensioner(rs.getDouble(14));
                fundType.setExpenseTotal(rs.getDouble(15));
                fundType.setExpenseTransfer(rs.getDouble(16));
                fundType.setExpenseTransferEs(rs.getDouble(17));
                fundType.setExpenseChanges(rs.getDouble(18));
                fundType.setExpenseOther(rs.getDouble(19));
                fundType.setTaxMmc(rs.getDouble(20));
                fundType.setTaxInv(rs.getDouble(21));
                fundType.setTaxOther(rs.getDouble(22));
                fundType.setPaidInv(rs.getDouble(23));
                fundType.setMEnd(rs.getDouble(24));
                fundType.setFippLifetimePension(rs.getDouble(25));
                fundType.setFippRecalcPension(rs.getDouble(26));
                fundType.setFippWithoutHeirs(rs.getDouble(27));
                fundType.setFrpDefPay(rs.getDouble(28));
                fundType.setFrpRecalcPayments(rs.getDouble(29));
                fundType.setExpenseHeirs(rs.getDouble(30));
                fundType.setCurrencyCode(rs.getString(31));
                fundType.setMEndCurrency(rs.getDouble(32));
                fundType.setMEndLeva(rs.getDouble(33));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report10P generateReport10P() {
        Report10P report = new Report10P();
        jdbcTemplate.query("select BANK_ID,ISIN,PRISE_BUY_INTEREST,PRISE_BUY_NO,PRISE_SELL_INTEREST,PRISE_SELL_NO from "
                                   + "PNL_REPORT_10P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_id) from PNL_REPORT_10P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._10p.Report10PRow row = new bg.fsc.schemas.report.on.p._10p.Report10PRow();
                row.setBankId(rs.getString(1));
                row.setIsin(rs.getString(2));
                row.setPriceBuyInterest(rs.getDouble(3));
                row.setPriceBuyNo(rs.getDouble(4));
                row.setPriceSellInterest(rs.getDouble(5));
                row.setPriceSellNo(rs.getDouble(6));
                report.getRow().add(row);

            });
        return report;
    }

    private Report11P generateReport11P() {
        Report11P report = new Report11P();
        jdbcTemplate.query("select FUND_TYPE_ID,UNIT_VALUE_K,NET_VALUE_K1,UNIT_NUMBER_K1,UNIT_VALUE_24,UNIT_NUMBER_24,UNIT_VALUE_UP,"
                                   + "RESERVE_VALUE,RESERVE_VALUE_ADD,RESERVE_VALUE_12,RESERVE_VALUE_NEW,UNIT_VALUE_RESERVE,"
                                   + "UNIT_NUMBER_K1_AFTER,UNIT_VALUE_K_AFTER from PNL_REPORT_11P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_11P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._11p.FundType fundType = new bg.fsc.schemas.report.on.p._11p.FundType();
                fundType.setUnitValueK(rs.getDouble(2));
                fundType.setNetValueK1(rs.getDouble(3));
                fundType.setUnitNumberK1(rs.getDouble(4));
                fundType.setUnitValue24(rs.getDouble(5));
                fundType.setUnitNumber24(rs.getDouble(6));
                fundType.setUnitValueUp(rs.getDouble(7));
                fundType.setReserveValue(rs.getDouble(8));
                fundType.setReserveValueAdd(rs.getDouble(9));
                fundType.setReserveValue12(rs.getDouble(10));
                fundType.setReserveValueNew(rs.getDouble(11));
                fundType.setUnitValueReserve(rs.getDouble(12));
                fundType.setUnitNumberK1After(rs.getDouble(13));
                fundType.setUnitValueKAfter(rs.getDouble(14));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report12P generateReport12P() {
        Report12P report = new Report12P();
        jdbcTemplate.query("select FUND_TYPE_ID,UNIT_VALUE_K,NET_VALUE_K1,UNIT_NUMBER_K1,UNIT_VALUE_24,UNIT_NUMBER_24,UNIT_VALUE_MIN,"
                                   + "TOTAL_ADD,TOTAL_RESERVE,UNIT_NUMBER_OUT,UNIT_NUMBER_K1_AFTER,TOTAL_RESERVE_OUT,NET_VALUE_K1_AFTER,"
                                   + "UNIT_VALUE_K_AFTER from PNL_REPORT_12P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_12P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._12p.FundType fundType = new bg.fsc.schemas.report.on.p._12p.FundType();
                fundType.setUnitValueK(rs.getDouble(2));
                fundType.setNetValueK1(rs.getDouble(3));
                fundType.setUnitNumberK1(rs.getDouble(4));
                fundType.setUnitValue24(rs.getDouble(5));
                fundType.setUnitNumber24(rs.getDouble(6));
                fundType.setUnitValueMin(rs.getDouble(7));
                fundType.setTotalAdd(rs.getDouble(8));
                fundType.setTotalReserve(rs.getDouble(9));
                fundType.setUnitNumberOut(rs.getDouble(10));
                fundType.setUnitNumberK1After(rs.getDouble(11));
                fundType.setTotalReserveOut(rs.getDouble(12));
                fundType.setNetValueK1After(rs.getDouble(13));
                fundType.setUnitValueKAfter(rs.getDouble(14));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report14P generateReport14P() {
        Report14P report = new Report14P();
        jdbcTemplate.query("select FUND_TYPE_ID,DEBT_OBLIGATION_CODE,DESCRIPTION,TYPE_SHARE,CONTRACTOR,CURRENCY_CODE,NOM_CURRENCY,"
                                   + "NOM_LV,BULSTAT from PNL_REPORT_14P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_14P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._14p.FundType fundType = new bg.fsc.schemas.report.on.p._14p.FundType();
                fundType.setDebtObligationCode(BigInteger.valueOf(rs.getLong(2)));
                fundType.setDescription(rs.getString(3));
                fundType.setTypeShare(BigInteger.valueOf(rs.getLong(4)));
                fundType.setContractor(rs.getString(5));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(6)));
                fundType.setNomCurrency(rs.getDouble(7));
                fundType.setNomLv(rs.getDouble(8));
                fundType.setBulstat(rs.getString(9));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report15P generateReport15P() {
        Report15P report = new Report15P();
        jdbcTemplate.query("select FUND_TYPE_ID,TYPE_REPO,CONTRACTOR,DATE_TRANSACTION,DATE_OF_VALUE_TRANSACTION,TYPE_SHARE,ISIN,"
                                   + "COUNTRY_ID,EMITENT,BFB_NAME,NUMBER_SHARE,CURRENCY_CODE,NOM_CURRENCY,NOM_LV,VALUE_REPO,"
                                   + "VALUE_REPO_OPPOSITE,DATE_OF_VALUE_TRANSACTION_OPPOSITE,RATING_REPO_TRANSACTION,RELATIVE_PART,BULSTAT "
                                   + "from PNL_REPORT_15P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_15P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._15p.FundType fundType = new bg.fsc.schemas.report.on.p._15p.FundType();
                fundType.setTypeRepo(BigInteger.valueOf(rs.getLong(2)));
                fundType.setContractor(rs.getString(3));
                fundType.setDateTransaction(getXmlGregorianCalendar(rs.getDate(4)));
                fundType.setDateOfValueTransaction(getXmlGregorianCalendar(rs.getDate(5)));
                fundType.setTypeShare(BigInteger.valueOf(rs.getLong(6)));
                fundType.setIsin(rs.getString(7));
                fundType.setCountry(CountryType.fromValue(rs.getString(8)));
                fundType.setEmitent(rs.getString(9));
                fundType.setBfbName(rs.getString(10));
                fundType.setNumberShare(BigInteger.valueOf(rs.getLong(11)));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(12)));
                fundType.setNomCurrency(rs.getDouble(13));
                fundType.setNomLv(rs.getDouble(14));
                fundType.setValueRepo(rs.getDouble(15));
                fundType.setValueRepoOpposite(rs.getDouble(16));
                fundType.setDateOfValueTransactionOpposite(getXmlGregorianCalendar(rs.getDate(17)));
                fundType.setRatingRepoTransaction(rs.getDouble(18));
                fundType.setRelativePart(rs.getDouble(19));
                fundType.setBulstat(rs.getString(20));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report31P generateReport31P() {
        Report31P report = new Report31P();
        jdbcTemplate.query("select FUND_TYPE_ID,OPTIONS_FUTURES,PURCHASE_SALE,COUNTRY_ID,TRADE_MARKET,BFB_NAME,DATE_CONTRACT,"
                                   + "DATE_EXPIRY_CONTRACT,BASIC_ASSET,VALUE_BASIC_ASSET,VOLUME_CONTRACT,NUMBER_CONTRACT,MARKET_COST_ASSET,"
                                   + "PRICE_OPTION_FUTURES,METHOD_ID,SOURCE_ID,COST_FUTURES_CONTRACT,SUM_OPTION_FUTURES,"
                                   + "MARGIN_BALANCE_MORNING,MARGIN_BALANCE_PAID_RECEIVE,MARGIN_BALANCE_PROFIT_LOSS,MARGIN_BALANCE_EVENING "
                                   + "from PNL_REPORT_31P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_31P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._31p.FundType fundType = new bg.fsc.schemas.report.on.p._31p.FundType();
                fundType.setOptionsFutures(BigInteger.valueOf(rs.getLong(2)));
                fundType.setPurchaseSale(BigInteger.valueOf(rs.getLong(3)));
                fundType.setCountry(CountryType.fromValue(rs.getString(4)));
                fundType.setTradeMarket(BigInteger.valueOf(rs.getLong(5)));
                fundType.setBfbName(rs.getString(6));
                fundType.setDateContract(getXmlGregorianCalendar(rs.getDate(7)));
                fundType.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(8)));
                fundType.setBasicAsset(BigInteger.valueOf(rs.getLong(9)));
                fundType.setValueBasicAsset(rs.getDouble(10));
                fundType.setVolumeContract(rs.getDouble(11));
                fundType.setNumberContract(BigInteger.valueOf(rs.getLong(12)));
                fundType.setMarketCostAsset(rs.getDouble(13));
                fundType.setPriceOptionFutures(rs.getDouble(14));
                fundType.setMethodId(BigInteger.valueOf(rs.getLong(15)));
                fundType.setSourceId(BigInteger.valueOf(rs.getLong(16)));
                fundType.setCostFuturesContract(rs.getDouble(17));
                fundType.setSumOptionFutures(rs.getDouble(18));
                fundType.setMarginBalanceMorning(rs.getDouble(19));
                fundType.setMarginBalancePaidReceive(rs.getDouble(20));
                fundType.setMarginBalanceProfitLoss(rs.getDouble(21));
                fundType.setMarginBalanceEvening(rs.getDouble(22));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report32P generateReport32P() {
        Report32P report = new Report32P();
        jdbcTemplate.query("select FUND_TYPE_ID,PURCHASE_SALE,COUNTRY_ID,CONTRAGENT,RATING,RATING_AGENCY,DATE_CONTRACT,"
                                   + "DATE_EXPIRY_CONTRACT,CURRENCY_PAIR,CURRENCY_CODE,RATE_FORWARD_CONTRACT,VOLUME_CONTRACT_CURRENCY,"
                                   + "SPOT_EXCHANGE_RATE,PRICE_FORWARD_CONTRACT,METHOD_ID,SOURCE_ID,COST_CONTRACT_CURRENCY,"
                                   + "COST_CONTRACT_LEVA,BULSTAT from PNL_REPORT_32P where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_32P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._32p.FundType fundType = new bg.fsc.schemas.report.on.p._32p.FundType();
                fundType.setPurchaseSale(BigInteger.valueOf(rs.getLong(2)));
                fundType.setCountry(CountryType.valueOf(rs.getString(3)));
                fundType.setContragent(rs.getString(4));
                fundType.setRating(BigInteger.valueOf(rs.getLong(5)));
                fundType.setRatingAgency(BigInteger.valueOf(rs.getLong(6)));
                fundType.setDateContract(getXmlGregorianCalendar(rs.getDate(7)));
                fundType.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(8)));
                fundType.setCurrencyPair(rs.getString(9));
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(10)));
                fundType.setRateForwardContract(rs.getDouble(11));
                fundType.setVolumeContractCurrency(rs.getDouble(12));
                fundType.setSpotExchangeRate(rs.getDouble(13));
                fundType.setPriceForwardContract(rs.getDouble(14));
                fundType.setMethodId(BigInteger.valueOf(rs.getLong(15)));
                fundType.setSourceId(BigInteger.valueOf(rs.getLong(16)));
                fundType.setCostContractCurrency(rs.getDouble(17));
                fundType.setCostContractLeva(rs.getDouble(18));
                fundType.setBulstat(rs.getString(19));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report33P generateReport33P() {
        Report33P report = new Report33P();
        jdbcTemplate.query("select FUND_TYPE_ID,RECEIVE_PAY_INTEREST_RATE,COUNTRY_ID,CONTRAGENT,RATING,RATING_AGENCY,DATE_CONTRACT,"
                                   + "DATE_EXPIRY_CONTRACT,NUMBER_PAYMENT_YEAR,NUMBER_PAYMENT_END_CONTRACT,DATE_NEXT_PAYMENT,"
                                   + "VOLUME_CONTRACT,FIXED_INTEREST_RATE,REFERE_INTEREST_RATE_FLOATING,REFERE_INTEREST_RATE,"
                                   + "MARKET_PRICE_CONTRACT,METHOD_ID,SOURCE_ID,VALUE_CONTRACT,BULSTAT from PNL_REPORT_33P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_33P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._33p.FundType fundType = new bg.fsc.schemas.report.on.p._33p.FundType();
                fundType.setReceivePayInterestRate(BigInteger.valueOf(rs.getLong(2)));
                fundType.setCountry(CountryType.fromValue(rs.getString(3)));
                fundType.setContragent(rs.getString(4));
                fundType.setRating(BigInteger.valueOf(rs.getLong(5)));
                fundType.setRatingAgency(BigInteger.valueOf(rs.getLong(6)));
                fundType.setDateContract(getXmlGregorianCalendar(rs.getDate(7)));
                fundType.setDateExpiryContract(getXmlGregorianCalendar(rs.getDate(8)));
                fundType.setNumberPaymentYear(BigInteger.valueOf(rs.getLong(9)));
                fundType.setNumberPaymentEndContract(BigInteger.valueOf(rs.getLong(10)));
                fundType.setDateNextPayment(getXmlGregorianCalendar(rs.getDate(11)));
                fundType.setVolumeContract(rs.getDouble(12));
                fundType.setFixedInterestRate(rs.getDouble(13));
                fundType.setRefereInterestRateFloating(rs.getString(14));
                fundType.setRefereInterestRate(rs.getDouble(15));
                fundType.setMarketPriceContract(rs.getDouble(16));
                fundType.setMethodId(BigInteger.valueOf(rs.getLong(17)));
                fundType.setSourceId(BigInteger.valueOf(rs.getLong(18)));
                fundType.setValueContract(rs.getDouble(19));
                fundType.setBulstat(rs.getString(20));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDDPF -> report.setFundDpf(fundType);
                    case FUNDUPF -> report.setFundUpf(fundType);
                    case FUNDPPF -> report.setFundPpf(fundType);
                    case FUNDDPFPS -> report.setFundDpfps(fundType);
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report34P generateReport34P() {
        Report34P report = new Report34P();
        jdbcTemplate.query("select FUND_TYPE_ID,INVESTMENTS,M_SETTLE_ACOUNTS,M_CASH,M_GET,M_GET_INVESTMENTS,M_GET_POD,M_OTHER_GET,"
                                   + "M_DUTY_INVESTMENTS,M_DUTY_POD,TAX,RELEASE_OF_EXCESS,OTHER,OTHER_DUTIES from PNL_REPORT_34P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_34P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._34p.FundType fundType = new bg.fsc.schemas.report.on.p._34p.FundType();
                fundType.setInvestments(rs.getDouble(2));
                fundType.setMSettleAcounts(rs.getDouble(3));
                fundType.setMCash(rs.getDouble(4));
                fundType.setMGet(rs.getDouble(5));
                fundType.setMGetInvestments(rs.getDouble(6));
                fundType.setMGetPod(rs.getDouble(7));
                fundType.setMOtherGet(rs.getDouble(8));
                fundType.setMDutyInvestments(rs.getDouble(9));
                fundType.setMDutyPod(rs.getDouble(10));
                fundType.setTax(rs.getDouble(11));
                fundType.setReleaseOfExcess(rs.getDouble(12));
                fundType.setOther(rs.getDouble(13));
                fundType.setOtherDuties(rs.getDouble(14));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }

    private Report35P generateReport35P() {
        Report35P report = new Report35P();
        jdbcTemplate.query("select FUND_TYPE_ID,CURRENCY_CODE,CURRENCY_VALUE,LV_VALUE from PNL_REPORT_35P "
                                   + "where fund_id ||'|'||  date_report = ("
                                   + "select fund_id ||'|'|| d from ("
                                   + "select fund_id, max(date_report) as d, count( distinct fund_type_id) from PNL_REPORT_35P where "
                                   + "fund_id <> 16 "
                                   + "group by fund_id, date_report "
                                   + "order by 3 desc, 2 desc) where rownum = 1)", (rs) -> {
                bg.fsc.schemas.report.on.p._35p.FundType fundType = new bg.fsc.schemas.report.on.p._35p.FundType();
                fundType.setCurrencyCode(CurrencyType.fromValue(rs.getString(2)));
                fundType.setCurrencyValue(rs.getDouble(3));
                fundType.setLvValue(rs.getDouble(4));

                CommonReportMapper.FundTypeId fundTypeId = CommonReportMapper.FundTypeId.fromInt(rs.getInt(1));
                switch (fundTypeId) {
                    case FUNDFIPP -> report.setFundFipp(fundType);
                    case FUNDFRP -> report.setFundFrp(fundType);
                    default -> { }
                }
            });
        return report;
    }


    private XMLGregorianCalendar getXmlGregorianCalendar(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        XMLGregorianCalendar xmlGregorianCalendar9 = null;
        try {
            xmlGregorianCalendar9 = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        } catch (DatatypeConfigurationException e) {
            log.error("Couldn't map date to xml calendar.", e);
        }
        xmlGregorianCalendar9.setDay(date.getDate());
        xmlGregorianCalendar9.setMonth(date.getMonth() + 1);
        xmlGregorianCalendar9.setYear(date.getYear());
        return xmlGregorianCalendar9;
    }
}
