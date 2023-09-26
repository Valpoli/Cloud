package com.epita.cloud.services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.epita.cloud.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    @Query("SELECT c.name FROM Vehicle v " +
           "INNER JOIN v.vehicleType vt " +
           "INNER JOIN vt.company c " +
           "WHERE v.vin LIKE :vin")
    String findCompanyNameByVin(String vin);
}
