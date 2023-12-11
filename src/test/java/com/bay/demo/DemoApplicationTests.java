package com.bay.demo;

import com.bay.demo.repository.CustomerInformation;
import com.bay.demo.repository.CustomerInformationRepository;
import com.bay.demo.service.CustomerInformationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

	@InjectMocks
	private CustomerInformationService customerInformationService;

	@Mock
	private CustomerInformation customerInformation;

	@Mock
	private CustomerInformationRepository customerInformationRepository;

	@Test
	public void getAllCustomersShouldReturn1Size() {
		when(customerInformationRepository.findAll())
				.thenReturn(List.of(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com"),
						new CustomerInformation(2, "Mrs.", "Koravit", "Sitti", "", new Date(), "THA", "koravit.s@gmail.com")));

		assertEquals(2, customerInformationService.getAllCustomerInformation().size());
	}
}
