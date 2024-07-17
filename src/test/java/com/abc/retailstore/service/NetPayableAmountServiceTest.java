package com.abc.retailstore.service;

import com.abc.retailstore.model.Bill;
import com.abc.retailstore.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class NetPayableAmountServiceTest {

    @InjectMocks
    private NetPayableAmountService netPayableAmountService;

    @Mock
    private Bill mockBill;

    public NetPayableAmountServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEmployeeDiscount() {
        when(mockBill.getTotalAmount()).thenReturn(12000.0);
        when(mockBill.getGroceryAmount()).thenReturn(1000.0);
        when(mockBill.getUser()).thenReturn(new User("aad", "asads", true, false, LocalDate.parse("2023-09-08")));

        double netAmount = netPayableAmountService.calculateNetPayableAmount(mockBill);
        assertEquals(8100, netAmount);
    }

    @Test
    void testEmployeeAffiliateDiscount() {
        when(mockBill.getTotalAmount()).thenReturn(12000.0);
        when(mockBill.getGroceryAmount()).thenReturn(1000.0);
        when(mockBill.getUser()).thenReturn(new User("aad", "asads", false, true, LocalDate.parse("2023-09-08")));

        double netAmount = netPayableAmountService.calculateNetPayableAmount(mockBill);
        assertEquals(10300, netAmount);
    }
}
