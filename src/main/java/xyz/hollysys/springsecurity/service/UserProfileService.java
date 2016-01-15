package xyz.hollysys.springsecurity.service;

import java.util.List;

import xyz.hollysys.springsecurity.model.User;
import xyz.hollysys.springsecurity.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);
	
	List<UserProfile> getUserProfiles(int user_id);
	
	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
}