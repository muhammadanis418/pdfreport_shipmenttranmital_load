package com.example.shipment_transmital_load_pdf.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.*;



public class PdfRequest {


    //  private String logoPath;
    private String toName;
    private String toAddress;
    private String toContact;
    private String fromName;
    private String fromAddress;
    private String fromEmail;
//        private String fromContact;

    private Integer TargetReferenceNo;
//    private String transmittalNumber;
    private Date date;

    private List<ShipmentTableData> tableData = new ArrayList<>();


    private String waybill;
    //private String[][] tableData;

    // private Map<Integer, Map<String,Integer>>tableData= new HashMap<>();
    private String viaNumber;

    private String shipmentSummary;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getToContact() {
        return toContact;
    }

    public void setToContact(String toContact) {
        this.toContact = toContact;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

//    public String getFromContact() {
//        return fromContact;
//    }
//
//    public void setFromContact(String fromContact) {
//        this.fromContact = fromContact;
//    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

//    public String[][] getTableData() {
//        return tableData;
//    }
//
//    public void setTableData(String[][] tableData) {
//        this.tableData = tableData;
//    }

    public Integer getTargetReferenceNo() {
        return TargetReferenceNo;
    }

    public void setTargetReferenceNo(Integer targetReferenceNo) {
        TargetReferenceNo = targetReferenceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


//    public Map<Integer, Map<String, Integer>> getTableData() {
//        return tableData;
//    }
//
//    public void setTableData(Map<Integer, Map<String, Integer>> tableData) {
//        this.tableData = tableData;
//    }

    public String getViaNumber() {
        return viaNumber;
    }

    public void setViaNumber(String viaNumber) {
        this.viaNumber = viaNumber;
    }

    public List<ShipmentTableData> getTableData() {
        return tableData;
    }

    public void setTableData(List<ShipmentTableData> tableData) {
        this.tableData = tableData;
    }

    public String getShipmentSummary() {
        return shipmentSummary;
    }

    public void setShipmentSummary(String shipmentSummary) {
        this.shipmentSummary = shipmentSummary;
    }

}


