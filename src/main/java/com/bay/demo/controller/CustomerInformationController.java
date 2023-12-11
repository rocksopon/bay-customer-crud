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

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasRole('MAKER')")
    @ApiOperation(value = "Get customers information by ID")
    public CustomerInformation getCustomerInformationById(@PathVariable(required = true) long id) {
        return customerInformationService.getCustomerInformationById(id);
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('MAKER')")
    @ApiOperation(value = "Save new customers information")
    public CustomerInformation saveCustomerInformation(@Valid @RequestBody CustomerInformation customerInformation) {
        return customerInformationService.saveCustomerInformation(customerInformation);
    }

    @PutMapping("/customers/{id}")
    @PreAuthorize("hasRole('MAKER')")
    @ApiOperation(value = "Update customers information by ID")
    public CustomerInformation updateCustomerInformation(@PathVariable(required = true) long id, @RequestBody CustomerInformation customerInformation) {
        return customerInformationService.updateCustomerInformation(id, customerInformation);
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasRole('MAKER')")
    @ApiOperation(value = "Delete customers information by ID")
    public void deleteCustomerInformationById(@PathVariable(required = true) long id) {
        customerInformationService.deleteCustomerInformationById(id);
    }
}
