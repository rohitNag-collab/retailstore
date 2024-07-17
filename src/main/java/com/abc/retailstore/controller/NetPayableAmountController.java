package com.abc.retailstore.controller;

import com.abc.retailstore.exception.BillNotFoundException;
import com.abc.retailstore.service.INetPayableAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bill")
public class NetPayableAmountController {

  @Autowired private INetPayableAmountService netPayableAmountService;

  @GetMapping("/netpayableamount/{billId}")
  public ResponseEntity<Double> calculate(@PathVariable Integer billId)
      throws BillNotFoundException {
    return ResponseEntity.ok(netPayableAmountService.calculateNetPayableAmount(billId));
  }

  @ExceptionHandler(BillNotFoundException.class)
  public ResponseEntity<String> handleBillNotFoundException(BillNotFoundException ex) {
    return ResponseEntity.status(404).body(ex.getMessage());
  }
}
