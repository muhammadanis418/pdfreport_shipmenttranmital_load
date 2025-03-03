package com.example.shipment_transmital_load_pdf.service;


import com.example.shipment_transmital_load_pdf.dto.ShipmentTableData;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class PdfService {


    @Value("${logo.path}")
    private String logoPath;

//    @Autowired
//    private FileServiceExt fileServiceExt;

    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);


    public byte[] generatePdf(String toName, String toAddress, String toContact, String fromName, String fromAddress, String fromEmail, Integer targetReferenceNo, Date date, String viaNumber, String waybill, List<ShipmentTableData> tableData, String shipmentSummary) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);

        Document document = new Document(pdfDoc);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateFormat = simpleDateFormat.format(date);


        LocalDateTime ldf = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddSSS");  //   yyyyMMddHHmmssSSS
        String formattedDate = ldf.format(formatter);
        long dateWithMilliseconds = Long.parseLong(formattedDate);


        // Add logo
//        ImageData imageData = ImageDataFactory.create(logoPath);
//        Image logo = new Image(imageData);
//        logo.setHorizontalAlignment(com.itextpdf.layout.property.HorizontalAlignment.CENTER);
//        document.add(logo);


        Table toTable = new Table(new float[]{1});

        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(logoPath);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image logo = new Image(imageData);

        logo.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
        logo.setHeight(150);
        logo.setWidth(150);


        //toTable.addCell("TO:");
        toTable.setWidth(UnitValue.createPercentValue(100));
        //toTable.setHorizontalAlignment(HorizontalAlignment.LEFT);
        String combinedToInfo = "TO:\n" + toName + "\n" + toAddress + "\n Contact Number: " + toContact;
        toTable.addCell(combinedToInfo);


        Table fromTable = new Table(new float[]{1});
        fromTable.setWidth(UnitValue.createPercentValue(100));
        // fromTable.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        String combinedFromInfo = "FROM:\n" + fromName + "\n" + fromAddress + "\n" + fromEmail + "\n" + "\n" + "\n";
        fromTable.addCell(combinedFromInfo);
//        fromTable.addCell("FROM:");
//        fromTable.addCell(fromName);
//        fromTable.addCell(fromAddress);
//        fromTable.addCell(fromEmail);

        Table parentTable = new Table(new float[]{1, 1});
        parentTable.setWidth(UnitValue.createPercentValue(100));


        Cell fromCell = new Cell().add(fromTable).setBorder(Border.NO_BORDER)
                .setWidth(UnitValue.createPercentValue(48))
                .setPaddingRight(10);

        Cell toCell = new Cell().add(toTable).setBorder(Border.NO_BORDER)
                .setWidth(UnitValue.createPercentValue(48))
                .setPaddingLeft(10);

//        Cell fromCell = new Cell().add(fromTable).setBorder(Border.NO_BORDER)
//                .setWidth(UnitValue.createPercentValue(48))
//                .setPaddingLeft(10);
        parentTable.addCell(fromCell);
        parentTable.addCell(toCell);
        // parentTable.addCell(fromCell);


        document.add(logo);
        document.add(new Paragraph("\n"));
        document.add(parentTable);
        // document.add(fromTable);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("RECORD OF SHIPMENT OF CONFIDENTIAL DATA").setFontColor(ColorConstants.BLACK)
                .setBold().setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("\n"));
