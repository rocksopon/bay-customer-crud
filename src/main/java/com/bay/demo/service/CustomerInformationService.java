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

}
