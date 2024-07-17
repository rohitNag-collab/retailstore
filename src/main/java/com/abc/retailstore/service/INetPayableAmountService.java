package com.abc.retailstore.service;

import com.abc.retailstore.exception.BillNotFoundException;

public interface INetPayableAmountService {

  double calculateNetPayableAmount(Integer billId) throws BillNotFoundException;
}
