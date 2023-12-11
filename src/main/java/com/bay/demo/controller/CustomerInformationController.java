package com.bay.demo.controller;

import com.bay.demo.repository.CustomerInformation;
import com.bay.demo.service.CustomerInformationService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerInformationController {

    @Autowired
    private CustomerInformationService customerInformationService;

    @GetMapping("/customers")
    @PreAuthorize("hasRole('MAKER')")
    @ApiOperation(value = "Get all customers information")
    public List<CustomerInformation> getAllCustomerInformation() {
        return customerInformationService.getAllCustomerInformation();
    }
}
