package org.com.esti.service;

import org.com.esti.models.service.UserPasswordServiceModel;
import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserPersonalServiceModel findById(Long id);

    UserPasswordServiceModel editUserPassword(Long id, UserPasswordServiceModel userPasswordServiceModel);

    UserPersonalServiceModel editUserPersonal(Long id, UserPersonalServiceModel userPersonalServiceModel);

}
