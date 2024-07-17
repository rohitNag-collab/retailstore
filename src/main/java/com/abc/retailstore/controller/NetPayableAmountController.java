package com.abc.retailstore.controller;

import com.abc.retailstore.model.Bill;
import com.abc.retailstore.service.INetPayableAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bill")
public class NetPayableAmountController {

    @Autowired
    private INetPayableAmountService netPayableAmountService;

    @PostMapping("/netpayableamount")
    public double calculate(@RequestBody Bill bill) {
        return netPayableAmountService.calculateNetPayableAmount(bill);
    }

}
