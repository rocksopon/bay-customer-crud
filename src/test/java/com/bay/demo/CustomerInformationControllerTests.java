package com.bay.demo;

import com.bay.demo.controller.CustomerInformationController;
import com.bay.demo.repository.CustomerInformation;
import com.bay.demo.repository.CustomerInformationRepository;
import com.bay.demo.service.CustomerInformationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerInformationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerInformationControllerTests {

    @MockBean
    private CustomerInformationRepository customerInformationRepository;

    @MockBean
    private CustomerInformationService customerInformationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCustomers() throws Exception {
        var customerInformationList = List.of(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com"));

        when(customerInformationService.getAllCustomerInformation()).thenReturn(customerInformationList);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());
    }

    @Test
    void getCustomersById() throws Exception {
        var customerInformation = new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com");

        when(customerInformationService.getCustomerInformationById(1L)).thenReturn(customerInformation);

        long id = 1L;

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstNameEn").value(customerInformation.getFirstNameEn()))
                .andDo(print());
    }

    @Test
    void createNewCustomers() throws Exception {

        String sDate1="23/07/1993";
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);

        var customerInformation = new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", date1, "THA", "rocksopon@gmail.com");

        when(customerInformationService.saveCustomerInformation(customerInformation)).thenReturn(customerInformation);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerInformation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstNameEn").value(customerInformation.getFirstNameEn()))
                .andDo(print());
    }

    @Test
    void updateExistCustomers() throws Exception {

        String sDate1="23/07/1993";
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);

        var customerInformation = new CustomerInformation(1, "Mrs.", "Koravit", "Sitti", "", date1, "THA", "koravit.s@gmail.com");
        long id = 1L;

        when(customerInformationService.updateCustomerInformation(id, customerInformation)).thenReturn(customerInformation);

        mockMvc.perform(put("/api/customers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerInformation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstNameEn").value(customerInformation.getFirstNameEn()))
                .andDo(print());
    }

    @Test
    void deleteCustomersById() throws Exception {

        long id = 1L;

        doNothing().when(customerInformationService).deleteCustomerInformationById(id);

        mockMvc.perform(delete("/api/customers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
