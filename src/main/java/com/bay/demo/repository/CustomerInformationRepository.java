package com.bay.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerInformationRepository extends JpaRepository<CustomerInformation, Long> {
    Optional<CustomerInformation> findByCif(long cif);
}
