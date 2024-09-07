package org.example.springpractise.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getCompany() {
		return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		if(companyService.updateCompany(id, company))
			return new ResponseEntity<>("Company updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Company record not found", HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		companyService.addCompany(company);
		return new ResponseEntity<>("Company Added Successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
		if(companyService.deleteCompanyById(id))
			return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Company record not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyService.getCompanyById(id);
		if(company != null) {
			return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
}
