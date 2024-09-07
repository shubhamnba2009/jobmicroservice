package org.example.springpractise.company;

import java.util.List;

public interface CompanyService {
	List<Company> getAllCompany();
	boolean updateCompany(Long id, Company company);
	void addCompany(Company company);
	boolean deleteCompanyById(Long id);
	Company getCompanyById(Long id);
}
