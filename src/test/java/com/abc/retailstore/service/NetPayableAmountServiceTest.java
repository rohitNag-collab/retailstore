package com.abc.retailstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.abc.retailstore.exception.BillNotFoundException;
import com.abc.retailstore.model.Bill;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NetPayableAmountServiceTest {

  @InjectMocks private NetPayableAmountService netPayableAmountService;

  @Mock private Bill mockBill;

  public NetPayableAmountServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testEmployeeDiscount() throws BillNotFoundException {
    when(mockBill.getTotalAmount()).thenReturn(12000.0);
    when(mockBill.getBillID()).thenReturn(1);
    when(mockBill.getUserId()).thenReturn(1);
    when(mockBill.getProducts()).thenReturn(Utils.getProducts());

    double netAmount = netPayableAmountService.calculateNetPayableAmount(1);
    assertEquals(8100, netAmount);
  }
}
