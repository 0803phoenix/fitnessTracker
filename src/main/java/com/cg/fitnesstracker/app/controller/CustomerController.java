package com.cg.fitnesstracker.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fitnesstracker.app.dto.UpdateCustomerHeight;
import com.cg.fitnesstracker.app.dto.UpdateCustomerWeight;
import com.cg.fitnesstracker.app.dto.UpdateEmailDto;
import com.cg.fitnesstracker.app.model.AppUser;
import com.cg.fitnesstracker.app.model.Customer;
import com.cg.fitnesstracker.app.service.CustomerService;

@RestController
@RequestMapping("/fitness/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Customer')")
	public ResponseEntity<AppUser> addCustomerDetails(Principal p,@RequestBody Customer customer){
		return new ResponseEntity<AppUser>(customerService.addCustomerDetailService(p.getName(),customer),HttpStatus.OK);
	}
	
	
	@PutMapping(value="/weight",produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Customer')")
	public ResponseEntity<Customer> updateCustomerWeight(Principal p,@RequestBody UpdateCustomerWeight ucw){
		Customer c =customerService.updateCustomerWeightService(p.getName(), ucw.getUpdatedWeight());
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	
	@PutMapping(value="/height",produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Customer')")
	public ResponseEntity<Customer> updateCustomerHeight(Principal p,@RequestBody UpdateCustomerHeight uch){
		Customer c = customerService.updateCustomerHeightService(p.getName(), uch.getUpdatedHeight());
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	@PutMapping(value = "/email",produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
	@PreAuthorize("hasAuthority('Customer')")
	public ResponseEntity<AppUser> updateEmail(Principal p,@RequestBody UpdateEmailDto updateEmailDto){
		AppUser appUser = customerService.updateCustomerEmailService(p.getName(),updateEmailDto.getNewEmail());
		return new ResponseEntity<AppUser>(appUser,HttpStatus.OK);
	}
	
//	@GetMapping(value="/toggle_status", produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
//	@PreAuthorize("hasAuthority('Customer')")
//	public ResponseEntity<Customer> toggleCustomerStatus(Principal p)
//	{
//		Customer customer=customerService.toggleCustomerStatus(p.getName());
//		
//		return new ResponseEntity<>(customer, HttpStatus.OK);
//	}
}
