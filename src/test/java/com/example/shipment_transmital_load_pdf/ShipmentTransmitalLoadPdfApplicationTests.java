package com.example.shipment_transmital_load_pdf;

import com.example.shipment_transmital_load_pdf.dto.ShipmentTableData;
import com.example.shipment_transmital_load_pdf.service.PdfService;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(properties = "logo.path=src/test/resources/logo.jpg")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ShipmentTransmitalLoadPdfApplicationTests {


    private final PdfService pdfService;

    public ShipmentTransmitalLoadPdfApplicationTests(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @Test
    void contextLoads() {
        try {
            String toName = "abc office";
            String toAddress = "xyz office, Beach One Building, 2nd floor, Office 222, P.O Box: 123, P.C: 110, Sarkof, Muscat, Sultanate of Oman";
            String toContact = "9087654321";
            String fromName = "abc office";
            String fromAddress = "Operator abc";
            String fromEmail = "abc@gmail.com";
            int targetReferenceNo = 123;
            Date date = new Date();
            String viaNumber = "123";
            String waybill = "123";
            ArrayList<ShipmentTableData> tableData = getShipmentTableData();

            String shipmentSummary = "we are testing this report by giving dump data.The POM for is missing, no dependency information available even though it exists in maven repository.";
            assertThat(pdfService.getLogoPath()).isEqualTo("src/test/resources/logo.jpg");
           // System.out.println("logo.path injected: " + pdfService.getLogoPath());

            pdfService.generatePdf(toName, toAddress, toContact, fromName, fromAddress, fromEmail, targetReferenceNo, date, viaNumber, waybill, tableData, shipmentSummary);


        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private static ArrayList<ShipmentTableData> getShipmentTableData() {
        ArrayList<ShipmentTableData> tableData = new ArrayList<>();

        ShipmentTableData rowOne = new ShipmentTableData();
        rowOne.setMediaType("Inserting first dump row");
        rowOne.setNumberOfItems("testing-1");
        rowOne.setBlock("D");
        rowOne.setFieldSurvey("field dump");
        rowOne.setDataType("Random data");
        rowOne.setComments("Testing phase Started");

        ShipmentTableData rowTwo = new ShipmentTableData();
        rowTwo.setMediaType("Inserting Second dump row");
        rowTwo.setNumberOfItems("testing-2");
        rowTwo.setBlock("U");
        rowTwo.setFieldSurvey("field dump");
        rowTwo.setDataType("Raw data");
        rowTwo.setComments("Nil");

        tableData.add(rowOne);
        tableData.add(rowTwo);
        return tableData;
    }
}
