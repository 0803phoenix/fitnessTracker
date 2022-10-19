package com.cg.fitnesstracker.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fitnesstracker.app.dto.UpdateEmailDto;
import com.cg.fitnesstracker.app.model.Admin;
import com.cg.fitnesstracker.app.model.AppUser;
import com.cg.fitnesstracker.app.model.Customer;
import com.cg.fitnesstracker.app.repository.AppUserRepository;
import com.cg.fitnesstracker.app.service.AdminService;
import com.cg.fitnesstracker.app.service.CustomerService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Admin> addAdmin(Principal p, @RequestBody Admin ad){
		return new ResponseEntity<Admin>(adminService.addAdminDetailService(p.getName(),ad),HttpStatus.OK);
	}
	
	@GetMapping(value = "/Customers", produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<Customer>> readAllCustomer() {
		List<Customer> custList = adminService.readAllCustomerDetailService();
		return new ResponseEntity<List<Customer>>(custList,HttpStatus.OK);	
	}
	
	@GetMapping(value="/Customer/{username}", produces = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Customer> readCustomerDetailById(@PathVariable String username){
		Customer cust = adminService.readCustomerDetailByIdService(username);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/Customer/{username}",  produces = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable String username){
		Customer cust = adminService.deleteCustomerByIdService(username);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@PutMapping(value = "/email",produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<AppUser> updateEmail(@RequestBody UpdateEmailDto updateEmailDto){
		AppUser appUser = adminService.updateAdminEmailService(updateEmailDto.getNewEmail(),updateEmailDto.getUsername());
		return new ResponseEntity<AppUser>(appUser,HttpStatus.OK);
	}
}
