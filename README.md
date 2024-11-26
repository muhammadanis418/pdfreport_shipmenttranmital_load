# PDF Report_shipmenttranmital_load
PDF file generation for shipment Transmital Load

## For local testing
[URL](http://localhost:8083/pdf/reportGeneration)


## Parameters of Request body
{
  "toName": "DRDR Office",
  "toAddress": "Beach Three Building, 0 floor, Office wer, P.O Box: 103, P.C: 000, oooooo, kkkkkk, Sultanate of Oman",
  "toContact": "+968 24649012",
  "fromName": "wanwan Malma",
  "fromAddress": "Operator target",
  "fromEmail": "wanwan.lilik@rghgetkkk.com",
  "targetReferenceNo": "1234",
  "date": "2022-05-20",
  "viaNumber": "",
  "waybill": "",
  "tableData": [
    {
      "mediaType": "Started",
      "numberOfItems": "for testing purpose",
      "block": "A",
      "fieldSurvey": "testing the field",
      "dataType": "Not mention",
      "comments": "Testing phase Started"
    },
    {
      "mediaType": "LoadPdfApplication",
      "numberOfItems": "only for testing purpose",
      "block": "B",
      "fieldSurvey": "testing the field",
      "dataType": "Not mention",
      "comments": "Testing phase Started"
    },
    {
      "mediaType": "PdfApplication",
      "numberOfItems": "only for testing purpose",
      "block": "C",
      "fieldSurvey": "testing the field",
      "dataType": "Not mention",
      "comments": "Testing phase Started"
    }
  ],
  "shipmentSummary": "we are testing this report by giving dump data. The POM for is missing, no dependency information available even though it exists in maven repository."
}

## View 
[URL](https://github.com/muhammadanis418/pdfreport_shipmenttranmital_load/blob/master/example.pdf)

