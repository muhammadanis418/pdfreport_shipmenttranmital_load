package com.example.shipment_transmital_load_pdf;

import com.example.shipment_transmital_load_pdf.dto.ShipmentTableData;
import com.example.shipment_transmital_load_pdf.service.PdfService;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShipmentTransmitalLoadPdfApplicationTests {

    @InjectMocks
    private PdfService pdfService;

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
            ArrayList<ShipmentTableData> tableData = new ArrayList<>();

            ShipmentTableData rowOne = new ShipmentTableData();
            rowOne.setMediaType("Started");
            rowOne.setNumberOfItems("for testing purpose");
            rowOne.setBlock("A");
            rowOne.setFieldSurvey("testing the field");
            rowOne.setDataType("Not mention");
            rowOne.setComments("Testing phase Started");

            ShipmentTableData rowTwo = new ShipmentTableData();
            rowTwo.setMediaType("Started");
            rowTwo.setNumberOfItems("for testing purpose");
            rowTwo.setBlock("B");
            rowTwo.setFieldSurvey("testing the field");
            rowTwo.setDataType("Not mention");
            rowTwo.setComments("Testing phase Started");

            tableData.add(rowOne);
            tableData.add(rowTwo);


            String shipmentSummary = "we are testing this report by giving dump data.The POM for is missing, no dependency information available even though it exists in maven repository.";
            pdfService.generatePdf(toName, toAddress, toContact, fromName, fromAddress, fromEmail, targetReferenceNo, date, viaNumber, waybill, tableData, shipmentSummary);


        }
        catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
