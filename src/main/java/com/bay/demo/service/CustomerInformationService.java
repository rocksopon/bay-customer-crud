package com.bay.demo.service;

import com.bay.demo.core.exception.NotFoundException;
import com.bay.demo.repository.CustomerInformation;
import com.bay.demo.repository.CustomerInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerInformationService {

    @Autowired
    private CustomerInformationRepository customerInformationRepository;

    public List<CustomerInformation> getAllCustomerInformation() {
        return customerInformationRepository.findAll();
    }

    public CustomerInformation getCustomerInformationById(long id) {
        var customerData = customerInformationRepository.findById(id);
        return customerData.orElseThrow(() -> new NotFoundException("CUSTOMER_ID_NOT_FOUND", "Customer ID " + id + " is not found"));
    }

    public CustomerInformation saveCustomerInformation(CustomerInformation entity) {
        return customerInformationRepository.save(entity);
    }

    public CustomerInformation updateCustomerInformation(long id, CustomerInformation request) {
        var customerData = customerInformationRepository.findById(id);
        if (customerData.isPresent()) {
            CustomerInformation customerInformation = customerData.get();
            customerInformation.setEmail(request.getEmail());
            customerInformation.setNationality(request.getNationality());
            customerInformation.setTitleEn(request.getTitleEn());
            customerInformation.setFirstNameEn(request.getFirstNameEn());
            customerInformation.setLastNameEn(request.getLastNameEn());
            customerInformation.setMiddleNameEn(request.getMiddleNameEn());
            customerInformation.setDateOfBirth(request.getDateOfBirth());
            return customerInformationRepository.save(customerInformation);
        } else {
            throw new NotFoundException("CUSTOMER_ID_NOT_FOUND", "Customer ID " + id + " is not found");
        }
    }

    public void deleteCustomerInformationById(long id) {
        var customerData = customerInformationRepository.findById(id);
        if (customerData.isPresent()) {
            customerInformationRepository.deleteById(id);
        } else {
            throw new NotFoundException("CUSTOMER_ID_NOT_FOUND", "Customer ID " + id + " is not found");
        }
    }
}
