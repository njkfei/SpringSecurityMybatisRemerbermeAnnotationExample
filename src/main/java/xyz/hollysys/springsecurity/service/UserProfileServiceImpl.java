package xyz.hollysys.springsecurity.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.hollysys.springsecurity.dao.UserDao;
import xyz.hollysys.springsecurity.dao.UserProfileDao;
import xyz.hollysys.springsecurity.model.User;
import xyz.hollysys.springsecurity.model.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	static Logger logger = Logger.getLogger(UserProfileServiceImpl.class);
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired 
	@Qualifier("userProfileDao")
	UserProfileDao userProfileDao;

	public UserProfile findById(int id) {
		logger.info("findById id : " + id);
		return userProfileDao.findById(id);
	}

	public List<UserProfile> getUserProfiles(int user_id){
		logger.info("getUserProfiles user_id : " + user_id);
		return userProfileDao.getUserProfiles(user_id);
	}

	public List<UserProfile> findAll() {
		logger.info("findAll ");
		return userProfileDao.findAll();
	}

	public UserProfile findByType(String type) {
		logger.info("findByType type : " + type);
		return userProfileDao.findByType(type);
	}
}
