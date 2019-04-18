package org.com.esti.service;

import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

  //  UserServiceModel findUserById(Long id);

    UserServiceModel editUserPassword(UserServiceModel userServiceModel, String oldPassword);

    UserPersonalServiceModel editUserPersonal(Long id,UserPersonalServiceModel userPersonalServiceModel);

}
