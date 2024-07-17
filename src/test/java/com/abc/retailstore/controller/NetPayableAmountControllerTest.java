package com.abc.retailstore.controller;

import com.abc.retailstore.service.NetPayableAmountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class NetPayableAmountControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private NetPayableAmountService netPayableAmountService;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void calculateEmployeeDiscountTest() throws Exception {

    Mockito.when(netPayableAmountService.calculateNetPayableAmount(Mockito.any(Integer.class)))
        .thenReturn(8100.0);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/bill/netpayableamount/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("8100.0"));
  }
}