//        Cell titleCell = new Cell(1, 4).add(new Paragraph("RECORD OF SHIPMENT OF CONFIDENTIAL DATA").setFontColor(ColorConstants.BLACK)
//                .setBold().setFontSize(12)
//                .setTextAlignment(TextAlignment.CENTER));
        //setHorizontalAlignment(com.itextpdf.layout.property.HorizontalAlignment.CENTER));
        //titleCell.setPadding(40);


        float[] columnWidths = {2, 1, 1, 1, 1, 1, 1};
        //Table combinedTable = new Table(new float[]{1,1,1,1,1,1,1}); //2, 3, 2, 2  //0, 1, 1, 1, 1, 1,2
        Table combinedTable = new Table(columnWidths);
        //combinedTable.setPaddings(10, 10, 10, 10);
        combinedTable.setWidth(UnitValue.createPercentValue(100));


        combinedTable.addCell(new Cell(1, 1).add(new Paragraph("Target REF:").setWidth(50)).setTextAlignment(TextAlignment.CENTER).setFontSize(8)).setFontColor(ColorConstants.BLACK);

        if (targetReferenceNo == null) {
            combinedTable.addCell(new Cell(1, 2));
        } else {
            combinedTable.addCell(new Cell(1, 2).add(new Paragraph(String.valueOf(targetReferenceNo)).setFontSize(8)).setFontColor(ColorConstants.BLACK));
        }
        combinedTable.addCell(new Cell(1, 2).add(new Paragraph("Transmittal #:")).setTextAlignment(TextAlignment.CENTER).setFontSize(8)).setFontColor(ColorConstants.BLACK);

        combinedTable.addCell(new Cell(1, 2).add(new Paragraph(String.valueOf(dateWithMilliseconds))).setFontSize(8)).setFontColor(ColorConstants.BLACK);


        combinedTable.addCell(new Cell(1, 1).add(new Paragraph("Date:")).setTextAlignment(TextAlignment.CENTER).setFontSize(8)).setFontColor(ColorConstants.BLACK);
        combinedTable.addCell(new Cell(1, 2).add(new Paragraph(dateFormat)).setFontSize(8)).setFontColor(ColorConstants.BLACK);

//            combinedTable.addCell(new Cell());
//            combinedTable.addCell(new Cell());
        combinedTable.addCell(new Cell(1, 2).add(new Paragraph("Via:")).setTextAlignment(TextAlignment.CENTER).setFontSize(8)).setFontColor(ColorConstants.BLACK);

        if (viaNumber == null) {
            combinedTable.addCell(new Cell(1, 2));
        } else {
            combinedTable.addCell(new Cell(1, 2).add(new Paragraph(viaNumber).setFontSize(8)));
        }

        combinedTable.addCell(new Cell(1, 1).add(new Paragraph("Waybill #:")).setTextAlignment(TextAlignment.CENTER).setFontSize(8)).setFontColor(ColorConstants.BLACK);
        if (waybill == null) {
            combinedTable.addCell(new Cell(1, 2));
        }
        combinedTable.addCell(new Cell(1, 2).add(new Paragraph(waybill)).setFontSize(8));

        combinedTable.addCell(new Cell(1, 2));
        combinedTable.addCell(new Cell(1, 2));
//        }
        //combinedTable.setHorizontalAlignment(HorizontalAlignment.CENTER);


        combinedTable.addCell(new Cell().setBackgroundColor(ColorConstants.GRAY).setBold());

        combinedTable.addCell(new Cell().add(new Paragraph("Media Type").setFontSize(8)).setBackgroundColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER));

        combinedTable.addCell(new Cell().add(new Paragraph("No of Items").setFontSize(8)).setBackgroundColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER));

        combinedTable.addCell(new Cell().add(new Paragraph("Block").setFontSize(8)).setBackgroundColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER));

        combinedTable.addCell(new Cell().add(new Paragraph("Fields/Survey").setFontSize(8)).setBackgroundColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER));

        combinedTable.addCell(new Cell().add(new Paragraph("Data Type").setFontSize(8)).setBackgroundColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER));

        combinedTable.addCell(new Cell().add(new Paragraph("Comments").setFontSize(8)).setBackgroundColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER));


