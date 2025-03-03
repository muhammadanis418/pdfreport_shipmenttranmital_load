package com.example.shipment_transmital_load_pdf.controller;

import com.example.shipment_transmital_load_pdf.dto.PdfRequest;
import com.example.shipment_transmital_load_pdf.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/pdf")
public class PdfController {

    private PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/reportGeneration")
    public ResponseEntity<byte[]> generatePdf(@RequestBody PdfRequest pdfRequest) throws IOException {
        byte[] pdfBytes = pdfService.generatePdf(
                // pdfRequest.getLogoPath(),
                pdfRequest.getToName(), pdfRequest.getToAddress(), pdfRequest.getToContact(), pdfRequest.getFromName(), pdfRequest.getFromAddress(), pdfRequest.getFromEmail(), pdfRequest.getTargetReferenceNo(), pdfRequest.getDate(), pdfRequest.getViaNumber(), pdfRequest.getWaybill(), pdfRequest.getTableData(), pdfRequest.getShipmentSummary());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
