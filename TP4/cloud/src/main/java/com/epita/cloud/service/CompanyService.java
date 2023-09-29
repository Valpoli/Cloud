package com.epita.cloud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epita.cloud.dto.CompanyDTO;
import com.epita.cloud.model.Company;
import com.epita.cloud.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    
    public String findCompanyNameByVin(String vin)
    {
        return companyRepository.findCompanyNameByVin(vin);
    }

    public Optional<Company> findCompanyByID(Integer ID)
    {
        return companyRepository.findById(ID);
    }
    
    public List<CompanyDTO> getAllCompany()
    {
        return companyRepository.findAll().stream().map(company -> new CompanyDTO(
            company.getId(),
            company.getName()
        )).collect(Collectors.toList());
    }

    public Company save(Company vt)
    {
        companyRepository.save(vt);
        return vt;
    }

    public Optional<Company> checkCompanyExistence(String companyName)
    {
        return companyRepository.checkCompanyExistence(companyName);
    }

    public void deleteCompVehicule(Integer cID)
    {
        companyRepository.deleteCompVehicule(cID);
    }

    public void deleteCompVT(Integer cID)
    {
        companyRepository.deleteCompVT(cID);
    }

    public void deleteCompany(Integer cID)
    {
        companyRepository.deleteCompany(cID);
    }

}