//        for (Map.Entry<Integer, Map<String, Integer>> entry : tableData.entrySet()) {
//            Integer count = entry.getKey();
//            Map<String, Integer> innerMap = entry.getValue();
//
//            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
//                String mediaType = innerEntry.getKey();
//                Integer number = innerEntry.getValue();
//
//                combinedTable.addCell(new Cell().add(new Paragraph(String.valueOf(count))).setFontColor(ColorConstants.BLACK).setBold());
//                combinedTable.addCell(new Cell().add(new Paragraph(mediaType)));//.setFontSize(10));
//                combinedTable.addCell(new Cell(1, 2).add(new Paragraph(String.valueOf(number))));
//            }
//
//        }
        for (int i = 0; i < tableData.size(); i++) {
            ShipmentTableData std = tableData.get(i);
            Integer count = i + 1;
            String mediaType = std.getMediaType();
            String noOfItems = std.getNumberOfItems();
            String block = std.getBlock();
            String fieldSurvey = std.getFieldSurvey();
            String dataType = std.getDataType();
            String comments = std.getComments();


            combinedTable.addCell(new Cell().add(new Paragraph(String.valueOf(count))).setFontColor(ColorConstants.BLACK).setBold().setFontSize(8));
            combinedTable.addCell(new Cell().add(new Paragraph(mediaType)).setFontSize(8));//.setFontSize(10));
            combinedTable.addCell(new Cell().add(new Paragraph(noOfItems)).setFontSize(8));
            combinedTable.addCell(new Cell().add(new Paragraph(block)).setFontSize(8));
            combinedTable.addCell(new Cell().add(new Paragraph(fieldSurvey)).setFontSize(8));
            combinedTable.addCell(new Cell().add(new Paragraph(dataType)).setFontSize(8));

            combinedTable.addCell(new Cell().add(new Paragraph(String.valueOf(comments))).setFontSize(8));

        }

        //  Paragraph shipmentSummary= new Paragraph("Shipment Summary:").setPaddings(0,15,35,0).setTextAlignment(TextAlignment.LEFT);
        if (shipmentSummary == null) {
            combinedTable.addCell(new Cell(1, 7).add(new Paragraph("Shipment Summary:").setPaddings(0, 15, 25, 0)).setFontSize(8).setBold());

        } else {
            //combinedTable.addCell(new Cell(1, 7).add(new Paragraph("Shipment Summary: \n" + shipmentSummary).setPaddings(0, 15, 25, 0)).setFontSize(8));
            combinedTable.addCell(new Cell(1, 7)
                    .add(new Paragraph()
                            .add(new Text("Shipment Summary:").setBold())
                            .add(new Text("\n" + shipmentSummary))
                            .setPaddings(0, 15, 25, 0))
                    .setFontSize(8));
        }
        //combinedTable.addCell(new Cell(1, 4).add(shipmentSummary);
//        Cell cell = new Cell(1, 4).add(shipmentSummary);
//        cell.setVerticalAlignment(VerticalAlignment.TOP);
//combinedTable.addCell(cell);
        Cell detailCell = new Cell().add(combinedTable).setBorder(Border.NO_BORDER)
                .setWidth(UnitValue.createPercentValue(48))
                .setPaddingLeft(10);
        document.add(combinedTable);
        parentTable.addCell(detailCell);
        document.add(new Paragraph("PLEASE ACKNOWLEDGE RECEIPT OF DATA BY SIGNING, AND RETURNING ONE COPY OF THIS TRANSMITTAL, FOR THE ATTENTION OF THE SENDER, TO THE ADDRESS ABOVE").setFontSize(6).setBold());

        // Add acknowledgment section
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("RECEIVED BY:").setFontSize(12).setBold());

        Paragraph signatureDateParagraph = new Paragraph()
                .addTabStops(new TabStop(1000, TabAlignment.RIGHT))
                .add(new Text("Signature: ____________________").setFontSize(12).setBold())
                .add(new Tab())
                .add(new Text("Date: _________________________").setFontSize(12).setBold());
        document.add(signatureDateParagraph);
        document.add(new Paragraph("Print Name: ___________________").setFontSize(12).setBold());


        document.close();
        File originalFile = new File("example.pdf");
        try (FileOutputStream fos = new FileOutputStream(originalFile)) {
            byteArrayOutputStream.writeTo(fos);
        }

//        JSONObject uploadFmResponse = fileServiceExt.uploadFile(document);
//        JSONArray jsonArray = (JSONArray) uploadFmResponse.get("files");
//        JSONObject jsonObject = (JSONObject) jsonArray.getJSONObject(0);
//        if (originalFile.delete()){
//            if (originalFile.getParentFile().delete()) {
//                logger.info("File "+originalFile.getName()+" Removed successfully from "+originalFile.getPath());
//            }
//        }
//        return jsonObject.get("url").toString();
//
//
//
////        someMethod(originalFile);
        return byteArrayOutputStream.toByteArray();
    }
}

