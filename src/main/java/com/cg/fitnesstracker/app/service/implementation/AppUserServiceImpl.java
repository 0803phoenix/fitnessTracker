package com.cg.fitnesstracker.app.service.implementation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fitnesstracker.app.exceptions.ApplicationException;
import com.cg.fitnesstracker.app.model.AppUser;
import com.cg.fitnesstracker.app.repository.AppUserRepository;
import com.cg.fitnesstracker.app.service.AppUserService;
@Component
public class AppUserServiceImpl implements AppUserService{
	@Autowired
	private AppUserRepository appUserRepository;

	//To add app user
	@Override
	public AppUser addAppUserService(AppUser appUser) {
		AppUser user = appUserRepository.save(appUser);
		return user;
	}
	
	//To update customer password
	@Transactional
	@Override
	public AppUser updateCustomerPasswordService(String password, String username) {
		AppUser appUser = appUserRepository.findByUsername(username);
		int c=appUserRepository.updatePassword(password,appUser.getUserId());
		if(c>0) 
			return appUserRepository.findById(appUser.getUserId()).get();
		throw new ApplicationException("User doesn't exists. Can't update", 404);
	}
	
	public List<AppUser> getAllAppUsers(){
		List<AppUser> users = new ArrayList<>(); 
		Iterable<AppUser> userList = appUserRepository.findAll();
		userList.forEach(u->users.add(u));
		return users;
	}
}
