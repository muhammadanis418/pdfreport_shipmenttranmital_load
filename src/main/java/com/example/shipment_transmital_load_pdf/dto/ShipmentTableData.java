package com.example.shipment_transmital_load_pdf.dto;





public class ShipmentTableData {

   // private Integer number;
    private String mediaType;



    private String numberOfItems;

    private String block;

    private String fieldSurvey;

    private String dataType;
    private String comments;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFieldSurvey() {
        return fieldSurvey;
    }

    public void setFieldSurvey(String fieldSurvey) {
        this.fieldSurvey = fieldSurvey;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
       this. comments = comments;
    }

    public String getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(String numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
