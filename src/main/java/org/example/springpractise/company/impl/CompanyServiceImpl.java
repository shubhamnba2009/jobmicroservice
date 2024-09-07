package org.example.springpractise.company.impl;

import org.example.springpractise.company.Company;
import org.example.springpractise.company.CompanyRepository;
import org.example.springpractise.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
	CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}


	@Override
	public List<Company> getAllCompany() {
		return companyRepository.findAll();
	}

	@Override
	public boolean updateCompany(Long id, Company company) {
		Optional<Company> optionalCompany = companyRepository.findById(id);

		if(optionalCompany.isPresent()){
			Company company1 = optionalCompany.get();
			company1.setName(company.getName());
			company1.setDescription(company.getDescription());
			company1.setJobs(company.getJobs());
			companyRepository.save(company1);
			return true;
		}
		return false;
	}

	@Override
	public void addCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		try {
			companyRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}
}
