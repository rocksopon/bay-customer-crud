package com.bay.demo;

import com.bay.demo.repository.CustomerInformation;
import com.bay.demo.repository.CustomerInformationRepository;
import com.bay.demo.service.CustomerInformationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@InjectMocks
	private CustomerInformationService customerInformationService;

	@Mock
	private CustomerInformation customerInformation;

	@Mock
	private CustomerInformationRepository customerInformationRepository;

}
