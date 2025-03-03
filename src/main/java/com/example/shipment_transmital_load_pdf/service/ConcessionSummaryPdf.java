/*
 * @deprecated: This code is currently not in use but is retained for future reference or potential use.
 * @reason: It may be required later.
 */
//package com.example.shipment_transmital_load_pdf.service;
//import com.itextpdf.io.exceptions.IOException;
//import com.itextpdf.io.image.ImageData;
//import com.itextpdf.io.image.ImageDataFactory;
//import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.*;
//import com.itextpdf.layout.properties.HorizontalAlignment;
//import com.itextpdf.layout.properties.TextAlignment;
//import com.itextpdf.layout.properties.UnitValue;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.net.MalformedURLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//    @Service
//    public class ConcessionSummaryPdf {
//
//        private static final String LOGO_PATH="classpath:images/target-logo.png";
//
//        @Autowired
//        private FileServiceExt fileServiceExt;
//
//        private static final Logger logger = LoggerFactory.getLogger(ConcessionSummaryPdf.class);
//
//        public String generateConcessionSummaryPdf(List<ConcessionSummary> summaries) throws Exception {
//
//            LocalDateTime ldf = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddSSS");  // for checking purpose yyyyMMddHHmmssSSS
//            String formattedDate = ldf.format(formatter);
//            long dateWithMilliseconds = Long.parseLong(formattedDate);
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
//            PdfDocument pdfDoc = new PdfDocument(writer);
//
//            Document document = new Document(pdfDoc);
//
//            ImageData imageData = null;
//
//            try {
//                imageData = ImageDataFactory.create(LOGO_PATH);
//
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
//
//
//            Image logo = new Image(imageData);
//            logo.setHorizontalAlignment(HorizontalAlignment.LEFT);
//
//            logo.scaleToFit(81.20f, 70.20f);
//
//
//            document.add(logo);
//
//            document.add(new Paragraph("\n"));
//
//            document.add(new Paragraph("Concession Summary")
//                    .setFontColor(ColorConstants.BLACK)
//                    .setBold()
//                    .setFontSize(16)
//                    .setTextAlignment(TextAlignment.CENTER));
//            document.add(new Paragraph("\n"));
//
//            Paragraph paragraph = new Paragraph();
//            paragraph.add(new Text("Concession Name:  ").setFontColor(ColorConstants.BLACK).setBold().setFontSize(14));
//            Text concessionName = new Text(summaries.get(0).getConcessionName())
//                    .setFontColor(ColorConstants.BLACK)
//                    .setBold()
//                    .setFontSize(14);
//            paragraph.add(concessionName);
//            paragraph.setTextAlignment(TextAlignment.LEFT);
//            document.add(paragraph);
//
//
//            Map<String, List<ConcessionSummary>> groupedSummaries = summaries.stream()
//                    .collect(Collectors.groupingBy(ConcessionSummary::getSchemaName));
//
//            for (Map.Entry<String, List<ConcessionSummary>> entry : groupedSummaries.entrySet()) {
//                String schemaName = entry.getKey();
//                List<ConcessionSummary> schemaSummaries = entry.getValue();
//                float[] columnWidths = {1, 1, 1, 1, 1, 1};
//
//                Table table = new Table(columnWidths);
//                table.setWidth(UnitValue.createPercentValue(100));
//                Cell schemaCell = new Cell(1, 6).add(new Paragraph(schemaName + "\n")
//                        .setFontColor(ColorConstants.BLACK)
//                        .setBold()
//                        .setFontSize(14)
//                        .setTextAlignment(TextAlignment.CENTER));
//                table.addHeaderCell(schemaCell);
//
//
//                table.addCell(new Cell().add(new Paragraph("Catalog").setFontSize(10)).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
//
//                table.addCell(new Cell().add(new Paragraph("Count").setFontSize(10)).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
//
//                table.addCell(new Cell().add(new Paragraph("Size").setFontSize(10)).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
//
//                table.addCell(new Cell().add(new Paragraph("Area").setFontSize(10)).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
//
//                table.addCell(new Cell().add(new Paragraph("Length").setFontSize(10)).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
//
//                table.addCell(new Cell().add(new Paragraph("Message").setFontSize(10)).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
//
//
//                for (ConcessionSummary summary : schemaSummaries) {
//                    table.addCell(summary.getCatalogName()).setFontSize(8);
//                    table.addCell(String.valueOf(summary.getCount())).setFontSize(8);
//                    table.addCell(String.valueOf(summary.getSize())).setFontSize(8);
//                    table.addCell(String.valueOf(summary.getArea())).setFontSize(8);
//                    table.addCell(String.valueOf(summary.getLength())).setFontSize(8);
//                    table.addCell(summary.getMessage()).setFontSize(8);
//                }
//                document.add(new Paragraph("\n"));
//                document.add(table);
//            }
//
//            document.close();
//
//            File concessionSummaryPdf = new File("ConcessionSummary_" + dateWithMilliseconds + ".pdf");
//            try (FileOutputStream fos = new FileOutputStream(concessionSummaryPdf)) {
//                byteArrayOutputStream.writeTo(fos);
//            } catch (IOException | java.io.IOException e) {
//                e.printStackTrace();
//            }
//            JSONObject uploadFmResponse = fileServiceExt.uploadFile(concessionSummaryPdf);
//            JSONArray jsonArray = (JSONArray) uploadFmResponse.get("files");
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
//            if(concessionSummaryPdf.delete()){
//                logger.info("File " + concessionSummaryPdf.getName() + " Removed successfully from " + concessionSummaryPdf.getPath());
//            }
//            return jsonObject.get("url").toString();
//        }
//    }
//
//}
