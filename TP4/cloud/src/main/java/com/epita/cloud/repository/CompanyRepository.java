package com.epita.cloud.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epita.cloud.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    @Query("SELECT c.name FROM Vehicle v " +
           "INNER JOIN v.vehicleType vt " +
           "INNER JOIN vt.company c " +
           "WHERE v.vin LIKE :vin")
    String findCompanyNameByVin(String vin);

    @Query("SELECT name FROM Company c WHERE c.name LIKE :newCompanyName")
    Optional<Company> checkCompanyExistence(String newCompanyName);

    @Modifying
    @Query(value = "DELETE FROM Vehicle v " +
                "WHERE v.vehicleType IN " +
                "(SELECT vt FROM VehicleType vt WHERE vt.company.id = :companyId)")
    void deleteCompVehicule(Integer companyId);

    @Modifying
    @Query("DELETE FROM VehicleType vt WHERE vt.company.id = :companyId")
    void deleteCompVT(Integer companyId);

    @Modifying
    @Query("DELETE FROM Company c WHERE c.id = :companyId")
    void deleteCompany(Integer companyId);
}
