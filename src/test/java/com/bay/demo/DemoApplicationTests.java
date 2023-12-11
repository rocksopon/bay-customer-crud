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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class DemoApplicationTests {

	@InjectMocks
	private CustomerInformationService customerInformationService;

	@Mock
	private CustomerInformationRepository customerInformationRepository;

	@Test
	public void getAllCustomersShouldReturn1Size() {
		when(customerInformationRepository.findAll())
				.thenReturn(List.of(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com"),
						new CustomerInformation(2, "Mrs.", "Koravit", "Sitti", "", new Date(), "THA", "koravit.s@gmail.com")));

		assertEquals(2, customerInformationService.getAllCustomerInformation().size());
	}

	@Test
	public void getAllCustomerByIdShouldReturnId1() {
		when(customerInformationRepository.findById(1L))
				.thenReturn(Optional.of(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com")));

		assertEquals(1, customerInformationService.getCustomerInformationById(1).getCif());
	}

	@Test
	public void saveCustomerByIdShouldReturnFirstNameSopon() {
		CustomerInformation customerEntity = CustomerInformation.builder()
				.titleEn("Mrs.")
				.firstNameEn("Sopon")
				.lastNameEn("Jamreankit")
				.middleNameEn("")
				.dateOfBirth(new Date())
				.email("rocksopon@gmail.com")
				.build();

		when(customerInformationRepository.save(customerEntity))
				.thenReturn(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com"));

		assertEquals("Sopon", customerInformationService.saveCustomerInformation(customerEntity).getFirstNameEn());
	}

	@Test
	public void updateFirstNameFromSoponToKoravitShouldBeKoravit() {
		CustomerInformation newCustomerEntity = CustomerInformation.builder()
				.cif(1)
				.titleEn("Mrs.")
				.firstNameEn("Koravit")
				.lastNameEn("Sitti")
				.middleNameEn("")
				.dateOfBirth(new Date())
				.email("koravit.s@gmail.com")
				.build();

		when(customerInformationRepository.findById(1L))
				.thenReturn(Optional.of(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com")));

		when(customerInformationRepository.save(newCustomerEntity))
				.thenReturn(newCustomerEntity);


		assertEquals("Koravit", customerInformationService.updateCustomerInformation(1, newCustomerEntity).getFirstNameEn());
	}

	@Test
	public void deleteId1ShouldBeDeleted() {
		when(customerInformationRepository.findById(1L))
				.thenReturn(Optional.of(new CustomerInformation(1, "Mrs.", "Sopon", "Jamreankit", "", new Date(), "THA", "rocksopon@gmail.com")));

		customerInformationService.deleteCustomerInformationById(1L);

		verify(customerInformationRepository, times(1)).deleteById(1L);
	}
}
