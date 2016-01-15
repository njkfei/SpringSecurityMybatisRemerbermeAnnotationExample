package xyz.hollysys.springsecurity.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.hollysys.springsecurity.dao.UserDao;
import xyz.hollysys.springsecurity.dao.UserProfileDao;
import xyz.hollysys.springsecurity.model.User;
import xyz.hollysys.springsecurity.model.UserProfile;

@Service("userService")
public class UserServiceImpl implements UserService{
	static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired 
	@Qualifier("userProfileDao")
	UserProfileDao userProfileDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		logger.info("findById id : " + id);
		return userDao.findById(id);
	}

	public User findBySso(String sso) {
		logger.info("findBySso sso : " + sso);
		return userDao.findBySSO(sso);
	}

	public List<UserProfile> getUserProfiles(int user_id){
		logger.info("getUserProfiles user_id : " + user_id);
		return userProfileDao.getUserProfiles(user_id);
	}

	@Transactional
	public void save(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setState("Active");
		userDao.save(user);
		
		int userid = userDao.findBySSO(user.getSsoId()).getId();
		
		for(UserProfile profile : user.getUserProfiles()){
			logger.info(profile.toString());
			userProfileDao.insert(userid, userProfileDao.findTypeId(profile.getType()));
		}
	}

	public int getUserId() {
		int id = userDao.getUserId();
		logger.info("getUserProfiles user_id :" + id );
		return id;
	}
}
