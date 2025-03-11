package com.example.shipment_transmital_load_pdf.dto;

import lombok.Data;

import java.util.*;

@Data
public class PdfRequest {

    private String toName;
    private String toAddress;
    private String toContact;
    private String fromName;
    private String fromAddress;
    private String fromEmail;

    private Integer TargetReferenceNo;
    private Date date;
    private List<ShipmentTableData> tableData = new ArrayList<>();
    private String waybill;
    //private String[][] tableData;

    // private Map<Integer, Map<String,Integer>>tableData= new HashMap<>();
    private String viaNumber;
    private String shipmentSummary;


}


