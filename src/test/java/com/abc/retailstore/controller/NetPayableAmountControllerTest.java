package com.abc.retailstore.controller;

import com.abc.retailstore.model.Bill;
import com.abc.retailstore.model.User;
import com.abc.retailstore.service.NetPayableAmountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest
class NetPayableAmountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NetPayableAmountService netPayableAmountService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateEmployeeDiscountTest() throws Exception {
        User user = new User("","", true, false, LocalDate.parse("2023-09-23"));
        Bill bill = new Bill(user,12000.0, 1000.0);
        Mockito.when(netPayableAmountService.calculateNetPayableAmount(Mockito.any(Bill.class))).thenReturn(8100.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bill/netpayableamount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bill)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("8100.0"));
    }
}
