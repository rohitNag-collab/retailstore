package com.abc.retailstore.service;

import com.abc.retailstore.model.Bill;

public interface INetPayableAmountService {

  double calculateNetPayableAmount(Bill bill);
}
